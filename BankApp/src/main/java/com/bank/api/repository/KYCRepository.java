package com.bank.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.api.model.KYC;

public interface KYCRepository extends JpaRepository<KYC, Long> {

	
	

	List<KYC> findByUserUserId(Long uid);

}
