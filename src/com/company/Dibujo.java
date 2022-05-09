package com.company;


import static com.company.Conecta4.*;

public class Dibujo {

    public static void mostrarTablero() {
        System.out.print("         ");
        for (int i = 0; i < Columnas; i++) {
            System.out.print("___ ");
        }System.out.println();

        for (int i = 0; i < Filas; i++) {
            System.out.print("        |");
            for (int j = 0; j < Columnas; j++) {
                if (tablero[j][i].getOcp()){
                    if (tablero[j][i].getChar()==simbol[0]){

                        System.out.print("\033[41m "+tablero[j][i].getChar()+" \u001B[0m");

                    }else if (tablero[j][i].getChar()==simbol[1]){

                        System.out.print("\033[44m "+tablero[j][i].getChar()+" \u001B[0m");

                    }
                }else { System.out.print("   ");}

                System.out.print("|");
            }
            System.out.println();
            if (i < Filas -1) {
                System.out.print("        |");
                for (int p = 0; p < Columnas ; p++) {
                    if (p==Columnas-1){
                        System.out.print("-––");
                    }else{
                        System.out.print("-–– ");
                    }
                }
                System.out.println("|");
            }
        }
        System.out.print("         ");
        for (int i = 1; i < Columnas + 1; i++) {
            System.out.print("¯¯¯ ");
        }
        System.out.println();

        System.out.print("          ");
        for (int i = 1; i < Columnas + 1; i++) {
            System.out.print(i + "   ");
        }System.out.println();

        if (running) {
            System.out.println("=================================================================");
            System.out.println(jugador[0] + ": " + simbol[0] + "      " + jugador[1] + ": " + simbol[1]);
            System.out.println("===============================================||| TURNO N°" + turno + " |||");
        }
    }

}
