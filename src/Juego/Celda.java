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
    private ArrayList<Arma> arrayArma;
    private ArrayList<Armadura> arrayArmadura;
    private boolean transitable;

    public Celda(boolean transitable) {
        this.transitable = transitable;
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
        arrayEnemigos = new ArrayList<>();
        arrayArma = new ArrayList<>();
        arrayArmadura = new ArrayList<>();
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
     * Devolve todos os inimigos da celda.
     * @return Enemigo
     */
    public ArrayList<Enemigo> getEnemigo() {
        if(arrayEnemigos.isEmpty())
            return null;
        else
            return arrayEnemigos; //Necesitamos el aliasing
    }

    public void setEnemigo(Enemigo enemigo) {
        if(enemigo != null)
            arrayEnemigos.add(enemigo);
        else
            System.out.println("ERROR insertando enemigo en la casilla");
    }

    public void setArma(Arma arma)
    {
        if(arma != null)
            arrayArma.add(arma);
        else
            System.out.println("ERROR insertando arma en la casilla");
    }

    public ArrayList<Arma> getArma()
    {
        if(arrayArma.isEmpty())
            return null;
        else
            return arrayArma; //Necesitamos el aliasing
    }

    public void setArmadura(Armadura armadura)
    {
        if(armadura != null)
            arrayArmadura.add(armadura);
        else
            System.out.println("ERROR insertando armadura en la casilla");
    }

    public ArrayList<Armadura> getArmaduras()
    {
        if(arrayArmadura.isEmpty())
            return null;
        else
            return arrayArmadura; //Necesitamos el aliasing
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
