package Controllers;

import dbManage.DAOPerson;
import dbManage.DAOProject;
import dbMaps.Person;
import dbMaps.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListProjectController {
    @FXML
    private TableView<Project> table;
    @FXML
    private TableColumn<Project, String> name;
    @FXML
    private TableColumn<Project, Float> budget;
    @FXML
    TextField nameField;

    @FXML
    TextField budgetField;
    @FXML
    public ComboBox contactPerson;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        showPerson();
    }

    public void showPerson() throws SQLException, ClassNotFoundException {

        contactPerson.getItems().clear();
        ResultSet rs = DAOPerson.getPersonByNamePhoneLastEmail("", "", "", "");
        if (rs != null)
            while (rs.next()) {
                contactPerson.getItems().add(new Person(rs.getInt("id"), "", rs.getString("name"), rs.getString("last"),
                        rs.getString("email"), rs.getString("phone")));
            }
    }

    ObservableList<Project> obl = FXCollections.observableArrayList();

    public void mainWindow(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "mainWindow", 700, 600);
    }

    public void updateTable(ActionEvent actionEvent) throws SQLException {
        Person p = (Person) contactPerson.getSelectionModel().getSelectedItem();
        double f = Double.parseDouble(budgetField.getText());
        DAOProject.addProject(nameField.getText(), f, p.getId());
        //DAOProject.addPersonToProject(p.getId());

    }

    public void load(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        table.getItems().clear();
        ResultSet rs = null;

        rs = DAOProject.getProject();
        fill(rs);
    }

    private void fill(ResultSet rs) throws SQLException {
        if (rs != null)
            while (rs.next()) {
                obl.add(new Project(rs.getInt("id"), rs.getString("name"), rs.getFloat("budget")));

            }


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        budget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        table.setItems(obl);
    }


}
