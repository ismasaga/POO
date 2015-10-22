package Juego;

/**
 * Clase enemigo. Funciones:
 * - Atacar y moverse.
 * - Detectar proximidad del personaje.
 */
public class Enemigo
{

    private int puntosVida;
    //TODO: ataque y armadura podrían ser final
    private int ataque;
    /**
     * El daño se calcula como: daño = ataque - armadura
     */
    private int armadura;

    public Enemigo(int puntosVida, int ataque, int armadura)
    {
        this.puntosVida = puntosVida > 0 ? puntosVida : 100;
        this.ataque = ataque > 0 ? ataque : 4;
        this.armadura = armadura > 0 ? armadura : 2;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
    }

    public int getArmadura() {
        return armadura;
    }

    public void atacar(Personaje personaje)
    {
        int coeficienteAtaque; //Esta variable previene que un ataque sume puntos de vida (armadura > ataque)
        coeficienteAtaque = (this.ataque - personaje.getArmadura() >= 0)? this.ataque - personaje.getArmadura() : 0;
        personaje.setPuntosVida(personaje.getPuntosVida() - coeficienteAtaque);
    }
}
