/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author jialu_lin
 */
public class DatabaseException extends Exception {
    /**
     * Constructor with a custom message and the cause of the error 
     * @param message
     * @param cause 
     */
    public DatabaseException(String message, Throwable cause){
        super(message,cause);
    }
    
    //Constructor with custom message only
    public DatabaseException(String message){
        super(message);
    }    
}
