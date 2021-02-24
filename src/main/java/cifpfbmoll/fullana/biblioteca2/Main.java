/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Jaume
 */
public class Main {
    
    public static Scanner sc=new Scanner(System.in);
    /**
     * metodo main del programa, en el se incluyen bucles y switch que llaman a los diferentes modulos de las 
     * diferentes clases del programa, ademas de la creacion de la biblioteca en el principio del programa.
     */
    public static void main(String[] args) {
        RedBiblioteca redBibliotecas=new RedBiblioteca();
        System.out.println("Bienvenido al programa de gestion de bibliotecas.");
        System.out.println("Creemos la primera biblioteca");
        Biblioteca biblioteca=Biblioteca.añadirBiblioteca(redBibliotecas.getListaBibliotecas());
        crearPrimerAdministrador(biblioteca);
        int privilegios=1;
        int opcionSesion;
        String [] listaInicioSesion1;
        int [] listaInicioSesion2;
        int tlfUsuarioActivo=0;
        String nifBibliotecarioActivo=((Bibliotecario)biblioteca.getListaPersona().get(0)).getNif();
        boolean salir=false;
        //bucle para poder realizar acciones hasta que se desee
        while (!salir) {
            while (privilegios!=0){
                if (privilegios==1){
                    privilegios = menuAdministrador(privilegios, biblioteca, nifBibliotecarioActivo);
                }
                else if (privilegios==2){
                    privilegios = menuUsuario(privilegios, biblioteca, tlfUsuarioActivo);
                }
                //Opciones de inicio de sesion o para salir del programa
                opcionSesion = opcionesInicioSesion(biblioteca);
                //inicio de sesion de administrador
                if (opcionSesion==1){
                    listaInicioSesion1 = menuInicioSesionAdministrador(biblioteca, privilegios, nifBibliotecarioActivo);
                    privilegios=Integer.parseInt(listaInicioSesion1[0]);
                    nifBibliotecarioActivo=listaInicioSesion1[1];
                } 
                //inicio de sesion de usuario
                else if(opcionSesion==2){
                    listaInicioSesion2 = menuInicioSesionUsuario(biblioteca, privilegios, tlfUsuarioActivo);
                    privilegios=listaInicioSesion2[0];
                    tlfUsuarioActivo=listaInicioSesion2[1];

                } 
                else if (opcionSesion==0){
                    System.out.println("ADIOS!");
                    privilegios=0;
                } 
                else{
                    System.out.println("ERROR: Esta opcion no existe");
                    privilegios=9;
                }
            }
            System.out.println("MENU DE GESTION DE LAS BIBLIOTECAS");
            System.out.println("Pulsa 1 para crear una nueva biblioteca");
            System.out.println("Pulsa 2 para eliminar una biblioteca");
            System.out.println("Pulsa 3 para ver todas las bibliotecas");
            System.out.println("Pulsa 4 para entrar al gestor de una de las bibliotecas");
            System.out.println("Pulsa 5 para buscar informacion de un libro en todas las bibliotecas");
            System.out.println("Pulsa 0 para salir del programa");
            int opcionBiblioteca=sc.nextInt();
            sc.nextLine();
            switch (opcionBiblioteca){
                
                case 1:
                    Biblioteca nuevaBiblioteca=Biblioteca.añadirBiblioteca(redBibliotecas.getListaBibliotecas());
                    crearPrimerAdministrador(nuevaBiblioteca);
                    break;
                    
                case 2:
                    Biblioteca.eliminarBiblioteca(redBibliotecas.getListaBibliotecas());
                    break;
                
                case 3:
                    Biblioteca.consultarBibliotecas(redBibliotecas.getListaBibliotecas());
                    break;
                    
                case 4:
                    System.out.println("Dime el nombre de la biblioteca a la que quieres entrar");
                    String nombre=sc.nextLine();
                    boolean encontrado=false;
                    int i=0;
                    while (i<redBibliotecas.getListaBibliotecas().size() && !encontrado){
                        if (redBibliotecas.getListaBibliotecas().get(i).getNombreBiblioteca().equals(nombre)){
                            encontrado=true;
                            biblioteca=redBibliotecas.getListaBibliotecas().get(i);
                            privilegios=9;
                        }
                        i++;
                    }
                    if (!encontrado){
                        System.out.println("No existe ninguna biblioteca con ese nombre en nuesta red");
                    }
                    break;
                    
                case 5:
                    redBibliotecas.buscarLibroBibliotecas();
                    break;
                    
                case 0:
                    System.out.println("ADIOS!");
                    salir=true;
                    break;
                    
                default:
                    System.out.println("ERROR: Este comando no existe");
                    System.out.println("Inserta un comando que exista");
                    System.out.println("");
                    
            }
            System.out.println("Pulsa enter para continuar");
            sc.nextLine();
        }
    }
    /**
     * Metodo que pregunta la opcion de inicio de sesion que desea realizar el 
     * usuario, esa opcion es un int que es devuelto por el metodo.
     * 
     * @return opcionSesion int que indica la opcion que ha elegido el usuario para iniciar sesion
     */
    public static int opcionesInicioSesion(Biblioteca biblioteca) {
        int opcionSesion;
        System.out.println("MENU DE INICIO DE SESION DE LA BIBLIOTECA "+biblioteca.getNombreBiblioteca());
        System.out.println("Inserta 1 para inciar sesion si eres parte del personal");
        System.out.println("Inserta 2 para iniciar sesion si eres un usuario de la biblioteca");
        System.out.println("Inserta 0 para salir del gestor de esta biblioteca");
        opcionSesion=sc.nextInt();
        sc.nextLine();
        return opcionSesion;
    }
    /**
     * Metodo que se ejecuta siempre cuando se crea una biblioteca, sirve para crear el primer administrador de la biblioteca
     * (Accion que es llevada a cabo a traves de otro metodo el cual es llamado en este metodo)
     * 
     * @param biblioteca Objeto de la clase biblioteca del cual se coge el atributo ArrayList listaPersonal 
     * para guardar al primer administrador(Persona) de la biblioteca.
     */
    public static void crearPrimerAdministrador(Biblioteca biblioteca) {
        System.out.println("Ahora pasemos a crear un usuario para gestionar esta biblioteca");
        crearBibliotecario(biblioteca);
        System.out.println("Bienvenido "+biblioteca.getListaPersona().get(0).getNombre()+"!");
    }
    /**
     * Metodo de un menu de inicio de sesion para usuarios normales, recibe por parametros un int privilegios para despues ser devuelto
     * con el valor correspondiente, un int tlfUsuarioActivo que despues sera devuelto tambien con el numero del usuario que haya logeado y
     * un objeto Biblioteca del cual se usa el atributo listaPersona, que es un ArrayList de Persona, para ver si existe 
     * un Usuario con ese telefono y luego comprobar si el correo introducido es el correcto(una vez comprobado,
     * y si es correcto, devuelve 1, si es incorrecto se sigue ejecutando hasta que el usuario quiere, cuando no quiere, si sigue
     * siendo incorrecto, devuelve 9).
     * 
     * @param biblioteca Objeto de la clase Biblioteca.
     * @param privilegios int que hace referencia a que puede hacer el usuario conectado(administrador o usuario normal).
     * @param tlfUsuarioActivo int que es el telefono del usuario que esta en la sesion actualmente.
     * @return listaInicioSesion Array creada para poder almacenar y devolver el int privilegios y el int tlfUsuarioActivo.
     */
    public static int[] menuInicioSesionUsuario(Biblioteca biblioteca, int privilegios, int tlfUsuarioActivo){
        System.out.println("Inserta tu telefono");
        int telefono=sc.nextInt();
        sc.nextLine();
        System.out.println("Inserta tu correo electronico");
        String correo=sc.nextLine();
        int i=0;
        boolean encontrado=false;
        while (i<biblioteca.getListaPersona().size() && !encontrado){
            if (biblioteca.getListaPersona().get(i) instanceof Usuario){
                if(((Usuario)biblioteca.getListaPersona().get(i)).getTelefono()==telefono){
                    encontrado=true;
                    boolean salir=false;
                    while (!(((Usuario)biblioteca.getListaPersona().get(i)).getCorreoElectronico().equals(correo)) && !salir){
                        System.out.println("El correo no coincide con el usuario que tiene ese telefono, inserte el correo correcto.");
                        System.out.println("Si quieres salir del inicio de sesion apriete enter");
                        correo=sc.nextLine();
                        if (correo.equals("")){
                            salir=true;
                            privilegios=9;
                        }

                    }
                    if(((Usuario)biblioteca.getListaPersona().get(i)).getTelefono()==telefono && (((Usuario)biblioteca.getListaPersona().get(i)).getCorreoElectronico().equals(correo))){
                        privilegios=2;
                        System.out.println("Bienvenido "+biblioteca.getListaPersona().get(i).getNombre());
                        tlfUsuarioActivo=((Usuario)biblioteca.getListaPersona().get(i)).getTelefono();
                    }
                }
            }
            i++;
        }
        if (!encontrado){
            System.out.println("No hay ningun usuario con ese telefono");
        }
        int[] listaInicioSesion={privilegios,tlfUsuarioActivo};
        return listaInicioSesion;
    }
    
