package com.ilelli.githubfetchnonfork.model;

import lombok.Data;

@Data
public class GithubBranch {
    private String name;
    private String lastCommitSHA;
}
