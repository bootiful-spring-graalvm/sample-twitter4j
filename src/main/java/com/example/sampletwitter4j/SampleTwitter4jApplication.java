package com.example.sampletwitter4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@SpringBootApplication
public class SampleTwitter4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleTwitter4jApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(Twitter twitter) {
        return args -> twitter.timelines()
                .getHomeTimeline()
                .forEach(System.out::println);
    }

    @Bean
    Twitter twitter(//
                    @Value("${twitter.consumerKey}") String twitterConsumerKey,
                    @Value("${twitter.consumerSecret}") String twitterConsumerSecret,
                    @Value("${twitter.oauthTokenKey}") String twitterOauthTokenKey,
                    @Value("${twitter.oauthTokenSecret}") String twitterOauthTokenSecret) {
        var twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(twitterConsumerKey, twitterConsumerSecret);
        twitter.setOAuthAccessToken(new AccessToken(twitterOauthTokenKey, twitterOauthTokenSecret));
        return twitter;
    }

}
