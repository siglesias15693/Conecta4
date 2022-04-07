package com.company;

import java.util.Scanner;

public class Conecta4 {

    private static String[][] tablero;
    private static int Columnas;
    private static int Filas;
    private static int turno;
    private static boolean running = true;
    private static boolean[] auto;

    private static String[] jugador;
    private static char[] simbol;

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
                if (tablero[j][i].equals(Character.toString(simbol[0]))){
                    System.out.print("\033[31m"+tablero[j][i]+"\u001B[0m");
                }else if (tablero[j][i].equals(Character.toString(simbol[1]))){
                    System.out.print("\033[34m"+tablero[j][i]+"\u001B[0m");
                }else{
                    System.out.print(tablero[j][i]);
                }
                System.out.print(" | ");
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

    public static void verificadorGanador(String jugador, char caracter) {
        String mssg="";
        //verificacion (horizontal)
        for (int i = 1; i < Filas; i++) {
            for (int j = 0; j < Columnas - 3; j++) {
                if (tablero[j][i].equals(Character.toString(caracter)) &&
                        tablero[j+1][i].equals(Character.toString(caracter)) &&
                        tablero[j+2][i].equals(Character.toString(caracter)) &&
                        tablero[j+3][i].equals(Character.toString(caracter))
                ) {
                    running = false;
                    mssg=("HA GANADO: " + jugador);
                }
            }
        }

        //verificacion (vertical)
        for (int i = 0; i < Filas- 3 ; i++) {
            for (int j = 0; j <  Columnas; j++) {
                if (tablero[j][i].equals(Character.toString(caracter)) &&
                        tablero[j][i+1].equals(Character.toString(caracter)) &&
                        tablero[j][i+2].equals(Character.toString(caracter)) &&
                        tablero[j][i+3].equals(Character.toString(caracter))
                ) {
                    running = false;
                    mssg=("HA GANADO: " + jugador);
                }
            }
        }

        //verificacion (diagonal)
        for (int i = 0; i < Filas - 3; i++) {
            for (int j = 0; j < Columnas - 3; j++) {
                if (tablero[j][i].equals(Character.toString(caracter)) &&
                        tablero[j + 1][i + 1].equals(Character.toString(caracter)) &&
                        tablero[j + 2][i + 2].equals(Character.toString(caracter)) &&
                        tablero[j + 3][i + 3].equals(Character.toString(caracter))
                ) {
                    running = false;
                    mssg=("HA GANADO: " + jugador);
                }
            }
        }
        for (int i = 0; i < Filas - 3; i++) {
            for (int j = Columnas-1;j>=3 ; j--) {
                if (tablero[j][i].equals(Character.toString(caracter)) &&
                        tablero[j-1][i+1].equals(Character.toString(caracter)) &&
                        tablero[j-2][i+2].equals(Character.toString(caracter)) &&
                        tablero[j-3][i+3].equals(Character.toString(caracter))
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
            mostrarTablero();
            System.out.println("======================");
            System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m"+turno+"/"+(Filas * Columnas));
            System.out.println("El tablero esta lleno");
            System.out.println("======================");
        }
    }

    public static boolean colocarFicha(int columna,char caracter) {
        int contador = 0;

        if (columna > Columnas || columna < 1) {
            mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre 1 y " + Columnas;
            return false;
        } else {
            columna--;
            for (int i = Filas - 1; i >= 0; i--) {

                //Si se cumple esta condicion, termina el turno del jugador 1
                if (!tablero[columna][i].equals(Character.toString(simbol[0])) && !tablero[columna][i].equals(Character.toString(simbol[1]))) {
                    tablero[columna][i] = Character.toString(caracter);
                    turno++;
                    return true;
                } else {
                    contador++;
                    if (contador >= Filas) {
                        mensajeError = "\n\033[35m**ERROR:\u001B[0m Esta columna esta completa, escoja otra";
                        return false;
                    }
                }

            }
        }
        return false;
    }

    public static void Player(String jugador, char caracter){
        boolean verificador=true;

        //turno jugador
        while (verificador) {

            //llamo a la funcion "mostrarTablero"
            mostrarTablero();
            System.out.println(mensajeError);
            mensajeError = "";

            //introducir valor
            System.out.println(jugador + " escriba el numero de columna para poner su ficha:");
            int columna = lector.nextInt();

            verificador=!colocarFicha(columna,caracter);

        }

        //llamo a la funcion "verificarGanador"
        verificadorGanador(jugador, caracter);
    }

    public static void Bot(String jugador, char caracter){
        boolean verificador=true;

        //turno jugador
        while (verificador) {

            //Columna random
            int columna = (int) (Math.random() * Columnas);
            verificador=!colocarFicha(columna,caracter);

        }

        //llamo a la funcion "verificarGanador"
        verificadorGanador(jugador, caracter);
    }

    public static void juego(){
        while (running){

            if (auto[turno%2]){
                Player(jugador[turno%2],simbol[turno%2]);
            }else{
                Bot(jugador[turno%2],simbol[turno%2]);
            }

        }
        mostrarTablero();
    }

    public static void main(String[] args) {
        //Inicializar

        jugador = new String[]{"\033[31mJugador1\u001B[0m", "\033[34mJugador2\u001B[0m"};
        simbol= new char[]{'X','O'};
        auto = new boolean[]{true,true};

        tablero= new String[7][7];
        Columnas = tablero.length;
        Filas = tablero[0].length;
        turno = 0;

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