    /**
     * Metodo de un menu de inicio de sesion para administradores, recibe por parametros un int privilegios para despues ser devuelto,
     * un String nifBibliotecarioActivo para devolver el nif del bibliotecario que logea y un objeto Biblioteca del cual se usa 
     * el atributo listaPersona, que es un ArrayList de Personas, para ver si existe un bibliotecario(Persona ) con ese NIF y 
     * luego comprobar si la contraseña introducida es la correcta(una vez comprobado, y si es correcto, devuelve 1, si es incorrecto 
     * se sigue ejecutando hasta que el usuario quiere, cuando no quiere, si sigue siendo incorrecto, devuelve 9).
     * 
     * @param biblioteca Objeto de la clase Biblioteca.
     * @param privilegios int que hace referencia a que puede hacer el usuario conectado(administrador o usuario normal).
     * @param nifBibliotecarioActivo String que servira para alamacenar el NIF del bibliotecario que entre a la sesion.
     * @return listaInicioSesion Array creada para poder almacenar y devolver el int privilegios y el int nifBibliotecarioActivo.
     */
    public static String [] menuInicioSesionAdministrador(Biblioteca biblioteca, int privilegios, String nifBibliotecarioActivo) {
        System.out.println("Inserta tu NIF");
        String NIF=sc.nextLine();
        System.out.println("Inserta tu contraseña");
        String contrasena=sc.nextLine();
        int i=0;
        boolean encontrado=false;
        while (i<biblioteca.getListaPersona().size() && !encontrado){
            if(biblioteca.getListaPersona().get(i) instanceof Bibliotecario){
                if(((Bibliotecario)biblioteca.getListaPersona().get(i)).getNif().equals(NIF)){
                    encontrado=true;
                    boolean salir=false;
                    while (!((Bibliotecario)biblioteca.getListaPersona().get(i)).getContrasena().equals(contrasena) && !salir){
                        System.out.println("La contraseña es incorrecta, inserta la contraseña correcta."); 
                        System.out.println("Si quieres salir del inicio de sesion apriete enter");
                        contrasena=sc.nextLine();
                        if (contrasena.equals("")){
                            salir=true;
                            privilegios=9;
                        }

                    }
                    if(((Bibliotecario)biblioteca.getListaPersona().get(i)).getNif().equals(NIF) && ((Bibliotecario)biblioteca.getListaPersona().get(i)).getContrasena().equals(contrasena)){
                        privilegios=1;
                        nifBibliotecarioActivo=NIF;
                        System.out.println("Bienvenido "+biblioteca.getListaPersona().get(i).getNombre());
                    }
                }
            }
            i++;
        }
        if (!encontrado){
            System.out.println("No hay ningun bibliotecario con ese NIF");
        }
        String privilegiosString=Integer.toString(privilegios);
        String[] listaInicioSesion={privilegiosString,nifBibliotecarioActivo};
        return listaInicioSesion;
    }
    /**
     * Metodo de un menu de las diferentes opciones de un Usuario de la biblioteca, recibe por parametro un int
     * privilegios para poder entrar en el bucle, el objeto biblioteca de la clase Biblioteca para poder entrar
     * en su instancia con los diferentes metodos y el int tlfUsuarioActivo para poder saber que usuario es
     * el que esta en la sesion (Devuelve un int privilegios).
     * 
     * @param privilegios int que hace referencia a que puede hacer el usuario conectado(administrador o usuario normal).
     * @param biblioteca Objeto de la clase Biblioteca.
     * @param tlfUsuarioActivo int que es el telefono del usuario que esta en la sesion actualmente.
     * @return privilegios int que hace referencia a que puede hacer el usuario conectado(administrador o usuario normal).
     */
    public static int menuUsuario(int privilegios, Biblioteca biblioteca, int tlfUsuarioActivo) {
        int opcionGestor;
        //while de interfaz de usuario, gestiona Libros
        while (privilegios==2){
            System.out.println("Que desea hacer?");
            System.out.println("Pulsa 1 para buscar un libro por su ISBN");
            System.out.println("Pulsa 2 para buscar un libro por su titulo");
            System.out.println("Pulsa 3 para mostrar todos los libros");
            System.out.println("Pulsa 4 para mostrar todos los libros disponibles en este momento");
            System.out.println("Pulsa 5 para que se muestren los libros que tienes reservados");
            System.out.println("Pulsa 6 para cambiar tu contraseña(Correo Electronico)");
            System.out.println("Pulsa 7 para salir de la sesion");
            opcionGestor=sc.nextInt();
            sc.nextLine();
            
            switch (opcionGestor){
                case 1:
                    int posicion=Libro.buscarLibroISBN(biblioteca.getListaLibros());
                    /** interpreta el valor que se devuelve del metodo, siendo este
                     * la posicion de la lista en la que se encuentra el Libro o siendo -1
                     * si no se encuentra en ella.
                     */
                    if (posicion==-1){
                        System.out.println("No tenemos ningun libro con esa ISBN");
                    }
                    else {
                        System.out.println("El libro con esa ISBN se encuentra en la posicion numero "+posicion);
                        System.out.println("y su titulo es: "+biblioteca.getListaLibros().get(posicion).getTitulo());
                    }
                    break;
                    
                case 2:
                    Libro.buscarLibroTitulo(biblioteca.getListaLibros());
                    break;
                    
                case 3:
                    biblioteca.mostrarLibros();
                    break;
                    
                case 4:
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                    
                case 5:
                    int i=0;
                    boolean encontrado=false;
                    while (i<biblioteca.getListaPersona().size() && !encontrado){
                        if(biblioteca.getListaPersona().get(i) instanceof Usuario){
                            if (((Usuario)biblioteca.getListaPersona().get(i)).getTelefono()==tlfUsuarioActivo){
                                ((Usuario)biblioteca.getListaPersona().get(i)).mostrarReservas();
                                encontrado=true;
                            }
                        }
                        i++;
                    }
                    
                    break;
                case 6:
                    i=0;
                    encontrado=false;
                    while (i<biblioteca.getListaPersona().size() && !encontrado){
                        if(biblioteca.getListaPersona().get(i) instanceof Usuario){
                            if (((Usuario)biblioteca.getListaPersona().get(i)).getTelefono()==tlfUsuarioActivo){
                                ((Usuario)biblioteca.getListaPersona().get(i)).cambiarContraseña();
                                encontrado=true;
                            }
                        }
                        i++;
                    }
                    break;
                    
                case 7:
                    System.out.println("Hasta la proxima!");
                    privilegios=9;
                    break;
                    
                default:
                    System.out.println("ERROR: Este comando no existe");
                    System.out.println("Inserta un comando que exista");
            }
            System.out.println("Pulsa enter para continuar");
            sc.nextLine();
        }
        return privilegios;
    }
    /**
     * Metodo de un menu para administradores para entrar a los diferentes gestores de la biblioteca, recibe por parametro un int
     * privilegios para poder entrar en el bucle, el objeto biblioteca de la clase Biblioteca para poder poder pasarlo
     * por parametro a otras funciones y el String nifBibliotecarioActivo para tambien usarlo en otros metodos dentro de este
     * (El metodo devuelve un int privilegios).
     * 
     * @param privilegios int que hace referencia a que puede hacer el usuario conectado(administrador o usuario normal).
     * @param biblioteca Objeto de la clase Biblioteca.
     * @param nifBibliotecarioActivo String donde se ha guardado el nif del bibliotecario que esta logeado actualmente.
     * @return privilegios int que hace referencia a que puede hacer el usuario conectado(administrador o usuario normal).
     */
    public static int menuAdministrador(int privilegios, Biblioteca biblioteca, String nifBibliotecarioActivo) {
        int opcion;
        //while de interfaz de empleado
        while (privilegios==1){
            System.out.println("El programa se divide en dos gestores, el de libros y el de personas");
            System.out.println("Pulsa 1 si deseas acceder al gestor de libros");
            System.out.println("Pulsa 2 si deseas acceder al gestor de personas");
            System.out.println("Pulsa 3 si deseas cambiar tu contraseña");
            System.out.println("Pulsa 0 para salir de la sesion");
            opcion=sc.nextInt();
            sc.nextLine();
            //comprobacion de error
            while (opcion>3 || opcion<0){
                System.out.println("ERROR: esa opcion no existe.");
                System.out.println("Pulsa 1 si deseas acceder al gestor de libros");
                System.out.println("Pulsa 2 si deseas acceder al gestor de personas");
                System.out.println("Pulsa 3 si deseas cambiar tu contraseña");
                System.out.println("Pulsa 0 para salir de la sesion");
                opcion=sc.nextInt();
                sc.nextLine();
            }
            if (opcion==1){
                opcion = gestorLibrosAdministrador(opcion, biblioteca);
            }
            else if (opcion==2){
                opcion = gestorPersonasAdministrador(opcion, biblioteca);
            }
            else if (opcion==3){
                int i=0;
                boolean encontrado=false;
                while (i<biblioteca.getListaPersona().size() && !encontrado){
                    if(biblioteca.getListaPersona().get(i) instanceof Bibliotecario){
                        if (((Bibliotecario)biblioteca.getListaPersona().get(i)).getNif().equals(nifBibliotecarioActivo)){
                            ((Bibliotecario)biblioteca.getListaPersona().get(i)).cambiarContraseña();
                            encontrado=true; 
                        }
                    }
                    i++;
                }
                opcion=9;
            }
            else if (opcion==0){
                System.out.println("Volvamos al selector de gestores entonces");
                privilegios=9;
            }
        }
        return privilegios;
    }
    /**
     * Metodo del menu del gestor de Personas de los administradores, recibe por parametro el int opcion
     * para poder entrar en el bucle y el objeto biblioteca de la clase Biblioteca para poder entrar en
     * su instancia con los diferentes metodos que se usan(devuelve un int opcion).
     * 
     * @param opcion int para poder entrar en el bucle i salir de el
     * @param biblioteca Objeto de la clase Biblioteca.
     * @return opcion int para poder entrar en el bucle i salir de el
     */
    public static int gestorPersonasAdministrador(int opcion, Biblioteca biblioteca) {
        int opcionGestor;
        //bucle donde transcurren las opciones del gestor de personas
        while (opcion==2){
            System.out.println("Que desea hacer?");
            System.out.println("Pulsa 1 para añadir un bibliotecario al sistema");
            System.out.println("Pulsa 2 para eliminar un bibliotecario del sistema");
            System.out.println("Pulsa 3 para añadir un usuario al sistema");
            System.out.println("Pulsa 4 para eliminar un usuario del sistema");
            System.out.println("Pulsa 5 para volver al selector de gestores");
            opcionGestor=sc.nextInt();
            sc.nextLine();
            switch (opcionGestor){
                case 1:
                    crearBibliotecario(biblioteca);
                    break;
                    
                case 2:
                    Bibliotecario.eliminarPersona(biblioteca.getListaPersona());
                    break; 
                    
                case 3:
                    crearUsuario(biblioteca);
                    break;
                    
                case 4:
                    Usuario.eliminarPersona(biblioteca.getListaPersona());
                    break;
                    
                case 5:
                    System.out.println("Hasta la proxima");
                    opcion=9;
                    break;
                    
                default:
                    System.out.println("ERROR: Este comando no existe");
                    System.out.println("Inserta un comando que exista");
            }
            System.out.println("Pulsa enter para continuar");
            sc.nextLine();
        }
        return opcion;
    }
    
