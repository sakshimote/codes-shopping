package com.bank.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.api.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

	List<Loan> findByUserUserId(Long uid);

}
