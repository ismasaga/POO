package Objetos;

import Personajes.Personaje;

public class Armadura extends Objeto{
    private int incrVida, incrEnergia, defensa;
    private float peso;
    private int espacio;

    public Armadura() {
        setNombre("desconocida");
        setDescripcion("null");
        setIncrVida(0);
        setIncrEnergia(0);
        setDefensa(0);
        setPeso(1);
        setEspacio(1);
    }

    public Armadura(String nombre, String descripcion, int incrVida, int incrEnergia, int defensa, float peso, int espacio) {
        setDescripcion(descripcion);
        setNombre(nombre);
        setIncrVida(incrVida);
        setIncrEnergia(incrEnergia);
        setDefensa(defensa);
        setPeso(peso);
        setEspacio(espacio);
    }

    /**
     * Constructor para armaduras parseadas de archivo
     */
    public Armadura(String nombre, String descripcion, int defensa, int incrVida, int incrEnergia, float peso) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setDefensa(defensa);
        setIncrVida(incrVida);
        setIncrEnergia(incrEnergia);
        setPeso(peso);
        setEspacio(1);
    }

    /**
     * Devolve o espacio que ocupa a armadura
     */
    public int getEspacio() {
        return espacio;
    }

    /**
     * Asigna o espacio que ocupa a armadura
     */
    public void setEspacio(int espacio) {
        if (espacio < 0)
            this.espacio = 0;
        else
            this.espacio = espacio;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getIncrVida() {
        return incrVida;
    }

    public void setIncrVida(int incrVida) {
        this.incrVida = incrVida;
    }

    public int getIncrEnergia() {
        return incrEnergia;
    }

    public void setIncrEnergia(int incrEnergia) {
        this.incrEnergia = incrEnergia;
    }

    /**
     * Devuelve los puntos de defensa que proporciona la armadura
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Asigna los puntos de defensa que tiene la armadura
     */
    public void setDefensa(int defensa) {
        if (defensa < 0)
            this.defensa = 0;
        else
            this.defensa = defensa;
    }

    /**
     * Proporciona informacion sobre la armadura
     */
    public void info() {
        System.out.println("Armadura:");
        System.out.println("\tNombre: " + getNombre());
        System.out.println("\tDefensa: " + getDefensa());
        System.out.println("\tEspacio: " + getEspacio());
        System.out.println("\tPeso: " + getPeso());
        System.out.println("\tDefensa: " + getDefensa());
        System.out.println("\tIncremento de energia: " + getIncrEnergia());
        System.out.println("\tIncremento de vida: " + getIncrVida());
        System.out.println("\tDescripción: " + getDescripcion());
    }

    public void usar(Personaje personaje){
        //TODO: tirar una excepción como dios manda.
        throw new UnsupportedOperationException("Error fatal");
    }

}
