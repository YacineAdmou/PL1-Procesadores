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
    private final int[][] transiciones;       // matriz de transiciones
    private final Set<Integer> estadosFinales;  // estados de aceptación
    private final Map<Character,Integer> sigma; // alfabeto (caracter → columna)

    public AFD(int[][]transiciones, Set<Integer> estadosFinales, Map<Character,Integer> sigma) {
        this.transiciones = transiciones;
        this.estadosFinales = estadosFinales;
        this.sigma = sigma;
    }

    public int[][] getTransiciones() {
        return transiciones;
    }

    public Set<Integer> getFinalStates() {
        return estadosFinales;
    }

    public Map<Character,Integer> getSigma() {
        return sigma;
    }
}
