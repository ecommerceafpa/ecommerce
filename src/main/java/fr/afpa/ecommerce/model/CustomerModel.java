package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Customer;
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

public class CustomerModel implements Crud<Customer> {

    @Override
    public Integer save(Customer customer) throws IOException, SQLException, ClassNotFoundException {

        String req = "INSERT INTO customer (firstname, lastname, username, password) VALUES (?, ?, ?, ?)";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, customer.getFirstName());
        pstm.setString(2, customer.getLastName());
        pstm.setString(3, customer.getUserName());
        pstm.setString(4, customer.getPassword());

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
    public Customer find(Integer id) throws IOException, SQLException, ClassNotFoundException {

        Customer customer = null;

        String req = "SELECT id, firstname, lastname, username, created, disabled FROM customer WHERE deleted = false AND id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstname"));
            customer.setLastName(rs.getString("lastname"));
            customer.setUserName(rs.getString("username"));
            customer.setCreated(rs.getDate("created"));
            customer.setDisabled(rs.getBoolean("disabled"));
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return customer;
    }

    @Override
    public List<Customer> findAll() throws IOException, SQLException, ClassNotFoundException {

        List<Customer> customers = new ArrayList<>();
        String req = "SELECT id, firstname, lastname, username, created, disabled FROM customer WHERE deleted = false;";
        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstname"));
            customer.setLastName(rs.getString("lastname"));
            customer.setUserName(rs.getString("username"));
            customer.setCreated(rs.getDate("created"));
            customer.setDisabled(rs.getBoolean("disabled"));
            customers.add(customer);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return customers;
    }

    @Override
    public void update(Customer customer) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE customer SET firstname = ?, lastname = ?, username = ?, password = ? WHERE id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, customer.getFirstName());
        pstm.setString(2, customer.getLastName());
        pstm.setString(3, customer.getUserName());
        pstm.setString(4, customer.getPassword());
        pstm.setInt(5, customer.getId());

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

    @Override
    public void delete(Integer id) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE customer SET deleted=1 WHERE id=?;";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

    public void disable(int id, boolean enable) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE customer SET disabled = ? WHERE id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setBoolean(1, enable);
        pstm.setInt(2, id);

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

}
