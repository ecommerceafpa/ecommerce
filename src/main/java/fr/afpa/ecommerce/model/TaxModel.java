package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Tax;
import fr.afpa.ecommerce.bean.Tax;
import fr.afpa.ecommerce.jdbc.ConnectionFactory;
import fr.afpa.ecommerce.jdbc.ConnectionUtil;
import fr.afpa.ecommerce.model.Crud;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaxModel implements Crud<Tax> {
    
    @Override
    public Tax save(Tax tax) throws IOException, SQLException, ClassNotFoundException {

        String req = "INSERT INTO tax (name, value) VALUES (?, ?);";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, tax.getName());
        pstm.setFloat(2, tax.getValue());

        int rows = pstm.executeUpdate();
        if (rows == 0) {
            throw new SQLException();
        }

        try (ResultSet rs = pstm.getGeneratedKeys()) {
            if (rs.next()) {
                tax.setId(rs.getInt("id"));
            }
            ConnectionUtil.close(rs);
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return tax;
    }

    @Override
    public Tax find(int id) throws IOException, SQLException, ClassNotFoundException {
        Tax tax = null;

        String req = "SELECT id, name, value FROM tax WHERE deleted = false AND id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            tax = new Tax();
            tax.setId(rs.getInt("id"));
            tax.setName(rs.getString("name"));
            tax.setValue(rs.getFloat("value"));            
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return tax;
    }

    @Override
    public List<Tax> findAll() throws IOException, SQLException, ClassNotFoundException {
        List<Tax> taxs = new ArrayList<>();
        String req = "SELECT id,name,value FROM tax WHERE deleted = false;";
        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Tax tax = new Tax();
            tax.setId(rs.getInt("id"));
            tax.setName(rs.getString("name"));
            tax.setValue(rs.getFloat("value"));            
            taxs.add(tax);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return taxs;
    }

    @Override
    public boolean update(Tax tax) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE tax SET name=?, value=? WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, tax.getName());
        pstm.setFloat(2, tax.getValue());        
        pstm.setInt(3, tax.getId());

        int exe = pstm.executeUpdate();

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return exe > 0;
    }

    @Override
    public boolean delete(Tax tax) throws IOException, SQLException, ClassNotFoundException {
        String req = "UPDATE tax SET deleted=1 WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, tax.getId());

        int exe = pstm.executeUpdate();

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return exe > 0;
    }

    
}
