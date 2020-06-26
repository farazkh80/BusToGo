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
        int routeId;
        int departureId;
        int destinationId;
        int numOfTickets;
        String departureTime;
        String resume;

        System.out.println("\n\nWelcome Client\n\n");

        System.out.println("**********************************************************************************************************");
        System.out.println("You can either book a bus ticket  manually by seeing all the available routes and times,\nor you can simply enter your desired location and time"
                + " and BusToGo will find you the best option right away!");
        System.out.println("**********************************************************************************************************");

        //resumes as long as user wants so
        do {

            System.out.println("\n\nPlease Choose an option:\n1-Seeing and Choosing a route and time manually\n--------------------------------\n2-Find me the earliest bus\n--------------------------------\n3-Go Back to the Main Page"
                    + "\n--------------------------------\n4-Quit");

            int clientChoice = input.nextInt();

            switch (clientChoice) {

                //if client wants to choose manually
                case 1:

                    System.out.println("\n--------------------------------\n| Seeing and Choosing Manually |\n--------------------------------\n");

                    //displays the list of routes
                    newRoute.getRoutesList();

                    //prompts for the id of the desired route
                    System.out.println("\n\nEnter the id of the route you would like to choose");
                    routeId = input.nextInt();

                    //displays the scheduled times for that route
                    newRoute.getTheListOfRoutesAndTimes(routeId);

                    //promts the user to chooose their desired stops and tickets
                    newRoute.chooseYourRouteAndTime();

                    //booke the tickets and display's the order summary
                    newRoute.bookTicketManually();

                    System.out.println("\n\nThe Action Is Finished\n\n");

                    break;

                //if client chooses to find the ticket automatically
                case 2:

                    System.out.println("\n----------------------------\n| Find Me the Earliest Bus |\n----------------------------\n");

                    //displays the list of routes
                    newRoute.getRoutesList();

                    //prompts for the id of the desired club
                    System.out.println("\n\nEnter the id of the route you would like to choose");
                    routeId = input.nextInt();

                    //displays the list of stops of the chosen route
                    newRoute.getStopList(routeId);

                    //prompts for the id of the departures stop
                    System.out.println("\n\nWhat stop would you like to depart on?(Enter the ID)");

                    departureId = input.nextInt();

                    //prompts for the user's desired departure time
                    System.out.println("\n\nEnter your desired time of departure from (HH:MM)");

                    departureTime = input.next();

                    //prompts for the id of the destination stop
                    System.out.println("\n\nWhat stop would you like to get off at?(Enter the ID)");

                    destinationId = input.nextInt();

                    //prompts for the number of the tickets
                    System.out.println("\n\nhow many tickets do you need?");

                    numOfTickets = input.nextInt();

                    //finds the earliest possible scheduled bus
                    String earliestRootAndTimeName = newRoute.findTheEarliestBus(departureId, departureTime, destinationId, numOfTickets);

                    //confirms the booking and shows the order summary
                    newRoute.bookTicketsForTheEarliestPossible(earliestRootAndTimeName);

                    System.out.println("\n\nThe Action Is Finished\n\n");

                    break;

                //goes back to the main page
                case 3:

                    System.out.println("\n\n\n");
                    String[] args = {};
                    BusToGo.main(args);
                    break;

                //quits the program
                case 4:
                    System.out.println("\n\nYou chose to quit\n\n");
                    System.out.println("\n\nThanks for using BusToGo.\n\n");
                    System.exit(0);
                    break;
            }

            //asks if the user would like to continue 
            System.out.println("\n\nWould you like to do any other actions? Y/N");

            resume = input.next();
        } while (resume.equalsIgnoreCase("y"));
    }

}
