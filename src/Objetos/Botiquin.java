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

    /**
     * Constructor para botiquines parseados de archivo
     */
    public Botiquin(String nombre, String descripcion, int curacion, int peso) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.curacion = curacion;
        this.peso = peso > 0 ? peso : 0;
        this.espacio = 1;
    }

    /**
     * Devuelve el nombre del botiquin
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna nombre al botiquin
     */
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = new String(nombre);
        else
            System.out.println("ERROR asignando nombre al botiquin");
    }

    /**
     * Devuelve la descripcion del botiquin
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna descripcion al botiquin
     */
    public void setDescripcion(String descripcion) {
        if(descripcion != null)
            this.descripcion = new String(descripcion);
        else
            System.out.println("ERROR asignando descripcion al botiquin");
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

    /**
     * Imprime informacion sobre el binocular
     */
    public void info()
    {
        System.out.println("Botiquin:\n");
        System.out.println("\tPeso: " + getPeso() + "\n");
        System.out.println("\tEspacio: " + getEspacio() + "\n");
        System.out.println("\tCuracion: " + getCuracion() + "\n");
        System.out.println("\tNombre:" + getNombre());
        System.out.println("\tDescripcion:" + getDescripcion());
    }


}
