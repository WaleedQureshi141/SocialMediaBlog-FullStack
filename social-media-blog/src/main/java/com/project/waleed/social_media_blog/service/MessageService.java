package com.project.waleed.social_media_blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.waleed.social_media_blog.model.Account;
import com.project.waleed.social_media_blog.model.Message;
import com.project.waleed.social_media_blog.repository.AccountRepo;
import com.project.waleed.social_media_blog.repository.MessageRepo;

@Service
public class MessageService 
{
    @Autowired
    MessageRepo repo;

    @Autowired
    AccountRepo accRepo;

    // POST: create new message
    public Message newMessage(Message msg)
    {
        if (!msg.getMessageText().isBlank() 
        && msg.getMessageText().length() <= 255 
        && accRepo.findById(msg.getPostedBy().getAccountId()).isPresent())
        {
            Account acc = accRepo.findById(msg.getPostedBy().getAccountId()).get();
            msg.setPostedBy(acc);
            return repo.save(msg);
        }

        return null;
    }

    // GET: get all messages
    public List<Message> getAllMsg()
    {
        return repo.findAll();
    }

    // GET: get message by id
    public Message getMsgById(int id)
    {
        return repo.findById(id).get();
    }

    // DELETE: delete message by id
    public int delMsg(int id)
    {
        if (repo.findById(id).isPresent())
        {
            repo.deleteById(id);
            return 1;
        }
        return 0;
    }

    // PATCH: update a message by id
    public int updateMsg(int id, Message msg)
    {
        if (
            repo.findById(id).isPresent() 
            && !msg.getMessageText().isBlank()
            && msg.getMessageText().length() <= 255
        )
        {
            Message old = repo.findById(id).get();
            old.setMessageText(msg.getMessageText());
            repo.save(old);
            return 1;
        }
        return 0;
    }

    // GET: messages by an account
    public List<Message> msgByUser(int id)
    {
        if (accRepo.findById(id).isPresent())
        {
            Account acc = accRepo.findById(id).get();
            return repo.findByPostedBy(acc);
        }
        return new ArrayList<>();
    }
}
