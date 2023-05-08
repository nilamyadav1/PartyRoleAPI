package com.partyrole.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partyrole.entities.Account;
import com.partyrole.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	public List<Account> getAccounts(){
		return accountRepository.findAll();
	}

	public Account add(Account account) {
		return accountRepository.save(account);
	}

}
