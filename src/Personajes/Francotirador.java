package Personajes;

import java.awt.*;

public class Francotirador extends Jugador {

    /**
     * Constructor para archivo parseado
     */
    public Francotirador(Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
    }
}
