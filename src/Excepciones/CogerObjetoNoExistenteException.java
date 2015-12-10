package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class CogerObjetoNoExistenteException extends Exception{
    public CogerObjetoNoExistenteException(){};

    public String getError(){
        return "Error: se ha intentado coger un objeto no existente.";
    }
}
