package sopadeletras;
import java.util.Scanner;

public class SopaDeLetrasSinArrayList {
    private static final String[] palabras = new String[25];
    private static int palabraCount = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static char[][] juego;
    private static final String[] nombresJug = new String[25];
    private static final String[] carneJug = new String[25];
    private static final int[] puntosTotales = new int[25];
    private static final int[] erroresTotales = new int[25];
    private static final int[] palabrasPartidas = new int[25];
    private static int jugadorCount = 0;

    public static void main(String[] args) {
        try (scanner) {
            int opcion;
            do {
                System.out.println("\n --- MENÚ ---");
                System.out.println("1. Ingreso y modificación de palabras");
                System.out.println("2. Jugar");
                System.out.println("3. Mostrar historial de la partida");
                System.out.println("4. Puntuaciones más altas");
                System.out.println("5. Información del estudiante");
                System.out.println("6. No deseo hacer más");
                System.out.println("\n Elija su opción: ");
                
                while(!scanner.hasNextInt()){
                    System.out.println("Valor no esperado, ingrese un entero de 1 a 6 según le convenga");
                    scanner.next();
                }
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch(opcion){
                    case 1 -> {
                        int opcion2;
                        do {
                            System.out.println("\n ---Seleccione una opción: ---");
                            System.out.println("1. Insertar palabras");
                            System.out.println("2. Modificar palabras");
                            System.out.println("3. Eliminar palabras");
                            System.out.println("4. Mostrar las palabras ingresadas");
                            System.out.println("5. Volver al menú");
                            System.out.print("\n Elija su opción: ");
                            
                            while(!scanner.hasNextInt()){
                                System.out.println("Valor no esperado, ingrese un entero de 1 a 5 según le convenga");
                                scanner.next();
                            }
                                opcion2 = scanner.nextInt();
                                scanner.nextLine();
                            
                            switch(opcion2){
                                case 1 -> {
                                    agregarPalabra();
                                    System.out.println("Ingrese su palabra");
                                }
                                case 2 -> {
                                    System.out.println("Seleccione la palabra a cambiar");
                                    cambiarPalabra();
                                }
                                case 3 -> {
                                    System.out.println("Seleccione la palabra a eliminar");
                                    quitarPalabra();
                                }
                                case 4 -> {
                                    System.out.println("Mostrar las palabras ingresadas");
                                    verPalabras();
                                }
                                case 5 -> {
                                    System.out.println("Regresar al menú principal");
                                }
                                default -> {
                                    System.out.println("Opción no esperada, ingrese una opción entre 1 a 5 por favor");
                                }
                            }
                        } while (opcion2 != 5);
                    }
                    case 2 -> {
                        System.out.println("\n INICIANDO JUEGO...");
                        iniciarJuego();
                    }
                    case 3 -> {
                        System.out.println("Mostrar Historial: ");
                        verHistorial();
                    }
                    case 4 -> {
                        System.out.println("Puntuaciones: ");
                        mejoresPuntos();
                    }
                    case 5 -> {
                        System.out.println("\n --- ESTUDIANTE --- ");
                        System.out.println("Nombre: Angel Raúl Herrera Chilel");
                        System.out.println("Carnet: 202402587");
                        System.out.println("Sección: B");
                    }
                    case 6 -> {
                        System.out.println("Terminar la Partida");
                        System.exit(0);
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } while(opcion != 6);
        }
    }
    private static void agregarPalabra(){
        System.out.println("Ingrese el número de palabras: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidad; i++) {
            System.out.println("Ingrese su palabra " + (i + 1) + ":");
            String palabra = scanner.nextLine();

            if (palabra.length() >= 3 && palabra.length() <= 8) {
                palabras[palabraCount++] = palabra.toUpperCase();
            } else {
                System.out.println("La palabra debe contener de 3 a 8 caracteres");
                i--;
            }
        }
    }
    private static void cambiarPalabra(){
        System.out.println("Ingrese la palabra a modificar: ");
        String palabraModificar = scanner.nextLine().toUpperCase();

        int index = buscarPalabra(palabraModificar);

        if (index != -1) {
            System.out.println("Ingrese la nueva palabra: ");
            String nuevaPalabra = scanner.nextLine().toUpperCase();

            if (nuevaPalabra.length() >= 3 && nuevaPalabra.length() <= 8) {
                palabras[index] = nuevaPalabra;
                System.out.println("Ha cambiado la palabra: " + palabraModificar + "; por: " + nuevaPalabra);
            } else {
                System.out.println("La palabra debe contener de 3 a 8 caracteres");
            }
        } else {
            System.out.println("El dato no existe");
        }
    }
    private static int buscarPalabra(String palabra){
        for (int i = 0; i < palabraCount; i++) {
            if (palabras[i].equals(palabra)) {
                return i;
            }
        }
        return -1;
    }
    private static void quitarPalabra(){
        System.out.println("Ingrese la palabra a eliminar: ");
        String palabraEliminar = scanner.nextLine().toUpperCase();

        int index = buscarPalabra(palabraEliminar);

        if (index != -1) {
            for (int i = index; i < palabraCount - 1; i++) {
                palabras[i] = palabras[i + 1];
            }
            palabras[--palabraCount] = null;
            System.out.println("La palabra " + palabraEliminar + " ha sido eliminada");
        } else {
            System.out.println("La palabra no se encuentra en la lista");
        }
    }
    private static void verPalabras(){
        if (palabraCount == 0) {
            System.out.println("No hay datos");
        } else {
            for (int i = 0; i < palabraCount; i++) {
                System.out.println(palabras[i]);
            }
        }
    }
    private static void iniciarJuego(){
        if (palabraCount == 0) {
            System.out.println("No hay datos para iniciar");
            return;
        }
        System.out.println("Introduzca su nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduzca su carné: ");
        String carne = scanner.nextLine();

        juego = new char[15][15];
        crearTablero();
        System.out.println("\n --- Tablero ---");
        verMatriz();

        int puntos = 25;
        int errores = 0;
        int palabrasEncontradas = 0;

        String[] palabrasPendientes = new String[palabraCount];
        System.arraycopy(palabras, 0, palabrasPendientes, 0, palabraCount);

        while (errores < 4 && palabrasEncontradas < palabraCount) {
            System.out.println("Ingrese una palabra: ");
            String palabra = scanner.nextLine().toUpperCase();
            int index = bPP(palabrasPendientes, palabra);
            if (index != -1) {
                palabrasEncontradas++;
                palabrasPendientes[index] = null;
                puntos += palabra.length();
                marcarPalabra(palabra);
                System.out.println("¡Palabra Encontrada!");
            } else {
                errores++;
                puntos -= 5;
                System.out.println("Palabra no encontrada, te quedan " + (4 - errores) + " intentos");
            }
            System.out.println("\n --- TABLERO ---");
            verMatriz();
            System.out.println("puntos: " + puntos);
            System.out.println("palabras encontradas: " + palabrasEncontradas);
            System.out.println("palabras pendientes: " + (palabraCount - palabrasEncontradas));
        }

        if (palabrasEncontradas == palabraCount) {
            System.out.println("Felicitaciones, ¡has ganado!");
        } else {
            System.out.println("Has Perdido");
        }
        System.out.println("Puntos Finales: " + puntos);
        registrarJugador(nombre, carne, puntos, errores, palabrasEncontradas);
    }
    private static int bPP(String[] palabrasPendientes, String palabra){
        for (int i = 0; i < palabrasPendientes.length; i++) {
            if (palabra.equals(palabrasPendientes[i])) {
                return i;
            }
        }
        return -1;
    }
    private static void crearTablero() {
    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 15; j++) {
            juego[i][j] = (char) ('A' + Math.random() * 26);
        }
    }
    for (int i = 0; i < palabraCount; i++) {
        String palabra = palabras[i];
        if (palabra != null) {
            boolean posicion = false;
            while (!posicion) {
                int fila = (int) (Math.random() * 15);
                int columna = (int) (Math.random() * 15);
                int direccion = (int) (Math.random() * 2);

                if (direccion == 0 && columna + palabra.length() <= 15) {
                    for (int j = 0; j < palabra.length(); j++) {
                        juego[fila][columna + j] = palabra.charAt(j);
                    }
                    posicion = true;
                } else if (direccion == 1 && fila + palabra.length() <= 15) {
                    for (int j = 0; j < palabra.length(); j++) {
                        juego[fila + j][columna] = palabra.charAt(j);
                    }
                    posicion = true;
                }
            }
        }
    }
}
private static void verMatriz() {
    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 15; j++) {
            System.out.print(juego[i][j] + " | ");
        }
        System.out.println();
    }
}
private static void marcarPalabra(String palabra) {
    for (int i = 0; i < 15; i++) {
        for (int j = 0; j < 15; j++) {
            if (juego[i][j] == palabra.charAt(0)) {
                boolean palloc = true;
                for (int a = 0; a < palabra.length(); a++) {
                    if (j + a >= 15 || juego[i][j + a] != palabra.charAt(a)) {
                        palloc = false;
                        break;
                    }
                }
                if (palloc) {
                    for (int a = 0; a < palabra.length(); a++) {
                        juego[i][j + a] = '*';
                    }
                    return;
                }
                palloc = true;
                for (int a = 0; a < palabra.length(); a++) {
                    if (i + a >= 15 || juego[i + a][j] != palabra.charAt(a)) {
                        palloc = false;
                        break;
                    }
                }
                if (palloc) {
                    for (int a = 0; a < palabra.length(); a++) {
                        juego[i + a][j] = '*';
                    }
                    return;
                }
            }
        }
    }
}
private static void verHistorial() {
    if (jugadorCount == 0) {
        System.out.println("No hay jugadores");
    } else {
        for (int i = 0; i < jugadorCount; i++) {
            System.out.println("Nombre: " + nombresJug[i]);
            System.out.println("Carné: " + carneJug[i]);
            System.out.println("Puntos: " + puntosTotales[i]);
            System.out.println("Fallos: " + erroresTotales[i]);
            System.out.println("Palabras Encontradas: " + palabrasPartidas[i]);
        }
    }
}
private static void mejoresPuntos() {
    if (jugadorCount == 0) {
        System.out.println("No hay jugadores");
    } else {
        int[] indices = new int[jugadorCount];
        for (int i = 0; i < jugadorCount; i++) {
            indices[i] = i;
        }
        for (int i = 0; i < jugadorCount - 1; i++) {
            for (int j = i + 1; j < jugadorCount; j++) {
                if (puntosTotales[indices[j]] > puntosTotales[indices[i]]) {
                    int temp = indices[i];
                    indices[i] = indices[j];
                    indices[j] = temp;
                }
            }
        }
        int limite = Math.min(3, jugadorCount);
        for (int i = 0; i < limite; i++) {
            int indice = indices[i];
            System.out.println("Nombre del jugador: " + nombresJug[indice]);
            System.out.println("Carné del jugador: " + carneJug[indice]);
            System.out.println("La puntuación es: " + puntosTotales[indice]);
        }
    }
}
private static void registrarJugador(String nombre, String carne, int puntos, int errores, int palabrasEncontradas) {
    if (jugadorCount < 25) {
        nombresJug[jugadorCount] = nombre;
        carneJug[jugadorCount] = carne;
        puntosTotales[jugadorCount] = puntos;
        erroresTotales[jugadorCount] = errores;
        palabrasPartidas[jugadorCount] = palabrasEncontradas;
        jugadorCount++;
    } else {
        System.out.println("No se pueden registrar más jugadores.");
    }
}
}