/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.response;

/**
 *
 * @author BEMULAP
 */
public class Respuesta<T> {
    
    private String message;
    private T data;

    public Respuesta(String message) {
        this.message = message;
        this.data = null;
    }
    
    public Respuesta(String message, Object o) {
        this.message = message;
        this.data = (T) o;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
    
    
    
}
