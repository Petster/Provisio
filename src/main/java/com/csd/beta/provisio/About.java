package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.Location;
import com.csd.beta.provisio.repo.LocationRepository;
import com.csd.beta.provisio.repo.ReservationRepository;
import com.csd.beta.provisio.repo.RoomRepository;
import com.csd.beta.provisio.repo.UserRepository;
import com.csd.beta.provisio.util.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "About", value = "/About")
public class About extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final LocationRepository locationRepository;
    private final Logger logger;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public About() {
        super();
        locationRepository = new LocationRepository();
        logger = new Logger(Reserve.class.getSimpleName());
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Location> allLocations = locationRepository.getAll();

        request.setAttribute("allLocations", allLocations);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
