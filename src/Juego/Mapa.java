package Juego;

import java.util.ArrayList;

/**
 * Clase Mapa. Realiza las siguientes funciones:
 * - Imprime los limites del mapa, las casillas visibles y los enemigos visibles y las casillas transitables.
 */
public class Mapa {

    private int ancho;
    private int alto;

    /**
     * Para comparar esta celda con la celda del personaje (o del enemigo), se debe usar aliasing; es decir, <strong>
     *     Comparar las referencias del objeto, habiendo, previamente, asignado "a pelo" (e.g: personaje.setCelda = mapa.getCelda(i,j)).
     * </strong>
     */
    private ArrayList<ArrayList<Celda>> mapa;

    public Mapa(int ancho,int alto) {
    }

    /**
     * O setMapa non será definido porque non queremos que se poida cambiar o mapa despois de que o programa sexa iniciado
     */

    /**
     * O getMapa non será definido porque non queremos que se poida obter o mapa despois de que o programa sexa iniciado
     */


}
