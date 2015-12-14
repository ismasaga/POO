package Excepciones;

/**
 * Indica que atacar o mirar esta fuera de rango
 */
public class FueraDeRangoException extends Exception{
    String accion;
    public FueraDeRangoException(String accion){
        super(accion);
    }

}
