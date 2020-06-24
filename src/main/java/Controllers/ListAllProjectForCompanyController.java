package Controllers;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ListAllProjectForCompanyController {
    public void mainWindow(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "mainWindow", 700, 600);
    }

    public void updateTable(ActionEvent actionEvent) {
    }

    public void load(ActionEvent actionEvent) {
    }
}
