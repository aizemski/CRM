package dbManage;


import Utili.DbUtil;
import Utili.RegexUtil;
import dbMaps.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPerson {
    public static void addPerson(String name, String last, String email, String phone, String tin) throws SQLException, ClassNotFoundException {
        if (RegexUtil.nameRegex(name) && RegexUtil.lastnameRegex(last) && RegexUtil.emailRegex(email) && RegexUtil.phoneRegex(phone)) {
            Connection connection = DbUtil.getConn();
            final String insert = "INSERT INTO person(ID,NAME,LAST,EMAIL,PHONE,EMPLOYEE_ID,COMPANY_TIN)"
                    + " VALUES(NULL, '" + name + "', '" + last + "', '" + email + "','" + phone + "','" + DAOEmployee.getId() + "', '" + tin + "' )";
            System.out.println(last);
            try {
                DbUtil.executeCRUD(insert);
            } catch (SQLException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static ResultSet getPersonByNamePhoneLastEmail(String name, String last, String email, String phone) throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String select = " SELECT name,  phone,  last,  email ,id FROM person   WHERE " +
                "employee_id = " + DAOEmployee.getId() + " AND name LIKE'%" + name + "%' AND " +
                "last LIKE '%" + last + "%' AND email LIKE '%" + email + "%' AND " +
                "phone LIKE '%" + phone + "%'";
        return DbUtil.executeQuery(select);
    }

    public static String getById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String select = "SELECT name,last FROM person WHERE id =" + id;
        ResultSet rs = DbUtil.executeQuery(select);
        rs.next();
        Person p = new Person(id, "", rs.getString("name"), rs.getString("last"), "", "");
        return p.toString();
    }

}
