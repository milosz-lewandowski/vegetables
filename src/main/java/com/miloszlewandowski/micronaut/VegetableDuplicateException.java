package com.miloszlewandowski.micronaut;

public class VegetableDuplicateException extends RuntimeException{

    public VegetableDuplicateException(String name) {
        super("Vegetable '" + name + "' already exists.");
    }
}