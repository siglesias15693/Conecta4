package com.company;

import java.util.Scanner;

import static com.company.Conecta4.*;

public class player {

    private static final Scanner lector = new Scanner(System.in);

    public static boolean colocarFicha(int columna,char caracter) {
        int contador = 0;

        if (columna >= Columnas || columna < 0) {
            mensajeError = "\n\033[35m**ERROR:\u001B[0m Debe ingresar un numero entre 1 y " + Columnas;
            return false;
        } else {
            for (int i = Filas - 1; i >= 0; i--) {

                //Si se cumple esta condicion, termina el turno
                if (!tablero[columna][i].getOcp()) {
                    tablero[columna][i].setChar(caracter);
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
            Dibujo.mostrarTablero();
            System.out.println(mensajeError);
            mensajeError = "";

            //introducir valor
            System.out.println(jugador + " escriba el numero de columna para poner su ficha:");
            int columna = lector.nextInt();
            columna--;

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

            columna=IA(columna);

            verificador=!colocarFicha(columna,caracter);
        }

        //llamo a la funcion "verificarGanador"
        verificadorGanador(jugador, caracter);
    }

    public static int IA(int x){
        for (int i=0;i<Columnas;i++){
            boolean verificador=true;
            for (int j=Filas-1;j>=0 && verificador;j--){
                if (tablero[i][j].equals(" ")){
                    if (comprovarIA(i,j,simbol[turno%2])){
                        return i;
                    }

                    if (comprovarIA(i,j,simbol[1-(turno%2)])){
                        return i;
                    }
                    verificador=false;
                }
            }
        }

        return x;
    }

    public static boolean comprovarIA(int x,int y, char caracter){

        if(y+2<Filas){
            if (tablero[x][y+1].getChar()==caracter && tablero[x][y+2].getChar()==caracter){return true;}
        }

        if(x-1>=0 && x+1<Columnas){
            if (tablero[x-1][y].getChar()==caracter && tablero[x+1][y].getChar()==caracter){return true;}

            if(x-2>=0 && x+2<Columnas){
                if (tablero[x-2][y].getChar()==caracter && tablero[x-1][y].getChar()==caracter){return true;}
                if (tablero[x+2][y].getChar()==caracter && tablero[x+1][y].getChar()==caracter){return true;}
            }else{
                if(x+3<Columnas){
                    if (tablero[x+3][y].getChar()==caracter && tablero[x+2][y].getChar()==caracter && tablero[x+1][y].getChar()==caracter){return true;}
                }

                if(x-3>=0){
                    if (tablero[x-3][y].getChar()==caracter && tablero[x-2][y].getChar()==caracter && tablero[x-1][y].getChar()==caracter){return true;}
                }
            }
        }


        return false;
    }
}
