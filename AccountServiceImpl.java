package com.example.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.banking.dto.AccountDto;
import com.example.banking.entity.Account;
import com.example.banking.mapper.AccountMapper;
import com.example.banking.repository.AccountRepositoty;
import com.example.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepositoty accountRepository;
	


//post
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account=AccountMapper.mapToAccount(accountDto);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}


//get
	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository
				.findById(id).
				orElseThrow(()-> new RuntimeException("Account does not exists"));
		return AccountMapper.mapToAccountDto(account);
	}


//deposite
	@Override
	public AccountDto deposite(Long id, double amount) {
		Account account=accountRepository
				.findById(id).
				orElseThrow(()-> new RuntimeException("Account does not exists"));
		
		double total=account.getBalance() + amount;
		account.setBalance(total);
	    Account savedAccount=accountRepository.save(account);
	    return AccountMapper.mapToAccountDto(savedAccount);
	}

  //widthdraw

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account=accountRepository
				.findById(id).
				orElseThrow(()-> new RuntimeException("Account does not exists"));
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient amount");
		}
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}


	//getAllAccounts
	
	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
		   .collect(Collectors.toList());
	}


	

   //delete
	@Override
	public void deleteAccount(Long id) {
		Account account=accountRepository
				.findById(id).
				orElseThrow(()-> new RuntimeException("Account does not exists"));
		accountRepository.deleteById(id);
		
	}

}
