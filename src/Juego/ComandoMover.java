package Juego;

import Excepciones.ComandoException;
import Personajes.Personaje;

public class ComandoMover implements Comando {
    Mapa mapa;
    Personaje personaje;
    char direccion;

    public ComandoMover(Mapa mapa, Personaje personaje, char direccion) throws ComandoException {
        if(mapa != null && personaje != null) {
            this.mapa = mapa;
            this.personaje = personaje;
        } else
            throw new ComandoException("Se ha pasado el mapa o el personaje nulos");
        this.direccion = direccion;
    }

    @Override
    public void ejecutar() throws Exception {

    }

    @Override
    public void mover() {

    }

    @Override
    public void coger() {

    }

    @Override
    public void atacar() {

    }

    @Override
    public void usar() {

    }
}
