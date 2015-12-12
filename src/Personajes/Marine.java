package Personajes;

import Juego.Mapa;

import java.awt.*;

public class Marine extends Jugador {

    /**
     * Constructor para archivo parseado
     */
    public Marine(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa, punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
    }
}
