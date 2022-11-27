package com.example.exp;

public class ItemNotfoundException extends RuntimeException{
    public ItemNotfoundException(String message) {
        super(message);
    }
}
