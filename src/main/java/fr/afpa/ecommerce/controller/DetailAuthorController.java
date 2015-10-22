package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Book;
import fr.afpa.ecommerce.model.AuthorModel;
import fr.afpa.ecommerce.model.BookModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetailAuthorController", urlPatterns = {"/Author"})
public class DetailAuthorController extends HttpServlet {

    private final String authorPage = "/WEB-INF/default/Author.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        AuthorModel authorModel = new AuthorModel();
        BookModel bookModel = new BookModel();

        try {

            request.setAttribute("author", authorModel.detailAuthor(id));
            request.setAttribute("books", bookModel.findBooksByAuthor(id));

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DetailBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher(authorPage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
