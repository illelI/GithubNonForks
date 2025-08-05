package com.ilelli.githubfetchnonfork;

import com.ilelli.githubfetchnonfork.model.GithubRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/github")
class GithubRepositoriesController {
    private final GithubRepositoriesService githubRepositoriesService;

    GithubRepositoriesController(GithubRepositoriesService githubRepositoriesService) {
        this.githubRepositoriesService = githubRepositoriesService;
    }

    @GetMapping("/{username}/getNonForks")
    public ResponseEntity<List<GithubRepository>> getNonForks(@PathVariable String username) {
        return ResponseEntity.ok(githubRepositoriesService.getNonForks(username));
    }
}
