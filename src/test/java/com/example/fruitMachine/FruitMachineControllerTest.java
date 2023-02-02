package com.example.fruitMachine;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import java.util.Map;
import com.example.fruitMachine.utils.FruitMachine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

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
    public void playSuccessReturnsOkResponse() {
        // Given
        String playerName = "John Doe";
        int bet = 100;
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("result", "success");
        expectedData.put("message", "You won 100 coins");
        when(fruitMachine.play(playerName, bet)).thenReturn(expectedData);

        ResponseEntity<Map<String, Object>> result = fruitMachineController.play(playerName, bet);

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo(expectedData);
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