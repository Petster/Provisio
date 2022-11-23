package com.csd.beta.provisio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.csd.beta.provisio.entity.Location;
import com.csd.beta.provisio.entity.Reservation;
import com.csd.beta.provisio.entity.Room;
import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.repo.LocationRepository;
import com.csd.beta.provisio.repo.ReservationRepository;
import com.csd.beta.provisio.repo.RoomRepository;
import com.csd.beta.provisio.repo.UserRepository;
import com.csd.beta.provisio.util.Logger;
import com.google.gson.JsonObject;

@WebServlet("/Reserve")
public class Reserve extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RoomRepository roomRepository;
    private final LocationRepository locationRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final Logger logger;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reserve() {
        super();
        roomRepository = new RoomRepository();
        locationRepository = new LocationRepository();
        reservationRepository = new ReservationRepository();
        userRepository = new UserRepository();
        logger = new Logger(Reserve.class.getSimpleName());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        List<Room> allRooms = roomRepository.getAll();
        List<Location> allLocations = locationRepository.getAll();

        request.setAttribute("allLocations", allLocations);
        request.setAttribute("allRooms", allRooms);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final PrintWriter out = response.getWriter();
        final JsonObject myObj = new JsonObject();
        final Map<String, String> submitData = new HashMap<>();
        response.setContentType("application/json");

        try {
            User LoggedIn = (User)session.getAttribute("LoggedIn");
            submitData.put("location", request.getParameter("location"));
            submitData.put("guests", request.getParameter("guests"));
            submitData.put("price", request.getParameter("price"));
            submitData.put("fromDate", request.getParameter("fromDate"));
            submitData.put("toDate", request.getParameter("toDate"));
            submitData.put("id", request.getParameter("roomType"));
            submitData.put("loyalty", request.getParameter("loyalty"));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();

            Reservation newRes = new Reservation(LoggedIn.getId(), Long.parseLong(submitData.get("id")), Long.parseLong(submitData.get("location")), Integer.parseInt(submitData.get("guests")), dtf.format(now), submitData.get("fromDate"), submitData.get("toDate"), Integer.parseInt(submitData.get("price")));

            int previousPoints = LoggedIn.getLoyaltyPoints();
            int newPoints = previousPoints + Integer.parseInt(submitData.get("loyalty"));
            LoggedIn.setLoyaltyPoints(newPoints);
            userRepository.updateById(LoggedIn, LoggedIn.getId());

            Reservation created = reservationRepository.insertOne(newRes);
            if (created == null) {
                throw new ProvisioException("Unable to save the repository");
            }

            myObj.addProperty("success", true);
            myObj.addProperty("msg", "Your reservation was created successfully");

        } catch (Exception e) {
            logger.e("doPost", e);
            myObj.addProperty("success", false);
            myObj.addProperty("msg", e.getMessage());
        }

        out.println(myObj);
        out.close();
        doGet(request, response);
    }

}