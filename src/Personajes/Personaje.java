package Personajes;

import Excepciones.InsuficienteEnergiaException;
import Excepciones.MoverException;
import Juego.Mapa;
import Juego.Mochila;
import Objetos.Arma;
import Objetos.Armadura;
import Objetos.Objeto;

import java.awt.Point;

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

        //getMapa().get

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

        /**
         * If que controla que quede suficiente energia para mover tantas celdas
         */
        /*if ((this.getEnergiaActual() - ENERGIA_REQUERIDA) < 0)
            throw new InsuficienteEnergiaException("Energia insuficiente para moverse.");
        else
            this.setEnergiaActual(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));

        if (direccion == 'u' && i - 1 >= 0)
            if (mapa.getCelda(i - 1, j).isTransitable())
                celda = mapa.getCelda(i - 1, j);
            else {
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'd' && i + 1 < mapa.getAlto())
            if (mapa.getCelda(i + 1, j).isTransitable())
                celda = mapa.getCelda(i + 1, j);
            else {
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
                throw new MoverException("La celda de abajo es intransitable.");
            }
        else if (direccion == 'l' && j - 1 >= 0)
            if (mapa.getCelda(i, j - 1).isTransitable())
                celda = mapa.getCelda(i, j - 1);
            else {
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
                throw new MoverException("La celda izquierda es intransitable.");
            }
        else if (direccion == 'r' && j + 1 < mapa.getAncho())
            if (mapa.getCelda(i, j + 1).isTransitable())
                celda = mapa.getCelda(i, j + 1);
            else {
                this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
                throw new MoverException("La celda derecha es intransitable.");
            }
        else {
            System.out.println("ERROR, no puedes mover tantas casillas en esa dirección");
            this.setEnergia(this.getEnergia() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5)));
        }*/
    }

    public void coger(Objeto objeto) {}

    public void atacar(Personaje personaje) {}

    public void tirar(Objeto objeto) {}

    public void equipar(Arma arma) {}

    public void equipar(Armadura armadura) {}

    public void desequipar(Arma arma) {}

    public void desequipar(Armadura armadura) {}
}
