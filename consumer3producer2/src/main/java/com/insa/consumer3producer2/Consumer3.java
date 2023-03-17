package com.insa.consumer3producer2;


import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


@Service
public class Consumer3 {

    private List<String> lastResult = new ArrayList<>();

    BlockingQueue<List<String>> messageQueue = new LinkedBlockingQueue<>();


    @KafkaListener(topics = "topic-3", groupId = "group-3")
    public void consume(String message) {
        try {
            String[] rows = message.split(";");
            this.setLastResult(List.of(rows));
            messageQueue.put(lastResult);
            System.out.println(lastResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getLastResult() {
        return lastResult;
    }

    public void setLastResult(List<String> lastResult) {
        this.lastResult = lastResult;
    }
}
