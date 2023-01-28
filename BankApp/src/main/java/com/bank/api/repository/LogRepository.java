package com.bank.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.api.model.Log;

public interface LogRepository extends JpaRepository<Log, Long> {

}
