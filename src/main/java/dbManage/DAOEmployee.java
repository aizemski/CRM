package dbManage;

import Controllers.ListController;
import Utili.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOEmployee {
    private static final String salt = "qwe123###";

    public static void addEmployee(String login, String name, String last, String password, String email, String phone) throws SQLException {
        Connection connection = DbUtil.getConn();
        final String insert = "INSERT INTO employee(ID ,NAME,LAST ,LOGIN,PASSWORD,EMAIL,PHONE) VALUES(NULL, ?, ?, ?, ? , ?,? )";
       // final String insert = "INSERT INTO employee(ID,NAME,LAST,LOGIN,PASSWORD,EMAIL,PHONE)"
         //       + " VALUES(NULL, '" + name + "', '" + last + "', '" + login + "', '" + password + salt + "', '" + email + "','" + phone + "' )";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,last);
        preparedStatement.setString(4,login);
        preparedStatement.setString(5,password+salt);
        preparedStatement.setString(6,phone);
        try {
            DbUtil.executeCRUD(preparedStatement);
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean isExisiting(String login, String password, String email) throws SQLException {
        Connection connection = DbUtil.getConn();
        final String sql = "SELECT * FROM employee WHERE (login = ?  AND password = PASSWORD(? )) " +
                "OR email =  ENCRYPT( ?,'key')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password+salt);
        preparedStatement.setString(3,email);
        //final String select = "SELECT * FROM employee WHERE (login = '" + login + "'  AND password = PASSWORD('" + password + salt + "')) " +
          //      "OR email =  ENCRYPT('" + email + "','key')";
        try {
            System.out.println("xd");
            ResultSet rs = DbUtil.executeQuery(preparedStatement);
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean loginCheck(String login, String password) throws SQLException {
        Connection connection = DbUtil.getConn();
        final String select = "SELECT * FROM employee WHERE login = ?  AND password = PASSWORD(?) ";
        //final String select = "SELECT * FROM employee WHERE login = '" + login + "'  AND password = PASSWORD('" + password + salt + "') ";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password+salt);

        try {
            ResultSet rs = DbUtil.executeQuery(preparedStatement);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getId() throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        String select = "SELECT id FROM employee WHERE login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, ListController.login);
        ResultSet rs = DbUtil.executeQuery(preparedStatement);
        rs.next();
        return rs.getInt("id");

    }

    public static void updatePass(String pass) throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String update = "UPDATE employee " +
                "SET password= PASSWORD(?) " +
                "WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1,pass+salt);
        preparedStatement.setInt(2,getId());
        DbUtil.executeCRUD(preparedStatement);
    }
}


