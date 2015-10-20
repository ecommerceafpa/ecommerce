package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.model.BookModel;
import fr.afpa.ecommerce.model.CategoryModel;
import fr.afpa.ecommerce.model.EventModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Riad YOUSFI
 */
@WebServlet(name = "HomeController", urlPatterns = {"/Home"})
public class HomeController extends HttpServlet {

    private final String homePage = "/WEB-INF/default/Index.jsp";
    private CategoryModel categoryModel;
    private EventModel eventModel;
    private BookModel bookModel;

    @Override
    public void init() throws ServletException {
        super.init();
        categoryModel = new CategoryModel();
        eventModel = new EventModel();
        bookModel = new BookModel();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("events", eventModel.findCurrentEvent());
            request.setAttribute("categories", categoryModel.findParentCategories());
            request.setAttribute("books", bookModel.findBookByEvent());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(homePage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
