package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class InsuficienteEnergiaException extends Exception{
    public InsuficienteEnergiaException(){};

    public String getError(){
        return "Error: no hay suficiente energia";
    }
}
