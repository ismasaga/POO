package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class EquiparArmaDosManos extends Exception{
    public EquiparArmaDosManos(){};

    public String getError(){
        return "Error equipando arma de dos manos.";
    }
}
