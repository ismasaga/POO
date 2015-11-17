package Objetos;

public class Armadura {
    private String nombre;
    private int incrVida,incrEnergia,defensa;
    private String descripcion;
    private int peso;
    private int espacio;

    public Armadura() {
        setNombre("desconocida");
        setDescripcion("null");
        setIncrVida(0);
        setIncrEnergia(0);
        setDefensa(0);
        setPeso(1);
        setEspacio(1);
    }

    public Armadura(String nombre, String descripcion, int incrVida, int incrEnergia, int defensa, int peso, int espacio) {
        setDescripcion(descripcion);
        setNombre(nombre);
        setIncrVida(incrVida);
        setIncrEnergia(incrEnergia);
        setDefensa(defensa);
        setPeso(peso);
        setEspacio(espacio);
    }

    public int getEspacio()
    {
        return espacio;
    }

    public void setEspacio(int espacio)
    {
        this.espacio = espacio;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso > 0 ? peso : 1;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = new String(descripcion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIncrVida() {
        return incrVida;
    }

    public void setIncrVida(int incrVida) {
        this.incrVida = incrVida;
    }

    public int getIncrEnergia() {
        return incrEnergia;
    }

    public void setIncrEnergia(int incrEnergia) {
        this.incrEnergia = incrEnergia;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    /**
     * Proporciona informacion sobre la armadura
     */
    public void info()
    {
        System.out.println("Armadura:");
        System.out.println("\tNombre: " + getNombre());
        System.out.println("\tDefensa: " + getDefensa());
        System.out.println("\tEspacio: " + getEspacio());
        System.out.println("\tPeso: " + getPeso());
        System.out.println("\tDefensa: " + getDefensa());
        System.out.println("\tIncremento de energia: " + getIncrEnergia());
        System.out.println("\tIncremento de vida: " + getIncrVida());
        System.out.println("\tDescripción: " + getDescripcion());
    }
}
