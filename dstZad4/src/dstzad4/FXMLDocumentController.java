/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstzad4;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author pawel
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private AnchorPane ap;
    private Label pointsPlayer, pointsCasino, dummy;
    private boolean gameOn;
    private Player player, casino;
    private String[] cardNames = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private int[] cardValues = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    
    private void startGame() {
        this.clearScreen();
        this.showPointLabels();
        this.player = new Player(true);
        this.casino = new Player(false);
        this.createCard(player);
        this.createCard(player);
        this.createDummy();
        this.casino.addCard();
        this.createCard(casino);
        this.showGameUI();
        this.gameOn = true;
    }
    
    private void createCard(Player p) {
        int y;
        if(p.isHuman()) y = 460;
        else y = 50;
        int x = 100 + 100 * p.countCards();
        Label card = new Label();
        card.setLayoutX(x);
        card.setLayoutY(y);
        card.setMinWidth(80);
        card.setMinHeight(100);
        Random random = new Random();
        Integer cardNum = random.nextInt(12);
        p.addPoints(this.cardValues[cardNum]);
        p.addCard();
        card.setText(this.cardNames[cardNum]);
        card.setStyle("-fx-background-color: #C6BBA9; -fx-border-width: 5; -fx-border-color: black;");
        card.setAlignment(Pos.CENTER);
        card.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        card.setVisible(true);
        ap.getChildren().add(card);
        this.showPoints(p);
        this.checkPoints(p);
    }
    
    private void createDummy() {
        this.dummy = new Label();
        this.dummy.setLayoutX(100);
        this.dummy.setLayoutY(50);
        this.dummy.setMinWidth(80);
        this.dummy.setMinHeight(100);
        this.dummy.setText("?");
        this.dummy.setStyle("-fx-background-color: #C6BBA9; -fx-border-width: 5; -fx-border-color: black;");
        this.dummy.setAlignment(Pos.CENTER);
        this.dummy.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        this.dummy.setVisible(true);
        ap.getChildren().add(this.dummy);
    }
    
    private void doHit(Player p) {
        this.createCard(p);
    }
    
    private void checkPoints(Player p) {
        if(p.countPoints() > 21) {
            if(p.isHuman()) this.endGame(-1);
            else endGame(1);
        }
        else if(p.countPoints() == 21) {
            if(p.isHuman()) this.endGame(1);
            else endGame(-1);
        }
    }
    
    private void finalCheckPoints() {
        if(this.player.countPoints() > this.casino.countPoints()) this.endGame(1);
        else if(this.player.countPoints() < this.casino.countPoints()) this.endGame(-1);
        else this.endGame(0);
    }
    
    private void doStand() {
        Random rand = new Random();
        int hidden = rand.nextInt(12);
        this.dummy.setText(this.cardNames[hidden]);
        this.casino.addPoints(this.cardValues[hidden]);
        this.showPoints(casino);
        this.checkPoints(casino);
        while(casino.countPoints() < 17) {
            this.createCard(casino);
        }
        if(this.gameOn) this.finalCheckPoints();
    }
    
    private void clearScreen() {
        this.ap.getChildren().clear();
    }
    
    private void endGame(int win) {
        Alert a = new Alert(AlertType.INFORMATION);
        a.setTitle("Game Over");
        switch (win) {
            case 1:
                a.setContentText("You win!");
                break;
            case -1:
                a.setContentText("You lose");
                break;
            default:
                a.setContentText("It's a draw");
                break;
        }
        a.showAndWait();
        this.clearScreen();
        this.showStartButton();
        this.gameOn = false;
    }
    
    private void showGameUI() {
        Button hitButton = new Button();
        hitButton.setText("HIT");
        hitButton.setLayoutX(300);
        hitButton.setLayoutY(400);
        hitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                doHit(player);
            }
        });
        ap.getChildren().add(hitButton);
        
        Button standButton = new Button();
        standButton.setText("STAND");
        standButton.setLayoutX(350);
        standButton.setLayoutY(400);
        standButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                doStand();
            }
        });
        ap.getChildren().add(standButton);
    }
    
    private void showPointLabels() {
        this.pointsPlayer = new Label();
        pointsPlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        pointsPlayer.setLayoutX(50);
        pointsPlayer.setLayoutY(570);
        ap.getChildren().add(pointsPlayer);
        
        this.pointsCasino = new Label();
        pointsCasino.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        pointsCasino.setLayoutX(50);
        pointsCasino.setLayoutY(10);
        ap.getChildren().add(pointsCasino);
    }
    
    private void showPoints(Player p) {
        if(p.isHuman()) {
            pointsPlayer.setText("Points: " + ((Integer) p.countPoints()).toString());
        }
        else {
            pointsCasino.setText("Points: " + ((Integer) p.countPoints()).toString());
        }
    }
    
    private void showStartButton() {
        Button startButton = new Button();
        startButton.setText("start game");
        startButton.setLayoutX(380);
        startButton.setLayoutY(290);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                startGame();
            }
        });
        ap.getChildren().add(startButton);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.showStartButton();
    }    
    
}
