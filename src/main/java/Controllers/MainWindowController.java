package Controllers;

import Utili.RegexUtil;
import dbManage.DAOEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.sql.SQLException;

public class MainWindowController {
    @FXML
    PasswordField oldPassword;
    @FXML
    PasswordField newPassword;
    @FXML
    PasswordField repeatNewPassword;

    public void logOut(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "login", 480, 360);
    }

    public void manageConcactPerson(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "list", 700, 600);
    }

    public void manageProject(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "listProject", 700, 600);
    }

    public void makeContact(ActionEvent e) throws IOException {

    }

    public void showAll(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "listAllProjectForComapny", 700, 600);
    }

    public void changePassword() throws SQLException, ClassNotFoundException {
        if (DAOEmployee.loginCheck(ListController.login, oldPassword.getText())) ;
        if (RegexUtil.passwordRegex(newPassword.getText()) && newPassword.getText().equals(repeatNewPassword.getText())) {
            DAOEmployee.updatePass(newPassword.getText());
        }
    }

    public void manageCompany(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "listCompany", 700, 600);
    }

    public void makeNote(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "makeNote", 480, 360);
    }
}
