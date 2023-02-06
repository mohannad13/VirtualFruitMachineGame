package com.example.fruitMachine;

import com.example.fruitMachine.gameLogic.FruitMachine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/fruit-machine")
public class FruitMachineController {

    private FruitMachine fruitMachine;

    public FruitMachineController() {
        fruitMachine = new FruitMachine();
    }

    public FruitMachineController(FruitMachine fruitMachine) {
        this.fruitMachine = fruitMachine;
    }

    public FruitMachine getFruitMachine() {
        return fruitMachine;
    }

    @GetMapping("/play")
    public ResponseEntity<Map<String, Object>> play(@RequestParam("player") String playerName,
                                                    @RequestParam("bet") int bet) {
        try {
            Map<String, Object> response = fruitMachine.play(playerName, bet);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

}
