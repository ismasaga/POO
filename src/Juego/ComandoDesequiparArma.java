package Juego;

import Excepciones.*;
import Personajes.*;

public class ComandoDesequiparArma implements Comando{

    private Mapa mapa;
    private Jugador personaje;
    private String mano;

    public ComandoDesequiparArma(Mapa mapa, Jugador personaje, String mano) {
        this.mapa = mapa;
        this.personaje = personaje;
        this.mano = mano;
    }

    @Override
    public void ejecutar() throws SegmentationFaultException, ComandoException{
        if(mano.equals("derecha")){
            personaje.desequipar(personaje.getArmaDer());
        }
        else if (mano.equals("izquierda")) {
            personaje.desequipar(personaje.getArmaIzq());
        }
        else if (mano.equals("")){
            personaje.desequipar(personaje.getArmaDosM());
        }
        else
            throw new ComandoException("No se ha escrito correctamente la mano");
    }
}
