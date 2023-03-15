package com.insa.Consumer1.countries;

import com.insa.Consumer1.global.Global;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface CountryRepository extends JpaRepository<Country, Timestamp> {
}
