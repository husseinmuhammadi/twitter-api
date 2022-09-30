package com.javastudio.tutorial.twitter.oauth.repository;

import com.javastudio.tutorial.twitter.oauth.entity.Tweet;
import com.javastudio.tutorial.twitter.oauth.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TweetRepositoryTest {

    @Autowired
    private TweetRepository tweetRepository;

    @Test
    void shouldSaveTweetAndUser(){
        Tweet tweet = new Tweet();
        tweet.setTweetId(1L);
        User user = new User();
        user.setUserId(1L);
        tweet.setUser(user);
        Tweet savedTweet = tweetRepository.save(tweet);
        tweetRepository.flush();
        assertNotNull(savedTweet.getId());
        System.out.println(savedTweet.getId());
    }
}