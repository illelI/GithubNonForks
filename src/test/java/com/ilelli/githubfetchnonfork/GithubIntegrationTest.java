package com.ilelli.githubfetchnonfork;

import com.ilelli.githubfetchnonfork.model.GithubRepository;
import com.ilelli.githubfetchnonfork.model.RepositoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
class GithubIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldGetOnlyNonForkRepositories() {
        // given
        String user = "torvalds";

        ResponseEntity<List<RepositoryResponse>> allReposResponse = new RestTemplate().exchange(
                "https://api.github.com/users/" + user + "/repos",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RepositoryResponse>>() {}
        );

        List<String> forkRepos = allReposResponse.getBody().stream()
                .filter(RepositoryResponse::isFork)
                .map(RepositoryResponse::getName)
                .toList();

        // when
        ResponseEntity<GithubRepository[]> response = restTemplate.getForEntity(
                "/api/v1/github/{username}/getNonForks",
                GithubRepository[].class,
                user
        );

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<GithubRepository> repos = List.of(response.getBody());

        if (!forkRepos.isEmpty() && !repos.isEmpty()) {
            assertThat(repos).extracting(GithubRepository::getRepository).doesNotContainAnyElementsOf(forkRepos);
        }

    }
}
