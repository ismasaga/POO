package Personajes;

import Juego.Celda;
import Juego.Consola;
import Juego.ConsolaNormal;
import Juego.Mapa;
import Objetos.Objeto;
import Excepciones.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public final class Francotirador extends Jugador {

    /**
     * Constructor para archivo parseado
     */
    public Francotirador(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa, punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
    }

    //El francotirador se mueve con 5 de energia requerida

    //No puede coger explosivos
    @Override
    public void coger(Objeto objeto) throws SegmentationFaultException, PesoMaximoException, EspacioMaximoException {
        if(objeto.getNombre().equals("explosivos") || objeto.getNombre().equals("bazooka"))
            throw new SegmentationFaultException();
        else
            super.coger(objeto);
    }

    @Override
    public void atacar(Personaje personaje) throws InsuficienteEnergiaException {
        Consola consola = new ConsolaNormal();
        int distanciaX = Math.abs(this.getPunto().x - personaje.getPunto().x);
        int distanciaY = Math.abs(this.getPunto().y - personaje.getPunto().y);
        int coeficienteAtaque; //Previene que se sume vida al atacar
        double correcccionAtaque = (Math.pow(Math.max(distanciaX,distanciaY),1.2));
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
        double correcccionAtaque = (Math.pow(Math.max(distanciaX,distanciaY),1.2));
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
                System.out.println("CR1T 1N Y0U8 F4C3");
            }
            if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
            {
                ataqueEjecutado = 0;
            }
            enemigo.setVidaActual(enemigo.getVidaActual() - ataqueEjecutado);
            consola.imprimir("El personaje " + enemigo.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getVidaActual());
            if (enemigo.getVidaActual() <= 0) {
                System.out.println("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                enemigosAbatidos.add(enemigo);
            }
        }
        for (Enemigo enemigo : enemigosAbatidos) {
            enemigo.soltarObjetos(celda);
            celda.eliminarEnemigo(enemigo);
        }
    }
}
