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
    void insertTimeData(int routeId, int startTime){
        
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
                int time = input.nextInt();

                insertTimeDataForStops(routeId, startTime, stopName, time);

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

}
