package Excepciones;

/**
 * Indica que atacar o mirar esta fuera de rango
 */
public class FueraDeRangoException extends Exception{
    String accion;
    public FueraDeRangoException(){};

    public FueraDeRangoException(String accion){
        this.accion = new String(accion);
    }

    public String getError(){
        return "No hay suficiente rango de visión para " + accion + " a la celda.";
    }
}
