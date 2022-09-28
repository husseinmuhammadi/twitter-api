package org.digilinq.twitter.web.resources;

import org.digilinq.twitter.api.TweetService;
import org.digilinq.twitter.web.model.Tweet;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/tweets")
public class TweetsResource {

    @Inject
    TweetService tweetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> findAllRealtimeTweet(){
        return tweetService.findAllTweets();
    }


}
