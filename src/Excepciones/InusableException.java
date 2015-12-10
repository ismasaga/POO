package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class InusableException extends Exception{
    String objeto;
    public InusableException(String objeto){
        this.objeto = new String(objeto);
    }

    public String getError(){
        return "Imposible utilizar " + this.objeto + ".";
    }
}
