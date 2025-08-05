package com.ilelli.githubfetchnonfork;

import com.ilelli.githubfetchnonfork.model.GithubRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GithubRepositoriesService {

    private final GithubClient githubClient;

    GithubRepositoriesService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public List<GithubRepository> getNonForks(String username) {
        return githubClient.getNonForks(username);
    }
}
