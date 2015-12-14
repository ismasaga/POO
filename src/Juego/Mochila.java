package Juego;

import Objetos.*;

import java.util.ArrayList;

/**
 * Clase mochila. Esta clase gestiona el almacenamiento de los objetos del jugador.
 * <p>
 * A destacar:
 * <p>
 * Los setters de arrayBinoculares y arrayBotiquin son anadirBinocular y anadirBotiquin pues los botiquines y binoculares
 * es mejor añadirlos uno a uno y evitar un aliasing innecesario.
 * <p>
 * pesoMaximo y objetosMaximos son final (constantes) pues en esta versión no hay manera de modificar el peso de la mochila
 * <p>
 * La mochila puede almacenar un conjunto de armaduras y armas
 */
public class Mochila {
    private final float pesoMaximo;
    private final int objetosMaximos;
    private ArrayList<Binocular> arrayBinoculares;
    private ArrayList<Botiquin> arrayBotiquin;
    private ArrayList<Arma> arrayArmas;
    private ArrayList<Armadura> arrayArmaduras;
    private ArrayList<Torito> arrayTorito;
    private String nombre;
    private String descripcion;
    //Hay que obtener el peso actual de la mochila
    //Estos atributos no tienen setters, pues sus valores se calculan desde mochila.
    private float pesoActual;
    private float objetosActuales;
    private ConsolaNormal consola = new ConsolaNormal();

    /**
     * Constructor que crea una nueva mochila
     * Se supone que, cuando se crea la mochila (al principio), esta esta vacía
     */
    public Mochila(int pesoMaximo, int objetosMaximos) {
        //Se agregan valores por omisión
        this.pesoMaximo = pesoMaximo > 0 ? pesoMaximo : 20;
        this.objetosMaximos = objetosMaximos > 0 ? objetosMaximos : 5;
        //Esto previene que se acceda al arrayList antes de crear el objeto
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
        arrayArmas = new ArrayList<>();
        arrayArmaduras = new ArrayList<>();
        arrayTorito = new ArrayList<>();
        this.pesoActual = 0;
        this.objetosActuales = 0;
    }

    //# mochila: celda, portador, tipo, nombre, descripcion, capacidad (num objetos), peso max (kg).

