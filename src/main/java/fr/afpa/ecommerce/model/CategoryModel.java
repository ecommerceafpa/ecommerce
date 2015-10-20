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
    public Integer save(Category category) throws IOException, SQLException, ClassNotFoundException {

        String req;

        if (category.getParentCategoryId() != null) {
            req = "INSERT INTO category (name, parent_category_id) VALUES (?, ?)";
        } else {
            req = "INSERT INTO category (name) VALUES (?)";
        }

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, category.getName());

        if (category.getParentCategoryId() != null) {
            pstm.setInt(2, category.getParentCategoryId());
        }

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        Integer id = null;
        try (ResultSet rs = pstm.getGeneratedKeys()) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
            ConnectionUtil.close(rs);
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return id;
    }

    @Override
    public Category find(Integer id) throws IOException, SQLException, ClassNotFoundException {

        Category category = null;

        String req = "SELECT id, name, parent_category_id FROM category WHERE deleted = false AND id = ?";
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

    public List<Category> findParentCategories() throws IOException, SQLException, ClassNotFoundException {

        List<Category> categories = new ArrayList();

        String query = "SELECT id, name FROM category WHERE deleted = false  AND parent_category_id is null";
        Connection cnt = ConnectionFactory.getConnection();
        Statement statement = cnt.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            categories.add(category);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(statement);
        ConnectionUtil.close(cnt);

        return categories;
    }

    @Override
    public List<Category> findAll() throws IOException, SQLException, ClassNotFoundException {

        List<Category> categorys = new ArrayList<>();
        String req = "SELECT c1.id, c1.name, c2.name AS parent, c1.created, c1.updated FROM category c1 LEFT JOIN category c2 ON (c1.parent_category_id = c2.id) WHERE c1.deleted = false";
        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setParent(rs.getString("parent"));
            category.setCreated(rs.getTimestamp("created"));
            category.setUpdated(rs.getTimestamp("updated"));
            categorys.add(category);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return categorys;
    }

    @Override
    public void update(Category category) throws IOException, SQLException, ClassNotFoundException {

        String req;
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm;

        if (category.getParentCategoryId() != null) {
            req = "UPDATE category SET name = ?, parent_category_id = ? WHERE id = ?";
            pstm = cnt.prepareStatement(req);
            pstm.setInt(2, category.getParentCategoryId());
            pstm.setInt(3, category.getId());
        } else {
            req = "UPDATE category SET name = ? WHERE id = ?";
            pstm = cnt.prepareStatement(req);
            pstm.setInt(2, category.getId());
        }

        pstm.setString(1, category.getName());

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

    }

    @Override
    public void delete(Integer id) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE category SET deleted = 1 WHERE id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

}
