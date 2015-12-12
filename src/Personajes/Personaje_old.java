package Personajes;

import Excepciones.InsuficienteEnergiaException;
import Excepciones.MoverException;
import Juego.Celda;
import Juego.Mapa;
import Juego.Mochila;
import Objetos.Arma;
import Objetos.Armadura;
import Objetos.Binocular;
import Objetos.Botiquin;

import java.util.ArrayList;
import java.util.Random;

/**
 * Resumen. A destacar:
 * puntosAtaque: representa el arma del personaje, sin nombre (no hace falta) y con una cantidad de puntos fija y
 * predeterminada por el constructor.
 * <p>
 * armadura: representa la armadura del persoaje, sin nombre (no hace falta) y con una cantidad de puntos fija y
 * predeterminada por el constructor. La armadura disminuye el daño recibido (dañoRecb - armadura).
 * <p>
 * MAXIMO_VIDA y MAXIMO_ENERGIA siguen la misma lógica que los dos atributos anteriores.
 * <p>
 * El getter getCelda devuelve directamente la celda, pues se usa aliasing para modificarla desde otras clases (ídem setter).
 */

public class Personaje_old {
    /**
     * Constante de los puntos maximos de vida.
     * Esta variable se incializa en el constructor.
     */
    private int MAXIMO_VIDA;//
    private int MAXIMO_ENERGIA;//
    private int puntosVida;//
    //private final int armadura;
    private Celda celda;//
    private Mochila mochila;//
    private String nombre;//
    private int rangoVision;//
    /**
     * Casillas visibles por el personaje
     **/
    //private final int ataque;
    private float energia;//
    /**
     * Energia actual
     **/
    private Arma armaDer;//
    private Arma armaIzq;//
    private Arma armaDosM;//
    private Armadura armadura;//
    private Binocular binocular;


