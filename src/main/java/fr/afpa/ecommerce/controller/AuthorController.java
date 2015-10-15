package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Author;
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
 * @author Riad YOUSFI
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/Admin/Author"})
public class AuthorController extends HttpServlet {

    private final String authorForm = "/WEB-INF/admin/AuthorForm.jsp";
    private final String authorIndex = "/WEB-INF/admin/AuthorIndex.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                    int id = Integer.parseInt(request.getParameter("id"));

                    //FindById Category from database
                    Author author = new Author("Riad", "YOUSFI", "Mon portrait");
                    author.setId(id);
                    request.setAttribute("author", author);
                    request.getRequestDispatcher(authorForm).forward(request, response);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertStyle.DANGER, "L'auteur que vous recherchez n'existe pas");
                    request.setAttribute("alert", alert);
                    goIndex(request, response);
                }
                break;
            case "delete":
                //Delete Author from database
                request.setAttribute("alert", new Alert("Auteur supprimé avec succes"));
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

        String firstName = request.getParameter("firstName").trim();
        String lastName = request.getParameter("lastName").trim();
        String portrait = request.getParameter("portrait").trim();
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            id = 0;
        }

        Author author = new Author();

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
            author.setPortrait(lastName);
        } catch (Exception e) {
            errors.put("portraitError", e.getMessage());
        }

        if (!errors.isEmpty()) {
            request.setAttribute("author", author);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(authorForm).forward(request, response);

        } else {
            if (id > 0) {
                //Update Author from database
                request.setAttribute("alert", new Alert("Auteur modifié avec succes"));
            } else {
                //Insert Author from database
                request.setAttribute("alert", new Alert("Auteur ajouté avec succes"));
            }

            goIndex(request, response);
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Author> authors = findAllAuthor();

        request.setAttribute("authors", authors);
        request.getRequestDispatcher(authorIndex).forward(request, response);
    }

    private List<Author> findAllAuthor() {
        //FindAll Author from database
        List<Author> authors = new ArrayList<>();
        authors.add(new Author(1, "Riad", "YOUSFI", "Mon portrait"));
        authors.add(new Author(2, "Adel", "YOUSFI", "Mon portrait"));
        authors.add(new Author(3, "Luqman", "YOUSFI", "Mon portrait"));
        authors.add(new Author(4, "Soumaya", "YOUSFI", "Mon portrait"));
        authors.add(new Author(5, "Sofian", "YOUSFI", "Mon portrait"));
        authors.add(new Author(6, "Riad", "YOUSFI", "Mon portrait"));
        authors.add(new Author(7, "Adel", "YOUSFI", "Mon portrait"));
        authors.add(new Author(8, "Luqman", "YOUSFI", "Mon portrait"));
        authors.add(new Author(9, "Soumaya", "YOUSFI", "Mon portrait"));
        authors.add(new Author(10, "Sofian", "YOUSFI", "Mon portrait"));
        authors.add(new Author(11, "Riad", "YOUSFI", "Mon portrait"));
        authors.add(new Author(12, "Adel", "YOUSFI", "Mon portrait"));
        authors.add(new Author(13, "Luqman", "YOUSFI", "Mon portrait"));
        authors.add(new Author(14, "Soumaya", "YOUSFI", "Mon portrait"));
        authors.add(new Author(15, "Sofian", "YOUSFI", "Mon portrait"));
        authors.add(new Author(16, "Riad", "YOUSFI", "Mon portrait"));
        authors.add(new Author(17, "Adel", "YOUSFI", "Mon portrait"));
        authors.add(new Author(18, "Luqman", "YOUSFI", "Mon portrait"));
        authors.add(new Author(19, "Soumaya", "YOUSFI", "Mon portrait"));
        authors.add(new Author(20, "Sofian", "YOUSFI", "Mon portrait"));
        return authors;
    }

}
