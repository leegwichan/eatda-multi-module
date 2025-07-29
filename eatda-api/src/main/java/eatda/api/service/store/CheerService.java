package eatda.api.service.store;

import eatda.domain.client.map.MapClient;
import eatda.domain.client.map.StoreSearchResult;
import eatda.api.controller.store.CheerPreviewResponse;
import eatda.api.controller.store.CheerRegisterRequest;
import eatda.api.controller.store.CheerResponse;
import eatda.api.controller.store.CheersResponse;
import eatda.domain.domain.Image;
import eatda.domain.domain.ImageDomain;
import eatda.domain.domain.ImageKey;
import eatda.domain.domain.member.Member;
import eatda.domain.domain.store.Cheer;
import eatda.domain.domain.store.Store;
import eatda.domain.exception.BusinessErrorCode;
import eatda.domain.exception.BusinessException;
import eatda.domain.repository.member.MemberRepository;
import eatda.domain.repository.store.CheerRepository;
import eatda.domain.repository.store.StoreRepository;
import eatda.domain.storage.image.ImageStorage;
import eatda.domain.domain.store.StoreSearchFilter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CheerService {

    private static final int MAX_CHEER_SIZE = 3;

    private final MapClient mapClient;
    private final StoreSearchFilter storeSearchFilter;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final CheerRepository cheerRepository;
    private final ImageStorage imageStorage;

    @Transactional
    public CheerResponse registerCheer(CheerRegisterRequest request, MultipartFile imageFile, long memberId) {
        Member member = memberRepository.getById(memberId);
        validateRegisterCheer(member, request.storeKakaoId());

        List<StoreSearchResult> searchResults = mapClient.searchShops(request.storeName());
        StoreSearchResult result = storeSearchFilter.filterStoreByKakaoId(searchResults, request.storeKakaoId());
        ImageKey imageKey = imageStorage.upload(new Image(ImageDomain.CHEER, imageFile));

        Store store = storeRepository.findByKakaoId(result.kakaoId())
                .orElseGet(() -> storeRepository.save(result.toStore())); // TODO 상점 조회/저장 동시성 이슈 해결
        Cheer cheer = cheerRepository.save(new Cheer(member, store, request.description(), imageKey));
        return new CheerResponse(cheer, imageStorage.getPreSignedUrl(imageKey), store);
    }

    private void validateRegisterCheer(Member member, String storeKakaoId) {
        if (cheerRepository.countByMember(member) >= MAX_CHEER_SIZE) {
            throw new BusinessException(BusinessErrorCode.FULL_CHEER_SIZE_PER_MEMBER);
        }
        if (cheerRepository.existsByMemberAndStoreKakaoId(member, storeKakaoId)) {
            throw new BusinessException(BusinessErrorCode.ALREADY_CHEERED);
        }
    }

    @Transactional(readOnly = true)
    public CheersResponse getCheers(int size) {
        List<Cheer> cheers = cheerRepository.findAllByOrderByCreatedAtDesc(Pageable.ofSize(size));
        return toCheersResponse(cheers);
    }

    private CheersResponse toCheersResponse(List<Cheer> cheers) {
        return new CheersResponse(cheers.stream()
                .map(cheer -> new CheerPreviewResponse(cheer, cheer.getStore(),
                        imageStorage.getPreSignedUrl(cheer.getImageKey())))
                .toList());
    }
}
