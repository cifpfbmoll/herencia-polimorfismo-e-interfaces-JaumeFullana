/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Jaume
 */
public class Reserva implements Material{
    private Libro libro;
    private String fechaReserva;
    private String horaReserva;
    
    private static Scanner sc=new Scanner(System.in);
    
    public Reserva() {
    }

    public Reserva(Libro libro, String fechaReserva, String horaReserva) {
        this.setLibro(libro);
        this.setFechaReserva(fechaReserva);
        this.setHoraReserva(horaReserva);
    }

    public Reserva(Reserva r1) {
        this.setLibro(r1.getLibro());
        this.setFechaReserva(r1.getFechaReserva());
        this.setHoraReserva(r1.getHoraReserva());
    }
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" + "libro=" + libro.ReservasToString() + ", fechaReserva=" + fechaReserva + ", horaReserva=" + horaReserva + '}';
    }
    /**
     * Metodo que recibe por parametro una ArrayList de Persona y una ArrayList de Libro las cuales seran
     * utilizadas para reservar uno de los libros de la lista para una persona de la lista. Se pedira el
     * telefono y el correo de la persona para la que queremos reservar un libro, buscaremos si esos datos
     * coinciden con algun usuario de la ArrayList de Personas, en caso de que coincidan pediremos la ISBN
     * del libro que se quiere reservar y lo buscaremos en la ArrayList de Libros. Si esa ISBN se encuentra
     * en la lista pasaremos a crear la reserva con ese libro y la fecha actual. Se modifican todos los atributos
     * relazionados con la reserva: se resta un libro a copias disponibles, se suma 1 libro a librosReservados por
     * el usuario,...
     * 
     * @param listaPersona ArrayList de Personas donde se bascara el usuario que quiere reservar el libro.
     * @param listaLibros ArrayList de libros donde buscaremos el libro que se quiere reservar.
     */
    public static void reservarLibro(ArrayList <Persona> listaPersona, ArrayList <Libro> listaLibros){
        System.out.println("Inserta el telefono del usuario para el que va a reservar el libro");
        int telefono=sc.nextInt();
        sc.nextLine();
        System.out.println("Ahora tu correo electronico");
        String correo=sc.nextLine();
        boolean personaEncontrada=false;
        int i=0;
        while (i<listaPersona.size() && !personaEncontrada){
            if(listaPersona.get(i) instanceof Usuario){
                if (((Usuario)listaPersona.get(i)).getTelefono()==telefono &&
                ((Usuario)listaPersona.get(i)).getCorreoElectronico().equals(correo)){
                    personaEncontrada=true;
                    if (((Usuario)listaPersona.get(i)).getLibrosReservados()<5){
                        boolean libroEncontrado=false;
                        System.out.println("Perfecto "+listaPersona.get(i).getNombre());
                        System.out.println("Dame la ISBN del libro que quieres reservar");
                        String ISBN=sc.nextLine();
                        int j=0;
                        while (j<listaLibros.size() && !libroEncontrado){
                            if (listaLibros.get(j).getISBN().equals(ISBN)){
                                libroEncontrado=true;
                                if (listaLibros.get(j).getCopiasDisponibles()>=1){
                                    LocalDateTime fecha= LocalDateTime.now();
                                    DateTimeFormatter formatoFecha=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    DateTimeFormatter formatoHora=DateTimeFormatter.ofPattern("HH:mm");
                                    Libro l1=new Libro(listaLibros.get(j));
                                    Reserva r1=new Reserva(l1,fecha.format(formatoFecha),fecha.format(formatoHora));
                                    ((Usuario)listaPersona.get(i)).getListaReservas().add(r1);
                                    listaLibros.get(j).setCopiasDisponibles(listaLibros.get(j).getCopiasDisponibles()-1, listaLibros.get(j).getNumeroCopias());
                                    ((Usuario)listaPersona.get(i)).setLibrosReservados(((Usuario)listaPersona.get(i)).getLibrosReservados()+1);
                                    System.out.println("Perfecto! reserva realizada"); 
                                }
                                else{
                                    System.out.println("Lo siento, pero todas las copias del libro estan reservadas actualmente");
                                }
                            }
                            j++;
                        }
                        if (!libroEncontrado){
                            System.out.println("No hay ningun libro con esa ISBN en el sistema");
                        }
                    }
                    else{
                        System.out.println("Lo siento, ya tienes 5 libros reservados, no puedes reservar mas");
                    }
                }
            }
            i++;
        }
        if (!personaEncontrada){
            System.out.println("Tu telefono o correo no son correctos");
        }
    }
    /**
     * Metodo que recibe por parametro una ArrayList de Persona y una ArrayList de Libro las cuales seran
     * utilizadas para devolver uno de los libros reservados por una persona de la lista a la biblioteca. Se pedira el
     * telefono y el correo de la persona para la que vamos a devolver un libro, buscaremos si esos datos
     * coinciden con algun usuario de la ArrayList de Personas, en caso de que coincidan pediremos la ISBN
     * del libro que se quiere devolver y lo buscaremos en la ArrayList de Reservas del Usuario. Si esa ISBN se encuentra
     * en la lista pasaremos a devolver el libro y eliminar la reserva. Se modifican todos los atributos
     * relazionados con la reserva: se suma un libro a copias disponibles, se resta 1 libro a librosReservados por
     * el usuario,...
     * 
     * @param listaPersona ArrayList de Personas donde se bascara el usuario que quiere devolver el libro.
     * @param listaLibros ArrayList de libros donde actualizaremos la copias del libro devuelto.
     */
    public static void devolverLibro(ArrayList <Persona> listaPersona, ArrayList <Libro> listaLibros){
        System.out.println("Inserta el telefono del usuario para el que va a devolver el libro");
        int telefono=sc.nextInt();
        sc.nextLine();
        System.out.println("Ahora tu correo electronico");
        String correo=sc.nextLine();
        boolean personaEncontrada=false;
        int i=0;
        while (i<listaPersona.size() && !personaEncontrada){
            if(listaPersona.get(i) instanceof Usuario){
                if (((Usuario)listaPersona.get(i)).getTelefono()==telefono &&
                ((Usuario)listaPersona.get(i)).getCorreoElectronico().equals(correo)){
                    personaEncontrada=true;
                    boolean libroEncontrado=false;
                    System.out.println("Perfecto "+listaPersona.get(i).getNombre());
                    System.out.println("Dame la ISBN del libro que quieres devolver");
                    String ISBN=sc.nextLine();
                    int j=0;
                    while (j<((Usuario)listaPersona.get(i)).getListaReservas().size() && !libroEncontrado){
                        if (((Usuario)listaPersona.get(i)).getListaReservas().get(j).getLibro().getISBN().equals(ISBN)){
                            libroEncontrado=true;
                                int k=0;
                                boolean devuelto=false;
                                while (k<listaLibros.size() && !devuelto){
                                    if(listaLibros.get(k).getISBN().equals(ISBN)){
                                        listaLibros.get(k).setCopiasDisponibles(listaLibros.get(k).getCopiasDisponibles()+1, listaLibros.get(k).getNumeroCopias());
                                        devuelto=true;
                                    }
                                    k++;
                                }
                                ((Usuario)listaPersona.get(i)).setLibrosReservados(((Usuario)listaPersona.get(i)).getLibrosReservados()-1);
                                ((Usuario)listaPersona.get(i)).getListaReservas().remove(j);
                                System.out.println("Perfecto! devolucion realizada"); 
                        }
                        j++;
                    }
                    if (!libroEncontrado){
                        System.out.println("No hay ningun libro con esa ISBN en tus libros reservados");
                    }
                }
            }
            i++;
        }
        if (!personaEncontrada){
            System.out.println("Tu telefono o correo no son correctos");
        }
    }
    /**
     * Metodo que sobreeescribe el metodo de la interfaz Material implementada en esta clase,
     * hacemos una comprobacion para saber de que clase es el Material(ahora siempre es Libro. Al ver
     * la clase de ese Material le sumamos al atributo fechaReserva de ese objeto el tiempo
     * assignado para que se cumpla el tiempo de reserva y asi saber la fecha de devolucion.
     * 
     */
    @Override
    public void obtenerFechaDevolucion() {
        if(this.getLibro() instanceof Libro){/*Esto lo pongo asi teniendo en cuenta que si añadimos videos, revistas....
            lo agruparemos en una clase padre i usaremos esa clase padre para meter ese objeto en la clase reserva com
            atributo donde ahora es Libro*/
            String fechaReserva=this.getFechaReserva();
            DateTimeFormatter formatoFecha=DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaReservaDate= LocalDate.parse(fechaReserva,formatoFecha);
            LocalDate fechaDevolucion=fechaReservaDate.plusMonths(1);
            System.out.println("La fecha de devolucion es: "+fechaDevolucion.format(formatoFecha));
        }
    }
    /**
     * Metodo creado para imprimir de forma mas clara los datos de una reserva. Tambien
     * se usa el metodo obtenerFechaDevolucion para saber la fecha de devolucion.
     */
    @Override
    public void mostrarInfoChula() {
        System.out.println("===============================================================");
        System.out.println("");
        System.out.println("Esta reserva fue realizada el dia: "+this.getFechaReserva());
        System.out.println("La hora en la que se realizo la reserva fue: "+this.getHoraReserva());
        System.out.println("El libro reservado en esta reserva tiene los siguientes datos");
        System.out.println("Titulo: "+this.getLibro().getTitulo());
        System.out.println("Autor: "+this.getLibro().getAutor());
        System.out.println("Editorial: "+this.getLibro().getEditorial());
        System.out.println("ISBN: "+this.getLibro().getISBN());
        this.obtenerFechaDevolucion();
        System.out.println("");
        System.out.println("===============================================================");
    }
    
}
