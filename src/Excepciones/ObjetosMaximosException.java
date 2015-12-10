package Excepciones;

/**
 * Created by efren on 10/12/15.
 */
public class ObjetosMaximosException extends Exception{
    public ObjetosMaximosException(){};

    public String getError(){
        return "Error: se ha excedido el numero maximo de objetos maximos en la mochila";
    }
}
