package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Author;
import fr.afpa.ecommerce.bean.Editor;
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

public class EditorModel implements Crud<Editor> {
    
    @Override
    public Editor save(Editor editor) throws IOException, SQLException, ClassNotFoundException {

        String req = "INSERT INTO editor (name) VALUES (?);";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, editor.getName());
       

        int rows = pstm.executeUpdate();
        if (rows == 0) {
            throw new SQLException();
        }

        try (ResultSet rs = pstm.getGeneratedKeys()) {
            if (rs.next()) {
                editor.setId(rs.getInt("id"));
            }
            ConnectionUtil.close(rs);
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return editor;
    }

    @Override
    public Editor find(int id) throws IOException, SQLException, ClassNotFoundException {
        Editor editor = null;

        String req = "SELECT id, name FROM editor WHERE deleted = false AND id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            editor = new Editor();
            editor.setId(rs.getInt("id"));
            editor.setName(rs.getString("name"));
           
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return editor;
    }

    @Override
    public List<Editor> findAll() throws IOException, SQLException, ClassNotFoundException {
        List<Editor> editors = new ArrayList<>();
        String req = "SELECT id,name FROM editor WHERE deleted = false;";
        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Editor editor = new Editor();
            editor.setId(rs.getInt("id"));
            editor.setName(rs.getString("name"));            
            editors.add(editor);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return editors;
    }

    @Override
    public boolean update(Editor editor) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE editor SET name=? WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, editor.getName());
        pstm.setInt(2, editor.getId());

        int exe = pstm.executeUpdate();

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return exe > 0;
    }

    @Override
    public boolean delete(Editor editor) throws IOException, SQLException, ClassNotFoundException {
        String req = "UPDATE editor SET deleted=1 WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, editor.getId());

        int exe = pstm.executeUpdate();

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return exe > 0;
    }
    
}
