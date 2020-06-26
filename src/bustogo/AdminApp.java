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

        String resume;

        System.out.println("\n\nWelcome Admin\n\n");

        //resumes as long as admin wants so
        do {

            System.out.println("What Would You Like to Do?\n\n1-Creating a New Bus Route\n------------------------------\n2-Insert Time Data/Schedule to a Certain Route\n------------------------------"
                    + "\n3-Go Back to the Main Page\n------------------------------\n4-Quit");
            int adminChoice = input.nextInt();

            switch (adminChoice) {

                //if admin creates a new route
                case 1:

                    System.out.println("\n----------------------------\n| Creating a New Bus Route |\n----------------------------\n");

                    //prompts for the route name and inserts it into the database
                     newRoute.insertRoute();
                    //creates a stop list for the route in the database
                    newRoute.createStopListTable();
                    //inserts the stops in the stop list
                    newRoute.insertStopsInStopList();
                    //creates a database for the route in order to store the routes
                    newRoute.createRouteDataBase();
                    //creates a table for the route in order to take a track of the schedule list
                    newRoute.createTimesListTable();

                    System.out.println("\n\nThe Action Is Finished\n\n");

                    break;
                case 2:

                    System.out.println("\n-----------------------------------------------\n|Insert Time Data/Schedule to a Certain Route |\n-----------------------------------------------\n");

                    //displays the list of routes
                    newRoute.getRoutesList();
                    //creates a table for the schedule in the route's specific database
                    newRoute.createTimeDataTable();
                    //promts for the departure times from every stop of that route and inserts the detailed time data in the schedule table
                    newRoute.insertTimeData();
                    //adds the shedule and its starttime to the shedule list of the chosen route
                    newRoute.insertRouteAndTimeIntoList();

                    System.out.println("\n\nThe Action Is Finished\n\n");

                    break;

                //goes to main page
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

            //asks if admin wants to continue
            System.out.println("\n\nWould you like to do any other actions? Y/N");

            resume = input.next();
        } while (resume.equalsIgnoreCase("y"));

    }
}
