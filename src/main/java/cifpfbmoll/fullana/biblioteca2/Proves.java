/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifpfbmoll.fullana.biblioteca2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Proves {
    
    public static void main(String[] args) throws InputMismatchException{
        Scanner sc=new Scanner(System.in);
        System.out.println("Escriu un numero");
        int opcion=comprobarInt(sc);
        System.out.println(opcion);
    }

    public static int comprobarInt(Scanner sc) {
        int opcion=0;
        boolean correcto=false;
        while(!correcto){
            try{
                opcion=sc.nextInt();
                sc.nextLine();
                correcto=true;
            }
            catch(InputMismatchException excepcion){
                sc.nextLine();
                System.out.println("ERROR: el valor introducido no es correcto, inserta uno correcto");
            }
        }
        return opcion;
    }
}
