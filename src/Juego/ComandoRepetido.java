package Juego;

import Excepciones.*;

public class ComandoRepetido extends ComandoCompuesto {

    private Comando comando;
    private int veces;

    public ComandoRepetido(Comando comando, int veces) {
        this.comando = comando;
        this.veces = veces;
    }

    @Override
    public void ejecutar() throws ComandoException, MoverException, InsuficienteEnergiaException, SegmentationFaultException, FueraDeRangoException, EspacioMaximoException, PesoMaximoException {
        for (int i = 0; i < veces; i++) {
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
            } catch(SegmentationFaultException | FueraDeRangoException ex) {
                consola.imprimir(ex.getMessage());
            } catch (PesoMaximoException | EspacioMaximoException e){
                consola.imprimir(e.getMessage());
            } catch (ObjetoException e) {
                consola.imprimirError("Error de objeto : "+e.getMessage());
            }
        }
    }
}
