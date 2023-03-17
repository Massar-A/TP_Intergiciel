package com.insa.consumer2producer3.countries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountryByCountryNameIgnoreCase(String Country);

    @Query("SELECT AVG(c.TotalConfirmed) FROM Country c")
    Double getAverageConfirmed();

    @Query("SELECT AVG(c.TotalDeaths) FROM Country c")
    Double getAverageDeaths();

}
