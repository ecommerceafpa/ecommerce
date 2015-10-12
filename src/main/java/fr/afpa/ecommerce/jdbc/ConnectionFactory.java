package fr.afpa.ecommerce.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private final String driver;
    private final String url;
    private final String user;
    private final String password;

    public ConnectionFactory() throws IOException {

        Properties properties = new Properties();
        properties.load(ConnectionFactory.class.getResourceAsStream("/database/config.properties"));
        driver = properties.getProperty("mysql.driver");
        url = properties.getProperty("mysql.url");
        user = properties.getProperty("mysql.user");
        password = properties.getProperty("mysql.password");

    }

    private Connection createConnection() throws SQLException, ClassNotFoundException {
        
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        
        ConnectionFactory connectionFactory = new ConnectionFactory();
        return connectionFactory.createConnection();
    }
}
