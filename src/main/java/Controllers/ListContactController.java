package Controllers;

import dbManage.DAOContact;
import dbMaps.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListContactController {
    @FXML
    TableView<Contact> table;
    @FXML
    TableColumn<Contact, Date> date;
    @FXML
    TableColumn<Contact, String> note;
    public static int id;
    ObservableList<Contact> obl = FXCollections.observableArrayList();

    public void list(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "list", 700, 600);
    }

    public void load(ActionEvent e) throws SQLException, ClassNotFoundException {

        table.getItems().clear();
        ResultSet rs = null;

        rs = DAOContact.getContact(id);
        fill(rs);
    }

    private void fill(ResultSet rs) throws SQLException {
        while (rs.next()) {
            obl.add(new Contact(0, rs.getDate("date"), 0, rs.getString("note")));

        }


        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));

        table.setItems(obl);
    }
}
