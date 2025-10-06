/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pl1_procesadores;
import java.util.*;
/**
 *
 * @author admou
 */
public class PL1_Procesadores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ========== DEFINICIÓN DE LOS DFA ==========

        // (1) IDENTIFICADOR: [a-zA-Z][a-zA-Z0-9]*
        int[][] IDENTIFIER_DFA = {
            {1, -1},  // estado 0
            {1, 1}    // estado 1
        };
        Set<Integer> IDENTIFIER_FINALS = Set.of(1);
        Map<Character,Integer> sigmaId = new HashMap<>();
        for (char c='a'; c<='z'; c++) sigmaId.put(c,0);
        for (char c='A'; c<='Z'; c++) sigmaId.put(c,0);
        for (char c='0'; c<='9'; c++) sigmaId.put(c,1);
        AFD identifier = new AFD(IDENTIFIER_DFA, IDENTIFIER_FINALS, sigmaId);

        // (2) PARIDAD DE 'a'
        int[][] EVEN_A_DFA = {
            {1, 0},  // estado 0 (par)
            {0, 1}   // estado 1 (impar)
        };
        Set<Integer> EVEN_A_FINALS = Set.of(0);
        Map<Character,Integer> sigmaEvenA = Map.of('a',0,'b',1);
        AFD evenA = new AFD(EVEN_A_DFA, EVEN_A_FINALS, sigmaEvenA);

        // (3) FLOAT aún no implementado
        int[][] FLOAT_DFA = {
            // dígito, '.', otro
            {1, -1, -1},  // 0 inicio
            {1, 2, -1},   // 1 parte entera
            {3, -1, -1},  // 2 punto decimal, espera dígito
            {3, -1, -1}   // 3 parte decimal
        };
        Set<Integer> FLOAT_FINALS = Set.of(1, 3);
        Map<Character,Integer> sigmaFloat = new HashMap<>();
        for (char c='0'; c<='9'; c++) sigmaFloat.put(c,0);
        sigmaFloat.put('.',1);
        // Todo lo demás se considerará inválido (-1)
        AFD floatDFA = new AFD(FLOAT_DFA, FLOAT_FINALS, sigmaFloat);

        // (4) EXPRESIONES ARITMÉTICAS: identificadores, floats, +, -
        int[][] EXPR_DFA = {
            //   a/A-z/Z,  0-9,  '.',  '+',  '-',  ' '
            {1, 2, -1, -1, -1, 0},  // 0 inicio
            {1, 1, -1, 5, 5, 4},    // 1 identificador
            {-1, 2, 3, 5, 5, 4},    // 2 entero
            {-1, 3, -1, 5, 5, 4},   // 3 decimal
            {-1, -1, -1, 5, 5, 4},  // 4 post-operando
            {1, 2, -1, -1, -1, 5}   // 5 operador (espera operando)
        };
        Set<Integer> EXPR_FINALS = Set.of(1, 2, 3, 4);
        Map<Character,Integer> sigmaExpr = new HashMap<>();
        for (char c='a'; c<='z'; c++) sigmaExpr.put(c,0);
        for (char c='A'; c<='Z'; c++) sigmaExpr.put(c,0);
        for (char c='0'; c<='9'; c++) sigmaExpr.put(c,1);
        sigmaExpr.put('.', 2);
        sigmaExpr.put('+', 3);
        sigmaExpr.put('-', 4);
        sigmaExpr.put(' ', 5);
        AFD exprDFA = new AFD(EXPR_DFA, EXPR_FINALS, sigmaExpr);

        // ========== INTERACCIÓN CON EL USUARIO ==========
        while (true) {
            try {
                System.out.println("Seleccione caso:");
                System.out.println("1. Identificador");
                System.out.println("2. Cadenas con número par de 'a'");
                System.out.println("3. Número en coma flotante");
                System.out.println("4. Operaciones aritméticas");
                System.out.println("5. Salir de la Simulación");
                System.out.print("Opción: ");

                int choice = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                if (choice == 5) {
                    System.out.println("Saliendo...");
                    break;
                } else if (choice < 1 || choice > 5) {
                    System.out.println("Opción no válida.");
                    continue;
                }

                System.out.print("Introduzca cadena: ");
                String input = sc.nextLine();

                boolean accepted = false;

                switch (choice) {
                    case 1 -> accepted = Simulador.simular(identifier, input);
                    case 2 -> accepted = Simulador.simular(evenA, input);
                    case 3 -> {
                        if (floatDFA != null)
                            accepted = Simulador.simular(floatDFA, input);
                        else
                            System.out.println("DFA para floats aún no implementado.");
                    }
                    case 4 -> accepted = Simulador.simular(exprDFA, input);
                }

                if (choice == 1 || choice == 2 || (choice == 3 && floatDFA != null) || choice == 4) {
                    System.out.println("Resultado: " + (accepted ? "ACEPTADA" : "RECHAZADA"));
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe introducir un número entre 1 y 5.");
                sc.nextLine(); // limpiar entrada inválida
            }
        }

        sc.close();
    }
}

// ================= CLASE AFD =================
class AFD {
    private final int[][] transiciones;
    private final Set<Integer> estadosFinales;
    private final Map<Character,Integer> sigma;

    public AFD(int[][] transiciones, Set<Integer> estadosFinales, Map<Character,Integer> sigma) {
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

// ================= CLASE SIMULADOR =================
class Simulador {
    public static boolean simular(AFD dfa, String input) {
        int estado = 0; // estado inicial
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