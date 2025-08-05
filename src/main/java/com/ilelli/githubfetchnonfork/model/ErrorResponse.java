package com.ilelli.githubfetchnonfork.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    int status;
    String message;
}
