package eatda.api.controller.member;

import eatda.domain.domain.member.Member;
import jakarta.validation.constraints.NotBlank;

public record MemberUpdateRequest(@NotBlank String nickname,
                                  @NotBlank String phoneNumber,
                                  boolean optInMarketing) {

    public Member toMemberUpdater() {
        return new Member(nickname, phoneNumber, optInMarketing);
    }
}
