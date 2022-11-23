package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.Location;
import com.csd.beta.provisio.entity.Reservation;
import com.csd.beta.provisio.entity.Room;
import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.repo.LocationRepository;
import com.csd.beta.provisio.repo.ReservationRepository;
import com.csd.beta.provisio.repo.RoomRepository;
import com.csd.beta.provisio.util.Logger;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "MyAccount", value = "/MyAccount")
public class MyAccount extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final RoomRepository roomRepository;
    private final LocationRepository locationRepository;
    private final ReservationRepository reservationRepository;
    private final Logger logger;

    public MyAccount() {
        super();
        roomRepository = new RoomRepository();
        locationRepository = new LocationRepository();
        reservationRepository = new ReservationRepository();
        logger = new Logger(MyAccount.class.getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        User LoggedIn = (User)session.getAttribute("LoggedIn");
        List<Reservation> myReservations = reservationRepository.getAllById(LoggedIn.getId());
        List<Room> allRooms = roomRepository.getAll();
        List<Location> allLocations = locationRepository.getAll();
        Collections.reverse(myReservations);

        request.setAttribute("myReservations", myReservations);
        request.setAttribute("allRooms", allRooms);
        request.setAttribute("allLocations", allLocations);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getQueryString();
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        JsonObject myObj = new JsonObject();

        if(query.equals("cancelRes")) {
            final Map<String, String> submitData = new HashMap<>();
            submitData.put("reservation", request.getParameter("reservation"));

            try {
                reservationRepository.deleteById(Long.parseLong(submitData.get("reservation")));
                myObj.addProperty("success", true);
                myObj.addProperty("msg", "Your Reservation was cancelled");
            } catch (Exception e) {
                logger.e("doPost", e);
                myObj.addProperty("success", false);
                myObj.addProperty("msg", e.getMessage());
            }
        }
        out.println(myObj);
        out.close();
        doGet(request, response);
    }
}
