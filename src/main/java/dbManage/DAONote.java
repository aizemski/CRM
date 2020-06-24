package dbManage;

import Utili.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAONote {
    public static ResultSet getNote() throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String select = "SELECT id,date,note WHERE employee_id =" + DAOEmployee.getId();
        return DbUtil.executeQuery(select);
    }

    public static void addNote(String note, int projectId) throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String insert = " INSERT INTO note (id,date,note,project_id,employee_id) VALUES( " +
                "NULL,NOW(),'" + note + "'," + DAOEmployee.getId() + "," + projectId + ")";
        DbUtil.executeCRUD(insert);
    }
}
