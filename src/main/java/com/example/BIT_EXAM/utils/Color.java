package com.example.BIT_EXAM.utils;

public enum Color {
    BLACK("black"),
    WHITE("white"),
    GREEN("green"),
    YELLOW("yellow");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
