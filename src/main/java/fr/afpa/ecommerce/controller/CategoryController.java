package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Category;
import fr.afpa.ecommerce.model.CategoryModel;
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
 * @category Riad YOUSFI
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/Admin/Category"})
public class CategoryController extends HttpServlet {

    private final String categoryForm = "/WEB-INF/admin/CategoryForm.jsp";
    private final String categoryIndex = "/WEB-INF/admin/CategoryIndex.jsp";

    private CategoryModel categoryModel;

    @Override
    public void init() throws ServletException {
        super.init();
        categoryModel = new CategoryModel();
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
                    request.setAttribute("categories", categoryModel.findAll());
                    request.getRequestDispatcher(categoryForm).forward(request, response);
                    break;
                case "edit":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Category category = categoryModel.find(id);

                        if (category != null) {
                            request.setAttribute("category", category);
                            request.setAttribute("categories", categoryModel.findAll());
                            request.getRequestDispatcher(categoryForm).forward(request, response);

                        } else {
                            goIndex(request, response, new Alert(AlertStyle.DANGER, "La categorie que vous recherchez n'existe pas."));
                        }

                    } catch (NumberFormatException e) {
                        goIndex(request, response, new Alert(AlertStyle.DANGER, "La categorie que vous recherchez n'existe pas."));
                    }
                    break;
                case "delete":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    categoryModel.delete(id);
                    goIndex(request, response, new Alert("Categorie supprimé avec succes"));
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

        Category category = new Category();
        Integer id = null;
        Integer parentCategoryId;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            category.setId(id);
        } catch (NumberFormatException e) {
        }

        try {
            parentCategoryId = Integer.parseInt(request.getParameter("parent_category_id"));
            category.setParentCategoryId(parentCategoryId);
        } catch (NumberFormatException e) {
        }

        try {
            Validator.required(name);
            category.setName(name);
        } catch (Exception e) {
            errors.put("name", e.getMessage());
        }

        try {
            if (!errors.isEmpty()) {
                request.setAttribute("categories", categoryModel.findAll());
                request.setAttribute("category", category);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher(categoryForm).forward(request, response);

            } else {
                Alert alert;
                if (id != null) {
                    categoryModel.update(category);
                    alert = new Alert("Category modifié avec succes");
                } else {
                    categoryModel.save(category);
                    alert = new Alert("Category ajouté avec succes");
                }
                goIndex(request, response, alert);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            goIndex(request, response, new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response, Alert alert) throws IOException, ServletException {
        List<Category> categories = new ArrayList<>();

        try {
            categories = categoryModel.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("alert", new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

        request.setAttribute("alert", alert);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher(categoryIndex).forward(request, response);
    }

}
