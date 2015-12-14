package Objetos;

import Juego.Consola;
import Juego.ConsolaNormal;
import Personajes.Jugador;
import Personajes.Personaje;

public class Torito extends Objeto{
    private int incrementoEnergia;

    public Torito(String nombre, String descripcion, int incrementoEnergia)
    {
        super.setNombre(nombre);
        super.setDescripcion(descripcion);
        this.incrementoEnergia = incrementoEnergia;
        setPeso(1);
        setEspacio(1);
    }

    public Torito(String nombre, String descripcion, int incrementoEnergia, int peso, int espacio)
    {
        super.setNombre(nombre);
        super.setDescripcion(descripcion);
        this.incrementoEnergia = incrementoEnergia;
        setPeso(peso);
        setEspacio(espacio);
    }

    public void usar(Personaje personaje){
        if(personaje instanceof Jugador) {
            personaje.setEnergiaActual(personaje.getEnergiaActual() + incrementoEnergia);
            ((Jugador) personaje).setTieneToritina(true);
        }

    }

    public int getIncrementoEnergia() { return incrementoEnergia; }

    /**
     * Este método es el "efecto colateral de la toritina"
     * @param personaje Personaje a aplicar el efecto
     */
    public void disipar(Personaje personaje){
        personaje.setEnergiaActual((int)(0.9*personaje.getEnergiaActual()));
    }

    public void info()
    {
        Consola consola = new ConsolaNormal();
        consola.imprimir("Nombre: " + getNombre());
        consola.imprimir("\tDescripcion: " + getDescripcion());
        consola.imprimir("\tEspacio: " + getEspacio());
        consola.imprimir("\tPeso: " + getPeso());
        consola.imprimir("\tIncremento de energia: " + getIncrementoEnergia());
    }
}
