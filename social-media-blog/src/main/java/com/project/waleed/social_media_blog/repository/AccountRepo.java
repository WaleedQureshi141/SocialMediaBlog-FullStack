package com.project.waleed.social_media_blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.waleed.social_media_blog.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer>
{
    // checking duplicate username
    public Optional<Account> findByUsername(String username);

    // POST: login
    public Optional<Account> findByUsernameAndPassword(String username, String password);
}
