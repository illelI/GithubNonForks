package com.ilelli.githubfetchnonfork.model;

import lombok.Data;

import java.util.List;

@Data
public class GithubRepository {
    private String repository;
    private String owner;
    private List<GithubBranch> branches;
}
