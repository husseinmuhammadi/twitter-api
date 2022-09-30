package org.interview.oauth.twitter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.interview.oauth.twitter.model.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MappingConfiguration.class)
class MappingConfigurationTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldParseTwitterMessage_whenGetDataFromTwitter() throws IOException {
        Tweet tweet;
        try(InputStream in = this.getClass().getClassLoader().getResourceAsStream("mock-data/tweet.json")){
            tweet = objectMapper.readValue(in, Tweet.class);
        }
        Assertions.assertThat(tweet.getId()).isEqualTo(1552607592437977088L);
        Assertions.assertThat(tweet.getCreatedAt()).hasYear(2022).hasMonth(7).hasDayOfMonth(28);
        Assertions.assertThat(tweet.getUser().getName()).contains("Natacha");
        Assertions.assertThat(tweet.getUser().getScreenName()).contains("NatachaaDrew");
    }
}