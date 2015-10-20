package Juego;

import Objetos.*;

/**
 * Clase personaje. Es la clase que se encarga del personaje encarnado por el jugador.
 * Almacena:
 * <ul>
 *     <li>puntosVida: puntos de vida del personaje</li>
 *     <li>armadura: reducción de daño recibido por el personaje (calculado como daño = ataque - armadura)</li>
 *     <li>celda: objetos disponibles en la ubicación actual del personaje</li>
 *     <li>mochila: objetos en el inventario del personaje</li>
 *     <li>rangoVision: maximo de casillas visibles por el personaje</li>
 *     <li>energía: energía máxima para realizar operaciones</li> //TODO: avisar
 * </ul>
 * Posibles operaciones (metodos):
 * <ul>
 *     <li>atacar(Enemigo): ataca al enemigo pasado por parámetro</li>
 *     <li>mover(mapa,fila,columna)?: permite mover al personaje sobre el mapa cambiando la celda del personaje.. <i>en diagonal también</i></li>
 *     //TODO: meterlo en el mapa? (no mucho sentido).
 *     <li>cogerBotiquin(): coge un botiquín de la posición actual si lo hay</li>
 *     <li>cogerBinoculares(): coge un botiquín de la posición actual si lo hay</li>
 *     <li>mirar(Mapa): imprime las celdas adyacentes a la posición actual del personaje (<i>se llama a un método de mapa que permite imprimir los objetos de las celdas adyacentes
 *     a una dada y según un rango de visión</i></li>
 *     //TODO: añadir identificador a la celda (para distingrirla una de otra).
 *     <li>ojearInvetario(): recorre mochila imprimiendo todos sus contenidos</li>
 *     <li>tirarObjeto(Binocular|Botiquin): permite depositar un objeto en la celda actual</li>
 *     //TODO: añadir un identificador o clave (p.ej, puntos de restauracion de salud) para saber que objeto tirar.
 *
 * </ul>
 */

public class Personaje
{
    private final int MAXIMO_VIDA = 100;

    private int puntosVida;
    private int armadura;
    private Celda celda;
    private Mochila mochila;
    private int rangoVision;

    public int getPuntosVida()
    {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida)
    {
        if(this.puntosVida + puntosVida > MAXIMO_VIDA)
        {
            this.puntosVida = MAXIMO_VIDA;
        }
        else
        {
            this.puntosVida = puntosVida + MAXIMO_VIDA;
        }
    }

    public int getArmadura()
    {
        return armadura;
    }

    public void setArmadura(int armadura)
    {
        this.armadura = armadura;
    }

    public Celda getCelda()
    {
        return celda;
    }

    public void setCelda(Celda celda)
    {
        this.celda = celda;
    }

    public Mochila getMochila()
    {
        return mochila;
    }

    public void setMochila(Mochila mochila)
    {
        this.mochila = mochila;
    }

    public int getRangoVision()
    {
        return rangoVision;
    }

    public void setRangoVision(int rangoVision)
    {
        if(rangoVision > 0)
        {
            this.rangoVision = rangoVision;
        }
    }
}
