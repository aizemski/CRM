package dbManage;


import Utili.DbUtil;
import Utili.RegexUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOProject {
    public static ResultSet getProject() throws SQLException, ClassNotFoundException {
        Connection connection = DbUtil.getConn();
        final String select = "SELECT p.id, p.name,p.budget FROM project p, personprojects pp ,person pe " +
                "WHERE pe.employee_id = " + DAOEmployee.getId() + " AND pe.id = pp.person_id AND  pp.project_id=p.id";
        return DbUtil.executeQuery(select);


    }

    public static void addProject(String name, double budget, int personID) throws SQLException {
        Connection connection = DbUtil.getConn();
        if (RegexUtil.nameRegex(name) && RegexUtil.priceRegex(Double.toString(budget))) {
            final String insert = "INSERT INTO project (ID,NAME,BUDGET) VALUES(" +
                    "NULL , '" + name + "' ," + budget + ")";
            final String insert2 = " INSERT INTO personprojects(person_id,project_id) VALUES( " + personID + ", LAST_INSERT_ID())";
            Connection conn = null;
            Statement stmt = null;
            try {
                DbUtil.connect();
                conn = DbUtil.getConn();
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
                stmt.executeUpdate(insert);
                stmt.executeUpdate(insert2);
                conn.commit();
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println("Problem occurred at executeUpdate operation : " + e);
                try {
                    conn.rollback();
                } catch (SQLException excp) {
                    System.err.println("Rollback doesnt work" + excp.getMessage());
                }
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
                DbUtil.disconnect();


            }
        }
    }

    public static void addPersonToProject(int projectID, int personID) {
        Connection connection = DbUtil.getConn();
        final String insert = " INSERT INTO personprojects(person_id,project_id) VALUES( " + personID + "," + projectID + ")";
        try {
            DbUtil.executeCRUD(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addPersonToProject(int personID) {
        Connection connection = DbUtil.getConn();
        final String insert = " INSERT INTO personprojects(person_id,project_id) VALUES( " + personID + ", LAST_INSERT_ID())";
        try {
            DbUtil.executeCRUD(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
