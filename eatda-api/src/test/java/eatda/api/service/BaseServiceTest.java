package eatda.api.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import eatda.domain.DatabaseCleaner;
import eatda.domain.client.map.MapClient;
import eatda.domain.client.oauth.OauthClient;
import eatda.domain.domain.ImageKey;
import eatda.domain.fixture.ArticleGenerator;
import eatda.domain.fixture.CheerGenerator;
import eatda.domain.fixture.MemberGenerator;
import eatda.domain.fixture.StoreGenerator;
import eatda.domain.repository.member.MemberRepository;
import eatda.domain.repository.store.CheerRepository;
import eatda.domain.repository.store.StoreRepository;
import eatda.domain.repository.story.StoryRepository;
import eatda.domain.storage.image.ExternalImageStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@ExtendWith(DatabaseCleaner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class BaseServiceTest {

    private static final ImageKey MOCKED_IMAGE_KEY = new ImageKey("mocked-image-key");
    private static final String MOCKED_IMAGE_URL = "https://example.com/image.jpg";

    @MockitoBean
    protected OauthClient oauthClient;

    @MockitoBean
    protected MapClient mapClient;

    @MockitoBean
    protected ExternalImageStorage externalImageStorage;

    @Autowired
    protected MemberGenerator memberGenerator;

    @Autowired
    protected StoreGenerator storeGenerator;

    @Autowired
    protected CheerGenerator cheerGenerator;

    @Autowired
    protected ArticleGenerator articleGenerator;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected StoreRepository storeRepository;

    @Autowired
    protected CheerRepository cheerRepository;

    @Autowired
    protected StoryRepository storyRepository;

    @BeforeEach
    void mockingImageService() {
        doReturn(MOCKED_IMAGE_URL).when(externalImageStorage).getPreSignedUrl(any());
        doReturn(MOCKED_IMAGE_KEY).when(externalImageStorage).upload(any());
    }
}
