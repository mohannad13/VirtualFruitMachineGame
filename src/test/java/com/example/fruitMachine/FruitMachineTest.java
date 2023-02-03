package com.example.fruitMachine;

import static com.example.fruitMachine.utils.FruitMachine.BALANCE;
import static com.example.fruitMachine.utils.FruitMachine.SELECTED_COLORS;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import com.example.fruitMachine.utils.FruitMachine;
import org.junit.jupiter.api.Test;

public class FruitMachineTest {
    private final FruitMachine fruitMachine = new FruitMachine();
    private final String playerName = "player";

    @Test
    public void testPlayValidInputReturnsColorsAndUpdatedBalance() {
        Map<String, Object> result = fruitMachine.play(playerName, 100);
        assertNotNull(result.get(SELECTED_COLORS));
        assertNotNull(result.get(BALANCE));
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
        int initialBalance = (int) fruitMachine.play(playerName, 100).get(BALANCE);
        int updatedBalance = (int) fruitMachine.play(playerName, 200).get(BALANCE);
        assertNotEquals(initialBalance, updatedBalance);
    }
}
