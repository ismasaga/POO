package Juego;

import Objetos.*;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
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
    //private final int armadura;
    private Celda celda;
    private Mochila mochila;
    private String nombre;
    private int rangoVision; /**Casillas visibles por el personaje**/
    //private final int ataque;
    private int energia; /**Energia actual**/
    private Arma armaDer;
    private Arma armaIzq;
    private Arma armaDosM;
    private Armadura armadura;


    public Personaje(int MAXIMO_VIDA,int puntosVida,Armadura armadura,Celda celda,Mochila mochila,int rangoVision,Arma armaIzq,Arma armaDer,int energia,int MAXIMO_ENERGIA,String nombre) {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0? MAXIMO_VIDA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= this.MAXIMO_VIDA)? puntosVida : this.MAXIMO_VIDA;
        setArmadura(armadura);
        this.celda = celda;
        this.mochila = mochila;
        this.rangoVision = (rangoVision > 0)? rangoVision : 2;
        setArmaDer(armaDer);
        setArmaIzq(armaIzq);
        this.MAXIMO_ENERGIA  = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        this.energia = (energia > 0 && energia <= this.MAXIMO_ENERGIA) ? energia : 100;
        setNombre(nombre);
    }

    public Personaje(int MAXIMO_VIDA,int puntosVida,Armadura armadura,Celda celda,Mochila mochila,int rangoVision,ArrayList<Arma> armas,int energia,int MAXIMO_ENERGIA,String nombre) {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0? MAXIMO_VIDA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= this.MAXIMO_VIDA)? puntosVida : this.MAXIMO_VIDA;
        setArmadura(armadura);
        this.celda = celda;
        this.mochila = mochila;
        this.rangoVision = (rangoVision > 0)? rangoVision : 2;
        setArmas(armas);
        this.MAXIMO_ENERGIA  = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        this.energia = (energia > 0 && energia <= this.MAXIMO_ENERGIA) ? energia : 100;
        setNombre(nombre);
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

    /**
     * Devuelve un entero con el ataque total del personaje tenga las armas que tenga
     * @return ataque
     */
    public int getAtaque() {
        ArrayList<Arma> armas = getArmas();
        int ataque = 0;
        if(armas.size() > 0 && armas.size() <= 2)
            for(Arma arma : armas)
                ataque += arma.getDano();
        else
            System.out.println("ERROR, no hay armas con las que atacar.");
        return ataque;
    }


    /**
     * Devuelve la armadura, en caso de que no este equipada devolverá null
     * @return Armadura
     */
    public Armadura getArmadura() {
        return armadura;
    }

    /**
     * Asigna armadura al personaje
     * @param armadura
     */
    public void setArmadura(Armadura armadura) {
        if(armadura != null)
            this.armadura = armadura;
        else
            System.out.println("ERROR asignando armadura al personaje");
    }

    /**
     * Devuelve un arraylist de las armas que lleva equipadas el personaje
     * ATENCION : si no lleva ninguna devuelve el conjunto pero vacio
     * @return conjuntoArmas
     */
    /* Este metodo es muerte. Si quieres desequipar el arma de mano izquierda no se puede.*/
    public ArrayList<Arma> getArmas() {
        ArrayList<Arma> conjuntoArmas = new ArrayList<>();
        if(armaDer != null)
            conjuntoArmas.add(armaDer);
        if(armaIzq != null)
            conjuntoArmas.add(armaIzq);
        if(armaDosM != null)
            conjuntoArmas.add(armaDosM);
        return conjuntoArmas;
    }

    public Arma getArmaDer() {
        return armaDer;
    }

    public Arma getArmaIzq() {
        return armaIzq;
    }

    public Arma getArmaDosM() {
        return armaDosM;
    }

    /**
     * Este método equipa las armas al personaje dado un array de ellas(1 de dos manos o 2 de una mano).
     * ATENCIÓN : si se usa éste método se eliminarán las armas que el personaje lla portaba
     * ATENCIÓN : si se usa para mandar solo una arma de una mano ésta quedará equipada en la mano derecha.
     * ATENCIÓN : en caso de ser dos armas a una mano la primera irá a la mano derecha.
     * @param armas
     */
    public void setArmas(ArrayList<Arma> armas) {
        if(armas != null) {
            if(armas.size() == 1) {
                if(armas.get(0).isDosManos()) {
                    armaDosM = armas.get(0);
                    armaDer = null;
                    armaIzq = null;
                } else {
                    armaDer = armas.get(0);
                    armaDosM = null;
                }
            } else if(armas.size() == 2) {
                if(armas.get(0).isDosManos() || armas.get(1).isDosManos())
                    System.out.println("ERROR, quieres equipar dos armas pero una de ellas es a dos manos(El personaje solo tiene dos manos)");
                else {
                    armaDer = armas.get(0);
                    armaIzq = armas.get(1);
                    armaDosM = null;
                }
            } else
                System.out.println("ERROR en el número de armas que quieres equipar al personaje");
        } else
            System.out.println("ERROR asignando las armas al personaje");
    }

    /**
     * Este método equipa el arma en la mano derecha
     */
    public void setArmaDer(Arma armaDer) {
        if(armaDer != null)
            if(armaDer.isDosManos())
                System.out.println("ERROR, el arma es de dos manos");
            else {
                this.armaDer = armaDer;
                this.armaDosM = null;
            }
        else
            System.out.println("ERROR en el arma que intentas equipar en la mano derecha");
    }

    /**
     * Este método equipa el arma en la mano izquierda
     */
    public void setArmaIzq(Arma armaIzq) {
        if(armaIzq != null)
            if(armaIzq.isDosManos())
                System.out.println("ERROR, el arma es de dos manos");
            else {
                this.armaIzq = armaIzq;
                this.armaDosM = null;
            }
        else
            System.out.println("ERROR en el arma que intentas equipar en la mano izquierda");
    }

    /**
     * Este método equipa el arma de dos manos
     */
    public void setArmaDosM(Arma armaDosM) {
        if(armaDosM != null)
            if(!armaDosM.isDosManos())
                System.out.println("ERROR, el arma es de una mano");
            else {
                this.armaDosM = armaDosM;
                this.armaDer = null;
                this.armaIzq = null;
            }
        else
            System.out.println("ERROR en el arma de dos manos que intentas equipar");
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
     * Ahora tambien permite atacar a un enemigo concreto
     * Ej atacar 3u Ambrosio
     * @param mapa
     * @param dir
     */
    public void atacar(Mapa mapa, char dir, String nombre)
    {
        /**
         * Energia requerida para atacar
         */
        final int ENERGIA_REQUERIDA = 20;
        ArrayList<Enemigo> enemigos = null;
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
            enemigos = celdaObtenida.getEnemigo();
        }
        else if(dir == 'd' && i+1 < mapa.getAlto() && mapa.getCelda(i+1,j).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i + 1, j);
            enemigos = celdaObtenida.getEnemigo();
        }
        else if(dir == 'l' && j-1 >= 0 && mapa.getCelda(i,j-1).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i, j - 1);
            enemigos = celdaObtenida.getEnemigo();
        }
        else if(dir == 'r' && j+1 < mapa.getAncho() && mapa.getCelda(i,j+1).getEnemigo() != null)
        {
            celdaObtenida = mapa.getCelda(i, j + 1);
            enemigos = celdaObtenida.getEnemigo();
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

        if(nombre == null) //Se ataca a todos los enemigos de la celda
        {
            for (Enemigo enemigo : enemigos)
            {
                //Ahora hay que dividir el daño del personaje entre todos los enemigos
                if (prob > 0.25) //No es crítico
                {
                    ataqueEjecutado = (getAtaque() / enemigos.size() - enemigo.getArmadura().getDefensa());
                } else //Golpe critico
                {
                    ataqueEjecutado = (2 * (getAtaque() / enemigos.size()) - enemigo.getArmadura().getDefensa());
                    System.out.println("CR1T 1N Y0U8 F4C3");
                }
                if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
                {
                    ataqueEjecutado = 0;
                }
                enemigo.setPuntosVida(enemigo.getPuntosVida() - ataqueEjecutado);
                System.out.println("El enemigo " + enemigo.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getPuntosVida());
                if (enemigo.getPuntosVida() <= 0)
                {
                    System.out.println("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                    celdaObtenida.eliminarEnemigo(enemigo);
                    if (!mapa.moreEnemies())
                    {
                        System.out.println("No hay mas enemigos, enhorabuena, has ganado la partida");
                        System.exit(0);
                    }
                }
            }
        }
        else //Se ha especificado un nombre
        {
            for (Enemigo enemigo : enemigos)
            {
                if (enemigo.getNombre().equals(nombre)) //Se ha encontrado el enemigo
                {
                    if (prob > 0.25) //No es crítico
                    {
                        ataqueEjecutado = (getAtaque() - enemigo.getArmadura().getDefensa());
                    } else //Golpe critico
                    {
                        ataqueEjecutado = (2 * (getAtaque()) - enemigo.getArmadura().getDefensa());
                        System.out.println("CR1T 1N Y0U8 F4C3");
                    }
                    if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
                    {
                        ataqueEjecutado = 0;
                    }
                    enemigo.setPuntosVida(enemigo.getPuntosVida() - ataqueEjecutado);
                    System.out.println("El enemigo " + enemigo.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getPuntosVida());
                    if (enemigo.getPuntosVida() <= 0)
                    {
                        System.out.println("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                        celdaObtenida.eliminarEnemigo(enemigo);
                        if (!mapa.moreEnemies())
                        {
                            System.out.println("No hay mas enemigos, enhorabuena, has ganado la partida");
                            System.exit(0);
                        }
                    }
                }
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
        System.out.println("Capacidad restante: " + mochila.getObjetosActuales() + " objetos");
        System.out.println("Peso restante: " + mochila.getPesoActual() + " kilogramos");
        for (Binoculares bin : arrayBin)
        {
            System.out.println("Binocular:\n");
            System.out.println("\tNombre: " + bin.getNombre());
            System.out.println("\tDescripcion " + bin.getDescripcion());
            System.out.println("\tPeso: " + bin.getPeso() + "\n");
            System.out.println("\tEspacio: " + bin.getEspacio() + "\n");
            System.out.println("\tAumento de rango de vision: " + bin.getVision() + "\n");
        }
        for (Botiquin bot : arrayBot)
        {
            System.out.println("Botiquin:\n");
            System.out.println("\tNombre: " + bot.getNombre());
            System.out.println("\tDescripcion " + bot.getDescripcion());
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
        int ENERGIA_REQUERIDA = 5;

        boolean encontrado = false;
        int j = 0,i = 0;
        for (i = 0;i < mapa.getAlto();i++) {
            for (j = 0;j < mapa.getAncho();j++) {
                encontrado = mapa.getCelda(i,j).equals(this.getCelda());
                if(encontrado)
                    break;
            }
            if(encontrado)
                break;
        }

        /**
         * If que controla que quede suficiente energia para mover tantas celdas
         */
        if((this.getEnergia() - ENERGIA_REQUERIDA * num) < 0) {
            System.out.println("No hay suficiente energia para mover tantas casillas.");
            return;
        } else
            this.setEnergia(this.getEnergia() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual()/5) * num));

        if(dir == 'u' && i-num >= 0)
            if(mapa.getCelda(i-num,j).isTransitable())
                celda = mapa.getCelda(i-num,j);
            else
            {
                System.out.println("ERROR, la celda a la que te pretendes mover no es transitable.");
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual()/5) * num));
            }
        else if(dir == 'd' && i+num < mapa.getAlto())
            if(mapa.getCelda(i+num,j).isTransitable())
                celda = mapa.getCelda(i+num,j);
            else
            {
                System.out.println("ERROR, la celda a la que te pretendes mover no es transitable.");
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual()/5) * num));
            }
        else if(dir == 'l' && j-num >= 0)
            if(mapa.getCelda(i,j-num).isTransitable())
                celda = mapa.getCelda(i,j-num);
            else
            {
                System.out.println("ERROR, la celda a la que te pretendes mover no es transitable.");
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual()/5) * num));
            }
        else if(dir == 'r' && j+num < mapa.getAncho())
            if(mapa.getCelda(i,j+num).isTransitable())
                celda = mapa.getCelda(i,j+num);
            else
            {
                System.out.println("ERROR, la celda a la que te pretendes mover no es transitable.");
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual()/5) * num));
            }
        else
        {
            System.out.println("ERROR, no puedes mover tantas casillas en esa dirección");
            this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual()/5) * num));
        }
    }

    /**
     * Mira la celda especificada por la posicion y la direccion. Si se especifica un objeto solo imprime informacion sobre ese objeto
     *
     * @param posicion Posicion a mirar. Si es null se mira la actual.
     * @param direccion Direccion a mirar.
     * @param objeto Objeto a mirar. Si es null se listan todos
     */
    //TODO: probar objeto
    public void mirar(Mapa mapa, Integer posicion, String direccion, String objeto) {
        ArrayList<Binoculares> arrayBin;
        ArrayList<Botiquin> arrayBot;
        Celda celda;
        int[] array = new int[2];
        //Si esta fuera del rango de vision
        array = mapa.posicionPersonaje(this);
        int i = array[0];
        int j = array[1];

        celda = mapa.getCelda(i,j);
        if(posicion != null)
        {
            int celdaI = direccion.equals("u") ? i - posicion : i;
            celdaI = direccion.equals("d") ? i + posicion : celdaI;
            int celdaJ = (direccion.equals("l") ? j - posicion : j);
            celdaJ = (direccion.equals("r") ? j + posicion : celdaJ);
            //If importado de mapa
            if (( celdaI >= i - getRangoVision() &&
                    celdaI <= i + getRangoVision() &&
                    celdaJ >= j - getRangoVision() &&
                    celdaJ <= j + getRangoVision()))
            {
               celda = mapa.getCelda(celdaI,celdaJ);
                System.out.println("Got celda: " + celdaI + "," + celdaJ + "!=null " + (celda!=null));
            }
        }

        arrayBin = celda.getBinoculares();
        arrayBot = celda.getBotiquin();
        System.out.println("Tamaño botiquin: " + arrayBot.size());

        //Hay una serie de ifs que controlan si se imprime un objeto.
        //Si no se proporciona objeto se imprimen todos
        //Si se proporciona solo se imprime aquel que tenga el mismo nombre
        if (!arrayBin.isEmpty() || !arrayBot.isEmpty()) {
            for (Binoculares bin : arrayBin) {
                if(objeto == null || (objeto.equals(bin.getNombre())))
                    bin.info();
            }
            for (Botiquin bot : arrayBot) {
                if(objeto == null || (objeto.equals(bot.getNombre())))
                    bot.info();
            }
        }
        if (this.celda.getEnemigo() != null) {
            ArrayList<Enemigo> enemigos = this.celda.getEnemigo();
            for(Enemigo enemigo : enemigos)
            {
                if(objeto == null || (objeto.equals(enemigo.getNombre())))
                    enemigo.info();
            }
        }
        if(this.celda.getArmaduras() != null)
        {
            ArrayList<Armadura> armaduras = this.celda.getArmaduras();
            for(Armadura armadura : armaduras)
            {
               if(objeto == null || (objeto.equals(armadura.getNombre())))
                   armadura.info();
            }
        }
        if(this.celda.getArma() != null)
        {
            ArrayList<Arma> armas = this.celda.getArma();
            for(Arma arma : armas)
            {
                if(objeto == null || (objeto.equals(arma.getNombre())))
                    arma.info();
            }
        }
    }

    public void pasar()
    {
        this.energia = MAXIMO_ENERGIA;
    }

    /**
     * Devuelve true si el personaje tiene vida <= 0
     * @return boolean
     */
    public boolean estaMuerto() {
        boolean muerto = false;
        if(getPuntosVida() <= 0)
            muerto = true;
        return muerto;
    }
}
