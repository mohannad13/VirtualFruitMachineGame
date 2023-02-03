package com.example.fruitMachine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


import java.util.Map;

import com.example.fruitMachine.utils.FruitMachine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FruitMachineControllerTest {

    @Mock
    private FruitMachine fruitMachine;

    private FruitMachineController fruitMachineController;

    @Before
    public void setUp() {
        fruitMachineController = new FruitMachineController(fruitMachine);
    }


    @Test
    public void testFruitMachineController_defaultConstructor() {
        FruitMachineController controller = new FruitMachineController();
        assertNotNull(controller.getFruitMachine());
    }

    @Test
    public void testSetAndGetMoneyBox() {
        FruitMachine fruitMachine = new FruitMachine();
        fruitMachine.setMoneyBox(100);
        int expectedMoneyBox = 100;
        int actualMoneyBox = fruitMachine.getMoneyBox();
        assertEquals(expectedMoneyBox, actualMoneyBox);
    }

    @Test
    public void playSuccessReturnsOkResponse() {
        String playerName = "John Doe";
        int bet = 100;
        ResponseEntity<Map<String, Object>> result = fruitMachineController.play(playerName, bet);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void playErrorReturnsBadRequestResponse() {
        String playerName = "John Doe";
        int bet = 100;
        String errorMessage = "Insufficient balance";
        when(fruitMachine.play(playerName, bet)).thenThrow(new IllegalArgumentException(errorMessage));

        ResponseEntity<Map<String, Object>> result = fruitMachineController.play(playerName, bet);

        assertThat(result.getStatusCodeValue()).isEqualTo(400);
        assertThat(result.getBody()).isEqualTo(Map.of("error", errorMessage));
    }
}
