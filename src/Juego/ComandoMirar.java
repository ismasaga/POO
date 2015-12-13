package Juego;

import Excepciones.*;
import Personajes.Jugador;

public class ComandoMirar implements Comando{

    private Mapa mapa;
    private Jugador jugador;
    private int posicionX,posicionY;
    private char direccionX, direccionY;
    private String objeto;
    private String[] cadeas;

    public ComandoMirar(Mapa mapa, Jugador jugador, String sel) throws ComandoException{
        this.mapa = mapa;
        this.jugador = jugador;
        cadeas = sel.split(" ");
        if (cadeas.length == 1) {
            //new ComandoMirar(mapa,personaje, 0, 0, ' ', ' ', null).ejecutar();
            posicionX = 0;
            posicionY = 0;
            direccionX = ' ';
            direccionY = ' ';
            objeto = null;
            return;
        }
        //Mira
        else if (cadeas.length == 2 && cadeas[1].length() != 2) {
            //new ComandoMirar(mapa, personaje, 0, 0, ' ', ' ', cadeas[1]).ejecutar();
            posicionX = 0;
            posicionY = 0;
            direccionX = ' ';
            direccionY = ' ';
            objeto = cadeas[1];
            return;
        }
        //Obtiene informacion de una casilla lejana
        else if (cadeas.length == 3 && cadeas[1].length() == 2 && cadeas[2].length() == 2) {
            /*
            new ComandoMirar(mapa,
                    personaje,
                    Character.getNumericValue(cadeas[1].charAt(0)),
                    Character.getNumericValue(cadeas[2].charAt(0)),
                    cadeas[1].charAt(1),
                    cadeas[2].charAt(1),
                    null).ejecutar();
                    */
            posicionX = Character.getNumericValue(cadeas[1].charAt(0));
            posicionY = Character.getNumericValue(cadeas[2].charAt(0));
            direccionX = cadeas[1].charAt(1);
            direccionY = cadeas[2].charAt(1);
            objeto = null;
            return;
        }
        //Mira solo en una direccion una casilla lejana
        else if (cadeas.length == 2 && cadeas[1].length() == 2) {
            if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l') {
                /*
                new ComandoMirar(mapa,
                        personaje,
                        Character.getNumericValue(cadeas[1].charAt(0)),
                        0,
                        cadeas[1].charAt(1),
                        ' ',
                        null).ejecutar();
                        */
                posicionX = Character.getNumericValue(cadeas[1].charAt(0));
                posicionY = 0;
                direccionX = cadeas[1].charAt(1);
                direccionY = ' ';
                objeto = null;
            }
            if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd') {
                /*
                new ComandoMirar(mapa,
                        personaje,
                        0,
                        Character.getNumericValue(cadeas[1].charAt(0)),
                        ' ',
                        cadeas[1].charAt(1),
                        null).ejecutar();
                        */
                posicionX = 0;
                posicionY = Character.getNumericValue(cadeas[1].charAt(0));
                direccionX = ' ';
                direccionY = cadeas[1].charAt(1);
                objeto = null;
            }
        }
        //Se mira un objeto en una direccion
        else if (cadeas.length == 3 && cadeas[2].length() != 2) {
            if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l') {
                /*
                new ComandoMirar(mapa,
                        personaje,
                        Character.getNumericValue(cadeas[1].charAt(0)),
                        0,
                        cadeas[1].charAt(1),
                        ' ',
                        cadeas[2]).ejecutar();
                        */
                posicionX = Character.getNumericValue(cadeas[1].charAt(0));
                posicionY = 0;
                direccionX = cadeas[1].charAt(1);
                direccionY = ' ';
                objeto = cadeas[2];
            }
            if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd') {
                /*
                new ComandoMirar(mapa,
                        personaje,
                        0,
                        Character.getNumericValue(cadeas[1].charAt(0)),
                        ' ',
                        cadeas[1].charAt(1),
                        cadeas[2]);
                        */
                posicionX = 0;
                posicionY = Character.getNumericValue(cadeas[1].charAt(0));
                direccionX = ' ';
                direccionY = cadeas[1].charAt(1);
                objeto = cadeas[2];
            }
        }
        //Se mira un objeto en varias direcciones
        else if (cadeas.length == 4 && cadeas[1].length() == 2 && cadeas[2].length() == 2) {
            /*
            new ComandoMirar(mapa,
                    personaje,
                    Character.getNumericValue(cadeas[1].charAt(0)),
                    Character.getNumericValue(cadeas[2].charAt(0)),
                    cadeas[1].charAt(1),
                    cadeas[2].charAt(1),
                    cadeas[3]).ejecutar();
                    */
            posicionX = Character.getNumericValue(cadeas[1].charAt(0));
            posicionY = Character.getNumericValue(cadeas[2].charAt(0));
            direccionX = cadeas[1].charAt(1);
            direccionY = cadeas[2].charAt(1);
            objeto = cadeas[3];
        }
        else{
            throw new ComandoException("Error procesando comando");
        }

    }

    @Override
    public void ejecutar() throws SegmentationFaultException, FueraDeRangoException{
        jugador.mirar(mapa,posicionX,posicionY,direccionX,direccionY,objeto);
    }
}
