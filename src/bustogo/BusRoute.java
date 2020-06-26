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
    private static String routeStartTime;
    private static int routeAndTimeId;
    private static String routeAndTimeName;
    private static int departureStopId;
    private static int destinationStopId;
    private static int numberOfBookingTickets;
    private static String departureTime;
    private static String stopName;
    private static String finalizeOrder;
    private static String contin = "y";

    DataBaseCon db = new DataBaseCon();
    Scanner input = new Scanner(System.in);

    //getter and setters
    public void setBusRouteName() {

        System.out.println("\n\nEnter the name of the route:");
        busRouteName = input.nextLine();
    }

    public String getBusRouteName() {

        return busRouteName;

    }

    public void setBusRouteId() {

        System.out.println("\n\nEnter the id of the route:");
        busRouteId = input.nextInt();
    }

    public int getBusRouteId() {

        return busRouteId;

    }

    public void setBusRouteStartTime() {

        System.out.println("\n\nEnter the start time of the route(HH:MM):");
        routeStartTime = input.next();
    }

    public String getBusRouteStartTime() {

        return routeStartTime;

    }

    public int getRouteAndTimeId() {

        return routeAndTimeId;
    }

    public String getRouteAndTimeName() {

        return routeAndTimeName;

    }

    public void setDepartureStopId() {
        System.out.println("\n\nEnter the id Of the stop you would like to depart on:");
        departureStopId = input.nextInt();
    }

    public int getDepartureStopId() {
        return departureStopId;

    }

    public void setDestinationStopId() {
        System.out.println("\n\nEnter the id Of the stop you would like to get off:");
        destinationStopId = input.nextInt();
    }

    public int getDestinationStopId() {
        return destinationStopId;
    }

    public void setNumberOfBookingTickets() {
        System.out.println("\n\nEnter the number of the tickets you would like to book:");
        numberOfBookingTickets = input.nextInt();
    }

    public int getNumberOfBookingTickets() {

        return numberOfBookingTickets;

    }

    public String getDepartureTime() {

        return departureTime;
    }

    public void setStopName() {
        System.out.println("\n\nEnter the stop name");
        stopName = input.nextLine();

    }

    public String getStopName() {

        return stopName;
    }

    //time conversion from real time to seconds
    public int getSeconds(String realTime) {

        int seconds, minutes, hours;

        char[] timeArray = realTime.toCharArray();

        if (String.valueOf(timeArray[0]).equals("0")) {

            String hourInString = String.valueOf(timeArray[1]);

            hours = Integer.valueOf(hourInString);
        } else {

            String hourInString = String.valueOf(timeArray[0]) + String.valueOf(timeArray[1]);
            hours = Integer.valueOf(hourInString);

        }
        if (String.valueOf(timeArray[3]).equals("0")) {

            String minutesInString = String.valueOf(timeArray[4]);
            minutes = Integer.valueOf(minutesInString);

        } else {

            String minutesInString = String.valueOf(timeArray[3]) + String.valueOf(timeArray[4]);
            minutes = Integer.valueOf(minutesInString);

        }

        seconds = hours * 3600 + minutes * 60;
        return seconds;

    }

    //create methods
    public void createStopListTable() {

        db.createStopListTable(getBusRouteName());

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

        db.createTimeDataTable(getBusRouteId(), getSeconds(getBusRouteStartTime()));

    }

    //insert methods
    public void insertRoute() {

        setBusRouteName();

        db.insertRoute(getBusRouteName());

    }

    public void insertStopsInStopList() {

        contin = "y";

        do {

            setStopName();

            db.insertStopName(getBusRouteName(), getStopName());

            System.out.println("\n\nAdd another stop? Y/N");
            contin = input.next();
            input.nextLine();

        } while (contin.equals("y"));
    }

    public void insertTimeData() {

        db.insertTimeData(getBusRouteId(), getSeconds(getBusRouteStartTime()));

    }

    public void insertRouteAndTimeIntoList() {

        db.insertRouteAndTimeIntoList(getBusRouteId(), getSeconds(getBusRouteStartTime()));

    }

    //get list methods
    public void getRoutesList() {

        db.getRouteList();
    }

    public void getStopList(int routeId) {

        busRouteId = routeId;

        db.getStopList(getBusRouteId());

    }

    public void getTheListOfRoutesAndTimes(int routeId) {

        busRouteId = routeId;

        db.getTheListOfRoutesAndTimes(busRouteId);
    }

    //choose ticket manually method
    public void chooseYourRouteAndTime() {
        System.out.println("\n\nEnter the id of the route and time you would like to choose:");
        routeAndTimeId = input.nextInt();

        db.chooseRouteAndTime(getBusRouteId(), db.findRouteAndTimeName(getBusRouteId(), getRouteAndTimeId()));
    }

    //find the eraliest possible method
    public String findTheEarliestBus(int departureId, String departTime, int destinationId, int numberOfTickets) {

        departureStopId = departureId;
        departureTime = departTime;
        destinationStopId = destinationId;
        numberOfBookingTickets = numberOfTickets;

        routeAndTimeName = db.findTheEarliestBus(getBusRouteId(), getDepartureStopId(), getSeconds(getDepartureTime()), getDestinationStopId(), getNumberOfBookingTickets());

        return getRouteAndTimeName();

    }

    //book tickets methods
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

            System.out.println("\n\nWould You Like to Finalize? Y/N\n");

            finalizeOrder = input.next();

            if (finalizeOrder.equals("y")) {

                db.bookTickets(getBusRouteId(), db.findRouteAndTimeName(getBusRouteId(), getRouteAndTimeId()),
                        getDepartureStopId(),
                        getDestinationStopId(),
                        getNumberOfBookingTickets()
                );

            }
        }

    }

    public void bookTicketsForTheEarliestPossible(String earliestRouteAndTimeName) {

        routeAndTimeName = earliestRouteAndTimeName;

        if (!(getRouteAndTimeName().equals("notFound"))) {

            System.out.println("\n\nWould You Like to Finalize? Y/N\n");

            finalizeOrder = input.next();
            if (finalizeOrder.equals("y")) {

                db.bookTickets(getBusRouteId(), getRouteAndTimeName(),
                        getDepartureStopId(),
                        getDestinationStopId(),
                        getNumberOfBookingTickets()
                );

            }

        }
    }

}
