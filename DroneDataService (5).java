package com.dronerecon.ws;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

/**
 *
 * @author Steven Al-Sheikh
 */
public class DroneDataService extends HttpServlet{


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        // ##############################
        // 1. Get params passed in.

        // Get the following parameters from the request object and put them into strings:
        // area_id
        // tilex
        // tiley
        // totalcols
        // totalrows
        // r
        // g
        // ##############################
        //
        String sAreaID = request.getParameter("area_id");
        String sTilex = request.getParameter("tilex");
        String sTiley = request.getParameter("tiley");
        String sTotalcols = request.getParameter("totalcols");
        String sTotalrows = request.getParameter("totalrows");
        String sR = request.getParameter("r");
        String sG = request.getParameter("g");

        try {
            URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=" +
                    sAreaID + "&tilex=" + sTilex + "&tiley=" + sTiley + "&r=" + sR + "&g=" + sG + "");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        // ##############################
        // 2. Default value of beginning direction.

        // Set a string called sDirection to "right".
        // ##############################
        String sDirection = "right";


        // ##############################
        // 3. Calculate next drone move.

        // Determine next tile to move to.
        // Base this on current x and y.
        // Change sDirection if necessary.
        // Drone must serpentine from top left of grid back and forth down.
        // If rows are done, change sDirection to "stop".
        // ##############################

        int iTilex = Integer.parseInt(sTilex);
        int iTiley = Integer.parseInt(sTiley);
        int iTotalcols = Integer.parseInt(sTotalcols);
        int iTotalrows = Integer.parseInt(sTotalrows);


        if (iTiley % 2 == 0) {
            if (iTilex == iTotalcols - 1) {
                iTiley++;
                sDirection = "left";
            } else {
                iTilex++;
                sDirection = "right";
            }
        }
        else if (iTilex == 0) {
            iTiley++;
            sDirection = "right";
        } else {
            iTilex--;
            sDirection = "left";
        }
        if(iTiley == iTotalrows){
            sDirection = "stop";
        }

        // ##############################
        // 4. Format & Return JSON string to caller.

        /* Return via out.println() a JSON string like this:
        {"area_id":"[area id from above]", "nextTileX":"[next tile x]", "nextTileY":"[next tile y]", "direction":"[direction string from above]"}
        */
        // ##############################
        String sReturnJson = "{\"area_id\":\"" + sAreaID + "\",\"nextTileX\":\"" + iTilex +"\",\"nextTileY\":\"" + iTiley +"\",\"direction\":\"" + sDirection +"\"}";
        out.println(sReturnJson);
    }
}