import Juego.Celda;
import Objetos.Binoculares;

import java.util.ArrayList;

/**
 * Esta clase solo debería llamar a Juego.Juego.run()...
 */
public class Main {
    public static void main(String[] args) {
        Binoculares b1 = new Binoculares(1,2,3);
        Binoculares b2 = new Binoculares(1,2,3);
        Binoculares b3 = new Binoculares(1,2,3);
        Celda c = new Celda(true);
        c.setBinoculares(b1);
        c.setBinoculares(b2);
        c.setBinoculares(b3);

        ArrayList<Binoculares> mochila = new ArrayList<>();
        mochila = c.getBinoculares();
        for(Binoculares b : mochila)
            System.out.println("Elemento : "+b);
        c.eliminarBinocular(b2);
        System.out.println("SOY UNA BARRA ESPACIADORA");
        for(Binoculares b : mochila)
            System.out.println("Elemento : "+b);
    }
}
