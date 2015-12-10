package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class CeldaInexistenteAtacarException extends Exception{
    public CeldaInexistenteAtacarException(){};

    public String getError(){
        return "Error: la celda a atacar es inexistente";
    }
}
