package Objetos;

import Juego.Personaje;

/**
 * Implementa un binocular
 *
 * A destacar
 *
 * Vision, peso y espacio son final pues no se pueden modificar mediante mecánicas del juego esos valores (en este caso
 * no tendría sentido).
 */
public class Binoculares
{
    /**
     * Aumento en el rango de vision del binocular. El rango de vision del personaje se calcularia como: rangoVision = vision + rangoPorDefecto
     */
    private final int vision;
    private final int peso;
    private final int espacio;
    private String descripcion;
    private String nombre;


    public Binoculares(String nombre, String descripcion, int vision, int peso, int espacio)
    {
        //Se agrega un valor por defecto
        setNombre(nombre);
        setDescripcion(descripcion);
        this.vision = vision > 0 ? vision : 1;
        this.peso = peso > 0 ? peso : 2;
        this.espacio = espacio > 0 ? espacio : 1;
    }

    public Binoculares(int vision)
    {
        //Se agrega un valor por defecto
        setNombre("default");
        setDescripcion("null");
        this.vision = vision > 0 ? vision : 1;
        this.peso = 2;
        this.espacio = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = new String(nombre);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = new String(descripcion);
    }

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
