package eatda.api.controller.store;

import eatda.domain.client.map.StoreSearchResult;

public record StoreSearchResponse(
        String kakaoId,
        String name,
        String address
) {

    public StoreSearchResponse(StoreSearchResult searchResult) {
        this(
                searchResult.kakaoId(),
                searchResult.name(),
                searchResult.lotNumberAddress()
        );
    }
}
