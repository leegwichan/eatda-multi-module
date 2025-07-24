package eatda.domain.repository;

import eatda.domain.fixture.CheerGenerator;
import eatda.domain.fixture.MemberGenerator;
import eatda.domain.fixture.StoreGenerator;
import eatda.domain.repository.member.MemberRepository;
import eatda.domain.repository.store.CheerRepository;
import eatda.domain.repository.store.StoreRepository;
import eatda.domain.repository.story.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({MemberGenerator.class, StoreGenerator.class, CheerGenerator.class})
@DataJpaTest
public abstract class BaseRepositoryTest {

    @Autowired
    protected MemberGenerator memberGenerator;

    @Autowired
    protected StoreGenerator storeGenerator;

    @Autowired
    protected CheerGenerator cheerGenerator;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected StoreRepository storeRepository;

    @Autowired
    protected CheerRepository cheerRepository;

    @Autowired
    protected StoryRepository storyRepository;
}
