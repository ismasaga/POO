package Objetos;

public class Armadura {
    private String nombre;
    private int incrVida,incrEnergia,defensa;
    private String descripcion;
    private int peso;

    public Armadura() {
        setNombre("desconocida");
        setDescripcion("null");
        setIncrVida(0);
        setIncrEnergia(0);
        setDefensa(0);
        setPeso(1);
    }

    public Armadura(String nombre, String descripcion, int incrVida, int incrEnergia, int defensa, int peso) {
        setDescripcion(descripcion);
        setNombre(nombre);
        setIncrVida(incrVida);
        setIncrEnergia(incrEnergia);
        setDefensa(defensa);
        setPeso(peso);
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
}
