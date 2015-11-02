package Juego;

/**
 * Clase enemigo. Funciones:
 * - Atacar y moverse.
 * - Detectar proximidad del personaje.
 *
 * A destacar:
 *
 * VIDA_MAXIMA, ataque y armadura son constantes pues no hay manera de modificarlos (con elementos
 * del juego )en esta versión.
 */
public class Enemigo
{
    private final int VIDA_MAXIMA;
    private int puntosVida;
    //TODO: ataque y armadura podrían ser final
    private final int ataque;
    /**
     * El daño se calcula como: daño = ataque - armadura
     */
    private final int armadura;
    private String nombre;


    public Enemigo(int VIDA_MAXIMA, int puntosVida, int ataque, int armadura, String nombre)
    {
        this.VIDA_MAXIMA = VIDA_MAXIMA > 0 ? VIDA_MAXIMA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= VIDA_MAXIMA) ? puntosVida : 100;
        this.ataque = ataque > 0 ? ataque : 4;
        this.armadura = armadura > 0 ? armadura : 2;
        setNombre(nombre);
    }

    public Enemigo(int VIDA_MAXIMA, int puntosVida, int ataque, int armadura)
    {
        this.VIDA_MAXIMA = VIDA_MAXIMA > 0 ? VIDA_MAXIMA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= VIDA_MAXIMA) ? puntosVida : 100;
        this.ataque = ataque > 0 ? ataque : 4;
        this.armadura = armadura > 0 ? armadura : 2;
        setNombre("desconocido");
    }

    public int getVIDA_MAXIMA()
    {
        return VIDA_MAXIMA;
    }

    public int getAtaque()
    {
        return ataque;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        if(puntosVida < 0)
        {
            this.puntosVida = 0;
            return;
        }
        if (puntosVida > VIDA_MAXIMA)
        {
            this.puntosVida = VIDA_MAXIMA;
            return;
        }
        this.puntosVida = puntosVida;
    }

    public int getArmadura() {
        return armadura;
    }

    public void atacar(Personaje personaje)
    {
        int coeficienteAtaque; //Esta variable previene que un ataque sume puntos de vida (armadura > ataque)
        coeficienteAtaque = (this.ataque - personaje.getArmadura() >= 0) ? this.ataque - personaje.getArmadura() : 0;
        personaje.setPuntosVida(personaje.getPuntosVida() - coeficienteAtaque);
    }

    /**
     * Asigna nombre al enemigo
     * @param nombre
     */
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            System.out.println("ERROR asignando nombre al enemigo");
    }

    /**
     * Devuelve el nombre del enemigo, en caso de que no fuese definido devolvería null
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
}
