package Personajes;

import Excepciones.InsuficienteEnergiaException;
import Excepciones.MoverException;
import Juego.Celda;
import Juego.Consola;
import Juego.ConsolaNormal;
import Juego.Mapa;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public final class Zapador extends Jugador {

    /**
     * Constructor para archivo parseado
     */
    public Zapador(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa, punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
    }

    @Override
    public void mover(char direccion) throws MoverException, InsuficienteEnergiaException {
        final int ENERGIA_REQUERIDA = 2;

        int i = (int)this.getPunto().getX();
        int j = (int)this.getPunto().getY();

        /**
         * If que controla que quede suficiente energia para mover tantas celdas
         */
        if ((this.getEnergiaActual() - ENERGIA_REQUERIDA) < 0)
            throw new InsuficienteEnergiaException("Energia insuficiente para moverse.");
        else
            this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));

        if (direccion == 'u' && i - 1 >= 0)
            if (getMapa().getCelda(i - 1, j).isTransitable()) {
                    getMapa().getCelda(i,j).setJugador(null);
                    getMapa().getCelda(i - 1, j).setJugador((Jugador) this);
                    getPunto().setLocation(i-1,j);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'd' && i + 1 < getMapa().getAlto())
            if (getMapa().getCelda(i + 1, j).isTransitable()) {
                    getMapa().getCelda(i, j).setJugador(null);
                    getMapa().getCelda(i + 1, j).setJugador((Jugador) this);
                    getPunto().setLocation(i + 1,j);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'l' && j - 1 >= 0)
            if (getMapa().getCelda(i, j - 1).isTransitable()) {
                    getMapa().getCelda(i, j).setJugador(null);
                    getMapa().getCelda(i, j - 1).setJugador((Jugador) this);
                    getPunto().setLocation(i,j - 1);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'r' && j + 1 < getMapa().getAncho())
            if (getMapa().getCelda(i, j + 1).isTransitable()) {
                    getMapa().getCelda(i,j).setJugador(null);
                    getMapa().getCelda(i, j + 1).setJugador((Jugador) this);
                    getPunto().setLocation(i,j + 1);
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


    @Override
    public void atacar(Personaje personaje) throws InsuficienteEnergiaException {
        Consola consola = new ConsolaNormal();
        int distanciaX = Math.abs(this.getPunto().x - personaje.getPunto().x);
        int distanciaY = Math.abs(this.getPunto().y - personaje.getPunto().y);
        int coeficienteAtaque; //Previene que se sume vida al atacar
        double correcccionAtaque = (distanciaX > 2 || distanciaY > 2 ? 0.05 : 1);
        Random random = new Random();


        float prob = random.nextFloat();
        int ataqueEjecutado;

        if (prob > 0.25) { //No es critico
            ataqueEjecutado = (int)(correcccionAtaque * getAtaque() * 20 / personaje.getArmadura().getDefensa());
        } else { //Golpe critico
            ataqueEjecutado = (int)(correcccionAtaque * 2 * (getAtaque() * 20 / personaje.getArmadura().getDefensa()));
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

    public void atacar(Celda celda) throws InsuficienteEnergiaException
    {
        Consola consola = new ConsolaNormal();
        int distanciaX = Math.abs(this.getPunto().x - celda.getPunto().x);
        int distanciaY = Math.abs(this.getPunto().y - celda.getPunto().y);
        int coeficienteAtaque; //Previene que se sume vida al atacar
        double correcccionAtaque = (distanciaX > 2 || distanciaY > 2 ? 0.05 : 1);
        Random random = new Random();

        float prob = random.nextFloat();
        int ataqueEjecutado;

        if(this.getEnergiaActual() - 20 < 0)
            throw new InsuficienteEnergiaException("Insuficiente energia para atacar");
        else
            this.setEnergiaActual(this.getEnergiaActual() - 20);
        ArrayList<Enemigo> enemigosAbatidos = new ArrayList<>();
        for (Enemigo enemigo : celda.getEnemigo()) {
            //Ahora hay que dividir el daño del personaje entre todos los enemigos
            if (prob > 0.25) //No es crítico
            {
                ataqueEjecutado = ((int)(correcccionAtaque * getAtaque() / celda.getEnemigo().size()) * 20 / enemigo.getArmadura().getDefensa());
            } else //Golpe critico
            {
                ataqueEjecutado = ((int)(correcccionAtaque * 2 * (getAtaque() / celda.getEnemigo().size())) * 20 / enemigo.getArmadura().getDefensa());
                consola.imprimir("CR1T 1N Y0U8 F4C3");
            }
            if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
            {
                ataqueEjecutado = 0;
            }
            enemigo.setVidaActual(enemigo.getVidaActual() - ataqueEjecutado);
            consola.imprimir("El personaje " + enemigo.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getVidaActual());
            if (enemigo.getVidaActual() <= 0) {
                consola.imprimir("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                enemigosAbatidos.add(enemigo);
            }
        }
        for (Enemigo enemigo : enemigosAbatidos) {
            enemigo.soltarObjetos(celda);
            celda.eliminarEnemigo(enemigo);
        }
    }
}
