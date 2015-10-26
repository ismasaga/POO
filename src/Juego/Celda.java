package Juego;

import Objetos.*;
import java.util.ArrayList;

/**
 * Os set tanto de arrayBinoculares como de arrayBotiquin non son definidos porque non precisamos pasarlle
 * o array completo en ningún momento e chegamos o acordo de que non o imos facer
 */

public class Celda {
    private ArrayList<Binoculares> arrayBinoculares;
    private ArrayList<Botiquin> arrayBotiquin;
    private boolean transitable;

    private Enemigo enemigo = null; //Las celdas contienen un enemigo (facilita la impresión)

    public Celda(boolean transitable) {
        this.transitable = transitable;
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
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

    public Enemigo getEnemigo()
    {
        return enemigo;
    }

    public void setEnemigo(Enemigo enemigo)
    {
        this.enemigo = enemigo;
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
