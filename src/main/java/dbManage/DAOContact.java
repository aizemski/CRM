package dbManage;


import Utili.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOContact {
    public static void addContact(String note, int personId) {
        //System.out.println(note);
        Connection connection = DbUtil.getConn();
        final String insert = "INSERT INTO contact(id,date,note,person_id) VALUES(NULL,NOW(),'" + note + "'," + personId + ")";
        try {
            DbUtil.executeCRUD(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static ResultSet getContact(int personId) throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String select = " SELECT date,note from contact WHERE person_id =" + personId;
        return DbUtil.executeQuery(select);
    }

}
