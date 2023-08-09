package com.boa.gatewayapi.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
@Component
@Slf4j

public class LoggingGlobalPostFilter implements GlobalFilter,Ordered{

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
    public Mono<Void> filter(
      ServerWebExchange exchange,
      GatewayFilterChain chain) {
        log.info("Global Post Filter executed");
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            var response = exchange.getResponse();
            response.setRawStatusCode(201);
            exchange.mutate().response(response).build();
        }));
    }

}
