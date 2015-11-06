package Objetos;

public class Arma {
    private String nombre;
    private boolean dosManos;
    private int dano;

    public Arma() {
        setNombre("desconocida");
        setDano(10);
        setDosManos(true);
    }

    public Arma(String nombre,boolean dosManos,int dano) {
        setNombre(nombre);
        setDano(dano);
        setDosManos(dosManos);
    }

    public boolean isDosManos() {
        return dosManos;
    }

    public void setDosManos(boolean dosManos) {
        this.dosManos = dosManos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve los puntos de ataque del arma
     * @return dano
     */
    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }
}
