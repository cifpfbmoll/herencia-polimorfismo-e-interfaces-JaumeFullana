/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.util.ArrayList;

/**
 *
 * @author Jaume
 */
public class Bibliotecario extends Persona{
    
    private String puestoTrabajo;
    private String nif;
    private String contrasena;

    public Bibliotecario() {
    }

    public Bibliotecario(String puestoTrabajo, String nif, String contrasena, String nombre, String apellido1, String apellido2, int edad) {
        super(nombre, apellido1, apellido2, edad);
        this.setPuestoTrabajo(puestoTrabajo);
        this.setNif(nif);
        this.setContrasena(contrasena);
    }
    
    public Bibliotecario(Bibliotecario b1) {
        super(b1.getNombre(), b1.getApellido1(), b1.getApellido2(), b1.getEdad());
        this.setPuestoTrabajo(b1.getPuestoTrabajo());
        this.setNif(b1.getNif());
        this.setContrasena(b1.getContrasena());
    }
    
    public Bibliotecario(Persona p1,String puestoTrabajo, String nif, String contrasena) {
        super(p1.getNombre(), p1.getApellido1(), p1.getApellido2(), p1.getEdad());
        this.setPuestoTrabajo(puestoTrabajo);
        this.setNif(nif);
        this.setContrasena(contrasena);
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Bibliotecario{" + "puestoTrabajo=" + puestoTrabajo + ", nif=" + nif + ", contrasena=" + contrasena + '}';
    }
    /**
     * Metodo que sobreescribe el metodo de su clase padre, ademas de pedir los 
     * datos que ya se piden en el metodo de la clase padre pide otros para asignarlos como
     * valor a los atributos de la instancia donde se esta ejectuando el metodo.
     */
    @Override
    public void solicitarDatosPersona() {
        super.solicitarDatosPersona();
        System.out.println("Escribe el puesto de trabajo del bibliotecario");
        this.setPuestoTrabajo(sc.nextLine());
        System.out.println("Escribe el nif del bibliotecario");
        this.setNif(sc.nextLine());
        System.out.println("Escribe la contraseña del bibliotecario");
        this.setContrasena(sc.nextLine());
    }
    /**
     * Metodo que sobreescribe le metodo de su clase padre, pide unos datos 
     * para luego assignarlos al atributo contrasena de la instancia sobre la que
     * se esta ejecutando.
     */
    @Override
    public void cambiarContraseña() {
        System.out.println("Introduce tu nueva contraseña");
        String contrasena=sc.nextLine();
        this.setContrasena(contrasena);
        System.out.println("Contraseña actualizada");
    }

    
    
    
    
    
}
