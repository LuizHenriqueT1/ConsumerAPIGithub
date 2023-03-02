package com.consumer.services;

import com.consumer.domain.ConsumerRepository;
import com.consumer.domain.ConsumerUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsumerServiceTest {

    @InjectMocks
    private ConsumerService service;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 10",
            "10, 15",
            "20, 50"
    })
    void  whenRequestFindAllUserThenReturnListOfConsumerUser(Integer since, Integer perPage) {
        List<ConsumerUser> userList = service.consumeAllUsers(since, perPage);

        String url = "https://api.github.com/users?since=" + since + "&per_page=" + perPage;
        ResponseEntity<List<ConsumerUser>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ConsumerUser>>() {});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(userList.size(), responseEntity.getBody().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "user1",
            "user2",
            "user3"
    })
    void whenRequestUserDetailsThenReturnConsumerUser(String username) {
        ConsumerUser userDetails = service.consumeUserDetails(username);

        String url = "https://api.github.com/users/"+username;
        ResponseEntity<ConsumerUser> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ConsumerUser>(){});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(username, responseEntity.getBody().getLogin());
        assertNotNull(responseEntity.getBody());
        assertNotNull(responseEntity.getBody().getId());
        assertNotNull(responseEntity.getBody().getLogin());
        assertNotNull(responseEntity.getBody().getHtml_url());
        assertNotNull(responseEntity.getBody().getPublic_repos());
        assertNotNull(responseEntity.getBody().getCreated_at());
    }

    @ParameterizedTest
    @MethodSource("parameterforUrlConsumeAllUserRepositories")
    void whenRequestAllUserRepositoriesThenReturnListOfConsumerRepository(String username, Integer page, Integer perPage) {
        List<ConsumerRepository> listUserRepositories = service.consumeAllUserRepositories(username, page, perPage);
        String url = "https://api.github.com/users/" +username+"/repos?page=" +page+ "&per_page=" +perPage;
        ResponseEntity<List<ConsumerRepository>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ConsumerRepository>>(){});

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(listUserRepositories.size(), responseEntity.getBody().size());
    }

    static Stream<Arguments> parameterforUrlConsumeAllUserRepositories() {
        return Stream.of(
                Arguments.of("user1", 1, 10),
                Arguments.of("user1", 2, 20),
                Arguments.of("user2", 3, 10)
        );
    }
}