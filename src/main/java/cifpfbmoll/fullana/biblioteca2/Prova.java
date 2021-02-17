/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;
import java.util.Scanner;
/**
 *
 * @author jaume
 */
public class Prova {
    
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args) {
        Biblioteca biblioteca=new Biblioteca("Son");
        Bibliotecario b1=new Bibliotecario();
        b1.solicitarDatosPersona();
        biblioteca.getListaPersona().add(b1);
        System.out.println(biblioteca.getListaPersona().get(0));
        System.out.println("1 registrar 2 borrar");
        int respuesta=sc.nextInt();
        sc.nextLine();
        while (respuesta!=0){
            if (respuesta==1){
            Bibliotecario b2=new Bibliotecario();
            b2.solicitarDatosPersona();
            biblioteca.getListaPersona().add(b2);
            }
            else if (respuesta==2){
            Persona.eliminarPersona(biblioteca.getListaPersona()); 
            }
            for (int i=0;i<biblioteca.getListaPersona().size();i++){
                System.out.println(biblioteca.getListaPersona().get(i));
            }
            System.out.println("1 registrar 2 borrar");
            respuesta=sc.nextInt();
            sc.nextLine();
        }
    }
}
