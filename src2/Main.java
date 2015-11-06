import Juego.*;
import Objetos.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Nota: los atributos final no necesitan (ni permiten) setter pues sólo se pueden inicializar en el constructor
 * de su respectiva clase.
 */
public class Main {

    public static void main(String[] args) {
        String sel;
        String[] cadeas;
        Scanner entradaEscaner;

        entradaEscaner = new Scanner (System.in);
        sel = "A Perouta"; /** Nombre del mapa**/
        /*
        System.out.print("Introduzca el nombre del mapa : ");
        sel = entradaEscaner.nextLine();
        */

        Arma armaMala = new Arma("pistolita",false,20);
        Arma armaBuena = new Arma("pistolón",false,40);
        ArrayList<Arma> armas = new ArrayList<>();
        armas.add(armaBuena);
        armas.add(armaMala);
        Arma armaMasBuena = new Arma("bazoka",true,80);
        ArrayList<Arma> armas2 = new ArrayList<>();
        armas2.add(armaMasBuena);
        Armadura armaduraVida = new Armadura("armadura de vida",10,0,3);
        Armadura armaduraEnergy = new Armadura("armadura de energia",0,10,3);

        Mapa mapa = new Mapa(10,10,sel);
        System.out.println(mapa.getDescripcion());
        mapa.getCelda(0,0).setBotiquin(new Botiquin(1,2,3));
        mapa.getCelda(9,0).setEnemigo(new Enemigo(100,100,armaMala,null,armaduraEnergy,"desconocido"));

        mapa.getCelda(1,1).setBinoculares(new Binoculares(2,3,4));
        mapa.getCelda(6,1).setBotiquin(new Botiquin(1, 2, 3));

        mapa.getCelda(1,2).setEnemigo(new Enemigo(100,100,armaBuena,null,armaduraEnergy,"Fulgensio"));
        mapa.getCelda(5,2).setTransitable(false);

        mapa.getCelda(3,3).setTransitable(false);
        mapa.getCelda(7,3).setEnemigo(new Enemigo(100,100,armas,armaduraVida));

        mapa.getCelda(4,5).setBotiquin(new Botiquin(1, 2, 3));
        mapa.getCelda(6,5).setTransitable(false);

        mapa.getCelda(1,6).setTransitable(false);
        mapa.getCelda(7,6).setBinoculares(new Binoculares(2,3,4));

        mapa.getCelda(8,7).setEnemigo(new Enemigo(100,100,null,armaBuena,armaduraEnergy,"Enemyger"));

        mapa.getCelda(1,8).setBinoculares(new Binoculares(2,3,4));

        mapa.getCelda(3,9).setEnemigo(new Enemigo(100,100,armas,armaduraEnergy));

        Celda celdaActual = mapa.getCelda(5,5);
        System.out.print("Introduzca el nombre del personaje : ");
        sel = entradaEscaner.nextLine();
        Personaje personaje = new Personaje(100,100,armaduraEnergy,celdaActual,new Mochila(10,20),3,armas2,100,100,sel);
        do {
            /**
             * Si se acaba la energía no se hace nada (ni imprimir)
             **/
            mapa.imprimir(personaje);
            System.out.println(personaje.getNombre()+"[Vida: " + personaje.getPuntosVida() + " Energia: " + personaje.getEnergia() + "]");
            System.out.print(">");
            sel =  entradaEscaner.nextLine();
            cadeas = sel.split(" ");
            switch (cadeas[0]) {
                case "ayuda":
                    System.out.println("/**********************************************/");
                    System.out.println("/- Introduce 'terminar' para finalizar el juego/");
                    System.out.println("/- Introduce 'mover' seguido de un espacio y la/");
                    System.out.println("/  dirección :                                 /");
                    System.out.println("/  *r=derecha *l=izquierda *u=arriba *d=abajo  /");
                    System.out.println("/     ejemplo : 'mover 3u' = 'mover 3 arriba'  /");
                    System.out.println("/- Introduce 'atacar' seguido de un espacio y  /");
                    System.out.println("/  las casillas con la dirección(como mover)   /");
                    System.out.println("/- Introudce 'pasar' para pasar de turno y     /");
                    System.out.println("/  regenerar tu energia                        /");
                    System.out.println("/- Introduce 'mirar' para obtener todos los    /");
                    System.out.println("/  objetos de tu casilla actual.               /");
                    System.out.println("/- Introduce descripcion para ver a descripción/");
                    System.out.println("/  del mapa(su nombre y dimensiones)           /");
                    System.out.println("/**********************************************/");
                    break;
                case "mover":
                    if(cadeas.length == 2 && cadeas[1].length() == 1)
                        if(cadeas[1].charAt(0) == 'r' || cadeas[1].charAt(0) == 'l' || cadeas[1].charAt(0) == 'u' || cadeas[1].charAt(0) == 'd')
                            personaje.mover(mapa,1,cadeas[1].charAt(0));
                        else
                            System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
                    else
                        System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
                    break;
                case "mirar":
                    personaje.mirar();
                    break;
                case "atacar":
                    if(cadeas.length == 2 && cadeas[1].length() == 1)
                        if(cadeas[1].charAt(0) == 'r' || cadeas[1].charAt(0) == 'l' || cadeas[1].charAt(0) == 'u' || cadeas[1].charAt(0) == 'd')
                            personaje.atacar(mapa, cadeas[1].charAt(0));
                        else
                            System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
                    else
                        System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
                    break;
                case "pasar":
                    personaje.pasar();
                    break;
                case "terminar":
                    break;
                case "descripcion":
                    System.out.println(mapa.getDescripcion());
                    break;
                default:
                    System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
            }
        } while(!sel.equals("terminar"));
    }
}
