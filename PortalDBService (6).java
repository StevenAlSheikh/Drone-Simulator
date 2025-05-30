package com.dronerecon.ws;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PortalDBService extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ############
        // Get 5 parameter values from the request object (these are passed in from part 1 code).
        // "area_id" : String type
        // "tilex" : int type (Reference part 1 of Project 2 for converting string to int).
        // "tiley"
        // "r"
        // "g"
        // ############

        // all 5 parameter variables will be set as strings variables initially.
        // 4 of them will be converted into integers.
        String sAreaID = request.getParameter("area_id");
        String sTilex = request.getParameter("tilex");
        String sTiley = request.getParameter("tiley");
        String sR = request.getParameter("r");
        String sG = request.getParameter("g");


        // these are 4 int variables that converted 4 of the above string variables
        // into integers.
        int iTilex = Integer.parseInt(sTilex);
        int iTiley = Integer.parseInt(sTiley);
        int iR = Integer.parseInt(sR);
        int iG = Integer.parseInt(sG);


        // ############
        // Instantiate a DBManager instance.
        // ############

        DBManager oDBManager = new DBManager();

        // Set DB location (Currently uses current DB file name and adds direct path from C drive before it).
        //TODO: COMMENTED OUT DBLocation ,  path already set inside DBManager
        /**
         // oDBManager.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\" + oDBManager.DBLocation;
         */
        //*** IMPORTANT: For Mac Users, comment out the above and use below line:
        //oDBManager.DBLocation = System.getProperty("catalina.base") + "/webapps/dronereconportal/db/" + oDBManager.DBLocation;


        // ############
        // Call insertAreaGridTile on db manager object and pass the 5 values from above.
        // ############
        oDBManager.insertAreaGridTile(sAreaID, iTilex, iTiley, iR, iG);

        // Response with confirmation of DB record written.
        out.println("{\"success\":true}");
    }
}



