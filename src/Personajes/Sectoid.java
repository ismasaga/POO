package Personajes;

import Juego.*;
import java.awt.*;
import Objetos.*;
public final class Sectoid extends Enemigo{
    public Sectoid(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa,punto,nombre,vidaMaxAct,energiaMaxAct);
    }

    public Sectoid(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct, Arma armaIzq, Arma armaDer, Armadura armadura) {
        super(mapa, punto, nombre, vidaMaxAct, energiaMaxAct, armaIzq, armaDer, armadura);
    }

    public Sectoid(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct, Arma armaDosM, Armadura armadura) {
        super(mapa, punto, nombre, vidaMaxAct, energiaMaxAct, armaDosM, armadura);
    }
}
