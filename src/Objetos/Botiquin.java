package Objetos;

import Personajes.Personaje;

/**
 * Implementa la clase botiquín
 * <p>
 * Vision, peso y espacio son final pues no se pueden modificar mediante mecánicas del juego esos valores (en este caso
 * no tendría sentido).
 */
public class Botiquin extends Objeto {
    private final int curacion;
    private final float peso;
    private final int espacio;

    public Botiquin(String nombre, String descripcion, int curacion, float peso, int espacio) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.curacion = curacion > 0 ? curacion : 10;
        this.peso = peso > 0 ? peso : 2;
        this.espacio = espacio > 0 ? espacio : 1;
    }

    public Botiquin(int curacion) {
        setNombre("default");
        setDescripcion("null");
        this.curacion = curacion > 0 ? curacion : 10;
        this.peso = 2;
        this.espacio = 1;
    }

    /**
     * Constructor para botiquines parseados de archivo
     */
    public Botiquin(String nombre, String descripcion, int curacion, float peso) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.curacion = curacion;
        this.peso = peso > 0 ? peso : 0;
        this.espacio = 1;
    }

    public int getCuracion() {
        return curacion;
    }

    public float getPeso() {
        return peso;
    }

    public int getEspacio() {
        return espacio;
    }

    public void usar(Personaje personaje) {
        personaje.setVidaActual(personaje.getVidaActual() + curacion);
    }

    /**
     * Imprime informacion sobre el binocular
     */
    public void info() {
        System.out.println("Botiquin:\n");
        System.out.println("\tPeso: " + getPeso() + "\n");
        System.out.println("\tEspacio: " + getEspacio() + "\n");
        System.out.println("\tCuracion: " + getCuracion() + "\n");
        System.out.println("\tNombre: " + getNombre());
        System.out.println("\tDescripcion: " + getDescripcion());
    }


}
