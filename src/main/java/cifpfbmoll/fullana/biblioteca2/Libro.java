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
public class Libro {
    
    public static Scanner sc=new Scanner(System.in);
    //contador de los diferetes titulos que se guardan en la biblioteeca
    private static int contadorLibros;
    //Atributos de Libro
    private String ISBN;
    private String titulo;
    private String autor;
    private String editorial;
    private int numeroCopias;
    private int copiasDisponibles;
    /**
     * Constructores de Libro(vacio, con todos los atributos y el constructor copia) 
     * en cada uno se suma 1 al contador de libros, ya que se añade un libro a la biblioteca.
     */
    public Libro() {
        contadorLibros++;
    }

    public Libro(String ISBN, String titulo, String autor, String editorial, int numeroCopias, int copiasDisponibles) {
        contadorLibros++;
        this.setISBN(ISBN);
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setEditorial(editorial);
        this.setNumeroCopias(numeroCopias);
        this.setCopiasDisponibles(copiasDisponibles, numeroCopias);
    }
    
    public Libro(Libro lbr) {
        this.setISBN(lbr.getISBN());
        this.setTitulo(lbr.getTitulo());
        this.setAutor(lbr.getAutor());
        this.setEditorial(lbr.getEditorial());
    }
    
    //Getters y setters
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(int numeroCopias) {
        //Control para asegurarse que almenos añadan 1 copia del libro
        while (numeroCopias<1){
            System.out.println("El numero de copias no puede ser menor que 1!");
            System.out.println("Inserta el numero de copias del libro:");
            numeroCopias=sc.nextInt();
            sc.nextLine();
        }
        this.numeroCopias = numeroCopias;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles, int numeroCopias) {
            //Control para que no haya mas copias disponibles de las que tenemos ni el numero de copias disponibles sea negativo
        while(numeroCopias<copiasDisponibles || copiasDisponibles<0){
            if (numeroCopias<copiasDisponibles){
                System.out.println("Es imposible que haya disponibles mas copias de las que tenemos");
                System.out.println("Inserte el numero correcto, porfavor");
                copiasDisponibles=sc.nextInt();
            }
            else if (copiasDisponibles<0){
            System.out.println("No puedes insertar un numero de copias disponibles negativo!");
            System.out.println("inserta el numero correcto");
            copiasDisponibles=sc.nextInt();
            }
        }
        
        this.copiasDisponibles = copiasDisponibles;
    }
    //metodo para imprimir los valores de un objeto Libro
    @Override
    public String toString() {
        return "Libro{" + "ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + ", numeroCopias=" + numeroCopias + ", copiasDisponibles=" + copiasDisponibles + '}';
    }
    //metodo para imprimir valor de un objeto libro que se encuentra en la lista de reservas de un usuario
    public String ReservasToString() {
        return "Libro{" + "ISBN=" + ISBN + ", titulo=" + titulo + ", autor=" + autor + ", editorial=" + editorial + '}';
    }
    
