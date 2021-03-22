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
 * @author Jaume
 */
public class Biblioteca {
    
    public static Scanner sc=new Scanner (System.in);
    //Atributos de Biblioteca
    private String nombreBiblioteca;
    private ArrayList <Libro> listaLibros;
    private ArrayList <Persona> listaPersona;
    
    //Constructor vacio de biblioteca
    public Biblioteca() {
    }
    
    public Biblioteca(String nombreBiblioteca, ArrayList <Libro> listaLibros, ArrayList <Persona> listaPersona) {
        this.setNombreBiblioteca(nombreBiblioteca);
        this.setListaLibros(listaLibros);
        this.setListaPersona(listaPersona);
    }
    
    public Biblioteca(Biblioteca b1) {
        this.setNombreBiblioteca(b1.getNombreBiblioteca());
        this.setListaLibros(b1.getListaLibros());
        this.setListaPersona(b1.getListaPersona());
    }
    //Getters y setters(no se utilizan los setter de las listas ya que se crean cuando se definen los atributos)
    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public void setNombreBiblioteca(String nombreBiblioteca) {
        //Comprobacion de que el nombre de la biblioteca empiezacon mayuscula
        while((Character.getType(nombreBiblioteca.charAt(0)))!=1){
            System.out.println("El nombre de la biblioteca debe empezar en mayuscula");
            System.out.println("Escribe bien el nombre de la biblioteca:");
            nombreBiblioteca=sc.nextLine();
        }
        this.nombreBiblioteca = nombreBiblioteca;
    }

    public ArrayList<Libro> getListaLibros() {
        
        return listaLibros;
    }

    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
    
    public ArrayList<Persona> getListaPersona() {
        return listaPersona;
    }
    
    
    public void setListaPersona(ArrayList<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    @Override
    public String toString() {
        return "Biblioteca{" + "nombreBiblioteca=" + nombreBiblioteca +'}';
    }
    
    
    /**
     * Metodo de instancia que imprime todo los Libros de la lista listaLibros.
     */
    public void mostrarLibros(){
        
        for (int i=0;i<listaLibros.size();i++){
            System.out.println(listaLibros.get(i).toString());
        }
    }
    /**
     * Metodo de instancia que imrpime todos los Libros de la lista listaLibros 
     * que esten disponibles.
     */
    public void mostrarLibrosDisponibles(){
        
        for (int i=0;i<listaLibros.size();i++){
            if (listaLibros.get(i).getCopiasDisponibles()>0){
                System.out.println(listaLibros.get(i).toString());
            }
        }
    }
    /**
     * Metodo que pide el nombre de la biblioteca y crea dos ArrayList, una de persona
     * y otra de libros, y los pasa por parametro al constructor de bibliotecas para
     * crear una nueva. Luego añade la biblioteca a la listaBibliotecas pasada por parametro 
     * y devuelve la biblioteca creada.
     * 
     * @param listaBibliotecas ArrayList de Bibliotecas donde se alamacenan las diferentes 
     * Bibliotecas de nuestro programa.
     * @return devuelve la biblioteca creada.
     */
    public static Biblioteca añadirBiblioteca(ArrayList <Biblioteca> listaBibliotecas){
        System.out.println("Escribe el nombre de la biblioteca");
        String nombre=sc.nextLine();
        ArrayList <Libro> listaLibros=new ArrayList();
        ArrayList <Persona> listaPersona=new ArrayList();
        Biblioteca b1=new Biblioteca(nombre, listaLibros, listaPersona);
        listaBibliotecas.add(b1);
        return b1;
    }
    /**
     * Metodo que imprime por pantalla todas las bibliotecas que tenemos guardadas
     * en la listaBibliotecas pasada por parametro.
     * @param listaBibliotecas ArrayList de Bibliotecas donde se alamacenan las diferentes 
     * Bibliotecas de nuestro programa.
     */
    public static void consultarBibliotecas(ArrayList <Biblioteca> listaBibliotecas) {
        for (int i=0; i<listaBibliotecas.size();i++){
            System.out.println(listaBibliotecas.get(i).toString());
        }
    }
    /**
     * Metodo que pide el nombre de una biblioteca para buscarlo en la listaBibliotecas,
     * que hemos pasado por parametro, y borrarla si la encuentra.
     * 
     * @param listaBibliotecas ArrayList de Bibliotecas donde se alamacenan las diferentes 
     * Bibliotecas de nuestro programa.
     */
    public static void eliminarBiblioteca(ArrayList <Biblioteca> listaBibliotecas) {
        //Excepcion que ocurre en el metodo y se trata en el metodo
        try{
            System.out.println("Dame el nombre de la biblioteca que quieras eliminar");
            String nombre=sc.nextLine();
            boolean encontrado=false;
            int i=0;
            while (i<listaBibliotecas.size() && !encontrado){
                if (listaBibliotecas.get(i).getNombreBiblioteca().equals(nombre)){
                    encontrado=true;
                    listaBibliotecas.remove(i);
                    System.out.println("Biblioteca eliminada");
                }
                i++;
            }
            if(!encontrado){
                throw new Excepciones(4);
            }
        }
        catch(Excepciones e1){
            System.out.println(e1.getMessage());
        }
    }
    /**
     * Metodo que pide la ISBN de un libro para buscarlo en todas las reservas de la
     * biblioteca sobre la que se esta ejecutando. Comprueba si el libro existe en la
     * biblioteca primero, y luego si hay alguna copia no disponible ya lo busca en
     * las reservas de los usuarios hasta que encuentra tantas reservas con ese libro
     * como copias no disponibles hay en la biblioteca.
     */
    public void buscarLibroBiblioteca(){
        System.out.println("Dame la ISBN del libro que quieres informacion");
        String ISBN=sc.nextLine();
        boolean encontrado=false;
        int i=0;
        while (i<this.getListaLibros().size() && !encontrado){
            if (this.getListaLibros().get(i).getISBN().equals(ISBN)){
                encontrado=true;
                boolean usuariosConLibro=false;
                int contador=0;
                int j=0;
                while (contador<(this.getListaLibros().get(i).getNumeroCopias()-
                this.getListaLibros().get(i).getCopiasDisponibles())){
                    if (this.getListaPersona().get(j) instanceof Usuario){
                        int k=0;
                        while(k<((Usuario)this.getListaPersona().get(j)).getListaReservas().size()
                        && contador<(this.getListaLibros().get(i).getNumeroCopias()-
                        this.getListaLibros().get(i).getCopiasDisponibles())){
                            if(((Usuario)this.getListaPersona().get(j)).getListaReservas().get(k).getLibro().getISBN().equals(ISBN)){
                                usuariosConLibro=true;
                                System.out.println("");
                                System.out.println("Usuario con este libro:");
                                System.out.println(((Usuario)this.getListaPersona().get(j)).toStringCorta());
                                System.out.println("");
                                System.out.println("Reserva de este usuario:");
                                ((Usuario)this.getListaPersona().get(j)).getListaReservas().get(k).mostrarInfoChula();
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
