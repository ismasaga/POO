package Juego;

import Excepciones.*;
import Objetos.Armadura;
import Personajes.Personaje;

public class ComandoDesequiparArmadura implements Comando{

    private Mapa mapa;
    private Personaje personaje;

    public ComandoDesequiparArmadura(Mapa mapa, Personaje personaje) {
        this.mapa = mapa;
        this.personaje = personaje;
    }

    @Override
    public void ejecutar() {
        personaje.desequipar(new Armadura());
    }
}
