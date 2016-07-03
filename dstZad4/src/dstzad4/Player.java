/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstzad4;

/**
 *
 * @author pawel
 */
public class Player {
    private boolean human;
    private int cards, points;
    
    public Player() {
        this.cards = 0;
        this.points = 0;
        this.human = true;
    }
    
    public Player(boolean isHuman) {
        this.cards = 0;
        this.points = 0;
        this.human = isHuman;
    }
    
    public int countCards() {
        return this.cards;
    }
    
    public int countPoints() {
        return this.points;
    }
    
    public boolean isHuman() {
        return this.human;
    }
    
    public void addPoints(int plus) {
        this.points += plus;
    }
    
    public void addCard() {
        this.cards++;
    }
}
