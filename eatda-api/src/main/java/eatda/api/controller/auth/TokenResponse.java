package eatda.api.controller.auth;

public record TokenResponse(String accessToken, String refreshToken) {
}
