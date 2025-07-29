package eatda.api.controller.store;

public record CheerRegisterRequest(
        String storeKakaoId,
        String storeName,
        String description) {
}
