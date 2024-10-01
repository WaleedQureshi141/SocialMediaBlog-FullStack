package com.project.waleed.social_media_blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.waleed.social_media_blog.model.Account;
import com.project.waleed.social_media_blog.repository.AccountRepo;

@Service
public class AccountService 
{
    @Autowired
    AccountRepo repo;

    // checking duplicate username
    public boolean usernameExists(String username)
    {
        return repo.findByUsername(username).isPresent();
    }

    // POST: create new account
    public Account register(Account acc)
    {
        if (!acc.getUsername().isBlank() && acc.getPassword().length() >= 4)
        {
            return repo.save(acc);
        }

        return null;
    }

    // POST: logging in
    public Account login(Account acc)
    {
        Optional<Account> res = repo.findByUsernameAndPassword(acc.getUsername(), acc.getPassword());
        if (res.isPresent())
        {
            return res.get();
        }
        
        return null;
    }
}
