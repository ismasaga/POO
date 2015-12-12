package Personajes;

import Excepciones.InsuficienteEnergiaException;
import Excepciones.MoverException;
import Juego.Mapa;
import Juego.Mochila;
import Objetos.Arma;
import Objetos.Armadura;
import Objetos.Objeto;

import java.awt.Point;
import java.util.ArrayList;

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

    /**
     * Constructor para archivo parseado
     */
    public Personaje(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        setEnergiaMaxima(energiaMaxAct);
        setNombre(nombre);
        setVidaMaxima(vidaMaxAct);
        setVidaActual(vidaMaxAct);
        setEnergiaActual(energiaMaxAct);
        setRangoVision(2);              //Por defecto
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
        else
            this.vidaActual = getVidaMaxima();
    }

    public int getEnergiaActual() {
        return energiaActual;
    }

    public void setEnergiaActual(int energiaActual) {
        if(energiaActual > 0 && energiaActual <= getEnergiaMaxima())
            this.energiaActual = energiaActual;
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


        /*boolean encontrado = false;
        int j = 0, i = 0;
        for (i = 0; i < mapa.getAlto(); i++) {
            for (j = 0; j < mapa.getAncho(); j++) {
                encontrado = mapa.getCelda(i, j).equals(this.getCelda());
                if (encontrado)
                    break;
            }
            if (encontrado)
                break;
        }*/
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

    public void coger(Objeto objeto) {}

    public void atacar(Personaje personaje) {}

    public void tirar(Objeto objeto) {}

    public void equipar(Arma arma) {}

    public void equipar(Armadura armadura) {}

    public void desequipar(Arma arma) {}

    public void desequipar(Armadura armadura) {}

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
}
