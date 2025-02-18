package com.example.chat.security;

import com.example.chat.user.Authority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JpaUserDetailsService jpaUserDetailsService;

    @Value("${springboot.jwt.secret.key}")
    private String secret;

    private Key secretKey;

    private final long expirationTime = 1000L * 60 * 60;

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String account, List<Authority> roles) {
        Claims claims = Jwts.claims().setSubject(account);
        claims.put("roles", roles);

        Date now = new Date();

        String token = Jwts.builder()
                .setClaims (claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime () + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }


    /*
        인증이 성공했을 시, SecurityContextHolder에 저장할 Authentication 을 생성한다.
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(this.getAccount(token));

        // 두번째 인자로 password를 넘기는 대신, 빈 문자열을 보낸다. (Token에 비밀번호를 넣는다는 것은.. 끔찍하다.)
        // 비밀번호는 인증과정에서만 사용되고 메모리에서 제거되어야 한다.
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getAccount(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token) {
        try {
            // equalsIgnoreCase() : 두 문자열을 대소문자 구분없이 비교.
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("Bearer ")) {
                return false;
            }

            token = token.split(" ")[1].trim();
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
