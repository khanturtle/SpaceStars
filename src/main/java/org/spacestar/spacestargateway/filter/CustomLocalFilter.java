package org.spacestar.spacestargateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomLocalFilter extends AbstractGatewayFilterFactory<CustomLocalFilter.Config> {

	public CustomLocalFilter() {
		super(Config.class);
	}
	@Override
	public GatewayFilter apply(Config config){

		return (exchange, chain) -> {

			if(config.isPre()){
				log.info("pre local filter 1");
			}

			return chain.filter(exchange)
				.then(Mono.fromRunnable(() ->{
					if(config.isPost()){
						log.info("post local filter 1");
					}
			}));
		};
	}
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Config {

		private boolean pre;
		private boolean post;
	}

}
