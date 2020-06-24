package Controllers;

import Utili.RegexUtil;
import dbManage.DAOEmployee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    Button signIn;
    @FXML
    Button signUp;
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    Label error;
    final String wrong = "Wrong login or password!";


    public void signInAction(ActionEvent e) throws IOException {
        String l = login.getText();
        String p = password.getText();

        if (RegexUtil.passwordRegex(p) && DAOEmployee.loginCheck(l, p)) {
            ListController.login = l;
            ChangeSceneController.changeScene(e, "mainWindow", 700, 600);

        } else {
            login.setText("");
            password.setText("");
            error.setText(wrong);
        }
    }

    public void signUpAction(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "signUp", 300, 410);

    }

}
