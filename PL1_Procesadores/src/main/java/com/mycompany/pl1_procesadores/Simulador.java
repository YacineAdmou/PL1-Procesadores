/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pl1_procesadores;

/**
 *
 * @author admou
 */
public class Simulador {
    public static boolean simular(AFD dfa, String input) {
        int estado = 0; // estado inicial siempre es 0
        for (char c : input.toCharArray()) {
            if (!dfa.getSigma().containsKey(c)) {
                return false; // símbolo no válido
            }
            int col = dfa.getSigma().get(c);
            estado = dfa.getTransiciones()[estado][col];
            if (estado == -1) return false; // transición inválida
        }
        return dfa.getFinalStates().contains(estado);
    }
}
