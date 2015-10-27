import Juego.*;
import Objetos.Binoculares;
import Objetos.Botiquin;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String sel;
        String[] cadeas;

        Mapa mapa = new Mapa(10,10);
        mapa.getCelda(0,0).setBotiquin(new Botiquin(1,2,3));
        mapa.getCelda(9,0).setEnemigo(new Enemigo(100,2,3));

        mapa.getCelda(1,1).setBinoculares(new Binoculares(2,3,4));
        mapa.getCelda(6,1).setBotiquin(new Botiquin(1, 2, 3));

        mapa.getCelda(1,2).setEnemigo(new Enemigo(100,2,3));
        mapa.getCelda(5,2).setTransitable(false);

        mapa.getCelda(3,3).setTransitable(false);
        mapa.getCelda(7,3).setEnemigo(new Enemigo(100,2,3));

        mapa.getCelda(4,5).setBotiquin(new Botiquin(1, 2, 3));
        mapa.getCelda(6,5).setTransitable(false);

        mapa.getCelda(1,6).setTransitable(false);
        mapa.getCelda(7,6).setBinoculares(new Binoculares(2,3,4));

        mapa.getCelda(8,7).setEnemigo(new Enemigo(100,2,3));

        mapa.getCelda(1,8).setBinoculares(new Binoculares(2,3,4));

        mapa.getCelda(3,9).setEnemigo(new Enemigo(100,2,3));

        Celda celdaActual = mapa.getCelda(5,5);
        Personaje personaje = new Personaje(100,100,2,celdaActual,new Mochila(10,20),3,2,100);
        mapa.imprimir(personaje);
        Scanner entradaEscaner = new Scanner (System.in);
        do {
            System.out.print(">");
            sel =  entradaEscaner.nextLine();
            cadeas = sel.split(" ");
            switch (cadeas[0]) {
                case "axuda":
                    System.out.println("/*********************************************/");
                    System.out.println("/-Introduce 'terminar' para finalizar el juego/");
                    System.out.println("/-Introduce 'mover' seguido de un espacio y el/");
                    System.out.println("/  número de casillas y dirección :           /");
                    System.out.println("/  *r=derecha *l=izquierda *u=arriba *d=abajo /");
                    System.out.println("/     ejemplo : 'mover 3u' = 'mover 3 arriba' /");
                    System.out.println("/*********************************************/");
                    break;
                case "mover":
                    if(cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l' || cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd')
                        personaje.mover(mapa,cadeas[1].charAt(0),cadeas[1].charAt(1));
                    else
                        System.out.println("A opción seleccionada non existe, seleccione axuda para saber mais.");
                case "mirar":
                    personaje.mirar();
                    break;
                case "terminar":
                    break;
                default:
                    System.out.println("A opción seleccionada non existe, seleccione axuda para saber mais.");
            }
        } while(!sel.equals("terminar"));
    }
}
