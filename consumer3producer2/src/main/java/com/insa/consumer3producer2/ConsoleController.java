package com.insa.consumer3producer2;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ConsoleController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Consumer3 cs3;

    public ConsoleController(KafkaTemplate<String, String> kafkaTemplate) {
        super();
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("command")
    public ResponseEntity<String> sendCommand(@RequestBody String command) {
        kafkaTemplate.send("topic-2", command);
        String result = cs3.getLastResult();
        return ResponseEntity.ok("Commande envoyée avec succès ! Résultat" + result);
    }
}

