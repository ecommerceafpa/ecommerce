package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Book;
import fr.afpa.ecommerce.model.AuthorModel;
import fr.afpa.ecommerce.model.BookModel;
import fr.afpa.ecommerce.model.CategoryModel;
import fr.afpa.ecommerce.model.EditorModel;
import fr.afpa.ecommerce.model.LanguageModel;
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
 * @author Riad YOUSFI
 */
@WebServlet(name = "BookController", urlPatterns = {"/Admin/Book"})
public class BookController extends HttpServlet {

    private final String bookForm = "/WEB-INF/admin/BookForm.jsp";
    private final String bookIndex = "/WEB-INF/admin/BookIndex.jsp";
    private BookModel bookModel;
    private EditorModel editorModel;
    private LanguageModel languageModel;
    private TaxModel taxModel;
    private AuthorModel authorModel;
    private CategoryModel categoryModel;

    @Override
    public void init() throws ServletException {
        super.init();
        bookModel = new BookModel();
        editorModel = new EditorModel();
        languageModel = new LanguageModel();
        taxModel = new TaxModel();
        authorModel = new AuthorModel();
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
                    request.setAttribute("editors", editorModel.findAll());
                    request.setAttribute("languages", languageModel.findAll());
                    request.setAttribute("taxes", taxModel.findAll());
                    request.setAttribute("authors", authorModel.findAll());
                    request.setAttribute("categories", categoryModel.findAll());
                    request.getRequestDispatcher(bookForm).forward(request, response);
                    break;
                case "edit":
                    try {
                        Integer id = Integer.parseInt(request.getParameter("id"));
                        Book book = bookModel.find(id);
                        if (book != null) {
                            request.setAttribute("book", book);
                            request.setAttribute("editors", editorModel.findAll());
                            request.setAttribute("languages", languageModel.findAll());
                            request.setAttribute("taxes", taxModel.findAll());
                            request.setAttribute("authors", authorModel.findAll());
                            request.setAttribute("categories", categoryModel.findAll());
                            request.setAttribute("bookAuthors", bookModel.findAuthors(id));
                            request.setAttribute("bookCategories", bookModel.findCategories(id));
                            request.getRequestDispatcher(bookForm).forward(request, response);
                        } else {
                            goIndex(request, response, new Alert(AlertStyle.DANGER, "Le livre que vous recherchez n'existe pas"));
                        }
                    } catch (NumberFormatException e) {
                        goIndex(request, response, new Alert(AlertStyle.DANGER, "Le livre que vous recherchez n'existe pas"));
                    }
                    break;
                case "delete":
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    bookModel.delete(id);
                    goIndex(request, response, new Alert("Livre supprimé avec succes"));
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

        String[] authorIds = request.getParameterValues("authorIds");
        String[] categoryIds = request.getParameterValues("categoryIds");

        String isbn = request.getParameter("isbn").trim();
        String edition = request.getParameter("edition").trim();
        String title = request.getParameter("title").trim();
        String subtitle = request.getParameter("subtitle").trim();
        String nbPage = request.getParameter("nbPage").trim();
        String releaseDate = request.getParameter("releaseDate").trim();
        String price = request.getParameter("price").trim();
        String summary = request.getParameter("summary").trim();
        String editorId = request.getParameter("editorId").trim();
        String taxId = request.getParameter("taxId").trim();
        String languageId = request.getParameter("languageId").trim();

        Book book = new Book();
        Integer id = null;

        try {
            id = Integer.parseInt(request.getParameter("id"));
            book.setId(id);
        } catch (NumberFormatException e) {
        }

        try {
            Validator.required(title);
            book.setTitle(title);
        } catch (Exception e) {
            errors.put("title", e.getMessage());
        }

        try {
            Validator.required(summary);
            book.setSummary(summary);
        } catch (Exception e) {
            errors.put("summary", e.getMessage());
        }

        try {
            book.setReleaseDate(Validator.date(releaseDate, "dd/MM/yyyy", true));
        } catch (Exception e) {
            errors.put("releaseDate", e.getMessage());
        }

        try {
            book.setPrice(Validator.decimal(price, true));
        } catch (Exception e) {
            errors.put("price", e.getMessage());
        }

        try {
            book.setEdition(Validator.number(edition, true));
        } catch (Exception e) {
            errors.put("edition", e.getMessage());
        }

        try {
            book.setNbPage(Validator.number(nbPage, true));
        } catch (Exception e) {
            errors.put("nbPage", e.getMessage());
        }

        try {
            book.setEditorId(Validator.number(editorId, true));
        } catch (Exception e) {
            errors.put("editorId", e.getMessage());
        }

        try {
            book.setLanguageId(Validator.number(languageId, true));
        } catch (Exception e) {
            errors.put("languageId", e.getMessage());
        }

        try {
            book.setTaxId(Validator.number(taxId, true));
        } catch (Exception e) {
            errors.put("taxId", e.getMessage());
        }

        try {
            book.setIsbn(Validator.isbn(isbn, true));
        } catch (Exception e) {
            errors.put("isbn", e.getMessage());
        }

        try {
            book.setAuthorIds(Validator.integerList(authorIds, true));
        } catch (Exception e) {
            errors.put("authorIds", e.getMessage());
        }
        
        try {
            book.setCategoryIds(Validator.integerList(categoryIds, true));
        } catch (Exception e) {
            errors.put("categoryIds", e.getMessage());
        }

        book.setSubtitle(subtitle);

        try {

            if (!errors.isEmpty()) {
                request.setAttribute("book", book);
                request.setAttribute("editors", editorModel.findAll());
                request.setAttribute("languages", languageModel.findAll());
                request.setAttribute("taxes", taxModel.findAll());
                request.setAttribute("authors", authorModel.findAll());
                request.setAttribute("categories", categoryModel.findAll());
                if (id != null) {
                    request.setAttribute("bookAuthors", bookModel.findAuthors(id));
                    request.setAttribute("bookCategories", bookModel.findCategories(id));
                }
                request.setAttribute("errors", errors);
                request.getRequestDispatcher(bookForm).forward(request, response);
            } else {
                Alert alert;

                if (id != null) {
                    bookModel.update(book);
                    bookModel.addAuthors(book);
                    bookModel.addCategories(book);
                    alert = new Alert("Livre modifié avec succes");
                } else {
                    Integer bookId = bookModel.save(book);
                    book.setId(bookId);
                    bookModel.addAuthors(book);
                    bookModel.addCategories(book);
                    alert = new Alert("Livre ajouté avec succes");
                }

                goIndex(request, response, alert);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            goIndex(request, response, new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite."));
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response, Alert alert) throws IOException, ServletException {
        List<Book> books = new ArrayList();

        try {
            books = bookModel.findAll();
        } catch (SQLException | ClassNotFoundException e) {
            alert = new Alert(AlertStyle.DANGER, "Une erreur inattendue s'est produite.");
            System.out.println(e.getMessage());
        }

        request.setAttribute("alert", alert);
        request.setAttribute("books", books);
        request.getRequestDispatcher(bookIndex).forward(request, response);
    }

}
