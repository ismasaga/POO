package Objetos;

public class Armadura {
    private String nombre;
    private int incrVida,incrEnergia,defensa;

    public Armadura() {
        setNombre("desconocida");
        setIncrVida(0);
        setIncrEnergia(0);
        setDefensa(0);
    }

    public Armadura(String nombre, int incrVida, int incrEnergia, int defensa) {
        setNombre(nombre);
        setIncrVida(incrVida);
        setIncrEnergia(incrEnergia);
        setDefensa(defensa);
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
