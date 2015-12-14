package Objetos;

import Personajes.Personaje;
import Juego.*;
/**
 * Implementa un binocular
 * <p>
 * A destacar
 * <p>
 * Vision, peso y espacio son final pues no se pueden modificar mediante mecánicas del juego esos valores (en este caso
 * no tendría sentido).
 */
public class Binocular extends Objeto{
    /**
     * Aumento en el rango de vision del binocular. El rango de vision del personaje se calcularia como: rangoVision = vision + rangoPorDefecto
     */
    private final int vision;
    private boolean usado = false; //Indica si fue usado en el turno actual
    private Consola consola = new ConsolaNormal();



    public Binocular(String nombre, String descripcion, int vision, int peso, int espacio) {
        //Se agrega un valor por defecto
        setNombre(nombre);
        setDescripcion(descripcion);
        this.vision = vision > 0 ? vision : 1;
        setPeso(peso > 0 ? peso : 2);
        setEspacio(espacio > 0 ? espacio : 1);
    }

    public Binocular(int vision) {
        //Se agrega un valor por defecto
        setNombre("default");
        setDescripcion("null");
        this.vision = vision > 0 ? vision : 1;
        setPeso(2);
        setEspacio(1);
    }

    /**
     * Constructor para objetos parseados de archivo
     */
    public Binocular(String nombre, String descripcion, int vision, int peso) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.vision = vision > 0 ? vision : 1;
        setPeso(peso > 0 ? peso : 2);
        setEspacio(1);
    }


    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    //No se implementan setters, pues los valores del binocular no cambia durante su existencia.
    public int getVision() {
        return vision;
    }

    public void usar(Personaje personaje) {
        //El nuevo rango de vision del personaje sera: rangoVisionNuevo = rangoVisionViejo + aumento
        personaje.setRangoVision(personaje.getRangoVision() + vision);
    }

    /**
     * Restaura el valor por defecto del rango de vision del personaje.
     *
     * @param personaje Personaje a aplicar la disipacion
     */
    public void disipar(Personaje personaje) {
        personaje.setRangoVision(personaje.getRangoVision() - vision);
    }

    /**
     * Imprime informacion sobre el binocular:
     */
    public void info() {
        consola.imprimir("Binocular:");
        consola.imprimir("\tNombre: " + getNombre());
        consola.imprimir("\tPeso: " + getPeso());
        consola.imprimir("\tEspacio: " + getEspacio());
        consola.imprimir("\tAumento de rango de vision: " + getVision());
        consola.imprimir("\tDescripcion: " + getDescripcion());
    }
}
