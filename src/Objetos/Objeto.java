package Objetos;

import Personajes.Personaje;

/**
 * Created by efren on 7/12/15.
 */
public abstract class Objeto {
    private String descripcion;
    private String nombre;

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

    public abstract void usar(Personaje personaje);
}
