package com.consumer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.consumer.domain.ConsumerRepository;
import com.consumer.domain.ConsumerUser;
import com.consumer.services.ConsumerService;

@RestController
@RequestMapping(value = "/api/users")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping(value = "/{username}/details" )
    public ResponseEntity<ConsumerUser> consumeAPIGitHubByUsername(@PathVariable("username") String username) {
        ConsumerUser consumerUser = consumerService.consumeAPIGitHubByUsername(username);
        return new ResponseEntity<ConsumerUser>(consumerUser, HttpStatus.OK);
    }
    @GetMapping(value = "/{username}/repos")
    public ResponseEntity<List<ConsumerRepository>> consumeAPIGitHubByUsernameAllRepositories(@PathVariable("username") String username) {
        List<ConsumerRepository> consumerRepository = consumerService.consumeAPIGitHubByUsernameAllRepositories(username);
        return new ResponseEntity<List<ConsumerRepository>>(consumerRepository, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<ConsumerUser>> consumeAllUsersSince(@RequestParam("number") Integer number) {
        List<ConsumerUser> consumerUser = consumerService.consumeAllUsersSince(number);
        return new ResponseEntity<List<ConsumerUser>>(consumerUser, HttpStatus.OK);
    }
}
