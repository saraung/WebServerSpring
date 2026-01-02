package com.saraung.WebApp.dto;

public record LoginRequest(
        String email,
        String password
) {}
