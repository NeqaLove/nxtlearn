package it.nextre.academy.nxtlearn.myutils;

import java.util.Scanner;

public class MyInput {
    public static String leggiRiga() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static int leggiInteroPositivo() {
        Scanner s = new Scanner(System.in);
        String tmp = s.nextLine().trim();
        String numeri = "0123456789";
        for (int i = 0; i < tmp.length(); i++) {
            if (numeri.indexOf(tmp.charAt(i)) < 0) {
                if (i == 0 && (tmp.charAt(i) == '+')) {
                } else {
                    //trovato carattere non valido
                    System.out.println("Input non valido, riprova");
                    return leggiIntero();
                }
            }
        }
        //se arrivo qui tmp è una sequenza di numeri positiva
        return Integer.parseInt(tmp);
    }

    public static int leggiIntero() {
        Scanner s = new Scanner(System.in);
        String tmp = s.nextLine().trim();
        String numeri = "0123456789";
        for (int i = 0; i < tmp.length(); i++) {
            if (numeri.indexOf(tmp.charAt(i)) < 0) {
                if (i == 0 && (tmp.charAt(i) == '+' || tmp.charAt(i) == '-')) {
                } else {
                    //trovato carattere non valido
                    System.out.println("Input non valido, riprova");
                    return leggiIntero();
                }
            }
        }
        //se arrivo qui tmp è una sequenza di numeri positiva
        return Integer.parseInt(tmp);
    }

    public static int leggiIntero2() {
        //System.out.println("Inserisci un numero intero");
        Scanner s = new Scanner(System.in);
        String tmp = s.nextLine().trim();
        try {
            return Integer.parseInt(tmp);
        } catch (NumberFormatException ex) {
            System.out.println("Valore non valido, riprova");
            return leggiIntero2();
        }
    }
}//end class