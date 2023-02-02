package com.example.BIT_EXAM.utils;

import java.util.*;

public class FruitMachine {

    private static Map<String, Player> players = new HashMap<>();
    private static Random random = new Random();

    public static Map<String, Object> play(String playerName, int bet) {
        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException("utils.Player name must not be empty or null");
        }
        if (bet <= 0) {
            throw new IllegalArgumentException("Bet amount must be a positive integer");
        }

        Player player = players.getOrDefault(playerName, new Player(playerName, 1000));
        if (bet > player.getBalance()) {
            throw new IllegalArgumentException("Bet amount cannot be greater than player's balance");
        }
        player.setBalance(player.getBalance() - bet);
        players.put(playerName, player);

        List<Color> selectedColors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(Color.values().length);
            selectedColors.add(Color.values()[index]);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("colors", selectedColors);
        response.put("balance", player.getBalance());
        return response;
    }
}

