package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Customer;
import fr.afpa.ecommerce.model.AuthorModel;
import fr.afpa.ecommerce.model.BookModel;
import fr.afpa.ecommerce.model.CategoryModel;
import fr.afpa.ecommerce.model.CustomerModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegistrationController", urlPatterns = {"/Registration"})
public class RegistrationController extends HttpServlet {

    private final String customerPage = "/WEB-INF/default/Registration.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(customerPage).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = new Customer();
        CustomerModel customerModel = new CustomerModel();

        try {

            customer.setLastName(request.getParameter("lastname"));
            customer.setFirstName(request.getParameter("firstname"));
            customer.setUserName(request.getParameter("username"));
            customer.setPassword(request.getParameter("password"));
            customerModel.save(customer);

        } catch (SQLException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
