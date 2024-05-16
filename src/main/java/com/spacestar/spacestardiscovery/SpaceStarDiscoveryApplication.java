package com.spacestar.spacestardiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpaceStarDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpaceStarDiscoveryApplication.class, args);
	}

}
