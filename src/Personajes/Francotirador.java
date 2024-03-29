package Personajes;

import Juego.*;
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
    public void coger(Objeto objeto) throws ExplosivosException , PesoMaximoException, EspacioMaximoException {
        if(objeto.getNombre().equals("explosivos") || objeto.getNombre().equals("bazooka"))
            throw new ExplosivosException("E francotirador no puede equipar explosivos");
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
        int defensa = (personaje.getArmadura() != null && personaje.getArmadura().getDefensa() > 0 ? personaje.getArmadura().getDefensa() : 1);

        float prob = random.nextFloat();
        int ataqueEjecutado;

        if (prob > 0.25) { //No es critico
            ataqueEjecutado = (int)(correcccionAtaque * getAtaque() * Constantes.REDUCCION_ARMADURA / defensa);
        } else { //Golpe critico
            ataqueEjecutado = (int)(correcccionAtaque * 2 * (getAtaque() * Constantes.REDUCCION_ARMADURA / defensa));
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

    public void atacar(Celda celda) throws InsuficienteEnergiaException, EnemigoInexistenteException
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
        if(celda.getEnemigo() != null) {
            for (Enemigo enemigo : celda.getEnemigo()) {
                int defensa = (enemigo.getArmadura() != null && enemigo.getArmadura().getDefensa() > 0 ? enemigo.getArmadura().getDefensa() : 1);
                //Ahora hay que dividir el daño del personaje entre todos los enemigos
                if (prob > 0.25) //No es crítico
                {
                    ataqueEjecutado = ((int) (correcccionAtaque * getAtaque() / celda.getEnemigo().size()) * 20 / defensa);
                } else //Golpe critico
                {
                    ataqueEjecutado = ((int) (correcccionAtaque * 2 * (getAtaque() / celda.getEnemigo().size())) * 20 / defensa);
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
        else{
            throw new EnemigoInexistenteException("No hay enemigo en esa celda");
        }
    }
}
