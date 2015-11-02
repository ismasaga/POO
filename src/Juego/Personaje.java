package Juego;

import Objetos.*;

import java.util.ArrayList;
import java.util.Random;

/**Resumen. A destacar:
 * puntosAtaque: representa el arma del personaje, sin nombre (no hace falta) y con una cantidad de puntos fija y
 * predeterminada por el constructor.
 *
 * armadura: representa la armadura del persoaje, sin nombre (no hace falta) y con una cantidad de puntos fija y
 * predeterminada por el constructor. La armadura disminuye el daño recibido (dañoRecb - armadura).
 *
 * MAXIMO_VIDA y MAXIMO_ENERGIA siguen la misma lógica que los dos atributos anteriores.
 *
 * El getter getCelda devuelve directamente la celda, pues se usa aliasing para modificarla desde otras clases (ídem setter).
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
    private final int armadura;
    private Celda celda;
    private Mochila mochila;
    private String nombre;
    private int rangoVision; /**Casillas visibles por el personaje**/
    private final int ataque;
    private int energia; /**Energia actual**/


    public Personaje(int MAXIMO_VIDA,int puntosVida,int armadura,Celda celda,Mochila mochila,int rangoVision,int ataque,int energia,int MAXIMO_ENERGIA,String nombre) {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0? MAXIMO_VIDA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= this.MAXIMO_VIDA)? puntosVida : this.MAXIMO_VIDA;
        this.armadura = armadura > 0 ? armadura : 5;
        this.celda = celda;
        this.mochila = mochila;
        this.rangoVision = (rangoVision > 0)? rangoVision : 2;
        this.ataque = (ataque > 0) ? ataque : 0;
        this.MAXIMO_ENERGIA  = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        this.energia = (energia > 0 && energia <= this.MAXIMO_ENERGIA) ? energia : 100;
        setNombre(nombre);
    }

    public Personaje(int MAXIMO_VIDA,int puntosVida,int armadura,Celda celda,Mochila mochila,int rangoVision,int ataque,int energia,int MAXIMO_ENERGIA) {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0? MAXIMO_VIDA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= this.MAXIMO_VIDA)? puntosVida : this.MAXIMO_VIDA;
        this.armadura = armadura > 0 ? armadura : 5;
        this.celda = celda;
        this.mochila = mochila;
        this.rangoVision = (rangoVision > 0)? rangoVision : 2;
        this.ataque = (ataque > 0) ? ataque : 0;
        this.MAXIMO_ENERGIA  = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        this.energia = (energia > 0 && energia <= this.MAXIMO_ENERGIA) ? energia : 100;
        setNombre("The Punisher");
    }

    /**
     * Asigna el nombre al personaje
     * @param nombre
     */
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            System.out.println("ERROR asignando el nombre al personaje");
    }

    public int getMAXIMO_VIDA()
    {
        return MAXIMO_VIDA;
    }

    public int getMAXIMO_ENERGIA()
    {
        return MAXIMO_ENERGIA;
    }

    /**
     * Retorna el nombre del personaje
     * @return nombre
     */
    public String getNombre() {
        return nombre;
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

    public int getAtaque()
    {
        return ataque;
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
     * @param dir
     */
    public void atacar(Mapa mapa, char dir)
    {
        /**
         * Energia requerida para atacar
         */
        final int ENERGIA_REQUERIDA = 20;
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
        if(dir == 'u' && i-1 >= 0 && mapa.getCelda(i-1,j).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i - 1, j);
            enemigo = celdaObtenida.getEnemigo();
        }
        else if(dir == 'd' && i+1 < mapa.getAlto() && mapa.getCelda(i+1,j).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i + 1, j);
            enemigo = celdaObtenida.getEnemigo();
        }
        else if(dir == 'l' && j-1 >= 0 && mapa.getCelda(i,j-1).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i, j - 1);
            enemigo = celdaObtenida.getEnemigo();
        }
        else if(dir == 'r' && j+1 < mapa.getAncho() && mapa.getCelda(i,j+1).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i, j + 1);
            enemigo = celdaObtenida.getEnemigo();
        }
        else
        {
            //Si el enemigo no existe se sale de la ejecucion
            System.out.println("El enemigo no existe");
            //Retornamos la energia
            this.setEnergia(this.getEnergia() + ENERGIA_REQUERIDA);
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
        System.out.println("El enemigo "+enemigo.getNombre()+" ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getPuntosVida());
        if(enemigo.getPuntosVida() <= 0) {
            System.out.println("El enemigo "+enemigo.getNombre()+" ha sido abatido.");
            celdaObtenida.eliminarEnemigo(enemigo);
            if(!mapa.moreEnemies()) {
                System.out.println("No hay mas enemigos, enhorabuena, has ganado la partida");
                System.exit(0);
            }
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
     * Esta implementación de mover permite desplazar al personaje a cualquier casilla de manera horizontal y vertical
     * (menos a las no transitables). Sin embargo, esta función es llamada con num = 1 por lo que sólo permite mover a
     * las casillas adyacentes.
     *
     * Se ha decantado por un modelo más simple que el propuesto de ejemplo de introducir el comando mover
     * (y atacar, que sigue el mismo patrón). En lugar de numerar a las filas por un número y a las columnas por una
     * letra (o viceversa), se indica si se desea mover arriba, abajo, izquierda o derecha. Esto evita un problema:
     * <ul>
     *     <li>Si tenemos un mapa muy grande (pongamos por caso 1000x1000), la numeración de las casillas sería engorrosa.
     *     (¿mover 756zrt?), esto implicaría numerar las casiilas  a priori y mostrar dicho número/letras al usuario.</li>
     * </ul>
     *
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

        /**Si se realiza un movimiento incorrecto hay que rellenar la energia**/

        if((this.getEnergia() - ENERGIA_REQUERIDA * num) < 0)
        {
            System.out.println("No hay suficiente energia para mover tantas casillas.");
            return;
        }
        else
            this.setEnergia((this.getEnergia() - ENERGIA_REQUERIDA * num));

        if(dir == 'u' && i-num >= 0)
            if(mapa.getCelda(i-num,j).isTransitable())
                celda = mapa.getCelda(i-num,j);
            else
            {
                System.out.println("ERROR, la celda a la que te pretendes mover no es transitable.");
                this.setEnergia((this.getEnergia() + ENERGIA_REQUERIDA * num));
            }
        else if(dir == 'd' && i+num < mapa.getAlto())
            if(mapa.getCelda(i+num,j).isTransitable())
                celda = mapa.getCelda(i+num,j);
            else
            {
                System.out.println("ERROR, la celda a la que te pretendes mover no es transitable.");
                this.setEnergia((this.getEnergia() + ENERGIA_REQUERIDA * num));
            }
        else if(dir == 'l' && j-num >= 0)
            if(mapa.getCelda(i,j-num).isTransitable())
                celda = mapa.getCelda(i,j-num);
            else
            {
                System.out.println("ERROR, la celda a la que te pretendes mover no es transitable.");
                this.setEnergia((this.getEnergia() + ENERGIA_REQUERIDA * num));
            }
        else if(dir == 'r' && j+num < mapa.getAncho())
            if(mapa.getCelda(i,j+num).isTransitable())
                celda = mapa.getCelda(i,j+num);
            else
            {
                System.out.println("ERROR, la celda a la que te pretendes mover no es transitable.");
                this.setEnergia((this.getEnergia() + ENERGIA_REQUERIDA * num));
            }
        else
        {
            System.out.println("ERROR, no puedes mover tantas casillas en esa dirección");
            this.setEnergia((this.getEnergia() + ENERGIA_REQUERIDA * num));
        }
    }

    public void mirar() {
        ArrayList<Binoculares> arrayBin = celda.getBinoculares();
        ArrayList<Botiquin> arrayBot = celda.getBotiquin();

        //System.out.println(arrayBin.isEmpty()+","+arrayBot.isEmpty());
        if (!arrayBin.isEmpty() || !arrayBot.isEmpty()) {
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
        } else
            System.out.println("No hay objetos en esta casilla");
        if (this.celda.getEnemigo() != null) {
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
