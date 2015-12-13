package Juego;

import Objetos.Arma;
import Objetos.Armadura;
import Objetos.Binocular;
import Objetos.Botiquin;
import Personajes.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Clase auxiliar.
 * Lee un archivo y devuelve el personaje y el mapa creado a partir de el.
 */
public class Parser {
    public Bundle Parse(String rutaMapa, String rutaNpcs, String rutaObjetos, Consola consola) {
        String linea;
        String[] cadeas;
        BufferedReader buffer;
        int[] tamano = new int[2];
        Arma armaMasBuena;
        Mapa mapa;
        Jugador personaje = null;
        Bundle bundle = new Bundle();

        //Leo o tamanho que vai ter o mapa
        try {
            buffer = new BufferedReader(new FileReader(new File(rutaMapa)));
            try {
                while ((linea = buffer.readLine()) != null) {
                    if (linea.length() != 0 && linea.charAt(0) != '#') {
                        cadeas = linea.split(",");
                        tamano[0] = tamano[1] = 0;
                        if (tamano[0] < Integer.parseInt(cadeas[0]))
                            tamano[0] = Integer.parseInt(cadeas[0]);
                        if (tamano[1] < Integer.parseInt(cadeas[1]))
                            tamano[1] = Integer.parseInt(cadeas[1]);
                    }
                }
                buffer.close();
            } catch (IOException ex) {
                consola.imprimir("ERROR leyendo el fichero : " + ex);
                consola.imprimir("Loading defaults");
                bundle = loadDefault(consola);
                return bundle;
            }
        } catch (FileNotFoundException ex) {
            consola.imprimir("ERROR abriendo el fichero : " + ex);
            consola.imprimir("Loading defaults");
            bundle = loadDefault(consola);
            return bundle;
        }

        consola.imprimir("El mapa va a tener : " + tamano[0] + " filas por : " + tamano[1] + " columnas");

        /**Sumamos un porque creamos sin facelo en base cero no constructor**/
        mapa = new Mapa(tamano[0] + 1, tamano[1] + 1, "A Perouta");

        /**
         * Tras crear o mapa poñemos a transitables as casillas que nos indica o arquivo
         */
        try {
            buffer = new BufferedReader(new FileReader(new File(rutaMapa)));
            try {
                while ((linea = buffer.readLine()) != null) {
                    if (linea.length() != 0 && linea.charAt(0) != '#') {
                        cadeas = linea.split(",");
                        mapa.getCelda(Integer.parseInt(cadeas[0]), Integer.parseInt(cadeas[1])).setTransitable(true);
                    }
                }
                buffer.close();
            } catch (IOException ex) {
                consola.imprimir("ERROR leyendo el fichero" + ex);
                consola.imprimir("Loading defaults");
                bundle = loadDefault(consola);
                return bundle;
            }
        } catch (FileNotFoundException ex) {
            consola.imprimir("ERROR abriendo el fichero" + ex);
            consola.imprimir("Loading defaults");
            bundle = loadDefault(consola);
            return bundle;
        }

        /**
         * Leo e creo as persoaxes
         */
        try {
            buffer = new BufferedReader(new FileReader(new File(rutaNpcs)));
            try {
                while ((linea = buffer.readLine()) != null) {
                    if (linea.length() != 0 && linea.charAt(0) != '#') {
                        cadeas = linea.split(";");
                        tamano[0] = Integer.parseInt(cadeas[0].split(",")[0]);
                        tamano[1] = Integer.parseInt(cadeas[0].split(",")[1]);
                        if (!(cadeas[1].equals("enemigo") || cadeas[1].equals("jugador"))) {
                            consola.imprimir("ERROR, el tipo de personaje no está contemplado");
                        }
                        switch (cadeas[1]) {
                            case "enemigo":
                                mapa.getCelda(tamano[0], tamano[1]).setEnemigo(new Enemigo(mapa, new Point(tamano[0],tamano[1]), cadeas[2], Integer.parseInt(cadeas[3]), Integer.parseInt(cadeas[4])));
                                break;
                            case "jugador":
                                switch (consola.leer("Que tipo de personaje desea?")) {
                                    case "marine":
                                        personaje = new Marine(mapa, new Point(tamano[0],tamano[1]), cadeas[2], Integer.parseInt(cadeas[3]), Integer.parseInt(cadeas[4]));
                                        mapa.getCelda(tamano[0], tamano[1]).setJugador(personaje);
                                        break;
                                    case "francotirador":
                                        personaje = new Francotirador(mapa ,new Point(tamano[0],tamano[1]), cadeas[2], Integer.parseInt(cadeas[3]), Integer.parseInt(cadeas[4]));
                                        mapa.getCelda(tamano[0], tamano[1]).setJugador(personaje);
                                        break;
                                    case "zapador":
                                        personaje = new Zapador(mapa, new Point(tamano[0],tamano[1]), cadeas[2], Integer.parseInt(cadeas[3]), Integer.parseInt(cadeas[4]));
                                        mapa.getCelda(tamano[0], tamano[1]).setJugador(personaje);
                                        break;
                                    default:
                                        consola.imprimir("Tipo de personaje incorrecto, mire la ayuda para saber mas.");
                                }
                                break;
                        }
                    }
                }
                buffer.close();
            } catch (IOException ex) {
                consola.imprimir("ERROR leyendo el fichero" + ex);
                consola.imprimir("Loading defaults");
                bundle = loadDefault(consola);
                return bundle;
            }
        } catch (FileNotFoundException ex) {
            consola.imprimir("ERROR abriendo el fichero" + ex);
            consola.imprimir("Loading defaults");
            bundle = loadDefault(consola);
            return bundle;
        }

        //Asegurome de que o persoaxe sempre é creado
        if (personaje == null) {
            //consola.imprimir("El personaje ha sido creado por defecto");
            consola.imprimir("Error, el personaje no ha sido creado, error fatal, juego abortado");
            System.exit(0);
            //armaMasBuena = new Arma("bazoka", "lanzamisiles 2.0", true, 80, 1, 1);
            //ArrayList<Arma> armas2 = new ArrayList<>();
            //armas2.add(armaMasBuena);
            //personaje = new Personaje(100, 100, new Armadura("armadura rapida", "armadura de energia", 0, 10, 3, 4, 4), mapa.getCelda(5, 5), new Mochila(10, 20), 3, armas2, 100, 100, sel);
        }

        /**
         * Leo, creo e asigno os obxetos
         */
        try {
            buffer = new BufferedReader(new FileReader(new File(rutaObjetos)));
            try {
                while ((linea = buffer.readLine()) != null) {
                    if (linea.length() != 0 && linea.charAt(0) != '#') {
                        cadeas = linea.split(";");
                        tamano[0] = Integer.parseInt(cadeas[0].split(",")[0]);
                        tamano[1] = Integer.parseInt(cadeas[0].split(",")[1]);
                        switch (cadeas[1]) {
                            case ".":
                                switch (cadeas[2]) {
                                    case "binoculares":
                                        mapa.getCelda(tamano[0], tamano[1]).anadirObjeto(new Binocular(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6])));
                                        break;
                                    case "arma":
                                        mapa.getCelda(tamano[0], tamano[1]).anadirObjeto(new Arma(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Float.parseFloat(cadeas[8])));
                                        break;
                                    case "armadura":
                                        mapa.getCelda(tamano[0], tamano[1]).anadirObjeto(new Armadura(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Integer.parseInt(cadeas[8])));
                                        break;
                                    case "botiquin":
                                        mapa.getCelda(tamano[0], tamano[1]).anadirObjeto(new Botiquin(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Float.parseFloat(cadeas[6])));
                                        break;
                                    case "mochila":
                                        System.out.println("Non podes deixar unha mochila nunha casilla(Por ahora)");
                                        break;
                                    default:
                                        System.out.println("ERROR, objeto desconocido(Por ahora) para una casilla");
                                }
                                break;
                            case "jugador":
                                switch (cadeas[2]) {
                                    case "binoculares":
                                        if (personaje.getMochila() != null)
                                            personaje.getMochila().anadirBinocular(new Binocular(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6])));
                                        else
                                            System.out.println("ERROR, el personaje intenta coger un binocular pero tiene que tener mochila para hacerlo");
                                        break;
                                    case "arma":
                                        if (personaje.getMochila() != null)
                                            personaje.getMochila().anadirArma(new Arma(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Integer.parseInt(cadeas[8])));
                                        else
                                            System.out.println("ERROR, el personaje intenta coger un arma pero tiene que tener mochila para hacerlo");
                                        break;
                                    case "armadura":
                                        if (personaje.getMochila() != null)
                                            personaje.getMochila().anadirArmadura(new Armadura(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Integer.parseInt(cadeas[8])));
                                        else
                                            System.out.println("ERROR, el personaje intenta coger una armadura pero tiene que tener mochila para hacerlo");
                                        break;
                                    case "botiquin":
                                        if (personaje.getMochila() != null)
                                            personaje.getMochila().anadirBotiquin(new Botiquin(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6])));
                                        else
                                            System.out.println("ERROR, el personaje intenta coger un botiquin pero tiene que tener mochila para hacerlo");
                                        break;
                                    case "mochila":
                                        personaje.setMochila(new Mochila(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6])));
                                        break;
                                    default:
                                        System.out.println("ERROR, intentas dar un objeto desconocido(Por ahora) al personaje");
                                }
                                break;
                            default:
                                Celda celda = mapa.getCelda(tamano[0], tamano[1]);
                                for (Enemigo enemigo : celda.getEnemigo()) {
                                    switch (cadeas[2]) {
                                        case "arma":
                                            ArrayList armas = new ArrayList<>();
                                            Arma arma = new Arma(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Float.parseFloat(cadeas[8]));
                                            armas.add(arma);
                                            if (enemigo.getNombre().equals(cadeas[1])) {
                                                enemigo.setArmas(armas);
                                            }
                                            break;
                                        case "armadura":
                                            enemigo.setArmadura(new Armadura(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Float.parseFloat(cadeas[8])));
                                            break;
                                        default:
                                            System.out.println("Comando no encontrado");
                                            break;
                                    }

                                }
                        }
                    }
                }
                buffer.close();
            } catch (IOException ex) {
                System.out.println("ERROR leyendo el fichero" + ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR abriendo el fichero" + ex);
            System.exit(1);
        }

        bundle.setMapa(mapa);
        bundle.setPersonaje(personaje);

        return bundle;
    }

    /**
     * Metodo que carga o xogo por defecto
     */
    public Bundle loadDefault (Consola consola) {
        Mapa mapa = new Mapa(20,20,"Default conlleira");

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

        System.out.println(mapa.getDescripcion());

        mapa.getCelda(0,0).anadirObjeto(new Botiquin("botiquin_grande", "botiquin mas grande que tu cabeza", 1, 2, 3));
        //mapa.getCelda(5,0).setEnemigo(new Enemigo(100, 100, armaMala, null, armaduraEnergy, "desconocido"));

        mapa.getCelda(1,1).anadirObjeto(new Binocular("binoculares","mira a lo lejos",2, 3, 4));
        mapa.getCelda(6,1).anadirObjeto(new Botiquin("botiquin_grande","botiquin mas grande que tu cabeza", 1, 2, 3));

        //mapa.getCelda(1,2).setEnemigo(new Enemigo(100,100,armaBuena,null,armaduraEnergy,"Fulgensio"));
        mapa.getCelda(5,2).setTransitable(false);

        mapa.getCelda(3,3).setTransitable(false);
        //mapa.getCelda(7,3).setEnemigo(new Enemigo(100,100,armas,armaduraVida));

        mapa.getCelda(4,5).anadirObjeto(new Botiquin("botiquin","asf",1, 2, 3));
        mapa.getCelda(6,5).setTransitable(false);

        mapa.getCelda(1,6).setTransitable(false);
        mapa.getCelda(7,6).anadirObjeto(new Binocular("binocular","asf",2,3,4));

        //mapa.getCelda(8,7).setEnemigo(new Enemigo(100,100,null,armaBuena,armaduraEnergy,"Enemyger"));

        mapa.getCelda(1,8).anadirObjeto(new Binocular("binocular","asdfx2",2,3,4));

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

        Bundle bundle = new Bundle();
        bundle.setPersonaje(personaje);
        bundle.setMapa(mapa);
        return bundle;
    }
}
