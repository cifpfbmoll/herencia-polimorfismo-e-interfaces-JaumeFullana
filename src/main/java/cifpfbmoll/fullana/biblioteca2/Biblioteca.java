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
    private ArrayList <Libro> listaLibros=new ArrayList <Libro>();
    private ArrayList <Persona> listaPersona=new ArrayList <Persona>();
    //Constructor vacio de biblioteca
    public Biblioteca() {
    }
    /** Constructor con todos los atributos(solo se pide el nombre ya que este programa
     * es para gestionar 1 sola biblioteca, por tanto solo haran falta una lista de libros
     * i una lista de personal para todo el programa).
     */
    public Biblioteca(String nombreBiblioteca) {
        this.setNombreBiblioteca(nombreBiblioteca);
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

    public ArrayList<Persona> getListaPersona() {
        return listaPersona;
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
}
