package org.interview.oauth.twitter.service;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.interview.oauth.twitter.authenticator.TwitterAuthenticator;
import org.interview.oauth.twitter.config.MappingConfiguration;
import org.interview.oauth.twitter.config.TwitterEndpoints;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FilterRealtimeTweetsServiceImpl.class,
        TwitterEndpoints.class, MappingConfiguration.class})
class FilterRealtimeTweetsServiceImplTest {

    @Autowired
    FilterRealtimeTweetsService filterRealtimeTweetsService;

    @MockBean
    Logger logger;

    @MockBean
    AuthenticatorPinRetrieval pinRetrieval;

    @MockBean
    TwitterAuthenticator authenticator;

    @Test
    void shouldAddParametersToURI_whenGenerateUriWithQueryParameters() throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder("https://stream.twitter.com/1.1/statuses/filter.json");
        List<NameValuePair> queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair("track", "bieber"));
        uriBuilder.addParameters(queryParameters);
        assertEquals("https://stream.twitter.com/1.1/statuses/filter.json?track=bieber", uriBuilder.build().toString());
    }
}