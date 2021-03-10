package com.marcin.pokemon_api.controller;

import com.marcin.pokemon_api.services.CombatService;
import com.marcin.pokemon_api.services.GetAllTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TestController {

    @Autowired
    private GetAllTypesService types;

    @Autowired
    private CombatService combat;

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<String> getMainResponse() {
        return types.getResponse();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/combat")
    public String attack(@RequestParam String attackingPokemon, @RequestParam String defendingPokemon) {
        return combat.combatResult(attackingPokemon, defendingPokemon);
    }

}
