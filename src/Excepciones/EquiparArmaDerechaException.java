package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class EquiparArmaDerechaException extends Exception{
    public EquiparArmaDerechaException(){};

    public String getError(){
        return "Error: no se puede equipar arma derecha.";
    }
}
