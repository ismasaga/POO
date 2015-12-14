package Juego;

import Excepciones.ComandoException;
import Excepciones.FueraDeRangoException;
import Excepciones.InsuficienteEnergiaException;
import Personajes.Enemigo;
import Personajes.Jugador;
import Personajes.Personaje;

public class ComandoAtacar implements Comando {
    private Mapa mapa;
    private Jugador personaje;
    private char dirX;
    private char dirY;
    private int numX;
    private int numY;
    private String nombre;
    private String[] cadeas;

    public ComandoAtacar(Mapa mapa, Jugador personaje, String sel) throws ComandoException{
        this.mapa = mapa;
        this.personaje = personaje;
        cadeas = sel.split(" ");
        if (cadeas.length == 2 && cadeas[1].length() == 2)
            if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l') {
                //new ComandoAtacar(mapa, personaje, Character.getNumericValue(cadeas[1].charAt(0)), 0, cadeas[1].charAt(1), 'q', null).ejecutar();
                numX = Character.getNumericValue(cadeas[1].charAt(0));
                numY = 0;
                dirX = cadeas[1].charAt(1);
                dirY = 'q';
                nombre = null;
            } else if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd') {
                //new ComandoAtacar(mapa, personaje, 0, Character.getNumericValue(cadeas[1].charAt(0)), 'q', cadeas[1].charAt(1), null).ejecutar();
                numX = 0;
                numY = Character.getNumericValue(cadeas[1].charAt(0));
                dirX = 'q';
                dirY = cadeas[1].charAt(1);
                nombre = null;
            } else
                throw new ComandoException("La opción seleccionada no existe, seleccione ayuda para saber más");
        else if (cadeas.length == 3 && cadeas[2].length() != 2 && cadeas[1].length() == 2) //Ataca de frente o a los lados.
            if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l') {
                //new ComandoAtacar(mapa, personaje, Character.getNumericValue(cadeas[1].charAt(0)), 0, cadeas[1].charAt(1), 'q', cadeas[2]).ejecutar();
                numX = Character.getNumericValue(cadeas[1].charAt(0));
                numY = 0;
                dirX = cadeas[1].charAt(1);
                dirY = 'q';
                nombre = cadeas[2];
            } else if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd') {
                //new ComandoAtacar(mapa, personaje, 0, Character.getNumericValue(cadeas[1].charAt(0)), 'q', cadeas[1].charAt(1), cadeas[2]).ejecutar();
                numX = 0;
                numY = Character.getNumericValue(cadeas[1].charAt(0));
                dirX = 'q';
                dirY = cadeas[1].charAt(1);
                nombre = cadeas[2];
            } else
                throw new ComandoException("La opción seleccionada no existe, seleccione ayuda para saber más");
        else if (cadeas.length == 3 && cadeas[1].length() == 2 && cadeas[1].length() == 2) //Quiere atacar en diagonal
        {
            //new ComandoAtacar(mapa, personaje, Character.getNumericValue(cadeas[1].charAt(0)), Character.getNumericValue(cadeas[2].charAt(0))
                    //, cadeas[1].charAt(1), cadeas[2].charAt(1), null).ejecutar();
            numX = Character.getNumericValue(cadeas[1].charAt(0));
            numY = Character.getNumericValue(cadeas[2].charAt(0));
            dirX = cadeas[1].charAt(1);
            dirY = cadeas[2].charAt(1);
            nombre = null;
        } else if (cadeas.length == 4 && cadeas[1].length() == 2 && cadeas[1].length() == 2) //Quiere atacar en diagonal a un enemigo
        {
            //new ComandoAtacar(mapa, personaje, Character.getNumericValue(cadeas[1].charAt(0)), Character.getNumericValue(cadeas[2].charAt(0))
            //        , cadeas[1].charAt(1), cadeas[2].charAt(1), cadeas[3]).ejecutar();
            numX = Character.getNumericValue(cadeas[1].charAt(0));
            numY = Character.getNumericValue(cadeas[2].charAt(0));
            dirX = cadeas[1].charAt(1);
            dirY = cadeas[2].charAt(1);
            nombre = cadeas[3];
        }
        else{
            throw new ComandoException("Comando no reconocido");
        }
    }

    @Override
    public void ejecutar() throws ComandoException, InsuficienteEnergiaException, FueraDeRangoException {
        Consola consola = new ConsolaNormal();
        int i = personaje.getPunto().x;
        int j = personaje.getPunto().y;
        int componenteI = 0;
        int componenteJ = 0;

        Celda celdaObtenida;

        if (dirY == 'q')
            componenteI = i;
        if (dirX == 'q')
            componenteJ = j;
        if (dirY == 'u' && i - numY >= 0)
            componenteI = i - numY;
        if (dirY == 'd' && i + numY < mapa.getAlto())
            componenteI = i + numY;
        if (dirX == 'l' && j - numX >= 0)
            componenteJ = j - numX;
        if (dirX == 'r' && j + numX < mapa.getAncho())
            componenteJ = j + numX;

        int minX = numX;
        if (minX > personaje.getRangoVision()) {
            //consola.imprimir("No tienes suficiente rango de vision");
            throw new FueraDeRangoException("Sin suficiente rango de visión");
        }
        if (personaje.getArmaDer() != null) {
            if (minX > personaje.getArmaDer().getAlcance()) {
                throw new FueraDeRangoException("Tu arma derecha no tiene suficiente alcance.");
            }

        }
        if (personaje.getArmaIzq() != null) {
            if (minX > personaje.getArmaIzq().getAlcance()) {
                throw new FueraDeRangoException("Tu arma izquierda no tiene suficiente alcance.");
            }
        }
        if (personaje.getArmaDosM() != null) {
            if (minX > personaje.getArmaDosM().getAlcance()) {
                throw new FueraDeRangoException("Tu arma de dos manos no tiene suficiente alcance.");
            }
        }

        /**Buscamos el maximo de casillas atacables (eje y)**/
        int minY = numY;
        if (minY > personaje.getRangoVision()) {
            throw new FueraDeRangoException("No tienes suficiente rango de vision");
        }
        if (personaje.getArmaDer() != null) {
            if (minY > personaje.getArmaDer().getAlcance()) {
                throw new FueraDeRangoException("Tu arma derecha no tiene suficiente alcance.");
            }

        }
        if (personaje.getArmaIzq() != null) {
            if (minY > personaje.getArmaIzq().getAlcance()) {
                throw new FueraDeRangoException("Tu arma izquierda no tiene suficiente alcance.");
            }
        }
        if (personaje.getArmaDosM() != null) {
            if (minY > personaje.getArmaDosM().getAlcance()) {
                throw new FueraDeRangoException("Tu arma no tiene suficiente alcance.");
            }
        }

        celdaObtenida = mapa.getCelda(componenteI,componenteJ);
        if(nombre == null) //Se ataca a todos los enemigos
        {
            (personaje).atacar(celdaObtenida);
        }
        else {
            for (Enemigo enemigo : celdaObtenida.getEnemigo()) {
                if (enemigo.getNombre().equals(nombre)) {
                    personaje.atacar(enemigo);
                    if (enemigo.getVidaActual() <= 0) {
                        consola.imprimir("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                        enemigo.soltarObjetos(celdaObtenida);
                    }
                    return;
                }
            }
        }
    }
}
