package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class EnemigoInexistenteException extends Exception{
    public EnemigoInexistenteException(){};

    public String getError(){
        return "No hay un enemigo en la celda.";
    }
}
