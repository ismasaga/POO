package Juego;

import Objetos.*;

import java.util.Random;

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
    /**
     * Constante de los puntos maximos de vida.
     * Esta variable se incializa en el constructor.
     */
    private final int MAXIMO_VIDA;
    private int puntosVida;
    private int armadura;
    private Celda celda;
    private Mochila mochila;
    private int rangoVision;
    private int ataque;


    public Personaje(int MAXIMO_VIDA, int puntosVida, int armadura, Celda celda, Mochila mochila, int rangoVision,int ataque)
    {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0? MAXIMO_VIDA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= this.MAXIMO_VIDA)? puntosVida : this.MAXIMO_VIDA;
        this.armadura = armadura > 0 ? armadura : 5;
        //TODO: asignar celda
        //TODO: calibrar estos valores
        mochila = new Mochila(100,10);
        this.rangoVision = (rangoVision > 0)? rangoVision : 2;
        this.ataque = (ataque > 0) ? ataque : 0;
    }

    public int getPuntosVida()
    {
        return puntosVida;
    }

    /**
     * Asigna los puntos de vida.
     * Para restar se introduce como parámetro un número negativo.
     * @param puntosVida
     */
    public void setPuntosVida(int puntosVida)
    {
        if(this.puntosVida + puntosVida > MAXIMO_VIDA)
        {
            this.puntosVida = MAXIMO_VIDA;
        }
        else if (this.puntosVida + puntosVida < 0)
        {
            this.puntosVida = 0;
        }
        else
        {
            this.puntosVida = this.puntosVida + puntosVida;
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

    //TODO: evitar aliasing
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


    public void atacar(Enemigo enemigo)
    {
        int coeficienteAtaque; //Previene que se sume vida al atacar
        Random random = new Random();
        int prob = (int) random.nextFloat();
        if(prob > 0.25) //No es crítico
            enemigo.setPuntosVida(enemigo.getPuntosVida() - (ataque - enemigo.getArmadura()));
        else //Golpe critico
            enemigo.setPuntosVida(enemigo.getPuntosVida() - (2*ataque - enemigo.getArmadura()));
    }
}
