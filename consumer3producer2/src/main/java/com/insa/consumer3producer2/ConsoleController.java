package com.insa.consumer3producer2;


import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class ConsoleController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Consumer3 cs3 = new Consumer3();

    public ConsoleController(KafkaTemplate<String, String> kafkaTemplate) {
        super();
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("command")
    @Cacheable(sync = false)
    public ResponseEntity<Object> sendCommand(@RequestBody String command) throws IOException, InterruptedException {
        kafkaTemplate.send("topic-2", command);
        List<String> result = cs3.messageQueue.take();
        if(!Objects.equals(result.get(0).toLowerCase(), "export")){
            return ResponseEntity.ok(result.get(result.size()-1));
        }
        else{
            String data = result.get(result.size()-1);
            File file = new File("data.xml");
            try (Writer writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(data);
            }
            Resource resource = new FileSystemResource(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.xml");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_XML)
                    .body(resource);
        }
    }
}

