package com.company;

import java.util.Scanner;

public class Conecta4 {

    //cambiando los valores de la matriz "tablero" se puede cambiar el tamaño del tablero
    public static String[][] tablero = new String[7][7];
    public static int capacidadColumnas;
    public static int capacidadFilas;
    public static boolean finJuego = false;

    public void mostrarTablero(String[][] tabla) {
        System.out.print("          ");
        for (int i = 1; i < capacidadColumnas + 1; i += 1) {
            System.out.print(i + "   ");
        }
        System.out.println("");
        System.out.print("         ");
        for (int i = 0; i < capacidadColumnas; i += 1) {
            System.out.print("____");
        }
        System.out.println("");
        for (int i = 0; i < capacidadFilas; i++) {
            System.out.print("       ");
            for (int j = 0; j < capacidadFilas; j++) {
                System.out.print(" | " + tabla[i][j]);
            }
            System.out.println(" |");
            if (i < capacidadFilas - 1) {
                System.out.print("        |");
                for (int p = 1; p < capacidadColumnas - 1; p += 1) {
                    System.out.print("––––––");
                }
                System.out.println("|");
            }
        }
        System.out.print("         ");
        for (int i = 1; i < capacidadColumnas + 1; i += 1) {
            System.out.print("¯¯¯¯");
        }
        System.out.println("");
    }

    public void mostrarInterfazTablero(String jugador1, String caracter1, String jugador2, String caracter2, int jugadasMaximas) {
        System.out.println("=================================================================");
        System.out.println(jugador1 + " : " + caracter1 + "      " + jugador2 + " : " + caracter2);
        System.out.print("==============================================||| JUGADA N°" + jugadasMaximas + " |||");
    }

