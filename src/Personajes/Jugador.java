package Personajes;

import Excepciones.*;
import Juego.Celda;
import Juego.Consola;
import Juego.ConsolaNormal;
import Juego.Mapa;
import Objetos.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract  class Jugador extends Personaje {
    private Binocular binocular;
    private boolean tieneToritina;

    /**
     * Constructor para archivo parseado
     */
    public Jugador(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa,punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
    }

    public boolean isTieneToritina() {
        return tieneToritina;
    }

    public void setTieneToritina(boolean tieneToritina) {
        this.tieneToritina = tieneToritina;
    }

    /**
     * Devolve null cando non ten equipado ningun binocular
     */
    public Binocular getBinocular() {
        return binocular;
    }

    /**
     * Pode ser null para indicar que non ten binocular equipado
     */
    public void setBinocular(Binocular binocular) {
        this.binocular = binocular;
    }

    /*
     * Método antiguo.
     * Mira la celda especificada por la posicion y la direccion. Si se especifica un objeto solo imprime informacion sobre ese objeto
     *
     * @param posicionX  Posicion a mirar. Si es 0 se mira la actual. (eje x)
     * @param posicionX  Posicion a mirar. Si es 0 se mira la actual. (eje x)
     * @param direccionX Direccion a mirar. (rIGHT,lEFT)
     * @param direccionY Direccion a mirar. (uP,dOWN)
     * @param objeto     Objeto a mirar. Si es null se listan todos
     */
    public void mirar(Mapa mapa, int posicionX, int posicionY, char direccionX, char direccionY, String objeto) throws SegmentationFaultException, FueraDeRangoException {
        Consola consola = new ConsolaNormal();
        Celda celda;
        int[] array = new int[2];
        //Si esta fuera del rango de vision
        int i = getPunto().x;
        int j = getPunto().y;

        celda = mapa.getCelda(i, j);
        int celdaI = direccionY == 'u' ? i - posicionY : i;
        celdaI = direccionY == 'd' ? i + posicionY : celdaI;
        int celdaJ = (direccionX == 'l' ? j - posicionX : j);
        celdaJ = (direccionX == 'r' ? j + posicionX : celdaJ);
        //Se necesitan los iguales porque esta en base 0
        if (celdaJ < 0 || celdaJ >= mapa.getAncho() || celdaI < 0 || celdaI >= mapa.getAlto()) {
            throw new FueraDeRangoException("Mirar fuera de rango");
        }
        //If importado de mapa
        if ((celdaI >= i - getRangoVision() &&
                celdaI <= i + getRangoVision() &&
                celdaJ >= j - getRangoVision() &&
                celdaJ <= j + getRangoVision()))
            celda = mapa.getCelda(celdaI, celdaJ);

        consola.imprimir("Got celda: " + celdaI + "," + celdaJ + "!=null " + (celda != null));
        if (celda == null) {
            throw new SegmentationFaultException();
        }

        if(objeto == null) //Imprimir solo los nombres de lo que hay en la celda
        {
            if(celda.getEnemigo() != null){
                for(Enemigo enemigo : celda.getEnemigo()){
                    consola.imprimir(enemigo.getNombre());
                }
            }
            for (Objeto obj : celda.getArrayObjetos()) {
                consola.imprimir(obj.getNombre());
            }
        }
        else //Imprimir solo el objeto
        {
            if(celda.getEnemigo() != null){
                for(Enemigo enemigo : celda.getEnemigo()){
                    if(enemigo.getNombre().equals(objeto)){
                        enemigo.info();
                        return;
                    }
                }
            }
            for(Objeto obj : celda.getArrayObjetos()) {
                if(obj.getNombre().equals(objeto)) { //Hemos obtenido el objeto
                    if(obj instanceof Arma)
                        ((Arma)obj).info();
                    else if (obj instanceof Armadura)
                        ((Armadura)obj).info();
                    else if (obj instanceof Binocular)
                        ((Binocular)obj).info();
                    else if (obj instanceof Botiquin)
                        ((Botiquin)obj).info();
                    else if (obj instanceof Torito)
                        ((Torito)obj).info();
                    return;
                }
            }
        }
    }

    /**
     * Usa o binocular equipandoo na persoaxe, no caso de que a persoaxe xa teña un binocular,
     * so se equipará o novo se supera en rango de visión ó actual
     */
    public boolean usar(Binocular binocular) {
        boolean usado = false;
        if(getBinocular() != null) {
            if(getBinocular().getVision() < binocular.getVision()) {
                getMochila().anadirBinocular(getBinocular());
                binocular.usar(this);
                binocular.setUsado(true);
                setBinocular(binocular);
                getMochila().quitarBinocular(binocular);
                usado = true;
            }
        } else {
            binocular.usar(this);
            binocular.setUsado(true);
            setBinocular(binocular);
            getMochila().quitarBinocular(binocular);
            usado = true;
        }
        return usado;
    }

    public void usar(Botiquin botiquin) {
        botiquin.usar(this);
        getMochila().quitarBotiquin(botiquin);
    }

    public void usar(Torito torito) {
        torito.usar(this);
        getMochila().quitarTorito(torito);
    }

    /**
     * Ataca a todos los enemigos de la celda especificada
     * Este método si comprueba que se sueltan las pertenencias de los enemigos que se matan
     */
    public abstract void atacar(Celda celda) throws InsuficienteEnergiaException, EnemigoInexistenteException;
    public abstract void atacar(Personaje personaje) throws InsuficienteEnergiaException, EnemigoInexistenteException;


    public void pasar(Mapa mapa, Personaje personaje) {
        if(!tieneToritina)
            setEnergiaActual(getEnergiaMaxima());
        else {
            setEnergiaActual((int) (0.9 * getEnergiaMaxima()));
            tieneToritina = false;
        }
        ArrayList<Enemigo> arrayEnemigos = new ArrayList<>();
        ArrayList<Integer[]> arrayPos = new ArrayList<>();
        for (int i = 0; i < mapa.getAlto(); i++) {
            for (int j = 0; j < mapa.getAncho(); j++) {
                Celda celda = mapa.getCelda(i, j);
                if (celda.getEnemigo() != null) {
                    for (Enemigo enemigo : celda.getEnemigo()) {
                        if (enemigo != null) {
                            arrayEnemigos.add(enemigo);
                            Integer[] pos = new Integer[2];
                            pos[0] = i;
                            pos[1] = j;
                            arrayPos.add(pos);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < arrayEnemigos.size(); i++) {
            arrayEnemigos.get(i).ia(mapa, personaje);
        }
    }
}
