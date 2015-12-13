package Personajes;

import Excepciones.*;
import Juego.*;
import Objetos.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * setMapa() non implementado para evita que se cambie o mapa dun persoaxe unha vez foi creado
 */

public class Personaje {
    private String nombre;
    private int vidaMaxima, vidaActual;
    private int energiaMaxima, energiaActual;
    private int rangoVision;
    private Arma armaIzq, armaDer, armaDosM;
    private Armadura armadura;
    private Mochila mochila;
    private Point punto;
    private Mapa mapa;
    private Consola consola = new ConsolaNormal();


    /**
     * Constructor para archivo parseado
     */
    public Personaje(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        setEnergiaMaxima(energiaMaxAct);
        setNombre(nombre);
        setVidaMaxima(vidaMaxAct);
        setVidaActual(vidaMaxAct);
        setEnergiaActual(energiaMaxAct);
        setRangoVision(3);              //Por defecto
        setArmaIzq(null);               //Por defecto
        setArmaDer(null);               //Por defecto
        setArmaDosM(null);              //Por defecto
        setArmadura(null);              //Por defecto
        setMochila(new Mochila(20, 5)); //Por defecto
        setPunto(punto);
        this.mapa = mapa;
    }

    public Personaje(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct, Arma armaIzq, Arma armaDer, Armadura armadura) {
        setEnergiaMaxima(energiaMaxAct);
        setNombre(nombre);
        setVidaMaxima(vidaMaxAct);
        setVidaActual(vidaMaxAct);
        setEnergiaActual(energiaMaxAct);
        setRangoVision(2);              //Por defecto
        setArmaIzq(armaIzq);               //Por defecto
        setArmaDer(armaDer);               //Por defecto
        setArmaDosM(null);              //Por defecto
        setArmadura(armadura);              //Por defecto
        setMochila(new Mochila(20, 5)); //Por defecto
        setPunto(punto);
        this.mapa = mapa;
    }

    public Personaje(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct, Arma armaDosM, Armadura armadura) {
        this(mapa,punto,nombre,vidaMaxAct,energiaMaxAct);
        setArmaDosM(armaDosM);
        setArmadura(armadura);
    }

    public Mapa getMapa() {
        return mapa;
    }

    public int getEnergiaMaxima() {
        return energiaMaxima;
    }

    public void setEnergiaMaxima(int energiaMaxima) {
        if(energiaMaxima > 0)
            this.energiaMaxima = energiaMaxima;
        else
            this.energiaMaxima = 100;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            this.nombre = "personaje";
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        if(vidaMaxima > 0)
            this.vidaMaxima = vidaMaxima;
        else
            this.vidaMaxima = 100;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        if(vidaActual > 0 && vidaActual <= getVidaMaxima())
            this.vidaActual = vidaActual;
        else if (vidaActual <= 0)
            this.vidaActual = 0;
        else
            this.vidaActual = getVidaMaxima();
    }

    public int getEnergiaActual() {
        return energiaActual;
    }

    public void setEnergiaActual(int energiaActual) {
        if(energiaActual > 0 && energiaActual <= getEnergiaMaxima())
            this.energiaActual = energiaActual;
        else if (energiaActual < 0)
            this.energiaActual = 0;
        else
            this.energiaActual = getEnergiaMaxima();
    }

    public int getRangoVision() {
        return rangoVision;
    }

    public void setRangoVision(int rangoVision) {
        if(rangoVision > 0)
            this.rangoVision = rangoVision;
        else
            this.rangoVision = 2;
    }

    /**
     * Pode devolver null para indicar que non existe
     */
    public Arma getArmaIzq() {
        return armaIzq;
    }

    /**
     * Pode ser null para indicar que non existe
     */
    public void setArmaIzq(Arma armaIzq) {
        this.armaIzq = armaIzq;
    }

    /**
     * Pode devolver null para indicar que non existe
     */
    public Arma getArmaDer() {
        return armaDer;
    }

    /**
     * Pode ser null para indicar que non existe
     */
    public void setArmaDer(Arma armaDer) {
        this.armaDer = armaDer;
    }

    /**
     * Pode devolver null para indicar que non existe
     */
    public Arma getArmaDosM() {
        return armaDosM;
    }

