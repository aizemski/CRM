package Utili;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class DbUtil {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "password";
    private static Connection conn = null;
    private static final String connStr = "jdbc:mysql://localhost:3306/crm";

    public static Connection getConn() {
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void connect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found!");
            e.printStackTrace();
            throw e;
        }


        try {
            conn = DriverManager.getConnection(connStr, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Connection failed");
            e.printStackTrace();

        }
    }

    public static void disconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static ResultSet executeQuery(PreparedStatement preparedStatement) throws SQLException, ClassNotFoundException {
        //Statement stmnt = null;
        ResultSet resSet = null;
        CachedRowSet crs = null;

        try {


            conn.setAutoCommit(false);
            //stmnt = conn.createStatement();
            resSet = preparedStatement.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(resSet);
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Problem occured at: " + e);
            try {
                conn.rollback();
            } catch (SQLException excp) {
                System.err.println("Rollback doesne work" + excp.getMessage());
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            disconnect();
        }
        return crs;
    }

    public static void executeCRUD(PreparedStatement preparedStatement) throws SQLException, ClassNotFoundException {
        //Uzywaj do operacji CRUD (create, update, delete)

        try {

            conn.setAutoCommit(false);
            //Create Statement
            //stmt = conn.createStatement();
            //Run executeUpdate operation with given sql statement
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.err.println("Problem occurred at executeUpdate operation : " + e);
            try {
                conn.rollback();
            } catch (SQLException excp) {
                System.err.println("Rollback doesnt work" + excp.getMessage());
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            disconnect();
        }
    }
}
