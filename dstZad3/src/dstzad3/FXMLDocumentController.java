/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstzad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author A20111
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField textName, textNumber;
    @FXML private TableView tab;
    
    @FXML private void updateContact(ActionEvent event) {
        ObservableList items = tab.getItems();
        ((Contact) items.get(tab.getSelectionModel().getSelectedIndex())).setName(textName.getText());
        ((Contact) items.get(tab.getSelectionModel().getSelectedIndex())).setNumber(textNumber.getText());
        updateFile(items);
        fillTable();
        Alert alert = new Alert(AlertType.INFORMATION, "contact updated successfully");
        alert.show();
    }
    
    @FXML private void deleteContact(ActionEvent event) {
        if(!tab.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Delete selected contact?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                ObservableList items = tab.getItems();
                items.remove(tab.getSelectionModel().getSelectedIndex());
                updateFile(items);
                fillTable();
            }
        }
    }
    
    private void updateFile(ObservableList items) {
        try {
            PrintWriter writer = new PrintWriter("contacts.txt");
            int n = 0;
            while(n < items.size()) {
                writer.println(((Contact)items.get(n)).getName());
                writer.println(((Contact)items.get(n)).getNumber());
                n++;
            }
            writer.close();
        }
        catch(IOException e) {
            Alert a = new Alert(AlertType.INFORMATION, "file error");
            a.show();
        }
    }
    
    @FXML private void getSelectedContact() {
        Contact person = (Contact)tab.getSelectionModel().getSelectedItem();
        textName.setText(person.getName());
        textNumber.setText(person.getNumber());
    }
    
    @FXML private void addContact(ActionEvent event) {
        if(textName.getText().length() == 0 || textNumber.getText().length() == 0) {
            Alert alert = new Alert(AlertType.ERROR, "both values must be typed");
            alert.show();
        }
        else {
            ObservableList items = tab.getItems();
            items.add(new Contact(textName.getText(), textNumber.getText()));
            updateFile(items);
            fillTable();
            Alert alert = new Alert(AlertType.INFORMATION, "contact added successfully");
            alert.show();
        }
    }
    
    private void fillTable() {
        try {
            ObservableList<TableColumn> cols = FXCollections.observableArrayList(tab.getColumns());
            cols.get(0).setCellValueFactory(new PropertyValueFactory<Contact, String>("name"));
            cols.get(1).setCellValueFactory(new PropertyValueFactory<Contact, String>("number"));
            Scanner scan = new Scanner(new File("contacts.txt"));
            ObservableList<Contact> items = FXCollections.observableArrayList();
            while(scan.hasNextLine()) {
                String name = scan.nextLine();
                String number = scan.nextLine();
                items.add(new Contact(name, number));
            }
            tab.setItems(items);
            scan.close();
        }
        catch(Exception e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillTable();
    }    
    
}
