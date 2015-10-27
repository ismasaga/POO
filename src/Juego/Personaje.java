package Juego;

import Objetos.*;

import java.util.ArrayList;
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
 *     <li>cogerBotiquin(): coge un botiquín de la posición actual si lo hay</li>
 *     <li>cogerBinoculares(): coge un botiquín de la posición actual si lo hay</li>
 *     <li>mirar(Mapa): imprime la celda actual</li>
 *     <li>ojearInvetario(): recorre mochila imprimiendo todos sus contenidos</li>
 *     <li>tirarObjeto(Binocular|Botiquin): permite depositar un objeto en la celda actual</li>
 *     <li>Pasar(): acaba el turno y restaura la energia del personaje</li>
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
    private final int MAXIMO_ENERGIA;
    private int puntosVida;
    private int armadura;
    private Celda celda;
    private Mochila mochila;
    /**
     * Rango vision simboliza cuantas casillas se pueden ver aparte de la actual
     */
    private int rangoVision;
    private int ataque;
    private int energia;


    public Personaje(int MAXIMO_VIDA, int puntosVida, int armadura, Celda celda, Mochila mochila, int rangoVision,int ataque,int energia,int MAXIMO_ENERGIA)
    {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0? MAXIMO_VIDA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= this.MAXIMO_VIDA)? puntosVida : this.MAXIMO_VIDA;
        this.armadura = armadura > 0 ? armadura : 5;
        //TODO: calibrar estos valores
        this.celda = celda;
        this.mochila = mochila;
        this.rangoVision = (rangoVision > 0)? rangoVision : 2;
        this.ataque = (ataque > 0) ? ataque : 0;
        this.MAXIMO_ENERGIA  = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        this.energia = (energia > 0 && energia <= this.MAXIMO_ENERGIA) ? energia : 100;

        //TODO:
    }

    public int getEnergia()
    {
        return energia;
    }

    public void setEnergia(int energia)
    {
        if (energia < 0)
        {
            this.energia = 0;
        }
        if (energia > MAXIMO_ENERGIA)
        {
            this.energia = MAXIMO_ENERGIA;
        }
        else
        {
            this.energia = energia;
        }
    }

    public int getPuntosVida()
    {
        return puntosVida;
    }

    /**
     * Asigna los puntos de vida.
     * @param puntosVida
     */
    public void setPuntosVida(int puntosVida)
    {
        if(puntosVida > MAXIMO_VIDA)
        {
            this.puntosVida = MAXIMO_VIDA;
            return;
        }
        else if (puntosVida < 0)
        {
            this.puntosVida = 0;
            return;
        }
        else
        {
            this.puntosVida = puntosVida;
            return;
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


    /**
     * Esta funcion sigue la misma estructura que mover. Dado un numero y direccion, ataca a un enemigo si existe (!= null)
     * Ej atacar 3u
     * @param mapa
     * @param num
     * @param dir
     */
    public void atacar(Mapa mapa,int num, char dir)
    {
        /**
         * Energia requerida para atacar
         */
        final int ENERGIA_REQUERIDA = 50;
        Enemigo enemigo = null;
        /*Obtenemos la posicion actual del personaje*/
        boolean encontrado = false;
        int j = 0,i = 0;
        for (i = 0; i < mapa.getAlto(); i++) {
            for (j = 0; j < mapa.getAncho(); j++) {
                encontrado = mapa.getCelda(i,j).equals(this.getCelda());
                if(encontrado)
                    break;
            }
            if(encontrado)
                break;
        }

        /*No hay suficiente energia*/
        if(this.getEnergia() - ENERGIA_REQUERIDA < 0 )
        {
            System.out.println("No hay suficiente energia");
            return;
        }
        /*Hay energia*/
        else
        {
            this.setEnergia(this.getEnergia() - ENERGIA_REQUERIDA);
        }

        Celda celdaObtenida;
        if(dir == 'u' && i-num >= 0 && mapa.getCelda(i-num,j).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i - num, j);
            enemigo = celdaObtenida.getEnemigo();
        }
        else if(dir == 'd' && i+num < mapa.getAlto() && mapa.getCelda(i+num,j).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i + num, j);
            enemigo = celdaObtenida.getEnemigo();
        }
        else if(dir == 'l' && j-num >= 0 && mapa.getCelda(i,j-num).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i, j - num);
            enemigo = celdaObtenida.getEnemigo();
        }
        else if(dir == 'r' && j+num < 0 && mapa.getCelda(i,j+num).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i, j + num);
            enemigo = celdaObtenida.getEnemigo();
        }
        else
        {
            //Si el enemigo no existe se sale de la ejecucion
            System.out.println("El enemigo no existe");
            return;
        }
        int coeficienteAtaque; //Previene que se sume vida al atacar
        Random random = new Random();

        float prob = random.nextFloat();
        int ataqueEjecutado;
        if (prob > 0.25) //No es crítico
        {
            ataqueEjecutado = (ataque - enemigo.getArmadura());
        }
        else //Golpe critico
        {
            ataqueEjecutado = (2 * ataque - enemigo.getArmadura());
            System.out.println("CR1T 1N Y0U8 F4C3");
        }
        enemigo.setPuntosVida(enemigo.getPuntosVida() - ataqueEjecutado);
        System.out.println("El enemigo ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getPuntosVida());
        if(enemigo.getPuntosVida() <= 0)
        {
            System.out.println("El enemigo ha sido abatido.");
            celdaObtenida.setEnemigo(null);
        }
    }

    public void tirarBinocular(Binoculares binocular)
    {
        celda.setBinoculares(binocular);
        /*Se elimina porque el binocular procede del array de binoculares*/
        mochila.quitarBinocular(binocular);
    }

    public void cogerBinocular(Binoculares binocular)
    {
        celda.eliminarBinocular(binocular);
        /**El binocular procede de la celda*/
        mochila.anadirBinocular(binocular);
    }

    public void tirarBotiquin(Botiquin botiquin)
    {
        celda.setBotiquin(botiquin);
        mochila.quitarBotiquin(botiquin);
    }

    public void cogerBotiquin(Botiquin botiquin)
    {
        celda.eliminarBotiquin(botiquin);
        mochila.anadirBotiquin(botiquin);
    }

    public void ojearInventario()
    {
        ArrayList<Binoculares> arrayBin = mochila.getArrayBinoculares();
        ArrayList<Botiquin> arrayBot = mochila.getArrayBotiquin();
        System.out.println("\n\n\n\n\n\n\n\n\n");
        for (Binoculares bin : arrayBin)
        {
            System.out.println("Binocular:\n");
            System.out.println("\tPeso: " + bin.getPeso() + "\n");
            System.out.println("\tEspacio: " + bin.getEspacio() + "\n");
            System.out.println("\tAumento de rango de vision: " + bin.getVision() + "\n");
        }
        for (Botiquin bot : arrayBot)
        {
            System.out.println("Botiquin:\n");
            System.out.println("\tPeso: " + bot.getPeso() + "\n");
            System.out.println("\tEspacio: " + bot.getEspacio() + "\n");
            System.out.println("\tCuracion: " + bot.getCuracion() + "\n");
        }
    }

    /**
     * Si el personaje no es encontrado(cosa teóricamente imposible) mete el personaje en la casilla
     * 0,0 con el movimiento indicado en caso de que éste sea posible
     * @param mapa
     * @param num
     * @param dir
     */
    public void mover(Mapa mapa,int num, char dir) {
        int ENERGIA_REQUERIDA = 10;

        boolean encontrado = false;
        int j = 0,i = 0;
        for (i = 0; i < mapa.getAlto(); i++) {
            for (j = 0; j < mapa.getAncho(); j++) {
                encontrado = mapa.getCelda(i,j).equals(this.getCelda());
                if(encontrado)
                    break;
            }
            if(encontrado)
                break;
        }

        if((this.getEnergia() - ENERGIA_REQUERIDA * num) < 0)
        {
            System.out.println("No hay suficiente energia para mover tantas casillas.");
        }
        else
        {
            this.setEnergia((this.getEnergia() - ENERGIA_REQUERIDA * num));
        }

        if(dir == 'u' && i-num >= 0)
            celda = mapa.getCelda(i-num,j).isTransitable() ? mapa.getCelda(i-num,j) : mapa.getCelda(i,j);
        else if(dir == 'd' && i+num < mapa.getAlto())
            celda = mapa.getCelda(i+num,j).isTransitable() ? mapa.getCelda(i+num,j) : mapa.getCelda(i,j);
        else if(dir == 'l' && j-num >= 0)
            celda = mapa.getCelda(i,j-num).isTransitable() ? mapa.getCelda(i,j-num) : mapa.getCelda(i,j);
        else if(dir == 'r' && j+num < mapa.getAncho())
            celda = mapa.getCelda(i,j+num).isTransitable() ? mapa.getCelda(i,j+num) : mapa.getCelda(i,j);
        else
            System.out.println("ERRO, non podes mover tantas casillas nesa dirección");
    }

    public void mirar() {
        ArrayList<Binoculares> arrayBin = celda.getBinoculares();
        ArrayList<Botiquin> arrayBot = celda.getBotiquin();

        for (Binoculares bin : arrayBin) {
            System.out.println("Binocular:\n");
            System.out.println("\tPeso: " + bin.getPeso() + "\n");
            System.out.println("\tEspacio: " + bin.getEspacio() + "\n");
            System.out.println("\tAumento de rango de vision: " + bin.getVision() + "\n");
        }
        for (Botiquin bot : arrayBot) {
            System.out.println("Botiquin:\n");
            System.out.println("\tPeso: " + bot.getPeso() + "\n");
            System.out.println("\tEspacio: " + bot.getEspacio() + "\n");
            System.out.println("\tCuracion: " + bot.getCuracion() + "\n");
        }
        if (this.celda.getEnemigo() != null)
        {
            Enemigo enemigo = this.celda.getEnemigo();
            System.out.println("Enemigo: ");
            System.out.println("Puntos de vida: " + enemigo.getPuntosVida() +
                        "\nPuntos de ataque: " + enemigo.getAtaque() +
                        "\nPuntos de armadura: " + enemigo.getArmadura());
        }
    }

    public void pasar()
    {
        this.energia = MAXIMO_ENERGIA;
    }
}
