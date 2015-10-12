package fr.afpa.ecommerce.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtil {

    public static void close(Connection cnt) throws SQLException {
        cnt.close();
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(Statement stm) throws SQLException {
        stm.close();
    }

    public static void close(PreparedStatement pstm) throws SQLException {
        pstm.close();
    }
}
