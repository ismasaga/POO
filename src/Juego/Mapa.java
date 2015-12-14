package Juego;

import Objetos.Binocular;
import Objetos.Botiquin;
import Objetos.Objeto;
import Personajes.Enemigo;
import Personajes.Personaje;

import java.awt.*;
import java.util.ArrayList;

/**
 * Clase Mapa. Realiza las siguientes funciones:
 *  Imprime los limites del mapa, las casillas visibles y los enemigos visibles y las casillas transitables.
 * Consideraciones:
 *  ancho y alto son constantes pues no se puede modificar una vez creado el mapa.
 *  Set mapa no es definido para no modificar el mapa una vez inciado (idem getter, aliasing).
 */
public class Mapa {

    private final int ancho, alto;
    private String nombre;
    private ConsolaNormal consola = new ConsolaNormal();

    /**
     * Para comparar esta celda con la celda del personaje (o del enemigo), se debe usar aliasing; es decir, <strong>
     * Comparar las referencias del objeto, habiendo, previamente, asignado "a pelo" (e.g: personaje.setCelda = mapa.getCelda(i,j)).
     * </strong>
     */
    private ArrayList<ArrayList<Celda>> mapa = new ArrayList<>();

    public Mapa(int alto, int ancho, String nombre) {
        this.alto = alto > 0 ? alto : 10;
        this.ancho = ancho > 0 ? ancho : 10;
        for (int i = 0; i < alto; i++) {
            ArrayList<Celda> arrayC = new ArrayList<>();
            for (int j = 0; j < ancho; j++) {
                arrayC.add(new Celda(false,new Point(i,j)));
            }
            mapa.add(arrayC);
        }
        setNombre(nombre);
    }

    public Mapa(int alto, int ancho) {
        this.alto = alto > 0 ? alto : 10;
        this.ancho = ancho > 0 ? ancho : 10;
        for (int i = 0; i < alto; i++) {
            ArrayList<Celda> arrayC = new ArrayList<>();
            for (int j = 0; j < ancho; j++) {
                arrayC.add(new Celda(false,new Point(i,j)));
            }
            mapa.add(arrayC);
        }
        setNombre("desconocido");
    }

    /**
     * O setMapa non será definido porque non queremos que se poida cambiar o mapa despois de que o programa sexa iniciado
     */

    /**
     * O getMapa non será definido porque non queremos que se poida obter o mapa despois de que o programa sexa iniciado
     */

    /**
     * Devuelve un String que incluye el nombre del mapa y sus dimensiones
     */
    public String getDescripcion() {
        return "Esta jugando en el mapa " + getNombre() + " que cuenta con " + getAlto() + " filas y " + getAncho() + " columnas";
    }

    /**
     * El método setDescripción no será definido porque hemos decidido no hacerlo asi
     */

    /**
     * Asigna nome o mapa
     */
    public void setNombre(String nombre) {
        if (nombre != null)
            this.nombre = nombre;
        else
            consola.imprimirError("ERROR asignando nombre al mapa");
    }

    /**
     * Devolve o nome do mapa, no caso de que non fose definido, devolvería null
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setAncho y setAlto no serán definidos ya que no queremos que puedan ser cambiados tras ser definidos
     */
    public int getAncho() {
        return ancho;
    }
    public int getAlto() {
        return alto;
    }

    /**
     * No se define setCelda, pues el contenido de las celdas se modifica con aliasing
     */

    /**
     * Retorna la celda de la posición (i,j) en base 0 (0..9, por defecto en los valores del constructor de dos parametros)
     *
     * @param i Fila a obtener (alto)
     * @param j Columna a obtener (ancho)
     * @return Celda a obtener (null en caso de fallo)
     */
    public Celda getCelda(int i, int j) {
        if (i < alto && j < ancho && i >= 0 && j >= 0) {
            return mapa.get(i).get(j);
        } else {
            return null;
        }
    }


    /**
     * Imprime el mapa en la posicion del personaje pasado por parametro
     * Leyenda
     * O: objeto
     * E: enemigo
     * P: situación del personaje
     */
    public void imprimir(Personaje personaje) {
        /*int i;
        int j;
        int[] array;
        array = posicionPersonaje(personaje);
        i = array[0];
        j = array[1];*/
        int i = (int)personaje.getPunto().getX();
        int j = (int)personaje.getPunto().getY();

        for (int fila = 0; fila < getAlto(); fila++) {
            for (int columna = 0; columna < getAncho(); columna++) {
                String imprimir = "";
                consola.imprimirSinSalto("|");
                if (i == fila && j == columna) {
                    imprimir = " P |";
                    consola.imprimirSinSalto(imprimir);
                    continue;
                }
                if (!(fila >= i - personaje.getRangoVision() && fila <= i + personaje.getRangoVision() && columna >= j - personaje.getRangoVision() && columna <= j + personaje.getRangoVision())) {
                    imprimir = "---|";
                    consola.imprimirSinSalto(imprimir);
                    continue;
                }
                if (!mapa.get(fila).get(columna).isTransitable()) {
                    imprimir = " * |";
                    consola.imprimirSinSalto(imprimir);
                    continue;
                }
                if(mapa.get(fila).get(columna).getEnemigo() != null) {
                    imprimir += " E |";
                    consola.imprimirSinSalto(imprimir);
                    continue;
                }
                if(!mapa.get(fila).get(columna).getArrayObjetos().isEmpty()) {
                    for (Objeto objeto : mapa.get(fila).get(columna).getArrayObjetos()) {
                        if (objeto instanceof Binocular) {
                            imprimir = imprimir + "B";
                            break;
                        }
                    }
                    if (imprimir.equals(""))
                        imprimir += " ";
                    for (Objeto objeto : mapa.get(fila).get(columna).getArrayObjetos()) {
                        if (objeto instanceof Botiquin) {
                            imprimir = imprimir + "V";
                            break;
                        }
                    }
                    if(imprimir.length() == 1)
                        imprimir += " ";
                    imprimir += " ";
                }
                else{
                    imprimir = "   ";
                }

                consola.imprimirSinSalto(imprimir);
                consola.imprimirSinSalto("|");
            }
            consola.imprimir(" ");
        }
    }

    /**
     * Devuelve false hasta que el mapa no contenga mas enemigos, en cuyo caso devolverá true
     *
     * @return boolean
     */
    public boolean moreEnemies() {
        boolean res = false;
        for (int i = 0; i < getAlto(); i++) {
            for (int j = 0; j < getAncho(); j++)
                if (mapa.get(i).get(j).getEnemigo() != null) {
                    res = true;
                    break;
                }
            if (res)
                break;
        }
        return res;
    }

    /**
     * Devuelve la posicion actual del personaje
     *
     * @return int[0] = alto, int[1] = ancho
     */
    /*public int[] posicionPersonaje(Personaje personaje) {
        int i;
        int j = 0;
        boolean encontrado = false;
        int[] array = new int[2];
        for (i = 0; i < this.alto; i++) {
            for (j = 0; j < this.ancho; j++) {
                encontrado = mapa.get(i).get(j).equals(personaje.getCelda());
                if (encontrado) {
                    //Hemos obtenido la posicion del personaje
                    break;
                }
            }
            if (encontrado)
                break;
        }
        array[0] = i;
        array[1] = j;
        return array;
    }*/
}
