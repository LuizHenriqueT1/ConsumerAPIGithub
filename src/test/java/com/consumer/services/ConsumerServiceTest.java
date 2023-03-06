package com.consumer.services;

import com.consumer.WireMockConfiguration;
import com.consumer.domain.ConsumerRepository;
import com.consumer.domain.ConsumerUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsumerServiceTest {

    @LocalServerPort
    private int serverPort;

    private WireMockServer wireMockServer;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restTemplate = new RestTemplate();
        wireMockServer = new WireMockServer(options().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor(wireMockServer.port());

        restTemplate = restTemplateBuilder.rootUri("http://localhost:" + serverPort).build();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }


    @Test
    public void  whenRequestFindAllUserThenReturnListOfConsumerUser() throws JsonProcessingException {
        Integer since = 0;
        Integer perPage = 10;
        // Configurando a resposta simulada do WireMock
        WireMockConfiguration.setListOfUserData();
        String responseBody = WireMockConfiguration.getResponseBody();
        MappingBuilder mappingBuilder = WireMockConfiguration.configureGetRequest(
                "/users?since=" + since + "&per_page=" + perPage, 200, responseBody);
        WireMock.stubFor(mappingBuilder);

        String url = "http://localhost:" + wireMockServer.port() + "/users?since=" + since + "&per_page=" + perPage;
        ResponseEntity<List<ConsumerUser>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ConsumerUser>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isNotEmpty();
        assertThat(responseEntity.getHeaders().containsValue("Content-Type"));
        assertThat(responseEntity.getHeaders().containsValue("application/json"));
    }

    @Test
    public void whenRequestUserDetailsThenReturnConsumerUser() throws JsonProcessingException {
        // configuração da resposta simulada do Wiremock
        ConsumerUser user = new ConsumerUser();
        WireMockConfiguration.setUserData();
        String responseBody = WireMockConfiguration.getResponseBody();
        MappingBuilder mappingBuilder = WireMockConfiguration.configureGetRequest(
                "/users/" + user.getLogin(), 200, responseBody);
        WireMock.stubFor(mappingBuilder);

        // realiza a solitação HTTP para URL simulada
        String url = "http://localhost:" + wireMockServer.port() + "/users/" + user.getLogin();
        ResponseEntity<ConsumerUser> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<ConsumerUser>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getHeaders().containsValue("Content-Type"));
        assertThat(responseEntity.getHeaders().containsValue("application/json"));
        assertThat(responseEntity.getBody().getId()).isNotNull();
        assertThat(responseEntity.getBody().getLogin()).isNotNull();
        assertThat(responseEntity.getBody().getHtml_url()).isNotNull();
        assertThat(responseEntity.getBody().getPublic_repos()).isNotNull();
        assertThat(responseEntity.getBody().getCreated_at()).isNotNull();
    }

    @Test
    public void whenRequestUserRepositoriesThenReturnListConsumerRepository() throws JsonProcessingException {
        String username = "mojanbo";
        Integer page = 1;
        Integer perPage = 10;

        WireMockConfiguration.setListUserRepositories();
        String responseBody = WireMockConfiguration.getResponseBody();
        MappingBuilder mappingBuilder = WireMockConfiguration.configureGetRequest(
                "/users/"+username+"/repos?page="+page+"&per_page="+perPage, 200, responseBody);
        WireMock.stubFor(mappingBuilder);

        String url = "http://localhost:" + wireMockServer.port() + "/users/"+username+"/repos?page="+page+"&per_page="+perPage;
        ResponseEntity<List<ConsumerRepository>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ConsumerRepository>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isNotEmpty();
        assertThat(responseEntity.getHeaders().containsValue("Content-Type"));
        assertThat(responseEntity.getHeaders().containsValue("application/json"));
    }
}