    /** 
     * Metodo que crea y añade valores a un objeto Libro y lo guarda dentro de la lista 
     * listaLibros que se pasa por parametro.
     * 
     * @param listaLibros Arraylist donde se guardan los libros de la biblioteca
     */
    public static void anadirLibro(ArrayList <Libro> listaLibros){
        Libro lbr=new Libro();
        System.out.println("Inserta la ISBN del libro");
        lbr.setISBN(sc.nextLine());
        System.out.println("Inserta el titulo del libro");
        lbr.setTitulo(sc.nextLine());
        System.out.println("Inserta el autor del libro");
        lbr.setAutor(sc.nextLine());
        System.out.println("Inserta la editorial del libro");
        lbr.setEditorial(sc.nextLine());
        System.out.println("Inserta el numero de copias del libro");
        int numeroCopias=sc.nextInt();
        sc.nextLine();
        lbr.setNumeroCopias(numeroCopias);
        System.out.println("Inserta el numero de copias disponibles del libro");
        int numeroCopiasDisponibles=sc.nextInt();
        sc.nextLine();
        lbr.setCopiasDisponibles(numeroCopiasDisponibles,numeroCopias);
        listaLibros.add(lbr);
    }
    /**
     * Metodo que busca un Libro por su ISBN en la lista listaLibros de la biblioteca,
     * que se pasa por parametro, y si lo encuentra, y no hay ninguna copia reservada,
     * lo elimina de la lista (Si no lo encuentra lo indica).
     * 
     * @param listaLibros Arraylist donde se guardan los libros de la biblioteca
     */
    public static void eliminarLibro (ArrayList <Libro> listaLibros){
        
        boolean encontrado=false;
        System.out.println("Escribe la ISBN del libro");
        String isbn=sc.nextLine();
        for (int i=0; i<listaLibros.size();i++){
            
            if (isbn.equals(listaLibros.get(i).getISBN())){
                encontrado=true;
                //Si el Libro tiene alguna copia no disponible(esta reservada) no se puede eliminar
                if( (listaLibros.get(i).getNumeroCopias())!=(listaLibros.get(i).getCopiasDisponibles()) ){
                    System.out.println("Lo siento, no se puede eliminar un libro reservado");
                }
                else{
                    System.out.println("Procedo a eliminar el libro: "+(listaLibros.get(i).getTitulo()));
                    listaLibros.remove(i);
                    contadorLibros--;
                }
            }   
        }
        if (!encontrado){
            System.out.println("No hay ningun libro con esa ISBN");
        }    
    }
    /** 
     * Metodo que busca un Libro por su ISBN en la lista que se pasa por parametro
     * y devuelve su posicion en la lista, si no se encuentra en ella devuelve -1.
     *
     * @param listaLibros Arraylist donde se guardan los libros de la biblioteca
     * @return posicion numero que indica la posicion del libro en listaLibros, devuelve -1 si no se encuentra alli
     */
    public static int buscarLibroISBN (ArrayList <Libro> listaLibros){
        System.out.println("Escribe la ISBN del libro");
        String isbn=sc.nextLine();
        int posicion=-1;
        for (int i=0; i<listaLibros.size();i++){
            
            if (isbn.equals(listaLibros.get(i).getISBN())){
                posicion=listaLibros.lastIndexOf(listaLibros.get(i));
            }
        }
        return posicion;
    }
    /** Metodo que busca un Libro por su titulo (solo hace falta que la string este en el titulo,
     * no que sea el titulo entero) en una lista pasada por parametro y lo imprime por pantalla,
     * si no encuentra el libro tambien lo especifica.
     *
     * @param listaLibros Arraylist donde se guardan los libros de la biblioteca
     */
    public static void buscarLibroTitulo(ArrayList <Libro> listaLibros){
        boolean encontrado=false;
        System.out.println("Escribe el titulo o una parte del titulo del libro que quieres buscar");
        String titulo=sc.nextLine();
        for (int i=0;i<listaLibros.size();i++){
            if(listaLibros.get(i).getTitulo().contains(titulo)){
                System.out.println(listaLibros.get(i).toString());
                encontrado=true;
            }
        }
        if (!encontrado){
            System.out.println("No hay ningun libro que contenga o tenga el titulo especificado");
        }
    }
    /**
     * Metodo que pide la ISBN de un libro para buscarlo en la biblioteca y, si lo encuentra,
     * hacer una copia de el de los datos que se quieran y meterlo en la listaLibros de la biblioteca. 
     * La ISBN no se puede copiar ya que tiene que ser unica.
     * 
     * @param biblioteca objeto de la clase Biblioteca.
     */
    public static void añadirLibroCopia(Biblioteca biblioteca){
        System.out.println("Dame la ISBN del libro que quieres copiar");
        String ISBN=sc.nextLine();
        boolean encontrado=false;
        int i=0;
        while (i<biblioteca.getListaLibros().size() && !encontrado){
            if (biblioteca.getListaLibros().get(i).getISBN().equals(ISBN)){
                encontrado=true;
                Libro l1=new Libro(biblioteca.getListaLibros().get(i));
                /*Se copia numeroCopias y copiasDisponibles a traves del setter ya que en
                el constructor copia que he realizado no se copian estos datos
                */ 
                l1.setNumeroCopias(biblioteca.getListaLibros().get(i).getNumeroCopias());
                l1.setCopiasDisponibles(biblioteca.getListaLibros().get(i).getCopiasDisponibles(),
                biblioteca.getListaLibros().get(i).getNumeroCopias());
                System.out.println("La ISBN no puede ser la misma que la del libro que estas copiando");
                System.out.println("las ISBN son unicas, escribe la ISBN de este nuevo libro");
                String ISBNl1=sc.nextLine();
                l1.setISBN(ISBNl1);
                System.out.println("nueva ISBN asignada.");
                System.out.println("");
                opcionesCopiado(l1);
                biblioteca.getListaLibros().add(l1);
                contadorLibros++;
                System.out.println("El libro ha sido creado como usted ha indicado!");
            }
            i++;
        }
        if (!encontrado){
            System.out.println("No existe ningun libro con esa ISBN en esta biblioteca");
        }
    }
    /**
     * Metodo que recibe por parametro un libro y modifica sus atributos se quiere.
     * 
     * @param l1 Libro en el que se modificaran, o no, los datos de sus atributos.
     */
    private static void opcionesCopiado(Libro l1) {
        System.out.println("Pulsa 1 si quieres copiar el titulo, cualquier otro numero si quieres asignarlo tu");
        int opcion=sc.nextInt();
        sc.nextLine();
        if (opcion!=1){
            System.out.println("Dame el nuevo titulo de este libro:");
            String titulo=sc.nextLine();
            l1.setTitulo(titulo);
            System.out.println("Hecho!");
            System.out.println("");
        }
        else{
            System.out.println("Copiado");
            System.out.println("");
        }
        System.out.println("Pulsa 1 si quieres copiar el autor, cualquier otro numero si quieres asignarlo tu");
        opcion=sc.nextInt();
        sc.nextLine();
        if (opcion!=1){
            System.out.println("Dame el nuevo autor de este libro:");
            String autor=sc.nextLine();
            l1.setAutor(autor);
            System.out.println("Hecho!");
            System.out.println("");
        }
        else{
            System.out.println("Copiado");
            System.out.println("");
        }
        System.out.println("Pulsa 1 si quieres copiar la editorial, cualquier otro numero si quieres asignarlo tu");
        opcion=sc.nextInt();
        sc.nextLine();
        if (opcion!=1){
            System.out.println("Dame la nueva editorial de este libro:");
            String editorial=sc.nextLine();
            l1.setEditorial(editorial);
            System.out.println("Hecho!");
            System.out.println("");
        }
        else{
            System.out.println("Copiado");
            System.out.println("");
        }
        System.out.println("Pulsa 1 si quieres copiar el numero de copias, cualquier otro numero si quieres asignarlo tu");
        opcion=sc.nextInt();
        sc.nextLine();
        if (opcion!=1){
            System.out.println("Dame el nuevo numero de copias de este libro:");
            int numeroCopias=sc.nextInt();
            sc.nextLine();
            l1.setNumeroCopias(numeroCopias);
            System.out.println("Hecho!");
            System.out.println("");
        }
        else{
            System.out.println("Copiado");
            System.out.println("");
        }
        System.out.println("Pulsa 1 si quieres copiar el numero de copias disponibles,"
                + " cualquier otro numero si quieres asignarlo tu");
        opcion=sc.nextInt();
        sc.nextLine();
        if (opcion!=1){
            System.out.println("Dame el nuevo numero de copias disponibles de este libro:");
            int numeroCopiasDisponibles=sc.nextInt();
            sc.nextLine();
            l1.setCopiasDisponibles(numeroCopiasDisponibles,l1.getNumeroCopias());
            System.out.println("Hecho!");
            System.out.println("");
        }
        else{
            System.out.println("Copiado");
            System.out.println("");
        }
    }
    
}
