package practice.Practice.global.security.jwt;


import practice.Practice.domain.auth.RefreshToken;
import practice.Practice.domain.auth.RefreshTokenRepository;
import practice.Practice.global.exception.ExpiredTokenException;
import practice.Practice.global.exception.InvalidTokenException;
import practice.Practice.global.security.TokenResponse;
import io.jsonwebtoken.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RequiredArgsConstructor
@Component("JwtTokenProvider")
public class JwtTokenProvider {

    private String SecretKey = "secret_key";

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;

    public TokenResponse createToken(String accountId){
        return TokenResponse
                .builder()
                .accessToken(createAccessToken(accountId))
                .refreshToken(createRefreshToken(accountId))
                .build();
    }

    // JWT 토큰 생성
    public String createAccessToken(String accountId) {
        Claims claims = Jwts.claims().setSubject(accountId); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessExp() * 1000)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, SecretKey)  // 사용할 암호화 알고리즘과
                .compact();

    return accessToken;
    }

    private String createRefreshToken(String accountId) {

        Date now = new Date();

        String refreshToken = Jwts.builder()
                .setSubject(accountId)
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getRefreshExp() * 1000))//만료시간은
                .signWith(SignatureAlgorithm.HS512, SecretKey)
                .compact();

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .accountId(accountId)
                        .refreshToken(refreshToken)
                        .expiration(jwtProperties.getRefreshExp())
                        .build());

        return refreshToken;
    }


    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser()
                .setSigningKey(SecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }


    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SecretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private String getId(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(SecretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private boolean isRefreshToken(String token) {
        return getClaims(token).get("type").equals("refresh");

    }


    public TokenResponse reissue(String refreshToken) {

        if(!isRefreshToken(refreshToken))
            throw InvalidTokenException.EXCEPTION;

        String accountId = getId(refreshToken);

        return TokenResponse.builder()
                .accessToken(createAccessToken(accountId))
                .refreshToken(refreshToken)
                .build();
    }
}