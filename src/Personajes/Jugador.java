package Personajes;

import Juego.Mapa;
import Objetos.Binocular;

import java.awt.*;

public class Jugador extends Personaje {
    private Binocular binocular;

    /**
     * Constructor para archivo parseado
     */
    public Jugador(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa,punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
    }


    /**
     * Devolve null cando non ten equipado ningun binocular
     */
    public Binocular getBinocular() {
        return binocular;
    }

    /**
     * Pode ser null para indicar que non ten binocular equipado
     */
    public void setBinocular(Binocular binocular) {
        this.binocular = binocular;
    }
}
