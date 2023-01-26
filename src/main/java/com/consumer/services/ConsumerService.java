package com.consumer.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.consumer.domain.ConsumerRepository;
import com.consumer.domain.ConsumerUser;

@Service
public class ConsumerService {
	
    RestTemplate restTemplate = new RestTemplate();

    public ConsumerUser consumeAPIGitHubByUsername(String username) {
        return restTemplate.getForEntity("https://api.github.com/users/"+username, ConsumerUser.class).getBody();
    }
    public List<ConsumerRepository> consumeAPIGitHubByUsernameAllRepositories(String username) {
        String url = "https://api.github.com/users/"+username+"/repos";
        ConsumerRepository[] listRepositories = restTemplate.getForEntity(url, ConsumerRepository[].class).getBody();
        return Arrays.asList(listRepositories);
    }
    public List<ConsumerUser> consumeAllUsersSince(Integer number) {
        String url = "https://api.github.com/users?since=" + (number != null ? number : "");
        ConsumerUser[] list = restTemplate.getForEntity(url, ConsumerUser[].class).getBody();
        return Arrays.asList(list);
    }

}
