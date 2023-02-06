package com.example.fruitMachine.utils;

import com.example.fruitMachine.gameLogic.Color;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ColorTest {

    @Test
    public void testColorValues() {
        assertEquals(Color.BLACK.getName(), "black");
        assertEquals(Color.WHITE.getName(), "white");
        assertEquals(Color.GREEN.getName(), "green");
        assertEquals(Color.YELLOW.getName(), "yellow");
    }
}
