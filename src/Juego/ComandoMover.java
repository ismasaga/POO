package Juego;

import Excepciones.ComandoException;
import Excepciones.InsuficienteEnergiaException;
import Excepciones.MoverException;
import Personajes.Jugador;

public class ComandoMover implements Comando {
    private Jugador personaje;
    private char direccion;

    public ComandoMover(Mapa mapa, Jugador personaje, char direccion) throws ComandoException {
        if(mapa != null && personaje != null) {
            this.personaje = personaje;
        } else
            throw new ComandoException("Se ha pasado el personaje nulo");
        if(direccion == 'd' || direccion == 'u' || direccion == 'r' || direccion == 'l')
            this.direccion = direccion;
        else
            throw new ComandoException("Dirección a mover incorrecta, mas info en la ayuda");
    }

    @Override
    public void ejecutar() throws ComandoException,MoverException,InsuficienteEnergiaException {
        personaje.mover(direccion);
    }
}
