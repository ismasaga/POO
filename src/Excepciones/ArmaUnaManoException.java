package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class ArmaUnaManoException extends Exception{
    public ArmaUnaManoException(){};

    public String getError(){
        return "Error: el arma es de una mano";
    }
}
