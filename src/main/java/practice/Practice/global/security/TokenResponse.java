package practice.Practice.global.security;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor

public class TokenResponse {

    private String accessToken;

    private String refreshToken;
}
