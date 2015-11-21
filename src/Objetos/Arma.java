package Objetos;

public class Arma {
    private String nombre;
    private boolean dosManos;
    private String descripcion;
    private int dano;
    private float peso; //Crashea ó ler o arquivo
    private int espacio;
    private int alcance;

    public Arma() {
        setNombre("desconocida");
        setDescripcion("null");
        setDano(10);
        setDosManos(true);
        setPeso(1);
        setEspacio(1);
        setAlcance(1);
    }

    public Arma(String nombre,String descripcion, boolean dosManos,int dano, float peso, int espacio) {
        setNombre(nombre);
        setDano(dano);
        setDescripcion(descripcion);
        setDosManos(dosManos);
        setPeso(peso);
        setEspacio(espacio);
        setAlcance(1);
    }

    /**
     * Constructor para armas parseadas de archivo
     */
    public Arma(String nombre, String descripcion, int dano, int alcance, int manos, float peso) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setDano(dano);
        setAlcance(alcance);
        if(manos == 1)
            setDosManos(false);
        else if(manos == 2)
            setDosManos(true);
        else
            System.out.println("ERROR definiendo de cuántas manos és le arma");
        setPeso(peso);
        setEspacio(1);
    }

    /**
     * Devuelve alcance del arma
     */
    public int getAlcance() {
        return alcance;
    }

    /**
     * Asigna alcance a la arma
     */
    public void setAlcance(int alcance) {
        if(alcance < 0)
            this.alcance = 0;
        else
            this.alcance = alcance;
    }

    public int getEspacio()
    {
        return espacio;
    }

    public void setEspacio(int espacio)
    {
        this.espacio = espacio;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso > 0 ? peso : 1;
    }

    /**
     * Devuelve la descripcion del arma
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna la descripcion del arma
     */
    public void setDescripcion(String descripcion) {
        if(descripcion != null)
            this.descripcion = new String(descripcion);
        else
            System.out.println("ERROR asignando la descripcion al arma");
    }

    /**
     * Devuleve true si el arma es a dos manos y false en caso contrario
     */
    public boolean isDosManos() {
        return dosManos;
    }

    /**
     * Asigna el valor booleano a la variable dosManos
     * IMPORTANTE : meter true en caso de que el arma sea de dos manos y false en caso contrario
     */
    public void setDosManos(boolean dosManos) {
        this.dosManos = dosManos;
    }

    /**
     * Devuelve el nombre del arma
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna nombre al arma
     */
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            System.out.println("ERROR asignando nombre al arma");
    }

    /**
     * Devuelve los puntos de ataque del arma
     * @return dano
     */
    public int getDano() {
        return dano;
    }

    /**
     * Asigna los puntos de daño al arma
     */
    public void setDano(int dano) {
        if(dano < 0)
            this.dano = 0;
        else
            this.dano = dano;
    }

    /**
     * Imprime informacion sobre el arma
     */
    public void info()
    {
        System.out.println("Arma:" + getNombre());
        System.out.println("\tDaño" + getDano());
        System.out.println("\tPeso" + getPeso());
        System.out.println("\tEspacio" + getEspacio());
        System.out.println("\tDescripcion" + getDescripcion());
        System.out.println("\tMano: " + (isDosManos() ? "Dos Manos" : "Una mano"));
    }
}
