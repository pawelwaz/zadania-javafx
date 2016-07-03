/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstzad5;

import java.awt.Color;

/**
 *
 * @author A20111
 */
public class Keyword {
    private String content;
    private Color color;
    
    public Keyword(String newContent, Color newColor) {
        content = newContent;
        color = newColor;
    }
    
    public void setContent(String newContent) {
        content = newContent;
    }
    
    public void setColor(Color newColor) {
        color = newColor;
    }
    
    public String getContent() {
        return content;
    }
    
    public Color getColor() {
        return color;
    }
}
