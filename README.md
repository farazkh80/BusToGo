# Inspiration
Taking public transport in Vancouver is already very slow compared to larger cities such as
Toronto. The process of purchasing bus tickets in Vancouver is rather inefficient and takes
an extended period of time for two main reasons. One of the main issues with the current
system is that each user must pay the fee once they are already on the bus. This leads to
lines developing which can result in delays in the bus schedule, especially during rush hour
periods. Another issue is that users aren't able to know in advance whether there will be
enough room on the bus to accommodate everyone at the stop. With the limited capacity of
the buses, combined with the surge of large groups of people, busses quickly all up leaving
many users waiting for the next bus (which can take up to an hour).

# Solution Explanation
To solve the issues highlighted above with our current bus-ticket purchasing system, we
created BusToGo. BusToGo is a program which increases the efficiency of the current
system through the following ways. Within the BusToGo program you can complete three
of the following tasks:
  1. Creating a new bus route
  2. Insert time data to a certain route
  3. Get the List of Routes

BusToGo starts by gathering the following pieces of information from the user:

What Would You Like to Do?

- Creating a new bus route
  - Enter the name of the route
  - Enter the id of the route
  - Enter the start time of the route
  - Enter the id Of the stop you would like to depart on
  - Enter the ID Of the stop you would like to get on
  - Enter the number of the tickets you would like to book
  - Enter the stop name
  - Add another stop? Y/N
  - Enter the id of the route and time you would like to choose
  - Would You Like to Finalize? Y/N
  
- Insert time data to a certain route
- Get the List of Routes
  - You can either book a bus ticket by seeing all the available routes and times
manually,or you can simply enter your desired location and time and BustoGo
will and you the best option right away
  - Please Choose an option:
    - Seeing and Choosing a route and a time manually
    - Find me the earliest bus
  - Enter the id of the route you would like to choose
  - What stop would you like to depart on?(Enter the ID)
  - Enter the time of your departure
  - What stop would you like to get on at?(Enter the ID)
  - How many tickets do you need?

The BusToGo program connects to the SQL database which stores all the data for bus
schedules, capacity, timing, etc. Thus, the program immediately updates the datatables in
the database as the user adds information about their bus trip. When the user has answered
all the necessary questions about their root, BusToGo will display the next possible bus time
that has sufficient capacity to facilitate all the users throughout their trip.

Looking at the client part of the program, we believe that BusToGo is able to solve the
main issues with the current bus ticketing system in Vancouver. It allows the users to book
bus tickets prior to boarding the bus which reduces the chance for line ups and delays while
stopping at each bus station. Secondly, by using our platform users can also clearly see the
current availability on each bus. This allows them to better plan their bus schedule without
having to worry about the bus being too full to accommodate everyone.

# Program Configuration
Please note that this application wouldn't't work unless you install the MySQL database
on your computer, add JDK java library and congure it properly and finally adjust the
databaseCon class correspondingly.
Please find a reference video in the following link about how to download the MySQL
database and add JDK library to configure your connection:
Video for installing MySQL database: https://youtu.be/BOUMR85B-V0
Video for adding JDK library: https://youtu.be/9rTJa4l8YQ0


# Program Screening
You can also simply watch the following video to see how the program works completely.
Video of the program: https://youtu.be/tIcgLdXuujM


# Possible Improvements
For future versions, BusToGo could be expanded/improved in the following ways. First,
we plan on developing the platform's user interface. Creating a more aesthetic design of
the platform would help make BusToGo more user friendly and thus more readily used by
everyday bus users. In addition, we plan on implementing a function to the platform which
gets the current time and location of the user, and based on this information, provides
various options for bus routes that the user can reserve. This would reduce the number of
questions the user would need to manually input before getting the list of possible routes.
