package com.project.waleed.social_media_blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.waleed.social_media_blog.model.Account;
import com.project.waleed.social_media_blog.model.Message;
import java.util.List;


@Repository
public interface MessageRepo extends JpaRepository<Message, Integer>
{
    List<Message> findByPostedBy(Account postedBy);
}