package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class SinArmasParaAtacarException extends Exception{
    public SinArmasParaAtacarException(){};

    public String getError(){
        return "Error: no hay armas para atacar.";
    }
}
