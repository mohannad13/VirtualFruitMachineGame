package com.example.fruitMachine.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testGetBalance() {
        Player player = new Player("John", 100);
        assertEquals(100, player.getBalance());
    }

    @Test
    public void testSetBalance() {
        Player player = new Player("John", 100);
        player.setBalance(200);
        assertEquals(200, player.getBalance());
    }
}