    public void verificadorGanador(String auxiliarJugador, String auxilarCaracter) {
        //verificacion ganador (horizontal)
        for (int i = 1; i < capacidadFilas; i += 1) {
            for (int j = 0; j < capacidadColumnas - 3; j += 1) {
                if (tablero[i][j].equals(auxilarCaracter) && tablero[i][j + 1].equals(auxilarCaracter) && tablero[i][j + 2].equals(auxilarCaracter) && tablero[i][j + 3].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTO 4 \033[35mHORIZONTALMENTE!!!\u001B[0m");
                }
            }
        }

        //verificacion ganador (vertical)
        for (int i = 0; i < capacidadFilas; i += 1) {
            for (int j = 0; j < capacidadColumnas - 3; j += 1) {
                if (tablero[j][i].equals(auxilarCaracter) && tablero[j + 1][i].equals(auxilarCaracter) && tablero[j + 2][i].equals(auxilarCaracter) && tablero[j + 3][i].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTO 4 \033[35mVERTICALMENTE!!!\u001B[0m");
                }
            }
        }

        //verificacion ganador (diagonal)
        for (int i = 0; i < capacidadColumnas - 4 + 1; i += 1) {
            for (int j = 0; j < capacidadFilas - 4 + 1; j += 1) {
                if (tablero[j][i].equals(auxilarCaracter) && tablero[j + 1][i + 1].equals(auxilarCaracter) && tablero[j + 2][i + 2].equals(auxilarCaracter) && tablero[j + 3][i + 3].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTO 4 \033[35mDIAGONALMENTE!!!\u001B[0m");
                }
            }
        }
        for (int i = capacidadColumnas; i > 3; i -= 1) {
            for (int j = 0; j < capacidadFilas - 3; j += 1) {
                if (tablero[j][i - 1].equals(auxilarCaracter) && tablero[j + 1][i - 2].equals(auxilarCaracter) && tablero[j + 2][i - 3].equals(auxilarCaracter) && tablero[j + 3][i - 4].equals(auxilarCaracter)) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + auxiliarJugador + " CONECTO 4 \033[35mDIAGONALMENTE!!!\u001B[0m");
                }
            }
        }
    }

    public static void main(String[] args) {
        Conecta4 programa = new Conecta4();
        Scanner lector = new Scanner(System.in);
        int jugadasMaximas = 1;
        int columna = 0;
        int contador = 0;
        int modoDeJuego = 0;
        String mensajeError = "";
        String jugador1;
        String caracter1;
        String jugador2;
        String caracter2;
        boolean verificador = false;
        boolean verificador2 = false;
        capacidadColumnas = tablero[1].length;
        capacidadFilas = tablero.length;
        String auxiliarJugador = "";
        String auxiliarCaracter = "";

        //titulo
        modoDeJuego = 2;

        //Se asigna un caracter por defecto al tablero
        for (int i = 0; i <= capacidadColumnas - 1; i++) {
            for (int j = 0; j <= capacidadFilas - 1; j++) {
                tablero[i][j] = " ";
            }
        }

        verificador = false;

        if (modoDeJuego == 2) {

            //aplico color azul al nombre y caracter del jugador 1
            jugador1 = "\033[32m" + "Jugador 1" + "\u001B[0m";
            caracter1 = "\033[31m" + "X" + "\u001B[0m";

            jugador2 = "\033[33mComputadorNoob\u001B[0m";
            caracter2 = "\033[33m§\u001B[0m";
            while (!finJuego) {

                //turno jugador 1
                while (!verificador) {
                    //llamo a la funcion "mostrarTablero" y "mostrarInterfazTablero" de la clase conecta 4;
                    programa.mostrarTablero(tablero);
                    programa.mostrarInterfazTablero(jugador1, caracter1, jugador2, caracter2, jugadasMaximas);
                    System.out.println(mensajeError);
                    mensajeError = "";
                    try {
                        System.out.println(jugador1 + " escriba el numero de columna para poner su ficha:");
                        String auxiliar = lector.nextLine();
                        columna = Integer.parseInt(auxiliar) - 1;
                        if (columna > capacidadColumnas - 1 || columna < 0) {
                            mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + capacidadColumnas;
                        } else {
                            contador = 1;
                            for (int i = capacidadColumnas - 1; i > -1; i = i - 1) {
                                if (!verificador2) {
                                    //Si se cumple esta condicion, termina el turno del jugador 1
                                    if (tablero[i][columna] != caracter1 && tablero[i][columna] != caracter2) {
                                        tablero[i][columna] = caracter1;
                                        verificador2 = true;
                                        verificador = true;
                                        jugadasMaximas++;
                                    } else {
                                        contador += 1;
                                    }
                                }
                                if (contador == capacidadFilas + 1) {
                                    mensajeError = "\n\033[35m**ERROR:\u001B[0m Esta columna esta completa, escoja otra";
                                }
                            }
                        }
                    } catch (NumberFormatException e) {
                        mensajeError = "\n\033[35m**ERROR:\u001B[0m Ingreso un caracter no valido, debe ingresar un NUMERO";
                    } catch (Exception e) {
                        mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre el 1 al " + capacidadColumnas;
                    }
                }
                verificador2 = false;
                verificador = false;
                auxiliarJugador = jugador1;
                auxiliarJugador = caracter1;
                //llamo a la funcion "verificarGanador" para controlar si gano el jugador 1
                programa.verificadorGanador(jugador1, caracter1);

                if (jugadasMaximas == (capacidadFilas * capacidadColumnas) + 1) {
                    finJuego = true;
                    programa.mostrarTablero(tablero);
                    System.out.println("======================");
                    System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
                    System.out.println("El tablero esta lleno");
                    System.out.println("======================");
                }

                //Si el jugador 1 no gano en su turno continua con el ordenador
                if (finJuego == false) {

                    //turno ordenador
                    while (!verificador) {
                        columna = (int) (Math.random() * capacidadColumnas);
                        for (int i = capacidadColumnas - 1; i > -1; i = i - 1) {
                            if (!verificador2) {
                                //Si se cumple esta condicion, termina el turno del jugador 2
                                if (tablero[i][columna] != caracter2 && tablero[i][columna] != caracter1) {
                                    tablero[i][columna] = caracter2;
                                    verificador2 = true;
                                    verificador = true;
                                    jugadasMaximas++;
                                }
                            }
                        }
                    }
                    verificador2 = false;
                    verificador = false;
                    auxiliarJugador = jugador2;
                    auxiliarJugador = caracter2;
                    //llamo a la funcion "verificarGanador" para controlar si gano el jugador 2
                    programa.verificadorGanador(jugador2, caracter2);

                    if (jugadasMaximas == (capacidadFilas * capacidadColumnas) + 1) {
                        finJuego = true;
                        programa.mostrarTablero(tablero);
                        System.out.println("======================");
                        System.out.println("\033[35m¡¡¡EMPATE!!!\u001B[0m");
                        System.out.println("El tablero esta lleno");
                        System.out.println("======================");
                    }
                }
            }
        }
    }
}
