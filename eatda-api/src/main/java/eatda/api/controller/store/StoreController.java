package eatda.api.controller.store;

import eatda.api.controller.web.auth.LoginMember;
import eatda.api.service.store.StoreService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/api/shops")
    public ResponseEntity<StoresResponse> getStores(@RequestParam @Min(1) @Max(50) int size) {
        return ResponseEntity.ok(storeService.getStores(size));
    }

    @GetMapping("/api/shop/search")
    public ResponseEntity<StoreSearchResponses> searchStore(@RequestParam String query, LoginMember member) {
        StoreSearchResponses response = storeService.searchStores(query);
        return ResponseEntity.ok(response);
    }
}
