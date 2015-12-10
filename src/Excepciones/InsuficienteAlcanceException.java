package Excepciones;

/**
 * Esta excepción se tira cuando no hay suficiente alcance del arma para atacar.
 * Distingue entre arma derecha, izquierda o dos manos.
 */
public class InsuficienteAlcanceException extends Exception{
    String mano;
    public InsuficienteAlcanceException(String mano){
        this.mano = new String(mano);
    }

    public String getError(){
        return "Tu arma " + mano + " no tiene suficiente alcance.";
    }
}
