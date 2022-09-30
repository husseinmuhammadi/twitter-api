package com.javastudio.tutorial.twitter.oauth.repository;

import com.javastudio.tutorial.twitter.oauth.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
