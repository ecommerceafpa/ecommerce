package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Tax;
import fr.afpa.ecommerce.model.TaxModel;
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
 * @tax Riad YOUSFI
 */
@WebServlet(name = "TaxController", urlPatterns = {"/Admin/Tax"})
public class TaxController extends HttpServlet {

    private final String taxForm = "/WEB-INF/admin/TaxForm.jsp";
    private final String taxIndex = "/WEB-INF/admin/TaxIndex.jsp";
    private TaxModel taxModel;

    @Override
    public void init() throws ServletException {
        super.init();
        taxModel = new TaxModel();
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
                    request.getRequestDispatcher(taxForm).forward(request, response);
                    break;
                case "edit":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Tax tax = taxModel.find(id);
                        if (tax != null) {
                            request.setAttribute("tax", tax);
                            request.getRequestDispatcher(taxForm).forward(request, response);

                        } else {
                            goIndex(request, response, new Alert(AlertStyle.DANGER, "La taxe que vous recherchez n'existe pas"));

                        }
                    } catch (NumberFormatException e) {
                        goIndex(request, response, new Alert(AlertStyle.DANGER, "La taxe que vous recherchez n'existe pas"));
                    }
                    break;
                case "delete":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    taxModel.delete(id);
                    goIndex(request, response, new Alert("Taxe supprimé avec succes"));
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

        String name = request.getParameter("name").trim();
        String value = request.getParameter("value").trim();

        Tax tax = new Tax();
        Integer id = null;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            tax.setId(id);
        } catch (NumberFormatException e) {
        }

        try {
            Validator.required(name);
            tax.setName(name);
        } catch (Exception e) {
            errors.put("name", e.getMessage());
        }

        try {
            tax.setValue(Validator.decimal(value, true));
        } catch (Exception e) {
            errors.put("value", e.getMessage());
        }

        try {

            if (!errors.isEmpty()) {
                request.setAttribute("tax", tax);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher(taxForm).forward(request, response);

            } else {
                Alert alert;

                if (id != null) {
                    taxModel.update(tax);
                    alert = new Alert("Taxe modifié avec succes");
                } else {
                    taxModel.save(tax);
                    alert = new Alert("Taxe ajouté avec succes");
                }

                goIndex(request, response, alert);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            goIndex(request, response, new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response, Alert alert) throws IOException, ServletException {
        List<Tax> taxes = new ArrayList();

        try {
            taxes = taxModel.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("alert", new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

        request.setAttribute("alert", alert);
        request.setAttribute("taxes", taxes);
        request.getRequestDispatcher(taxIndex).forward(request, response);
    }

}
