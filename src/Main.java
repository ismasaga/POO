import Excepciones.ComandoException;
import Excepciones.InsuficienteEnergiaException;
import Excepciones.MoverException;
import Juego.*;
import Personajes.Jugador;
import Personajes.Personaje;

public class Main {

    public static void main(String[] args) {
        String sel;
        String[] cadeas;
        Mapa mapa;
        Jugador personaje = null;
        ConsolaNormal consola = new ConsolaNormal();
        Parser parser = new Parser();
        Bundle bundle = new Bundle();
        bundle = parser.Parse("src/Juego/mapa.csv", "src/Juego/npcs.csv", "src/Juego/objetos.csv",consola);
        mapa = bundle.getMapa();
        personaje = bundle.getPersonaje();
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
                        System.out.println(ANSI_BLUE + "\t\tGame Player Manual\t\t\n" + ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "mover: " + ANSI_RESET + "mueve un personaje el numero de casillas adyacentes. Para seleccionar la direccion hay que usar d, l, r o u");
                        System.out.println("\tEjemplos:");
                        System.out.println(ANSI_CYAN + "\t\tmover 2r: mover dos posiciones a la derecha");
                        System.out.println(ANSI_CYAN + "\t\tmover 1d: mover una posicion abajo");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "mirar: " + ANSI_RESET + "mira la posicion designada. Puede listar todos los objetos de cualquier casilla visible o obtener detalles de un objeto de su casilla actual. ");
                        System.out.println("Si no consigue mirar obtiene la casilla actual.");
                        System.out.println("\tEjemplos:");
                        System.out.println(ANSI_CYAN + "\t\tmirar: lista los objetos de la casilla actual");
                        System.out.println(ANSI_CYAN + "\t\tmirar escopeta_1: obtiene detalles de la escopeta_1 de la casilla actual");
                        System.out.println(ANSI_CYAN + "\t\tmirar 2r,1d: mira la casilla dos posiciones a la derecha y una abajo de la actual.");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "atacar: " + ANSI_RESET + "ataca al personaje de la celda designada. Sigue el mismo patron que mirar");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "pasar: " + ANSI_RESET + "termina tu turno");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "terminar: " + ANSI_RESET + "termina una partida");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "descripcion: " + ANSI_RESET + "imprime la descripcion del mapa");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "estado: " + ANSI_RESET + "imprime toda la informacion disponible sobre el personaje");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "inventario: " + ANSI_RESET + "imprime los contenidos de la mochila");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "coger: " + ANSI_RESET + "coge el objeto de la casilla actual");
                        System.out.println("\tEjemplo:");
                        System.out.println(ANSI_CYAN + "\t\tcoger escopeta_1: coge la escopeta_1 de la casilla actual");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "tirar: " + ANSI_RESET + "tira el objeto designado de la mochila en la celda actual. Es como el comando coger");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "desequipar: " + ANSI_RESET + "desequipa un arma, armadura o binocular equipado");
                        System.out.println("\tEjemplo:");
                        System.out.println(ANSI_CYAN + "\t\tdesequipar armadura: desequipa la armadura");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "equipar: " + ANSI_RESET + "equipa un arma, armadura o binocular de la mochila");
                        System.out.println("\tEjemplo:");
                        System.out.println(ANSI_CYAN + "\t\tequipar armadura_25");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "usar: " + ANSI_RESET + "usa un botiquin de la mochila");
                        System.out.println(ANSI_RESET);
                        System.out.println(ANSI_PURPLE + "cargar: " + ANSI_RESET + "carga los archivos de la ruta especificada");
                        System.out.println(ANSI_RESET);
                        System.out.println("\tEjemplo:");
                        System.out.println(ANSI_CYAN + "\t\tcargar src/Juego/: carga los archivos de src/Juego/");
                        System.out.println(ANSI_RESET);
                        break;
                    case "mover":
                        try {
                            new ComandoMover(mapa, personaje, cadeas[1].charAt(0)).ejecutar();
                        } catch (ComandoException e) {
                            consola.imprimirError("Error de comando : "+e.getMessage());
                        } catch (ArrayIndexOutOfBoundsException e) {
                            consola.imprimirError("Error introduciendo el comando, mire la ayuda");
                            e.printStackTrace();
                        } catch (MoverException e) {
                            consola.imprimirError("Error moviendo : "+e.getMessage());
                        } catch (InsuficienteEnergiaException e) {
                            consola.imprimirError("Error de energia : "+e.getMessage());
                        }
                        break;
                    /*case "mirar":
                        //Obtiene informacion de la propia casilla
                        if (cadeas.length == 1) {
                            personaje.mirar(mapa, 0, 0, ' ', ' ', null);
                        }
                        //Mira
                        if (cadeas.length == 2 && cadeas[1].length() != 2) {
                            personaje.mirar(mapa, 0, 0, ' ', ' ', cadeas[1]);
                        }
                        //Obtiene informacion de una casilla lejana
                        if (cadeas.length == 3 && cadeas[1].length() == 2 && cadeas[2].length() == 2) {
                            personaje.mirar(mapa,
                                    Character.getNumericValue(cadeas[1].charAt(0)),
                                    Character.getNumericValue(cadeas[2].charAt(0)),
                                    cadeas[1].charAt(1),
                                    cadeas[2].charAt(1),
                                    null);
                        }
                        //Mira solo en una direccion una casilla lejana
                        if (cadeas.length == 2 && cadeas[1].length() == 2) {
                            if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l') {
                                personaje.mirar(mapa,
                                        Character.getNumericValue(cadeas[1].charAt(0)),
                                        0,
                                        cadeas[1].charAt(1),
                                        ' ',
                                        null);
                            }
                            if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd') {
                                personaje.mirar(mapa,
                                        0,
                                        Character.getNumericValue(cadeas[1].charAt(0)),
                                        ' ',
                                        cadeas[1].charAt(1),
                                        null);
                            }
                        }
                        //Se mira un objeto en una direccion
                        if (cadeas.length == 3 && cadeas[2].length() != 2) {
                            if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l') {
                                personaje.mirar(mapa,
                                        Character.getNumericValue(cadeas[1].charAt(0)),
                                        0,
                                        cadeas[1].charAt(1),
                                        ' ',
                                        cadeas[2]);
                            }
                            if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd') {
                                personaje.mirar(mapa,
                                        0,
                                        Character.getNumericValue(cadeas[1].charAt(0)),
                                        ' ',
                                        cadeas[1].charAt(1),
                                        cadeas[2]);
                            }
                        }
                        //Se mira un objeto en varias direcciones
                        if (cadeas.length == 4 && cadeas[1].length() == 2 && cadeas[2].length() == 2) {
                            personaje.mirar(mapa,
                                    Character.getNumericValue(cadeas[1].charAt(0)),
                                    Character.getNumericValue(cadeas[2].charAt(0)),
                                    cadeas[1].charAt(1),
                                    cadeas[2].charAt(1),
                                    cadeas[3]);
                        }
                        break;
                    case "atacar":
                        if (cadeas.length == 2 && cadeas[1].length() == 2)
                            if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l') {
                                personaje.atacar(mapa, Character.getNumericValue(cadeas[1].charAt(0)), 0, cadeas[1].charAt(1), 'q', null);
                            } else if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd') {
                                personaje.atacar(mapa, 0, Character.getNumericValue(cadeas[1].charAt(0)), 'q', cadeas[1].charAt(1), null);
                            } else
                                System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
                        else if (cadeas.length == 3 && cadeas[2].length() != 2 && cadeas[1].length() == 2) //Ataca de frente o a los lados.
                            if (cadeas[1].charAt(1) == 'r' || cadeas[1].charAt(1) == 'l') {
                                personaje.atacar(mapa, Character.getNumericValue(cadeas[1].charAt(0)), 0, cadeas[1].charAt(1), 'q', cadeas[2]);
                            } else if (cadeas[1].charAt(1) == 'u' || cadeas[1].charAt(1) == 'd') {
                                personaje.atacar(mapa, 0, Character.getNumericValue(cadeas[1].charAt(0)), 'q', cadeas[1].charAt(1), cadeas[2]);
                            } else
                                System.out.println("La opción seleccionada no existe, seleccione ayuda para saber más");
                        else if (cadeas.length == 3 && cadeas[1].length() == 2 && cadeas[1].length() == 2) //Quiere atacar en diagonal
                        {
                            personaje.atacar(mapa, Character.getNumericValue(cadeas[1].charAt(0)), Character.getNumericValue(cadeas[2].charAt(0))
                                    , cadeas[1].charAt(1), cadeas[2].charAt(1), null);
                        }
                        else if (cadeas.length == 4 && cadeas[1].length() == 2 && cadeas[1].length() == 2) //Quiere atacar en diagonal a un enemigo
                        {
                            personaje.atacar(mapa, Character.getNumericValue(cadeas[1].charAt(0)), Character.getNumericValue(cadeas[2].charAt(0))
                                    , cadeas[1].charAt(1), cadeas[2].charAt(1), cadeas[3]);
                        }
                        /*
                        if(cadeas.length == 3 && cadeas[1].length() == 1 && cadeas[2].length() == 1)
                            personaje.atacar(mapa, Integer.parseInt(cadeas[1]), Integer.parseInt(cadeas[2]),null);
                        else if(cadeas.length == 4  && cadeas[1].length() == 1 && cadeas[2].length() == 1)
                            personaje.atacar(mapa, Integer.parseInt(cadeas[1]), Integer.parseInt(cadeas[2]), cadeas[3]);
                            */
                        /*else
                            System.out.println("Formato de comando incorrecto, seleccione ayuda para saber mas");
                        break;
                    case "pasar":
                        personaje.pasar(mapa, personaje);
                        break;
                    case "terminar":
                        break;
                    case "descripcion":
                        System.out.println(mapa.getDescripcion());
                        break;
                    case "estado":
                        personaje.info();
                        break;
                    case "inventario":
                        personaje.ojearInventario();
                        break;
                    case "coger":
                        if (cadeas.length == 2) {
                            personaje.cogerArma(cadeas[1]);
                            personaje.cogerArmadura(cadeas[1]);
                            personaje.cogerBinocular(cadeas[1]);
                            personaje.cogerBotiquin(cadeas[1]);
                        } else
                            System.out.println("Formato del comando incorrecto, use ayuda para saber mas");
                        break;
                    case "tirar":
                        if (cadeas.length == 2) {
                            personaje.tirarArma(cadeas[1]);
                            personaje.tirarArmadura(cadeas[1]);
                            personaje.tirarBinocular(cadeas[1]);
                            personaje.tirarBotiquin(cadeas[1]);
                        } else
                            System.out.println("Formato del comando incorrecto, use ayuda para saber mas");
                        break;
                    case "desequipar":
                        if (cadeas.length == 2) {
                            switch (cadeas[1]) {
                                case "armadura":
                                    personaje.desequiparArmadura();
                                    break;
                                case "binocular":
                                    personaje.desequiparBinocular();
                                    break;
                                case "arma":
                                    personaje.desequiparArma("");
                                    break;
                                default:
                                    System.out.println("Comando no reconocido.");
                            }
                        }
                        if (cadeas.length == 3) {
                            if (cadeas[1].equals("arma"))
                                personaje.desequiparArma(cadeas[2]);
                        }
                        break;
                    case "equipar":
                        if (cadeas.length == 2) {
                            personaje.equiparArma(cadeas[1], "derecha"); //Por defecto, si es de una mano, la equipa en la derecha
                            personaje.equiparArmadura(cadeas[1]);
                            personaje.equiparBinocular(cadeas[1]);
                        } else if (cadeas.length == 3) {
                            personaje.equiparArma(cadeas[1], cadeas[2]);
                        }
                        break;
                    case "usar":
                        if (cadeas.length == 2)
                            personaje.usarBotiquin(cadeas[1]);
                        break;
                    case "cargar":
                        if (cadeas.length == 2) {
                            bundle = parser.Parse(cadeas[1] + "/mapa.csv", cadeas[1] + "/npcs.csv", cadeas[1] + "/objetos.csv");
                            personaje = bundle.getPersonaje();
                            mapa = bundle.getMapa();
                        }
                        break;*/
                    default:
                        consola.imprimirError("La opción seleccionada no existe, seleccione ayuda para saber más");
                }
            } else
                consola.imprimirError("Debes introducir algún comando");
        } while (!sel.equals("terminar"));
    }
}