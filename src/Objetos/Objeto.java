package Objetos;

import Excepciones.InusableException;
import Personajes.Personaje;

/**
 * Created by efren on 7/12/15.
 */
public abstract class Objeto {
    private String descripcion;
    private String nombre;
    private float peso;
    private int espacio;

    public String getDescripcion() {
        return new String(descripcion);
    }

    public void setDescripcion(String descripcion) {
        if(descripcion != null)
            this.descripcion = descripcion;
    }

    public String getNombre() {
        return new String(nombre);
    }

    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso > 0 ? peso : 1;
    }

    public int getEspacio() {
        return espacio;
    }

    public void setEspacio(int espacio) {
        this.espacio = espacio > 0 ? espacio : 1;
    }

    public abstract void usar(Personaje personaje) throws InusableException;
}
