package com.friendgit.api.controller;

import com.friendgit.api.model.Account;
import com.friendgit.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/login")
    public ResponseEntity<?> getAllAccount(){
        List<Account> accounts = accountRepository.findAll();
        if(accounts.isEmpty()){
            return new ResponseEntity<>("No token", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(accounts,HttpStatus.OK);
        }
    }
}
