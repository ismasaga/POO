package Juego;

import Objetos.Arma;
import Objetos.Armadura;
import Objetos.Binoculares;
import Objetos.Botiquin;

import java.util.ArrayList;

/**
 * Clase mochila. Esta clase gestiona el almacenamiento de los objetos del jugador.
 *
 *  A destacar:
 *
 * Los setters de arrayBinoculares y arrayBotiquin son anadirBinocular y anadirBotiquin pues los botiquines y binoculares
 *  es mejor añadirlos uno a uno y evitar un aliasing innecesario.
 *
 * pesoMaximo y objetosMaximos son final (constantes) pues en esta versión no hay manera de modificar el peso de la mochila
 *
 * La mochila puede almacenar un conjunto de armaduras y armas
 */
public class Mochila
{
    private final float pesoMaximo;
    private final int objetosMaximos;
    private ArrayList<Binoculares> arrayBinoculares;
    private ArrayList<Botiquin> arrayBotiquin;
    private ArrayList<Arma> arrayArmas;
    private ArrayList<Armadura> arrayArmaduras;
    private String nombre;
    private String descripcion;
    //Hay que obtener el peso actual de la mochila
    //Estos atributos no tienen setters, pues sus valores se calculan desde mochila.
    private float pesoActual;
    private float objetosActuales;

    /**
     * Constructor que crea una nueva mochila
     * Se supone que, cuando se crea la mochila (al principio), esta esta vacía
     */
    public Mochila(int pesoMaximo,int objetosMaximos)
    {
        //Se agregan valores por omisión
        this.pesoMaximo = pesoMaximo > 0 ? pesoMaximo : 20;
        this.objetosMaximos = objetosMaximos > 0 ?  objetosMaximos : 5;
        //Esto previene que se acceda al arrayList antes de crear el objeto
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
        arrayArmas = new ArrayList<>();
        arrayArmaduras = new ArrayList<>();
        this.pesoActual = 0;
        this.objetosActuales = 0;
    }

    //# mochila: celda, portador, tipo, nombre, descripcion, capacidad (num objetos), peso max (kg).

