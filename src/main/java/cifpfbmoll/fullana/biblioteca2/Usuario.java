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

    public int getLibrosReservados() {
        return librosReservados;
    }

    public void setLibrosReservados(int librosReservados) {
        this.librosReservados = librosReservados;
    }
    

    
    //convierte los valores de un objeto usuario en string, para que sea mas facil imprimir por pantalla

    @Override
    public String toString (){
        return super.toString()+"Usuario{" + "telefono=" + telefono + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", correoElectronico=" + correoElectronico + ", listaReservas=" + listaReservas + ", librosReservados=" + librosReservados + '}';
    }

    /**
     * Metodo que sobreescribe el metodo de su clase padre, ademas de pedir los 
     * datos que ya se piden en el metodo de la clase padre pide otros para asignarlos como
     * valor a los atributos de la instancia donde se esta ejectuando el metodo.
     */
    @Override
    public void solicitarDatosPersona(){
        super.solicitarDatosPersona();
        System.out.println("Escribe el telefono del usuario");
        this.setTelefono(sc.nextInt());
        sc.nextLine();
        System.out.println("Escribe la direccion del usuario");
        this.setDireccion(sc.nextLine());
        System.out.println("Escribe el codgio postal del usuario");
        this.setCodigoPostal(sc.nextInt());
        sc.nextLine();
        System.out.println("Escribe el correo electronico del usuario");
        this.setCorreoElectronico(sc.nextLine());
    }

    
    /**
     * Metodo para mostrar la Lista de reservas de un usuario concreto.
     */
    public void mostrarReservas(){
        for (int i=0;i<this.getListaReservas().size();i++){
            this.getListaReservas().get(i).mostrarInfoChula();
        }
    }
    /**
     * Metodo que sobreescribe le metodo de su clase padre, pide unos datos 
     * para luego assignarlos al atributo correo de la instancia sobre la que
     * se esta ejecutando.
     */
    @Override
    public void cambiarContraseña() {
        System.out.println("Introduce tu nuevo correo electronico que funciona como contraseña");
        String correo=sc.nextLine();
        this.setCorreoElectronico(correo);
        System.out.println("Contraseña actualizada");
    }
    
    
    
    
    
    
}
