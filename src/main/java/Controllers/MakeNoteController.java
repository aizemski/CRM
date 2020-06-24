package Controllers;

import dbManage.DAONote;
import dbManage.DAOProject;
import dbMaps.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MakeNoteController {
    @FXML
    ComboBox projectList;
    @FXML
    TextField text;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        showProject();
    }

    public void showProject() throws SQLException, ClassNotFoundException {
        projectList.getItems().clear();
        ResultSet rs = DAOProject.getProject();
        while (rs.next()) {
            projectList.getItems().add(new Project(rs.getInt("id"), rs.getString("name"), rs.getFloat("budget")));
        }
    }

    public void cancle(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "mainWindow", 700, 600);
    }


    public void make(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
        String t = text.getText();
        Project p = (Project) projectList.getSelectionModel().getSelectedItem();
        DAONote.addNote(t, p.getId());
        ChangeSceneController.changeScene(e, "mainWindow", 700, 600);
    }
    //TODO add combobox to choose a project
}
