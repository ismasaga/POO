package Excepciones;

/**
 * Esta excepción se lanza cuando no se logra asignar un objeto a una celda o mapa.
 */
public class AsignacionException extends Exception{
    String objeto;
    public AsignacionException(){};

    public AsignacionException(String objeto){
        super(objeto);
        this.objeto = new String(objeto);
    }

    public String getError(){
        return "Error asignando " + objeto + ".";
    }
}
