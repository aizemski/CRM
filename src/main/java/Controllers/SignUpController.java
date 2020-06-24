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


public class SignUpController {
    @FXML
    Button signIn;
    @FXML
    Button cancle;
    @FXML
    TextField name;
    @FXML
    TextField lastName;
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    PasswordField repeatPassowrd;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    Label nameLabel;
    @FXML
    Label lastNameLabel;
    @FXML
    Label loginLabel;
    @FXML
    Label passwordLabel;
    @FXML
    Label repeatPasswordLabel;
    @FXML
    Label emailLabel;
    @FXML
    Label phoneLabel;
    @FXML
    Label exist;

    @FXML
    private void initialize() {

        name.setText("Name");
    }

    public void signInAction(ActionEvent action) throws IOException {

        boolean accept = true;
        String n = name.getText();
        String lN = lastName.getText();
        String l = login.getText();
        String p = password.getText();
        String rP = repeatPassowrd.getText();
        String e = email.getText();
        String ph = phone.getText();
        nameLabel.setText("Name:");
        lastNameLabel.setText("Last Name:");
        passwordLabel.setText("Password:");
        repeatPasswordLabel.setText("Repeat Password:");
        emailLabel.setText("Email:");
        phoneLabel.setText("Phone:");
        loginLabel.setText("Login:");
        // Checking if fields are corrected filled

        if (RegexUtil.nameRegex(n) == false) {
            nameLabel.setText("Name: wrong name");
            accept = false;
        }
        if (RegexUtil.lastnameRegex(lN) == false) {
            lastNameLabel.setText("Last Name: wrong last name");
            accept = false;
        }
        if (RegexUtil.passwordRegex(p) == false) {
            passwordLabel.setText("Password: wrong password");
            accept = false;
        }
        if (rP.equals(p) == false) {
            repeatPasswordLabel.setText("Repeat Password: passowrds are diffrent");
            accept = false;
        }
        if (RegexUtil.emailRegex(e) == false) {
            emailLabel.setText("Email: wrong email");
            accept = false;
        }
        if (RegexUtil.phoneRegex(ph) == false) {
            phoneLabel.setText("Phone: wrong phone");
            accept = false;
        }
        if (RegexUtil.loginRegex(l) == false) {
            loginLabel.setText("Login: wrong login");
            accept = false;
        }

        if (accept) {

            if (DAOEmployee.isExisiting(l, p, e)) {
                DAOEmployee.addEmployee(l, n, lN, p, e, ph);
                ChangeSceneController.changeScene(action, "mainWindow", 700, 600);
                ListController.login = login.getText();
            } else {
                exist.setText("Login or email\n\texist");
            }
        }


    }

    public void cancleAction(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "login", 480, 360);
    }

}
