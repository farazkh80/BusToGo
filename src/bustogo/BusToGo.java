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

        System.out.println("Wlecome to BusToGo App.\n\nEnter 1 for client and 2 for admin");

        int user = input.nextInt();

        if (user == 1) {

            ClientApp client = new ClientApp();

            client.clientOperations();

        } else if (user == 2) {
            AdminApp admin = new AdminApp();
            admin.adminOperations();
        }
    }
}
