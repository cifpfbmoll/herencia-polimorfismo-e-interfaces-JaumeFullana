/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author jaume
 */
public class Reserva {
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
                                    Libro l1=new Libro(listaLibros.get(j));
                                    Reserva r1=new Reserva(l1,fecha.getDayOfMonth()+"/"+fecha.getMonth()+"/"+fecha.getYear(),fecha.getHour()+":"+fecha.getMinute());
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
    
}
