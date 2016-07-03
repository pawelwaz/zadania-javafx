/*
 * Przedstawiona wersja aplikacji jest niekompletna, gdyż nie zawiera możliwości otwierania i zapisywania pliku. Ponadto kolorowanie
 * składni przestaje działać poprawnie po przejściu do kolejnej linii. Jednakże tyle byłem w stanie zdziałać przed upływem terminu
 */
package dstzad5;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 *
 * @author A20111
 */
public class FXMLDocumentController implements Initializable, KeyListener {
    
    private String path;
    private JTextPane text;
    private Keyword[] keywords;
    
    @FXML AnchorPane ap;
    
    @FXML
    private void newDocument() {
        path = "";
        StyledDocument doc = text.getStyledDocument();
        Style style = text.addStyle("style", null);
        StyleConstants.setForeground(style, Color.BLACK);
        try {
            doc.insertString(0, "Ook? Ook! Ook! Ook? Ook. Ook! Ook! Ook." ,style); 
        }
        catch(BadLocationException ex) {}
        checkSyntax();
    }
    
    @FXML
    private void openDocument() {
        
    }
    
    @FXML
    private void saveDocument() {
        
    }
    
    @FXML
    private void saveDocumentAs() {
        
    }
    
    @FXML
    private void closeProgram() {
        System.exit(0);
    }
    
    @FXML
    private void showAbout() {
        Alert info = new Alert(AlertType.INFORMATION);
        info.setTitle("About");
        info.setHeaderText("Edytor języka Ook! Ook!");
        info.setContentText("Autor: Paweł Wąż\nInformatyka Inżynierska");
        info.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        keywords = new Keyword[4];
        keywords[0] = new Keyword("Ook? Ook!", Color.RED);
        keywords[1] = new Keyword("Ook! Ook?", Color.PINK);
        keywords[2] = new Keyword("Ook. Ook!", Color.GREEN);
        keywords[3] = new Keyword("Ook! Ook.", Color.BLUE);
        
        SwingNode swing = new SwingNode();
        swing.setLayoutY(25);
        text = new JTextPane();
        text.addKeyListener(this);
        text.setMinimumSize(new Dimension(500, 500));
        createSwingContent(swing);
        ap.getChildren().add(swing);
        newDocument();
        checkSyntax();
    }   
    
    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingNode.setContent(text);
            }
        });
    }
    
    private void colorText(JTextPane pane, Color col, String content, int pos) {
        StyledDocument doc = text.getStyledDocument();
        Style style = text.addStyle("style", null);
        StyleConstants.setForeground(style, col);
        try {
            doc.remove(pos, content.length());
            doc.insertString(pos, content ,style); 
        }
        catch(BadLocationException ex) {}
        StyleConstants.setForeground(style, Color.BLACK);
    }
    
    private void checkSyntax() {
        String code = text.getText();
        StringBuilder tmp = new StringBuilder();
        int lines = 0;
        int n = 0;
        for(int i = 0; i < code.length(); i++) {
            tmp.append(code.charAt(i));
            Character ch = code.charAt(i);
            if(ch.equals('\r')) lines++;
            String tmpStr = tmp.toString();
            for(int j = 0; j < keywords.length; j++) {
                if(tmpStr.contains(keywords[j].getContent())) {
                    if(tmpStr.indexOf(keywords[j].getContent()) != 0) {
                        String pre = tmpStr.substring(0, tmpStr.indexOf(keywords[j].getContent()));
                        colorText(text, Color.BLACK, pre, n + lines);
                        n += pre.length();
                    }
                    colorText(text, keywords[j].getColor(), keywords[j].getContent(), n + lines);
                    n += keywords[j].getContent().length();
                    colorText(text, Color.BLACK, "", n);
                    tmp = new StringBuilder();
                    break;
                }
            }
        }
        colorText(text, Color.BLACK, tmp.toString(), n - lines);
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
    
    public void keyPressed(KeyEvent e) {
        
    }
    
    public void keyReleased(KeyEvent e) {
        checkSyntax();
    }
     
}
