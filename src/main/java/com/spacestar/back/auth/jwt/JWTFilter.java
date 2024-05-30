package com.spacestar.back.auth.jwt;

import com.spacestar.back.auth.jwt.dto.CustomUserDetails;
import com.spacestar.back.auth.jwt.dto.UserDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더가 없거나 Bearer로 시작하지 않으면 다음 필터로 넘어감
        if (authorization == null || !authorization.startsWith("Bearer")) {
            System.out.println("token null");
            filterChain.doFilter(request, response); //다음 필터로 넘어감

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }
        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료(필수)
            return;
        }

        //토큰에서 uuid 와 role을 획득
        String uuid = jwtUtil.getUuid(token);
        String role = jwtUtil.getRole(token);

        //UserDto를 생성하여 값 set
        UserDto userDto = UserDto.builder()
                .uuid(uuid)
                .role(role)
                .build();

        //CustomUserDetails에 회원정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userDto);

        //스프링 시큐리티에 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);


    }
}