    /**
     * Metodo que recibe por parametro un objeto de la clase Biblioteca para luego
     * crear un objeto de la clase Bibliotecario, añadirle valores a sus atributos 
     * y añadir al bibliotecario en la ArrayList de Personas listaPersona que obtenemos
     * del objeto que hemos pasado por parametro.
     * 
     * @param biblioteca objeto de la clase Biblioteca del cual obtendremos la ListaPersona.
     */
    public static void crearBibliotecario(Biblioteca biblioteca){
        Bibliotecario b1=new Bibliotecario();
        b1.solicitarDatosPersona();
        biblioteca.getListaPersona().add(b1);
    }
    
    /**
     * Metodo que recibe por parametro un objeto de la clase Biblioteca para luego
     * crear un objeto de la clase Usuario, añadirle valores a sus atributos 
     * y añadir al Usuario en la ArrayList de Personas listaPersona que obtenemos
     * del objeto que hemos pasado por parametro.
     * 
     * @param biblioteca objeto de la clase Biblioteca del cual obtendremos la ListaPersona.
     */
    public static void crearUsuario(Biblioteca biblioteca){
        Usuario u1=new Usuario();
        u1.solicitarDatosPersona();
        biblioteca.getListaPersona().add(u1);
    }
    /**
     * Metodo del menu del gestor de Libros de los administradores, recibe por parametro el int opcion
     * para poder entrar en el bucle y el objeto biblioteca de la clase Biblioteca para poder entrar en
     * su instancia con los diferentes metodos que se usan(devuelve un int opcion).
     * 
     * @param opcion int para poder entrar en el bucle i salir de el
     * @param biblioteca Objeto de la clase Biblioteca.
     * @return opcion int para poder entrar en el bucle i salir de el
     */
    public static int gestorLibrosAdministrador(int opcion, Biblioteca biblioteca) {
        int opcionGestor;
        //bucle donde transcurren las opciones del gestor de libros
        while (opcion==1){
            System.out.println("Que desea hacer?");
            System.out.println("Pulsa 1 para añadir un libro");
            System.out.println("Pulsa 2 para eliminar un libro");
            System.out.println("Pulsa 3 para buscar un libro por su ISBN");
            System.out.println("Pulsa 4 para buscar un libro por su titulo");
            System.out.println("Pulsa 5 para mostrar todos los libros");
            System.out.println("Pulsa 6 para mostrar todos los libros disponibles en este momento");
            System.out.println("Pulsa 7 para reservar un libro para un usuario de la biblioteca");
            System.out.println("Pulsa 8 para devolver un libro de un usuario a la biblioteca");
            System.out.println("Pulsa 9 para ver los libros reservados de un usuario de la biblioteca");
            System.out.println("Pulsa 10 para ver informacion sobre las reservas de un libro");
            System.out.println("Pulsa 11 para copiar un libro de la biblioteca, cambiando solo los datos que desees");
            System.out.println("Pulsa 0 volver al selector de gestores");
            opcionGestor=sc.nextInt();
            sc.nextLine();
            
            switch (opcionGestor){
                case 1:
                    Libro.anadirLibro(biblioteca.getListaLibros());
                    break;
                    
                case 2:
                    Libro.eliminarLibro(biblioteca.getListaLibros());
                    break;
                    
                case 3:
                    int posicion=Libro.buscarLibroISBN(biblioteca.getListaLibros());
                    /* interpreta el valor que se devuelve del metodo, siendo este
                      la posicion de la lista en la que se encuentra el Libro o siendo -1
                      si no se encuentra en ella.
                     */
                    if (posicion==-1){
                        System.out.println("No tenemos ningun libro con esa ISBN");
                    }
                    else {
                        System.out.println("El libro con esa ISBN se encuentra en la posicion numero "+posicion);
                        System.out.println("y su titulo es: "+biblioteca.getListaLibros().get(posicion).getTitulo());
                    }
                    break;
                    
                case 4:
                    Libro.buscarLibroTitulo(biblioteca.getListaLibros());
                    break;
                    
                case 5:
                    biblioteca.mostrarLibros();
                    break;
                    
                case 6:
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                    
                case 7:
                    Reserva.reservarLibro(biblioteca.getListaPersona(), biblioteca.getListaLibros()); 
                    break;
                    
                case 8:
                    Reserva.devolverLibro(biblioteca.getListaPersona(), biblioteca.getListaLibros()); 
                    break;
                    
                case 9:
                    System.out.println("Dime el numero de telefono de tu usuario");
                    int telefono=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ahora tu correo electronico");
                    String correo=sc.nextLine();
                    int i=0;
                    boolean encontrado=false;
                    while (i<biblioteca.getListaPersona().size() && !encontrado){
                        if(biblioteca.getListaPersona().get(i) instanceof Usuario){
                            if (((Usuario)biblioteca.getListaPersona().get(i)).getTelefono()==(telefono) && 
                                ((Usuario)biblioteca.getListaPersona().get(i)).getCorreoElectronico().equals(correo)){
                                ((Usuario)biblioteca.getListaPersona().get(i)).mostrarReservas();
                                encontrado=true;
                            }
                        }
                        i++;
                    }
                    if(!encontrado){
                        System.out.println("No existe ningun usuario de la biblioteca que tenga ese telefono y ese correo electronico, hay algun error");
                    }
                    break;
                
                case 10:
                    biblioteca.buscarLibroBiblioteca();
                    break;
                    
                case 11:
                    Libro.añadirLibroCopia(biblioteca);
                    break;
                    
                case 0:
                    System.out.println("Volvamos al selector de gestores entonces");
                    opcion=9;
                    break;
                    
                default:
                    System.out.println("ERROR: Este comando no existe");
                    System.out.println("Inserta un comando que exista");
            }
            System.out.println("Pulsa enter para continuar");
            sc.nextLine();
        }
        return opcion;
    }
    

}
