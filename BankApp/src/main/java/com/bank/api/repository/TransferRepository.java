package com.bank.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.api.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long>{

}
