package Controllers;

import Utili.RegexUtil;
import dbManage.DAOCompany;
import dbManage.DAOPerson;
import dbMaps.Company;
import dbMaps.Person;
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

public class ListController {
    public static String login;

    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> last;
    @FXML
    private TableColumn<Person, String> email;
    @FXML
    private TableColumn<Person, String> phone;
    @FXML
    private TableColumn<Person, Integer> id;
    @FXML
    TextField nameLabel;
    @FXML
    TextField lastLabel;
    @FXML
    TextField emailLabel;
    @FXML
    TextField phoneLabel;
    @FXML
    TextField nameLabel1;
    @FXML
    TextField lastLabel1;
    @FXML
    TextField emailLabel1;
    @FXML
    TextField phoneLabel1;
    @FXML
    ComboBox companyList;

    ObservableList<Person> obl = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        showCompany();
    }

    public void load() throws SQLException, ClassNotFoundException {
        table.getItems().clear();
        ResultSet rs = null;
        rs = DAOPerson.getPersonByNamePhoneLastEmail("", "", "", "");
        fill(rs);
    }

    public void updateTable() throws SQLException, ClassNotFoundException {

        table.getItems().clear();
        ResultSet rs = null;

        rs = DAOPerson.getPersonByNamePhoneLastEmail(nameLabel.getText(), lastLabel.getText(), emailLabel.getText(), phoneLabel.getText());
        fill(rs);
    }

    private void fill(ResultSet rs) throws SQLException {
        while (rs.next()) {
            obl.add(new Person(rs.getInt("id"), "", rs.getString("name"), rs.getString("last"),
                    rs.getString("email"), rs.getString("phone")));

        }


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        last.setCellValueFactory(new PropertyValueFactory<>("last"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.setItems(obl);
    }

    public void showCompany() throws SQLException, ClassNotFoundException {
        companyList.getItems().clear();
        ResultSet rs = DAOCompany.getCompany();
        if (rs != null)
            while (rs.next()) {
                companyList.getItems().add(new Company(rs.getString("tin"), rs.getString("name"), rs.getString("address")));
            }
    }

    public void mainWindow(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "mainWindow", 700, 600);
    }

    public void add() throws SQLException, ClassNotFoundException {
        final String name1 = nameLabel1.getText();
        final String last1 = lastLabel1.getText();
        final String phone1 = phoneLabel1.getText();
        final String email1 = emailLabel1.getText();
        Company com = (Company) companyList.getSelectionModel().getSelectedItem();


        if (RegexUtil.nameRegex(name1) && RegexUtil.lastnameRegex(last1) &&
                RegexUtil.phoneRegex(phone1) && RegexUtil.emailRegex(email1) &&com.getTin()!=null&&RegexUtil.tinRegex(com.getTin())) {

            DAOPerson.addPerson(name1, last1, email1, phone1, com.getTin());

        }

    }

    public int getSelected() {
        Person p = table.getSelectionModel().getSelectedItem();
        if (p != null)
            return p.getId();
        else
            return -1;
    }

    public void makeContact(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {

        if ((MakeContactController.id = getSelected()) > 0) {
            ChangeSceneController.changeScene(e, "makeContact", 480, 360);
        }

    }

    public void checkContact(ActionEvent e) throws IOException {
        if ((ListContactController.id = getSelected()) > 0)
            ChangeSceneController.changeScene(e, "listContact", 700, 600);
    }
}
