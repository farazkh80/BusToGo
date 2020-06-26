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
public class ClientApp {

    public static void clientOperations() {

        Scanner input = new Scanner(System.in);
        BusRoute newRoute = new BusRoute();

        System.out.println("Welcome Client.\n\n");

        System.out.println("You can either book a bus ticket by seeing all the available routes and times manually,\nor you can simply enter your desired location and time"
                + " and BustoGo will find you the best option right away.\n\n");

        System.out.println("Please Choose an option:\n1-Seeing and Choosing a route and a time manually\n2-Find me the earliest bus");

        int clientChoice = input.nextInt();

        switch (clientChoice) {

            case 1:

                newRoute.getRoutesList();

                System.out.println("\n\nEnter the id of the route you would like to choose");
                int routeId = input.nextInt();

                newRoute.getTheListOfRoutesAndTimes(routeId);

                newRoute.chooseYourRouteAndTime();

                newRoute.bookTicketManually();

                break;

            case 2:

                newRoute.getRoutesList();

                System.out.println("\n\nEnter the id of the route you would like to choose");
                routeId = input.nextInt();

                newRoute.getStopList(routeId);

                System.out.println("What stop would you like to depart on?(Enter the ID)");

                int departureId = input.nextInt();

                System.out.println("Enter the time of your departure");

                String departureTime = input.next();

                System.out.println("What stop would you like to get off at?(Enter the ID)");

                int destinationId = input.nextInt();

                System.out.println("how many tickets do you need?");

                int numOfTickets = input.nextInt();

                String earliestRootAndTimeName = newRoute.findTheEarliestBus(departureId, departureTime, destinationId, numOfTickets);
                
                newRoute.bookTicketsForTheEarliestPossible(earliestRootAndTimeName);

                break;
        }
    }

}
