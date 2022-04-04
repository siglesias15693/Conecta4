package com.company;

import java.util.Scanner;

public class Conecta4 {

    private static String[][] tablero;
    private static int Columnas;
    private static int Filas;
    private static int jugadasMaximas;
    private static boolean running = true;
    private static int N_Jugadores;

    private static String jugador1;
    private static String caracter1;

    private static String jugador2;
    private static String caracter2;

    private static boolean verificador;
    private static String mensajeError= "";


    private static final Scanner lector = new Scanner(System.in);


    public static void mostrarTablero() {
        System.out.print("         ");
        for (int i = 0; i < Columnas; i++) {
            System.out.print("___ ");
        }System.out.println();

        for (int i = 0; i < Filas; i++) {
            System.out.print("        | ");
            for (int j = 0; j < Columnas; j++) {
                System.out.print(tablero[j][i]+" | ");
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
            System.out.println(jugador1 + ": " + caracter1 + "      " + jugador2 + ": " + caracter2);
            System.out.print("===============================================||| JUGADA N°" + jugadasMaximas + " |||");
        }
    }

    public static void verificadorGanador(String jugador, String caracter) {
        //verificacion (horizontal)
        for (int i = 1; i < Filas; i++) {
            for (int j = 0; j < Columnas - 3; j++) {
                if (tablero[j][i].equals(caracter) &&
                        tablero[j+1][i].equals(caracter) &&
                        tablero[j+2][i].equals(caracter) &&
                        tablero[j+3][i].equals(caracter)
                ) {
                    running = false;
                    System.out.println("HA GANADO: " + jugador);
                }
            }
        }

        //verificacion (vertical)
        for (int i = 0; i < Filas- 3 ; i++) {
            for (int j = 0; j <  Columnas; j++) {
                if (tablero[j][i].equals(caracter) &&
                        tablero[j][i+1].equals(caracter) &&
                        tablero[j][i+2].equals(caracter) &&
                        tablero[j][i+3].equals(caracter)
                ) {
                    running = false;
                    System.out.println("HA GANADO: " + jugador);
                }
            }
        }

        //verificacion (diagonal)
        for (int i = 0; i < Filas - 3; i++) {
            for (int j = 0; j < Columnas - 3; j++) {
                if (tablero[j][i].equals(caracter) &&
                        tablero[j + 1][i + 1].equals(caracter) &&
                        tablero[j + 2][i + 2].equals(caracter) &&
                        tablero[j + 3][i + 3].equals(caracter)
                ) {
                    running = false;
                    System.out.println("HA GANADO: " + jugador);
                }
            }
        }
        for (int i = 0; i < Filas - 3; i++) {
            for (int j = Columnas-1;j>=3 ; j--) {
                if (tablero[j][i].equals(caracter) &&
                        tablero[j-1][i+1].equals(caracter) &&
                        tablero[j-2][i+2].equals(caracter) &&
                        tablero[j-3][i+3].equals(caracter)
                ) {
                    running = false;
                    System.out.println("HA GANADO: " + jugador);
                }
            }
        }

        verificadorEmpate();
    }

    public static void verificadorEmpate(){
        if (jugadasMaximas >= (Filas * Columnas)) {
            running = false;
            mostrarTablero();
            System.out.println("======================");
            System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
            System.out.println("El tablero esta lleno");
            System.out.println("======================");
        }
    }

    public static void Player(String jugador,String caracter){
        int columna;

        //llamo a la funcion "mostrarTablero"
        mostrarTablero();

        //turno jugador
        while (!verificador) {
            int contador=0;
            System.out.println(mensajeError);
            mensajeError = "";

            System.out.println(jugador + " escriba el numero de columna para poner su ficha:");
            columna = lector.nextInt();
            if (columna > Columnas || columna < 1) {
                mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre 1 y " + Columnas;
            } else {
                columna--;
                for (int i = Filas - 1; i >=0; i--) {
                    if (!verificador) {
                        //Si se cumple esta condicion, termina el turno del jugador 1
                        if (!tablero[columna][i].equals(caracter1) && !tablero[columna][i].equals(caracter2)) {
                            tablero[columna][i] = caracter;
                            verificador = true;
                            jugadasMaximas++;
                        } else {
                            contador++;
                            if (contador>=Filas) {
                                mensajeError = "\n\033[35m**ERROR:\u001B[0m Esta columna esta completa, escoja otra";
                            }
                        }
                    }
                }
            }

        }
        verificador = false;
        //llamo a la funcion "verificarGanador"
        verificadorGanador(jugador, caracter);
    }

    public static void Bot(String bot, String caracter){

        //turno del Bot
        while (!verificador) {
            //Columna random
            int columna = (int) (Math.random() * Columnas);
            for (int i = Filas - 1; i > -1; i--) {
                if (!verificador) {
                    //Verficar csilla libre
                    if (!tablero[columna][i].equals(caracter2) && !tablero[columna][i].equals(caracter1)) {
                        tablero[columna][i] = caracter;
                        verificador = true;
                        jugadasMaximas++;
                    }
                }
            }
        }
        verificador = false;
        //llamo a la funcion "verificarGanador"
        verificadorGanador(bot, caracter);

    }

    public static void juego(){
        while (running){
            //Turnos de los jugadores

            if (N_Jugadores==0){
                if (running){Bot(jugador1,caracter1);}
                if (running){Bot(jugador2,caracter2);}
            }
            if (N_Jugadores==1){
                if (running){Player(jugador1,caracter1);}
                if (running){Bot(jugador2,caracter2);}
            }
            if (N_Jugadores==2){
                if (running){Player(jugador1,caracter1);}
                if (running){Player(jugador2,caracter2);}
            }
        }
        mostrarTablero();
    }

    public static void main(String[] args) {
        //Inicializar
        N_Jugadores=1;

        jugador1 = "\033[31m" + "Jugador1" + "\u001B[0m";
        caracter1 = "\033[31m" + "O" + "\u001B[0m";

        jugador2 = "\033[34m"+"Jugador2"+"\u001B[0m";
        caracter2 = "\033[34mX\u001B[0m";

        verificador = false;

        tablero= new String[7][7];
        Columnas = tablero.length;
        Filas = tablero[0].length;
        jugadasMaximas = 0;

        //Se asigna un caracter por defecto al tablero
        for (int i = 0; i <= Columnas - 1; i++) {
            for (int j = 0; j <= Filas - 1; j++) {
                tablero[i][j] = " ";
            }
        }

        //Comenzamos el juego
        juego();
    }

}
