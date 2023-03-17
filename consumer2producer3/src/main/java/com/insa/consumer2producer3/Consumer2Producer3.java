package com.insa.consumer2producer3;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.insa.consumer2producer3.countries.Country;
import com.insa.consumer2producer3.countries.CountryRepository;
import com.insa.consumer2producer3.data.Data;
import com.insa.consumer2producer3.global.Global;
import com.insa.consumer2producer3.global.GlobalRepository;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.spec.PSource;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class Consumer2Producer3 {

    private final CountryRepository countryRepository;
    private final GlobalRepository globalRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public Consumer2Producer3(CountryRepository countryRepository, GlobalRepository globalRepository) {
        this.countryRepository = countryRepository;
        this.globalRepository = globalRepository;
    }

    @KafkaListener(topics = "topic-2", groupId = "cs2")
    public void consume(String message) throws Exception {
        List<String> result = processCommand(message);

        kafkaTemplate.send("topic-3", String.join(";", result));

        System.out.println("Réponse envoyée au topic-3 avec succès");
    }

    public List<String> processCommand(String command) throws Exception {
        String[] tokens = command.split(" ");
        List<String> list = new ArrayList<>();
        list.add(tokens[0]);
        switch (tokens[0].toLowerCase()) {

            case "get_global_values":
                // Appeler la méthode appropriée de GlobalRepository pour récupérer les valeurs globales
                list.add(globalRepository.findAll().toString());
                return list;
            case "get_country_values":
                // Appeler la méthode appropriée de CountryRepository pour récupérer les valeurs du pays demandé
                if (tokens.length < 2) {
                    list.add("Le nom du pays doit être spécifié.");
                    return list;
                }
                String countryName = tokens[1];
                list.add(countryRepository.findCountryByCountryNameIgnoreCase(countryName).toString());
                return list;
            case "get_confirmed_avg":
                // Appeler la méthode appropriée de CountryRepository pour récupérer la moyenne des cas confirmés
                list.add(countryRepository.getAverageConfirmed().toString());
                return list;
            case "get_deaths_avg":
                // Appeler la méthode appropriée de CountryRepository pour récupérer la moyenne des décès
                list.add(countryRepository.getAverageDeaths().toString());
                return list;
            case "get_countries_deaths_percent":
                // Appeler la méthode appropriée de CountryRepository pour récupérer le pourcentage de décès par rapport aux cas confirmés
                list.add(globalRepository.getDeathPercentage().toString());
                return list;
            case "export":
                // Appeler la méthode appropriée de CountryRepository pour exporter les données en XML
                // Retourner un message indiquant que l'export a été effectué
                list.add(exportToXml());
                return list;
            case "help":
                // Retourner une liste des commandes et une explication
                list.add("Liste des commandes : \n"
                        + "- Get_global_values (retourne les valeurs globales clés Global du fichier json)\n"
                        + "- Get_country_values nom_pays (retourne les valeurs du pays demandé)\n"
                        + "- Get_confirmed_avg (retourne une moyenne des cas confirmés)\n"
                        + "- Export (permet de télécharger les données au format XML)\n"
);
                return list;
            default:
                list.add("Commande inconnue");
                return list;
        }
    }
    private String exportToXml() throws IOException {
        List<Country> countries = countryRepository.findAll();
        Global global = globalRepository.findAll().get(0);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

        StringWriter writer = new StringWriter();

        xmlMapper.writeValue(writer, new Data(global, countries));

        return writer.toString();
    }

}
