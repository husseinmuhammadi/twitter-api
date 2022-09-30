package org.interview.oauth.twitter.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.interview.oauth.twitter.authenticator.TwitterAuthenticationException;
import org.interview.oauth.twitter.config.TempFileConfig;
import org.interview.oauth.twitter.model.Tweet;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TweetsResource {

    private final FilterRealtimeTweetsService filterRealtimeTweetsService;
    private final Logger logger;

    public TweetsResource(FilterRealtimeTweetsService filterRealtimeTweetsService, Logger logger) {
        this.filterRealtimeTweetsService = filterRealtimeTweetsService;
        this.logger = logger;
    }

    @GetMapping("/tweets")
    public List<Tweet> filterRealtimeTweets() throws TwitterAuthenticationException, IOException {
        List<Tweet> tweets = filterRealtimeTweetsService.getTweets("bieber");
        writeJsonToTempFile(tweets);
        return tweets;
    }

    private void writeJsonToTempFile(final List<Tweet> tweets) throws IOException {
        File file = File.createTempFile(TempFileConfig.TEMP_FILE_PREFIX, TempFileConfig.TEMP_FILE_SUFFIX);
        logger.info(file.getAbsolutePath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            tweets.stream().sorted(this::compare).map(this::prettyJson).forEach(tweet -> {
                try {
                    writer.write(tweet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private int compare(Tweet tweet1, Tweet tweet2) {
        return !tweet1.getUser().getName().equalsIgnoreCase(tweet2.getUser().getName()) ?
                tweet1.getUser().getName().toLowerCase().compareTo(tweet2.getUser().getName().toLowerCase()) :
                tweet1.getCreatedAt().compareTo(tweet2.getCreatedAt());
    }

    private String prettyJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
