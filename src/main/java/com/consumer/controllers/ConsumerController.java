package com.consumer.controllers;

import com.consumer.domain.ConsumerRepository;
import com.consumer.domain.ConsumerUser;
import com.consumer.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping()
    public ResponseEntity<List<ConsumerUser>> consumeAllUsers(@RequestParam("since") Integer number, @RequestParam("per_page") Integer perPage) {
        List<ConsumerUser> consumerUser = consumerService.consumeAllUsers(number, perPage);
        return new ResponseEntity<List<ConsumerUser>>(consumerUser, HttpStatus.OK);
    }

    @GetMapping(value = "/{username}/details" )
    public ResponseEntity<ConsumerUser> consumeUserDetails(@PathVariable("username") String username) {
        ConsumerUser consumerUser = consumerService.consumeUserDetails(username);
        return new ResponseEntity<ConsumerUser>(consumerUser, HttpStatus.OK);
    }
    @GetMapping(value = "/{username}/repos")
    public ResponseEntity<List<ConsumerRepository>> consumeAllUserRepositories(
            @PathVariable("username") String username, @RequestParam("page") Integer page, @RequestParam("per_page") Integer perPage) {
        List<ConsumerRepository> consumerRepository = consumerService.consumeAllUserRepositories(username, page, perPage);
        return new ResponseEntity<List<ConsumerRepository>>(consumerRepository, HttpStatus.OK);
    }
}
