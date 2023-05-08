package com.partyrole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partyrole.entities.Agreement;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, String>{

}
