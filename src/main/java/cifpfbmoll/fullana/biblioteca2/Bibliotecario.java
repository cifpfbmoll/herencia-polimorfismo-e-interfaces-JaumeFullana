/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

/**
 *
 * @author jaume
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
    
    
    
}
