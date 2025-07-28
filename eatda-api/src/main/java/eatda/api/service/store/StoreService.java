package eatda.api.service.store;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import eatda.domain.client.map.MapClient;
import eatda.domain.client.map.StoreSearchResult;
import eatda.controller.store.StorePreviewResponse;
import eatda.api.controller.store.StoreSearchResponses;
import eatda.api.controller.store.StoresResponse;
import eatda.domain.domain.store.Store;
import eatda.domain.repository.store.CheerRepository;
import eatda.domain.repository.store.StoreRepository;
import eatda.domain.storage.image.ImageStorage;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final MapClient mapClient;
    private final StoreSearchFilter storeSearchFilter;
    private final StoreRepository storeRepository;
    private final CheerRepository cheerRepository;
    private final ImageStorage imageStorage;

    // TODO : N+1 문제 해결
    public StoresResponse getStores(int size) {
        return storeRepository.findAllByOrderByCreatedAtDesc(Pageable.ofSize(size))
                .stream()
                .map(store -> new StorePreviewResponse(store, getStoreImageUrl(store).orElse(null)))
                .collect(collectingAndThen(toList(), StoresResponse::new));
    }

    private Optional<String> getStoreImageUrl(Store store) {
        return cheerRepository.findRecentImageKey(store)
                .map(imageStorage::getPreSignedUrl);
    }

    public StoreSearchResponses searchStores(String query) {
        List<StoreSearchResult> searchResults = mapClient.searchShops(query);
        List<StoreSearchResult> filteredResults = storeSearchFilter.filterSearchedStores(searchResults);
        return StoreSearchResponses.from(filteredResults);
    }
}
