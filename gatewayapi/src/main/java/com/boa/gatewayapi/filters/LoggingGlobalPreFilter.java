package com.boa.gatewayapi.filters;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import com.boa.gatewayapi.dtos.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.result.view.RequestContext;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.github.wnameless.json.unflattener.JsonUnflattener;

import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class LoggingGlobalPreFilter implements GlobalFilter,Ordered {

	@Autowired
    private RestTemplate restTemplate;



    private ObjectMapper objectMapper;

    @Value("${apiUrl}")
    private String apiUrl;
    @Value("${api}")
    private String api;

    @Value("${boauser}")
    private String userName;

    @Value("${password}")
    private String password;


    @Override
    public Mono<Void> filter(
      ServerWebExchange exchange,
      GatewayFilterChain chain) {
        log.info("Global Pre Filter executed");
        
     // TODO Auto-generated method stub
     		//Step: 1
		/*
		 * RequestContext ctx = RequestContext.getCurrentContext();
		 * 
		 * HttpServletRequest servletRequest = ctx.getRequest();
		 * System.out.println("Entering pre filter........"); System.out.println(
		 * servletRequest.getRemoteAddr()); System.out.println("PreFilter: " +
		 * String.format("%s request to %s", servletRequest.getMethod(),
		 * servletRequest.getRequestURL().toString()));
		 * 
		 * //http://localhost:8765/api/customer/individuals/v1.0/?userName=eswari&
		 * userPwd=Test@123 Map<String, List<String>>
		 * params=ctx.getRequestQueryParams();
		 * 
		 * 
		 * List<String> data =params.values().stream() .flatMap(Collection::stream)
		 * .collect(Collectors.toList());
		 * 
		 * System.out.println(data.get(0)+","+data.get(1));
		 */
             String token="";

             //Redirect to JWT token
             JwtRequest jwtRequest=new JwtRequest();
             jwtRequest.setUserName(userName);
             jwtRequest.setUserPwd(password);

             //step 2

             try {
                 HttpHeaders headers = new HttpHeaders();
                 headers.setContentType(MediaType.APPLICATION_JSON);
                 HttpEntity request = new HttpEntity<>(jwtRequest, headers);
                 //phase 1 get jwt token
                 //synchronous inter service communication
                 //http://localhost:9091/signin
     	    /*
     	     * {
     	"userName":"eswari",
     	"userPwd":"test@123"
     }
     	     */
                 //step 3
                 ResponseEntity<?> authResponse = restTemplate.
                         postForEntity(apiUrl + "signin", request, String.class);
                 System.out.println(authResponse.getBody().toString());

                 token=parseString(authResponse.getBody().toString());

                 //step4

                 //Verification

                 headers = new HttpHeaders();
                 headers.setContentType(MediaType.APPLICATION_JSON);
                 headers.set("Authorization", "Bearer "+token);

                 request = new HttpEntity<String>(null,headers);

                 ResponseEntity<String> responseEntityStr = restTemplate.
                         exchange(apiUrl+api, HttpMethod.GET, request,
                                 String.class);
                 System.out.println(responseEntityStr.getBody());
                 System.out.println("token : {} Verification Passed"+token);

                 //Routing requests
                 //default router
                 //ctx.setSendZuulResponse(true);




                 return chain.filter(exchange).then(Mono.fromRunnable(()->{
                     var response = exchange.getResponse();
                     response.setRawStatusCode(201);
                     exchange.mutate().response(response).build();
                 }));
             }
             catch(Exception exception)
             {
                 System.out.println("token : {} Validation failed" + token );
                 //Do not route requests
                 //ctx.setSendZuulResponse(false);
                 //responseError(ctx, -403, "invalid token");
                 return chain.filter(exchange).then(Mono.fromRunnable(()->{
                	 var response = exchange.getResponse();
                     response.setRawStatusCode(401);
                     response.setComplete();
                     exchange.mutate().response(response).build();
                 }));
             }

           
        
        
    }
    @Override
    public int getOrder() {
        return 0;
    }

    
    private String toJsonString(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            System.out.println("json Serialization failed"+e);
            return null;
        }
    }


    //response string to object and separates the token

    private String parseString(String response)
    {
        JSONParser parser = new JSONParser();
        String token="";
        try {

            // Put above JSON content to crunchify.txt file and change path location
            Object obj = parser.parse(response);
            JSONObject jsonObject = (JSONObject) obj;

            // JsonFlattener: A Java utility used to FLATTEN nested JSON objects
            String flattenedJson = JsonFlattener.flatten(jsonObject.toString());
            //log("\n=====Simple Flatten===== \n" + flattenedJson);

            Map<String, Object> flattenedJsonMap = JsonFlattener.flattenAsMap(jsonObject.toString());
            token=(String) flattenedJsonMap.get("token");
            //log("\n=====Flatten As Map=====\n" + flattenedJson);
            // We are using Java8 forEach loop. More info: https://crunchify.com/?p=8047
            //flattenedJsonMap.forEach((k, v) -> log(k + " : " + v));

            // Unflatten it back to original JSON
            String nestedJson = JsonUnflattener.unflatten(flattenedJson);
            System.out.println("\n=====Unflatten it back to original JSON===== \n" + nestedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }

}