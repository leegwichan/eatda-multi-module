package eatda.api.controller.store;

public record CheerRegisterRequest(
        String storeName,
        String storeKakaoId,
        String description) {
}
