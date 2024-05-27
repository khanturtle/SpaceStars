package org.spacestar.spacestargateway.filter;

import java.util.Map;

import org.spacestar.spacestargateway.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

	@Autowired
	private JwtUtil jwtUtil;

	public AuthorizationHeaderFilter(){
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config){

		return ((exchange, chain) -> {
			String token = exchange.getRequest().getHeaders().get("Authorization").get(0).substring(7); // 헤더의 토큰 파싱 (Bearer 제거)
			Map<String, Object> userInfo = jwtUtil.

		})
	}

	public class Config {
	}
}
