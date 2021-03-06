package com.codependent.mutualauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SecureClientApplication {

    @PostConstruct
    public void initSsl() {
        System.setProperty("javax.net.ssl.keyStore", Thread.currentThread().getContextClassLoader().getResource("client-keystore.p12").getPath());
        System.setProperty("javax.net.ssl.keyStorePassword", "secret");
        System.setProperty("javax.net.ssl.trustStore", Thread.currentThread().getContextClassLoader().getResource("client-truststore.p12").getPath());
        System.setProperty("javax.net.ssl.trustStorePassword", "secret");
    }

    @Bean
    public RestTemplate template() throws Exception {
        RestTemplate template = new RestTemplate();
        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(SecureClientApplication.class, args);
    }
}
