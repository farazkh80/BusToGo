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
public class AdminApp {

    public static void adminOperations() {

        Scanner input = new Scanner(System.in);

        BusRoute newRoute = new BusRoute();

        System.out.println("Welcome Admin");
        System.out.println("\n\n");
        System.out.println("What Would You Like to Do?\n1-Creating a new bus route\n2-Insert time data to a certain route\n3-Get the List of Routes");
        int adminChoice = input.nextInt();

        switch (adminChoice) {

            case 1:

                newRoute.insertRoute();
                newRoute.createStopListTable();
                newRoute.insertStopsInStopList();
                newRoute.createRouteDataBase();
                newRoute.createTimesListTable();

                break;
            case 2:

                newRoute.getRoutesList();
                newRoute.createTimeDataTable();
                newRoute.insertTimeData();
                newRoute.insertRouteAndTimeIntoList();

                break;

            case 3:

                newRoute.getRoutesList();

                break;
        }

    }
}
