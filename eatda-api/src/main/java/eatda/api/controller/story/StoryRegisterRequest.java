package eatda.api.controller.story;

public record StoryRegisterRequest(
        String query,
        String storeKakaoId,
        String description
) {
}
