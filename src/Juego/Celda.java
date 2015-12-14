package Juego;

import Objetos.*;
import Personajes.Enemigo;
import Personajes.Jugador;

import java.awt.*;
import java.util.ArrayList;

/**
 * Os set tanto de arrayBinoculares como de arrayBotiquin non son definidos porque non precisamos pasarlle
 * o array completo en ningún momento e chegamos o acordo de que non o imos facer
 * <p>
 * A destacar:
 *  setMapa() non implementado para que so se poida cambiar o mapa dunha casilla cando esta e creada
 * Los setters de celda añaden elementos uno a uno y son setEnemigo, setBinoculares y setBotiquin
 */

public class Celda {
    private ArrayList<Objeto> arrayObjetos;
    private ArrayList<Enemigo> arrayEnemigos;
    private Jugador jugador;
    private boolean transitable;
    private Point punto;
    private Consola consola = new ConsolaNormal();

    public Celda(boolean transitable, Point punto) {
        this.transitable = transitable;
        arrayEnemigos = new ArrayList<>();
        arrayObjetos = new ArrayList<>();
        setJugador(null);
        setPunto(punto);
    }

    public ArrayList<Objeto> getArrayObjetos() {
        return arrayObjetos;
    }

    /**
     * Añade un objeto a la celda
     */
    public void anadirObjeto(Objeto objeto){
        arrayObjetos.add(objeto);
    }


    /**
     * Devolve o punto que contén as coordenadas da celda(pode ser nulo)
     */
    public Point getPunto() {
        return punto;
    }

    /**
     * Engade ou actualiza o punto onde se atopa a casilla,
     * se o punto esta a nulo creariase un co constructor por defecto
     */
    public void setPunto(Point punto) {
        if(punto != null)
            this.punto = punto;
        else
            this.punto = new Point();//Crea o punto coas coordenadas (0,0)
    }

    /**
     * Obten o xogador, que pode ser null no caso que non este nesta celda
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Mete o xogador na celda ou null no caso de que non estea nesta celda
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }


    /**
     * Devolve todos os inimigos da celda.
     */
    public ArrayList<Enemigo> getEnemigo() {
        if (arrayEnemigos.isEmpty())
            return null;
        else
            return arrayEnemigos; //Necesitamos el aliasing
    }

    public void setEnemigo(Enemigo enemigo) {
        if (enemigo != null)
            arrayEnemigos.add(enemigo);
        else
            consola.imprimir("ERROR insertando enemigo en la casilla");
    }


    /**
     * Elimina el enemigo de la celda
     */
    public void eliminarEnemigo(Enemigo enemigo) {
        if (!arrayEnemigos.remove(enemigo))
            consola.imprimir("ERROR eliminando enemigo");
    }

    public boolean isTransitable() {
        return transitable;
    }

    public void setTransitable(boolean transitable) {
        this.transitable = transitable;
    }
}
