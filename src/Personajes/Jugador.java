package Personajes;

import Excepciones.ArmaDosManosException;
import Excepciones.FueraDeRangoException;
import Excepciones.InsuficienteEnergiaException;
import Excepciones.SegmentationFaultException;
import Juego.Celda;
import Juego.Consola;
import Juego.ConsolaNormal;
import Juego.Mapa;
import Objetos.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Jugador extends Personaje {
    private Binocular binocular;

    /**
     * Constructor para archivo parseado
     */
    public Jugador(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa,punto,nombre,vidaMaxAct,energiaMaxAct);
        setBinocular(null); //Por defecto
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
        ArrayList<Binocular> arrayBin;
        ArrayList<Botiquin> arrayBot;
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

        System.out.println("Got celda: " + celdaI + "," + celdaJ + "!=null " + (celda != null));
        if (celda == null) {
            throw new SegmentationFaultException();
        }

        if(objeto == null) //Imprimir solo los nombres de lo que hay en la celda
        {
            for (Objeto obj : celda.getArrayObjetos()) {
                consola.imprimir(obj.getNombre());
            }
        }
        else //Imprimir solo el objeto
        {
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
     * Ataca a todos los enemigos de la celda especificada
     * Este método si comprueba que se sueltan las pertenencias de los enemigos que se matan
     * @param celda
     */
    public void atacar(Celda celda) throws InsuficienteEnergiaException
    {
        Consola consola = new ConsolaNormal();
        int coeficienteAtaque; //Previene que se sume vida al atacar
        Random random = new Random();

        float prob = random.nextFloat();
        int ataqueEjecutado;

            ArrayList<Enemigo> enemigosAbatidos = new ArrayList<>();
            for (Enemigo enemigo : celda.getEnemigo()) {
                //Ahora hay que dividir el daño del personaje entre todos los enemigos
                if (prob > 0.25) //No es crítico
                {
                    ataqueEjecutado = (getAtaque() / celda.getEnemigo().size()) * 20 / enemigo.getArmadura().getDefensa();
                } else //Golpe critico
                {
                    ataqueEjecutado = (2 * (getAtaque() / celda.getEnemigo().size())) * 20 / enemigo.getArmadura().getDefensa();
                    System.out.println("CR1T 1N Y0U8 F4C3");
                }
                if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
                {
                    ataqueEjecutado = 0;
                }
                enemigo.setVidaActual(enemigo.getVidaActual() - ataqueEjecutado);
                if (enemigo.getVidaActual() <= 0) {
                    System.out.println("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                    enemigosAbatidos.add(enemigo);
                }
        }
        if(this.getEnergiaActual() - 20 < 0)
            throw new InsuficienteEnergiaException("Insuficiente energia para atacar");
        else
            this.setEnergiaActual(this.getEnergiaActual() - 20);
        for (Enemigo enemigo : enemigosAbatidos) {
            enemigo.soltarObjetos(celda);
            celda.eliminarEnemigo(enemigo);
        }
    }
}
