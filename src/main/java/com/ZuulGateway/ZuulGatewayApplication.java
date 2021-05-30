package com.ZuulGateway;

import com.ZuulGateway.filters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
@EnableZuulProxy
@RestController
public class ZuulGatewayApplication {

	public static void main(String[] args) throws RestClientException, IOException {
        ApplicationContext ctx = SpringApplication.run(ZuulGatewayApplication.class, args);

        SignupController signupController = ctx.getBean(SignupController.class);
	}

	@Autowired
    private AuthRepository authRepository;

    @RequestMapping(value = "/available")
    public String available() {
        return "Available";
    }

    @RequestMapping(value = "/checked-out")
    public String checkedOut() {
        return "Checked-out";
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }

    @Bean
    public Producer producer()
    {
        return new Producer();
    }

}
