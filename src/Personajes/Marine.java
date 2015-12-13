package Personajes;

import Excepciones.*;
import Juego.Mapa;
import Objetos.Arma;
import Objetos.Objeto;

import java.awt.*;

public class Marine extends Jugador {

    private Arma armaDosM2;
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
    public void equipar(Arma arma, String mano) throws SegmentationFaultException {
        if(arma.isDosManos() && mano.equals("izquierda")){ //Tengo que tener en cuenta este caso especial
            if(armaDosM2 != null) {
                if (super.getMochila().anadirArma(armaDosM2)) {
                    armaDosM2 = arma;
                }
                else{
                    throw new SegmentationFaultException();
                }
            }
        }
        else //Equipo como siempre
            super.equipar(arma, mano);
    }

    //No puede coger explosivos
    @Override
    public void coger(Objeto objeto) throws SegmentationFaultException, PesoMaximoException, EspacioMaximoException {
        if(objeto.getNombre().equals("explosivos") || objeto.getNombre().equals("bazooka"))
            throw new SegmentationFaultException();
        else
            super.coger(objeto);
    }
}
