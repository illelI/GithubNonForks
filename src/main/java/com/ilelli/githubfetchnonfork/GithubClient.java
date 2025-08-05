package com.ilelli.githubfetchnonfork;

import com.ilelli.githubfetchnonfork.model.BranchResponse;
import com.ilelli.githubfetchnonfork.model.GithubBranch;
import com.ilelli.githubfetchnonfork.model.GithubRepository;
import com.ilelli.githubfetchnonfork.model.RepositoryResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
class GithubClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "https://api.github.com";

    GithubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GithubRepository> getNonForks(String user) {
        String reposUrl = baseUrl + "/users/" + user + "/repos";

        try {

            ResponseEntity<List<RepositoryResponse>> response = restTemplate.exchange(
                    reposUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<RepositoryResponse>>() {
                    }
            );


            List<GithubRepository> nonForks = response.getBody().stream()
                    .filter(repo -> !repo.isFork())
                    .map(Mapper::toGithubRepository)
                    .toList();

            nonForks.forEach(repo -> repo.setBranches(getBranchesForRepo(repo.getOwner(), repo.getRepository())));

            return nonForks;
        } catch (HttpClientErrorException.NotFound ex) {
            throw new UserNotFoundException("User not found");
        }
    }

    private List<GithubBranch> getBranchesForRepo(String owner, String repo) {
        String branchUrl = baseUrl + "/repos/" + owner + "/" + repo + "/branches";

        ResponseEntity<List<BranchResponse>> response = restTemplate.exchange(
                branchUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BranchResponse>>() {
                }
        );

        return response.getBody().stream()
                .map(Mapper::toGithubBranch)
                .toList();
    }


}
