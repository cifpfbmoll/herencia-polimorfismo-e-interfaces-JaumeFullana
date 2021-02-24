/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class RedBiblioteca {
    
    private ArrayList <Biblioteca> listaBibliotecas=new ArrayList();
    
    private static Scanner sc=new Scanner(System.in);
    
    public RedBiblioteca() {
    }
    
    public ArrayList<Biblioteca> getListaBibliotecas() {
        return listaBibliotecas;
    }
    /**
     * Metodo que pide la ISBN de un libro para buscarlo en todas las reservas de todas las
     * bibliotecas de el atributo redBibliotecas de la instancia sobre la que se esta ejecutando. 
     * Entra en una biblioteca, omprueba si el libro existe en la biblioteca primero, 
     * y luego si hay alguna copia no disponible ya lo busca en
     * las reservas de los usuarios hasta que encuentra tantas reservas con ese libro
     * como copias no disponibles hay en la biblioteca.
     */
    public void buscarLibroBibliotecas(){
        System.out.println("Dame la ISBN del libro que quieres informacion");
        String ISBN=sc.nextLine();
        for (int n=0;n<this.getListaBibliotecas().size();n++){
            System.out.println("En la biblioteca "+this.getListaBibliotecas().get(n).getNombreBiblioteca());
            int i=0;
            boolean encontrado=false;
            while (i<this.getListaBibliotecas().get(n).getListaLibros().size() && !encontrado){
                if (this.getListaBibliotecas().get(n).getListaLibros().get(i).getISBN().equals(ISBN)){
                    encontrado=true;
                    boolean usuariosConLibro=false;
                    int contador=0;
                    int j=0;
                    while (contador<(this.getListaBibliotecas().get(n).getListaLibros().get(i).getNumeroCopias()-
                    this.getListaBibliotecas().get(n).getListaLibros().get(i).getCopiasDisponibles())){
                        if (this.getListaBibliotecas().get(n).getListaPersona().get(j) instanceof Usuario){
                            int k=0;
                            while(k<((Usuario)this.getListaBibliotecas().get(n).getListaPersona().get(j)).getListaReservas().size()
                            && contador<(this.getListaBibliotecas().get(n).getListaLibros().get(i).getNumeroCopias()-
                            this.getListaBibliotecas().get(n).getListaLibros().get(i).getCopiasDisponibles())){
                                if(((Usuario)this.getListaBibliotecas().get(n).getListaPersona().get(j)).getListaReservas().get(k).getLibro().getISBN().equals(ISBN)){
                                    usuariosConLibro=true;
                                    System.out.println("");
                                    System.out.println("Usuario con este libro:");
                                    System.out.println(((Usuario)this.getListaBibliotecas().get(n).getListaPersona().get(j)).toStringCorta());
                                    System.out.println("");
                                    System.out.println("Reserva de este usuario:");
                                    ((Usuario)this.getListaBibliotecas().get(n).getListaPersona().get(j)).getListaReservas().get(k).mostrarInfoChula();
                                    contador++;
                                }
                                k++;
                            }
                        }
                        j++;
                    }
                    if (!usuariosConLibro){
                        System.out.println("");
                        System.out.println("Ningun usuario tiene reservado ese libro, todas sus copias estan disponibles");
                        System.out.println("");
                    }
                }
                i++;
            }
            if(!encontrado){
                System.out.println("");
                System.out.println("Ese libro no esta en esta biblioteca");
                System.out.println("");
            }
        }
    }
}
