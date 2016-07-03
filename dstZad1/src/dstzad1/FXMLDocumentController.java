/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstzad1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author A20111
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private ToggleGroup inputScale, outputScale;
    @FXML private TextField input, output;
    
    @FXML
    private void convert(ActionEvent event) {
        try {
            Double inputValue = Double.parseDouble(input.getText());
            RadioButton rbIn = (RadioButton) inputScale.getSelectedToggle();
            Double celsiusVal;
            switch(rbIn.getText()) {
                case "Fahrenheit":
                    celsiusVal = Converter.fahrenheitToCelsius(inputValue);
                    break;
                case "Kelvin":
                    celsiusVal = Converter.kelvinToCelsius(inputValue);
                    break;
                default:
                    celsiusVal = inputValue;
                    break;
            }
            Double outVal;
            RadioButton rbOut = (RadioButton) outputScale.getSelectedToggle();
            switch(rbOut.getText()) {
                case "Fahrenheit":
                    outVal = Converter.celsiusToFahrenheit(celsiusVal);
                    break;
                case "Kelvin":
                    outVal = Converter.celsiusToKelvin(celsiusVal);
                    break;
                default:
                    outVal = celsiusVal;
                    break;
            }
            output.setText(outVal.toString());
        }
        catch(NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR, "Incorrect input value!");
            alert.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
