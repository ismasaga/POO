package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class EquiparArmaIzquierdaException extends Exception{
    public EquiparArmaIzquierdaException(){};

    public String getError(){
        return "Error equipando arma izquierda.";
    }
}
