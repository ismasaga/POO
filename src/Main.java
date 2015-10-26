import Juego.*;
import Objetos.Binoculares;
import Objetos.Botiquin;

import java.util.ArrayList;

/**
 * Esta clase solo debería llamar a Juego.Juego.run()...
 */
public class Main {
    public static void main(String[] args) {
        Mapa mapa = new Mapa(10,10);
        mapa.getCelda(1,1).setBinoculares(new Binoculares(2,3,4));
        mapa.getCelda(0,0).setBotiquin(new Botiquin(1,2,3));
        mapa.getCelda(2,2).setEnemigo(new Enemigo(100,2,3));
        Celda celdaActual = mapa.getCelda(5,5);
        Personaje personaje = new Personaje(100,100,2,celdaActual,new Mochila(10,20),3,2,100);
        mapa.imprimir(personaje);
        System.out.println("\n\n\n\n\n\n\n");
        personaje.mover(mapa.getCelda(1,1));
        mapa.imprimir(personaje);
        personaje.mirar();
    }
}
