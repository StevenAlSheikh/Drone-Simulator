# Drone Recon Simulator (CSIS 294 - Project 2)

This project simulates a drone surveying a target area and collecting data. It consists of two parts:

1. **Drone Communication Simulation (Part 1)**: Interacting with a simulated drone to collect grid-based data and guide its movement.
2. **Drone Data Portal (Part 2)**: A web-based portal that stores drone data in a database and displays summarized results.

## Use Case

The simulation imagines a drone used for **search and data collection** in scenarios such as agriculture, surveillance, or disaster recovery. The drone scans a grid area, collects RGB values per tile, and sends data to a portal for storage and analysis.

---

## Technologies Used

- Java Servlets
- JSP (Java Server Pages)
- RESTful Web Services
- JSON
- SQLite
- Tomcat Server
- HTML/CSS
- JavaScript (with jQuery)

---

## Project Structure

```
webapps/
├── dronerecon/                         # Web app for drone simulation
│   ├── drone_launch.jsp               # Launch interface for drone
│   ├── drone_sim.jsp                  # Drone simulation grid and logic
│   └── js/                            # JavaScript files for simulation
│       ├── dronesim.js
│       ├── jquery.min.js
│       └── webservice_client.js
│
├── dronereconportal/                  # Web app for data portal and DB access
│   ├── areasearch.jsp                 # Area ID input form
│   ├── arearesults.jsp               # Display results from the database
│   ├── db/
│   │   └── dronedata.sqlite           # SQLite database file
│   └── WEB-INF/
│       └── lib/
│           └── dronereconportal.jar  # Compiled servlet and helper classes

```

---

## How to Run

### Prerequisites

- Java JDK
- Apache Tomcat Server
- SQLite JDBC driver
- A web browser

### Setup Steps

1. Copy the `dronerecon` and `dronereconportal` folders into the `webapps/` directory of the Tomcat installation.
2. Make sure `dronedata.sqlite` is located in the correct path expected by your servlet.
3. Add the compiled classes (`DroneDataService`, `PortalDBService`, etc.) to `WEB-INF/lib/dronereconportal.jar`.
4. Start the Tomcat server.
5. Access the drone interface via a browser (e.g., http://localhost:8080/dronerecon/drone_launch.jsp).
6. Use the portal at (e.g., http://localhost:8080/dronereconportal/areasearch.jsp).

---

## Part 1 – Drone Communication

### Files:

- `drone_launch.jsp`
- `drone_sim.jsp`
- `DroneDataService.java` (Servlet)

### Description:

- Simulates a drone flying over a grid.
- Each tile’s position and RGB data are collected and passed to the servlet.
- The servlet decides the drone’s next direction.
- The servlet also sends tile data to the portal backend (Part 2) using HTTP requests.


---

## Part 2 – Drone Data Portal

### Files:

- `PortalDBService.java`
- `AreaGridTile.java`
- `areasearch.jsp`
- `arearesults.jsp`

### Description:

- The portal receives drone data via REST and stores it in an SQLite database.
- Users can enter an `area_id` to query data.
- Displays:
  - The tile with the highest red value.
  - The tile with the highest green value.
