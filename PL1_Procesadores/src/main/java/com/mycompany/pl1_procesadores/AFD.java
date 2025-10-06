/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pl1_procesadores;

/**
 *
 * @author admou
 */
import java.util.Map;
import java.util.Set;

public class AFD {
    private final int[][] transitions;       // matriz de transiciones
    private final Set<Integer> finalStates;  // estados de aceptación
    private final Map<Character,Integer> sigma; // alfabeto (caracter → columna)

    public AFD(int[][] transitions, Set<Integer> finalStates, Map<Character,Integer> sigma) {
        this.transitions = transitions;
        this.finalStates = finalStates;
        this.sigma = sigma;
    }

    public int[][] getTransitions() {
        return transitions;
    }

    public Set<Integer> getFinalStates() {
        return finalStates;
    }

    public Map<Character,Integer> getSigma() {
        return sigma;
    }
}
