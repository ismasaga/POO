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
        String sel, linea;
        String[] cadeas;
        BufferedReader buffer;
        Scanner entradaEscaner;
        int[] tamano = new int[2];
        Mapa mapa;
        Personaje personaje = null;
        Arma armaMasBuena;

        /**
         * Leemos ficheiro quedandonos con cantas filas e columnas debe ter o mapa
         */
        try {
            buffer = new BufferedReader(new FileReader(new File("src/Juego/mapa.csv")));
            try {
                while ((linea = buffer.readLine()) != null) {
                    if(linea.length() != 0 && linea.charAt(0) != '#') {
                        cadeas = linea.split(",");
                        tamano[0] = tamano[1] = 0;
                        if(tamano[0] < Integer.parseInt(cadeas[0]))
                            tamano[0] = Integer.parseInt(cadeas[0]);
                        if(tamano[1] < Integer.parseInt(cadeas[1]))
                            tamano[1] = Integer.parseInt(cadeas[1]);
                    }
                }
                buffer.close();
            } catch (IOException ex) {
                System.out.println("ERROR leyendo el fichero : "+ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR abriendo el fichero : "+ex);
            System.exit(1);
        }
        System.out.println("Os mapa vai ter : "+tamano[0]+" filas por : "+tamano[1]+" columnas");

        entradaEscaner = new Scanner (System.in);
        sel = "A Perouta"; /** Nombre del mapa**/
        /*
        System.out.print("Introduzca el nombre del mapa : ");
        sel = entradaEscaner.nextLine();
        */

        //Sumamos un porque creamos sin facelo en base cero no constructor
        mapa = new Mapa(tamano[0]+1,tamano[1]+1,sel);

        /**
         * Tras crear o mapa poñemos a transitables as casillas que nos indica o arquivo
         */
        try {
            buffer = new BufferedReader(new FileReader(new File("src/Juego/mapa.csv")));
            try {
                while ((linea = buffer.readLine()) != null) {
                    if(linea.length() != 0 && linea.charAt(0) != '#') {
                        cadeas = linea.split(",");
                        mapa.getCelda(Integer.parseInt(cadeas[0]),Integer.parseInt(cadeas[1])).setTransitable(true);
                    }
                }
                buffer.close();
            } catch (IOException ex) {
                System.out.println("ERROR leyendo el fichero"+ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR abriendo el fichero"+ex);
            System.exit(1);
        }

        /**
         * Leo e creo as persoaxes
         */
        try {
            buffer = new BufferedReader(new FileReader(new File("src/Juego/npcs.csv")));
            try {
                while ((linea = buffer.readLine()) != null) {
                    if(linea.length() != 0 && linea.charAt(0) != '#') {
                        cadeas = linea.split(";");
                        tamano[0] = Integer.parseInt(cadeas[0].split(",")[0]);
                        tamano[1] = Integer.parseInt(cadeas[0].split(",")[1]);
                        if(!(cadeas[1].equals("enemigo") || cadeas[1].equals("jugador")))
                        {
                            System.out.println("ERROR, el tipo de personaje no está contemplado");
                        }
                        switch (cadeas[1]) {
                            case "enemigo":
                                mapa.getCelda(tamano[0], tamano[1]).setEnemigo(new Enemigo(cadeas[2],Integer.parseInt(cadeas[3]),Integer.parseInt(cadeas[4])));
                                break;
                            case "jugador":
                                personaje = new Personaje(mapa.getCelda(tamano[0],tamano[1]), cadeas[2], Integer.parseInt(cadeas[3]), Integer.parseInt(cadeas[4]));
                                break;

                        }
                    }
                }
                buffer.close();
            } catch (IOException ex) {
                System.out.println("ERROR leyendo el fichero"+ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR abriendo el fichero"+ex);
            System.exit(1);
        }

        //Asegurome de que o persoaxe sempre é creado
        if(personaje == null) {
            System.out.println("El personaje ha sido creado por defecto");
            armaMasBuena = new Arma("bazoka", "lanzamisiles 2.0", true, 80, 1, 1);
            ArrayList<Arma> armas2 = new ArrayList<>();
            armas2.add(armaMasBuena);
            personaje = new Personaje(100, 100, new Armadura("armadura rapida", "armadura de energia", 0, 10, 3, 4, 4), mapa.getCelda(5, 5), new Mochila(10, 20), 3, armas2, 100, 100, sel);
        }

        /**
         * Leo, creo e asigno os obxetos
         */
        try {
            buffer = new BufferedReader(new FileReader(new File("src/Juego/objetos.csv")));
            try {
                while ((linea = buffer.readLine()) != null) {
                    if(linea.length() != 0 && linea.charAt(0) != '#') {

                        cadeas = linea.split(";");
                        tamano[0] = Integer.parseInt(cadeas[0].split(",")[0]);
                        tamano[1] = Integer.parseInt(cadeas[0].split(",")[1]);
                        switch (cadeas[1]) {
                            case ".":
                                switch (cadeas[2]) {
                                    case "binoculares" :
                                        mapa.getCelda(tamano[0], tamano[1]).setBinoculares(new Binoculares(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6])));
                                        break;
                                    case "arma" :
                                        mapa.getCelda(tamano[0], tamano[1]).setArma(new Arma(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6]),Integer.parseInt(cadeas[7]),Float.parseFloat(cadeas[8])));
                                        break;
                                    case "armadura" :
                                        mapa.getCelda(tamano[0], tamano[1]).setArmadura(new Armadura(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6]),Integer.parseInt(cadeas[7]),Integer.parseInt(cadeas[8])));
                                        break;
                                    case "botiquin" :
                                        mapa.getCelda(tamano[0], tamano[1]).setBotiquin(new Botiquin(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Float.parseFloat(cadeas[6])));
                                        break;
                                    case "mochila" :
                                        System.out.println("Non podes deixar unha mochila nunha casilla(Por ahora)");
                                        break;
                                    default:
                                        System.out.println("ERROR, objeto desconocido(Por ahora) para una casilla");
                                }
                                break;
                            case "jugador":
                                switch (cadeas[2]) {
                                    case "binoculares" :
                                        if(personaje.getMochila() != null)
                                            personaje.getMochila().anadirBinocular(new Binoculares(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6])));
                                        else
                                            System.out.println("ERROR, el personaje intenta coger un binocular pero tiene que tener mochila para hacerlo");
                                        break;
                                    case "arma" :
                                        if(personaje.getMochila() != null)
                                            personaje.getMochila().anadirArma(new Arma(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6]),Integer.parseInt(cadeas[7]),Integer.parseInt(cadeas[8])));
                                        else
                                            System.out.println("ERROR, el personaje intenta coger un arma pero tiene que tener mochila para hacerlo");
                                        break;
                                    case "armadura" :
                                        if(personaje.getMochila() != null)
                                            personaje.getMochila().anadirArmadura(new Armadura(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6]),Integer.parseInt(cadeas[7]),Integer.parseInt(cadeas[8])));
                                        else
                                            System.out.println("ERROR, el personaje intenta coger una armadura pero tiene que tener mochila para hacerlo");
                                        break;
                                    case "botiquin" :
                                        if(personaje.getMochila() != null)
                                            personaje.getMochila().anadirBotiquin(new Botiquin(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6])));
                                        else
                                            System.out.println("ERROR, el personaje intenta coger un botiquin pero tiene que tener mochila para hacerlo");
                                        break;
                                    case "mochila" :
                                        personaje.setMochila(new Mochila(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6])));
                                        break;
                                    default:
                                        System.out.println("ERROR, intentas dar un objeto desconocido(Por ahora) al personaje");
                                }
                                break;
                            default:
                                Celda celda = mapa.getCelda(tamano[0],tamano[1]);
                                for(Enemigo enemigo : celda.getEnemigo())
                                {
                                    if(cadeas[2].equals("arma"))
                                    {
                                        ArrayList armas = new ArrayList<>();
                                        Arma arma = new Arma(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6]),Integer.parseInt(cadeas[7]),Float.parseFloat(cadeas[8]));
                                        armas.add(arma);
                                        if(enemigo.getNombre().equals(cadeas[1]))
                                        {
                                            enemigo.setArmas(armas);
                                        }
                                    }
                                    else if(cadeas[2].equals("armadura"))
                                    {
                                        enemigo.setArmadura(new Armadura(cadeas[3],cadeas[4],Integer.parseInt(cadeas[5]),Integer.parseInt(cadeas[6]),Integer.parseInt(cadeas[7]),Float.parseFloat(cadeas[8])));
                                    }
                                    else
                                    {
                                        System.out.println("Comando no encontrado");
                                    }

                                }
                        }
                    }
                }
                buffer.close();
            } catch (IOException ex) {
                System.out.println("ERROR leyendo el fichero"+ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR abriendo el fichero"+ex);
            System.exit(1);
        }

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
                    if(cadeas.length == 2 && cadeas[1].length() == 1)
                        if(cadeas[1].charAt(0) == 'r' || cadeas[1].charAt(0) == 'l' || cadeas[1].charAt(0) == 'u' || cadeas[1].charAt(0) == 'd')
                            personaje.atacar(mapa, cadeas[1].charAt(0),null); //Por defecto ataca a todos
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
                case "usar":
                    if(cadeas.length == 2)
                        personaje.usarBotiquin(cadeas[1]);
                    break;
                default:
                    System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
            }
        } while(!sel.equals("terminar"));
    }
}