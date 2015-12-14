package Juego;

import Personajes.Jugador;

/**
 * Clase auxiliar de una clase auxiliar (debería ser una innerclass).
 * Esta clase proporciona un medio seguro para obtener el mapa y el personaje de parser.
 */
public class Juego {
    Mapa mapa = null;
    Jugador personaje = null;

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Jugador getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Jugador personaje) {
        this.personaje = personaje;
    }
}
