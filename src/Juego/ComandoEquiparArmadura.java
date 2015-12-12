package Juego;

import Excepciones.*;
import Objetos.Armadura;
import Personajes.Jugador;

public class ComandoEquiparArmadura implements Comando{

    private Mapa mapa;
    private Jugador jugador;
    private String nombreArmadura;

    public ComandoEquiparArmadura(Mapa mapa, Jugador jugador, String nombreArmadura) {
        this.mapa = mapa;
        this.jugador = jugador;
        this.nombreArmadura = nombreArmadura;
    }

    @Override
    public void ejecutar() throws ComandoException{
        for(Armadura armadura : jugador.getMochila().getArrayArmaduras()) {
            if (armadura.getNombre().equals(nombreArmadura)) {
                jugador.equipar(armadura);
                return;
            }
        }
        throw new ComandoException("No se ha podido equipar armadura");
    }
}
