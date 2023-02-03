package com.example.fruitMachine.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * FruitMachine class implements the logic for playing a fruit machine game.
 * This class stores the player's balance and performs the operations necessary to play the game.
 */
public class FruitMachine {

    private static final String NAME_REGEX = "^[a-zA-Z]+$";
    public static final String SELECTED_COLORS = "selectedColors";
    public static final String BALANCE = "balance";
    private static final int STARTING_BALANCE = 1000;
    private static final int NUM_COLORS = 4;
    private final Map<String, Player> players = new HashMap<>();  // map to store the players and their balances
    private final Random random = new Random();  // random number generator

    private int moneyBox;  // money box to store the money lost in each game

    public void setMoneyBox(int moneyBox) {
        this.moneyBox = moneyBox;
    }

    public int getMoneyBox() {
        return moneyBox;
    }

    /**
     * Constructor for FruitMachine class.
     * Initializes the moneyBox to 0.
     */
    public FruitMachine() {
        moneyBox = 0;
    }

    /**
     * play method is used to play the fruit machine game.
     *
     * @param playerName name of the player playing the game
     * @param bet        bet amount placed by the player
     * @return map containing the selected colors and the updated balance of the player
     */
    public Map<String, Object> play(String playerName, int bet) {
        validateInput(playerName, bet);

        Player player = getOrCreatePlayer(playerName);
        validateBet(player, bet);

        List<Color> selectedColors = selectColors();
        boolean win = checkWin(selectedColors);

        updateBalance(player, win, bet);
        players.put(playerName, player);

        return createResponse(selectedColors, player.getBalance());
    }

    /**
     * validateInput method validates the player name and the bet amount.
     *
     * @param playerName name of the player playing the game
     * @param bet        bet amount placed by the player
     */
    private void validateInput(String playerName, int bet) {
        if (playerName == null || playerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name must not be empty or null");
        }
        if (!playerName.matches(NAME_REGEX)) {
            throw new IllegalArgumentException("Player name must contain only alphabetic characters");
        }
        if (bet <= 0) {
            throw new IllegalArgumentException("Bet amount must be a positive number");
        }
    }

    /**
     * getOrCreatePlayer method returns the player with the given name if it exists.
     * If the player does not exist, it creates a new player with a balance of 1000.
     *
     * @param playerName name of the player
     * @return the player with the given name
     */
    private Player getOrCreatePlayer(String playerName) {
        return players.computeIfAbsent(playerName, k -> new Player(playerName, STARTING_BALANCE));
    }

    /**
     * validateBet method validates the bet amount.
     * It throws an exception if the bet amount is greater than the player's balance.
     *
     * @param player the player playing the game
     * @param bet    bet amount placed by the player
     */
    private void validateBet(Player player, int bet) {
        if (bet > player.getBalance()) {
            throw new IllegalArgumentException("Bet amount cannot be greater than player's balance");
        }
    }

    /**
     * Selects NUM_COLORS random colors from the possible Color values.
     *
     * @return A List of NUM_COLORS selected Color values
     */
    private List<Color> selectColors() {
        List<Color> selectedColors = new ArrayList<>();
        for (int i = 0; i < NUM_COLORS; i++) {
            int index = random.nextInt(Color.values().length);
            selectedColors.add(Color.values()[index]);
        }
        return selectedColors;
    }

    /**
     * Checks if all selected colors are equal.
     *
     * @param selectedColors List of selected colors to check
     * @return True if all selected colors are equal, false otherwise
     */
    private boolean checkWin(List<Color> selectedColors) {
        Color prev = null;
        for (Color color : selectedColors) {
            if (prev == null) {
                prev = color;
            } else {
                if (prev != color) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Updates the player balance based on the outcome of the game. If win is true,
     * player balance is increased by the value of moneyBox. Otherwise, player
     * balance is decreased by the value of the bet.
     *
     * @param player Player object to update balance for
     * @param win    True if the player won, false otherwise
     * @param bet    The amount the player bet in this game
     */
    private void updateBalance(Player player, boolean win, int bet) {
        if (win) {
            player.setBalance(player.getBalance() + moneyBox);
            moneyBox = 0;
        } else {
            player.setBalance(player.getBalance() - bet);
            moneyBox += bet;
        }
    }

    /**
     * Creates a response map with the selected colors and the player balance.
     *
     * @param selectedColors List of selected colors for this game
     * @param balance        Current player balance
     * @return A map with the keys "colors" and "balance"
     */
    private Map<String, Object> createResponse(List<Color> selectedColors, int balance) {
        Map<String, Object> response = new HashMap<>();
        response.put(SELECTED_COLORS, selectedColors);
        response.put(BALANCE, balance);
        return response;
    }
}
