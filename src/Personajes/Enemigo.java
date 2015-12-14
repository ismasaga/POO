package Personajes;

import Juego.Celda;
import Juego.Mapa;
import Juego.Mochila;
import Objetos.Arma;
import Objetos.Armadura;
import Objetos.Binocular;
import Objetos.Botiquin;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase enemigo. Funciones:
 * - Atacar y moverse.
 * - Detectar proximidad del personaje.
 * <p>
 * A destacar:
 * <p>
 * VIDA_MAXIMA, ataque y armadura son constantes pues no hay manera de modificarlos (con elementos
 * del juego )en esta versión.
 */
public abstract class Enemigo extends Personaje{


    /**
     * Constructor para el parseado de los archivos
     * @param mapa
     * @param punto
     * @param nombre
     * @param vidaMaxAct
     * @param energiaMaxAct
     */
    public Enemigo(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {

        super(mapa,punto,nombre,vidaMaxAct,energiaMaxAct);
    }

    public Enemigo(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct, Arma armaIzq, Arma armaDer, Armadura armadura) {
        super(mapa, punto, nombre, vidaMaxAct, energiaMaxAct, armaIzq, armaDer, armadura);
    }

    public Enemigo(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct, Arma armaDosM, Armadura armadura) {
       super(mapa, punto, nombre, vidaMaxAct, energiaMaxAct, armaDosM, armadura);
    }

    public void atacar(Personaje personaje) {
        int coeficienteAtaque; //Esta variable previene que un ataque sume puntos de vida (armadura > ataque)
        coeficienteAtaque = (getAtaque() - personaje.getArmadura().getDefensa() >= 0) ? getAtaque() - personaje.getArmadura().getDefensa() : 0;
        personaje.setVidaActual(personaje.getVidaActual() - coeficienteAtaque);
        System.out.println(personaje.getNombre() + " atacado con" + coeficienteAtaque + " puntos de daño");
    }

    /**
     * Imprime informacion sobre el enemigo
     */
    public void info() {
        System.out.println("Enemigo: ");
        System.out.println("Nombre: " + getNombre() +
                "\nPuntos de vida: " + getVidaActual() +
                "\nPuntos de ataque: " + getAtaque() +
                "\nPuntos de armadura: " + getArmadura().getDefensa());
        if (!getArmas().isEmpty()) //Si el enemigo tiene armas
        {
            for (Arma arma : getArmas()) {
                arma.info();
            }
        }
    }

    /**
     * Suelta todos los objetos del enemigo en la celda actual (pasada por parametro)
     *
     * @param celda Celda actual
     */
    public void soltarObjetos(Celda celda) {
        for (Arma arma : this.getArmas()) {
            if (arma != null) {
                celda.anadirObjeto(arma);
                System.out.println("Soltando:");
                arma.info();
            }
            setArmaDer(null);
            setArmaIzq(null);
            setArmaDosM(null);
        }
        if (getArmadura() != null) {
            celda.anadirObjeto(getArmadura());
            System.out.println("Soltando:");
            getArmadura().info();
            setArmadura(null);
        }
        for (Arma arma : getMochila().getArrayArmas()) {
            if (arma != null) {
                celda.anadirObjeto(arma);
                System.out.println("Soltando:");
                arma.info();
            }
        }
        //mochila.setArrayArmas(null); //Se marca para eliminacion
        for (Armadura armadura : getMochila().getArrayArmaduras()) {
            if (armadura != null) {
                celda.anadirObjeto(armadura);
                System.out.println("Soltando:");
                armadura.info();
            }
        }
        getMochila().setArrayArmaduras(null);
        for (Binocular bin : getMochila().getArrayBinoculares()) {
            if (bin != null) {
                celda.anadirObjeto(bin);
                getMochila().getArrayBinoculares().remove(bin);
                System.out.println("Soltando:");
                bin.info();
            }
        }
        for (Botiquin bot : getMochila().getArrayBotiquin()) {
            if (bot != null) {
                celda.anadirObjeto(bot);
                getMochila().getArrayBotiquin().remove(bot);
                System.out.println("Soltando:");
                bot.info();
            }
        }
    }


    /**
     * Implementa la inteligencia artificial del enemigo.
     * <p>
     * Se deberia iterar sobre 'all' el mapa aplicando este metodo a cada enemigo
     *
     * @param mapa      Mapa sobre el que mover
     * @param iE        Fila actual del enemigo
     * @param jE        Columna actual del enemigo
     * @param personaje Personaje al que atacar
     */
    public void mover(Mapa mapa, int iE, int jE, Personaje personaje) {
        /*Random numero = new Random();

        int numeroMovimientos = numero.nextInt(4);
        int direccion;
        int iPersonaje = mapa.posicionPersonaje(personaje)[0];
        int jPersonaje = mapa.posicionPersonaje(personaje)[1];
        int iEnemigo = iE;
        int jEnemigo = jE;
        for (int i = 0; i < numeroMovimientos; i++) {
            if ((iPersonaje >= iEnemigo - getRangoVision() &&
                    iPersonaje <= iEnemigo + getRangoVision() &&
                    jPersonaje >= jEnemigo - getRangoVision() &&
                    jPersonaje <= jEnemigo + getRangoVision())) {

                atacar(personaje);
                return;
            } else {
                mapa.getCelda(iEnemigo, jEnemigo).getEnemigo().remove(this);
                direccion = numero.nextInt(3);
                switch (direccion) {
                    case 0: //Mueve abajo
                        if (iEnemigo + 1 < mapa.getAlto() && mapa.getCelda(iEnemigo + 1, jEnemigo).isTransitable())
                            iEnemigo += 1;
                        break;
                    case 1: //Mueve arriba
                        if (iEnemigo - 1 >= 0 && mapa.getCelda(iEnemigo - 1, jEnemigo).isTransitable())
                            iEnemigo -= 1;
                        break;
                    case 2: //Mueve derecha
                        if (jEnemigo + 1 < mapa.getAncho() && mapa.getCelda(iEnemigo, jEnemigo + 1).isTransitable())
                            jEnemigo += 1;
                        break;
                    case 3: //Mueve izquierda
                        if (jEnemigo - 1 < mapa.getAncho() && mapa.getCelda(iEnemigo, jEnemigo - 1).isTransitable())
                            jEnemigo -= 1;
                        break;
                    default:
                        System.err.println("Incapaz de mover el enemigo" + this.getNombre());
                }
                mapa.getCelda(iEnemigo, jEnemigo).setEnemigo(this);
            }
            if ((iPersonaje >= iEnemigo - getRangoVision() &&
                    iPersonaje <= iEnemigo + getRangoVision() &&
                    jPersonaje >= jEnemigo - getRangoVision() &&
                    jPersonaje <= jEnemigo + getRangoVision())) {

                atacar(personaje);
                return;
            }
        }*/
    }
}
