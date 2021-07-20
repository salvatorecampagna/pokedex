package com.truelayer.pokedex.details;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truelayer.pokedex.TestUtils;
import com.truelayer.pokedex.details.model.PokemonDetails;
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
class PokemonDetailsClientTest {
    @Autowired
    private PokemonDetailsProps props;

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
    public void shouldCallPokemonSpeciesApi() throws URISyntaxException, JsonProcessingException {
        final String pokemonName = "pikachu";
        final PokemonDetails pokemonDetails = TestUtils.buildPokemonDetails(
                1,
                "Pikachu",
                "A popular pokemon",
                "The forest",
                false
        );
        final String uri = String.format(props.getUrl(), pokemonName);
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(pokemonDetails)));

        final PokemonDetailsClient pokemonDetailsClient = new PokemonDetailsClientImpl(restTemplate, props);

        assertThat(pokemonDetailsClient.getByIdOrName(pokemonName))
                .isEqualTo(pokemonDetails);
        mockServer.verify();
    }
}