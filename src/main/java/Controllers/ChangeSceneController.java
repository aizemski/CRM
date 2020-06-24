package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class ChangeSceneController {

    public static void changeScene(ActionEvent e, String fxml, int width, int height) throws IOException {
        Parent root = FXMLLoader.load(ChangeSceneController.class.getResource("/FXML/" + fxml + ".fxml"));
        Scene new_scene = new Scene(root, width, height);

        Stage st = (Stage) ((Node) e.getSource()).getScene().getWindow();
        st.setScene(new_scene);
        st.show();
    }
}