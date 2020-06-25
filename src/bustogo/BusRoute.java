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
    private static int routeAndTimeId;
    private static String routeAndTimeName;
    private static int departureStopId;
    private static int destinationStopId;
    private static int numberOfBookingTickets;
    private static int departureTime;

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

    public int getRouteAndTimeId() {

        return routeAndTimeId;
    }

    public void setDepartureStopId() {
        System.out.println("Enter the id Of the stop you would like to depart on");
        departureStopId = input.nextInt();
    }

    public int getDepartureStopId() {
        return departureStopId;

    }

    public void setDestinationStopId() {
        System.out.println("Enter the id Of the stop you would like to get off");
        destinationStopId = input.nextInt();
    }

    public int getDestinationStopId() {
        return destinationStopId;
    }

    public void setNumberOfBookingTickets() {
        System.out.println("Enter the number of the tickets you would like to book");
        numberOfBookingTickets = input.nextInt();
    }

    public int getNumberOfBookingTickets() {

        return numberOfBookingTickets;

    }

    public int getDepartureTime() {

        return departureTime;
    }

    public void insertRoute() {

        setBusRouteName();

        db.insertRoute(getBusRouteName());

    }

    public void getRoutesList() {

        db.getRouteList();
    }

    public void getStopList(int routeId) {

        busRouteId = routeId;

        db.getStopList(getBusRouteId());

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

    public void getTheListOfRoutesAndTimes(int routeId) {

        busRouteId = routeId;

        db.getTheListOfRoutesAndTimes(busRouteId);
    }

    public void chooseYourRouteAndTime() {
        System.out.println("\n\nEnter the id of the route and time you would like to choose");
        routeAndTimeId = input.nextInt();

        db.chooseRouteAndTime(getBusRouteId(), db.findRouteAndTimeName(getBusRouteId(), getRouteAndTimeId()));
    }

    public void bookTicketManually() {

        setDepartureStopId();
        System.out.println("\n\n");
        setDestinationStopId();
        System.out.println("\n\n");
        setNumberOfBookingTickets();
        System.out.println("\n\n");

        if (db.checkSpace(getBusRouteId(), db.findRouteAndTimeName(getBusRouteId(), getRouteAndTimeId()),
                getDepartureStopId(),
                getDestinationStopId(),
                getNumberOfBookingTickets()
        )) {

            System.out.println("\n\nWould You Like to Finalize? Y/N");

            String finalizeOrder = input.next();

            if (finalizeOrder.equals("y")) {

                db.bookTickets(getBusRouteId(), db.findRouteAndTimeName(getBusRouteId(), getRouteAndTimeId()),
                        getDepartureStopId(),
                        getDestinationStopId(),
                        getNumberOfBookingTickets()
                );

            }
        }

    }

    public String findTheEarliestBus(int departureId, int departTime, int destinationId, int numberOfTickets) {

        departureStopId = departureId;
        departureTime = departTime;
        destinationStopId = destinationId;
        numberOfBookingTickets = numberOfTickets;

        routeAndTimeName = db.findTheEarliestBus(getBusRouteId(), getDepartureStopId(), getDepartureTime(), getDestinationStopId(), getNumberOfBookingTickets());

        return routeAndTimeName;

    }

    public void bookTicketsForTheEarliestPossible(String earliestRouteAndTimeName){

        routeAndTimeName = earliestRouteAndTimeName;

        if (!(routeAndTimeName.equals("notFound"))) {

            System.out.println("\n\nWould You Like to Finalize? Y/N");

            String finalizeOrder = input.next();
            if (finalizeOrder.equals("y")) {

                db.bookTickets(getBusRouteId(), routeAndTimeName,
                        getDepartureStopId(),
                        getDestinationStopId(),
                        getNumberOfBookingTickets()
                );

            }

        }
    }

}
