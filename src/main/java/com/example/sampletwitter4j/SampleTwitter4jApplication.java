package com.example.sampletwitter4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import twitter4j.Twitter;

import java.util.Map;


@SpringBootApplication
public class SampleTwitter4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleTwitter4jApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(Twitter twitter) {
        return args -> twitter.v1().timelines().getHomeTimeline().forEach(System.out::println);
    }

    @Bean
    Twitter twitter(
            @Value("${TWITTER_CONSUMERKEY}") String twitterConsumerKey,
            @Value("${TWITTER_CONSUMERSECRET}") String twitterConsumerSecret,
            @Value("${TWITTER_OAUTHTOKENKEY}") String tokenKey,
            @Value("${TWITTER_OAUTHTOKENSECRET}") String tokenKeySecret) {
        return Twitter
                .newBuilder()//
                .oAuthConsumer(twitterConsumerKey, twitterConsumerSecret)//
                .oAuthAccessToken(tokenKey, tokenKeySecret)
                .build();
    }

}
