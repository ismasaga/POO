package Objetos;

import Juego.Personaje;

/**
 * Implementa la clase botiquín
 *
 * Vision, peso y espacio son final pues no se pueden modificar mediante mecánicas del juego esos valores (en este caso
 * no tendría sentido).
 */
public class Botiquin
{
    private final int curacion;
    private final int peso;
    private final int espacio;
    private String descripcion;
    private String nombre;

    public Botiquin(String nombre, String descripcion, int curacion, int peso, int espacio)
    {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.curacion = curacion > 0 ? curacion : 10;
        this.peso = peso > 0 ? peso : 2;
        this.espacio = espacio > 0 ? espacio : 1;
    }

    public Botiquin(int curacion)
    {
        setNombre("default");
        setDescripcion("null");
        this.curacion = curacion > 0 ? curacion : 10;
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

    public int getCuracion()
    {
        return curacion;
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
        personaje.setPuntosVida(personaje.getPuntosVida() + curacion);
    }


}
