package org.spacestar.spacestargateway.filter;

import java.nio.charset.StandardCharsets;

import org.spacestar.spacestargateway.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

	@Autowired
	private JwtUtil jwtUtil;

	public AuthorizationHeaderFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {

		return (exchange, chain) -> {

			HttpHeaders headers = exchange.getRequest().getHeaders();
			if (!headers.containsKey("Authorization")) {
				log.info("Authorization header is missing");
				return onError(exchange, "토큰이 없습니다.", HttpStatus.UNAUTHORIZED);
			}

			String token = headers.get("Authorization").get(0).substring(7);

			try {
				// jwt 검증을 성공한 요청의 헤더에 uuid 추가
				ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
					.header("UUID", jwtUtil.getUuid(token))
					.build();

				return chain.filter(exchange.mutate().request(mutatedRequest).build());

			} catch (IllegalStateException e) {
				HttpStatus status =
					e.getMessage().equals("만료된 토큰입니다.") ? HttpStatus.FORBIDDEN : HttpStatus.UNAUTHORIZED;
				return onError(exchange, e.getMessage(), status);
			}
		};
	}

	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {


		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

		String errorJson = String.format("{\"error\": \"%s\"}", err);
		DataBuffer buffer = response.bufferFactory().wrap(errorJson.getBytes(StandardCharsets.UTF_8));

		return response.writeWith(Mono.just(buffer));
	}

	public static class Config {
		// 기본 생성자 추가
		public Config() {
		}
	}
}
