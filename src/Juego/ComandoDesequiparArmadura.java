package Juego;

import Excepciones.*;
import Objetos.Armadura;
import Personajes.Personaje;

/**
 * Created by efren on 13/12/15.
 */
public class ComandoDesequiparArmadura implements Comando{

    Mapa mapa;
    Personaje personaje;

    public ComandoDesequiparArmadura(Mapa mapa, Personaje personaje) {
        this.mapa = mapa;
        this.personaje = personaje;
    }

    @Override
    public void ejecutar() {
        personaje.desequipar(new Armadura());
    }
}
