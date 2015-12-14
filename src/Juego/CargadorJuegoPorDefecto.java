package Juego;

import Objetos.*;
import java.util.*;
import Personajes.*;
import java.awt.*;
public class CargadorJuegoPorDefecto implements CargadorJuego{

    @Override
    public Juego cargarJuego() {
        Consola consola = new ConsolaNormal();
        Mapa mapa = new Mapa(6,6,"Default conlleira");
        for (int i = 0; i < mapa.getAncho(); i++) {
            for (int j = 0; j < mapa.getAlto(); j++) {
                mapa.getCelda(i,j).setTransitable(true);
            }

        }
        Arma armaMala = new Arma("pistolita","pistola pequeña",false,20,1,1);
        Arma armaBuena = new Arma("pistolón","pistola grande",false,40,2,2);
        ArrayList<Arma> armas = new ArrayList<>();
        armas.add(armaBuena);
        armas.add(armaMala);

        //Arma armaMasBuena = new Arma("bazoka","lanzamisiles 2.0",true,80,1,1);
        //ArrayList<Arma> armas2 = new ArrayList<>();
        //armas2.add(armaMasBuena);
        Armadura armaduraVida = new Armadura("armadura curadora","armadura de vida",10,0,3,5,5);
        Armadura armaduraEnergy = new Armadura("armadura rapida","armadura de energia",0,10,3,4,4);

        consola.imprimir(mapa.getDescripcion());

        //mapa.getCelda(5,0).setEnemigo(new Enemigo(100, 100, armaMala, null, armaduraEnergy, "desconocido"));


        //mapa.getCelda(1,2).setEnemigo(new Enemigo(100,100,armaBuena,null,armaduraEnergy,"Fulgensio"));
        mapa.getCelda(0,3).setTransitable(false);

        mapa.getCelda(0,4).setTransitable(false);
        //mapa.getCelda(7,3).setEnemigo(new Enemigo(100,100,armas,armaduraVida));

        mapa.getCelda(1,0).setTransitable(false);

        mapa.getCelda(1,1).setTransitable(false);

        //mapa.getCelda(8,7).setEnemigo(new Enemigo(100,100,null,armaBuena,armaduraEnergy,"Enemyger"));

        mapa.getCelda(0,1).anadirObjeto(new Binocular("binoculares","mira a lo lejos",2, 3, 4));
        mapa.getCelda(0,2).anadirObjeto(new Botiquin("botiquin_grande","botiquin mas grande que tu cabeza", 1, 2, 3));
        mapa.getCelda(0,5).anadirObjeto(new Botiquin("botiquin","asf",1, 2, 3));
        mapa.getCelda(1,2).anadirObjeto(new Binocular("binocular","asf",2,3,4));
        mapa.getCelda(0,0).anadirObjeto(new Botiquin("botiquin_grande", "botiquin mas grande que tu cabeza", 1, 2, 3));
        mapa.getCelda(1,3).anadirObjeto(new Binocular("binocular","asdfx2",2,3,4));
        mapa.getCelda(1,4).anadirObjeto(new Arma("Agonia de escarcha","Estas jodido neno",false,2,3,4));
        mapa.getCelda(1,5).anadirObjeto(new Arma("Daga +1000","Porque una daga +1 estaba muy vista",false,2,3,4));
        mapa.getCelda(2,0).anadirObjeto(new Arma("Cayado de chamán","Unga unga",false,2,3,4));
        mapa.getCelda(2,1).anadirObjeto(new Arma("Ashbringer","Se me acabaron las descripciones",false,2,3,4));
        mapa.getCelda(2,2).anadirObjeto(new Arma("Emacs","ViViVi",false,6,(float)0.5,1));
        mapa.getCelda(5,5).anadirObjeto(new Arma("Vim","ViViVi",false,6,(float)0.56,1));
        mapa.getCelda(5,5).anadirObjeto(new Arma("Ed","ViViVi",false,6,(float)0.56,1));
        mapa.getCelda(5,5).anadirObjeto(new Arma("Ashbringer","Se me acabaron las descripciones",false,2,3,4));
        mapa.getCelda(5,5).anadirObjeto(new Arma("Nano","ViViVi",false,6,(float)0.56,1));
        mapa.getCelda(5,5).anadirObjeto(new Arma("Gedit","ViViVi",false,6,(float)0.56,1));

        mapa.getCelda(3,3).setEnemigo(new LightFloater(mapa,new Point(3,3),"Múrloc",100000,2));
        mapa.getCelda(5,3).setEnemigo(new LightFloater(mapa,new Point(3,3),"Múrloc_1",100000,2));
        mapa.getCelda(5,3).setEnemigo(new LightFloater(mapa,new Point(3,3),"Múrloc_2",100000,2));
        mapa.getCelda(5,3).setEnemigo(new LightFloater(mapa,new Point(3,3),"Múrloc_3",100000,2));
        mapa.getCelda(5,3).setEnemigo(new LightFloater(mapa,new Point(3,3),"Múrloc_4",100000,2));
        mapa.getCelda(3,4).setEnemigo(new LightFloater(mapa,new Point(3,4),"Capitan esparrago",1,2));
        mapa.getCelda(3,5).setEnemigo(new HeavyFloater(mapa,new Point(3,5),"Sida embotellado",1,2));
        mapa.getCelda(4,0).setEnemigo(new HeavyFloater(mapa,new Point(4,0),"Bill gates",1,2));

        //mapa.getCelda(3,3).setEnemigo(new Enemigo(100,100,armas,armaduraEnergy));

        Celda celdaActual = mapa.getCelda(5,5);
        Jugador personaje = null;
        switch (consola.leer("Que tipo de personaje desea?")) {
            case "marine":
                personaje = new Marine(mapa, new Point(celdaActual.getPunto()), "Chiquito",1000000,1000000000);
                celdaActual.setJugador(personaje);
                break;
            case "francotirador":
                personaje = new Francotirador(mapa, new Point(celdaActual.getPunto()), "Chiquito",1000000,1000000000);
                celdaActual.setJugador(personaje);
                break;
            case "zapador":
                personaje = new Zapador(mapa, new Point(celdaActual.getPunto()), "Chiquito",1000000,1000000000);
                celdaActual.setJugador(personaje);
                break;
            default:
                consola.imprimir("Tipo de personaje incorrecto, mire la ayuda para saber mas.");
        }

        Juego juego = new Juego();
        juego.setPersonaje(personaje);
        juego.setMapa(mapa);
        return juego;
    }
}
