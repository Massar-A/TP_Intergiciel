package com.insa.consumer3producer2;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer3 {
    
    @KafkaListener(topics = "topic-3", groupId = "group-3")
    public void consume(String message) {
        // Affiche le contenu du message reçu
        System.out.println("Received message: " + message);
    }
}