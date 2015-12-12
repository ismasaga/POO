package Objetos;

import Excepciones.InusableException;
import Personajes.Personaje;

public class Arma extends Objeto{
    private boolean dosManos;
    private int dano;
    private int alcance;

    public Arma() {
        setNombre("desconocida");
        setDescripcion("null");
        setDano(10);
        setDosManos(true);
        setPeso(1);
        setEspacio(1);
        setAlcance(1);
    }

    public Arma(String nombre, String descripcion, boolean dosManos, int dano, float peso, int espacio) {
        setNombre(nombre);
        setDano(dano);
        setDescripcion(descripcion);
        setDosManos(dosManos);
        setPeso(peso);
        setEspacio(espacio);
        setAlcance(1);
    }

    /**
     * Constructor para armas parseadas de archivo
     */
    public Arma(String nombre, String descripcion, int dano, int alcance, int manos, float peso) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setDano(dano);
        setAlcance(alcance);
        if (manos == 1)
            setDosManos(false);
        else if (manos == 2)
            setDosManos(true);
        else
            System.out.println("ERROR definiendo de cuántas manos és le arma");
        setPeso(peso);
        setEspacio(1);
    }

    /**
     * Devuelve alcance del arma
     */
    public int getAlcance() {
        return alcance;
    }

    /**
     * Asigna alcance a la arma
     */
    public void setAlcance(int alcance) {
        if (alcance < 0)
            this.alcance = 0;
        else
            this.alcance = alcance;
    }

    /**
     * Devuleve true si el arma es a dos manos y false en caso contrario
     */
    public boolean isDosManos() {
        return dosManos;
    }

    /**
     * Asigna el valor booleano a la variable dosManos
     * IMPORTANTE : meter true en caso de que el arma sea de dos manos y false en caso contrario
     */
    public void setDosManos(boolean dosManos) {
        this.dosManos = dosManos;
    }

    /**
     * Devuelve los puntos de ataque del arma
     *
     * @return dano
     */
    public int getDano() {
        return dano;
    }

    /**
     * Asigna los puntos de daño al arma
     */
    public void setDano(int dano) {
        if (dano < 0)
            this.dano = 0;
        else
            this.dano = dano;
    }

    /**
     * Imprime informacion sobre el arma
     */
    public void info() {
        System.out.println("Arma: " + getNombre());
        System.out.println("\tDaño: " + getDano());
        System.out.println("\tPeso: " + getPeso());
        System.out.println("\tEspacio: " + getEspacio());
        System.out.println("\tDescripcion: " + getDescripcion());
        System.out.println("\tMano: " + (isDosManos() ? "Dos Manos" : "Una mano"));
        System.out.println("\tAlcance: "+getAlcance());
    }

    public void usar(Personaje personaje) throws InusableException{
        //TODO: tirar una excepcion como dios manda
        throw new InusableException("arma");
    }
}