    /**
     * Constructor para objetos parseados de archivo
     */
    public Mochila(String nombre, String descripcion, int objetosMaximos, int pesoMaximo) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.pesoMaximo = pesoMaximo > 0 ? pesoMaximo : 20;
        this.objetosMaximos = objetosMaximos > 0 ? objetosMaximos : 5;
        //Esto previene que se acceda al arrayList antes de crear el objeto
        arrayBinoculares = new ArrayList<>();
        arrayBotiquin = new ArrayList<>();
        arrayArmas = new ArrayList<>();
        arrayTorito = new ArrayList<>();
        arrayArmaduras = new ArrayList<>();
        this.pesoActual = 0;
        this.objetosActuales = 0;
    }

    /**
     * Devolve os botiquins, binoculares e toritos todos xuntos
     */
    public ArrayList<Objeto> getObjetos() {
        ArrayList<Objeto> objetos = new ArrayList<>();
        for(Binocular b : getArrayBinoculares())
            objetos.add(b);
        for(Botiquin b : getArrayBotiquin())
            objetos.add(b);
        for(Torito t : getArrayTorito())
            objetos.add(t);
        return objetos;
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
        if (nombre != null)
            this.nombre = nombre;
        else
            consola.imprimirError("ERROR asignando nombre a la mochila");
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
        if (descripcion != null)
            this.descripcion = descripcion;
        else
            consola.imprimirError("ERROR asignando descripcion a la mochila");
    }

    public ArrayList<Torito> getArrayTorito() {
        return arrayTorito;
    }

    public ArrayList<Arma> getArrayArmas() {
        return arrayArmas;
    }

    public void setArrayArmas(ArrayList<Arma> arrayArmas) {
        if (arrayArmas != null)
            this.arrayArmas = arrayArmas;
    }

    public ArrayList<Armadura> getArrayArmaduras() {
        return arrayArmaduras;
    }

    public void setArrayArmaduras(ArrayList<Armadura> arrayArmaduras) {
        this.arrayArmaduras = arrayArmaduras;
    }

    public void setPesoActual(float pesoActual) {
        this.pesoActual = pesoActual >= 0 ? pesoActual : 0;
    }

    public void setObjetosActuales(float objetosActuales) {
        this.objetosActuales = objetosActuales >= 0 ? objetosActuales : 0;
    }

    public float getPesoActual() {
        return pesoActual;
    }

    public float getObjetosActuales() {
        return objetosActuales;
    }

    public float getPesoMaximo() {
        return pesoMaximo;
    }

    public int getObjetosMaximos() {
        return objetosMaximos;
    }

    public ArrayList<Binocular> getArrayBinoculares() {
        return arrayBinoculares;
    }

    public ArrayList<Botiquin> getArrayBotiquin() {
        return arrayBotiquin;
    }


    public boolean anadirTorito(Torito torito) {
        if (torito != null) {
            if (getPesoActual() + torito.getPeso() > this.getPesoMaximo()) {
                consola.imprimirError("Se ha sobrepasado el peso maximo");
                return false;
            }
            if (getObjetosActuales() + 1 > this.getObjetosMaximos()) {
                consola.imprimirError("Se ha sobrepasado el numero de objetos maximo");
                return false;
            }
            arrayTorito.add(torito);
            pesoActual = pesoActual + torito.getPeso();
            objetosActuales = objetosActuales + torito.getEspacio();
            return true;
        }
        return false;
    }
    /**
     * Añade un arma a la mochila
     * Devolve true se foi engadida e false en caso contrario
     */
    public boolean anadirArma(Arma arma) {
        if (arma != null) {
            if(arma.getPeso() + getPesoActual() > getPesoMaximo()) {
                consola.imprimirError("Se ha sobrepasado el peso maximo"); //ACHTUNG: no podemos tirar una excepción porque retorna un valor
                return false;
            }
            if(arma.getEspacio() + getObjetosActuales() > getObjetosMaximos()) {
                consola.imprimirError("Se ha sobrepasado el numero de objetos maximo");
                return false;
            }
            arrayArmas.add(arma);
            this.setPesoActual(this.getPesoActual() + arma.getPeso());
            this.setObjetosActuales(this.getObjetosActuales() + arma.getEspacio());
            return true;
        } else
            consola.imprimirError("ERROR insertando arma en la mochila");
        return false;
    }

    /**
     * Añade una armadura a la mochila
     * Devolve true se foi engadida e false en caso contrario
     */
    public boolean anadirArmadura(Armadura armadura) {
        if(armadura != null) {
            arrayArmaduras.add(armadura);
            if (armadura.getPeso() + getPesoActual() > getPesoMaximo()) {
                consola.imprimirError("Se ha sobrepasado el peso maximo");
                return false;
            }
            if(armadura.getEspacio() + getObjetosActuales() > getObjetosMaximos()) {
                consola.imprimirError("Se ha sobrepasado el numero de objetos maximo");
                return false;
            }
            this.setPesoActual(this.getPesoActual() + armadura.getPeso());
            this.setObjetosActuales(this.getObjetosActuales() + armadura.getEspacio());
            return true;
        }
        return false;
    }

    /**
     * Se sustituye el setter por defecto por un método que añade un binocular a la lista.
     * Devolve true se foi engadido e false en caso contrario
     */
    public boolean anadirBinocular(Binocular binocular) {
        if (binocular != null) {
            if (getPesoActual() + binocular.getPeso() > this.getPesoMaximo()) {
                consola.imprimirError("Se ha sobrepasado el peso maximo");
                return false;
            }
            if (getObjetosActuales() + 1 > this.getObjetosMaximos()) {
                consola.imprimirError("Se ha sobrepasado el numero de objetos maximo");
                return false;
            }
            arrayBinoculares.add(binocular);
            pesoActual = pesoActual + binocular.getPeso();
            objetosActuales = objetosActuales + binocular.getEspacio();
            return true;
        }
        return false;
    }

    /**
     * Retorna un botiquin por su nombre: botiquin_grande, botiquin_pequeño
     *
     * @param nombre Nombre del botiquin a buscar.
     * @return Botiquin encontrado o null si no encuentra.
     */
    public Botiquin getBotiquin(String nombre) {
        for (Botiquin botiquin : arrayBotiquin) {
            if (botiquin.getNombre().equals(nombre)) {
                return botiquin;
            }
        }
        return null;
    }

    public Binocular getBinocular(String nombre) {
        for (Binocular binocular : arrayBinoculares) {
            if (binocular.getNombre().equals(nombre)) {
                return binocular;
            }
        }
        return null;
    }

    /**
     * Devolve true se foi engadido e false en caso contrario
     */
    public boolean anadirBotiquin(Botiquin botiquin) {
        if(botiquin != null) {
            if (getPesoActual() + botiquin.getPeso() > this.getPesoMaximo()) {
                consola.imprimirError("Se ha sobrepasado el peso maximo");
                return false;
            }
            if (getObjetosActuales() + botiquin.getEspacio() > this.getObjetosMaximos()) {
                consola.imprimirError("Se ha sobrepasado el numero de objetos maximo");
                return false;
            }
            arrayBotiquin.add(botiquin);
            pesoActual = pesoActual + botiquin.getPeso();
            objetosActuales = objetosActuales + botiquin.getEspacio();
            return true;
        }
        return false;
    }

    public void quitarBotiquin(Botiquin botiquin) {
        /*Se pueden comparar referencias ya que el botiquin obtenido por parametro procede del array de mochilas*/
        if(botiquin != null) {
            setObjetosActuales(getObjetosActuales() - botiquin.getEspacio());
            setPesoActual(getPesoActual() - botiquin.getPeso());
            arrayBotiquin.remove(botiquin);
        }

    }

    public void quitarBinocular(Binocular binocular) {
        if(binocular != null) {
            arrayBinoculares.remove(binocular);
            setPesoActual(getPesoActual() - binocular.getPeso());
            setObjetosActuales(getObjetosActuales() - binocular.getEspacio());
        }
    }

    public void quitarArma(Arma arma) {
        if(arma != null) {
            setPesoActual(getPesoActual() - arma.getPeso());
            setObjetosActuales(getObjetosActuales() - arma.getEspacio());
            arrayArmas.remove(arma);
        }
    }

    public void quitarArmadura(Armadura armadura) {
        if(armadura != null) {
            setPesoActual(getPesoActual() - armadura.getPeso());
            setObjetosActuales(getObjetosActuales() - armadura.getEspacio());
            arrayArmaduras.remove(armadura);
        }
    }

    public void quitarTorito(Torito torito) {
        if(torito != null) {
            setPesoActual(getPesoActual() - torito.getPeso());
            setObjetosActuales(getObjetosActuales() - torito.getEspacio());
            arrayArmaduras.remove(torito);
        }
    }


}
