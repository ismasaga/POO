package Juego;

public interface Comando {

    void ejecutar() throws Exception; //Lanza comandoException

    void mover();

    void coger();

    void atacar();

    void usar();
}