    /**
     * Pode ser null para indicar que non existe
     */
    public void setArmaDosM(Arma armaDosM) {
        this.armaDosM = armaDosM;
    }

    /**
     * Pode devolver null para indicar que non existe
     */
    public Armadura getArmadura() {
        return armadura;
    }

    /**
     * Pode ser null para indicar que non existe
     */
    public void setArmadura(Armadura armadura) {
        this.armadura = armadura;
    }

    public Mochila getMochila() {
        return mochila;
    }

    public void setMochila(Mochila mochila) {
        if(mochila != null)
            this.mochila = mochila;
        else
            this.mochila = new Mochila(20, 5);
    }

    /**
     * Devolve o punto que conten as coordenadas de onde se atopa
     */
    public Point getPunto() {
        return punto;
    }

    /**
     * Engade ou actualiza o punto onde se atopa o persoaxe,
     * si se pasa nulo crease un co constructor por defecto
     */
    public void setPunto(Point punto) {
        if(punto != null)
            this.punto = punto;
        else
            this.punto = new Point();//Crea coas coordenadas (0,0)
    }

    /**
     * Devolve true se a persoaxe ten vida <= 0
     */
    public boolean estaMuerto() {
        boolean muerto = false;
        if (getVidaActual() <= 0)
            muerto = true;
        return muerto;
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
     * @param direccion Pode ser u = up, d = down, r = right, l = left
     */
    public void mover(char direccion) throws MoverException, InsuficienteEnergiaException {
        final int ENERGIA_REQUERIDA = 5;

        int i = (int)punto.getX();
        int j = (int)punto.getY();

        /**
         * If que controla que quede suficiente energia para mover tantas celdas
         */
        if ((this.getEnergiaActual() - ENERGIA_REQUERIDA) < 0)
            throw new InsuficienteEnergiaException("Energia insuficiente para moverse.");
        else
            this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));

