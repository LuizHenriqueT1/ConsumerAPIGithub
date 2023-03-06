package com.consumer;

import com.consumer.domain.ConsumerRepository;
import com.consumer.domain.ConsumerUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WireMockConfiguration {

    private static String responseBody = null;
    public static MappingBuilder configureGetRequest(String path, Integer status, String responseBody) {
        return WireMock.get(WireMock.urlEqualTo(path))
                .willReturn(WireMock.aResponse()
                        .withStatus(status)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseBody));
    }

    public static void setUserData() throws JsonProcessingException {
        ConsumerUser user = new ConsumerUser();
        user.setId(12345);
        user.setLogin("johndoe");
        user.setHtml_url("https://github.com/johndoe");
        user.setPublic_repos(10);
        user.setCreated_at(new Date().toString());

        responseBody = new ObjectMapper().writeValueAsString(user);
    }

    public static void setListOfUserData() throws JsonProcessingException {
        List<ConsumerUser> userList = new ArrayList<ConsumerUser>();
        ConsumerUser user1 = new ConsumerUser();
        user1.setId(12345);
        user1.setLogin("johndoe");
        user1.setHtml_url("https://github.com/johndoe");
        user1.setPublic_repos(0);
        user1.setCreated_at(null);
        userList.add(user1);

        ConsumerUser user2 = new ConsumerUser();
        user2.setId(67890);
        user2.setLogin("janedoe");
        user2.setHtml_url("https://github.com/janedoe");
        user2.setPublic_repos(0);
        user2.setCreated_at(null);
        userList.add(user2);

        responseBody = new ObjectMapper().writeValueAsString(userList);
    }

    public static void setListUserRepositories() throws JsonProcessingException {
        List<ConsumerRepository> repositoriesList = new ArrayList<>();
        String username = "mojanbo";
        String nameRepository1 = "crud";
        String nameRepository2 = "portifolio";

        ConsumerRepository repos1 = new ConsumerRepository();
        repos1.setId(1);
        repos1.setName(nameRepository1);
        repos1.setHtml_url("https://github.com/" +username+ "/" +nameRepository1);
        repositoriesList.add(repos1);

        ConsumerRepository repos2 = new ConsumerRepository();
        repos2.setId(1);
        repos2.setName(nameRepository2);
        repos2.setHtml_url("https://github.com/" +username+ "/" +nameRepository2);
        repositoriesList.add(repos2);

        responseBody = new ObjectMapper().writeValueAsString(repositoriesList);
    }
    public static String getResponseBody() {
        return responseBody;
    }
}
