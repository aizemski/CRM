package Controllers;

import dbManage.DAOContact;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MakeContactController {
    public static int id;
    @FXML
    public TextField text;



    public void cancle(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e,"list",700,600);
    }




    public void make(ActionEvent e) throws IOException {
        String t = text.getText();
            DAOContact.addContact(t,this.id);
        ChangeSceneController.changeScene(e,"list",700,600);
    }

    }