        if (direccion == 'u' && i - 1 >= 0)
            if (mapa.getCelda(i - 1, j).isTransitable()) {
                if (this instanceof Jugador) {
                    mapa.getCelda(i,j).setJugador(null);
                    mapa.getCelda(i - 1, j).setJugador((Jugador) this);
                    punto.setLocation(i-1,j);
                }
                else
                    mapa.getCelda(i - 1, j).setEnemigo((Enemigo) this);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'd' && i + 1 < mapa.getAlto())
            if (mapa.getCelda(i + 1, j).isTransitable()) {
                if (this instanceof Jugador) {
                    mapa.getCelda(i, j).setJugador(null);
                    mapa.getCelda(i + 1, j).setJugador((Jugador) this);
                    punto.setLocation(i + 1,j);
                }
                else
                    mapa.getCelda(i + 1, j).setEnemigo((Enemigo) this);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'l' && j - 1 >= 0)
            if (mapa.getCelda(i, j - 1).isTransitable()) {
                if (this instanceof Jugador) {
                    mapa.getCelda(i, j).setJugador(null);
                    mapa.getCelda(i, j - 1).setJugador((Jugador) this);
                    punto.setLocation(i,j - 1);
                }
                else
                    mapa.getCelda(i, j - 1).setEnemigo((Enemigo) this);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'r' && j + 1 < mapa.getAncho())
            if (mapa.getCelda(i, j + 1).isTransitable()) {
                if (this instanceof Jugador) {
                    mapa.getCelda(i,j).setJugador(null);
                    mapa.getCelda(i, j + 1).setJugador((Jugador) this);
                    punto.setLocation(i,j + 1);
                }
                else
                    mapa.getCelda(i, j + 1).setEnemigo((Enemigo) this);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else {
            this.setEnergiaActual((int)(this.getEnergiaActual() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
            throw new MoverException("Error moviendo");
        }
    }

    /**
     * Añade un objeto a la mochila y lo elimina de la celda
     * @param objeto Objeto a coger.
     * @throws SegmentationFaultException
     */
    public void coger(Objeto objeto) throws SegmentationFaultException, PesoMaximoException, EspacioMaximoException{
        if(objeto.getPeso() + mochila.getPesoActual() > mochila.getPesoMaximo())
            throw new PesoMaximoException("Peso maximo excedido");
        if(objeto.getEspacio() + mochila.getObjetosActuales() > mochila.getObjetosMaximos())
            throw new EspacioMaximoException("Espacio maximo excedido");

        if(objeto != null) {
            if (objeto instanceof Arma) {
                if(mochila.anadirArma((Arma) objeto))
                    mapa.getCelda(this.getPunto().x,this.getPunto().y).getArrayObjetos().remove(objeto);
            }
            else if (objeto instanceof Armadura) {
                if(mochila.anadirArmadura((Armadura) objeto))
                    mapa.getCelda(this.getPunto().x,this.getPunto().y).getArrayObjetos().remove(objeto);
            }
            else if (objeto instanceof Binocular) {
                if(mochila.anadirBinocular((Binocular) objeto))
                    mapa.getCelda(this.getPunto().x,this.getPunto().y).getArrayObjetos().remove(objeto);
            }
            else if (objeto instanceof Botiquin) {
                if(mochila.anadirBotiquin((Botiquin) objeto))
                    mapa.getCelda(this.getPunto().x,this.getPunto().y).getArrayObjetos().remove(objeto);
            }
            else if (objeto instanceof Torito) {
                if(mochila.anadirTorito((Torito) objeto))
                    mapa.getCelda(this.getPunto().x,this.getPunto().y).getArrayObjetos().remove(objeto);
            }
            else
                throw new SegmentationFaultException();
        }

    }

    /**
     * Ataca a un unico personaje
     * La comprobación de si está muerto se hace fuera de esta clase
     * Este metodo se sobreescribe siempre
     */
    public void atacar(Personaje personaje) throws InsuficienteEnergiaException{
        int coeficienteAtaque; //Previene que se sume vida al atacar
        Random random = new Random();

        float prob = random.nextFloat();
        int ataqueEjecutado;

        if (prob > 0.25) { //No es critico
            ataqueEjecutado = (getAtaque() * 20 / personaje.getArmadura().getDefensa());
        } else { //Golpe critico
            ataqueEjecutado = (2 * (getAtaque() * 20 / personaje.getArmadura().getDefensa()));
            consola.imprimir("CR1T 1N Y0U8 F4C3");
        }
        if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
        {
            ataqueEjecutado = 0;
        }

        if(this.getEnergiaActual() - 20 < 0)
            throw new InsuficienteEnergiaException("Insuficiente energia para atacar");
        else
            this.setEnergiaActual(this.getEnergiaActual() - 20);
        personaje.setVidaActual(personaje.getVidaActual() - ataqueEjecutado);
        consola.imprimir("El personaje " + personaje.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + personaje.getVidaActual());
    }

    public void tirar(Objeto objeto) throws SegmentationFaultException {
        if (objeto instanceof Arma)
            mochila.quitarArma((Arma) objeto);
        else if (objeto instanceof Armadura)
            mochila.quitarArmadura((Armadura) objeto);
        else if (objeto instanceof Binocular)
            mochila.quitarBinocular((Binocular) objeto);
        else if (objeto instanceof Botiquin)
            mochila.quitarBotiquin((Botiquin) objeto);
        else if (objeto instanceof Torito)
            mochila.quitarTorito((Torito)objeto);
        else
            throw new SegmentationFaultException();
        mapa.getCelda(this.getPunto().x,this.getPunto().y).getArrayObjetos().add(objeto);
    }


    //ACHTUNG: Es distinto del requerimiento
    public void equipar(Arma arma, String mano) throws SegmentationFaultException {
        Arma armaEscogida = null;
        for (Arma arm : mochila.getArrayArmas()) {
            if (arm.equals(arma)) {
                armaEscogida = arm;
            }
        }
        if(armaEscogida != null) {
            if (armaEscogida.isDosManos()) {
                if (getArmaDosM() != null) {
                    desequipar(armaDosM);
                } else {
                    desequipar(armaIzq);
                    desequipar(armaDer);
                }
                setArmaDosM(armaEscogida);
            } else {
                switch (mano) {
                    case "derecha":
                    case "":
                        desequipar(armaDer);
                        desequipar(armaDosM);
                        setArmaDer(armaEscogida);
                        break;
                    case "izquierda":
                        desequipar(armaIzq);
                        desequipar(armaDosM);
                        setArmaIzq(armaEscogida);
                        break;
                    default:
                        throw new SegmentationFaultException();
                }
            }
            mochila.quitarArma(armaEscogida);
        }
    }

    public void equipar(Armadura armadura){
        if (armadura != null) {
            desequipar(new Armadura());
            if(this.armadura == null) { //Desequipada correctamente
                mochila.quitarArmadura(armadura);
                setArmadura(armadura);
            }
        }
    }

    public void desequipar(Arma arma) {
        if (arma != null) {
            if (arma.equals(armaDosM)) {
                mochila.anadirArma(armaDosM);
                armaDosM = null; //El setter no funciona
            } else if (arma.equals(armaDer)) {
                mochila.anadirArma(getArmaDer());
                armaDer = null;
            } else if (arma.equals(armaIzq)){
                mochila.anadirArma(getArmaIzq());
                armaIzq = null;
            }
        }
    }

    //ACHTUNG: este metodo no es exacto a la especificacion
    //Da igual lo que pases por parametro que pasa de el
    public void desequipar(Armadura armadura) {
        if (getArmadura() != null) {
            if(mochila.getObjetosMaximos() > mochila.getObjetosActuales() + getArmadura().getEspacio()
                    && mochila.getPesoMaximo() > mochila.getPesoActual() + getArmadura().getPeso()) {
                setEnergiaMaxima(getEnergiaMaxima() - getArmadura().getIncrEnergia());
                setVidaMaxima(getVidaMaxima() - getArmadura().getIncrVida());
                mochila.anadirArmadura(getArmadura());
                armadura = null; //Para indicar que no hay nada
            }
        }
    }
    /**
     * Devuelve un entero con el ataque total del enemigo tenga las armas que tenga
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
     * Devuelve un arraylist de las armas que lleva equipadas el enemigo
     * ATENCION : si no lleva ninguna devuelve el conjunto pero vacio
     *
     * @return conjuntoArmas
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

    /**
     * Este método equipa las armas al enemigo dado un array de ellas(1 de dos manos o 2 de una mano).
     * ATENCIÓN : si se usa éste método se eliminarán las armas que el enemigo ya portaba
     * ATENCIÓN : si se usa para mandar solo una arma de una mano ésta quedará equipada en la mano derecha.
     * ATENCIÓN : en caso de ser dos armas a una mano la primera irá a la mano derecha.
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
                    System.out.println("ERROR, quieres equipar dos armas pero una de ellas es a dos manos(El enemigo solo tiene dos manos)");
                else {
                    armaDer = armas.get(0);
                    armaIzq = armas.get(1);
                    armaDosM = null;
                }
            } else
                System.out.println("ERROR en el número de armas que quieres equipar al enemigo");
        } else
            System.out.println("ERROR asignando las armas al enemigo");
    }

    public void info() {
        System.out.println("Personaje: " + getNombre());
        System.out.println("Energia maxima: " + getEnergiaMaxima());
        System.out.println("Salud maxima: " + getVidaMaxima());
        System.out.println("Energia actual: " + getEnergiaActual());
        System.out.println("Salud actual: " + getVidaActual());
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

    public void ojearInventario() {
        ArrayList<Binocular> arrayBin = getMochila().getArrayBinoculares();
        ArrayList<Botiquin> arrayBot = getMochila().getArrayBotiquin();
        System.out.println("Capacidad restante: " + (getMochila().getObjetosMaximos() - getMochila().getObjetosActuales()) + " objetos");
        System.out.println("Peso actual: " + getMochila().getPesoActual() + " kilogramos (Max : "+getMochila().getPesoMaximo()+")");
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
        for (Arma arma : getMochila().getArrayArmas()) {
            if (arma != null)
                arma.info();
            else
                System.out.println("No hay armas en la mochila.");
        }
        for (Armadura armadura : getMochila().getArrayArmaduras()) {
            if (armadura != null)
                armadura.info();
            else
                System.out.println("No hay armaduras en la mochila.");
        }
    }

}
