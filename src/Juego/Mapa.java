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
        this.alto = alto > 0 ? alto : 10;
        this.ancho = ancho > 0 ? ancho : 10;
    }

    /**
     * O setMapa non será definido porque non queremos que se poida cambiar o mapa despois de que o programa sexa iniciado
     */

    /**
     * O getMapa non será definido porque non queremos que se poida obter o mapa despois de que o programa sexa iniciado
     */

    /**
     * No se define setCelda, pues el contenido de las celdas se modifica con aliasing
     */

    /**
     * Retorna la celda de la posición (i,j) en base 0 (0..9, por defecto en los valores del constructor de dos parametros)
     *
     * @param i Fila a obtener (ancho)
     * @param j Columna a obtener (alto)
     * @return Celda a obtener (null en caso de fallo)
     */
    public Celda getCelda(int i, int j)
    {
        if(i < ancho && j < alto && i >= 0 && j >= 0)
        {
            return mapa.get(i).get(j);
        }
        else
        {
            return null;
        }
    }

    //TODO: crear setBotiquin/eliminarBotiquin (si se cambian los nombres cambiarlos en Personaje, donde hay un error)



}
