package Excepciones;


/**
 * Esta excepcion se lanza cuando no se consigue eliminar algo de una celda  o mapa.
 */
public class EliminacionException extends Exception{
    String objeto;
    public EliminacionException(){};

    public EliminacionException(String objeto){
        this.objeto = new String(objeto);
    }

    public String getError(){
        return "Error eliminando " + objeto + ".";
    }
}
