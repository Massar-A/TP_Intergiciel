package com.insa.consumer3producer2;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;
import java.util.Map;


@Service
public class Consumer3 {

    private String lastResult;

    @KafkaListener(topics = "topic-3", groupId = "group-3")
    public void consume(String message) {
        try {
            String[] rows = message.split(",");
            String result = rows[1];
            lastResult = result;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLastResult() {
        return lastResult;
    }
}
