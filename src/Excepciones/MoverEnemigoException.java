package Excepciones;

/**
 * Error general moviendo al enemigo
 */
public class MoverEnemigoException extends Exception{
    public MoverEnemigoException(){};

    public String getError(){
        return "Error: no se puede mover al enemigo.";
    }
}
