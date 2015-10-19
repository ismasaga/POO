package Juego;

import Objetos.*;

/**
 * Clase personaje. Es la clase que se encarga del personaje encarnado por el jugador. Realiza las siguientes funciones:
 * - Almacena los puntos de vida y armadura (¿maná?) del personaje.
 * - Permite atacar a un enemigo.
 * - Permite coger objetos del mapa (habiendo previamente mirado?).
 * - Permite mirar (gracias a la función del mapa).
 * - Tiene un atributo llamado "Arma" que almacena un <it>arma</it>.
 * - Las funciones descritas en el boletín.
 */
public class Personaje
{
    private String nombre;
    /**
     * No es constante pues podría variar con distitos objetos
     */
    private int vidaMaxima = 100;
    private int puntosVida;
    private Mochila mochila;
    private Arma arma;
    private Armadura armadura;

    /**
     * Único constructor. Permite crear un nuevo personaje con armas por defecto.
     * @param nombre
     */
    public Personaje(String nombre)
    {
        this.nombre = nombre;
        //TODO: inicializar correctamente estos constructores
        arma = new Arma();
        armadura = new Armadura();
        mochila = new Mochila();
        puntosVida = 100;
    }

    //TODO: evitar aliasing nombre = new Arma(nombre)

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getVidaMaxima()
    {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima)
    {
        this.vidaMaxima = vidaMaxima;
    }

    public int getPuntosVida()
    {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida)
    {
        this.puntosVida = puntosVida;
    }

    public Mochila getMochila()
    {
        return mochila;
    }

    public void setMochila(Mochila mochila)
    {
        this.mochila = mochila;
    }

    public Arma getArma()
    {
        return arma;
    }

    public void setArma(Arma arma)
    {
        this.arma = arma;
    }

    public Armadura getArmadura()
    {
        return armadura;
    }

    public void setArmadura(Armadura armadura)
    {
        this.armadura = armadura;
    }
}
