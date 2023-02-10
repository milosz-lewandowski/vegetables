package com.miloszlewandowski.micronaut;

import io.micronaut.core.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VegetableContainer {

    @NonNull
    private final Map<String, Vegetable> vegetables = new ConcurrentHashMap<>();

    @NonNull
    public Map<String, Vegetable> getVegetables() {
        return vegetables;
    }
}