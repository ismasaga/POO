import Excepciones.*;
import Juego.*;
import Personajes.Jugador;

public class Main {

    public static void main(String[] args) {
        String sel;
        String[] cadeas;
        Mapa mapa;
        Jugador personaje;
        ConsolaNormal consola = new ConsolaNormal();
        Juego juego;
        String input = consola.leer("Juego por defecto o de ficheros (defecto/ficheros)");
        if(input.equals("ficheros"))
            juego = new CargadorJuegoDeFicheros("src/Juego/mapa.csv", "src/Juego/npcs.csv", "src/Juego/objetos.csv").cargarJuego();
        else
            juego = new CargadorJuegoPorDefecto().cargarJuego();
        mapa = juego.getMapa();
        personaje = juego.getPersonaje();
        /**
         * Colores para terminal
         */
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLACK = "\u001B[30m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        String ANSI_CYAN = "\u001B[36m";
        String ANSI_WHITE = "\u001B[37m";

        do {
            /**
             * Si se acaba la energía no se hace nada (ni imprimir)
             **/
            if (personaje.estaMuerto()){
                consola.imprimir("Has muerto pringao.");
                System.exit(0);
            }

            mapa.imprimir(personaje);
            consola.imprimir(personaje.getNombre() + "[Vida: " + personaje.getVidaActual() + " Energia: " + personaje.getEnergiaActual() + "]");
            sel = consola.leer("Introduza comando : ");
            cadeas = sel.split(" ");

            if(cadeas.length > 0) {
                switch (cadeas[0]) {
                    case "ayuda":
                        consola.imprimir(ANSI_BLUE + "\t\tGame Player Manual\t\t\n" + ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "mover: " + ANSI_RESET + "mueve un personaje el numero de casillas adyacentes. Para seleccionar la direccion hay que usar d, l, r o u");
                        consola.imprimir("\tEjemplos:");
                        consola.imprimir(ANSI_CYAN + "\t\tmover 2r: mover dos posiciones a la derecha");
                        consola.imprimir(ANSI_CYAN + "\t\tmover 1d: mover una posicion abajo");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "mirar: " + ANSI_RESET + "mira la posicion designada. Puede listar todos los objetos de cualquier casilla visible o obtener detalles de un objeto de su casilla actual. ");
                        consola.imprimir("Si no consigue mirar obtiene la casilla actual.");
                        consola.imprimir("\tEjemplos:");
                        consola.imprimir(ANSI_CYAN + "\t\tmirar: lista los objetos de la casilla actual");
                        consola.imprimir(ANSI_CYAN + "\t\tmirar escopeta_1: obtiene detalles de la escopeta_1 de la casilla actual");
                        consola.imprimir(ANSI_CYAN + "\t\tmirar 2r,1d: mira la casilla dos posiciones a la derecha y una abajo de la actual.");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "atacar: " + ANSI_RESET + "ataca al personaje de la celda designada. Sigue el mismo patron que mirar");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "pasar: " + ANSI_RESET + "termina tu turno");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "terminar: " + ANSI_RESET + "termina una partida");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "descripcion: " + ANSI_RESET + "imprime la descripcion del mapa");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "estado: " + ANSI_RESET + "imprime toda la informacion disponible sobre el personaje");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "inventario: " + ANSI_RESET + "imprime los contenidos de la mochila");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "coger: " + ANSI_RESET + "coge el objeto de la casilla actual");
                        consola.imprimir("\tEjemplo:");
                        consola.imprimir(ANSI_CYAN + "\t\tcoger escopeta_1: coge la escopeta_1 de la casilla actual");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "tirar: " + ANSI_RESET + "tira el objeto designado de la mochila en la celda actual. Es como el comando coger");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "desequipar: " + ANSI_RESET + "desequipa un arma, armadura o binocular equipado");
                        consola.imprimir("\tEjemplo:");
                        consola.imprimir(ANSI_CYAN + "\t\tdesequipar armadura: desequipa la armadura");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "equipar: " + ANSI_RESET + "equipa un arma, armadura o binocular de la mochila");
                        consola.imprimir("\tEjemplo:");
                        consola.imprimir(ANSI_CYAN + "\t\tequipar armadura_25");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "usar: " + ANSI_RESET + "usa un botiquin de la mochila");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir(ANSI_PURPLE + "cargar: " + ANSI_RESET + "carga los archivos de la ruta especificada");
                        consola.imprimir(ANSI_RESET);
                        consola.imprimir("\tEjemplo:");
                        consola.imprimir(ANSI_CYAN + "\t\tcargar src/Juego/: carga los archivos de src/Juego/");
                        consola.imprimir(ANSI_RESET);
                        break;
                    case "mover":
                        try {
                            if(cadeas.length == 2)
                                new ComandoMover(mapa, personaje, cadeas[1].charAt(0)).ejecutar();
                            else if(cadeas.length == 3)
                                new ComandoRepetido(new ComandoMover(mapa, personaje, cadeas[1].charAt(0)),Integer.parseInt(cadeas[2])).ejecutar();
                            else
                                consola.imprimirError("Comando mover mal introducido, mire la ayuda para saber mas");
                        } catch (ComandoException e) {
                            consola.imprimirError("Error de comando : "+e.getMessage());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            consola.imprimirError("Error introduciendo el comando, mire la ayuda");
                            e.printStackTrace();
                        } catch (MoverException e) {
                            consola.imprimirError("Error moviendo : "+e.getMessage());
                        } catch (InsuficienteEnergiaException e) {
                            consola.imprimirError("Error de energia : "+e.getMessage());
                        } catch(EnemigoInexistenteException | SegmentationFaultException | FueraDeRangoException | EspacioMaximoException | PesoMaximoException e) {
                            consola.imprimir(e.getMessage());
                        }
                        break;
                    case "mirar":
                        //Obtiene informacion de la propia casilla
                        try {
                            new ComandoMirar(mapa, personaje, sel).ejecutar();
                        }catch(SegmentationFaultException | FueraDeRangoException | ComandoException ex) {
                            consola.imprimir(ex.getMessage());
                        }
                        break;
                    case "atacar":
                        try {
                            ComandoRepetido cR;
                            ComandoCompuesto c;
                            if(cadeas.length == 3 && cadeas[2].length() == 1){ //Atacar en una direccion repetido
                                c = new ComandoCompuesto();
                                c.addComando(new ComandoAtacar(mapa,personaje,cadeas[0]+" "+cadeas[1]));
                                cR = new ComandoRepetido(c,Integer.parseInt(cadeas[2]));
                                cR.ejecutar();
                            }
                            if(cadeas.length >= 4 && cadeas[2].length() > 2 && cadeas[cadeas.length-1].length() > 1) { //Se atacas nunha direccion so a enemigos (sen repetir)
                                c = new ComandoCompuesto();
                                for (int i = 2; i < cadeas.length;i++)
                                    c.addComando(new ComandoAtacar(mapa,personaje,cadeas[0]+" "+cadeas[1]+" "+cadeas[i]));
                                c.ejecutar();
                            }
                            if(cadeas.length == 4 && cadeas[2].length() > 2 && cadeas[cadeas.length-1].length() == 1){
                                c = new ComandoCompuesto();
                                c.addComando(new ComandoAtacar(mapa,personaje,cadeas[0]+" "+cadeas[1]+" "+cadeas[2]));
                                cR = new ComandoRepetido(c,Integer.parseInt(cadeas[3]));
                                cR.ejecutar();
                            }
                            if(cadeas.length > 4 && cadeas[2].length() > 2 && cadeas[cadeas.length-1].length() == 1){   //Atacar a varios enemigos repetidamente en una direccion
                                c = new ComandoCompuesto();
                                for (int i = 2; i < cadeas.length - 1; i++) {
                                    c.addComando(new ComandoAtacar(mapa,personaje,cadeas[0]+" "+cadeas[1]+" "+cadeas[i]));
                                }
                                cR = new ComandoRepetido(c,Integer.parseInt(cadeas[cadeas.length-1]));
                                cR.ejecutar();
                            }
                            else if(cadeas.length == 4 && cadeas[2].length() == 2 && cadeas[cadeas.length-1].length() == 1) { //Para o caso de catro compoñentes
                                c = new ComandoCompuesto();
                                c.addComando(new ComandoAtacar(mapa, personaje, cadeas[0] + " " + cadeas[1] + " " + cadeas[2]));
                                cR = new ComandoRepetido(c, Integer.parseInt(cadeas[cadeas.length - 1]));
                                cR.ejecutar();
                            }
                            else if(cadeas.length > 4 && cadeas[2].length() == 2 && cadeas[cadeas.length-1].length() == 1) { //Se o segundo parametro e unha coordenada pero hay mais dun nome de inimigo e o ultimo argumento e un numero
                                c = new ComandoCompuesto();
                                for (int i = 3; i < cadeas.length - 1; i++)  //O ultimo e un numero
                                    c.addComando(new ComandoAtacar(mapa, personaje, cadeas[0] + " " + cadeas[1] + " " + cadeas[2] + " " + cadeas[i]));
                                cR = new ComandoRepetido(c, Integer.parseInt(cadeas[cadeas.length - 1]));
                                cR.ejecutar();
                            }
                            else if (cadeas.length > 4 && cadeas[2].length() == 2 && cadeas[cadeas.length-1].length() > 1){ //Se hai duas cordenadas e todos son enemigos
                                c = new ComandoCompuesto();
                                for (int i = 3; i < cadeas.length; i++) { //O ultimo e un numero
                                    c.addComando(new ComandoAtacar(mapa, personaje, cadeas[0] + " " + cadeas[1] + " " + cadeas[2] + " " + cadeas[i]));
                                }
                                c.ejecutar();
                            }
                            else
                                new ComandoAtacar(mapa, personaje, sel).ejecutar();
                        }catch (EnemigoInexistenteException | ComandoException | InsuficienteEnergiaException | MoverException | EspacioMaximoException | SegmentationFaultException | FueraDeRangoException | PesoMaximoException e) {
                            consola.imprimir(e.getMessage());
                        }
                        break;
                    case "pasar":
                        personaje.pasar(mapa, personaje);
                        break;
                    case "terminar":
                        break;
                    case "descripcion":
                        consola.imprimir(mapa.getDescripcion());
                        break;
                    case "estado":
                        personaje.info();
                        break;
                    case "inventario":
                        personaje.ojearInventario();
                        break;
                    case "coger":
                        if (cadeas.length == 2) {
                            try {
                                new ComandoCoger(mapa, personaje, cadeas[1]).ejecutar();
                            }catch (ComandoException | PesoMaximoException | EspacioMaximoException | ExplosivosException e){
                                consola.imprimir(e.getMessage());
                            }
                        }
                        else{
                            ComandoCompuesto c = new ComandoCompuesto();
                            for (int i = 1; i < cadeas.length; i++) {
                                c.addComando(new ComandoCoger(mapa, personaje, cadeas[i]));
                            }
                            try {
                                c.ejecutar();
                            }
                            catch (FueraDeRangoException | SegmentationFaultException | InsuficienteEnergiaException | MoverException | EnemigoInexistenteException | ComandoException | PesoMaximoException | EspacioMaximoException e){
                                consola.imprimir(e.getMessage());
                            }
                        }
                        break;
                    case "tirar":
                        if (cadeas.length == 2) {
                            try {
                                new ComandoTirar(mapa, personaje, cadeas[1]).ejecutar();
                            } catch (SegmentationFaultException | ComandoException e ){
                                consola.imprimir(e.getMessage());
                            }
                        } else {
                            ComandoCompuesto c = new ComandoCompuesto();
                            for (int i = 1; i < cadeas.length; i++) {
                                c.addComando(new ComandoTirar(mapa, personaje, cadeas[i].trim()));
                            }
                            try {
                                c.ejecutar();
                            } catch (FueraDeRangoException | SegmentationFaultException | InsuficienteEnergiaException | MoverException | EnemigoInexistenteException | ComandoException | PesoMaximoException | EspacioMaximoException e) {
                                consola.imprimir(e.getMessage());
                            }
                        }
                        break;
                    case "desequipar":
                        if (cadeas.length == 2) {
                            switch (cadeas[1]) {
                                case "armadura":
                                    new ComandoDesequiparArmadura(mapa,personaje).ejecutar();
                                    break;
                                /*
                                case "binocular":
                                    personaje.desequiparBinocular();
                                    break;
                                    */
                                case "arma":
                                    try {
                                        new ComandoDesequiparArma(mapa, personaje, "").ejecutar();
                                    } catch (SegmentationFaultException | ComandoException e){
                                        consola.imprimir(e.getMessage());
                                    }
                                    break;
                                default:
                                    consola.imprimir("Comando no reconocido.");
                            }
                        }
                        if (cadeas.length == 3) {
                            if (cadeas[1].equals("arma"))
                                try {
                                    new ComandoDesequiparArma(mapa, personaje, cadeas[2]).ejecutar();
                                } catch (SegmentationFaultException | ComandoException e){
                                    consola.imprimir(e.getMessage());
                                }
                        }
                        break;
                    case "equipar":
                        if (cadeas.length == 2) {
                            try {
                                new ComandoEquipar(personaje, mapa, cadeas[1] , "").ejecutar(); //Por defecto la equipa como dos manos
                            }catch (ComandoException | ManosArmaException e){
                                consola.imprimir(e.getMessage());
                            }
                            //personaje.equiparBinocular(cadeas[1]);
                        } else if (cadeas.length == 3) {
                            try {
                                new ComandoEquipar(personaje, mapa, cadeas[1], cadeas[2]).ejecutar();
                            } catch (ComandoException | ManosArmaException e){
                                consola.imprimir(e.getMessage());
                            }
                        }
                        break;
                    case "usar":
                        if (cadeas.length == 2)
                            try {
                                new ComandoUsar(personaje,cadeas[1]).ejecutar();
                            } catch (ComandoException | ObjetoInexistenteMochilaException | ObjetoException e) {
                                consola.imprimirError("Error usando objeto : "+e.getMessage());
                            }
                        else
                            consola.imprimirError("Comando mal introducido, consulte la ayuda para saber mas.");
                        break;
                    case "cargar":
                        if (cadeas.length == 2) {
                            juego = new CargadorJuegoDeFicheros(cadeas[1] + "/mapa.csv", cadeas[1] + "/npcs.csv", cadeas[1] + "/objetos.csv").cargarJuego();
                            personaje = juego.getPersonaje();
                            mapa = juego.getMapa();
                        }
                        break;
                    default:
                        consola.imprimirError("La opción seleccionada no existe, seleccione ayuda para saber más");
                }
            } else
                consola.imprimirError("Debes introducir algún comando");
            /**
             * Paramos ejecucion 0.5s para evitar que imprima erros ou excepcions mentres imprime o mapa
             */
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } while (!sel.equals("terminar"));
    }
}