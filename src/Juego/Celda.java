package Juego;

import Objetos.Arma;
import Objetos.Armadura;
import Objetos.Binocular;
import Objetos.Botiquin;
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
    private ArrayList<Binocular> arrayBinoculares;
    private ArrayList<Botiquin> arrayBotiquin;
    private ArrayList<Enemigo> arrayEnemigos;
    private ArrayList<Arma> arrayArma;
    private ArrayList<Armadura> arrayArmadura;
    private Jugador jugador;
    private boolean transitable;
    private Point punto;

    public Celda(boolean transitable, Point punto) {
        this.transitable = transitable;
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
        arrayEnemigos = new ArrayList<>();
        arrayArma = new ArrayList<>();
        arrayArmadura = new ArrayList<>();
        setJugador(null);
        setPunto(punto);
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
     * Anhade un binocular a la celda
     */
    public void setBinoculares(Binocular binocular) {
        if (binocular != null)
            arrayBinoculares.add(binocular);
        else
            System.out.println("ERROR asignando binocular a la celda");
    }

    /**
     * Devolve os binoculares que ten a celda
     */
    public ArrayList<Binocular> getBinoculares() {
        return arrayBinoculares;
    }

    public ArrayList<Botiquin> getBotiquin() {
        return arrayBotiquin;
    }

    public void eliminarBinocular(Binocular binocular) {
        arrayBinoculares.remove(binocular);
    }

    public void setBotiquin(Botiquin botiquin) {
        arrayBotiquin.add(botiquin);
    }

    public void eliminarBotiquin(Botiquin botiquin) {
        arrayBotiquin.remove(botiquin);
    }

    public void eliminarArma(Arma arma) {
        arrayArma.remove(arma);
    }

    public void eliminarArmadura(Armadura armadura) {
        arrayArmadura.remove(armadura);
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
            System.out.println("ERROR insertando enemigo en la casilla");
    }

    public void setArma(Arma arma) {
        if (arma != null)
            arrayArma.add(arma);
        else
            System.out.println("ERROR insertando arma en la casilla");
    }

    public ArrayList<Arma> getArma() {
        if (arrayArma.isEmpty())
            return null;
        else
            return arrayArma; //Necesitamos el aliasing
    }

    public void setArmadura(Armadura armadura) {
        if (armadura != null)
            arrayArmadura.add(armadura);
        else
            System.out.println("ERROR insertando armadura en la casilla");
    }

    public ArrayList<Armadura> getArmaduras() {
        if (arrayArmadura.isEmpty())
            return null;
        else
            return arrayArmadura; //Necesitamos el aliasing
    }

    /**
     * Elimina el enemigo de la celda
     */
    public void eliminarEnemigo(Enemigo enemigo) {
        if (!arrayEnemigos.remove(enemigo))
            System.out.println("ERROR eliminando enemigo");
    }

    public boolean isTransitable() {
        return transitable;
    }

    public void setTransitable(boolean transitable) {
        this.transitable = transitable;
    }
}
