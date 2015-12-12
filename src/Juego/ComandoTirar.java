package Juego;

import Excepciones.*;
import Objetos.*;
import Personajes.Personaje;

/**
 * Created by efren on 12/12/15.
 */
public class ComandoTirar implements Comando{

    private Mapa mapa;
    private Personaje personaje;
    private String objeto;

    public ComandoTirar(Mapa mapa, Personaje personaje, String objeto) {
        this.mapa = mapa;
        this.personaje = personaje;
        this.objeto = objeto;
    }

    @Override
    public void ejecutar() throws SegmentationFaultException, ComandoException{
        for(Objeto obj: personaje.getMochila().getArrayArmas()){
            if(obj.getNombre().equals(objeto)){
                personaje.tirar(obj);
                return;
            }
        }
        for (Objeto obj: personaje.getMochila().getArrayArmaduras()){
            if(obj.getNombre().equals(objeto)){
                personaje.tirar(obj);
                return;
            }
        }
        for (Objeto obj: personaje.getMochila().getArrayBinoculares()){
            if(obj.getNombre().equals(objeto)){
                personaje.tirar(obj);
                return;
            }
        }
        for (Objeto obj: personaje.getMochila().getArrayBotiquin()){
            if(obj.getNombre().equals(objeto)){
                personaje.tirar(obj);
                return;
            }
        }
        for (Objeto obj: personaje.getMochila().getArrayTorito()){
            if(obj.getNombre().equals(objeto)){
                personaje.tirar(obj);
                return;
            }
        }
        throw new ComandoException("No se pudo tirar objeto");
    }
}
