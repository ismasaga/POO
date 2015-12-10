package Excepciones;


/**
 * Created by efren on 10/12/15.
 */
public class ObjetoInexistenteMochilaException extends Exception{
    String objeto;
    public ObjetoInexistenteMochilaException(){};

    public ObjetoInexistenteMochilaException(String objeto){
        this.objeto = new String(objeto);
    }

    public String getError(){
        return "No se ha encontrado -" + objeto + "-en la mochila";
    }
}