    public Personaje_old(int MAXIMO_VIDA, int puntosVida, Armadura armadura, Celda celda, Mochila mochila, int rangoVision, Arma armaIzq, Arma armaDer, int energia, int MAXIMO_ENERGIA, String nombre) {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0 ? MAXIMO_VIDA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= this.MAXIMO_VIDA) ? puntosVida : this.MAXIMO_VIDA;
        setArmadura(armadura);
        this.celda = celda;
        this.mochila = mochila;
        this.rangoVision = (rangoVision > 0) ? rangoVision : 2;
        setArmaDer(armaDer);
        setArmaIzq(armaIzq);
        this.MAXIMO_ENERGIA = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        this.energia = (energia > 0 && energia <= this.MAXIMO_ENERGIA) ? energia : 100;
        setNombre(nombre);
        binocular = null;
    }

    /**
     * Constructor para archivo parseado
     */
    public Personaje_old(Celda celda, String nombre, int puntosVida, int MAXIMO_ENERGIA) {
        this.MAXIMO_VIDA = puntosVida > 0 ? puntosVida : 100;
        setPuntosVida(puntosVida);
        setArmadura(new Armadura());
        setCelda(celda);
        setMochila(new Mochila(20, 5));
        setRangoVision(2);
        armaDer = armaIzq = armaDosM = null;
        this.MAXIMO_ENERGIA = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        setEnergia(this.MAXIMO_ENERGIA);
        setNombre(nombre);
        binocular = null;
    }

    public Personaje_old(int MAXIMO_VIDA, int puntosVida, Armadura armadura, Celda celda, Mochila mochila, int rangoVision, ArrayList<Arma> armas, int energia, int MAXIMO_ENERGIA, String nombre) {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0 ? MAXIMO_VIDA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= this.MAXIMO_VIDA) ? puntosVida : this.MAXIMO_VIDA;
        setArmadura(armadura);
        this.celda = celda;
        this.mochila = mochila;
        this.rangoVision = (rangoVision > 0) ? rangoVision : 2;
        setArmas(armas);
        this.MAXIMO_ENERGIA = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
        this.energia = (energia > 0 && energia <= this.MAXIMO_ENERGIA) ? energia : 100;
        setNombre(nombre);
        binocular = null;
    }

    public void setMAXIMO_VIDA(int MAXIMO_VIDA) {
        this.MAXIMO_VIDA = MAXIMO_VIDA > 0 ? MAXIMO_VIDA : 100;
    }

    public void setMAXIMO_ENERGIA(int MAXIMO_ENERGIA) {
        this.MAXIMO_ENERGIA = MAXIMO_ENERGIA > 0 ? MAXIMO_ENERGIA : 100;
    }

    public Binocular getBinocular() {
        return binocular;
    }

    public void setBinocular(Binocular binocular) {
        if(binocular != null)
            this.binocular = binocular;
    }

    /**
     * Asigna el nombre al personaje
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        if (nombre != null)
            this.nombre = nombre;
        else
            System.out.println("ERROR asignando el nombre al personaje");
    }

    public int getMAXIMO_VIDA() {
        return MAXIMO_VIDA;
    }

    public int getMAXIMO_ENERGIA() {
        return MAXIMO_ENERGIA;
    }

    /**
     * Retorna el nombre del personaje
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    public float getEnergia() {
        return energia;
    }

    public void setEnergia(float energia) {
        if (energia < 0) {
            this.energia = 0;
        }
        if (energia > MAXIMO_ENERGIA) {
            this.energia = MAXIMO_ENERGIA;
        } else {
            this.energia = energia;
        }
    }

    /**
     * Devuelve un entero con el ataque total del personaje tenga las armas que tenga
     *
     * @return ataque
     */
    public int getAtaque() {
        ArrayList<Arma> armas = getArmas();
        int ataque = 0;
        if (armas.size() > 0 && armas.size() <= 2)
            for (Arma arma : armas)
                ataque += arma.getDano();
        else
            System.out.println("ERROR, no hay armas con las que atacar.");
        return ataque;
    }


    /**
     * Devuelve la armadura, en caso de que no este equipada devolverá null
     *
     * @return Armadura
     */
    public Armadura getArmadura() {
        return armadura;
    }

    /**
     * Asigna armadura al personaje
     *
     * @param armadura
     */
    public void setArmadura(Armadura armadura) {
        if (armadura != null) {
            desequiparArmadura();
            setMAXIMO_ENERGIA(getMAXIMO_ENERGIA() + armadura.getIncrEnergia());
            setMAXIMO_VIDA(getMAXIMO_VIDA() + armadura.getIncrVida());
            this.armadura = armadura;
        } else
            System.out.println("ERROR asignando armadura al personaje");
    }

    /**
     * Devuelve un arraylist de las armas que lleva equipadas el personaje
     * ATENCION : si no lleva ninguna devuelve el conjunto pero vacio
     */
    public ArrayList<Arma> getArmas() {
        ArrayList<Arma> conjuntoArmas = new ArrayList<>();
        if (armaDer != null)
            conjuntoArmas.add(armaDer);
        if (armaIzq != null)
            conjuntoArmas.add(armaIzq);
        if (armaDosM != null)
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
     *
     * @param armas
     */
    public void setArmas(ArrayList<Arma> armas) {
        if (armas != null) {
            if (armas.size() == 1) {
                if (armas.get(0).isDosManos()) {
                    armaDosM = armas.get(0);
                    armaDer = null;
                    armaIzq = null;
                } else {
                    armaDer = armas.get(0);
                    armaDosM = null;
                }
            } else if (armas.size() == 2) {
                if (armas.get(0).isDosManos() || armas.get(1).isDosManos())
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
        if (armaDer != null)
            if (armaDer.isDosManos())
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
        if (armaIzq != null)
            if (armaIzq.isDosManos())
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
        if (armaDosM != null)
            if (!armaDosM.isDosManos())
                System.out.println("ERROR, el arma es de una mano");
            else {
                this.armaDosM = armaDosM;
                this.armaDer = null;
                this.armaIzq = null;
            }
        else
            System.out.println("ERROR en el arma de dos manos que intentas equipar");
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    /**
     * Asigna los puntos de vida.
     *
     * @param puntosVida
     */
    public void setPuntosVida(int puntosVida) {
        if (puntosVida > MAXIMO_VIDA) {
            this.puntosVida = MAXIMO_VIDA;
            return;
        } else if (puntosVida < 0) {
            this.puntosVida = 0;
            return;
        } else {
            this.puntosVida = puntosVida;
            return;
        }
    }

    public Celda getCelda() {
        return celda;
    }

    /**
     * Asigna unha celda á persoaxe
     *
     * @param celda
     */
    public void setCelda(Celda celda) {
        if (celda != null)
            this.celda = celda;
        else
            System.out.println("ERROR asignando celda al personaje");
    }

    public Mochila getMochila() {
        return mochila;
    }

    /**
     * Asigna unha mochila á persoaxe
     *
     * @param mochila
     */
    public void setMochila(Mochila mochila) {
        if (mochila != null)
            this.mochila = mochila;
        else
            System.out.println("ERROR asignando la mochila al personaje");
    }

    public int getRangoVision() {
        return rangoVision;
    }

    public void setRangoVision(int rangoVision) {
        if (rangoVision > 0) {
            this.rangoVision = rangoVision;
        }
    }


    /**
     * Esta funcion sigue la misma estructura que mover. Dado un numero y direccion, ataca a un enemigo si existe (!= null)
     * Ej atacar 3u
     * Ahora tambien permite atacar a un enemigo concreto
     * Ej atacar 3u Ambrosio
     * Para dejar una direccion sin aplicar pasar como parametro 'q' (deja la posicion del personaje).
     *
     * @param mapa  Mapa a atacar
     * @param numX: numero de casillas a atacar (ejeX)
     * @param numY: numero de casillas a atacar (ejeY)
     * @param dirX: Direccion de ataque en el eje de las X.
     * @param dirY: direccion de ataque en el eje de las Y
     */
    //FIXME: bug
    public void atacar(Mapa mapa, int numX, int numY, char dirX, char dirY, String nombre) {
        final int ENERGIA_REQUERIDA = 20; //Energia requerida para atacar
        ArrayList<Enemigo> enemigos = null;
        boolean atacado = false, encontrado = false;
        int j = 0, i, componenteI = 0, componenteJ = 0;
        Celda celdaObtenida;

        /*Obtenemos la posicion actual del personaje*/
        for (i = 0; i < mapa.getAlto(); i++) {
            for (j = 0; j < mapa.getAncho(); j++)
                if(mapa.getCelda(i, j) == this.getCelda()) {
                    encontrado = true;
                    break;
                }
            if (encontrado)
                break;
        }

        /*No hay suficiente energia*/
        if (this.getEnergia() - ENERGIA_REQUERIDA < 0) {
            System.out.println("No hay suficiente energia");
            return;
        } else if(!encontrado) {
            System.out.println("No existe la celda que quieres atacar");
            return;
        }
        /*Hay energia*/
        else {
            /* Miro se o enemigo e visible */
            if(numX > getRangoVision() || numY > getRangoVision()) {
                System.out.println("No tienes suficiente rango de vision");
                return;
            } else {

                /* Miro se teño armas */
                if(getArmas().isEmpty()) {
                    System.out.println("No tienes armas con las que atacar!!");
                    return;
                } else {
                    /*
                    boolean alcanza = false;
                    // Miro se algunha arma das equipadas alcanza o enemigo
                    for(Arma arma : getArmas()) {
                        if(arma.getAlcance() <= numX && arma.getAlcance() <= numY) {
                            alcanza = true;
                            break;
                        }
                    }
                    if(!alcanza){
                        System.out.println("No tienes suficiente rango de ataque.");
                        return;
                    }
                    // Comprobo que as direccions existan
                    if (!((dirX == 'q' || dirX == 'r' || dirX == 'l') && (dirY == 'u' || dirY == 'd' || dirY == 'q'))) {
                        System.out.println("Has introducido mal la dirección, consulta la ayuda");
                        return;
                    }
                    */

                    /* Calculo componentes da celda a atacar */
                    if (dirY == 'q')
                        componenteI = i;
                    if (dirX == 'q')
                        componenteJ = j;
                    if (dirY == 'u' && i - numY >= 0)
                        componenteI = i - numY;
                    if (dirY == 'd' && i + numY < mapa.getAlto())
                        componenteI = i + numY;
                    if (dirX == 'l' && j - numX >= 0)
                        componenteJ = j - numX;
                    if (dirX == 'r' && j + numX < mapa.getAncho())
                        componenteJ = j + numX;
                }
            }
        }

        /**Buscamos el maximo de casillas atacables (eje x)**/
        int minX = numX;
        if (minX > getRangoVision()) {
            System.out.println("No tienes suficiente rango de vision");
            minX = getRangoVision();
            return;
        }
        if (armaDer != null) {
            if (minX > armaDer.getAlcance()) {
                minX = armaDer.getAlcance();
                System.out.println("Tu arma derecha no tiene suficiente alcance.");
                return;
            }

        }
        if (armaIzq != null) {
            if (minX > armaIzq.getAlcance()) {
                minX = armaIzq.getAlcance();
                System.out.println("Tu arma izquierda no tiene suficiente alcance.");
                return;
            }
        }
        if (armaDosM != null) {
            if (minX > armaDosM.getAlcance()) {
                minX = armaDosM.getAlcance();
                System.out.println("Tu arma de dos manos no tiene suficiente alcance.");
                return;
            }
        }

        /**Buscamos el maximo de casillas atacables (eje y)**/
        int minY = numY;
        if (minY > getRangoVision()) {
            System.out.println("No tienes suficiente rango de vision");
            return;
        }
        if (armaDer != null) {
            if (minY > armaDer.getAlcance()) {
                minY = armaDer.getAlcance();
                System.out.println("Tu arma derecha no tiene suficiente alcance.");
                return;
            }

        }
        if (armaIzq != null) {
            if (minY > armaIzq.getAlcance()) {
                minY = armaIzq.getAlcance();
                System.out.println("Tu arma izquierda no tiene suficiente alcance.");
                return;
            }
        }
        if (armaDosM != null) {
            if (minY > armaDosM.getAlcance()) {
                minY = armaDosM.getAlcance();
                System.out.println("Tu arma no tiene suficiente alcance.");
                return;
            }
        }

        /*if (!((dirX == 'q' || dirX == 'r' || dirX == 'l') && (dirY == 'u' || dirY == 'd' || dirY == 'q'))) {
            //Si las direcciones estan mal pues se sale del programa
            System.out.println("Error atacando. Melon");
            //Retornamos la energia
            this.setEnergia(this.getEnergia() + ENERGIA_REQUERIDA);
            return;
        }*/

        System.out.println("ComponenteI " + componenteI + "ComponenteJ" + componenteJ);
        celdaObtenida = mapa.getCelda(componenteI, componenteJ);
        enemigos = celdaObtenida.getEnemigo();
        if (enemigos == null) {
            System.out.println("No hay un enemigo en esa celda.");
            this.setEnergia(this.getEnergia() + ENERGIA_REQUERIDA);
            return;
        }

        this.setEnergia(this.getEnergia() - ENERGIA_REQUERIDA);
        /*
        celdaObtenida = mapa.getCelda(numY, numX);
        enemigos = celdaObtenida.getEnemigo();
        if (enemigos == null) {
            System.out.println("No hay un enemigo en esa celda.");
            this.setEnergia(this.getEnergia() + ENERGIA_REQUERIDA);
            return;
        }
        */

        int coeficienteAtaque; //Previene que se sume vida al atacar
        Random random = new Random();

        float prob = random.nextFloat();
        int ataqueEjecutado;

        if (nombre == null) //Se ataca a todos los enemigos de la celda
        {
            ArrayList<Enemigo> enemigosAbatidos = new ArrayList<>();
            for (Enemigo enemigo : enemigos) {
                System.out.println("Tamaño enemigos: " + enemigos.size());
                //Ahora hay que dividir el daño del personaje entre todos los enemigos
                if (prob > 0.25) //No es crítico
                {
                    ataqueEjecutado = (getAtaque() / enemigos.size()) * 20 / enemigo.getArmadura().getDefensa();
                } else //Golpe critico
                {
                    ataqueEjecutado = (2 * (getAtaque() / enemigos.size())) * 20 / enemigo.getArmadura().getDefensa();
                    System.out.println("CR1T 1N Y0U8 F4C3");
                }
                if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
                {
                    ataqueEjecutado = 0;
                }
                //enemigo.setPuntosVida(enemigo.getPuntosVida() - ataqueEjecutado);
                System.out.println("El enemigo " + enemigo.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getVidaActual());
                if (enemigo.getVidaActual() <= 0) {
                    System.out.println("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                    enemigosAbatidos.add(enemigo);
                }
            }
            for (Enemigo enemigo : enemigosAbatidos) {
                enemigo.soltarObjetos(celdaObtenida);
                celdaObtenida.eliminarEnemigo(enemigo);
            }
            if (!mapa.moreEnemies()) {
                System.out.println("No hay mas enemigos, enhorabuena, has ganado la partida");
                System.exit(0);
            }
        } else //Se ha especificado un nombre
        {
            for (Enemigo enemigo : enemigos) {
                if (enemigo.getNombre().equals(nombre)) //Se ha encontrado el enemigo
                {
                    if (prob > 0.25) //No es crítico
                    {
                        ataqueEjecutado = (getAtaque() * 20 / enemigo.getArmadura().getDefensa());
                    } else //Golpe critico
                    {
                        ataqueEjecutado = (2 * (getAtaque()) * 20 / enemigo.getArmadura().getDefensa());
                        System.out.println("CR1T 1N Y0U8 F4C3");
                    }
                    if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
                    {
                        ataqueEjecutado = 0;
                    }
                    enemigo.setVidaActual(enemigo.getVidaActual() - ataqueEjecutado);
                    System.out.println("El enemigo " + enemigo.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getVidaActual());
                    if (enemigo.getVidaActual() <= 0) {
                        System.out.println("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                        enemigo.soltarObjetos(celdaObtenida);
                        celdaObtenida.eliminarEnemigo(enemigo);
                        if (!mapa.moreEnemies()) {
                            System.out.println("No hay mas enemigos, enhorabuena, has ganado la partida");
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }

    /*
    public void tirarBinocular(String nombreBinocular) {
        Binocular binocularATirar = null;
        if (celda.getBinoculares() == null)
            return;
        for (Binocular bin : mochila.getArrayBinoculares()) {
            if (bin.getNombre().equals(nombreBinocular)) {
                binocularATirar = bin;
            }
        }
        if (binocularATirar != null) {
            celda.setBinoculares(binocularATirar);
            mochila.quitarBinocular(binocularATirar);
        }
    }

    public void tirarBotiquin(String nombreBotiquin) {
        Botiquin botiquinATirar = null;
        if (celda.getBotiquin() == null)
            return;
        for (Botiquin bot : mochila.getArrayBotiquin()) {
            if (bot.getNombre().equals(nombreBotiquin)) {
                botiquinATirar = bot;
            }
        }
        if (botiquinATirar != null) {
            celda.setBotiquin(botiquinATirar);
            mochila.quitarBotiquin(botiquinATirar);
        }
    }

    public void cogerBotiquin(String nombreBotiquin) {
        Botiquin botiquinACoger = null;
        if (celda.getBotiquin() == null)
            return;
        for (Botiquin bot : celda.getBotiquin()) {
            if (bot.getNombre().equals(nombreBotiquin)) {
                botiquinACoger = bot;
            }
        }
        if (botiquinACoger != null) {
            if(mochila.anadirBotiquin(botiquinACoger))
                celda.eliminarBotiquin(botiquinACoger);
        }
    }

    public void cogerArma(String nombreArma) {
        Arma armaACoger = null; //Hay que hacerlo asi que da una excepcion
        if (celda.getArma() == null)
            return;
        for (Arma arma : celda.getArma()) {
            if (arma.getNombre().equals(nombreArma)) {
                armaACoger = arma;
            }
        }
        if (armaACoger != null) {
            if(mochila.anadirArma(armaACoger))
                celda.eliminarArma(armaACoger);
        }
    }

    public void tirarArma(String nombreArma) {
        Arma armaATirar = null;
        for (Arma arma : mochila.getArrayArmas()) {
            if (arma.getNombre().equals(nombreArma)) {
                armaATirar = arma;
            }
        }
        if (armaATirar != null) {
            mochila.quitarArma(armaATirar);
            celda.setArma(armaATirar);
        }
    }

    public void cogerArmadura(String nombreArmadura) {
        Armadura armaduraACoger = null;
        if (celda.getArmaduras() == null)
            return;
        for (Armadura armadura : celda.getArmaduras()) {
            if (armadura.getNombre().equals(nombreArmadura)) {
                armaduraACoger = armadura;
            }
        }
        if (armaduraACoger != null) {
            if(mochila.anadirArmadura(armaduraACoger))
                celda.eliminarArmadura(armaduraACoger);
        }
    }

    public void tirarArmadura(String nombreArmadura) {
        Armadura armaduraATirar = null;
        for (Armadura armadura : mochila.getArrayArmaduras()) {
            if (armadura.getNombre().equals(nombreArmadura)) {
                armaduraATirar = armadura;
            }
        }
        if (armaduraATirar != null) {
            mochila.quitarArmadura(armaduraATirar);
            celda.setArmadura(armaduraATirar);
        }
    }


*/
    public void ojearInventario() {
        ArrayList<Binocular> arrayBin = mochila.getArrayBinoculares();
        ArrayList<Botiquin> arrayBot = mochila.getArrayBotiquin();
        System.out.println("Capacidad restante: " + (mochila.getObjetosMaximos() - mochila.getObjetosActuales()) + " objetos");
        System.out.println("Peso actual: " + mochila.getPesoActual() + " kilogramos (Max : "+getMochila().getPesoMaximo()+")");
        for (Binocular bin : arrayBin) {
            if (bin != null)
                bin.info();
            else
                System.out.println("No hay botiquines en la mochila.");
        }
        for (Botiquin bot : arrayBot) {
            if (bot != null)
                bot.info();
            else
                System.out.println("No hay botiquines en la mochila.");
        }
        for (Arma arma : mochila.getArrayArmas()) {
            if (arma != null)
                arma.info();
            else
                System.out.println("No hay armas en la mochila.");
        }
        for (Armadura armadura : mochila.getArrayArmaduras()) {
            if (armadura != null)
                armadura.info();
            else
                System.out.println("No hay armaduras en la mochila.");
        }
    }

    /**
     * Esta implementación de mover permite desplazar al personaje a cualquier casilla de manera horizontal y vertical
     * (menos a las no transitables). Sin embargo, esta función es llamada con num = 1 por lo que sólo permite mover a
     * las casillas adyacentes.
     * <p>
     * Se ha decantado por un modelo más simple que el propuesto de ejemplo de introducir el comando mover
     * (y atacar, que sigue el mismo patrón). En lugar de numerar a las filas por un número y a las columnas por una
     * letra (o viceversa), se indica si se desea mover arriba, abajo, izquierda o derecha. Esto evita un problema:
     * <ul>
     * <li>Si tenemos un mapa muy grande (pongamos por caso 1000x1000), la numeración de las casillas sería engorrosa.
     * (¿mover 756zrt?), esto implicaría numerar las casiilas  a priori y mostrar dicho número/letras al usuario.</li>
     * </ul>
     * <p>
     * Si el personaje no es encontrado(cosa teóricamente imposible) mete el personaje en la casilla
     * 0,0 con el movimiento indicado en caso de que éste sea posible
     *
     * @param mapa
     * @param dir
     */
    public void mover(Mapa mapa, char dir) throws MoverException, InsuficienteEnergiaException {
        int ENERGIA_REQUERIDA = 5;

        boolean encontrado = false;
        int j = 0, i = 0;
        for (i = 0; i < mapa.getAlto(); i++) {
            for (j = 0; j < mapa.getAncho(); j++) {
                encontrado = mapa.getCelda(i, j).equals(this.getCelda());
                if (encontrado)
                    break;
            }
            if (encontrado)
                break;
        }

        /**
         * If que controla que quede suficiente energia para mover tantas celdas
         */
        if ((this.getEnergia() - ENERGIA_REQUERIDA) < 0)
            throw new InsuficienteEnergiaException("Energia insuficiente para moverse.");
        else
            this.setEnergia(this.getEnergia() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));

        if (dir == 'u' && i - 1 >= 0)
            if (mapa.getCelda(i - 1, j).isTransitable())
                celda = mapa.getCelda(i - 1, j);
            else {
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (dir == 'd' && i + 1 < mapa.getAlto())
            if (mapa.getCelda(i + 1, j).isTransitable())
                celda = mapa.getCelda(i + 1, j);
            else {
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
                throw new MoverException("La celda de abajo es intransitable.");
            }
        else if (dir == 'l' && j - 1 >= 0)
            if (mapa.getCelda(i, j - 1).isTransitable())
                celda = mapa.getCelda(i, j - 1);
            else {
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
                throw new MoverException("La celda izquierda es intransitable.");
            }
        else if (dir == 'r' && j + 1 < mapa.getAncho())
            if (mapa.getCelda(i, j + 1).isTransitable())
                celda = mapa.getCelda(i, j + 1);
            else {
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
                throw new MoverException("La celda derecha es intransitable.");
            }
        else {
            System.out.println("ERROR, no puedes mover tantas casillas en esa dirección");
            this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
        }
    }

    /*
     * Mira la celda especificada por la posicion y la direccion. Si se especifica un objeto solo imprime informacion sobre ese objeto
     *
     * @param posicionX  Posicion a mirar. Si es 0 se mira la actual. (eje x)
     * @param posicionX  Posicion a mirar. Si es 0 se mira la actual. (eje x)
     * @param direccionX Direccion a mirar. (rIGHT,lEFT)
     * @param direccionY Direccion a mirar. (uP,dOWN)
     * @param objeto     Objeto a mirar. Si es null se listan todos
     */
    /*
    public void mirar(Mapa mapa, int posicionX, int posicionY, char direccionX, char direccionY, String objeto) {
        ArrayList<Binocular> arrayBin;
        ArrayList<Botiquin> arrayBot;
        Celda celda;
        int[] array = new int[2];
        //Si esta fuera del rango de vision
        array = mapa.posicionPersonaje(this);
        int i = array[0];
        int j = array[1];

        celda = mapa.getCelda(i, j);
        int celdaI = direccionY == 'u' ? i - posicionY : i;
        celdaI = direccionY == 'd' ? i + posicionY : celdaI;
        int celdaJ = (direccionX == 'l' ? j - posicionX : j);
        celdaJ = (direccionX == 'r' ? j + posicionX : celdaJ);
        //Se necesitan los iguales porque esta en base 0
        if (celdaJ < 0 || celdaJ >= mapa.getAncho() || celdaI < 0 || celdaI >= mapa.getAlto()) {
            System.out.println("Mirar fuera de rango " + mapa.getAncho());
            return;
        }
        //If importado de mapa
        if ((celdaI >= i - getRangoVision() &&
                celdaI <= i + getRangoVision() &&
                celdaJ >= j - getRangoVision() &&
                celdaJ <= j + getRangoVision()))
            celda = mapa.getCelda(celdaI, celdaJ);

        System.out.println("Got celda: " + celdaI + "," + celdaJ + "!=null " + (celda != null));
        if (celda == null) {
            System.err.println("Segmentation fault.");
            return;
        }
        arrayBin = celda.getBinoculares();
        arrayBot = celda.getBotiquin();
        //Hay una serie de ifs que controlan si se imprime un objeto.
        //Si no se proporciona objeto se imprimen todos
        //Si se proporciona solo se imprime aquel que tenga el mismo nombre

        if (!arrayBin.isEmpty() || !arrayBot.isEmpty()) {
            for (Binocular bin : arrayBin) {
                //Si el objeto que se intenta mirar esta en la celda actual.
                if (objeto != null && (objeto.equals(bin.getNombre()) && posicionX == 0 && posicionY == 0))
                    bin.info();
                else {
                    System.out.print("Binocular: " + bin.getNombre());
                    System.out.println();
                }
            }
            for (Botiquin bot : arrayBot) {
                //Si el objeto que se intenta mirar esta en la celda actual.
                if (objeto != null && (objeto.equals(bot.getNombre()) && posicionX == 0 && posicionY == 0))
                    bot.info();
                else {
                    System.out.print("Botiquin: " + bot.getNombre());
                    System.out.println();
                }
            }
        }
        if (celda.getEnemigo() != null) {
            ArrayList<Enemigo> enemigos = celda.getEnemigo();
            for (Enemigo enemigo : enemigos) {
                if (objeto != null && (objeto.equals(enemigo.getNombre())))
                    enemigo.info();
                else {
                    System.out.print("Enemigo: " + enemigo.getNombre());
                    System.out.println();
                }

            }
        }
        if (celda.getArmaduras() != null) {
            ArrayList<Armadura> armaduras = celda.getArmaduras();
            //Si el objeto que se intenta mirar esta en la celda actual.
            for (Armadura armadura : armaduras) {
                if (objeto != null && (objeto.equals(armadura.getNombre()) && posicionX == 0 && posicionY == 0))
                    armadura.info();
                else {
                    System.out.print("Armadura: " + armadura.getNombre());
                    System.out.println();
                }
            }
        }
        if (celda.getArma() != null) {
            ArrayList<Arma> armas = celda.getArma();
            for (Arma arma : armas) {
                //Si el objeto que se intenta mirar esta en la celda actual.
                if (objeto != null && (objeto.equals(arma.getNombre()) && posicionX == 0 && posicionY == 0))
                    arma.info();
                else {
                    System.out.print("Arma: " + arma.getNombre());
                    System.out.println();
                }
            }
        }
    }

*/
    public void pasar(Mapa mapa, Personaje personaje) {
        this.energia = MAXIMO_ENERGIA;
        ArrayList<Enemigo> arrayEnemigos = new ArrayList<>();
        ArrayList<Integer[]> arrayPos = new ArrayList<>();
        for (int i = 0; i < mapa.getAlto(); i++) {
            for (int j = 0; j < mapa.getAncho(); j++) {
                Celda celda = mapa.getCelda(i, j);
                if (celda.getEnemigo() != null) {
                    for (Enemigo enemigo : celda.getEnemigo()) {
                        if (enemigo != null) {
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
        for (int i = 0; i < arrayEnemigos.size(); i++) {
            arrayEnemigos.get(i).mover(mapa, arrayPos.get(i)[0], arrayPos.get(i)[1], personaje);
        }
    }

    /**
     * Devuelve true si el personaje tiene vida <= 0
     *
     * @return boolean
     */
    public boolean estaMuerto() {
        boolean muerto = false;
        if (getPuntosVida() <= 0)
            muerto = true;
        return muerto;
    }


    public void equiparArmadura(String nombreArmadura) {
        Armadura armaduraEncontrada = null;
        for (Armadura armadura : mochila.getArrayArmaduras()) {
            if (armadura.getNombre().equals(nombreArmadura)) {
                armaduraEncontrada = armadura;
            }
        }
        if (armaduraEncontrada != null) {
            desequiparArmadura();
            if(armadura == null) { //Desequipada correctamente
                mochila.quitarArmadura(armaduraEncontrada);
                setArmadura(armaduraEncontrada);
            }
        }
    }

    /**
     * Desequipa la armadura actual del personaje
     */
    public void desequiparArmadura() {
        if (getArmadura() != null) {
            if(mochila.getObjetosMaximos() > mochila.getObjetosActuales() + getArmadura().getEspacio()
                    && mochila.getPesoMaximo() > mochila.getPesoActual() + getArmadura().getPeso()) {
                setMAXIMO_ENERGIA(getMAXIMO_ENERGIA() - getArmadura().getIncrEnergia());
                setMAXIMO_VIDA(getMAXIMO_VIDA() - getArmadura().getIncrVida());
                mochila.anadirArmadura(getArmadura());
                armadura = null; //Para indicar que no hay nada
            }
        }
    }

    /*public void desequiparBinocular() {
        if(getBinocular() != null) {
            if(mochila.getObjetosMaximos() > mochila.getObjetosActuales() + getBinocular().getEspacio()
                    && mochila.getPesoMaximo() > mochila.getPesoActual() + getBinocular().getPeso()){
                getBinocular().disipar(this);
                mochila.anadirBinocular(getBinocular());
                binocular = null; //Para indicar que no hay nada
            }
            else{
                System.out.println("No hay suficiente espacio en la mochila.");
            }

        }
    }*/

    /*public void usarBotiquin(String nombre) {
        Botiquin botiquin = mochila.getBotiquin(nombre);
        if (botiquin == null) {
            System.out.println("No hay ese botiquin");
            return;
        }
        botiquin.usar(this);
        mochila.quitarBotiquin(botiquin);
    }*/

    /*public void equiparBinocular(String nombre) {
        Binocular binocular = mochila.getBinocular(nombre);
        if (this.binocular != null && binocular != null) //Desequipa y equipa
        {
            this.binocular.disipar(this);
            desequiparBinocular();
            this.binocular = binocular;
            binocular.usar(this);
            getMochila().quitarBinocular(binocular);
        } else if(this.binocular == null && binocular != null) {
            setBinocular(binocular);
            binocular.usar(this);
            getMochila().quitarBinocular(binocular);
        }
    }*/

    public void info() {
        System.out.println("Personaje: " + getNombre());
        System.out.println("Energia maxima: " + getMAXIMO_ENERGIA());
        System.out.println("Salud maxima: " + getMAXIMO_VIDA());
        System.out.println("Energia actual: " + getEnergia());
        System.out.println("Salud actual: " + getPuntosVida());
        if (getArmaDosM() != null) {
            System.out.println("Arma equipada de dos manos: ");
            getArmaDosM().info();
        }
        if (getArmaDer() != null) {
            System.out.println("Arma equipada de mano derecha: ");
            getArmaDer().info();
        }
        if (getArmaIzq() != null) {
            System.out.println("Arma equipada de mano izquierda: ");
            getArmaIzq().info();
        }
        if (getArmadura() != null) {
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
    public void desequiparArma(String mano) {
            if (getArmaDosM() != null) {
                mochila.anadirArma(getArmas().get(0));
                armaDosM = null; //El setter no funciona
                return;
            } else if (mano.equals("derecha")) {
                if (getArmaDer() != null)
                    mochila.anadirArma(getArmaDer());
                armaDer = null;
            } else if (mano.equals("izquierda")) {
                if (getArmaIzq() != null)
                    mochila.anadirArma(getArmaIzq());
                armaIzq = null;
            } else {
                System.out.println("Mano mal escrita");
            }
    }

    /**
     * Recorre la mochila buscando un arma que coincida con el nombre
     *
     * @param nombre: nombre del arma a equipar
     * @param mano:   mano en la que equipar el arma
     */
    public void equiparArma(String nombre, String mano) {
        Arma armaEscogida = null;
        for (Arma arma : mochila.getArrayArmas()) {
            if (arma.getNombre().equals(nombre)) {
                armaEscogida = arma;
            }
        }
        if(armaEscogida != null) {
            if (armaEscogida.isDosManos()) {
                if (getArmaDosM() != null) {
                    desequiparArma("");
                } else {
                    desequiparArma("derecha");
                    desequiparArma("izquierda");
                }
                setArmaDosM(armaEscogida);
            } else {
                switch (mano) {
                    case "derecha":
                        desequiparArma("derecha");
                        setArmaDer(armaEscogida);
                        break;
                    case "izquierda":
                        desequiparArma("izquierda");
                        setArmaIzq(armaEscogida);
                        break;
                    default:
                        System.out.println("Mano mal escrita");
                        break;
                }
            }
            mochila.quitarArma(armaEscogida);
        }
    }
}