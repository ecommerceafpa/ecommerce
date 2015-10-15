package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Category;
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
 * @category Riad YOUSFI
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/Admin/Category"})
public class CategoryController extends HttpServlet {

    private final String categoryForm = "/WEB-INF/admin/CategoryForm.jsp";
    private final String categoryIndex = "/WEB-INF/admin/CategoryIndex.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "index";
        }

        switch (action) {
            case "add":
                request.setAttribute("categories", findAllCategories());
                request.getRequestDispatcher(categoryForm).forward(request, response);
                break;
            case "edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));

                    //FindById Category from database
                    Category category = new Category(4, "Hardware", 1);
                    request.setAttribute("category", category);
                    request.setAttribute("categories", findAllCategories());
                    request.getRequestDispatcher(categoryForm).forward(request, response);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertStyle.DANGER, "La categorie que vous recherchez n'existe pas");
                    request.setAttribute("alert", alert);
                    goIndex(request, response);
                }
                break;
            case "delete":
                //Delete Category from database
                request.setAttribute("alert", new Alert("Categorie supprimé avec succes"));
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

        int id;
        int parentCategoryId;

        try {
            parentCategoryId = Integer.parseInt(request.getParameter("parent_category_id"));
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            parentCategoryId = 0;
            id = 0;
        }

        Category category = new Category();

        try {
            Validator.required(name);
            category.setName(name);
        } catch (Exception e) {
            errors.put("name", e.getMessage());
        }

        if (parentCategoryId > 0) {
            category.setParentCategoryId(parentCategoryId);
        }

        if (!errors.isEmpty()) {
            request.setAttribute("categories", findAllCategories());
            request.setAttribute("category", category);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(categoryForm).forward(request, response);

        } else {
            if (id > 0) {
                //Update Category from database
                request.setAttribute("alert", new Alert("Category modifié avec succes"));
            } else {
                //Insert Category from database
                request.setAttribute("alert", new Alert("Category ajouté avec succes"));
            }

            goIndex(request, response);
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> categories = findAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher(categoryIndex).forward(request, response);
    }

    private List<Category> findAllCategories() {
        //FindAll Category from database
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Informatique"));
        categories.add(new Category(2, "Développement d'applications", 1));
        categories.add(new Category(3, "Informatique d'entreprise", 1));
        categories.add(new Category(4, "Hardware", 1));
        categories.add(new Category(5, "BDD", 1));
        categories.add(new Category(6, "Réseaux", 1));
        categories.add(new Category(7, "Certifications", 1));
        categories.add(new Category(8, "Graphisme & Multimedia", 1));
        categories.add(new Category(9, "Bureautique", 1));
        categories.add(new Category(10, "Audiovisuel"));
        categories.add(new Category(11, "Graphisme", 10));
        categories.add(new Category(12, "Design", 10));
        categories.add(new Category(13, "Photo", 10));
        categories.add(new Category(14, "Cinéma, Vidéo & Son", 10));
        return categories;
    }

}
