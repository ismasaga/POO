package Excepciones;

/**
 * Esta excepcion se utiliza cuando se equipa en la mano izquierda un arma de dos manos
 */
public class ArmaDosManosException extends Exception{
    public ArmaDosManosException(){};

    public String getError(){
        return "Error: el arma que intentas equipar es de dos manos";
    }
}
