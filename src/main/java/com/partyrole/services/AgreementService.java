package com.partyrole.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyrole.entities.Agreement;
import com.partyrole.repository.AgreementRepository;

@Service
public class AgreementService {

	@Autowired
	AgreementRepository agreementRepository;

	public List<Agreement> getAgreements(){
		return agreementRepository.findAll();
	}

	public void add(Agreement agreement) {
		agreementRepository.save(agreement);
	}

}
