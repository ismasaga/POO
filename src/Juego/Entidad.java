package Juego;

import Objetos.Arma;

import java.rmi.UnexpectedException;

/**
 * Clase abstracta Entidad. Consideraciones:
 * <ul>
 *    <li>No se incluye una armadura pues los enemigos no la poseen.</li>
 *    <li>No se incluye enegía, pues solo es propiedad del personaje principal</li>
 *    <li>Los <em>puntos de ataque</em> van asociados al arma que posea la entidad (en el caso de un enemigo, por defecto)</li>
 * </ul>
 */
public abstract class Entidad
{
    /**
     * Nombre del objeto o personaje
     */
    private String nombre;
    /**
     * Puntos de vida del personaje
     */
    private int puntosVida;
    /**
     * Arma de la entidad.
     */
    private Arma arma;

    /**
     * Mueve a la entidad a la posición seleccionada <strong>incluídos los enemigos</strong> //TODO: revisar
     * Consistiria en hacer un set de la posicion del mapa
     *
     * El mapa se pasa por <em>referencia</em>
     */
    //TODO: revisar
    public void mover(Mapa mapa)
    {
        System.err.println("No implementado");
    }

}
