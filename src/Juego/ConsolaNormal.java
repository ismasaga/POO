package Juego;

import java.util.Scanner;

public class ConsolaNormal implements Consola{

    public void imprimir(String mensaje){
        System.out.println(mensaje);
    }

    public String leer(String mensaje){
        this.imprimir(mensaje);
        Scanner scanner = new Scanner(System.in) ;
        return scanner.nextLine();
    }

}
