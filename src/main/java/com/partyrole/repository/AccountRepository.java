package com.partyrole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partyrole.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

}
