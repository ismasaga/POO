package Juego;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Clase Mapa. Realiza las siguientes funciones:
 * - Imprime los limites del mapa, las casillas visibles y los enemigos visibles y las casillas transitables.
 */
public class Mapa {

    private int ancho,alto;
    private String nombre;

    /**
     * Para comparar esta celda con la celda del personaje (o del enemigo), se debe usar aliasing; es decir, <strong>
     *     Comparar las referencias del objeto, habiendo, previamente, asignado "a pelo" (e.g: personaje.setCelda = mapa.getCelda(i,j)).
     * </strong>
     */
    private ArrayList<ArrayList<Celda>> mapa = new ArrayList<ArrayList<Celda>>();

    public Mapa(int ancho,int alto,String nombre) {
        this.alto = alto > 0 ? alto : 10;
        this.ancho = ancho > 0 ? ancho : 10;
        for (int i = 0; i < alto; i++)
        {
            ArrayList<Celda> arrayC = new ArrayList<>();
            for (int j = 0; j < ancho; j++)
            {
                arrayC.add(new Celda(true));
            }
            mapa.add(arrayC);
        }
        setNombre(nombre);
    }

    public Mapa(int ancho,int alto) {
        this.alto = alto > 0 ? alto : 10;
        this.ancho = ancho > 0 ? ancho : 10;
        for (int i = 0; i < alto; i++)
        {
            ArrayList<Celda> arrayC = new ArrayList<>();
            for (int j = 0; j < ancho; j++)
            {
                arrayC.add(new Celda(true));
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
     * @return
     */
    public String getDescripcion() {
        return "Esta jugando en el mapa "+getNombre()+" que cuenta con "+getAlto()+" filas y "+getAncho()+" columnas";
    }

    /**
     * El método setDescripción no será definido porque hemos decidido no hacerlo asi
     */

    /**
     * Asigna nome o mapa
     * @param nombre
     */
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            System.out.println("ERROR asignando nombre al mapa");
    }

    /**
     * Devolve o nome do mapa, no caso de que non fose definido, devolvería null
     * @return nombre
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
     * @param i Fila a obtener (ancho)
     * @param j Columna a obtener (alto)
     * @return Celda a obtener (null en caso de fallo)
     */
    public Celda getCelda(int i, int j)
    {
        if(i < ancho && j < alto && i >= 0 && j >= 0)
        {
            return mapa.get(i).get(j);
        }
        else
        {
            return null;
        }
    }

    //TODO: crear setBotiquin/eliminarBotiquin (si se cambian los nombres cambiarlos en Personaje, donde hay un error)

    /**
     * Imprime el mapa en la posicion del personaje pasado por parametro
     * Leyenda
     * B: binocular //TODO: quitar?
     * V: botiquin
     * E: enemigo
     * T: situación del personaje
     * //TODO: probar las celdas no transitables
     * @param personaje
     */
    public void imprimir(Personaje personaje)
    {
        /**
         * Interesa saber en que posicion esta el personaje
         */
        int i = 0;
        int j = 0;
        boolean encontrado = false; //Hay que hacer un doble break para salir de los dos fors
        for (i = 0; i < this.alto; i++)
        {
            for (j = 0; j < this.ancho; j++)
            {
                encontrado = mapa.get(i).get(j).equals(personaje.getCelda());
                if(encontrado)
                {
                    /**Hemos obtenido la posicion del personaje**/
                    break;
                }
            }
            if(encontrado)
                break;
        }
        for (int fila = 0; fila < alto; fila++)
        {
            for (int columna = 0; columna < ancho; columna++)
            {
                String imprimir = "";
                System.out.print("|");
                if(i == fila && j == columna)
                {
                    imprimir = " T |";
                    System.out.print(imprimir);
                    continue;
                }
                if (!(fila >= i-personaje.getRangoVision() && fila <= i+personaje.getRangoVision() && columna >= j-personaje.getRangoVision() && columna <= j+personaje.getRangoVision()))
                {
                    imprimir = "---|";
                    System.out.print(imprimir);
                    continue;
                }
                if(!mapa.get(fila).get(columna).isTransitable())
                {
                    imprimir = " * |";
                    System.out.print(imprimir);
                    continue;
                }
                /**
                 * TODO: eliminar los objetos?
                 */
                if (mapa.get(fila).get(columna).getBinoculares().size() > 0)
                    imprimir = imprimir + "B";
                else
                    imprimir = imprimir + " ";
                if (mapa.get(fila).get(columna).getBotiquin().size() > 0)
                    imprimir = imprimir + "V";
                else
                    imprimir = imprimir + " ";
                if (mapa.get(fila).get(columna).getEnemigo() != null)
                    imprimir = imprimir + "E";
                else
                    imprimir = imprimir + " ";

                System.out.print(imprimir);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    /**
     * Devuelve false hasta que el mapa no contenga mas enemigos, en cuyo caso devolverá true
     * @return boolean
     */
    public boolean moreEnemies() {
        boolean res = false;
        for(int i = 0;i < getAlto();i++) {
            for (int j = 0; j < getAncho(); j++)
                if (mapa.get(i).get(j).getEnemigo() != null) {
                    res = true;
                    break;
                }
            if(res)
                break;
        }
        return res;
    }
}
