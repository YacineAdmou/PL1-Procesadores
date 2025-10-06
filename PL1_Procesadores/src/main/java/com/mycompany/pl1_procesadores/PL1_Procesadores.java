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

        int[][] EVEN_A_DFA = {
            {1, 0},  // estado 0 (par)
            {0, 1}   // estado 1 (impar)
        };
        Set<Integer> EVEN_A_FINALS = Set.of(0);
        Map<Character,Integer> sigmaEvenA = Map.of('a',0,'b',1);
        AFD evenA = new AFD(EVEN_A_DFA, EVEN_A_FINALS, sigmaEvenA);

        AFD floatDFA = null;
        AFD exprDFA = null;

        // ========== INTERACCIÓN CON EL USUARIO ==========
        while (true) {
            try {
                System.out.println("Seleccione caso:");
                System.out.println("1. Identificador");
                System.out.println("2. Cadenas con numero par de 'a'");
                System.out.println("3. Numero en coma flotante");
                System.out.println("4. Operaciones aritmeticas");
                System.out.println("5. Salir de la Simulacion");
                System.out.print("Opcion: ");

                int choice = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                if (choice == 5) {
                    System.out.println("Saliendo...");
                    break; // en lugar de System.exit(0)
                }
                else if (choice < 1 || choice > 5) {
                    System.out.println("Opcion no valida.");
                    continue;
                }

                System.out.print("Introduzca cadena: ");
                String input = sc.nextLine();

                boolean accepted = false;

                switch (choice) {
                    case 1 -> accepted = Simulador.simulate(identifier, input);
                    case 2 -> accepted = Simulador.simulate(evenA, input);
                    case 3 -> {
                        if (floatDFA != null)
                            accepted = Simulador.simulate(floatDFA, input);
                        else
                            System.out.println("DFA para floats aún no implementado.");
                    }
                    case 4 -> {
                        if (exprDFA != null)
                            accepted = Simulador.simulate(exprDFA, input);
                        else
                            System.out.println("DFA para expresiones aún no implementado.");
                    }
                }

                if (choice == 1 || choice == 2 || (choice == 3 && floatDFA != null) || (choice == 4 && exprDFA != null)) {
                    System.out.println("Resultado: " + (accepted ? "ACEPTADA" : "RECHAZADA"));
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe introducir un número entre 1 y 5.");
                sc.nextLine(); // limpiar entrada inválida
            }
        }

        sc.close(); // cerrar scanner al salir
    }
}
