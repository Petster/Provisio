package com.csd.beta.provisio;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csd.beta.provisio.entity.Location;
import com.csd.beta.provisio.entity.Room;
import com.csd.beta.provisio.repo.LocationRepository;
import com.csd.beta.provisio.repo.RoomRepository;
import com.csd.beta.provisio.util.Logger;

@WebServlet("/Reserve")
public class Reserve extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RoomRepository roomRepository;
    private final LocationRepository locationRepository;
    private final Logger logger;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reserve() {
        super();
        roomRepository = new RoomRepository();
        locationRepository = new LocationRepository();
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


        doGet(request, response);
    }

}