    /**
     * Constructor para objetos parseados de archivo
     */
    public Mochila(String nombre,String descripcion,int objetosMaximos,int pesoMaximo) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.pesoMaximo = pesoMaximo > 0 ? pesoMaximo : 20;
        this.objetosMaximos = objetosMaximos > 0 ?  objetosMaximos : 5;
        //Esto previene que se acceda al arrayList antes de crear el objeto
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
        arrayArmas = new ArrayList<>();
        arrayArmaduras = new ArrayList<>();
        this.pesoActual = 0;
        this.objetosActuales = 0;
    }

    /**
     * Devuelve el nombre de la mochila
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna nombre a la mochila
     */
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            System.out.println("ERROR asignando nombre a la mochila");
    }

    /**
     * Devuelve la descripcion de la mochila
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Asigna descripcion a la mochila
     */
    public void setDescripcion(String descripcion) {
        if(descripcion != null)
            this.descripcion = descripcion;
        else
            System.out.println("ERROR asignando descripcion a la mochila");
    }

    public ArrayList<Arma> getArrayArmas()
    {
        return arrayArmas;
    }

    public void setArrayArmas(ArrayList<Arma> arrayArmas)
    {
        if(arrayArmas != null)
            this.arrayArmas = arrayArmas;
    }

    public ArrayList<Armadura> getArrayArmaduras()
    {
        return arrayArmaduras;
    }

    public void setArrayArmaduras(ArrayList<Armadura> arrayArmaduras)
    {
        this.arrayArmaduras = arrayArmaduras;
    }

    public void setPesoActual(float pesoActual)
    {
        this.pesoActual = pesoActual >= 0 ? pesoActual : 0;
    }

    public void setObjetosActuales(float objetosActuales)
    {
        this.objetosActuales = objetosActuales >= 0 ? objetosActuales : 0;
    }
    public float getPesoActual() {
        return pesoActual;
    }

    public float getObjetosActuales() {
        return objetosActuales;
    }

    public float getPesoMaximo()
    {
        return pesoMaximo;
    }

    public int getObjetosMaximos()
    {
        return objetosMaximos;
    }

    public ArrayList<Binoculares> getArrayBinoculares()
    {
        return arrayBinoculares;
    }

    public ArrayList<Botiquin> getArrayBotiquin()
    {
        return arrayBotiquin;
    }

    /**
     * Añade un arma a la mochila
     */
    public void anadirArma (Arma arma) {
        if(arma != null) {
            arrayArmas.add(arma);
            this.setPesoActual(this.getPesoActual() + arma.getPeso());
            this.setObjetosActuales(this.getObjetosActuales() + 1);
        } else
            System.out.println("ERROR insertando arma en la mochila");
    }

    /**
     * Añade una armadura a la mochila
     * @param armadura
     */
    public void anadirArmadura (Armadura armadura)
    {
        arrayArmaduras.add(armadura);
        this.setPesoActual(this.getPesoActual() + 1);
        this.setObjetosActuales(this.getObjetosActuales() + armadura.getPeso());
    }

    /**
     * Se sustituye el setter por defecto por un método que añade un binocular a la lista.
     * @param binocular Binocular a añadir
     */
    public void anadirBinocular(Binoculares binocular)
    {
        if(getPesoActual() + binocular.getPeso() > this.getPesoMaximo())
        {
            System.err.println("Se ha sobrepasado el peso maximo");
            return;
        }
        if(getObjetosActuales() + 1 > this.getObjetosMaximos())
        {
            //TODO: implementarlo bien en la UI
            System.err.println("Se ha sobrepasado el numero de objetos maximo");
            return;
        }
        if(binocular != null)
        {
            arrayBinoculares.add(binocular);
            pesoActual = pesoActual + binocular.getPeso();
            objetosActuales = objetosActuales + 1;
        }
    }

    /**
     * Retorna un botiquin por su nombre: botiquin_grande, botiquin_pequeño
     * @param nombre Nombre del botiquin a buscar.
     * @return Botiquin encontrado o null si no encuentra.
     */
    public Botiquin getBotiquin(String nombre)
    {
        for(Botiquin botiquin : arrayBotiquin)
        {
            if(botiquin.getNombre().equals(nombre))
            {
                return botiquin;
            }
        }
        return null;
    }

    public Binoculares getBinocular(String nombre)
    {
        for(Binoculares binocular: arrayBinoculares)
        {
            if(binocular.getNombre().equals(nombre))
            {
                return binocular;
            }
        }
        return null;
    }

    public void anadirBotiquin(Botiquin botiquin)
    {
        if(getPesoActual() + botiquin.getPeso() > this.getPesoMaximo())
        {
            System.err.println("Se ha sobrepasado el peso maximo");
            return;
        }
        if(getObjetosActuales() + 1 > this.getObjetosMaximos())
        {
            System.err.println("Se ha sobrepasado el numero de objetos maximo");
            return;
        }
        if(botiquin != null)
        {
            arrayBotiquin.add(botiquin);
            pesoActual = pesoActual + botiquin.getPeso();
            objetosActuales = objetosActuales + 1;
        }
    }

    public void quitarBotiquin(Botiquin botiquin)
    {
        /*Se pueden comparar referencias ya que el botiquin obtenido por parametro procede del array de mochilas*/
        arrayBotiquin.remove(botiquin);
    }

    public void quitarBinocular(Binoculares binocular)
    {
        arrayBinoculares.remove(binocular);
    }

    public void quitarArma (Arma arma)
    {
        arrayArmas.remove(arma);
    }

    public void quitarArmadura (Armadura armadura)
    {
        arrayArmaduras.remove(armadura);
    }


}
