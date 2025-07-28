package eatda.api.service.store;

import eatda.domain.client.map.StoreSearchResult;
import eatda.domain.exception.BusinessErrorCode;
import eatda.domain.exception.BusinessException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StoreSearchFilter {

    public List<StoreSearchResult> filterSearchedStores(List<StoreSearchResult> searchResults) {
        return searchResults.stream()
                .filter(this::isValidStore)
                .toList();
    }

    public StoreSearchResult filterStoreByKakaoId(List<StoreSearchResult> searchResults, String kakaoId) {
        return searchResults.stream()
                .filter(store -> store.kakaoId().equals(kakaoId))
                .filter(this::isValidStore)
                .findFirst()
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.STORE_NOT_FOUND));
    }

    private boolean isValidStore(StoreSearchResult store) {
        return store.isFoodStore() && store.isInSeoul();
    }
}
