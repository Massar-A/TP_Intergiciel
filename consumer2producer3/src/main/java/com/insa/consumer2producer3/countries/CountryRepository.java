package com.insa.consumer2producer3.countries;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface CountryRepository extends JpaRepository<Country, Long> {
    public Country findCountryByCountryIgnoreCase(String country);
}
