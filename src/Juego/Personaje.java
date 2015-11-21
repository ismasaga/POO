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
    private float energia; /**Energia actual**/
    private Arma armaDer;
    private Arma armaIzq;
    private Arma armaDosM;
    private Armadura armadura;
    private Binoculares binocular;


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
        binocular = null;
    }

    /**
     * Constructor para archivo parseado
     * @param celda
     * @param nombre
     * @param puntosVida
     * @param MAXIMO_ENERGIA
     */
    public Personaje(Celda celda, String nombre,int puntosVida,int MAXIMO_ENERGIA) {
        this.MAXIMO_VIDA = puntosVida > 0 ? puntosVida : 100;
        setPuntosVida(puntosVida);
        setArmadura(new Armadura());
        setCelda(celda);
        setMochila(new Mochila(20,5));
        setRangoVision(2);
        armaDer = armaIzq = armaDosM = null;
        this.MAXIMO_ENERGIA  = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        setEnergia(this.MAXIMO_ENERGIA);
        setNombre(nombre);
        binocular = null;
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
        binocular = null;
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

    public float getEnergia()
    {
        return energia;
    }

    public void setEnergia(float energia)
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

    /**
     * Asigna unha celda á persoaxe
     * @param celda
     */
    public void setCelda(Celda celda) {
        if(celda != null)
            this.celda = celda;
        else
            System.out.println("ERROR asignando celda al personaje");
    }

    public Mochila getMochila()
    {
        return mochila;
    }

    /**
     * Asigna unha mochila á persoaxe
     * @param mochila
     */
    public void setMochila(Mochila mochila) {
        if(mochila != null)
            this.mochila = mochila;
        else
            System.out.println("ERROR asignando la mochila al personaje");
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
                    enemigo.soltarObjetos(celdaObtenida);
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

    public void tirarBinocular(String nombreBinocular)
    {
        Binoculares binocularATirar = null;
        if(celda.getBinoculares() == null)
            return;
        for(Binoculares bin : mochila.getArrayBinoculares())
        {
            if(bin.getNombre().equals(nombreBinocular))
            {
                binocularATirar = bin;
            }
        }
        if(binocularATirar != null)
        {
            celda.setBinoculares(binocularATirar);
            mochila.quitarBinocular(binocularATirar);
        }
        /*Se elimina porque el binocular procede del array de binoculares*/
    }

    public void cogerBinocular(String nombreBinocular)
    {
        Binoculares binocularACoger = null;
        if(celda.getBinoculares() == null)
            return;
        for(Binoculares bin : celda.getBinoculares())
        {
            if(bin.getNombre().equals(nombreBinocular))
            {
                binocularACoger = bin;
            }
        }
        if(binocularACoger != null)
        {
            celda.eliminarBinocular(binocularACoger);
            mochila.anadirBinocular(binocularACoger);
        }
    }

    public void tirarBotiquin(String nombreBotiquin)
    {
        Botiquin botiquinATirar = null;
        if(celda.getBotiquin() == null)
            return;
        for(Botiquin bot : mochila.getArrayBotiquin())
        {
            if(bot.getNombre().equals(nombreBotiquin))
            {
                botiquinATirar = bot;
            }
        }
        if(botiquinATirar != null)
        {
            celda.setBotiquin(botiquinATirar);
            mochila.quitarBotiquin(botiquinATirar);
        }
    }

    public void cogerBotiquin(String nombreBotiquin)
    {
        Botiquin botiquinACoger = null;
        if(celda.getBotiquin() == null)
            return;
        for (Botiquin bot : celda.getBotiquin())
        {
            if(bot.getNombre().equals(nombreBotiquin))
            {
                botiquinACoger = bot;
            }
        }
        if(botiquinACoger != null)
        {
            celda.eliminarBotiquin(botiquinACoger);
            mochila.anadirBotiquin(botiquinACoger);
        }
    }

    public void cogerArma(String nombreArma)
    {
        Arma armaACoger = null; //Hay que hacerlo asi que da una excepcion
        if(celda.getArma() == null)
            return;
        for (Arma arma : celda.getArma())
        {
            if(arma.getNombre().equals(nombreArma))
            {
                armaACoger = arma;
            }
        }
        if(armaACoger != null)
        {
            celda.eliminarArma(armaACoger);
            mochila.anadirArma(armaACoger);
        }
    }

    public void tirarArma (String nombreArma)
    {
        Arma armaATirar = null;
        if(celda.getArma() == null)
            return;
        for (Arma arma: mochila.getArrayArmas())
        {
            if(arma.getNombre().equals(nombreArma))
            {
                armaATirar = arma;
            }
        }
        if (armaATirar != null)
        {
            mochila.quitarArma(armaATirar);
            celda.setArma(armaATirar);
        }
    }

    public void cogerArmadura (String nombreArmadura)
    {
        Armadura armaduraACoger = null;
        if(celda.getArmaduras() == null)
            return;
        for (Armadura armadura : celda.getArmaduras())
        {
            if(armadura.getNombre().equals(nombreArmadura))
            {
                armaduraACoger = armadura;
            }
        }
        if(armaduraACoger != null)
        {
            celda.eliminarArmadura(armadura);
            mochila.anadirArmadura(armadura);
        }
    }

    public void tirarArmadura (String nombreArmadura)
    {
        Armadura armaduraATirar = null;
        if(celda.getArmaduras() == null)
            return;
        for(Armadura armadura : celda.getArmaduras())
        {
            if(armadura.getNombre().equals(nombreArmadura))
            {
                if(armadura.getNombre().equals(nombreArmadura))
                {
                    armaduraATirar = armadura;
                }
            }
        }
        if(armaduraATirar != null)
        {
            mochila.quitarArmadura(armadura);
            celda.setArmadura(armadura);
        }
    }

    public void ojearInventario()
    {
        ArrayList<Binoculares> arrayBin = mochila.getArrayBinoculares();
        ArrayList<Botiquin> arrayBot = mochila.getArrayBotiquin();
        System.out.println("Capacidad restante: " + (mochila.getObjetosMaximos() - mochila.getObjetosActuales()) + " objetos");
        System.out.println("Peso actual: " + mochila.getPesoActual() + " kilogramos");
        for (Binoculares bin : arrayBin)
        {
            if(bin != null)
                bin.info();
            else
                System.out.println("No hay botiquines en la mochila.");
        }
        for (Botiquin bot : arrayBot)
        {
            if(bot != null)
                bot.info();
            else
                System.out.println("No hay botiquines en la mochila.");
        }
        for (Arma arma : mochila.getArrayArmas())
        {
            if(arma != null)
                arma.info();
            else
                System.out.println("No hay armas en la mochila.");
        }
        for(Armadura armadura : mochila.getArrayArmaduras())
        {
            if(armadura != null)
                armadura.info();
            else
                System.out.println("No hay armaduras en la mochila.");
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
        if (celda.getEnemigo() != null) {
            ArrayList<Enemigo> enemigos = celda.getEnemigo();
            for(Enemigo enemigo : enemigos)
            {
                if(objeto == null || (objeto.equals(enemigo.getNombre())))
                    enemigo.info();
            }
        }
        if(celda.getArmaduras() != null)
        {
            ArrayList<Armadura> armaduras = celda.getArmaduras();
            for(Armadura armadura : armaduras)
            {
               if(objeto == null || (objeto.equals(armadura.getNombre())))
                   armadura.info();
            }
        }
        if(celda.getArma() != null)
        {
            ArrayList<Arma> armas = celda.getArma();
            for(Arma arma : armas)
            {
                if(objeto == null || (objeto.equals(arma.getNombre())))
                    arma.info();
            }
        }
    }

    public void pasar(Mapa mapa,Personaje personaje)
    {
        this.energia = MAXIMO_ENERGIA;
        ArrayList<Enemigo> arrayEnemigos = new ArrayList<>();
        ArrayList<Integer[]> arrayPos = new ArrayList<>();
        for (int i = 0; i < mapa.getAlto(); i++)
        {
            for (int j = 0; j < mapa.getAncho(); j++)
            {
                Celda celda = mapa.getCelda(i,j);
                if(celda.getEnemigo() != null)
                {
                    for (Enemigo enemigo : celda.getEnemigo())
                    {
                        if(enemigo != null)
                        {
                            arrayEnemigos.add(enemigo);
                            Integer[] pos = new Integer[2];
                            pos[0] = i;
                            pos[1] = j;
                            arrayPos.add(pos);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < arrayEnemigos.size(); i++)
        {
            arrayEnemigos.get(i).mover(mapa,arrayPos.get(i)[0],arrayPos.get(i)[1],personaje);
        }
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


    public void equiparArmadura(Personaje personaje, String nombreArmadura)
    {
        for(Armadura armadura : mochila.getArrayArmaduras())
        {
            if(armadura.getNombre().equals(nombreArmadura))
            {
                mochila.anadirArmadura(armadura);
                personaje.setArmadura(personaje.getArmadura());
            }
        }
    }

    public void usarBotiquin(String nombre)
    {
        Botiquin botiquin = mochila.getBotiquin(nombre);
        if(botiquin == null)
        {
            System.out.println("No hay ese botiquin");
            return;
        }
        botiquin.usar(this);
        mochila.quitarBotiquin(botiquin);
    }

    public void equiparBinocular(Binoculares binocular)
    {
        if(this.binocular != null && binocular != null)
        {
            this.binocular.disipar(this);
            this.binocular = binocular;
            mochila.anadirBinocular(this.binocular);
            binocular.usar(this);
        }
    }

    public void info()
    {
        System.out.println("Personaje: " + getNombre());
        System.out.println("Energia actual: " + getEnergia());
        System.out.println("Salud actual: " + getPuntosVida());
        if(getArmaDosM() != null)
        {
            System.out.println("Arma equipada de dos manos: " );
            getArmaDosM().info();
        }
        if(getArmaDer() != null)
        {
            System.out.println("Arma equipada de mano derecha: ");
            getArmaDer().info();
        }
        if(getArmaIzq() != null)
        {
            System.out.println("Arma equipada de mano izquierda: ");
            getArmaIzq().info();
        }
        if(getArmadura() != null)
        {
            getArmadura().info();
        }
        System.out.println("Puntos de ataque: " + getAtaque());
        System.out.println("Rango vision: " + getRangoVision());
        System.out.println("Inventario: ");
        ojearInventario();
    }

        /**
     * Desequipa el arma de la mano seleccionada. Si el arma detectada es de dos manos se ignora la mano.
     */
    public void desequiparArma(String mano)
    {
        if(getArmas().size() > 0) {
            if (getArmaDosM() != null){
                mochila.anadirArma(getArmas().get(0));
                setArmaDosM(null);
                return;
            }
            else if (mano.equals("derecha"))
            {
                if(getArmaDer() != null)
                    mochila.anadirArma(getArmaDer());
                setArmaDer(null);
            }
            else if (mano.equals("izquierda"))
            {
                if(getArmaIzq() != null)
                    mochila.anadirArma(getArmaIzq());
                setArmaIzq(null);
            }
            else
            {
                System.out.println("Mano mal escrita");
            }
        }
    }

    /**
     * Recorre la mochila buscando un arma que coincida con el nombre
     * @param nombre: nombre del arma a equipar
     * @param mano: mano en la que equipar el arma
     */
    public void equiparArma (String nombre,String mano)
    {
        Arma armaEscogida = null;
        for(Arma arma : mochila.getArrayArmas())
        {
            if(arma.getNombre().equals(nombre))
            {
                armaEscogida = arma;
                if(arma.isDosManos())
                {
                    if(getArmaDosM() != null)
                    {
                        desequiparArma("");
                    }
                    else
                    {
                        desequiparArma("derecha");
                        desequiparArma("izquierda");
                    }
                    setArmaDosM(arma);
                }
                else
                {
                    if(mano.equals("derecha"))
                    {
                        desequiparArma("derecha");
                        setArmaDer(arma);
                    }
                    else if(mano.equals("izquierda"))
                    {
                        desequiparArma("izquierda");
                        setArmaIzq(arma);
                    }
                    else
                    {
                        System.out.println("Mano mal escrita");
                    }
                }
            }
        }
        if(armaEscogida != null)
        {
            mochila.quitarArma(armaEscogida);
        }
    }
}
