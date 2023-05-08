package com.partyrole.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partyrole.entities.Agreement;
import com.partyrole.services.AgreementService;

@RestController
@RequestMapping("/agreement")
public class AgreementController {

	  @Autowired AgreementService agreementService;

	  @GetMapping public List<Agreement> getAgreements() { return
	  agreementService.getAgreements(); }

	  @PostMapping("/addAgreement") public void helloAdd(@RequestBody final
	  Agreement agreement) { agreementService.add(agreement); }

	@PutMapping("/putAgreement")
	public String helloPut(@RequestBody final String hello) {
		return hello;
	}

	@PatchMapping("/patchAgreement")
	public String helloPatch(@RequestBody final String hello) {
		return hello;
	}
}
