
import funciones.Cell;
import funciones.Jugador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Creacion de las clases
        Cell table[][] = createTable();
        Jugador []jugadores = crearJugadores();
        Scanner scanner = new Scanner(System.in);


        //Tablero de memoria
        char [][]tablero = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };


        //mostrarTable(table);
        boolean finJuego = false;
        int ganador = 0;

        inicio:
        while(!finJuego){
            for (Jugador jugador: jugadores) {
                //mostrar Tablero
                mostrarTable(table);

                //mostramos mensaje de aviso al jugador
                System.out.println(jugador.toString());

                //obtenemos las cordenadas
                int cordenada_fila = Integer.parseInt(scanner.nextLine());
                int cordenada_columna = Integer.parseInt(scanner.nextLine());


                if(comprobarExistenciaCordenada(cordenada_fila, cordenada_columna, table)){

                    //las cordenadas estan en el limite que es
                    if(!(ponerFigura(cordenada_fila, cordenada_columna, jugador.getFigura(), table))){
                        System.out.println("Sitio no disponible");
                        continue inicio;
                    }

                    tablero[cordenada_fila][cordenada_columna] = (char) (jugador.getId() + '0');
                    //debemos verificar si alguien ya gano para romper el ciclo

                    char resultado = existeUnGanador(tablero);
                    if(resultado == ' '){
                        continue;
                    }
                    finJuego = true;
                    ganador = Character.getNumericValue(resultado);
                    break;

                }else{
                    //un mensaje que avise aL usuario
                    System.out.println(String.format("Cordenadas:{%d,%d} malas", cordenada_fila, cordenada_columna));
                    continue inicio;
                }
            }
        }
        Jugador jugadorGanador = ganadorEs(Character.getNumericValue(ganador), jugadores);
        assert jugadorGanador != null;
        System.out.println("Gano alguien:" + jugadorGanador.getNombre());
    }



    public static Jugador ganadorEs(int id, Jugador[]jugadores){
        //funcion para escoger el ganador
        if(id != 0){
            for (Jugador jugador: jugadores) {
                if(jugador.getId() == id){
                    return jugador;
                }
            }
        }
        return null;
    }

    public static char existeUnGanador(char [][]tablero){
        int contadorJugador1 = 0, contadorJugador2 = 0;


        //revisar de la primera forma
        for (int fila = 0; fila < tablero.length ; fila++) {
            for(int columna = 0; columna < tablero[0].length; columna++){
                if(tablero[columna][fila] != ' '){
                    //tenemos una ficha que alguien puso alli
                    if(tablero[columna][fila] == '1'){
                        contadorJugador1++;
                    }else{
                        contadorJugador2++;
                    }
                }
            }
            if(contadorJugador1 == 3 || contadorJugador2 == 3){
                //existe un ganador
                return contadorJugador1 == 3 ? '1' : '2';
            }
            contadorJugador1 = 0;
            contadorJugador2 = 0;
        }

        contadorJugador1 = 0;
        contadorJugador2 = 0;

        for(int fila = 0; fila < tablero.length; fila++){
            for(int columna = 0; columna < tablero[0].length; columna++){
                if(tablero[fila][columna] != ' '){
                    if(tablero[fila][columna] == '1'){
                        contadorJugador1++;
                    }else{
                        contadorJugador2++;
                    }
                }
            }
            if(contadorJugador1 == 3 || contadorJugador2 == 3){
                return contadorJugador1 == 3 ? '1' : '2';
            }
            contadorJugador1 = 0;
            contadorJugador2 = 0;
        }


        //falta revisar la ultima forma
        contadorJugador1 = 0;
        contadorJugador2 = 0;
        int contador = 0, fila = 0, columna = 0;
        while(contador < tablero.length){
            if(tablero[fila][columna] != ' '){
                if(tablero[fila][columna] == '1'){
                    contadorJugador1++;
                }else{
                    contadorJugador2++;
                }
            }
            if(contadorJugador1 == 3 || contadorJugador2 == 3){
                return contadorJugador1 == 3 ? '1' : '2';
            }

            fila ++;
            columna++;
            contador++;
        }


        contadorJugador1 = 0;
        contadorJugador2 = 0;
        contador = 0;
        fila = 0;
        columna = 2;
        while(contador < tablero.length){
            if(tablero[fila][columna] != ' '){
                if(tablero[fila][columna] == '1'){
                    contadorJugador1++;
                }else{
                    contadorJugador2++;
                }
            }
            if(contadorJugador1 == 3 || contadorJugador2 == 3){
                return contadorJugador1 == 3 ? '1' : '2';
            }

            fila ++;
            columna--;
            contador++;
        }

        //si llega aqui no existe un ganador
        return ' ';

    }

    public static boolean ponerFigura(int x, int y, char z, Cell [][] table){
        if(Character.compare(table[x][y].getId(), '-') == 0){
            table[x][y].setId(z);
            return true;
        }
        return false;
    }

    public static boolean comprobarExistenciaCordenada(int x, int y, Cell [][] z){
        try{
            Cell d = z[x][y];
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void mostrarTable(Cell table[][]){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j].getId());
            }
            System.out.println("\n");
        }
    }

    public static Jugador[] crearJugadores(){
        Jugador []jugadores = new Jugador[2];
        jugadores[0] = new Jugador("Miguel", 'X');
        jugadores[1] = new Jugador("Juan", 'O');
        return jugadores;
    }

    public static Cell[][] createTable(){
        //creamos la tabla
        Cell  [][] table  = new Cell[3][3];
        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[0].length; column++) {
                table[row][column] = new Cell('-');
            }
        }
        return table;
    }
}
