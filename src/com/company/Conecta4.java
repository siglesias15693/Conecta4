package com.company;

import java.util.Scanner;

public class Conecta4 {

    protected static Cell[][] tablero;
    protected static int Columnas;
    protected static int Filas;
    protected static int turno;
    protected static boolean running = true;
    private static boolean[] auto;

    protected static String[] jugador;
    protected static char[] simbol;

    protected static String mensajeError= "";

    public static void verificadorGanador(String jugador, char caracter) {
        String mssg="";
        //verificacion (horizontal)
        for (int i = 1; i < Filas; i++) {
            for (int j = 0; j < Columnas - 3; j++) {
                if (tablero[j][i].getChar()==caracter &&
                        tablero[j+1][i].getChar()==caracter &&
                        tablero[j+2][i].getChar()==caracter &&
                        tablero[j+3][i].getChar()==caracter
                ) {
                    running = false;
                    mssg=("HA GANADO: " + jugador);
                }
            }
        }

        //verificacion (vertical)
        for (int i = 0; i < Filas- 3 ; i++) {
            for (int j = 0; j <  Columnas; j++) {
                if (tablero[j][i].getChar()==caracter &&
                        tablero[j][i+1].getChar()==caracter &&
                        tablero[j][i+2].getChar()==caracter &&
                        tablero[j][i+3].getChar()==caracter
                ) {
                    running = false;
                    mssg=("HA GANADO: " + jugador);
                }
            }
        }

        //verificacion (diagonal)
        for (int i = 0; i < Filas - 3; i++) {
            for (int j = 0; j < Columnas - 3; j++) {
                if (tablero[j][i].getChar()==caracter &&
                        tablero[j + 1][i + 1].getChar()==caracter &&
                        tablero[j + 2][i + 2].getChar()==caracter &&
                        tablero[j + 3][i + 3].getChar()==caracter
                ) {
                    running = false;
                    mssg=("HA GANADO: " + jugador);
                }
            }
        }
        for (int i = 0; i < Filas - 3; i++) {
            for (int j = Columnas-1;j>=3 ; j--) {
                if (tablero[j][i].getChar()==caracter &&
                        tablero[j-1][i+1].getChar()==caracter &&
                        tablero[j-2][i+2].getChar()==caracter &&
                        tablero[j-3][i+3].getChar()==caracter
                ) {
                    running = false;
                    mssg=("HA GANADO: " + jugador);
                }
            }
        }
        System.out.println(mssg);
        verificadorEmpate();
    }

    public static void verificadorEmpate(){
        if (turno >= (Filas * Columnas)) {
            running = false;
            Dibujo.mostrarTablero();
            System.out.println("======================");
            System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m"+turno+"/"+(Filas * Columnas));
            System.out.println("El tablero esta lleno");
            System.out.println("======================");
        }
    }

    public static void juego(){
        while (running){

            if (auto[turno%2]){
                player.Bot(jugador[turno%2],simbol[turno%2]);
            }else{
                player.Player(jugador[turno%2],simbol[turno%2]);
            }

        }
        Dibujo.mostrarTablero();
    }

    public static void main(String[] args) {
        //Inicializar

        jugador = new String[]{"\033[31mJugador1\u001B[0m", "\033[34mJugador2\u001B[0m"};
        simbol= new char[]{'X','O'};
        auto = new boolean[]{false,true};

        Columnas = 7;
        Filas = 7;
        tablero= new Cell[Columnas][Filas];
        turno = 0;

        //Se asigna un caracter por defecto al tablero
        for (int i = 0; i <= Columnas - 1; i++) {
            for (int j = 0; j <= Filas - 1; j++) {
                tablero[i][j] = new Cell();
            }
        }

        //Comenzamos el juego
        juego();
    }

}
