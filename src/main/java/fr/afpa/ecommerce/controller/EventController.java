package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Event;
import fr.afpa.ecommerce.model.BookModel;
import fr.afpa.ecommerce.model.EventModel;
import fr.afpa.ecommerce.util.Alert;
import fr.afpa.ecommerce.util.AlertStyle;
import fr.afpa.ecommerce.util.Validator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Riad YOUSFI
 */
@WebServlet(name = "EventController", urlPatterns = {"/Admin/Event"})
public class EventController extends HttpServlet {

    private final String eventForm = "/WEB-INF/admin/EventForm.jsp";
    private final String eventIndex = "/WEB-INF/admin/EventIndex.jsp";
    private EventModel eventModel;
    private BookModel bookModel;

    @Override
    public void init() throws ServletException {
        super.init();
        eventModel = new EventModel();
        bookModel = new BookModel();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String action = request.getParameter("action");

            if (action == null) {
                action = "index";
            }

            switch (action) {
                case "add":
                    request.setAttribute("books", bookModel.findAll());
                    request.getRequestDispatcher(eventForm).forward(request, response);
                    break;
                case "edit":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));

                        Event event = eventModel.find(id);
                        if (event != null) {
                            request.setAttribute("bookEvents", eventModel.findBooks(id));
                            request.setAttribute("books", bookModel.findAll());
                            request.setAttribute("event", event);
                            request.getRequestDispatcher(eventForm).forward(request, response);

                        } else {
                            goIndex(request, response, new Alert(AlertStyle.DANGER, "La evente que vous recherchez n'existe pas"));
                        }
                    } catch (NumberFormatException e) {
                        goIndex(request, response, new Alert(AlertStyle.DANGER, "La evente que vous recherchez n'existe pas"));
                    }
                    break;
                case "delete":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    eventModel.delete(id);
                    goIndex(request, response, new Alert("Evente supprimé avec succes"));
                    break;
                case "index":
                    goIndex(request, response, null);
                    break;

            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            goIndex(request, response, new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> errors = new HashMap<>();

        String[] bookIds = request.getParameterValues("bookIds");
        String name = request.getParameter("name").trim();
        String startDate = request.getParameter("startDate").trim();
        String endDate = request.getParameter("endDate").trim();

        Event event = new Event();
        Integer id = null;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            event.setId(id);
        } catch (NumberFormatException e) {
        }

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

        try {
            event.setBookIds(Validator.integerList(bookIds, true));
        } catch (Exception e) {
            errors.put("bookIds", e.getMessage());
        }

        try {

            if (!errors.isEmpty()) {
                request.setAttribute("event", event);
                request.setAttribute("books", bookModel.findAll());
                if (id != null) {
                    request.setAttribute("bookEvents", eventModel.findBooks(id));
                }
                request.setAttribute("errors", errors);
                request.getRequestDispatcher(eventForm).forward(request, response);

            } else {
                Alert alert;
                if (id != null) {
                    eventModel.update(event);
                    eventModel.addBooks(event);
                    alert = new Alert("Evenement modifié avec succes");
                } else {
                    Integer eventId = eventModel.save(event);
                    event.setId(eventId);
                    eventModel.addBooks(event);
                    alert = new Alert("Evenement ajouté avec succes");
                }

                goIndex(request, response, alert);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            goIndex(request, response, new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response, Alert alert) throws IOException, ServletException {
        List<Event> events = new ArrayList<>();

        try {
            events = eventModel.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("alert", new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

        request.setAttribute("alert", alert);
        request.setAttribute("events", events);
        request.getRequestDispatcher(eventIndex).forward(request, response);
    }

}
