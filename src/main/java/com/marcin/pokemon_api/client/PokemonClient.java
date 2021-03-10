package com.marcin.pokemon_api.client;

import com.marcin.pokemon_api.domain.combat.DamageResponse;
import com.marcin.pokemon_api.domain.getTypes.TypesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@Component
public class PokemonClient {

    @Autowired
    private RestTemplate restTemplate;

    public TypesResponse getTypesResponse() {

        URI url = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/type/").build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "cheese");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<TypesResponse> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, TypesResponse.class);

        Optional<TypesResponse> search = Optional.ofNullable(response.getBody());
        return search.orElseGet(TypesResponse::new);
    }

    public DamageResponse getCombatResponse(String pokemonType) {

        URI url = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/type/" + pokemonType).build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "cheese");
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<DamageResponse> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, DamageResponse.class);

        Optional<DamageResponse> search = Optional.ofNullable(response.getBody());
        return search.orElseGet(DamageResponse::new);
    }
}
