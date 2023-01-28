package com.bank.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.api.model.Withdraw;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {

}
