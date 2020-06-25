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
public class BusRoute {

    private static int busRouteId;
    private static String busRouteName;
    private static int routeStartTime;
    DataBaseCon db = new DataBaseCon();
    Scanner input = new Scanner(System.in);

    public void setBusRouteName() {

        System.out.println("Enter the name of the route");
        busRouteName = input.nextLine();
    }

    public String getBusRouteName() {

        return busRouteName;

    }

    public void setBusRouteId() {

        System.out.println("Enter the id of the route");
        busRouteId = input.nextInt();
    }

    public int getBusRouteId() {

        return busRouteId;

    }

    public void setBusRouteStartTime() {

        System.out.println("Enter the start time of the route");
        routeStartTime = input.nextInt();
    }

    public int getBusRouteStartTime() {

        return routeStartTime;

    }

    public void insertRoute() {

        setBusRouteName();

        db.insertRoute(getBusRouteName());

    }

    public void getRoutesList() {

        db.getRouteList();
    }

    public void createStopListTable() {

        db.createStopListTable(getBusRouteName());

    }

    public void insertStopsInStopList() {

        String contin = "y";

        do {

            System.out.println("Enter the stop name");
            String stopName = input.nextLine();

            db.insertStopName(getBusRouteName(), stopName);

            System.out.println("Add another stop? Y/N");
            contin = input.next();
            input.nextLine();

        } while (contin.equals("y"));
    }

    public void createRouteDataBase() {

        db.createRouteDataBase(getBusRouteName());
    }

    public void createTimesListTable() {

        db.createTimesListTable(getBusRouteName());

    }

    public void createTimeDataTable() {

        setBusRouteId();
        setBusRouteStartTime();

        db.createTimeDataTable(getBusRouteId(), getBusRouteStartTime());

    }

    public void insertTimeData() {

        db.insertTimeData(getBusRouteId(), getBusRouteStartTime());

    }

    public void insertRouteAndTimeIntoList() {

        db.insertRouteAndTimeIntoList(getBusRouteId(), getBusRouteStartTime());

    }

}
