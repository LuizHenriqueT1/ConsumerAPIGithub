package com.consumer.services;

import com.consumer.domain.ConsumerRepository;
import com.consumer.domain.ConsumerUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ConsumerService {
	
    RestTemplate restTemplate = new RestTemplate();

    public List<ConsumerUser> consumeAllUsers(Integer since, Integer perPage) {
        String url = "https://api.github.com/users?since=" +since+ "&per_page=" +perPage;
        ConsumerUser[] list = restTemplate.getForEntity(url, ConsumerUser[].class).getBody();
        return Arrays.asList(list);
    }

    public ConsumerUser consumeUserDetails(String username) {
        return restTemplate.getForEntity(
                "https://api.github.com/users/"+username, ConsumerUser.class).getBody();
    }
    public List<ConsumerRepository> consumeAllUserRepositories(String username, Integer page, Integer perPage) {
        String url = "https://api.github.com/users/" +username+"/repos?page=" +page+ "&per_page=" +perPage;
        ConsumerRepository[] listRepositories = restTemplate.getForEntity(url, ConsumerRepository[].class).getBody();
        return Arrays.asList(listRepositories);
    }
}
