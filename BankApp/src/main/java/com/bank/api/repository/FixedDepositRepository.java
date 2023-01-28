package com.bank.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.api.model.FixedDeposit;

public interface FixedDepositRepository extends JpaRepository<FixedDeposit, Long> {


	List<FixedDeposit> findByUserUserId(Long uid);

}
