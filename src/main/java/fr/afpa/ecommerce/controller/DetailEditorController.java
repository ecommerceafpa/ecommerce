package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.model.BookModel;
import fr.afpa.ecommerce.model.CategoryModel;
import fr.afpa.ecommerce.model.EditorModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetailEditorController", urlPatterns = {"/Editor"})
public class DetailEditorController extends HttpServlet {

    private final String editorPage = "/WEB-INF/default/Editor.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        EditorModel editorModel = new EditorModel();
        BookModel bookModel = new BookModel();
        CategoryModel categoryModel = new CategoryModel();

        try {
            request.setAttribute("categories", categoryModel.findParentCategories());
            request.setAttribute("editor", editorModel.find(id));
            request.setAttribute("books", bookModel.findBooksByEditor(id));

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DetailBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(editorPage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
