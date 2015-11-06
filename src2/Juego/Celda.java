package Juego;

import Objetos.*;
import java.util.ArrayList;

/**
 * Os set tanto de arrayBinoculares como de arrayBotiquin non son definidos porque non precisamos pasarlle
 * o array completo en ningún momento e chegamos o acordo de que non o imos facer
 *
 * A destacar:
 *
 * Los setters de celda añaden elementos uno a uno y son setEnemigo, setBinoculares y setBotiquin
 */

public class Celda {
    private ArrayList<Binoculares> arrayBinoculares;
    private ArrayList<Botiquin> arrayBotiquin;
    private ArrayList<Enemigo> arrayEnemigos;
    private boolean transitable;

    //private Enemigo enemigo = null; //Las celdas contienen un enemigo (facilita la impresión)

    public Celda(boolean transitable) {
        this.transitable = transitable;
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
        arrayEnemigos = new ArrayList<>();
    }

    /**
     * Engade un binocular a celda
     * @param binocular
     */
    public void setBinoculares(Binoculares binocular) {
        arrayBinoculares.add(binocular);
    }

    /**
     * Devolve os binoculares que ten a celda
     * @return
     */
    public ArrayList<Binoculares> getBinoculares() {
        return arrayBinoculares;
    }

    public ArrayList<Botiquin> getBotiquin()
    {
        return arrayBotiquin;
    }

    public void eliminarBinocular(Binoculares binocular) {
        arrayBinoculares.remove(binocular);
    }

    public void setBotiquin(Botiquin botiquin)
    {
        arrayBotiquin.add(botiquin);
    }

    public void eliminarBotiquin(Botiquin botiquin)
    {
        arrayBotiquin.remove(botiquin);
    }

    /**
     * Devolve o primeiro enemigo que atopa no array, se non hay ningún devolve null
     * @return Enemigo
     */
    public Enemigo getEnemigo() {
        if(arrayEnemigos.isEmpty())
            return null;
        else
            return arrayEnemigos.get(0);
    }

    public void setEnemigo(Enemigo enemigo) {
        if(enemigo != null)
            arrayEnemigos.add(enemigo);
        else
            System.out.println("ERROR insertando enemigo en la casilla");
    }

    /**
     * Elimina el enemigo de la celda
     */
    public void eliminarEnemigo(Enemigo enemigo) {
        if(!arrayEnemigos.remove(enemigo))
            System.out.println("ERROR eliminando enemigo");
    }

    public boolean isTransitable()
    {
        return transitable;
    }

    public void setTransitable(boolean transitable)
    {
        this.transitable = transitable;
    }
}
