package Controllers;

import dbManage.DAOCompany;
import dbMaps.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListCompanyController {
    @FXML
    TextField tinAdd;
    @FXML
    TextField nameAdd;
    @FXML
    TextField cityAdd;
    @FXML
    TextField zipAdd;
    @FXML
    TextField streetAdd;
    @FXML
    TextField tinSearch;
    @FXML
    TextField nameSearch;
    @FXML
    TextField addressSearch;
    @FXML
    TableView<Company> table;
    @FXML
    TableColumn<Company, String> name;
    @FXML
    TableColumn<Company, String> tin;
    @FXML
    TableColumn<Company, String> address;

    ObservableList<Company> obl = FXCollections.observableArrayList();

    public void mainWindow(ActionEvent e) throws IOException {
        ChangeSceneController.changeScene(e, "mainWindow", 700, 600);
    }

    public void updateTable(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        table.getItems().clear();
        ResultSet rs = null;

        rs = DAOCompany.getCompanyBy(tinSearch.getText(), nameSearch.getText(), addressSearch.getText());
        fill(rs);
    }

    private void fill(ResultSet rs) throws SQLException {
        while (rs.next()) {
            obl.add(new Company(rs.getString("tin"), rs.getString("name"), rs.getString("address")));

        }


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tin.setCellValueFactory(new PropertyValueFactory<>("tin"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        table.setItems(obl);
    }

    public void load(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        table.getItems().clear();
        ResultSet rs = null;

        rs = DAOCompany.getCompany();
        fill(rs);
    }

    public void add(ActionEvent actionEvent) {
        DAOCompany.addCompany(tinAdd.getText(), nameAdd.getText(), zipAdd.getText() + "," + cityAdd.getText() + "," + streetAdd.getText());
    }

    public void dropSelected(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Company c = table.getSelectionModel().getSelectedItem();
        DAOCompany.delCompany(c.getTin());
    }
}
