package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.News;
import com.csd.beta.provisio.entity.Room;
import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.repo.NewsRepository;
import com.csd.beta.provisio.repo.RoomRepository;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(name = "Admin", value = "/Admin")
public class Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        JsonObject myObj = new JsonObject();

        if(query.equals("cNews")) {
            ArrayList<String> submitData = new ArrayList<String>();
            User LoggedIn = (User)session.getAttribute("LoggedIn");
            submitData.add(request.getParameter("newsTitle"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            submitData.add(dtf.format(now));
            submitData.add(request.getParameter("newsDesc"));
            submitData.add(request.getParameter("newsImg"));

            News newNews = new News(LoggedIn.getId(), submitData.get(0), submitData.get(1), submitData.get(2), submitData.get(3));

            NewsRepository NR = new NewsRepository();

            try {
                NR.insertOne(newNews);
                myObj.addProperty("success", true);
                myObj.addProperty("msg", "News Article Added");

                out.println(myObj);
                out.close();
            } catch (Exception e) {
                myObj.addProperty("success", false);
                myObj.addProperty("msg", e.getMessage());

                out.println(myObj);
                out.close();
            }

        } else if(query.equals("cRoom")) {
            ArrayList<String> submitData = new ArrayList<String>();
            submitData.add(request.getParameter("roomTitle"));
            int roomPrice = Integer.parseInt(request.getParameter("roomPrice"));
            submitData.add(request.getParameter("roomImg"));
            submitData.add(request.getParameter("roomDesc"));

            ArrayList<String> ammenities = new ArrayList<>();
            ammenities.add(request.getParameter("breakfast"));
            ammenities.add(request.getParameter("wifi"));
            ammenities.add(request.getParameter("fitness"));
            ammenities.add(request.getParameter("store"));
            ammenities.add(request.getParameter("nosmoke"));
            ammenities.add(request.getParameter("mobile"));
            ArrayList<Boolean> finalAmmen = new ArrayList<>();

            for(int i = 0; i <ammenities.size(); i++) {
                if(ammenities.get(i) != null) {
                    finalAmmen.add(true);
                } else {
                    finalAmmen.add(false);
                }
            }

            Room newRoom = new Room(submitData.get(0), finalAmmen.get(0),  finalAmmen.get(1),  finalAmmen.get(2),  finalAmmen.get(3),  finalAmmen.get(4),  finalAmmen.get(5),  submitData.get(2),  submitData.get(1), roomPrice);

            RoomRepository RR = new RoomRepository();

            try {
                RR.insertOne(newRoom);
                myObj.addProperty("success", true);
                myObj.addProperty("msg", "Room Added");

                out.println(myObj);
                out.close();
            } catch (Exception e) {
                myObj.addProperty("success", false);
                myObj.addProperty("msg", e.getMessage());

                out.println(myObj);
                out.close();
            }
        } else {
            System.out.println("Not Entered Through Form");
        }

        doGet(request, response);
    }
}
