package com.insa.Consumer1.countries;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface CountryRepository extends JpaRepository<Country, Timestamp> {
}
