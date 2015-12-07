package Objetos;

import Personajes.Personaje;

public class Torito extends Objeto{
    private int incrementoEnergia;

    public Torito(String nombre, String descripcion, int incrementoEnergia)
    {
        super.setNombre(nombre);
        super.setDescripcion(descripcion);
        this.incrementoEnergia = incrementoEnergia;
    }

    public void usar(Personaje personaje){
        personaje.setEnergia(personaje.getEnergia() + incrementoEnergia);
    }

    /**
     * Este método es el "efecto colateral de la toritina"
     * @param personaje Personaje a aplicar el efecto
     */
    public void disipar(Personaje personaje){
        personaje.setEnergia((int)0.9*personaje.getEnergia());
    }

}
