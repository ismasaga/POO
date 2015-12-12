package Juego;

import Excepciones.ComandoException;
import Excepciones.InsuficienteEnergiaException;
import Personajes.Enemigo;
import Personajes.Jugador;
import Personajes.Personaje;

public class ComandoAtacar implements Comando {
    private Mapa mapa;
    private Jugador personaje;
    private char dirX;
    private char dirY;
    private int numX;
    private int numY;
    private String nombre;

    public ComandoAtacar(Mapa mapa, Jugador personaje, int numX, int numY, char dirX, char dirY, String nombre) {
        this.mapa = mapa;
        this.personaje = personaje;
        this.numX = numX;
        this.numY = numY;
        this.dirX = dirX;
        this.dirY = dirY;
        this.nombre = nombre;
    }

    @Override
    public void ejecutar() throws ComandoException, InsuficienteEnergiaException {
        Consola consola = new ConsolaNormal();
        int i = personaje.getPunto().x;
        int j = personaje.getPunto().y;
        int componenteI = 0;
        int componenteJ = 0;

        Celda celdaObtenida;

        if (dirY == 'q')
            componenteI = i;
        if (dirX == 'q')
            componenteJ = j;
        if (dirY == 'u' && i - numY >= 0)
            componenteI = i - numY;
        if (dirY == 'd' && i + numY < mapa.getAlto())
            componenteI = i + numY;
        if (dirX == 'l' && j - numX >= 0)
            componenteJ = j - numX;
        if (dirX == 'r' && j + numX < mapa.getAncho())
            componenteJ = j + numX;

        int minX = numX;
        if (minX > personaje.getRangoVision()) {
            System.out.println("No tienes suficiente rango de vision");
            minX = personaje.getRangoVision();
            return;
        }
        if (personaje.getArmaDer() != null) {
            if (minX > personaje.getArmaDer().getAlcance()) {
                minX = personaje.getArmaDer().getAlcance();
                System.out.println("Tu arma derecha no tiene suficiente alcance.");
                return;
            }

        }
        if (personaje.getArmaIzq() != null) {
            if (minX > personaje.getArmaIzq().getAlcance()) {
                minX = personaje.getArmaIzq().getAlcance();
                System.out.println("Tu arma izquierda no tiene suficiente alcance.");
                return;
            }
        }
        if (personaje.getArmaDosM() != null) {
            if (minX > personaje.getArmaDosM().getAlcance()) {
                minX = personaje.getArmaDosM().getAlcance();
                System.out.println("Tu arma de dos manos no tiene suficiente alcance.");
                return;
            }
        }

        /**Buscamos el maximo de casillas atacables (eje y)**/
        int minY = numY;
        if (minY > personaje.getRangoVision()) {
            System.out.println("No tienes suficiente rango de vision");
            return;
        }
        if (personaje.getArmaDer() != null) {
            if (minY > personaje.getArmaDer().getAlcance()) {
                minY = personaje.getArmaDer().getAlcance();
                System.out.println("Tu arma derecha no tiene suficiente alcance.");
                return;
            }

        }
        if (personaje.getArmaIzq() != null) {
            if (minY > personaje.getArmaIzq().getAlcance()) {
                minY = personaje.getArmaIzq().getAlcance();
                System.out.println("Tu arma izquierda no tiene suficiente alcance.");
                return;
            }
        }
        if (personaje.getArmaDosM() != null) {
            if (minY > personaje.getArmaDosM().getAlcance()) {
                minY = personaje.getArmaDosM().getAlcance();
                System.out.println("Tu arma no tiene suficiente alcance.");
                return;
            }
        }

        celdaObtenida = mapa.getCelda(componenteI,componenteJ);
        if(nombre == null) //Se ataca a todos los enemigos
        {
            (personaje).atacar(celdaObtenida);
        }
        else {
            for (Enemigo enemigo : celdaObtenida.getEnemigo()) {
                if (enemigo.getNombre().equals(nombre)) {
                    personaje.atacar(enemigo);
                    if (enemigo.getVidaActual() <= 0) {
                        consola.imprimir("El enemigo " + enemigo.getNombre() + " ha sido abatido.");
                        enemigo.soltarObjetos(celdaObtenida);
                    }
                    return;
                }
            }
        }
    }
}
