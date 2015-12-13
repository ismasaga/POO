package Excepciones;

public class ObjetoInexistenteMochilaException extends Exception {

    public ObjetoInexistenteMochilaException(String objeto){
        super(objeto);
    }
}
