package eatda.service.auth;

import eatda.domain.client.oauth.OauthClient;
import eatda.domain.client.oauth.OauthMemberInformation;
import eatda.domain.client.oauth.OauthToken;
import eatda.controller.auth.LoginRequest;
import eatda.controller.member.MemberResponse;
import eatda.domain.domain.member.Member;
import eatda.domain.repository.member.MemberRepository;
import java.net.URI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final OauthClient oauthClient;
    private final MemberRepository memberRepository;

    public URI getOauthLoginUrl(String origin) {
        return oauthClient.getOauthLoginUrl(origin);
    }

    @Transactional
    public MemberResponse login(LoginRequest request) {
        OauthToken oauthToken = oauthClient.requestOauthToken(request.code(), request.origin());
        OauthMemberInformation oauthInformation = oauthClient.requestMemberInformation(oauthToken);

        Optional<Member> optionalMember = memberRepository.findBySocialId(Long.toString(oauthInformation.socialId()));
        boolean isFirstLogin = optionalMember.isEmpty();
        return new MemberResponse(
                optionalMember.orElseGet(() -> memberRepository.save(oauthInformation.toMember())),
                isFirstLogin);
    }
}
