package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class ManosArmaException extends Exception{
    public ManosArmaException(){};

    public String getError(){
        return "Error definiendo cuantas manos tiene el arma.";
    }
}
