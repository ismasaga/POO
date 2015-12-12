package Juego;

import Excepciones.*;
import Personajes.Jugador;

/**
 * Created by efren on 12/12/15.
 */
public class ComandoMirar implements Comando{

    Mapa mapa;
    Jugador jugador;
    int posicionX,posicionY;
    char direccionX, direccionY;
    String objeto;

    public ComandoMirar(Mapa mapa, Jugador jugador, int posicionX, int posicionY, char direccionX, char direccionY, String objeto) {
        this.mapa = mapa;
        this.jugador = jugador;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.direccionX = direccionX;
        this.direccionY = direccionY;
        this.objeto = objeto;
    }

    @Override
    public void ejecutar() throws SegmentationFaultException, FueraDeRangoException{
        jugador.mirar(mapa,posicionX,posicionY,direccionX,direccionY,objeto);
    }
}
