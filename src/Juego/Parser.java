package Juego;

import Juego.*;
import Objetos.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by efren on 23/11/15.
 */


/**
 * Clase auxiliar.
 * Lee un archivo y devuelve el personaje y el mapa creado a partir de el.
 */
public class Parser
{
    public Bundle Parse(String rutaMapa, String rutaNpcs, String rutaObjetos)
    {
        String sel, linea;
        String[] cadeas;
        BufferedReader buffer;
        int[] tamano = new int[2];
        Arma armaMasBuena;

        Mapa mapa;
        Personaje personaje = null;

        try
        {
            buffer = new BufferedReader(new FileReader(new File(rutaMapa)));
            try
            {
                while ((linea = buffer.readLine()) != null)
                {
                    if (linea.length() != 0 && linea.charAt(0) != '#')
                    {
                        cadeas = linea.split(",");
                        tamano[0] = tamano[1] = 0;
                        if (tamano[0] < Integer.parseInt(cadeas[0]))
                            tamano[0] = Integer.parseInt(cadeas[0]);
                        if (tamano[1] < Integer.parseInt(cadeas[1]))
                            tamano[1] = Integer.parseInt(cadeas[1]);
                    }
                }
                buffer.close();
            } catch (IOException ex)
            {
                System.out.println("ERROR leyendo el fichero : " + ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("ERROR abriendo el fichero : " + ex);
            System.exit(1);
        }
        System.out.println("Os mapa vai ter : " + tamano[0] + " filas por : " + tamano[1] + " columnas");

        sel = "A Perouta"; /** Nombre del mapa**/
        /*
        System.out.print("Introduzca el nombre del mapa : ");
        sel = entradaEscaner.nextLine();
        */

        //Sumamos un porque creamos sin facelo en base cero no constructor
        mapa = new Mapa(tamano[0] + 1, tamano[1] + 1, sel);

        /**
         * Tras crear o mapa poñemos a transitables as casillas que nos indica o arquivo
         */
        try
        {
            buffer = new BufferedReader(new FileReader(new File(rutaMapa)));
            try
            {
                while ((linea = buffer.readLine()) != null)
                {
                    if (linea.length() != 0 && linea.charAt(0) != '#')
                    {
                        cadeas = linea.split(",");
                        mapa.getCelda(Integer.parseInt(cadeas[0]), Integer.parseInt(cadeas[1])).setTransitable(true);
                    }
                }
                buffer.close();
            } catch (IOException ex)
            {
                System.out.println("ERROR leyendo el fichero" + ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("ERROR abriendo el fichero" + ex);
            System.exit(1);
        }

        /**
         * Leo e creo as persoaxes
         */
        try
        {
            buffer = new BufferedReader(new FileReader(new File(rutaNpcs)));
            try
            {
                while ((linea = buffer.readLine()) != null)
                {
                    if (linea.length() != 0 && linea.charAt(0) != '#')
                    {
                        cadeas = linea.split(";");
                        tamano[0] = Integer.parseInt(cadeas[0].split(",")[0]);
                        tamano[1] = Integer.parseInt(cadeas[0].split(",")[1]);
                        if (!(cadeas[1].equals("enemigo") || cadeas[1].equals("jugador")))
                        {
                            System.out.println("ERROR, el tipo de personaje no está contemplado");
                        }
                        switch (cadeas[1])
                        {
                            case "enemigo":
                                mapa.getCelda(tamano[0], tamano[1]).setEnemigo(new Enemigo(cadeas[2], Integer.parseInt(cadeas[3]), Integer.parseInt(cadeas[4])));
                                break;
                            case "jugador":
                                personaje = new Personaje(mapa.getCelda(tamano[0], tamano[1]), cadeas[2], Integer.parseInt(cadeas[3]), Integer.parseInt(cadeas[4]));
                                break;

                        }
                    }
                }
                buffer.close();
            } catch (IOException ex)
            {
                System.out.println("ERROR leyendo el fichero" + ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("ERROR abriendo el fichero" + ex);
            System.exit(1);
        }

        //Asegurome de que o persoaxe sempre é creado
        if (personaje == null)
        {
            System.out.println("El personaje ha sido creado por defecto");
            armaMasBuena = new Arma("bazoka", "lanzamisiles 2.0", true, 80, 1, 1);
            ArrayList<Arma> armas2 = new ArrayList<>();
            armas2.add(armaMasBuena);
            personaje = new Personaje(100, 100, new Armadura("armadura rapida", "armadura de energia", 0, 10, 3, 4, 4), mapa.getCelda(5, 5), new Mochila(10, 20), 3, armas2, 100, 100, sel);
        }

        /**
         * Leo, creo e asigno os obxetos
         */
        try
        {
            buffer = new BufferedReader(new FileReader(new File(rutaObjetos)));
            try
            {
                while ((linea = buffer.readLine()) != null)
                {
                    if (linea.length() != 0 && linea.charAt(0) != '#')
                    {

                        cadeas = linea.split(";");
                        tamano[0] = Integer.parseInt(cadeas[0].split(",")[0]);
                        tamano[1] = Integer.parseInt(cadeas[0].split(",")[1]);
                        switch (cadeas[1])
                        {
                            case ".":
                                switch (cadeas[2])
                                {
                                    case "binoculares":
                                        mapa.getCelda(tamano[0], tamano[1]).setBinoculares(new Binoculares(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6])));
                                        break;
                                    case "arma":
                                        mapa.getCelda(tamano[0], tamano[1]).setArma(new Arma(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Float.parseFloat(cadeas[8])));
                                        break;
                                    case "armadura":
                                        mapa.getCelda(tamano[0], tamano[1]).setArmadura(new Armadura(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Integer.parseInt(cadeas[8])));
                                        break;
                                    case "botiquin":
                                        mapa.getCelda(tamano[0], tamano[1]).setBotiquin(new Botiquin(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Float.parseFloat(cadeas[6])));
                                        break;
                                    case "mochila":
                                        System.out.println("Non podes deixar unha mochila nunha casilla(Por ahora)");
                                        break;
                                    default:
                                        System.out.println("ERROR, objeto desconocido(Por ahora) para una casilla");
                                }
                                break;
                            case "jugador":
                                switch (cadeas[2])
                                {
                                    case "binoculares":
                                        if (personaje.getMochila() != null)
                                            personaje.getMochila().anadirBinocular(new Binoculares(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6])));
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
                                for (Enemigo enemigo : celda.getEnemigo())
                                {
                                    switch (cadeas[2])
                                    {
                                        case "arma":
                                            ArrayList armas = new ArrayList<>();
                                            Arma arma = new Arma(cadeas[3], cadeas[4], Integer.parseInt(cadeas[5]), Integer.parseInt(cadeas[6]), Integer.parseInt(cadeas[7]), Float.parseFloat(cadeas[8]));
                                            armas.add(arma);
                                            if (enemigo.getNombre().equals(cadeas[1]))
                                            {
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
            } catch (IOException ex)
            {
                System.out.println("ERROR leyendo el fichero" + ex);
                System.exit(1);
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println("ERROR abriendo el fichero" + ex);
            System.exit(1);
        }

        Bundle bundle = new Bundle();
        bundle.setMapa(mapa);
        bundle.setPersonaje(personaje);

        return bundle;
    }
}
