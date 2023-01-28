package com.bank.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bank.api.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
