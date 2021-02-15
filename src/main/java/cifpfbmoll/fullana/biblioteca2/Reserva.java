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
public class Reserva {
    private Libro libro;
    private String fechaReserva;
    private String horaReserva;

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
        return "Reserva{" + "libro=" + libro + ", fechaReserva=" + fechaReserva + ", horaReserva=" + horaReserva + '}';
    }
    
    
}
