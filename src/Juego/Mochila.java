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
    private final int pesoMaximo;
    private final int objetosMaximos;
    private ArrayList<Binoculares> arrayBinoculares;
    private ArrayList<Botiquin> arrayBotiquin;
    private ArrayList<Arma> arrayArmas;
    private ArrayList<Armadura> arrayArmaduras;



    //Hay que obtener el peso actual de la mochila
    //Estos atributos no tienen setters, pues sus valores se calculan desde mochila.
    private int pesoActual;
    private int objetosActuales;

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
        arrayArmas = new ArrayList<>();
        this.pesoActual = 0;
        this.objetosActuales = 0;
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

    public void setPesoActual(int pesoActual)
    {
        this.pesoActual = pesoActual >= 0 ? pesoActual : 0;
    }

    public void setObjetosActuales(int objetosActuales)
    {
        this.objetosActuales = objetosActuales >= 0 ? objetosActuales : 0;
    }
    public int getPesoActual() {
        return pesoActual;
    }

    public int getObjetosActuales() {
        return objetosActuales;
    }

    public int getPesoMaximo()
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
     * @param arma
     */
    public void anadirArma (Arma arma)
    {
        arrayArmas.add(arma);
        this.setPesoActual(this.getPesoActual() + arma.getPeso());
        this.setObjetosActuales(this.getObjetosActuales() + 1);
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



    /**
     * Desequipa el arma de la mano seleccionada. Si el arma detectada es de dos manos se ignora la mano.
     */
    public void desequiparArma(Personaje personaje, String mano)
    {
        if(personaje.getArmas().get(0) != null) {
            if (personaje.getArmaDosM() != null){
                arrayArmas.add(personaje.getArmas().get(0));
                personaje.setArmaDosM(null);
            }
            else if (mano.equals("derecha"))
            {
                arrayArmas.add(personaje.getArmaDer());
                personaje.setArmaDer(null);
            }
            else if (mano.equals("izquierda"))
            {
                arrayArmas.add(personaje.getArmaIzq());
                personaje.setArmaIzq(null);
            }
            else
            {
                System.out.println("Mano mal escrita");
            }
        }
    }
}
