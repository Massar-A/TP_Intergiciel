package com.insa.Producer1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Service
public class Producer1 {

    private static final String TOPIC = "topic-1";
    private static final String URL = "https://api.covid19api.com/summary";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private WebClient webClient = WebClient.builder().build();

    @Scheduled(fixedRate = 1800000) // Exécute la méthode toutes les 30 minutes
    public void produce() {
        System.out.printf("retrieve data from api covid19");
        webClient.get()
                .uri(URL)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(result -> System.out.printf(result))
                .subscribe(message -> kafkaTemplate.send(TOPIC, message));
    }
}

