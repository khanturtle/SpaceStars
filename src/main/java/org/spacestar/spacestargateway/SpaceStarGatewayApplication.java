package org.spacestar.spacestargateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class SpaceStarGatewayApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpaceStarGatewayApplication.class, args);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addExposedHeader("*");
		corsConfiguration.addAllowedOrigin("http://localhost:3000");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", corsConfiguration);

		return source;
	}
	@Bean
	public CorsWebFilter corsWebFilter() {
		return new CorsWebFilter(corsConfigurationSource());
	}

}
