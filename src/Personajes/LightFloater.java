package Personajes;

import Juego.*;
import Objetos.*;
import java.awt.*;
public class LightFloater extends Floater{
    public LightFloater(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa,punto,nombre,vidaMaxAct,energiaMaxAct);
    }

    public LightFloater(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct, Arma armaIzq, Arma armaDer, Armadura armadura) {
        super(mapa, punto, nombre, vidaMaxAct, energiaMaxAct, armaIzq, armaDer, armadura);
    }

    public LightFloater(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct, Arma armaDosM, Armadura armadura) {
        super(mapa, punto, nombre, vidaMaxAct, energiaMaxAct, armaDosM, armadura);
    }
}
