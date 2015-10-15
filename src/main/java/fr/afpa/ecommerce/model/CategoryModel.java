package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Author;
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

public class CategoryModel implements Crud<Author> {

    @Override
    public Author save(Author author) throws IOException, SQLException, ClassNotFoundException {

        String req = "INSERT INTO author (firstname, lastname, portrait) VALUES (?, ?, ?);";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, author.getFirstName());
        pstm.setString(2, author.getLastName());
        pstm.setString(3, author.getPortrait());

        int rows = pstm.executeUpdate();
        if (rows == 0) {
            throw new SQLException();
        }

        try (ResultSet rs = pstm.getGeneratedKeys()) {
            if (rs.next()) {
                author.setId(rs.getInt("id"));
            }
            ConnectionUtil.close(rs);
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return author;
    }

    @Override
    public Author find(int id) throws IOException, SQLException, ClassNotFoundException {
        Author author = null;

        String req = "SELECT id, firstname, lastname, portrait FROM author WHERE deleted = false AND id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            author = new Author();
            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("firstname"));
            author.setLastName(rs.getString("lastname"));
            author.setPortrait(rs.getString("portrait"));
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return author;
    }

    @Override
    public List<Author> findAll() throws IOException, SQLException, ClassNotFoundException {
        List<Author> authors = new ArrayList<>();
        String req = "SELECT id,firstname,lastname,portrait FROM author WHERE deleted = false;";
        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("firstname"));
            author.setLastName(rs.getString("lastname"));
            author.setPortrait(rs.getString("portrait"));
            authors.add(author);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return authors;
    }

    @Override
    public boolean update(Author author) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE author SET firstname=?, lastname=?, portrait=? WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, author.getFirstName());
        pstm.setString(2, author.getLastName());
        pstm.setString(3, author.getPortrait());
        pstm.setInt(4, author.getId());

        int exe = pstm.executeUpdate();

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return exe > 0;
    }

    @Override
    public boolean delete(Author author) throws IOException, SQLException, ClassNotFoundException {
        String req = "UPDATE author SET deleted=1 WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, author.getId());

        int exe = pstm.executeUpdate();

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return exe > 0;
    }

}
