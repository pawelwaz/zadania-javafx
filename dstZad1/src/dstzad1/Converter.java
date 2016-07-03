/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dstzad1;

/**
 *
 * @author A20111
 */
public class Converter {
    
    public static double celsiusToFahrenheit(double c) {
        return c * (9.0 / 5.0) + 32.0;
    }
    
    public static double celsiusToKelvin(double c) {
        return c - 273.15;
    }
    
    public static double fahrenheitToCelsius(double f) {
        return (f - 32.0) * 5.0 / 9.0;
    }
    
    public static double kelvinToCelsius(double k) {
        return k + 273.15;
    }
    
}
