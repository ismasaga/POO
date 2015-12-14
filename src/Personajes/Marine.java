package Personajes;

import Excepciones.*;
import Juego.*;
import Objetos.Arma;
import Objetos.Objeto;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public final class Marine extends Jugador {

    private Arma armaDosM2;
    private ConsolaNormal consola = new ConsolaNormal();
    /**
     * Constructor para archivo parseado
     */
    public Marine(Mapa mapa, Point punto, String nombre, int vidaMaxAct, int energiaMaxAct) {
        super(mapa , punto, nombre, vidaMaxAct, energiaMaxAct);
        setBinocular(null); //Por defecto
    }

    public Arma getArmaDosM2() {
        return armaDosM2;
    }

    public void setArmaDosM2(Arma armaDosM2) {
        this.armaDosM2 = armaDosM2;
    }

    @Override
    public int getAtaque() {
        return (super.getAtaque() + (armaDosM2 != null ? armaDosM2.getDano() : 0));
    }

    @Override
    public void mover(char direccion) throws MoverException, InsuficienteEnergiaException {
        double correccionEnergia = (armaDosM2 != null ? 1.5 : 1); //Corrección de energia
        final int ENERGIA_REQUERIDA = (int)(10*correccionEnergia); //Requiere mas energia

        int i = (int)getPunto().getX();
        int j = (int)getPunto().getY();

        /**
         * If que controla que quede suficiente energia para mover tantas celdas
         */
        if ((this.getEnergiaActual() - ENERGIA_REQUERIDA) < 0)
            throw new InsuficienteEnergiaException("Energia insuficiente para moverse.");
        else
            this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));

        if (direccion == 'u' && i - 1 >= 0)
            if (getMapa().getCelda(i - 1, j).isTransitable()) {
                    getMapa().getCelda(i,j).setJugador(null);
                    getMapa().getCelda(i - 1, j).setJugador((Jugador) this);
                    getPunto().setLocation(i-1,j);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'd' && i + 1 < getMapa().getAlto())
            if (getMapa().getCelda(i + 1, j).isTransitable()) {
                    getMapa().getCelda(i, j).setJugador(null);
                    getMapa().getCelda(i + 1, j).setJugador((Jugador) this);
                    getPunto().setLocation(i + 1,j);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'l' && j - 1 >= 0)
            if (getMapa().getCelda(i, j - 1).isTransitable()) {
                    getMapa().getCelda(i, j).setJugador(null);
                    getMapa().getCelda(i, j - 1).setJugador((Jugador) this);
                    getPunto().setLocation(i,j - 1);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else if (direccion == 'r' && j + 1 < getMapa().getAncho())
            if (getMapa().getCelda(i, j + 1).isTransitable()) {
                    getMapa().getCelda(i,j).setJugador(null);
                    getMapa().getCelda(i, j + 1).setJugador((Jugador) this);
                    getPunto().setLocation(i,j + 1);
            }
            else {
                this.setEnergiaActual((int)(this.getEnergiaActual() - ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
                throw new MoverException("La celda de arriba es intransitable.");
            }
        else {
            this.setEnergiaActual((int)(this.getEnergiaActual() + ((ENERGIA_REQUERIDA + getMochila().getPesoActual() / 5))));
            throw new MoverException("Error moviendo");
        }
    }

    /**
     * No permite equipar una de 2 y una de 1
     * @param arma
     * @param mano
     * @throws SegmentationFaultException
     */
    @Override
    public void equipar(Arma arma, String mano) throws ManosArmaException {
        if(arma.isDosManos() && mano.equals("izquierda")){ //Tengo que tener en cuenta este caso especial
            if(arma != null) {
                super.getMochila().quitarArma(arma);
                armaDosM2 = arma;
            } else {
                consola.imprimirError("No se ha podido equipar el arma: segfault"); // No es un error del usuario
            }
        }
        else //Equipo como siempre
            super.equipar(arma, mano);
    }

    @Override
    public void desequipar(Arma arma) {

        if(arma != null && arma.equals(armaDosM2)){
            if(super.getMochila().anadirArma(arma)) {
                armaDosM2 = null;
            }
        }
        else {
            super.desequipar(arma);
        }
    }

    //No puede coger explosivos
    @Override
    public void coger(Objeto objeto) throws ExplosivosException , PesoMaximoException, EspacioMaximoException {
        if(objeto.getNombre().equals("explosivos") || objeto.getNombre().equals("bazooka"))
            throw new ExplosivosException("El marine no puede equipar explosivos");
        else
            super.coger(objeto);
    }

    @Override
    public void atacar(Personaje personaje) throws InsuficienteEnergiaException {
        Consola consola = new ConsolaNormal();
        int distanciaX = Math.abs(this.getPunto().x - personaje.getPunto().x);
        int distanciaY = Math.abs(this.getPunto().y - personaje.getPunto().y);
        int coeficienteAtaque; //Previene que se sume vida al atacar
        double correcccionAtaque = (distanciaX > 2 || distanciaY > 2 ? 0.05 : 2);
        Random random = new Random();
        int defensa = (personaje.getArmadura() != null && personaje.getArmadura().getDefensa() > 0? personaje.getArmadura().getDefensa() : 1);

        float prob = random.nextFloat();
        int ataqueEjecutado;

        if (prob > 0.25) { //No es critico
            ataqueEjecutado = (int)(correcccionAtaque * getAtaque() * Constantes.REDUCCION_ARMADURA / defensa);
        } else { //Golpe critico
            ataqueEjecutado = (int)(correcccionAtaque * 2 * (getAtaque() * Constantes.REDUCCION_ARMADURA / defensa));
            consola.imprimir("CR1T 1N Y0U8 F4C3");
        }
        if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
        {
            ataqueEjecutado = 0;
        }

        if(this.getEnergiaActual() - 20 < 0)
            throw new InsuficienteEnergiaException("Insuficiente energia para atacar");
        else
            this.setEnergiaActual(this.getEnergiaActual() - 20);
        personaje.setVidaActual(personaje.getVidaActual() - ataqueEjecutado);
        consola.imprimir("El personaje " + personaje.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + personaje.getVidaActual());
    }

    public void atacar(Celda celda) throws InsuficienteEnergiaException, EnemigoInexistenteException
    {
        Consola consola = new ConsolaNormal();
        int distanciaX = Math.abs(this.getPunto().x - celda.getPunto().x);
        int distanciaY = Math.abs(this.getPunto().y - celda.getPunto().y);
        int coeficienteAtaque; //Previene que se sume vida al atacar
        double correcccionAtaque = (distanciaX > 2 || distanciaY > 2 ? 0.05 : 2);
        Random random = new Random();

        float prob = random.nextFloat();
        int ataqueEjecutado;

        if(this.getEnergiaActual() - 20 < 0)
            throw new InsuficienteEnergiaException("Insuficiente energia para atacar");
        else
            this.setEnergiaActual(this.getEnergiaActual() - 20);
        ArrayList<Enemigo> enemigosAbatidos = new ArrayList<>();
        if(celda.getEnemigo() != null) {
            for (Enemigo enemigo : celda.getEnemigo()) {
                int defensa = (enemigo.getArmadura() != null && enemigo.getArmadura().getDefensa() > 0 ? enemigo.getArmadura().getDefensa() : 1);
                //Ahora hay que dividir el daño del personaje entre todos los enemigos
                if (prob > 0.25) //No es crítico
                {
                    ataqueEjecutado = ((int) (correcccionAtaque * getAtaque() / celda.getEnemigo().size()) * Constantes.REDUCCION_ARMADURA / defensa);
                } else //Golpe critico
                {
                    ataqueEjecutado = ((int) (correcccionAtaque * 2 * (getAtaque() / celda.getEnemigo().size())) * Constantes.REDUCCION_ARMADURA / defensa);
                    consola.imprimir("CR1T 1N Y0U8 F4C3");
                }
                if (ataqueEjecutado < 0) //No queremos sumar vida al enemigo
                {
                    ataqueEjecutado = 0;
                }
                enemigo.setVidaActual(enemigo.getVidaActual() - ataqueEjecutado);
                consola.imprimir("El personaje " + enemigo.getNombre() + " ha sido dañado en " + ataqueEjecutado + "\nVida restante: " + enemigo.getVidaActual());
                if (enemigo.getVidaActual() <= 0) {
                    consola.imprimir("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                    enemigosAbatidos.add(enemigo);
                }
            }
            for (Enemigo enemigo : enemigosAbatidos) {
                enemigo.soltarObjetos(celda);
                celda.eliminarEnemigo(enemigo);
            }
        }
        else{
            throw new EnemigoInexistenteException("No hay un enemigo en esa celda");
        }
    }

    @Override
    public void info() {
        Consola consola = new ConsolaNormal();
        super.info();
        if(armaDosM2 != null) {
            consola.imprimir("Arma de dos manos izquierda: " + armaDosM2.getNombre());
            armaDosM2.info();
        }

    }
}
