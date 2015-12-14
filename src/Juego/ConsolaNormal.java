package Juego;

import java.util.Scanner;

public class ConsolaNormal implements Consola {

    public void imprimir(String mensaje){
        System.out.println(mensaje);
    }

    public String leer(String mensaje){
        System.out.print(mensaje);
        Scanner scanner = new Scanner(System.in) ;
        return scanner.nextLine();
    }

    public void imprimirSinSalto(String mensaje){
        System.out.print(mensaje);
    }

    public void imprimirError(String mensaje) {
        System.err.println(mensaje);
    }
}
