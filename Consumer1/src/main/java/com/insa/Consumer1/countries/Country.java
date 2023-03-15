package com.insa.Consumer1.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Country {

    @JsonProperty("Country")
    @Column(length = 200)
    private String Country;

    @JsonProperty("Slug")
    @Column(length = 200)
    private String Slug;

    @JsonProperty("CountryCode")
    @Column(length = 6)
    private String CountryCode;

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

    public Country(String country, String slug, String countryCode, Long id, Timestamp date, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered) {
        Country = country;
        Slug = slug;
        CountryCode = countryCode;
        this.id = id;
        Date = date;
        NewConfirmed = newConfirmed;
        TotalConfirmed = totalConfirmed;
        NewDeaths = newDeaths;
        TotalDeaths = totalDeaths;
        NewRecovered = newRecovered;
        TotalRecovered = totalRecovered;
    }

    public Country() {

    }
}
