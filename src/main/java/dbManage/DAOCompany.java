package dbManage;

import Utili.DbUtil;
import Utili.RegexUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOCompany {
    public static void addCompany(String tin, String name, String address) {
        if (RegexUtil.tinRegex(tin) && RegexUtil.nameRegex(name)) {
            Connection connection = DbUtil.getConn();
            final String insert = "INSERT INTO company (TIN,NAME,ADDRESS) VALUES('" + tin + "','" + name + "','" + address + "')";
            try {
                DbUtil.executeCRUD(insert);
            } catch (SQLException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getCompany() throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String select = " SELECT tin,name,address FROM company ";
        return DbUtil.executeQuery(select);
    }

    public static ResultSet getCompanyBy(String tin, String name, String address) throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String select = " SELECT tin,name,address FROM company WHERE " +
                "tin like '%" + tin + "%' AND name like '" + name + "%' " +
                "AND address like '" + address + "%'";
        return DbUtil.executeQuery(select);
    }

    public static void delCompany(String tin) throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String del = "DELETE FROM company WHERE tin = " + tin;
        DbUtil.executeCRUD(del);
    }
}
