package Objetos;

import Juego.Personaje;

/**
 * Created by efren on 19/10/15.
 */
public class Binoculares
{
    //TODO: pueden ser final.
    /**
     * Aumento en el rango de vision del binocular. El rango de vision del personaje se calcularia como: rangoVision = vision + rangoPorDefecto
     */
    private int vision;
    private int peso;
    private int espacio;


    public Binoculares(int vision, int peso, int espacio)
    {
        //Se agrega un valor por defecto
        this.vision = vision > 0 ? vision : 1;
        this.peso = peso > 0 ? peso : 2;
        this.espacio = espacio > 0 ? espacio : 1;
    }

    public Binoculares(int vision)
    {
        //Se agrega un valor por defecto
        this.vision = vision > 0 ? vision : 1;
        this.peso = 2;
        this.espacio = 1;
    }

    //TODO: añadir un constructor por defecto con el binocular mas comun


    //No se implementan setters, pues los valores del binocular no cambia durante su existencia.
    public int getVision()
    {
        return vision;
    }

    public int getPeso()
    {
        return peso;
    }

    public int getEspacio()
    {
        return espacio;
    }

    public void usar(Personaje personaje)
    {
        //El nuevo rango de vision del personaje sera: rangoVisionNuevo = rangoVisionViejo + aumento
        personaje.setRangoVision(personaje.getRangoVision() + vision);
    }

    /**
     * Restaura el valor por defecto del rango de vision del personaje.
     * @param personaje Personaje a aplicar la disipacion
     */
    public void disipar(Personaje personaje)
    {
        personaje.setRangoVision(personaje.getRangoVision()-vision);
    }
}
