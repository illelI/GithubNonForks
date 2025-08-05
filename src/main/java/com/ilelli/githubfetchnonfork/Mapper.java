package com.ilelli.githubfetchnonfork;

import com.ilelli.githubfetchnonfork.model.BranchResponse;
import com.ilelli.githubfetchnonfork.model.GithubBranch;
import com.ilelli.githubfetchnonfork.model.GithubRepository;
import com.ilelli.githubfetchnonfork.model.RepositoryResponse;

class Mapper {
    public static GithubRepository toGithubRepository(RepositoryResponse response) {
        GithubRepository repo = new GithubRepository();
        repo.setOwner(response.getOwner().getLogin());
        repo.setRepository(response.getName());
        return repo;
    }

    public static GithubBranch toGithubBranch(BranchResponse response) {
        GithubBranch branch = new GithubBranch();
        branch.setName(response.getName());
        branch.setLastCommitSHA(response.getCommit().getSha());
        return branch;
    }
}
