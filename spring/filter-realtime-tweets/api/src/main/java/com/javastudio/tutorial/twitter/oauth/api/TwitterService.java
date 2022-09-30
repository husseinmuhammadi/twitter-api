package com.javastudio.tutorial.twitter.oauth.api;

import com.javastudio.tutorial.twitter.oauth.dto.TweetDTO;

public interface TwitterService {
    TweetDTO save(TweetDTO tweetDTO);
}
