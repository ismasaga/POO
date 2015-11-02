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

    public Botiquin(int curacion, int peso, int espacio)
    {
        this.curacion = curacion > 0 ? curacion : 10;
        this.peso = peso > 0 ? peso : 2;
        this.espacio = espacio > 0 ? espacio : 1;
    }

    public Botiquin(int curacion)
    {
        this.curacion = curacion > 0 ? curacion : 10;
        this.peso = 2;
        this.espacio = 1;
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

    //TODO: implementar constructor por defecto

    public void usar(Personaje personaje)
    {
        personaje.setPuntosVida(personaje.getPuntosVida() + curacion);
    }


}
