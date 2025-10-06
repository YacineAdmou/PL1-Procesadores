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

/**
 * Clase que representa un Autómata Finito Determinista (AFD)
 * Un AFD está definido por:
 *  - Una matriz de transiciones
 *  - Un conjunto de estados finales (de aceptación)
 *  - Un alfabeto que mapea cada símbolo a la columna correspondiente en la matriz
 */
public class AFD {
    // Matriz de transiciones: filas = estados, columnas = símbolos
    // El valor en transiciones[estado][simbolo] indica a qué estado se transita
    private final int[][] transiciones;

    // Conjunto de estados de aceptación
    private final Set<Integer> estadosFinales;

    // Mapa que convierte un carácter del alfabeto a un índice de columna en la matriz
    private final Map<Character, Integer> sigma;

    /**
     * Constructor de la clase AFD
     * @param transiciones matriz de transición del autómata
     * @param estadosFinales conjunto de estados de aceptación
     * @param sigma mapa del alfabeto que asigna un índice a cada símbolo
     */
    public AFD(int[][] transiciones, Set<Integer> estadosFinales, Map<Character,Integer> sigma) {
        this.transiciones = transiciones;   // inicializa la matriz de transiciones
        this.estadosFinales = estadosFinales; // inicializa los estados finales
        this.sigma = sigma;                   // inicializa el mapa del alfabeto
    }

    /**
     * Getter para obtener la matriz de transiciones
     * @return matriz de transiciones
     */
    public int[][] getTransiciones() {
        return transiciones;
    }

    /**
     * Getter para obtener los estados finales (de aceptación)
     * @return conjunto de estados finales
     */
    public Set<Integer> getFinalStates() {
        return estadosFinales;
    }

    /**
     * Getter para obtener el mapa del alfabeto
     * @return mapa sigma
     */
    public Map<Character,Integer> getSigma() {
        return sigma;
    }
}
