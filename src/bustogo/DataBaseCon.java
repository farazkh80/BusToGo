/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bustogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faraz Khoubsirat
 */
public class DataBaseCon {

    Connection createDataBaseCon() throws ClassNotFoundException {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "Mypassword1234");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    Connection routesCon() throws ClassNotFoundException {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busroutes", "root", "Mypassword1234");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    Connection routeTimesCon() throws ClassNotFoundException {

        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/routetimeslist", "root", "Mypassword1234");
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    String returnRealTime(int seconds) {

        int t1 = 0, t2 = 0, t3 = 0, remainder = 0;
        String t2InString;

        t1 = seconds / 3600;
        remainder = seconds % 3600;
        t2 = remainder / 60;

        if (t2 < 10) {

            t2InString = "0" + t2;

            return t1 + ":" + t2InString;

        } else {

            return t1 + ":" + t2;
        }
    }

    int getSeconds(String realTime) {
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

    String getRouteName(int routeId) {
        String routeName = null;

        try {

            Statement stmt = routesCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM routesList WHERE id = " + routeId + "");

            while (rs.next()) {

                routeName = rs.getString("routeName");

            }
        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return routeName;

    }

    void getRouteList() {

        int routeId;
        String routeName;

        try {
            //using prepared statement to get the list of clubs
            Statement stmt = routesCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM routesList");

            System.out.println("\n\nHere is the list of current available routes\n\n");
            while (rs.next()) {

                routeId = rs.getInt("id");
                routeName = rs.getString("routename");

                System.out.println("ID: " + routeId + "\t\tRoute: " + routeName);
                System.out.println("");
                System.out.println("");

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //shows error if unsuccessful
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void insertRoute(String route) {

        try {

            //using prepared statement to insert a new Club
            PreparedStatement stmt = routesCon().prepareStatement("INSERT INTO routeslist (routename) VALUES (?)");
            stmt.setString(1, route);

            stmt.execute();
            System.out.println("Succesfully added the new route to routeslist .");
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {
            //shows error if unsuccessful
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void createStopListTable(String tableName) {

        try {
            //using prepared statement to create a table for the posts of the Club
            PreparedStatement stmt = routesCon().prepareStatement("CREATE TABLE `" + tableName + "` (id int PRIMARY KEY AUTO_INCREMENT NOT NULL, stopname varchar(1000) not null);");

            stmt.executeUpdate();
            System.out.println("Succesfully Created the route Table.");
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {
            //shows error if unsuccessful
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void createRouteDataBase(String tableName) {

        try {
            //using prepared statement to create a table for the posts of the Club
            PreparedStatement stmt = createDataBaseCon().prepareStatement("CREATE DATABASE `" + tableName + "`");

            stmt.executeUpdate();
            System.out.println("Succesfully Created the DATABASE");
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {
            //shows error if unsuccessful
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void insertStopName(String routeName, String stopName) {

        try {

            PreparedStatement stmt = routesCon().prepareStatement("INSERT INTO `" + routeName + "` (stopname) VALUES (?)");
            stmt.setString(1, stopName);
            stmt.execute();
            System.out.println("Succesfully added the new stop.");
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void createTimesListTable(String tableName) {

        try {

            PreparedStatement stmt = routeTimesCon().prepareStatement("CREATE TABLE `" + tableName + "` (id int PRIMARY KEY AUTO_INCREMENT NOT NULL, routeandtime varchar(1000)"
                    + " not null, starttime int not null);");

            stmt.execute();
            System.out.println("Succesfully Created the route Times list Table.");
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {
            //shows error if unsuccessful
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void createTimeDataTable(int routeId, int startTime) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getRouteName(routeId), "root", "Mypassword1234");

            String tableName = getRouteName(routeId) + "_" + startTime;

            PreparedStatement stmt = con.prepareStatement("CREATE TABLE `" + tableName + "` (id int PRIMARY KEY AUTO_INCREMENT NOT NULL, stopname varchar(1000) not null, time int not null, "
                    + "currentcap int not null, maxcap int not null);");

            stmt.execute();
            System.out.println("Succesfully Created the route Time Table.");
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {
            //shows error if unsuccessful
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void insertRouteAndTimeIntoList(int routeId, int startTime) {
        try {

            PreparedStatement stmt = routeTimesCon().prepareStatement("INSERT INTO `" + getRouteName(routeId) + "` (routeandtime, starttime) VALUES (?, ?)");
            stmt.setString(1, getRouteName(routeId) + "_" + startTime);
            stmt.setInt(2, startTime);

            stmt.execute();
            System.out.println("Succesfully added the root and time to the times list db.");
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void insertTimeData(int routeId, int startTime) {

        getStopNamesForTimeDataInsertion(routeId, startTime);
    }

    void getStopNamesForTimeDataInsertion(int routeId, int startTime) {

        String stopName;
        Scanner input = new Scanner(System.in);
        try {
            //using prepared statement to get the list of clubs
            Statement stmt = routesCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `" + getRouteName(routeId) + "`");
            while (rs.next()) {

                stopName = rs.getString("stopname");
                System.out.println("Enter the time of departure from " + stopName);
                String time = input.next();

                insertTimeDataForStops(routeId, startTime, stopName, getSeconds(time));

            }
        } catch (ClassNotFoundException | SQLException ex) {
            //shows error if unsuccessful
            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void insertTimeDataForStops(int routeId, int startTime, String stopName, int time) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getRouteName(routeId), "root", "Mypassword1234");

            PreparedStatement stmt = con.prepareStatement("INSERT INTO `" + getRouteName(routeId) + "_" + startTime + "` (stopname, time, currentcap, maxcap) VALUES (?, ?, 0, 30)");
            stmt.setString(1, stopName);
            stmt.setInt(2, time);

            stmt.execute();
            System.out.println("Succesfully added the new stop to the time table.");
            stmt.close();

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void getTheListOfRoutesAndTimes(int routeId) {

        int routeAndTimeId;
        String routeAndTime;
        int startTime;

        try {

            Statement stmt = routeTimesCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `" + getRouteName(routeId) + "`");
            while (rs.next()) {

                routeAndTimeId = rs.getInt("id");
                routeAndTime = rs.getString("routeandtime");
                startTime = rs.getInt("starttime");

                System.out.println("ID: " + routeAndTimeId + "\t\tRoute: " + getRouteName(routeId) + "\t\tTIme: " + returnRealTime(startTime));

            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String findRouteAndTimeName(int routeId, int routeAndTimeId) {
        String routeAndTime = null;
        try {

            Statement stmt = routeTimesCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `" + getRouteName(routeId) + "` WHERE id = " + routeAndTimeId);
            while (rs.next()) {

                routeAndTime = rs.getString("routeandtime");

            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return routeAndTime;

    }

    void chooseRouteAndTime(int routeId, String routeAndTimeName) {

        int stopId;
        String stopName;
        int stopTime;
        int currentCap;
        int maxCap;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getRouteName(routeId), "root", "Mypassword1234");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `" + routeAndTimeName + "`");
            while (rs.next()) {

                stopId = rs.getInt("id");
                stopName = rs.getString("stopname");
                stopTime = rs.getInt("time");
                currentCap = rs.getInt("currentcap");
                maxCap = rs.getInt("maxcap");

                System.out.println("ID: " + stopId + "\t\tStop Name: " + stopName + "\nTime: " + returnRealTime(stopTime) + "\t\tCurrent Capacity: "
                        + currentCap + "\t\tMaximum Capacity: " + maxCap);
                System.out.println("");
                System.out.println("");

            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    boolean checkSpace(int routeId, String routeAndTimeName, int departureStopId, int destinationStopId, int numberOfTickets) {

        int currentCap;
        int maxCap;
        boolean available = false;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getRouteName(routeId), "root", "Mypassword1234");

            int stopId = departureStopId;

            while (stopId <= destinationStopId) {

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM `" + routeAndTimeName + "` WHERE id = " + stopId);
                while (rs.next()) {

                    currentCap = rs.getInt("currentcap");
                    maxCap = rs.getInt("maxcap");

                    if (!(maxCap == currentCap)) {

                        if (!(currentCap + numberOfTickets > maxCap)) {

                            available = true;
                        } else {

                            available = false;
                        }
                    } else {

                        available = false;
                    }

                }

                stopId++;

            }

        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return available;

    }

    void bookTickets(int routeId, String routeAndTimeName, int departureStopId, int destinationStopId, int numberOfTickets) {

        int stopId;
        int newCap;
        int currentCap;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getRouteName(routeId), "root", "Mypassword1234");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `" + routeAndTimeName + "`");
            while (rs.next()) {

                stopId = rs.getInt("id");

                if (stopId >= departureStopId && stopId <= destinationStopId) {
                    currentCap = rs.getInt("currentcap");

                    newCap = currentCap + numberOfTickets;

                    PreparedStatement updateCapStmt = con.prepareStatement("UPDATE `" + routeAndTimeName + "` SET currentcap = ? WHERE id = ?");

                    updateCapStmt.setInt(1, newCap);
                    updateCapStmt.setInt(2, stopId);

                    updateCapStmt.execute();

                }
            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

        showOrderOutput(routeId, routeAndTimeName, departureStopId, destinationStopId);

    }

    void showOrderOutput(int routeId, String routeAndTimeName, int departureStopId, int destinationStopId) {

        String departureStopName = null;
        String destinationStopName = null;
        int departureTime = 0;
        int destinationTime = 0;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getRouteName(routeId), "root", "Mypassword1234");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `" + routeAndTimeName + "` WHERE id = " + departureStopId);

            while (rs.next()) {

                departureStopName = rs.getString("stopname");
                departureTime = rs.getInt("time");

            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getRouteName(routeId), "root", "Mypassword1234");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `" + routeAndTimeName + "` WHERE id = " + destinationStopId);

            while (rs.next()) {

                destinationStopName = rs.getString("stopname");
                destinationTime = rs.getInt("time");

            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("You tickets from " + departureStopName + " to " + destinationStopName + ", are booked.");
        System.out.println("\nPlease depart the bus from " + departureStopName + " at " + returnRealTime(departureTime)
                + " . Your esstimated arrival time to " + destinationStopName + " is at " + returnRealTime(destinationTime));

    }

    void getStopList(int routeId) {

        String stopName;
        int stopId;

        try {

            Statement stmt = routesCon().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `" + getRouteName(routeId) + "`");

            while (rs.next()) {

                stopId = rs.getInt("id");
                stopName = rs.getString("stopname");

                System.out.println("ID: " + stopId + "\t\tStop: " + stopName);
                System.out.println("");

            }
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {

            Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String findTheEarliestBus(int routeId, int departureId, int departureTime, int destinationId, int numOfTickets) {

        String routeAndTimeName;
        String returnedRouteAndTimeName = null;
        int minDiff = 300;
        boolean found = false;

        while (!found && minDiff < 86400) {
            try {

                Statement stmt = routeTimesCon().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM `" + getRouteName(routeId) + "`");

                while (rs.next()) {

                    routeAndTimeName = rs.getString("routeandtime");

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + getRouteName(routeId), "root", "Mypassword1234");
                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("SELECT * FROM `" + routeAndTimeName + "` WHERE id = " + departureId);

                    int stopId;
                    int stopTime;
                    String stopName;
                    int currentCap;
                    int maxCap;

                    while (rs2.next()) {

                        stopTime = rs2.getInt("time");

                        if (stopTime - departureTime >= 0 && stopTime - departureTime <= minDiff) {

                            if (checkSpace(routeId, routeAndTimeName, departureId, destinationId, numOfTickets)) {
                                System.out.println("Enough Space available!\n\n");
                                System.out.println("You have the following option");

                                stopId = rs2.getInt("id");
                                stopName = rs2.getString("stopname");

                                currentCap = rs2.getInt("currentcap");
                                maxCap = rs2.getInt("maxcap");

                                System.out.println("ID: " + stopId + "\t\tStop Name: " + stopName + "\nTime: " + returnRealTime(stopTime) + "\t\tCurrent Capacity: "
                                        + currentCap + "\t\tMaximum Capacity: " + maxCap);
                                System.out.println("");
                                System.out.println("");

                                returnedRouteAndTimeName = routeAndTimeName;
                                found = true;
                                break;

                            }
                        }
                    }

                }
                stmt.close();
            } catch (ClassNotFoundException | SQLException ex) {

                Logger.getLogger(DataBaseCon.class.getName()).log(Level.SEVERE, null, ex);
            }

            minDiff += 300;
        }

        if (found) {

            return returnedRouteAndTimeName;
        } else {

            System.out.println("No Options Available");
            return "notFound";
        }

    }

}
