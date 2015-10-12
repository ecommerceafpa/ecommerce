package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Customer;
import fr.afpa.ecommerce.jdbc.ConnectionFactory;
import fr.afpa.ecommerce.jdbc.ConnectionUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerModel {

    public void save(Customer customer) throws IOException, SQLException, ClassNotFoundException {

        String req = "INSERT INTO customer (firstname, lastname, username, password) VALUES (?, ?, ?, ?);";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setString(1, customer.getFirstName());
        pstm.setString(2, customer.getLastName());
        pstm.setString(3, customer.getUserName());
        pstm.setString(4, customer.getPassword());
        pstm.executeUpdate();
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

    public void delete() {

    }
}
