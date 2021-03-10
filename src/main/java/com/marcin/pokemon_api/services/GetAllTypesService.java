package com.marcin.pokemon_api.services;

import com.marcin.pokemon_api.client.PokemonClient;
import com.marcin.pokemon_api.domain.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Service
public class GetAllTypesService {

    @Autowired
    private PokemonClient pokemonClient;

    public List<String> getResponse() {

        List<Types> getTypeList = Arrays.asList(pokemonClient.getTypesResponse().getTypes());

        List<String> types = new ArrayList<>();

        ListIterator<Types> iterator = getTypeList.listIterator();
        while (iterator.hasNext()) {
            types.add(iterator.next().getType());
        }

        return types;
    }

}
