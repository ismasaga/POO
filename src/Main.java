import Juego.*;
import Objetos.*;

import java.io.*;
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

        Mapa mapa;
        Personaje personaje = null;


        /**
         * Leemos ficheiro quedandonos con cantas filas e columnas debe ter o mapa
         */
        Parser parser = new Parser();
        Bundle bundle = new Bundle();
        bundle = parser.Parse("src/Juego/mapa.csv","src/Juego/npcs.csv","src/Juego/objetos.csv");

        mapa = bundle.getMapa();
        personaje = bundle.getPersonaje();

        entradaEscaner = new Scanner(System.in);
        /*Arma armaMala = new Arma("pistolita","pistola pequeña",false,20,1,1);
        Arma armaBuena = new Arma("pistolón","pistola grande",false,40,2,2);
        ArrayList<Arma> armas = new ArrayList<>();
        armas.add(armaBuena);
        armas.add(armaMala);

        /*Arma armaMasBuena = new Arma("bazoka","lanzamisiles 2.0",true,80,1,1);
        ArrayList<Arma> armas2 = new ArrayList<>();
        armas2.add(armaMasBuena);*/
        /*Armadura armaduraVida = new Armadura("armadura curadora","armadura de vida",10,0,3,5,5);
        //Armadura armaduraEnergy = new Armadura("armadura rapida","armadura de energia",0,10,3,4,4);

        System.out.println(mapa.getDescripcion());

        mapa.getCelda(0,0).setBotiquin(new Botiquin("botiquin_grande", "botiquin mas grande que tu cabeza", 1, 2, 3));
        mapa.getCelda(5,0).setEnemigo(new Enemigo(100, 100, armaMala, null, armaduraEnergy, "desconocido"));

        mapa.getCelda(1,1).setBinoculares(new Binoculares("binoculares","mira a lo lejos",2, 3, 4));
        mapa.getCelda(6,1).setBotiquin(new Botiquin("botiquin_grande","botiquin mas grande que tu cabeza", 1, 2, 3));

        mapa.getCelda(1,2).setEnemigo(new Enemigo(100,100,armaBuena,null,armaduraEnergy,"Fulgensio"));
        mapa.getCelda(5,2).setTransitable(false);

        mapa.getCelda(3,3).setTransitable(false);
        mapa.getCelda(7,3).setEnemigo(new Enemigo(100,100,armas,armaduraVida));

        mapa.getCelda(4,5).setBotiquin(new Botiquin("botiquin","asf",1, 2, 3));
        mapa.getCelda(6,5).setTransitable(false);

        mapa.getCelda(1,6).setTransitable(false);
        mapa.getCelda(7,6).setBinoculares(new Binoculares("binocular","asf",2,3,4));

        mapa.getCelda(8,7).setEnemigo(new Enemigo(100,100,null,armaBuena,armaduraEnergy,"Enemyger"));

        mapa.getCelda(1,8).setBinoculares(new Binoculares("binocular","asdfx2",2,3,4));

        mapa.getCelda(3,3).setEnemigo(new Enemigo(100,100,armas,armaduraEnergy));

        //Celda celdaActual = mapa.getCelda(5,5);
        System.out.print("Introduzca el nombre del personaje : ");
        sel = entradaEscaner.nextLine();*/

        do {
            /**
             * Si se acaba la energía no se hace nada (ni imprimir)
             **/
            if(personaje.getPuntosVida() == 0)
            {
                System.out.println("Has muerto pringao.");
                System.exit(0);
            }
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
                    if(cadeas.length == 1)
                    {
                            personaje.mirar(mapa,0,"",null);
                    }
                    if(cadeas.length == 2)
                    {
                        if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l' || cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd')
                        {
                            if(Character.isDigit(cadeas[1].charAt(0)))
                            personaje.mirar(mapa,Character.getNumericValue(cadeas[1].charAt(0)),cadeas[1].charAt(1) + "",null);
                        }
                    }
                    else if(cadeas.length == 3)
                    {
                        if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l' || cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd')
                        {
                            if(Character.isDigit(cadeas[1].charAt(0)))
                                personaje.mirar(mapa,Character.getNumericValue(cadeas[1].charAt(0)),cadeas[1].charAt(1) + "",cadeas[2]);
                        }
                    }
                    break;
                case "atacar":
                    if(cadeas.length == 2 && cadeas[1].length() == 2)
                        if(cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l' )
                        {
                            personaje.atacar(mapa, Character.getNumericValue(cadeas[1].charAt(0)), cadeas[1].charAt(1), 'q', null);
                        }
                        else if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd')
                        {
                            personaje.atacar(mapa, Character.getNumericValue(cadeas[1].charAt(0)), 'q', cadeas[1].charAt(1), null);
                        }
                        else
                            System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
                    else
                        System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
                    break;
                case "pasar":
                    personaje.pasar(mapa,personaje);
                    break;
                case "terminar":
                    break;
                case "descripcion":
                    System.out.println(mapa.getDescripcion());
                    break;
                case "estado":
                    personaje.info();
                    break;
                case "inventario":
                    personaje.ojearInventario();
                    break;
                case "coger":
                    /**Esperemos que no haya armas que se llamen igual que los botiquines**/
                    personaje.cogerArma(cadeas[1]);
                    personaje.cogerArmadura(cadeas[1]);
                    personaje.cogerBinocular(cadeas[1]);
                    personaje.cogerBotiquin(cadeas[1]);
                    break;
                case "desequipar":
                    if(cadeas[1].equals("arma"))
                        personaje.desequiparArma(cadeas[2]);
                    break;
                case "equipar":
                    if(cadeas.length == 3)
                    {
                        if (cadeas[1].equals("arma"))
                            personaje.equiparArma(cadeas[2], "");
                        if (cadeas[1].equals("armadura"))
                            personaje.equiparArmadura(cadeas[2]);
                        if (cadeas[1].equals("binocular"))
                            personaje.equiparBinocular(cadeas[2]);
                    }
                    else if (cadeas.length == 4)
                    {
                        if (cadeas[1].equals("arma"))
                            personaje.equiparArma(cadeas[2],cadeas[3]);
                    }
                    break;
                case "usar":
                    if(cadeas.length == 2)
                        personaje.usarBotiquin(cadeas[1]);
                    break;
                case "cargar":
                    if(cadeas.length == 2)
                    {
                        bundle = parser.Parse(cadeas[1]+"/mapa.csv",cadeas[1]+"/npcs.csv",cadeas[1]+"/objetos.csv");
                        personaje = bundle.getPersonaje();
                        mapa = bundle.getMapa();
                    }
                    break;
                default:
                    System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
            }
        } while(!sel.equals("terminar"));
    }
}