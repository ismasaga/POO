package Juego;

import Excepciones.*;
import Personajes.*;

import java.awt.*;
import java.util.ArrayList;

public class ComandoCompuesto implements Comando{

    private ArrayList<Comando> arrayComandos = new ArrayList<>();

    @Override
    public void ejecutar() throws ComandoException, MoverException, InsuficienteEnergiaException, SegmentationFaultException, FueraDeRangoException, EspacioMaximoException, PesoMaximoException {
        for(Comando comando : arrayComandos){

        }
    }

    public void addComando(Comando comando){
        arrayComandos.add(comando);
    }


}
