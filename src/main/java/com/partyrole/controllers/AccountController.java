package com.partyrole.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partyrole.entities.Account;
import com.partyrole.services.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping
	public ResponseEntity<List<Account>> getAccounts() {
		return ResponseEntity.ok(accountService.getAccounts());
	}

	@PostMapping("/addAccount")
	public ResponseEntity<Account> helloAdd(@RequestBody final Account account) {
		return new ResponseEntity<>(accountService.add(account),HttpStatus.CREATED);
	}
}
