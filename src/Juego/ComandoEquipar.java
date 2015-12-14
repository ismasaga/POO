package Juego;

import Excepciones.*;
import Objetos.Arma;
import Objetos.Armadura;
import Personajes.Jugador;

public class ComandoEquipar implements Comando{

    private Mapa mapa;
    private Jugador personaje;
    private String nombre;
    private String mano;

    public ComandoEquipar(Jugador personaje, Mapa mapa, String nombre, String mano) {
        this.personaje = personaje;
        this.mapa = mapa;
        this.nombre = nombre;
        this.mano = mano;
    }

    @Override
    public void ejecutar() throws ManosArmaException , ComandoException{
        for(Arma arma : personaje.getMochila().getArrayArmas()){
            if(arma.getNombre().equals(nombre)){
                personaje.equipar(arma,mano);
                return;
            }
        }
        for(Armadura armadura : personaje.getMochila().getArrayArmaduras()) {
            if (armadura.getNombre().equals(nombre)) {
                personaje.equipar(armadura);
                return;
            }
        }
        throw new ComandoException("Error procesando equipar");
    }
}
