package org.digilinq.twitter.api;

import org.digilinq.twitter.web.model.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> findAllTweets();
}
