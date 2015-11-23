package Juego;

/**
 * Clase auxiliar de una clase auxiliar (debería ser una innerclass).
 * Esta clase proporciona un medio seguro para obtener el mapa y el personaje de parser.
 */
public class Bundle {
    Mapa mapa = null;
    Personaje personaje = null;

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}
