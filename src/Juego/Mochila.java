package Juego;

import Objetos.Binoculares;
import Objetos.Botiquin;

import java.util.ArrayList;

/**
 * Clase mochila. Esta clase gestiona el almacenamiento de los objetos del jugador.
 */
public class Mochila
{
    private int pesoMaximo;
    private int objetosMaximos;
    private ArrayList<Binoculares> arrayBinoculares;
    private ArrayList<Botiquin> arrayBotiquin;

    /**
     * Constructor que crea una nueva mochila
     * Se supone que, cuando se crea la mochila (al principio), esta esta vacía
     * @param pesoMaximo Peso maximo de la mochila
     * @param objetosMaximos Objetos maximosde la mochila
     */
    public Mochila(int pesoMaximo,int objetosMaximos)
    {
        //Se agregan valores por omisión
        this.pesoMaximo = pesoMaximo > 0 ? pesoMaximo : 20;
        this.objetosMaximos = objetosMaximos > 0 ?  objetosMaximos : 5;
        //Esto previene que se acceda al arrayList antes de crear el objeto
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
    }

    public int getPesoMaximo()
    {
        return pesoMaximo;
    }

    public int getObjetosMaximos()
    {
        return objetosMaximos;
    }

    /**
     * Se sustituye el setter por defecto por un método que añade un binocular a la lista.
     * @param binocular Binocular a añadir
     */
    public void anadirBinocular(Binoculares binocular)
    {
        if(binocular != null)
        {
            arrayBinoculares.add(binocular);
        }
    }

    //TODO: crear funciones para obtener un botiquin (identificandolo ¿unívocamente?)

    public void anadirBotiquin(Botiquin botiquin)
    {
        if(botiquin != null)
        {
            arrayBotiquin.add(botiquin);
        }
    }

    //TODO: implementar. Buscar una forma de detectar que botiquin se quiere eliminar.
    public void quitarBotiquin(Botiquin botiquin)
    {
        System.err.println("No implementado");
    }

    //TODO: implementar. Buscar una forma de detectar que binocular se quiere eliminar.
    public void quitarBinocular(Binoculares binocular)
    {
        System.err.println("No implementado");
    }
}
