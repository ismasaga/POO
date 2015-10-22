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

    public void eliminarBinocular(Binoculares binocular) {
        arrayBinoculares.remove(binocular);
    }
}
