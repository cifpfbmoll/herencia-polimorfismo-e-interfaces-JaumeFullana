/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author jaume
 */
public class Usuario extends Persona{
    
    public static Scanner sc=new Scanner(System.in);
    //Atributos de la clase usuario
    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String correoElectronico;
    private ArrayList <Reserva> listaReservas;
    private int librosReservados;
    //Constructores vacio, completo y copia. Se crea lista de reservas en todos directamente.
    public Usuario() {
        this.listaReservas=new ArrayList <Reserva>();
    }   

    public Usuario(int telefono, String direccion, int codigoPostal, String correoElectronico, String nombre, String apellido1, String apellido2, int edad) {
        super(nombre, apellido1, apellido2, edad);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setCodigoPostal(codigoPostal);
        this.setCorreoElectronico(correoElectronico);
        this.listaReservas = new ArrayList <Reserva>();
    }
    

    public Usuario(Usuario u1) {
        super(u1.getNombre(), u1.getApellido1(), u1.getApellido2(), u1.getEdad());
        this.setTelefono(u1.getTelefono());
        this.setDireccion(u1.getDireccion());
        this.setCodigoPostal(u1.getCodigoPostal());
        this.setCorreoElectronico(u1.getCorreoElectronico());
        this.setListaReservas(u1.getListaReservas());
    }
    
        public Usuario(Persona p1,int telefono, String direccion, int codigoPostal, String correoElectronico) {
        super(p1.getNombre(), p1.getApellido1(), p1.getApellido2(), p1.getEdad());
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setCodigoPostal(codigoPostal);
        this.setCorreoElectronico(correoElectronico);
        this.listaReservas = new ArrayList <Reserva>();
    }
    //Getters y setters

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    
    //convierte los valores de un objeto usuario en string, para que sea mas facil imprimir por pantalla

    @Override
    public String toString (){
        return super.toString()+"Usuario{" + "telefono=" + telefono + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", correoElectronico=" + correoElectronico + ", listaReservas=" + listaReservas + ", librosReservados=" + librosReservados + '}';
    }

    /**
     * Metodo para crear un nuevo Usuario con valores en todos sus atributos, se le
     * pasa una lista de Usuarios por parametro para añadir el usuario creado en ella.
     * 
     * @param listaUsuario ArrayList en la que se guarda el usuario creado.
     */
    @Override
    public Usuario solicitarDatosPersona(){
        Persona p1=super.solicitarDatosPersona();
        System.out.println("Escribe el telefono del usuario");
        int telefono=sc.nextInt();
        sc.nextLine();
        System.out.println("Escribe la direccion del usuario");
        String direccion=sc.nextLine();
        System.out.println("Escribe el codgio postal del usuario");
        int codigoPostal=sc.nextInt();
        sc.nextLine();
        System.out.println("Escribe el correo electronico del usuario");
        String correoElectronico=sc.nextLine();
        Usuario u1=new Usuario(p1,telefono, direccion,codigoPostal,correoElectronico);
        return u1;
    }

    /**
     * Metodo para "reservar" un libro, se da una ISBN y con esa ISBN se busca en la
     * lista de Libros pasada por parametro un Libro con esa ISBN, una vez encontrado
     * se guarda una copia del objeto, solo con los valores necesarios para identificarlo,
     * en la listaLibrosReservados del Usuario, se modifican librosReservados y lo referente a las copias.
     * 
     * @param listaLibros ArrayList de Libros en la cual se busca y se obtiene el Libro a reservar
     * 
     */
    public void reservarLibro(ArrayList <Libro> listaLibros) {
        if (librosReservados<5){
            boolean encontrado=false;
            System.out.println("Escribe la ISBN del libro a reservar");
            String ISBN=sc.nextLine();
            int i=0;
            while (i<listaLibros.size() && !encontrado){
                if (listaLibros.get(i).getISBN().equals(ISBN)){
                    encontrado=true;
                    if ((listaLibros.get(i).getCopiasDisponibles()-1)<0){
                        System.out.println("Lo siento, pero todas las copias del libro estan reservadas actualmente");
                    }
                    else{
                        listaLibros.get(i).setCopiasDisponibles(listaLibros.get(i).getCopiasDisponibles()-1,listaLibros.get(i).getNumeroCopias());
                        System.out.println("El libro "+listaLibros.get(i).getTitulo()+" ha sido reservado con exito");
                        LocalDateTime fecha= LocalDateTime.now();
                        Reserva r1=new Reserva(listaLibros.get(i),fecha.toString(),fecha.getHour()+":"+fecha.getMinute());
                        listaReservas.add(r1);
                        librosReservados++;
                    }
                }
                i++;
            }
            if (!encontrado){
                System.out.println("No hay ningun libro con esa ISBN en el sistema");
            }
        }
        else {
            System.out.println("Lo siento, ya tienes 5 libros reservados, no puedes reservar mas");
        }
    }
    /**
     * Metodo para "devolver" un libro, se da una ISBN y con esa ISBN se busca en la
     * lista de Libros reservados del Usuario el primer Libro con esa ISBN, una vez encontrado
     * se elimina el Libro de la lista y se modifican librosReservados y lo referente a las copias.
     * 
     * @param listaLibros ArrayList de Libros en la cual se busca y se suma a las copiasDisponibles el Libro devuelto.
     * 
     */
    public void devolverLibro(ArrayList <Libro> listaLibros){
        if (librosReservados<1){
            System.out.println("No tienes ningun libro para devolver!");
        }
        else{
            boolean encontrado=false;
            System.out.println("Escribe la ISBN del libro a devolver");
            String ISBN=sc.nextLine();
            int i=0;
            while (i<listaReservas.size() && !encontrado){
                if (listaReservas.get(i).getLibro().getISBN().equals(ISBN)){
                    encontrado=true;
                    System.out.println("El libro "+listaReservas.get(i).getLibro().getTitulo()+" ha sido devuelto con exito");
                    listaReservas.remove(i);
                    librosReservados--;
                    int j=0;
                    boolean encontradoEnBiblioteca=false;
                    while (j<listaLibros.size() && !encontradoEnBiblioteca){
                        if (listaLibros.get(j).getISBN().equals(ISBN)){
                            encontradoEnBiblioteca=true;
                            listaLibros.get(j).setCopiasDisponibles(listaLibros.get(j).getCopiasDisponibles()+1,listaLibros.get(j).getNumeroCopias());
                        }
                        j++;
                    }

                }
                i++;
            }
            if (!encontrado){
                System.out.println("No hay ninguna ISBN como esa en la tu lista de reservados");
            }  
        }
    }
    /**
     * Metodo para mostrar la Lista de Libros reservados por un usuario concreto.
     */
    public void mostrarLibrosReservados(){
        for (int i=0;i<listaReservas.size();i++){
            System.out.println(listaReservas.get(i).toString());
        }
    }
    
    
    
    
    
    
}
