package com.marcin.pokemon_api.domain.combat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marcin.pokemon_api.domain.Types;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypesOfAttack {

    @JsonProperty("double_damage_to")
    private Types[] double_damage_to;

    @JsonProperty("half_damage_to")
    private Types[] half_damage_to;

    @JsonProperty("no_damage_to")
    private Types[] no_damage_to;

}