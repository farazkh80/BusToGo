/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bustogo;

import java.util.Scanner;

/**
 *
 * @author Faraz Khoubsirat
 */
public class BusToGo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Wlecome to BusToGo App.\n\n\n");
        System.out.println("Identify yourself please\n\n");
        System.out.println("1-Client\n----------\n2-Admin\n----------\n3-Quit");

        int user = input.nextInt();
        
        //if client
        if (user == 1) {
            
            //calls the ClientApp class
            ClientApp.clientOperations();

        } 
        //if admin
        else if (user == 2) {
            
            //calls the AdminApp class
            AdminApp.adminOperations();

        } 
        //if quits
        else if (user == 3) {

            System.out.println("\n\nYou chose to quit\n\n");
            System.out.println("\n\nThanks for using BusToGo.\n\n");
            System.exit(0);
        }
    }
}
