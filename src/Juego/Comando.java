package Juego;

import Excepciones.ComandoException;
import Excepciones.InsuficienteEnergiaException;
import Excepciones.MoverException;

public interface Comando {

    void ejecutar() throws ComandoException,MoverException,InsuficienteEnergiaException;
}
