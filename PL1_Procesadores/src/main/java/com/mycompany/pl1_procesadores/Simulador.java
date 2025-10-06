/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pl1_procesadores;

/**
 *
 * @author admou
 */
/**
 * Clase Simulador
 * Proporciona un método para simular la ejecución de un AFD sobre una cadena
 */
public class Simulador {

    /**
     * Simula la ejecución de un AFD sobre una cadena de entrada
     * @param dfa el autómata a simular
     * @param input la cadena que queremos evaluar
     * @return true si la cadena es aceptada, false si no
     */
    public static boolean simular(AFD dfa, String input) {
        int estado = 0; // Estado inicial del AFD (siempre se asume 0)

        // Recorremos cada carácter de la cadena
        for (char c : input.toCharArray()) {

            // Si el carácter no pertenece al alfabeto del AFD, la cadena no es válida
            if (!dfa.getSigma().containsKey(c)) {
                return false;
            }

            // Obtenemos la columna de la matriz de transiciones correspondiente al símbolo
            int col = dfa.getSigma().get(c);

            // Actualizamos el estado actual según la matriz de transiciones
            estado = dfa.getTransiciones()[estado][col];

            // Si no hay transición válida (por ejemplo, -1), la cadena no es aceptada
            if (estado == -1) return false;
        }

        // Al final de la cadena, si el estado actual es un estado final, la cadena se acepta
        return dfa.getFinalStates().contains(estado);
    }
}
