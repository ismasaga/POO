package Excepciones;

/**
 * Errores generales
 */
public class SegmentationFaultException extends Exception{
     public SegmentationFaultException(){};

     public String getError(){
          return "Segmentation fault (Core dumped).";
     }

}
