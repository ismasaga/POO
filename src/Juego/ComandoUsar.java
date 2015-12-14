package Juego;

import Excepciones.*;
import Objetos.Binocular;
import Objetos.Botiquin;
import Objetos.Objeto;
import Objetos.Torito;
import Personajes.Jugador;

public class ComandoUsar implements Comando {
    private Objeto objeto = null;
    private Jugador personaje;

    public ComandoUsar(Jugador personaje, String nombreObjeto) throws ComandoException, ObjetoInexistenteMochilaException {
        if(nombreObjeto != null && personaje != null) {
            for (Objeto o : personaje.getMochila().getObjetos())
                if (o.getNombre().equals(nombreObjeto))
                    objeto = o;
            if (objeto == null)
                throw new ObjetoInexistenteMochilaException("El objeto no está en la mochila.");
            this.personaje = personaje;
        } else
            throw new ComandoException("Se ha pasado un parámetro nulo.");
    }

    @Override
    public void ejecutar() throws ObjetoException {
        if(objeto instanceof Binocular) {
            if (!personaje.usar((Binocular) objeto))
                throw new ObjetoException("El binocular no ha sido equipado porque no mejorará la visión, desequipe antes el actual.");
        } else if(objeto instanceof Botiquin)
            personaje.usar((Botiquin)objeto);
        else if(objeto instanceof Torito)
            personaje.usar((Torito)objeto);
        else
            throw new ObjetoException("Tipo de objeto no contemplado.");
    }
}
