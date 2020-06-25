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
        DataBaseCon dbcon = new DataBaseCon();
        
        System.out.println("Welcome Admin");
        System.out.println("\n\n");
        System.out.println("What Would You Like to Do?\n1-Creating a new bus route\n2-Insert time data to a certain route\n3-Get the List of Routes");
        int adminChoice = input.nextInt();
        
        switch (adminChoice){
            
            case 1:
                input.nextLine();
                System.out.println("Enter the name of the route");
                String routeName = input.nextLine();
                
                
                
                
                dbcon.insertRoute(routeName);
                dbcon.createRouteTable(routeName);
                dbcon.createRouteDataBase(routeName);
                dbcon.createTimesListTable(routeName);
                
                String contin = "y";
                
                do{
                    
                    System.out.println("Enter the stop name");
                    String stopName = input.nextLine();
                    
                    dbcon.insertStopName(routeName, stopName);
                    
                    System.out.println("Add another stop? Y/N");
                    contin = input.next();
                    input.nextLine();
                    
                }while(contin.equals("y"));
                
                
                break;
            case 2:
                
                dbcon.getRouteList();
                
                System.out.println("\n\nEnter the id of the route");
                int routeId = input.nextInt();
                
                System.out.println("Enter the start time of the route");
                int startTime = input.nextInt();
                
                dbcon.createTimeDataTable(routeId, startTime);
                dbcon.insertTimeData(routeId, startTime);
                dbcon.insertRouteAndTime(routeId, startTime);
                
                
                
                
                
                
                break;
                
            case 3:
                
                dbcon.getRouteList();
                
                break;
        }
        
        
        
        
        

    }
}
