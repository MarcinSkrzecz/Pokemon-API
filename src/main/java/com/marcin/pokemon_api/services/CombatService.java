package com.marcin.pokemon_api.services;

import com.marcin.pokemon_api.client.PokemonClient;
import com.marcin.pokemon_api.domain.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Service
public class CombatService {

    @Autowired
    private PokemonClient pokemonClient;

    private boolean checkIfContains(Types[] types, String defendingPokemon) {

        List<Types> data = Arrays.asList(types);

        boolean check = false;

        ListIterator<Types> iterator = data.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getType().equals(defendingPokemon)) {
                check = true;
            }
        }
        return check;
    }

    public double checkAttackRelation(String attackingPokemon, String defendingPokemon) {
        double attackRelation = 1.0;

        Types[] doubleDamageTo = pokemonClient.getCombatResponse(attackingPokemon).getDamageRelations().getDouble_damage_to();
        boolean DDT = checkIfContains(doubleDamageTo, defendingPokemon);

        Types[] halfDamageTo = pokemonClient.getCombatResponse(attackingPokemon).getDamageRelations().getHalf_damage_to();
        boolean HDT = checkIfContains(halfDamageTo, defendingPokemon);

        Types[] noDamageTo = pokemonClient.getCombatResponse(attackingPokemon).getDamageRelations().getNo_damage_to();
        boolean NDT = checkIfContains(noDamageTo, defendingPokemon);

        if (DDT) {
            attackRelation = 2.0;
        } else if (HDT) {
            attackRelation = 0.5;
        } else if (NDT) {
            attackRelation = 0.0;
        }

        return attackRelation;
    }

    public boolean checkIfDoubleType(String pokemonType) {
        boolean containsSpace = pokemonType.contains(" ");

        if (containsSpace) {
            return true;
        } else {
            return false;
        }

    }

    public String combatResult (String attackingPokemon, String defendingPokemon) {
        boolean attackDoubleType = checkIfDoubleType(attackingPokemon);
        boolean defenceDoubleType = checkIfDoubleType(defendingPokemon);

        int caseNumber = 0;
        if (!attackDoubleType && !defenceDoubleType) {
            caseNumber = 1;
        } else if (!attackDoubleType && defenceDoubleType) {
            caseNumber = 2;
        } else if (attackDoubleType && !defenceDoubleType) {
            caseNumber = 3;
        } else if (attackDoubleType && defenceDoubleType) {
            caseNumber = 4;
        }

        double attack = 0;

        switch (caseNumber) {
            case 1:
                attack = checkAttackRelation(attackingPokemon, defendingPokemon);
                break;
            case 2:
                String[] splitDef_2 = defendingPokemon.split(" ");
                attack = checkAttackRelation(attackingPokemon, splitDef_2[0]) * checkAttackRelation(attackingPokemon, splitDef_2[1]);
                break;
            case 3:
                String[] splitATK_3 = attackingPokemon.split(" ");
                attack = checkAttackRelation(splitATK_3[0], defendingPokemon) * checkAttackRelation(splitATK_3[1], defendingPokemon);
                break;
            case 4:
                String[] splitATK_4 = attackingPokemon.split(" ");
                String[] splitDef_4 = defendingPokemon.split(" ");
                attack = checkAttackRelation(splitATK_4[0], splitDef_4[0]) * checkAttackRelation(splitATK_4[0], splitDef_4[1]) *
                        checkAttackRelation(splitATK_4[1], splitDef_4[0]) * checkAttackRelation(splitATK_4[1], splitDef_4[1]);
                break;
        }

        return "Final attack changed by " + attack + "* !!!";
    }

}
