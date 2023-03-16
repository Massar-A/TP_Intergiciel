package com.insa.consumer2producer3.global;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface GlobalRepository extends JpaRepository<Global, Long> {
}
