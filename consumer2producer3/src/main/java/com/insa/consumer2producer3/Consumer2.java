package com.insa.consumer2producer3;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer2 {
    @KafkaListener(topics = "Topic2", groupId = "cs2")
    public void consume(String message) {
        // Transforme la commande reçue en requête SQL ou JSON Path
        String query = transformCommandToQuery(message);

        // Exécute la requête sur la base de données PostgreSQL
        executeQuery(query);
    }

    private String transformCommandToQuery(String command) {
        // Implémente la transformation de commande en requête SQL ou JSON Path selon tes besoins
        // Retourne la requête transformée sous forme de chaîne de caractères
        return "";
    }

    private void executeQuery(String query) {
        // Implémente l'exécution de la requête sur la base de données PostgreSQL
    }
}
