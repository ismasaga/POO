package Juego;

import Excepciones.*;
import java.util.ArrayList;

public class ComandoCompuesto implements Comando {

    private ArrayList<Comando> arrayComandos = new ArrayList<>();
    ConsolaNormal consola = new ConsolaNormal();

    @Override
    public void ejecutar() throws EnemigoInexistenteException, ComandoException, MoverException, InsuficienteEnergiaException, SegmentationFaultException, FueraDeRangoException, EspacioMaximoException, PesoMaximoException {
        for(Comando comando : arrayComandos){
            try {
                comando.ejecutar();
            } catch (ComandoException e) {
                consola.imprimirError("Error de comando : "+e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                consola.imprimirError("Error introduciendo el comando, mire la ayuda");
                e.printStackTrace();
            } catch (MoverException e) {
                consola.imprimirError("Error moviendo : "+e.getMessage());
            } catch (InsuficienteEnergiaException e) {
                consola.imprimirError("Error de energia : "+e.getMessage());
            } catch(EnemigoInexistenteException | SegmentationFaultException | FueraDeRangoException | PesoMaximoException | EspacioMaximoException | ManosArmaException | ExplosivosException ex) {
                consola.imprimir(ex.getMessage());
            } catch (ObjetoException e) {
                consola.imprimirError("Error de objeto : "+e.getMessage());
            }
        }
    }

    public void addComando(Comando comando){
        arrayComandos.add(comando);
    }


}
