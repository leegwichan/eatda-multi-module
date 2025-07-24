package eatda.domain.fixture;

import eatda.domain.domain.ImageKey;
import eatda.domain.domain.member.Member;
import eatda.domain.domain.store.Cheer;
import eatda.domain.domain.store.Store;
import eatda.domain.repository.store.CheerRepository;
import org.springframework.stereotype.Component;

@Component
public class CheerGenerator {

    private static final String DEFAULT_IMAGE_KEY = "generator-cheer-image-key";
    private static final String DEFAULT_DESCRIPTION = "응원합니다!";

    private final CheerRepository cheerRepository;

    public CheerGenerator(CheerRepository cheerRepository) {
        this.cheerRepository = cheerRepository;
    }

    public Cheer generateAdmin(Member member, Store store) {
        Cheer cheer = new Cheer(member, store, DEFAULT_DESCRIPTION, new ImageKey(DEFAULT_IMAGE_KEY), true);
        return cheerRepository.save(cheer);
    }

    public Cheer generateCommon(Member member, Store store) {
        return generateCommon(member, store, DEFAULT_IMAGE_KEY);
    }

    public Cheer generateCommon(Member member, Store store, String imageKey) {
        Cheer cheer = new Cheer(member, store, DEFAULT_DESCRIPTION, new ImageKey(imageKey), false);
        return cheerRepository.save(cheer);
    }
}
