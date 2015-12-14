package Juego;

import Excepciones.*;
import Objetos.Arma;
import Personajes.Jugador;

public class ComandoEquiparArma implements Comando{

    private Mapa mapa;
    private Jugador personaje;
    private String nombreArma;
    private String mano;

    public ComandoEquiparArma(Jugador personaje, Mapa mapa, String nombreArma, String mano) {
        this.personaje = personaje;
        this.mapa = mapa;
        this.nombreArma = nombreArma;
        this.mano = mano;
    }

    @Override
    public void ejecutar() throws ManosArmaException , ComandoException{
        for(Arma arma : personaje.getMochila().getArrayArmas()){
            if(arma.getNombre().equals(nombreArma)){
                personaje.equipar(arma,mano);
                return;
            }
        }
        throw new ComandoException("Error procesando equipar");
    }
}
