/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.util.ArrayList;

/**
 *
 * @author jaume
 */
public class Usuario extends Persona{
    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String correoElectronico;
    private ArrayList <Reserva> listaReservas;

    public Usuario() {
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

    @Override
    public String toString() {
        return super.toString()+" Usuario{" + "telefono=" + telefono + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", correoElectronico=" + correoElectronico + ", listaReservas=" + listaReservas + '}';
    }
        
    

    
    
    
}
