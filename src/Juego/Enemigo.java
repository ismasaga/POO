package Juego;

/**
 * Clase enemigo. Funciones:
 * - Atacar y moverse.
 * - Detectar proximidad del personaje.
 */
public class Enemigo
{

    private final int VIDA_MAXIMA;
    private int puntosVida;
    //TODO: ataque y armadura podrían ser final
    private int ataque;
    /**
     * El daño se calcula como: daño = ataque - armadura
     */
    private int armadura;

    public Enemigo(int VIDA_MAXIMA, int puntosVida, int ataque, int armadura)
    {
        this.VIDA_MAXIMA = VIDA_MAXIMA > 0 ? VIDA_MAXIMA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= VIDA_MAXIMA) ? puntosVida : 100;
        this.ataque = ataque > 0 ? ataque : 4;
        this.armadura = armadura > 0 ? armadura : 2;
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
        coeficienteAtaque = (this.ataque - personaje.getArmadura() >= 0)? this.ataque - personaje.getArmadura() : 0;
        personaje.setPuntosVida(personaje.getPuntosVida() - coeficienteAtaque);
    }
}
