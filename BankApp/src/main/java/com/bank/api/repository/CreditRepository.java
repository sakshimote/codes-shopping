package com.bank.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.api.model.Credit;

public interface CreditRepository extends JpaRepository<Credit, Long> {

}
