/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstzad2;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author A20111
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private AnchorPane ap;
    @FXML private Button bt;
    
    private String nopeSignal() {
        Random r = new Random();
        int n = r.nextInt(5);
        switch(n) {
            case 0: return "haha!";
            case 1: return "lolz";
            case 2: return "you mad?";
            case 3: return "nice try";
            default: return "nope";
        }
    }
    
    @FXML
    private void handleButtonAction() {
        Label label = new Label();
        label.setText(nopeSignal());
        label.setLayoutX(bt.getLayoutX());
        label.setLayoutY(bt.getLayoutY());
        ap.getChildren().add(label);
        Random random = new Random();
        Integer newX = random.nextInt(700);
        Integer newY = random.nextInt(570);
        bt.setLayoutX(newX);
        bt.setLayoutY(newY);
    }
    
    @FXML
    private void youDidIt() {
        Alert alert = new Alert(AlertType.INFORMATION, "YOU MADE IT, BRO!");
        alert.showAndWait();
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
