package com.csd.beta.provisio;

import com.csd.beta.provisio.entity.Email;
import com.csd.beta.provisio.entity.User;
import com.csd.beta.provisio.exception.ProvisioException;
import com.csd.beta.provisio.repo.EmailRepository;
import com.csd.beta.provisio.repo.ReservationRepository;
import com.csd.beta.provisio.repo.UserRepository;
import com.csd.beta.provisio.util.Logger;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ContactUs", value = "/ContactUs")
public class ContactUs extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final ReservationRepository reservationRepository;
    private final Logger logger;

    public ContactUs() {
        super();
        userRepository = new UserRepository();
        emailRepository = new EmailRepository();
        reservationRepository = new ReservationRepository();
        logger = new Logger(ContactUs.class.getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        final JsonObject myObj = new JsonObject();
        final Map<String, String> submitData = new HashMap<>();
        response.setContentType("application/json");

        try {
            submitData.put("name", request.getParameter("name").trim());
            submitData.put("email", request.getParameter("email").trim());
            submitData.put("phone", request.getParameter("phone").trim());
            submitData.put("resnum", request.getParameter("resnum").trim());
            submitData.put("subject", request.getParameter("subject").trim());
            submitData.put("message", request.getParameter("message").trim());

            Email newEmail = new Email();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            newEmail.setDateSent(dtf.format(now));
            newEmail.setUserFirstName(submitData.get("name"));
            newEmail.setUserEmail(submitData.get("email"));
            newEmail.setUserPhone(submitData.get("phone"));
            newEmail.setReservationNum(Long.parseLong(submitData.get("resnum")));
            newEmail.setSubject(submitData.get("subject"));
            newEmail.setMessage(submitData.get("message"));

            Email created = emailRepository.insertOne(newEmail);
            if(created == null) {
                throw new ProvisioException("Unable to send Email. Try again later");
            }

            myObj.addProperty("success", true);
            myObj.addProperty("msg", "Your email was sent");
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
