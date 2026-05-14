/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javalab1;

/**
 *
 * @author alex
 */
public class InvalidException extends Exception {
    
    double errVal;
    
    public double getErrVal() {
        return errVal;
    }
    
    public InvalidException(String message, double num) {
        super(message);
        errVal = num;
    }

    public InvalidException(String message) {
        super(message);
    }
}

