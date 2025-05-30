<%@ page import="com.dronerecon.ws.AreaGridTile" %>
<%@ page import="com.dronerecon.ws.DBManager" %>
<%@ page import="java.util.ArrayList"%>

<html>
    <body>
        <a href="areasearch.jsp">back to Area Search page</a>
        <br>
        <br>
        <table>
            <tr>
            <td>
                <%!   //note: <%!  is jsp for a block of java code to declaire variables. not 100% sure if its necessary but things work so leave it haha.
                String s_AreaID = request.getParameter("area_id");

                DBManager oDBManager = new DBManager();
                %>
                <%
                //TODO: COMMENTED OUT DBLocation ,  path already set inside DBManager
                // oDBManager.DBLocation = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\dronereconportal\\db\\dronedata.sqlite";

                 ArrayList<AreaGridTile> aTileList = oDBManager.readAreaGridTiles(s_AreaID);

                    AreaGridTile aRHigh = aTileList.get(0);
                    AreaGridTile aGHigh = aTileList.get(0);

                for(int i = 0; i < aTileList.size(); i++){

                    if(aRHigh.r < aTileList.get(i).r) {
                        aRHigh = aTileList.get(i);
                    }
                    if(aGHigh.g < aTileList.get(i).g){
                        aGHigh = aTileList.get(i);
                    }
                }
%>
            <h2> Highest Red value is: <%=aRHigh.r%>, at LOCATION: X = <%=aRHigh.x%> and Y = <%=aRHigh.y%></h2>
        <br>
        <h2> Highest Green value is: <%=aGHigh.r%>, at LOCATION: X = <%=aGHigh.x%> and Y = <%=aGHigh.y%></h2>
    </body>
</html>