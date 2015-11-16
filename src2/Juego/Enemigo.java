package Juego;

import Objetos.*;
import Juego.*;

import java.util.ArrayList;

/**
 * Clase enemigo. Funciones:
 * - Atacar y moverse.
 * - Detectar proximidad del personaje.
 *
 * A destacar:
 *
 * VIDA_MAXIMA, ataque y armadura son constantes pues no hay manera de modificarlos (con elementos
 * del juego )en esta versión.
 */
public class Enemigo
{
    private final int VIDA_MAXIMA;
    private int puntosVida;
    private Arma armaDer;
    private Arma armaIzq;
    private Arma armaDosM;
    /**
     * El daño se calcula como: daño = ataque - armadura
     */
    private Armadura armadura;
    private Mochila mochila;
    private String nombre;


    public Enemigo(int VIDA_MAXIMA, int puntosVida,Arma armaIzq,Arma armaDer,Armadura armadura, String nombre)
    {
        this.VIDA_MAXIMA = VIDA_MAXIMA > 0 ? VIDA_MAXIMA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= VIDA_MAXIMA) ? puntosVida : 100;
        setArmaDer(armaDer);
        setArmaIzq(armaIzq);
        setArmadura(armadura);
        setNombre(nombre);
    }

    public Enemigo(int VIDA_MAXIMA, int puntosVida,ArrayList<Arma> armas, Armadura armadura)
    {
        this.VIDA_MAXIMA = VIDA_MAXIMA > 0 ? VIDA_MAXIMA : 100;
        this.puntosVida = (puntosVida > 0 && puntosVida <= VIDA_MAXIMA) ? puntosVida : 100;
        setArmas(armas);
        setArmadura(armadura);
        setNombre("desconocido");
    }

    public int getVIDA_MAXIMA()
    {
        return VIDA_MAXIMA;
    }

    /**
     * Devuelve un entero con el ataque total del enemigo tenga las armas que tenga
     * @return ataque
     */
    public int getAtaque() {
        ArrayList<Arma> armas = getArmas();
        int ataque = 0;
        if(armas.size() > 0 && armas.size() <= 2)
            for(Arma arma : armas)
                ataque += arma.getDano();
        else
            System.out.println("ERROR, no hay armas con las que atacar.");
        return ataque;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public void setPuntosVida(int puntosVida) {
        if(puntosVida < 0)
        {
            this.puntosVida = 0;
            return;
        }
        if (puntosVida > VIDA_MAXIMA)
        {
            this.puntosVida = VIDA_MAXIMA;
            return;
        }
        this.puntosVida = puntosVida;
    }

    public void atacar(Personaje personaje)
    {
        int coeficienteAtaque; //Esta variable previene que un ataque sume puntos de vida (armadura > ataque)
        coeficienteAtaque = (getAtaque() - personaje.getArmadura().getDefensa() >= 0) ? getAtaque() - personaje.getArmadura().getDefensa() : 0;
        personaje.setPuntosVida(personaje.getPuntosVida() - coeficienteAtaque);
    }

    /**
     * Asigna nombre al enemigo
     * @param nombre a asignar al enemigo
     */
    public void setNombre(String nombre) {
        if(nombre != null)
            this.nombre = nombre;
        else
            System.out.println("ERROR asignando nombre al enemigo");
    }

    /**
     * Devuelve el nombre del enemigo, en caso de que no fuese definido devolvería null
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la armadura, en caso de que no este equipada devolverá null
     * @return Armadura
     */
    public Armadura getArmadura() {
        return armadura;
    }

    /**
     * Asigna armadura al enemigo
     * @param armadura
     */
    public void setArmadura(Armadura armadura) {
        if(armadura != null)
            this.armadura = armadura;
        else
            System.out.println("ERROR asignando armadura al enemigo");
    }

    /**
     * Devuelve un arraylist de las armas que lleva equipadas el enemigo
     * ATENCION : si no lleva ninguna devuelve el conjunto pero vacio
     * @return conjuntoArmas
     */
    public ArrayList<Arma> getArmas() {
        ArrayList<Arma> conjuntoArmas = new ArrayList<>();
        if(armaDer != null)
            conjuntoArmas.add(armaDer);
        if(armaIzq != null)
            conjuntoArmas.add(armaIzq);
        if(armaDosM != null)
            conjuntoArmas.add(armaDosM);
        return conjuntoArmas;
    }

    /**
     * Este método equipa las armas al enemigo dado un array de ellas(1 de dos manos o 2 de una mano).
     * ATENCIÓN : si se usa éste método se eliminarán las armas que el enemigo lla portaba
     * ATENCIÓN : si se usa para mandar solo una arma de una mano ésta quedará equipada en la mano derecha.
     * ATENCIÓN : en caso de ser dos armas a una mano la primera irá a la mano derecha.
     * @param armas
     */
    public void setArmas(ArrayList<Arma> armas) {
        if(armas != null) {
            if(armas.size() == 1) {
                if(armas.get(0).isDosManos()) {
                    armaDosM = armas.get(0);
                    armaDer = null;
                    armaIzq = null;
                } else {
                    armaDer = armas.get(0);
                    armaDosM = null;
                }
            } else if(armas.size() == 2) {
                if(armas.get(0).isDosManos() || armas.get(1).isDosManos())
                    System.out.println("ERROR, quieres equipar dos armas pero una de ellas es a dos manos(El enemigo solo tiene dos manos)");
                else {
                    armaDer = armas.get(0);
                    armaIzq = armas.get(1);
                    armaDosM = null;
                }
            } else
                System.out.println("ERROR en el número de armas que quieres equipar al enemigo");
        } else
            System.out.println("ERROR asignando las armas al enemigo");
    }

    /**
     * Este método equipa el arma en la mano derecha, admite null
     */
    public void setArmaDer(Arma armaDer) {
        if(armaDer != null)
            if(armaDer.isDosManos())
                System.out.println("ERROR, el arma es de dos manos");
            else {
                this.armaDer = armaDer;
                this.armaDosM = null;
            }
    }

    /**
     * Este método equipa el arma en la mano izquierda, admite null
     */
    public void setArmaIzq(Arma armaIzq) {
        if(armaIzq != null)
            if(armaIzq.isDosManos())
                System.out.println("ERROR, el arma es de dos manos");
            else {
                this.armaIzq = armaIzq;
                this.armaDosM = null;
            }
    }

    /**
     * Este método equipa el arma de dos manos
     */
    public void setArmaDosM(Arma armaDosM) {
        if(armaDosM != null)
            if(!armaDosM.isDosManos())
                System.out.println("ERROR, el arma es de una mano");
            else {
                this.armaDosM = armaDosM;
                this.armaDer = null;
                this.armaIzq = null;
            }
        else
            System.out.println("ERROR en el arma de dos manos que intentas equipar");
    }

    /**
     * Imprime informacion sobre el enemigo
     */
    public void info()
    {
        System.out.println("Enemigo: ");
        System.out.println("Nombre" + getNombre() +
                "\nPuntos de vida: " + getPuntosVida() +
                "\nPuntos de ataque: " + getAtaque() +
                "\nPuntos de armadura: " + getArmadura());
        if(!getArmas().isEmpty()) //Si el enemigo tiene armas
        {
            for(Arma arma : getArmas())
            {
                arma.info();
            }
        }
    }

    /**
     * Suelta todos los objetos del enemigo en la celda actual (pasada por parametro)
     * @param celda Celda actual
     */
    public void soltarObjetos(Celda celda)
    {
        for(Arma arma : this.getArmas())
        {
            if(arma != null)
                celda.setArma(arma);
            armaDer = null;
            armaIzq = null;
            armaDosM = null;
        }
        if(getArmadura() != null)
        {
            celda.setArmadura(getArmadura());
            armadura = null;
        }
        for(Arma arma : mochila.getArrayArmas())
        {
            if(arma != null)
                celda.setArma(arma);
        }
        mochila.setArrayArmaduras(null); //Se marca para eliminacion
        for(Armadura armadura : mochila.getArrayArmaduras())
        {
            if(armadura != null)
                celda.setArmadura(armadura);
        }
        mochila.setArrayArmaduras(null);
        for(Binoculares bin : mochila.getArrayBinoculares())
        {
            if(bin != null)
            {
                celda.setBinoculares(bin);
                mochila.getArrayBinoculares().remove(bin);
            }
        }
        for(Botiquin bot : mochila.getArrayBotiquin())
        {
            if(bot != null)
            {
                celda.setBotiquin(bot);
                mochila.getArrayBotiquin().remove(bot);
            }
        }
    }
}
