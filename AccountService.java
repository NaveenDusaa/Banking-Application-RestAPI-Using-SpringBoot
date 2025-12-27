package com.example.banking.service;

import java.util.List;

import com.example.banking.dto.AccountDto;


public interface AccountService {

	AccountDto createAccount(AccountDto accountDto);
	
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposite(Long id,double amount);
	
	AccountDto withdraw(Long id,double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long id);


	

}
