package lk.ijse.jdbc.controller;

/*
    @author DanujaV
    @created 3/8/23 - 4:47 PM   
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TouchEvent;

import java.sql.*;
import java.util.Properties;

public class CustomerMangeFormController {
    private final static String URL = "jdbc:mysql://localhost:3306/thogakade";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtId1;
    @FXML
    private TextField txtName1;

    @FXML
    private TextField txtAddress1;

    @FXML
    private TextField txtSalary1;
    @FXML
    private TextField txtId11;

    @FXML
    private TextField txtName11;

    @FXML
    private TextField txtAddress11;

    @FXML
    private TextField txtSalary11;



    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        boolean isValid = searchId(txtId.getText());
        if(!isValid){
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());


            try(Connection con = DriverManager.getConnection(URL, props)) {
            PreparedStatement pstm = con.prepareStatement("INSERT INTO Customer(id, name, address, salary) " +
                    "VALUES(?, ?, ?, ?)");
            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, address);
            pstm.setDouble(4, salary);

            int affectedRows = pstm.executeUpdate();

            if (affectedRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").showAndWait();

                txtId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtSalary.setText("");
            }
        }
        }else{
            new Alert(Alert.AlertType.ERROR, "Duplicate ID!").show();
            txtId.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtSalary.setText("");
        }
    }

    private boolean searchId(String text) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Customer WHERE id = ?");
            pstm.setString(1, text);
            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }
        return false;
    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String id = txtId1.getText();
        String name = txtName1.getText();
        String address = txtAddress1.getText();
        String salary = txtSalary1.getText();


        if(id.equals("")||name.equals("")||address.equals("")||salary.equals("")){
            new Alert(Alert.AlertType.ERROR,"Field Can't Empty").show();
            System.out.println(10);
        }else {
            try (Connection con = DriverManager.getConnection(URL, props)) {
                PreparedStatement pstm = con.prepareStatement("UPDATE Customer SET name=?, address=?, salary=? WHERE id=?");
                double salary1 = Double.parseDouble(txtSalary1.getText());
                pstm.setString(1, name);
                pstm.setString(2, address);
                pstm.setDouble(3, salary1);
                pstm.setString(4, id);

                int affectedRow = pstm.executeUpdate();
                if (affectedRow!=0) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Customer Updated!").show();
                    txtId1.setText("");
                    txtName1.setText("");
                    txtAddress1.setText("");
                    txtSalary1.setText("");
                }else{
                    new Alert(Alert.AlertType.ERROR,"Customer not updated!").show();
                    txtId1.setText("");
                    txtName1.setText("");
                    txtAddress1.setText("");
                    txtSalary1.setText("");
                }
            }

        }
    }

    @FXML
    void txtIdOnKeyPressed(ActionEvent event) throws SQLException {
        String text = txtId1.getText();
        if((searchId(text))) {
            try (Connection con = DriverManager.getConnection(URL, props)) {
                PreparedStatement pstm = con.prepareStatement("SELECT * FROM Customer WHERE id = ?");
                pstm.setString(1, text);
                ResultSet resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                      txtName1.setText(resultSet.getString(2));
                      txtAddress1.setText(resultSet.getString(3));
                      txtSalary1.setText(resultSet.getString(4));

                }

            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
        }
    }
    @FXML
    void txt1KeyOnType(KeyEvent event) {
        if(txtId1.getText().equals("")){
            txtName1.setText("");
            txtAddress1.setText("");
            txtSalary1.setText("");
        }

    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String id = txtId11.getText();
        try (Connection con = DriverManager.getConnection(URL, props)) {
            PreparedStatement pstm = con.prepareStatement("DELETE FROM Customer WHERE id=?");
            pstm.setString(1,id);
            int affectedRow = pstm.executeUpdate();
            if (affectedRow!=0) {
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted!").show();
                txtId11.setText("");
                txtName11.setText("");
                txtAddress11.setText("");
                txtSalary11.setText("");
            }else{
                new Alert(Alert.AlertType.ERROR,"Customer not Deleted!").show();
                txtId11.setText("");
                txtName11.setText("");
                txtAddress11.setText("");
                txtSalary11.setText("");
            }
        }


    }
    @FXML
    void txt11KeyOnType(KeyEvent event) {

        if(txtId11.getText().equals("")){
            txtName11.setText("");
            txtAddress11.setText("");
            txtSalary11.setText("");
        }
    }
    @FXML
    void txtId11OnKeyPressed(ActionEvent event) throws SQLException {
        String text = txtId11.getText();
        if((searchId(text))) {
            try (Connection con = DriverManager.getConnection(URL, props)) {
                PreparedStatement pstm = con.prepareStatement("SELECT * FROM Customer WHERE id = ?");
                pstm.setString(1, text);
                ResultSet resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    txtName11.setText(resultSet.getString(2));
                    txtAddress11.setText(resultSet.getString(3));
                    txtSalary11.setText(resultSet.getString(4));

                }

            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
        }
    }
}
