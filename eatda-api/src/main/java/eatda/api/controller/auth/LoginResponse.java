package eatda.api.controller.auth;

import eatda.api.controller.member.MemberResponse;

public record LoginResponse(TokenResponse token, MemberResponse information) {

}
