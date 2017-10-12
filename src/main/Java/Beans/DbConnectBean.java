package Beans;

import Interfaces.DbconnectInterface;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.sql.*;

@Stateless
@Local
public class DbConnectBean implements DbconnectInterface {
    Connection conn;
    Statement stmt;
    PreparedStatement pst;
    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost/banking";
    final String USER = "root";
    final String PASS = "";

    @PostConstruct

    public void connect() {

        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

        } catch (SQLException e) {
            System.err.println("could not connect to database");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public boolean dbWrite(String sql) {
        try {
             stmt.execute(sql);
             return  true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean dbWrite(PreparedStatement pst) {
        try {

            pst.execute();
            return  true;
        } catch (SQLException e) {
            return false;
        }
    }


    public PreparedStatement createprepareStatement(String sql) {
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    public ResultSet dbRead(String sql) {
        try {
            return stmt.executeQuery(sql);
        } catch (SQLException e) {

            return null;
        }

    }

    public void dbClose() {
        try {
            if (conn != null) {
                conn.close();

            }
            if (stmt != null) {
                stmt.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
