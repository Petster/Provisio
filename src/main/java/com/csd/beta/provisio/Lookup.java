package com.csd.beta.provisio;

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
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "Lookup", value = "/Lookup")
public class Lookup extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RoomRepository roomRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final Logger logger;

    public Lookup() {
        super();
        roomRepository = new RoomRepository();
        locationRepository = new LocationRepository();
        reservationRepository = new ReservationRepository();
        userRepository = new UserRepository();
        logger = new Logger(MyAccount.class.getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> allRooms = roomRepository.getAll();
        List<Location> allLocations = locationRepository.getAll();

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
        User LoggedIn = (User)session.getAttribute("LoggedIn");

        final Map<String, String> submitData = new HashMap<>();
        submitData.put("reservation", request.getParameter("resnum"));
        submitData.put("email", request.getParameter("email"));

        try {
            User foundUser = userRepository.getUserByUserName(submitData.get("email"));

            if (foundUser == null || Objects.equals(foundUser.getEmail(), "")) { // no auth
                throw new ProvisioException.LoginException("No account exists for this email");
            }

            if(submitData.get("reservation") != null) {
                //get by id
                Reservation foundRes = reservationRepository.getById(Long.parseLong(submitData.get("reservation")));
                if(foundRes == null) {
                    throw new ProvisioException.ReservationRepositoryException("Reservation not found for that number");
                }

                if(foundRes.getUserId() != foundUser.getId()) {
                    throw new ProvisioException.ReservationRepositoryException("That reservation does not belong to the provided email");
                }

                Location foundLoc = locationRepository.getById(foundRes.getLocation());
                Room foundRoom = roomRepository.getById(foundRes.getRoomType());

                myObj.addProperty("success", true);
                String reserverationToJSON = new Gson().toJson(foundRes);
                String locationToJSON = new Gson().toJson(foundLoc);
                String roomToJSON = new Gson().toJson(foundRoom);
                myObj.addProperty("reservation", reserverationToJSON);
                myObj.addProperty("location", locationToJSON);
                myObj.addProperty("room", roomToJSON);
            } else {
                throw new ProvisioException.ReservationRepositoryException("Could not find a reservation for that number");
            }

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
