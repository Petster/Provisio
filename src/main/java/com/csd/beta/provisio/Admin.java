package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.*;
import com.csd.beta.provisio.repo.*;
import com.csd.beta.provisio.util.Logger;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Admin", value = "/Admin")
public class Admin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RoomRepository roomRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final ReservationRepository reservationRepository;
    private final Logger logger;

    public Admin() {
        super();
        roomRepository = new RoomRepository();
        locationRepository = new LocationRepository();
        reservationRepository = new ReservationRepository();
        userRepository = new UserRepository();
        newsRepository = new NewsRepository();
        logger = new Logger(MyAccount.class.getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> allUsers = userRepository.getAll();
        List<Reservation> allReservations = reservationRepository.getAll();
        request.setAttribute("allReservations", allReservations);
        request.setAttribute("allUsers", allUsers);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        JsonObject myObj = new JsonObject();
        ArrayList<String> submitData = new ArrayList<String>();
        User LoggedIn = (User)session.getAttribute("LoggedIn");

        switch(query) {
            case "cLocation":
                //System.out.println("location");
                submitData.add(request.getParameter("locationTitle"));
                submitData.add(request.getParameter("locationAddress"));
                Location newLocation = new Location(submitData.get(1), submitData.get(0));

                try {
                    locationRepository.insertOne(newLocation);
                    myObj.addProperty("success", true);
                    myObj.addProperty("msg", "Location Added");

                    out.println(myObj);
                    out.close();
                } catch (Exception e) {
                    myObj.addProperty("success", false);
                    myObj.addProperty("msg", e.getMessage());

                    out.println(myObj);
                    out.close();
                }
                break;
            case "cNews":
                //System.out.println("news");
                submitData.add(request.getParameter("newsTitle"));
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                submitData.add(dtf.format(now));
                submitData.add(request.getParameter("newsDesc"));
                submitData.add(request.getParameter("newsImg"));

                News newNews = new News(LoggedIn.getId(), submitData.get(0), submitData.get(1), submitData.get(2), submitData.get(3));

                try {
                    newsRepository.insertOne(newNews);
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
                break;
            case "cRoom":
                //System.out.println("room");
                submitData.add(request.getParameter("roomTitle"));
                int roomPrice = Integer.parseInt(request.getParameter("roomPrice"));
                submitData.add(request.getParameter("roomImg"));
                submitData.add(request.getParameter("roomDesc"));
                submitData.add(request.getParameter("roomLoyalty"));

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

                Room newRoom = new Room(submitData.get(0), finalAmmen.get(0),  finalAmmen.get(1),  finalAmmen.get(2),  finalAmmen.get(3),  finalAmmen.get(4),  finalAmmen.get(5),  submitData.get(2),  submitData.get(1), roomPrice, Integer.parseInt(submitData.get(3)));

                try {
                    roomRepository.insertOne(newRoom);
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
                break;
            case "dRes":
                long delID = Long.parseLong(request.getParameter("delid"));
                try {
                    Reservation current = reservationRepository.getById(delID);
                    Room currentRoom = roomRepository.getById(current.getRoomType());

                    int newPoints = LoggedIn.getLoyaltyPoints() - currentRoom.getLoyaltyPoints();
                    LoggedIn.setLoyaltyPoints(newPoints);
                    userRepository.updateById(LoggedIn, LoggedIn.getId());

                    reservationRepository.deleteById(delID);

                    myObj.addProperty("success", true);
                    myObj.addProperty("msg", "Reservation was cancelled");
                    out.println(myObj);
                    out.close();
                } catch (Exception e) {
                    logger.e("doPost", e);
                    myObj.addProperty("success", false);
                    myObj.addProperty("msg", e.getMessage());
                    out.println(myObj);
                    out.close();
                }
                break;
            default:
                myObj.addProperty("success", false);
                myObj.addProperty("msg", "not entered through form");

                out.println(myObj);
                out.close();
                break;
        }

        doGet(request, response);
    }
}
