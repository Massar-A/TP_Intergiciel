package com.insa.consumer2producer3.global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Global {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("Date")
    private Timestamp Date;

    @JsonProperty("NewConfirmed")
    private int NewConfirmed;

    @JsonProperty("TotalConfirmed")
    private int TotalConfirmed;

    @JsonProperty("NewDeaths")
    private int NewDeaths;

    @JsonProperty("TotalDeaths")
    private int TotalDeaths;

    @JsonProperty("NewRecovered")
    private int NewRecovered;

    @JsonProperty("TotalRecovered")
    private int TotalRecovered;


    public Global(Long id, Timestamp date, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered) {
        this.id = id;
        Date = date;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
    }

    public Global() {
        super();
    }
}
