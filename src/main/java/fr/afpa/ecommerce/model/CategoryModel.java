package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Category;
import fr.afpa.ecommerce.jdbc.ConnectionFactory;
import fr.afpa.ecommerce.jdbc.ConnectionUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryModel implements Crud<Category> {

    @Override
    public Category save(Category category) throws IOException, SQLException, ClassNotFoundException {

        String req;

        if (category.getParentCategoryId() > 0) {
            req = "INSERT INTO category (name, parent_category_id) VALUES (?, ?);";
        } else {
            req = "INSERT INTO category (name) VALUES (?);";
        }

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, category.getName());

        if (category.getParentCategoryId() > 0) {
            pstm.setInt(2, category.getParentCategoryId());
        }

        int rows = pstm.executeUpdate();

        if (rows == 0) {
            throw new SQLException();
        }

        try (ResultSet rs = pstm.getGeneratedKeys()) {
            if (rs.next()) {
                category.setId(rs.getInt("id"));
            }
            ConnectionUtil.close(rs);
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        System.out.println(category.toString());

        return category;
    }

    @Override
    public Category find(int id) throws IOException, SQLException, ClassNotFoundException {
        Category category = null;

        String req = "SELECT id, name, parent_category_id FROM category WHERE deleted = false AND id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setParentCategoryId(rs.getInt("parent_category_id"));
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return category;
    }

    @Override
    public List<Category> findAll() throws IOException, SQLException, ClassNotFoundException {
        List<Category> categorys = new ArrayList<>();
        String req = "SELECT id,name, parent_category_id FROM category WHERE deleted = false;";
        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setParentCategoryId(rs.getInt("parent_category_id"));
            categorys.add(category);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return categorys;
    }

    @Override
    public boolean update(Category category) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE category SET name=?, parent_category_id=? WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, category.getName());
        pstm.setInt(2, category.getParentCategoryId());
        pstm.setInt(3, category.getId());

        int exe = pstm.executeUpdate();

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return exe > 0;
    }

    @Override
    public boolean delete(Category category) throws IOException, SQLException, ClassNotFoundException {
        String req = "UPDATE category SET deleted=1 WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, category.getId());

        int exe = pstm.executeUpdate();

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return exe > 0;
    }

}
