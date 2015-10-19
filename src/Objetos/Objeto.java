package Objetos;

import Juego.*;

/**
 * Clase genérica para los objetos del juego. Dependiendo de los valores de los atributos el objeto en cuestión toma forma.
 * Funciones:
 * - Usar en enemigo o personaje.
 * - Dejar en el mapa (gracias a la clase mapa).
 */
public abstract class Objeto
{
    /**
     * Nombre del objeto
     */
    private String nombre;
    /**
     * Peso del objeto (determinante a la hora de incluirlo en la mochila). Se inicializa en el constructor (al ser cte.)
     */
    private final int peso;
    /**
     *  Visibilidad del objeto. Indica si el objeto puede ser imprimido en el mapa.
     */
    //TODO: revisar
    private boolean visible;
    /**
     * Gasto de energía asociado al uso del objeto.
     */
    private final int energia;

    public boolean esVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public int getEnergia()
    {
        return energia;
    }

    /**
     * Único constructor de esta clase. Inicializa el nombre y el peso
     * @param nombre Nombre del objeto.
     * @param peso Peso del objeto (es constante)
     */
    public Objeto(String nombre, int peso, int energia)
    {
        this.nombre = nombre;
        this.peso = peso;
        this.energia = energia;
    }

    /**
     * Metodo abstracto: debe implementarse en cada una de las clases
     * Ejemplo:
     *  - Botiquin cura
     *  - Arma pega
     *  - etc.
     * @param personaje Entidad a la que se aplica usar //TODO: revisar
     */
    public abstract void usar(Entidad personaje);

    /**
     * Método abstracto que permite situar el método en el mapa. Debe añadir el objeto al mapa y, luego, mostrarlo como visible.
     * @param mapa
     */
    //TODO: revisar
    public abstract void dejarEnMapa(Mapa mapa);
}
