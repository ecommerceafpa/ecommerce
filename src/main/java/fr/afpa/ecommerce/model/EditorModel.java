package fr.afpa.ecommerce.model;

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
    public Integer save(Editor editor) throws IOException, SQLException, ClassNotFoundException {

        String req = "INSERT INTO editor (name) VALUES (?)";

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, editor.getName());

        int rows = pstm.executeUpdate();
        if (rows == 0) {
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
    public Editor find(Integer id) throws IOException, SQLException, ClassNotFoundException {
        Editor editor = null;

        String req = "SELECT id, name FROM editor WHERE deleted = false AND id = ?";
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

        String req = "SELECT id, name, created, updated FROM editor WHERE deleted = false";

        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Editor editor = new Editor();
            editor.setId(rs.getInt("id"));
            editor.setName(rs.getString("name"));
            editor.setCreated(rs.getTimestamp("created"));
            editor.setUpdated(rs.getTimestamp("updated"));
            editors.add(editor);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return editors;
    }

    @Override
    public void update(Editor editor) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE editor SET name = ? WHERE id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, editor.getName());
        pstm.setInt(2, editor.getId());

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

    }

    @Override
    public void delete(Integer id) throws IOException, SQLException, ClassNotFoundException {
        String req = "UPDATE editor SET deleted = 1 WHERE id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

    public Editor findEditorByBook(Integer id) throws SQLException, IOException, ClassNotFoundException {

        Editor editor = null;
        String req = "select e.id, e.name "
                   + "from editor e "
                   + "join book b on (e.id=b.editor_id) "
                   + "where b.id=?";
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

}
