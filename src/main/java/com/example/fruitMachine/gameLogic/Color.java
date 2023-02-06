package com.example.fruitMachine.gameLogic;

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
