package org.interview.oauth.twitter.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.interview.oauth.twitter.authenticator.TwitterAuthenticationException;
import org.interview.oauth.twitter.model.Tweet;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@IntegrationTest
class FilterRealtimeTweetsServiceImplIntegrationTest {

    private final String pinGeneratedByTwitter = "1234567";

    @Autowired
    FilterRealtimeTweetsService filterRealtimeTweetsService;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    AuthenticatorPinRetrieval pinRetrieval;

    @Test
    void shouldReadTweetsWithin3Second() throws TwitterAuthenticationException {
        assertNotNull(filterRealtimeTweetsService);
        Mockito.when(pinRetrieval.retrievePin(ArgumentMatchers.any())).thenReturn(pinGeneratedByTwitter);
        List<Tweet> tweets = filterRealtimeTweetsService.getTweets("bieber");
        assertEquals(5, tweets.size());
    }
}