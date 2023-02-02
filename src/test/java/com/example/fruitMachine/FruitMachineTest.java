package com.example.fruitMachine;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import com.example.fruitMachine.utils.FruitMachine;
import org.junit.jupiter.api.Test;

public class FruitMachineTest {
    private FruitMachine fruitMachine = new FruitMachine();
    private String playerName = "player1";

    @Test
    public void testPlayValidInputReturnsColorsAndUpdatedBalance() {
        Map<String, Object> result = fruitMachine.play(playerName, 100);
        assertNotNull(result.get("selectedColors"));
        assertNotNull(result.get("balance"));
    }

    @Test
    public void testPlayInvalidPlayerNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> fruitMachine.play(null, 100));
        assertThrows(IllegalArgumentException.class, () -> fruitMachine.play("", 100));
    }

    @Test
    public void testPlayInvalidBetThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> fruitMachine.play(playerName, 0));
        assertThrows(IllegalArgumentException.class, () -> fruitMachine.play(playerName, -100));
    }

    @Test
    public void testPlayBetGreaterThanBalanceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> fruitMachine.play(playerName, 1500));
    }

    @Test
    public void testPlayMultiplePlaysReturnsUpdatedBalance() {
        int initialBalance = (int) fruitMachine.play(playerName, 100).get("balance");
        int updatedBalance = (int) fruitMachine.play(playerName, 200).get("balance");
        assertNotEquals(initialBalance, updatedBalance);
    }
}
