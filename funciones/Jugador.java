package funciones;

import java.util.concurrent.atomic.AtomicInteger;

public class Jugador {

    private static int  contador = 0; //Esta variable reserva espacio de memoria una sola vez
    private String nombre;
    private int id;

    private char figura;

    public Jugador(String nombre, char figura){
        this.nombre = nombre;
        this.id = ++this.contador;
        this.figura = figura;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getId(){
        return this.id;
    }

    public char getFigura() {
        return this.figura;
    }

    @Override
    public String toString() {
        return String.format("Turno del jugador:%s, identificador:%d, figura:%s", this.nombre, this.id, this.figura);
    }
}
