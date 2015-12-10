package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class NoTransitableException extends Exception{
    public NoTransitableException(){};

    public String getError(){
        return "Error: no se puede mover a una casilla no transitable";
    }
}
