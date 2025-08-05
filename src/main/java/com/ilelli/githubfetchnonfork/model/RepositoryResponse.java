package com.ilelli.githubfetchnonfork.model;

import lombok.Data;

@Data
public class RepositoryResponse {
    private String name;
    private boolean fork;
    private Owner owner;

    @Data
    public static class Owner {
        private String login;
    }
}
