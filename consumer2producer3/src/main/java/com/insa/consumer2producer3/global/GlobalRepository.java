package com.insa.consumer2producer3.global;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface GlobalRepository extends JpaRepository<Global, Long> {
    @Query("SELECT (((cast(g.TotalDeaths as float )) / cast(g.TotalConfirmed as float ))*100) AS DeathPercentage FROM Global g")
    Double getDeathPercentage();
}
