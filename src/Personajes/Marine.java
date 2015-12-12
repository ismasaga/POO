package Personajes;

import java.awt.*;

public class Marine extends Jugador {

    /**
     * Constructor para archivo parseado
     */
    public Marine(Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
    }
}
