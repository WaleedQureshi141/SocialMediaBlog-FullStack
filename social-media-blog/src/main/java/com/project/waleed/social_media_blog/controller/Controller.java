package com.project.waleed.social_media_blog.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.waleed.social_media_blog.model.Account;
import com.project.waleed.social_media_blog.model.Message;
import com.project.waleed.social_media_blog.service.AccountService;
import com.project.waleed.social_media_blog.service.MessageService;

@RestController
// @RequestMapping("")
@CrossOrigin("http://localhost:4200")
public class Controller 
{
    @Autowired
    AccountService accService;

    @Autowired
    MessageService msgService;

    // ACCOUNT HANDLERS

    // POST: creating new account
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account acc)
    {
        if (accService.usernameExists(acc.getUsername()))
        {
            return new ResponseEntity<>(HttpStatus.valueOf(409));
        }
        else
        {
            Account res = accService.register(acc);

            if (res == null)
            {
                return new ResponseEntity<>(HttpStatus.valueOf(400));
            }
            else
            {
                return new ResponseEntity<>(res, HttpStatus.valueOf(200));
            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account acc)
    {
        Account res = accService.login(acc);

        if (res != null)
        {
            return new ResponseEntity<>(res, HttpStatus.valueOf(200));
        }
        
        return new ResponseEntity<>(HttpStatus.valueOf(401));
    }

    // MESSAGE HANDLERS

    // POST: create new message
    @PostMapping("/messages")
    public ResponseEntity<Message> createMsg(@RequestBody Message msg)
    {
        msg.setTimePostedEpoch(Instant.now().toEpochMilli());
        Message res = msgService.newMessage(msg);

        if (res != null)
        {
            return new ResponseEntity<>(res, HttpStatus.valueOf(200));
        }

        return new ResponseEntity<>(HttpStatus.valueOf(400));
    }

    // GET: get all messages
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMsg()
    {
        return new ResponseEntity<>(msgService.getAllMsg(), HttpStatus.OK);
    }

    // GET: get message by id
    @GetMapping("/messages/{id}")
    public ResponseEntity<Message> getMsgById(@PathVariable int id)
    {
        return new ResponseEntity<>(msgService.getMsgById(id), HttpStatus.OK);
    }

    // DELETE: delete message by id
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Integer> delMsgById(@PathVariable int id)
    {
        int res = msgService.delMsg(id);

        if (res == 1)
        {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PATCH: updates a message by id
    @PatchMapping("/messages/{id}")
    public ResponseEntity<Integer> updateMsgById(@PathVariable int id, @RequestBody Message msg)
    {
        int res = msgService.updateMsg(id, msg);

        if (res == 1)
        {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.valueOf(400));
    }

    // GET: get all messages by account
    @GetMapping("/accounts/{id}/messages")
    public ResponseEntity<List<Message>> getByPostedBy(@PathVariable int id)
    {
        return new ResponseEntity<>(msgService.msgByUser(id), HttpStatus.OK);
    }
}
