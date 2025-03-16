package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
//			.route("PRODUCTCATALOGSERVICE", r -> r.path("/products/**")
//				.uri("lb://PRODUCTCATALOGSERVICE"))
//			.route("host_route", r -> r.host("*.myhost.org")
//				.uri("http://httpbin.org"))
//			.route("rewrite_route", r -> r.host("*.rewrite.org")
//				.filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
//				.uri("http://httpbin.org"))
//			.route("hystrix_route", r -> r.host("*.hystrix.org")
//				.filters(f -> f.hystrix(c -> c.setName("slowcmd")))
//				.uri("http://httpbin.org"))
//			.route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
//				.filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//				.uri("http://httpbin.org"))
			.route(r -> r.path("/prd")
                    .filters(f -> f.rewritePath("/prd", "/products")) // Rewriting the path
				.uri("lb://PRODUCTCATALOGSERVICE"))
            .route(r -> r.path("/prd/**")
                    .filters(f -> f.rewritePath("/prd/(?<remaining>.*)", "/products/${remaining}")) // Rewriting the path
				.uri("lb://PRODUCTCATALOGSERVICE"))
            
			.build();
	}

}
