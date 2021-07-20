package com.truelayer.pokedex.translate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truelayer.pokedex.translate.model.Contents;
import com.truelayer.pokedex.translate.model.Success;
import com.truelayer.pokedex.translate.model.Translation;
import com.truelayer.pokedex.translate.model.TranslationResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
class TranslationClientTest {

    @Autowired
    private TranslateProps props;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;
    private ObjectMapper mapper;

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
        restTemplate = restTemplateBuilder.build();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void shouldCallYodaTranslationRestApi() throws URISyntaxException, JsonProcessingException {
        final String translated = "No. Try not. Do or don't, there is no try";
        final String text = "No, do not try. There is no try, do or don't";
        final String translation = "yoda";
        final TranslationResponse translationResponse = TranslationResponse.builder()
                .contents(Contents.builder().translated(translated).translation(translation).text(text).build())
                .success(Success.builder().total(1).build()).build();
        final String uri = String.format(props.getUrl(), translation);
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(translationResponse)));

        final TranslationClient translationClient = new TranslationClientImpl(
                props, restTemplate, "yoda"
        );

        assertThat(translationClient.translate(text)).isEqualTo(
                Translation.builder().translation(translation).translated(translated).text(text).build()
        );
        mockServer.verify();
    }

    @Test
    public void shouldCallShakespeareTranslationRestApi() throws URISyntaxException, JsonProcessingException {
        final String translated = "To be or not to be, that is the question";
        final String text = "The question is, to be or not to be";
        final String translation = "shakepeare";
        final TranslationResponse translationResponse = TranslationResponse.builder()
                .contents(Contents.builder().translated(translated).translation(translation).text(text).build())
                .success(Success.builder().total(1).build()).build();
        mockServer.expect(ExpectedCount.once(), requestTo(
                new URI("https://api.funtranslations.com/translate/shakespeare.json")))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(translationResponse)));

        final TranslationClient translationClient = new TranslationClientImpl(
                props, restTemplate, "shakespeare"
        );

        assertThat(
                translationClient.translate(text)).isEqualTo(
                Translation.builder().translation(translation).translated(translated).text(text).build()
        );
        mockServer.verify();
    }
}