package main;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Login {
    // Mapa para almacenar usuarios: clave = cédula, valor = arreglo con datos
    private static HashMap<String, String[]> usuariosRegistrados = new HashMap<>();
    // Nombre del archivo donde se guardarán los usuarios
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarUsuarios(); // Cargar usuarios al iniciar el programa
        
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Iniciar sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Recuperar contraseña"); // Nueva opción
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    iniciarSesion();
                    break;
                case "2":
                    registrarse();
                    break;
                case "3":
                    recuperarContrasena();
                    break;
                case "4":
                    guardarUsuarios(); // Guardar antes de salir
                    System.out.println("¡Hasta pronto!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    // ========== FUNCIONES DE VALIDACIÓN ==========
    
    /**
     * Valida si una cédula tiene formato correcto (8-10 dígitos)
     * @param cedula Cédula a validar
     * @return true si es válida, false si no
     */
    private static boolean esCedulaValida(String cedula) {
        return Pattern.matches("^\\d{8,10}$", cedula);
    }

    // ========== FUNCIONES DE PERSISTENCIA ==========
    
    /**
     * Carga usuarios desde el archivo al iniciar el programa
     */
    private static void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Formato: cedula:password:idAdmin:rol:pregunta:respuesta
                String[] datos = linea.split(":");
                usuariosRegistrados.put(datos[0], new String[]{
                    datos[1], // password
                    datos[2], // idAdmin
                    datos[3], // rol
                    datos[4], // pregunta
                    datos[5]  // respuesta
                });
            }
        } catch (IOException e) {
            System.out.println("Creando nueva base de datos...");
        }
    }
    
    /**
     * Guarda todos los usuarios en el archivo
     */
    private static void guardarUsuarios() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_USUARIOS))) {
            usuariosRegistrados.forEach((cedula, datos) -> {
                // Formato: cedula:password:idAdmin:rol:pregunta:respuesta
                pw.println(String.join(":", 
                    cedula,
                    datos[0], // password
                    datos[1], // idAdmin
                    datos[2], // rol
                    datos[3], // pregunta
                    datos[4]  // respuesta
                ));
            });
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    // ========== FUNCIONES PRINCIPALES ==========
    
    private static void iniciarSesion() {
        System.out.println("\n=== INICIO DE SESION ===");
        
        // Pedir cédula con validación
        System.out.print("Usuario: ");
        String cedula = scanner.nextLine();
        
        if (!usuariosRegistrados.containsKey(cedula)) {
            System.out.println("Usuario no encontrado");
            return;
        }

        String[] datos = usuariosRegistrados.get(cedula);
        
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        if (!password.equals(datos[0])) {
            System.out.println("Contraseña incorrecta");
            return;
        }

        System.out.println("\nSeleccione rol:");
        System.out.println("1. Usuario normal");
        System.out.println("2. Administrador");
        System.out.print("Opcion: ");
        String rol = scanner.nextLine();

        if (rol.equals("1") && datos[2].equals("usuario")) {
            System.out.println("Bienvenido usuario " + cedula);
        } else if (rol.equals("2") && datos[2].equals("admin")) {
            System.out.print("ID de Admin: ");
            String id = scanner.nextLine();
            if (id.equals(datos[1])) {
                System.out.println("Bienvenido administrador " + cedula);
            } else {
                System.out.println("ID de administrador incorrecto");
            }
        } else {
            System.out.println("No tiene permisos para este rol");
        }
    }

    private static void registrarse() {
        System.out.println("\n=== REGISTRO ===");
        
        // Validar cédula
        String cedula;
        do {
            System.out.print("Usuario: ");
            cedula = scanner.nextLine();
            if (!esCedulaValida(cedula)) {
                System.out.println("Formato invalido. Ejemplo: 28455876");
            }
        } while (!esCedulaValida(cedula));

        if (usuariosRegistrados.containsKey(cedula)) {
            System.out.println("Esta cedula ya esta registrada");
            return;
        }

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        
        // Pregunta secreta
        System.out.print("Pregunta secreta (ej: ¿Color favorito?): ");
        String pregunta = scanner.nextLine();
        
        System.out.print("Respuesta secreta: ");
        String respuesta = scanner.nextLine();

        System.out.println("Tipo de cuenta:");
        System.out.println("1. Usuario normal");
        System.out.println("2. Administrador");
        System.out.print("Opcion: ");
        String tipo = scanner.nextLine();

        if (tipo.equals("1")) {
            usuariosRegistrados.put(cedula, new String[]{
                password, // [0] contraseña
                "",       // [1] idAdmin (vacío para usuarios normales)
                "usuario",// [2] rol
                pregunta, // [3] pregunta secreta
                respuesta // [4] respuesta secreta
            });
            System.out.println("Usuario registrado exitosamente");
        } else if (tipo.equals("2")) {
            System.out.print("ID de administrador: ");
            String id = scanner.nextLine();
            usuariosRegistrados.put(cedula, new String[]{
                password, // [0] contraseña
                id,       // [1] idAdmin
                "admin",  // [2] rol
                pregunta, // [3] pregunta secreta
                respuesta // [4] respuesta secreta
            });
            System.out.println("Administrador registrado exitosamente");
        } else {
            System.out.println("Opcion no valida");
        }
    }

    private static void recuperarContrasena() {
        System.out.println("\n=== RECUPERAR CONTRASEÑA ===");
        System.out.print("Ingrese su usuario: ");
        String cedula = scanner.nextLine();
        
        if (!usuariosRegistrados.containsKey(cedula)) {
            System.out.println("Usuario no registrado");
            return;
        }

        String[] datos = usuariosRegistrados.get(cedula);
        System.out.println("Pregunta secreta: " + datos[3]);
        System.out.print("Respuesta: ");
        String respuesta = scanner.nextLine();

        if (respuesta.equals(datos[4])) {
            System.out.print("Nueva contraseña: ");
            String nuevaPassword = scanner.nextLine();
            datos[0] = nuevaPassword;
            usuariosRegistrados.put(cedula, datos);
            System.out.println("Contraseña actualizada correctamente");
        } else {
            System.out.println("Respuesta incorrecta");
        }
    }
}
