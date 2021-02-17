/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.util.Scanner;
import java.util.ArrayList;
/**
 *Esta clase es abstracta, por tanto no se podran hacer instancias de esta clase.
 * 
 * @author Jaume
 */

public abstract class Persona {
    
    public static Scanner sc=new Scanner(System.in);
    //Atributos de Persona
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    //Cosntructores de Persona(Vacio, con todos los atributos y constructor copia)
    public Persona() {
    }

    public Persona(String nombre, String apellido1, String apellido2, int edad) {
        this.setNombre(nombre);
        this.setApellido1(apellido1);
        this.setApellido2(apellido2);
        this.setEdad(edad);
    }
    
    public Persona(Persona p1) {
        this.setNombre(p1.getNombre());
        this.setApellido1(p1.getApellido1());
        this.setApellido2(p1.getApellido2());
        this.setEdad(p1.getEdad());
    }
    //setters y getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    /**public void setContrasena(String contrasena) {
        //Comprobacion de que la contraseña del usuario tenga almenos 8 caracteres
        while (contrasena.length()<8){
        System.out.println("La contraseña tiene que ser de 8 caracteres minimo");
        System.out.println("Inserta una nueva contaseña");
        contrasena=sc.nextLine();
        }
        this.contrasena = contrasena;
    }
    */
    //metod para imprimir los valores de un objeto Persona

    @Override    
    public String toString (){
        return "Persona{" + "nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", edad=" + edad + '}';
    }

    /** Metodo que crea y añade valores a unas variables.
     * 
     * @param listaPersona Arraylist donde se guardan los libros de la biblioteca
     */
    public void solicitarDatosPersona(){
        System.out.println("Escribe el nombre del usuario:"); 
        this.setNombre(sc.nextLine());
        System.out.println("Escribe el primer apellido del usuario");
        this.setApellido1(sc.nextLine());
        System.out.println("Escribe el segundo apellido del usuario");
        this.setApellido2(sc.nextLine());
        System.out.println("Escribe la edad del usuario");
        this.setEdad(sc.nextInt());
        sc.nextLine();
    };
    /**
     * Metodo que busca una Persona por su NIF en la lista listaPersonal de la biblioteca,
     * que se pasa por parametro, y si lo encuentra lo elimina de la lista.
     * 
     * @param listaPersona Arraylist donde se guardan los libros de la biblioteca
     */
    public static void eliminarPersona(ArrayList <Persona> listaPersona){
        System.out.println("Escribe el nombre de la persona");
        String nombre=sc.nextLine();
        System.out.println("Escribe el 1 apellido de la persona");
        String apellido1=sc.nextLine();
        System.out.println("Escribe el 2 apellido de la persona");
        String apellido2=sc.nextLine();
        boolean encontrado=false;
        for (int i=0;i<listaPersona.size();i++){
            if (listaPersona.get(i).getNombre().equals(nombre) && 
                listaPersona.get(i).getApellido1().equals(apellido1) &&
                listaPersona.get(i).getApellido2().equals(apellido2)){
                listaPersona.remove(i);
                System.out.println("La persona ha sido eliminada");
                encontrado=true;
            }
        }
        if (!encontrado){
            System.out.println("No existe ninguna persona con ese nombre y apellidos en el sistema");
        }
    }
}
