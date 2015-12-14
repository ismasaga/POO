package Objetos;

import Juego.ConsolaNormal;
import Personajes.Personaje;

/**
 * Implementa la clase botiquín
 * <p>
 * Vision, peso y espacio son final pues no se pueden modificar mediante mecánicas del juego esos valores (en este caso
 * no tendría sentido).
 */
public final class Botiquin extends Objeto {
    private final int curacion;
    private ConsolaNormal consola = new ConsolaNormal();

    public Botiquin(String nombre, String descripcion, int curacion, float peso, int espacio) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.curacion = curacion > 0 ? curacion : 10;
        setPeso(peso > 0 ? peso : 2);
        setEspacio(espacio > 0 ? espacio : 1);
    }

    public Botiquin(int curacion) {
        setNombre("default");
        setDescripcion("null");
        this.curacion = curacion > 0 ? curacion : 10;
        setPeso(2);
        setEspacio(1);
    }

    /**
     * Constructor para botiquines parseados de archivo
     */
    public Botiquin(String nombre, String descripcion, int curacion, float peso) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.curacion = curacion;
        setPeso(peso > 0 ? peso : 0);
        setEspacio(1);
    }

    public int getCuracion() {
        return curacion;
    }

    public void usar(Personaje personaje) {
        personaje.setVidaActual(personaje.getVidaActual() + curacion);
    }

    /**
     * Imprime informacion sobre el binocular
     */
    public void info() {
        consola.imprimir("Botiquin:\n");
        consola.imprimir("\tPeso: " + getPeso() + "\n");
        consola.imprimir("\tEspacio: " + getEspacio() + "\n");
        consola.imprimir("\tCuracion: " + getCuracion() + "\n");
        consola.imprimir("\tNombre: " + getNombre());
        consola.imprimir("\tDescripcion: " + getDescripcion());
    }


}
