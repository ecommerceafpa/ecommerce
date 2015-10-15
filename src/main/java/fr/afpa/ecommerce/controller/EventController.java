package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Event;
import fr.afpa.ecommerce.util.Alert;
import fr.afpa.ecommerce.util.AlertStyle;
import fr.afpa.ecommerce.util.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @event Riad YOUSFI
 */
@WebServlet(name = "EventController", urlPatterns = {"/Admin/Event"})
public class EventController extends HttpServlet {

    private final String eventForm = "/WEB-INF/admin/EventForm.jsp";
    private final String eventIndex = "/WEB-INF/admin/EventIndex.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "index";
        }

        switch (action) {
            case "add":
                request.getRequestDispatcher(eventForm).forward(request, response);
                break;
            case "edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));

                    Calendar startDate = Calendar.getInstance();
                    startDate.set(15, 8, 1);

                    Calendar endDate = Calendar.getInstance();
                    endDate.set(2015, 9, 30);

                    //FindById Event from database
                    Event event = new Event(1, "Objectif Web", new java.sql.Date(115, 9, 1), new java.sql.Date(115, 9, 30));
                    request.setAttribute("event", event);
                    request.getRequestDispatcher(eventForm).forward(request, response);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertStyle.DANGER, "La evente que vous recherchez n'existe pas");
                    request.setAttribute("alert", alert);
                    goIndex(request, response);
                }
                break;
            case "delete":
                //Delete Event from database
                request.setAttribute("alert", new Alert("Evente supprimé avec succes"));
                goIndex(request, response);
                break;
            case "index":
                goIndex(request, response);
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String name = request.getParameter("name").trim();
        String startDate = request.getParameter("startDate").trim();
        String endDate = request.getParameter("endDate").trim();

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Event event = new Event();

        try {
            Validator.required(name);
            event.setName(name);
        } catch (Exception e) {
            errors.put("name", e.getMessage());
        }

        try {
            event.setStartDate(Validator.date(startDate, "dd/MM/yyyy", true));
        } catch (Exception e) {
            errors.put("startDate", e.getMessage());
        }

        try {
            event.setEndDate(Validator.date(endDate, "dd/MM/yyyy", true));
            try {
                Validator.dateCompareBefore(Validator.date(startDate, "dd/MM/yyyy", true), Validator.date(endDate, "dd/MM/yyyy", true));
            } catch (Exception e) {
                errors.put("endDate", e.getMessage());
            }
        } catch (Exception e) {
            errors.put("endDate", e.getMessage());
        }

        if (!errors.isEmpty()) {
            request.setAttribute("event", event);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(eventForm).forward(request, response);

        } else {
            if (id > 0) {
                //Update Event from database
                request.setAttribute("alert", new Alert("Evenement modifié avec succes"));
            } else {
                //Insert Event from database
                request.setAttribute("alert", new Alert("Evenement ajouté avec succes"));
            }

            goIndex(request, response);
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Event> events = findAllEvents();
        request.setAttribute("events", events);
        request.getRequestDispatcher(eventIndex).forward(request, response);
    }

    private List<Event> findAllEvents() {
        //FindAll Event from database
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, "Objectif Web", new java.sql.Date(115, 9, 1), new java.sql.Date(115, 9, 30)));
        return events;
    }

}
