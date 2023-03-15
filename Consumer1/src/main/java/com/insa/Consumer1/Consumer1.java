package com.insa.Consumer1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insa.Consumer1.countries.Country;
import com.insa.Consumer1.countries.CountryRepository;
import com.insa.Consumer1.global.Global;
import com.insa.Consumer1.global.GlobalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Consumer1 {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private GlobalRepository globalRepository;

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void consume(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(message);

            // Debugging line to print the received JSON data
            System.out.println("Received message: " + message);

            // parse global data
            JsonNode globalNode = rootNode.get("Global");
            Global global = mapper.treeToValue(globalNode, Global.class);
            globalRepository.save(global);

            // parse countries data
            JsonNode countriesNode = rootNode.get("Countries");
            List<Country> countries = new ArrayList<>();
            for (JsonNode countryNode : countriesNode) {
                Country country = mapper.treeToValue(countryNode, Country.class);
                countries.add(country);
            }
            countryRepository.saveAll(countries);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
