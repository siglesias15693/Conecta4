package com.company;

import java.util.Scanner;

public class Conecta4 {

    public static String[][] tablero = new String[9][7];
    public static int Columnas;
    public static int Filas;
    public static int jugadasMaximas;
    public static boolean finJuego = false;

    public static void mostrarTablero(String[][] tabla) {
        System.out.print("         ");
        for (int i = 0; i < Columnas; i++) {
            System.out.print("___ ");
        }System.out.println("");

        for (int i = 0; i < Filas; i++) {
            System.out.print("        | ");
            for (int j = 0; j < Columnas; j++) {
                System.out.print(tabla[j][i]+" | ");
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
    }

    public static void mostrarInterfazTablero(String jugador1, String caracter1, String jugador2, String caracter2, int jugadasMaximas) {
        System.out.println("=================================================================");
        System.out.println(jugador1 + ": " + caracter1 + "      " + jugador2 + ": " + caracter2);
        System.out.print("==============================================||| JUGADA N°" + jugadasMaximas + " |||");
    }

    public static void verificadorGanador(String auxiliarJugador, String auxilarCaracter) {
        //verificacion ganador (horizontal)
        for (int i = 1; i < Filas; i++) {
            for (int j = 0; j < Columnas - 3; j++) {
                if (tablero[j][i].equals(auxilarCaracter) &&
                        tablero[j+1][i].equals(auxilarCaracter) &&
                        tablero[j+2][i].equals(auxilarCaracter) &&
                        tablero[j+3][i].equals(auxilarCaracter)
                ) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador);
                }
            }
        }

        //verificacion ganador (vertical)
        for (int i = 0; i < Filas- 3 ; i++) {
            for (int j = 0; j <  Columnas; j++) {
                if (tablero[j][i].equals(auxilarCaracter) &&
                        tablero[j][i+1].equals(auxilarCaracter) &&
                        tablero[j][i+2].equals(auxilarCaracter) &&
                        tablero[j][i+3].equals(auxilarCaracter)
                ) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador);
                }
            }
        }

        //verificacion ganador (diagonal)
        for (int i = 0; i < Filas - 3; i++) {
            for (int j = 0; j < Columnas - 3; j++) {
                if (tablero[j][i].equals(auxilarCaracter) && tablero[j + 1][i + 1].equals(auxilarCaracter) && tablero[j + 2][i + 2].equals(auxilarCaracter) && tablero[j + 3][i + 3].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador);
                }
            }
        }
        for (int i = 0; i < Filas - 3; i++) {
            for (int j = Columnas-1;j>=3 ; j--) {
                if (tablero[j][i].equals(auxilarCaracter) &&
                        tablero[j-1][i+1].equals(auxilarCaracter) &&
                        tablero[j-2][i+2].equals(auxilarCaracter) &&
                        tablero[j-3][i+3].equals(auxilarCaracter)
                ) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int columna;
        int contador;
        String mensajeError = "";
        String jugador1;
        String caracter1;
        String jugador2;
        String caracter2;
        boolean verificador = false;
        boolean verificador2 = false;
        Columnas = tablero.length;
        Filas = tablero[0].length;
        jugadasMaximas = 0;

        //Se asigna un caracter por defecto al tablero
        for (int i = 0; i <= Columnas - 1; i++) {
            for (int j = 0; j <= Filas - 1; j++) {
                tablero[i][j] = " ";
            }
        }


        //aplico color azul al nombre y caracter del jugador 1
        jugador1 = "\033[32m" + "Jugador" + "\u001B[0m";
        caracter1 = "\033[31m" + "O" + "\u001B[0m";

        jugador2 = "\033[33mComputadorNoob\u001B[0m";
        caracter2 = "\033[33mX\u001B[0m";
        while (!finJuego) {

            //turno jugador
            while (!verificador) {
                //llamo a la funcion "mostrarTablero" y "mostrarInterfazTablero"
                mostrarTablero(tablero);
                mostrarInterfazTablero(jugador1, caracter1, jugador2, caracter2, jugadasMaximas);
                System.out.println(mensajeError);
                mensajeError = "";
                try {
                    System.out.println(jugador1 + " escriba el numero de columna para poner su ficha:");
                    String auxiliar = lector.nextLine();
                    columna = Integer.parseInt(auxiliar);
                    if (columna > Columnas || columna < 1) {
                        mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + Columnas;
                    } else {
                        contador = 0;
                        columna-=1;
                        for (int i = Filas - 1; i >=0; i--) {
                            if (!verificador2) {
                                //Si se cumple esta condicion, termina el turno del jugador 1
                                if (tablero[columna][i] != caracter1 && tablero[columna][i] != caracter2) {
                                    tablero[columna][i] = caracter1;
                                    verificador2 = true;
                                    verificador = true;
                                    jugadasMaximas++;
                                } else {
                                    contador += 1;
                                }
                            }
                            if (contador == Filas) {
                                mensajeError = "\n\033[35m**ERROR:\u001B[0m Esta columna esta completa, escoja otra";
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    mensajeError = "\n\033[35m**ERROR:\u001B[0m Ingreso un caracter no valido, debe ingresar un NUMERO";
                } catch (Exception e) {
                    mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + Columnas;
                }
            }
            verificador2 = false;
            verificador = false;
            //llamo a la funcion "verificarGanador" para controlar si gano el jugador 1
            verificadorGanador(jugador1, caracter1);

            if (jugadasMaximas == (Filas * Columnas) + 1) {
                finJuego = true;
                mostrarTablero(tablero);
                System.out.println("======================");
                System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
                System.out.println("El tablero esta lleno");
                System.out.println("======================");
            }

            //Si el jugador 1 no gano en su turno continua con el ordenador
            if (!finJuego) {

                //turno ordenador
                while (!verificador) {
                    columna = (int) (Math.random() * Columnas);
                    for (int i = Filas - 1; i > -1; i--) {
                        if (!verificador2) {
                            //Si se cumple esta condicion, termina el turno del jugador 2
                            if (tablero[columna][i] != caracter2 && tablero[columna][i] != caracter1) {
                                tablero[columna][i] = caracter2;
                                verificador2 = true;
                                verificador = true;
                                jugadasMaximas++;
                            }
                        }
                    }
                }
                verificador2 = false;
                verificador = false;
                //llamo a la funcion "verificarGanador" para controlar si gano el jugador 2
                verificadorGanador(jugador2, caracter2);

                empate();
            }
        }
    }

    public static void empate(){
        if (jugadasMaximas == (Filas * Columnas) + 1) {
            finJuego = true;
            mostrarTablero(tablero);
            System.out.println("======================");
            System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
            System.out.println("El tablero esta lleno");
            System.out.println("======================");
        }
    }
}
