package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class PesoMaximoException extends Exception{
    public PesoMaximoException(){};

    public String getError(){
        return "Error: se ha excedido el peso máximo de la mochila";
    }
}
