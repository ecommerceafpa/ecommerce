package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Tax;
import fr.afpa.ecommerce.util.Alert;
import fr.afpa.ecommerce.util.AlertStyle;
import fr.afpa.ecommerce.util.Validator;
import java.io.IOException;
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
 * @tax Riad YOUSFI
 */
@WebServlet(name = "TaxController", urlPatterns = {"/Admin/Tax"})
public class TaxController extends HttpServlet {

    private final String taxForm = "/WEB-INF/admin/TaxForm.jsp";
    private final String taxIndex = "/WEB-INF/admin/TaxIndex.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "index";
        }

        switch (action) {
            case "add":
                request.getRequestDispatcher(taxForm).forward(request, response);
                break;
            case "edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));

                    //FindById Tax from database
                    Tax tax = new Tax(id, "TVA", 19.6);
                    request.setAttribute("tax", tax);
                    request.getRequestDispatcher(taxForm).forward(request, response);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertStyle.DANGER, "La taxe que vous recherchez n'existe pas");
                    request.setAttribute("alert", alert);
                    goIndex(request, response);
                }
                break;
            case "delete":
                //Delete Tax from database
                request.setAttribute("alert", new Alert("Taxe supprimé avec succes"));
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
        String value = request.getParameter("value").trim();

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Tax tax = new Tax();

        try {
            Validator.required(name);
            tax.setName(name);
        } catch (Exception e) {
            errors.put("name", e.getMessage());
        }

        try {
            tax.setValue(Validator.numberDouble(value, true));
        } catch (Exception e) {
            errors.put("value", e.getMessage());
        }

        if (!errors.isEmpty()) {
            request.setAttribute("tax", tax);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(taxForm).forward(request, response);

        } else {
            if (id > 0) {
                //Update Tax from database
                request.setAttribute("alert", new Alert("Taxe modifié avec succes"));
            } else {
                //Insert Tax from database
                request.setAttribute("alert", new Alert("Taxe ajouté avec succes"));
            }

            goIndex(request, response);
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Tax> taxes = findAllTaxes();
        request.setAttribute("taxes", taxes);
        request.getRequestDispatcher(taxIndex).forward(request, response);
    }

    private List<Tax> findAllTaxes() {
        //FindAll Tax from database
        List<Tax> taxes = new ArrayList<>();
        taxes.add(new Tax(1, "TVA", 19.6));
        return taxes;
    }

}
