package Juego;

import Excepciones.ComandoException;
import Excepciones.EspacioMaximoException;
import Excepciones.PesoMaximoException;
import Excepciones.SegmentationFaultException;
import Objetos.Objeto;
import Personajes.Jugador;

public class ComandoCoger implements Comando {

    private Mapa mapa;
    private Jugador jugador;
    private String objeto;

    public ComandoCoger(Mapa mapa, Jugador jugador, String objeto) {
        this.mapa = mapa;
        this.jugador = jugador;
        this.objeto = objeto;
    }

    @Override
    public void ejecutar() throws ComandoException, SegmentationFaultException, EspacioMaximoException, PesoMaximoException {
        for(Objeto obj : mapa.getCelda(jugador.getPunto().x, jugador.getPunto().y).getArrayObjetos()){
            if(obj.getNombre().equals(objeto)){
                jugador.coger(obj);
                return;
            }
        }
        throw new ComandoException("Objeto no encontrado");
    }
}
