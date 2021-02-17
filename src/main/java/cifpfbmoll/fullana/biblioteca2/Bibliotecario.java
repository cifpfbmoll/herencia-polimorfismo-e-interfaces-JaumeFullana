/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.util.ArrayList;

/**
 *
 * @author PC
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

    @Override
    public Bibliotecario solicitarDatosPersona() {
        Persona p1=super.solicitarDatosPersona();
        System.out.println("Escribe el puesto de trabajo del bibliotecario");
        String puestoTrabajo=sc.nextLine();
        System.out.println("Escribe el nif del bibliotecario");
        String nif=sc.nextLine();
        System.out.println("Escribe la contraseña del bibliotecario");
        String contrasena=sc.nextLine();
        Bibliotecario b1=new Bibliotecario(p1,puestoTrabajo,nif,contrasena);
        return b1;
    }

    
    
    
    
    
}
