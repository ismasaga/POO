package Juego;

import Excepciones.*;

import java.security.PublicKey;

/**
 * Created by efren on 13/12/15.
 */
public class ComandoRepetido extends ComandoCompuesto{

    private Comando comando;
    private int veces;

    public ComandoRepetido(Comando comando, int veces) {
        this.comando = comando;
        this.veces = veces;
    }

    @Override
    public void ejecutar() throws ComandoException, MoverException, InsuficienteEnergiaException, SegmentationFaultException, FueraDeRangoException, EspacioMaximoException, PesoMaximoException {
        for (int i = 0; i < veces; i++) {
            comando.ejecutar();
        }
    }
}
