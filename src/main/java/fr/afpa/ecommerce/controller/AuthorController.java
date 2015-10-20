package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Author;
import fr.afpa.ecommerce.model.AuthorModel;
import fr.afpa.ecommerce.util.Alert;
import fr.afpa.ecommerce.util.AlertStyle;
import fr.afpa.ecommerce.util.Validator;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "AuthorController", urlPatterns = {"/Admin/Author"})
public class AuthorController extends HttpServlet {

    private final String authorForm = "/WEB-INF/admin/AuthorForm.jsp";
    private final String authorIndex = "/WEB-INF/admin/AuthorIndex.jsp";
    private AuthorModel authorModel;

    @Override
    public void init() throws ServletException {
        super.init();
        authorModel = new AuthorModel();
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
                    request.getRequestDispatcher(authorForm).forward(request, response);
                    break;
                case "edit":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Author author = authorModel.find(id);
                        if (author != null) {
                            request.setAttribute("author", author);
                            request.getRequestDispatcher(authorForm).forward(request, response);
                        } else {
                            goIndex(request, response, new Alert(AlertStyle.DANGER, "L'auteur que vous recherchez n'existe pas."));
                        }
                    } catch (NumberFormatException e) {
                        goIndex(request, response, new Alert(AlertStyle.DANGER, "L'auteur que vous recherchez n'existe pas."));
                    }
                    break;
                case "delete":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    authorModel.delete(id);
                    goIndex(request, response, new Alert("Auteur supprimé avec succes."));

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

        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String portrait = request.getParameter("portrait").trim();

        Author author = new Author();
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            author.setId(id);
        } catch (Exception e) {
        }

        try {
            Validator.required(firstName);
            author.setFirstName(firstName);
        } catch (Exception e) {
            errors.put("firstNameError", e.getMessage());
        }

        try {
            Validator.required(lastName);
            author.setLastName(lastName);
        } catch (Exception e) {
            errors.put("lastNameError", e.getMessage());
        }

        try {
            Validator.required(portrait);
            author.setPortrait(portrait);
        } catch (Exception e) {
            errors.put("portraitError", e.getMessage());
        }
        try {
            if (!errors.isEmpty()) {
                request.setAttribute("author", author);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher(authorForm).forward(request, response);

            } else {
                Alert alert;
                if (id != null) {
                    authorModel.update(author);
                    alert = new Alert("Auteur modifié avec succes.");
                } else {
                    authorModel.save(author);
                    alert = new Alert("Auteur ajouté avec succes.");
                }

                goIndex(request, response, alert);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            goIndex(request, response, new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response, Alert alert) throws IOException, ServletException {
        List<Author> authors = null;
        try {
            authors = authorModel.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            alert = new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite.");
        }
        request.setAttribute("alert", alert);
        request.setAttribute("authors", authors);
        request.getRequestDispatcher(authorIndex).forward(request, response);
    }

}
