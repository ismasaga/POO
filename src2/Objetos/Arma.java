package Objetos;

public class Arma {
    private String nombre;
    private boolean dosManos;
    private String descripcion;
    private int dano;
    private int peso;

    public Arma() {
        setNombre("desconocida");
        setDescripcion("null");
        setDano(10);
        setDosManos(true);
        setPeso(1);
    }

    public Arma(String nombre,String descripcion, boolean dosManos,int dano, int peso) {
        setNombre(nombre);
        setDano(dano);
        setDescripcion(descripcion);
        setDosManos(dosManos);
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
