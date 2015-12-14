package Juego;

import Excepciones.*;

public interface Comando {

    void ejecutar() throws
            ComandoException,
            MoverException,
            InsuficienteEnergiaException,
            SegmentationFaultException,
            FueraDeRangoException,
            EspacioMaximoException,
            PesoMaximoException,
            ObjetoException,
            ManosArmaException,
            ExplosivosException,
            EnemigoInexistenteException;
}
