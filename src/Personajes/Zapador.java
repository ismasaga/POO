package Personajes;

import java.awt.*;

public class Zapador extends Jugador {

    /**
     * Constructor para archivo parseado
     */
    public Zapador(Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
    }
}
