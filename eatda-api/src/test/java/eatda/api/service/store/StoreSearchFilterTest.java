package eatda.api.service.store;

import static org.assertj.core.api.Assertions.assertThat;

import eatda.api.service.store.StoreSearchFilter;
import eatda.domain.client.map.StoreSearchResult;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StoreSearchFilterTest {

    private final StoreSearchFilter storeSearchFilter = new StoreSearchFilter();

    @Nested
    class FilterSearchedStores {

        @Test
        void 빈_검색_결과를_넣으면_빈_리스트를_반환한다() {
            List<StoreSearchResult> actual = storeSearchFilter.filterSearchedStores(List.of());

            assertThat(actual).isEmpty();
        }

        @Test
        void 음식점이_아니거나_서울에_위치하지_않는_가게는_제외한다() {
            StoreSearchResult store1 = createStore("1", "서울음식점1","FD6", "서울 강남구 대치동 896-33");
            StoreSearchResult store2 = createStore("2", "카페", "CD2","서울 강남구 대치동 896-33");
            StoreSearchResult store3 = createStore("3", "서울음식점2", "FD6","서울 강남구 대치동 896-33");
            StoreSearchResult store4 = createStore("4", "부산음식점", "FD6","부산 연제구 연산동 632-8");

            List<StoreSearchResult> searchResults = List.of(store1, store2, store3, store4);
            List<StoreSearchResult> actual = storeSearchFilter.filterSearchedStores(searchResults);

            assertThat(actual).containsExactly(store1, store3);
        }

        private StoreSearchResult createStore(String id, String name, String categoryGroupCode, String location) {
            return new StoreSearchResult(id, categoryGroupCode, "음식점 > 식당", "010-1234-1234", name, "https://yapp.co.kr",
                    location, null, 37.0d, 128.0d);
        }
    }
}
