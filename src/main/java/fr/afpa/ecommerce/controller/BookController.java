package fr.afpa.ecommerce.controller;

import fr.afpa.ecommerce.bean.Author;
import fr.afpa.ecommerce.bean.Book;
import fr.afpa.ecommerce.bean.BookAuthor;
import fr.afpa.ecommerce.bean.BookCategory;
import fr.afpa.ecommerce.bean.Category;
import fr.afpa.ecommerce.bean.Editor;
import fr.afpa.ecommerce.bean.Language;
import fr.afpa.ecommerce.bean.Tax;
import fr.afpa.ecommerce.util.Alert;
import fr.afpa.ecommerce.util.AlertStyle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
 * @book Riad YOUSFI
 */
@WebServlet(name = "BookController", urlPatterns = {"/Admin/Book"})
public class BookController extends HttpServlet {

    private final String bookForm = "/WEB-INF/admin/BookForm.jsp";
    private final String bookIndex = "/WEB-INF/admin/BookIndex.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "index";
        }

        switch (action) {
            case "add":

                request.setAttribute("editors", findAllEditors());
                request.setAttribute("languages", findAllLanguages());
                request.setAttribute("taxes", findAllTaxes());
                request.setAttribute("authors", findAllAuthors());
                request.setAttribute("categories", findAllCategories());
                request.getRequestDispatcher(bookForm).forward(request, response);
                break;
            case "edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Book book = getBookFormData(id);
                    request.setAttribute("book", book);
                    request.setAttribute("editors", findAllEditors());
                    request.setAttribute("languages", findAllLanguages());
                    request.setAttribute("taxes", findAllTaxes());
                    request.setAttribute("authors", findAllAuthors());
                    request.setAttribute("categories", findAllCategories());
                    request.setAttribute("bookAuthors", findAllBookAuthors());
                    request.setAttribute("bookCategories", findAllBookCategories());
                    request.getRequestDispatcher(bookForm).forward(request, response);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertStyle.DANGER, "La livre que vous recherchez n'existe pas");
                    request.setAttribute("alert", alert);
                    goIndex(request, response);
                }
                break;
            case "delete":
                //Delete Book from database
                request.setAttribute("alert", new Alert("Booke supprimé avec succes"));
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

        int id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }

        Book book = new Book();

        if (!errors.isEmpty()) {
            request.setAttribute("book", book);
            request.setAttribute("editors", findAllEditors());
            request.setAttribute("languages", findAllLanguages());
            request.setAttribute("taxes", findAllTaxes());
            request.setAttribute("authors", findAllAuthors());
            request.setAttribute("categories", findAllCategories());
            request.setAttribute("bookAuthors", findAllBookAuthors());
            request.setAttribute("bookCategories", findAllBookCategories());
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(bookForm).forward(request, response);

        } else {
            if (id > 0) {
                //Update Book from database
                request.setAttribute("alert", new Alert("Livre modifié avec succes"));
            } else {
                //Insert Book from database
                request.setAttribute("alert", new Alert("Livre ajouté avec succes"));
            }

            goIndex(request, response);
        }

    }

    private void goIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Book> books = findAllBookes();
        request.setAttribute("books", books);
        request.getRequestDispatcher(bookIndex).forward(request, response);
    }

    private List<Book> findAllBookes() {
        //FindAll Book from database
        List<Book> books = new ArrayList<>();
        books.add(getBookFormData(1));
        books.add(getBookFormData(2));
        books.add(getBookFormData(3));
        books.add(getBookFormData(4));
        books.add(getBookFormData(5));
        return books;
    }

    private Book getBookFormData(int id) {
        //FindById Book from database
        Book book = new Book(id);
        book.setIsbn(9782746096486l);
        book.setEdition(1);
        book.setTitle("Java et jQuery");
        book.setSubtitle("Intégrer un framework JavaScript dans l'écosystème JEE");
        book.setNbPage(400);
        book.setReleaseDate(new Date(115, 7, 17));
        book.setPrice(2.3);
        book.setSummary("Ce livre s'adresse aux développeurs Java souhaitant dynamiser leurs applications web. Tout au long de ces pages l'auteur explique de façon pragmatique comment ajouter de la fluidité et du dynamisme au sein des pages mises à disposition des utilisateurs et comment les solutions clientes Javascript permettent d'apporter de la logique de présentation sur les écrans. C'est dans ce cadre que la bibliothèque jQuery intervient. Avec un concept simple \"write less, do more\", la bibliothèque propose des fonctionnalités performantes et concises afin d'améliorer les vues de l'application tout en uniformisant les différents comportements des navigateurs.");
        book.setEditorId(1);
        book.setTaxId(2);
        book.setLanguageId(1);
        return book;
    }

    private List<Editor> findAllEditors() {
        //FindAll Editor from database
        List<Editor> editors = new ArrayList<>();
        editors.add(new Editor(1, "Eni"));
        editors.add(new Editor(2, "OpenClassrooms - ex-Site du Zéro"));
        editors.add(new Editor(3, "Eyrolles"));
        return editors;
    }

    private List<Tax> findAllTaxes() {
        //FindAll Tax from database
        List<Tax> taxes = new ArrayList<>();
        taxes.add(new Tax(1, "TVA", 19.6));
        return taxes;
    }

    private List<Language> findAllLanguages() {
        //FindAll Tax from database
        List<Language> languages = new ArrayList<>();
        languages.add(new Language(1, "Français"));
        languages.add(new Language(2, "Englais"));
        return languages;
    }

    private List<Author> findAllAuthors() {
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

    private List<BookAuthor> findAllBookAuthors() {
        List<BookAuthor> bookAuthors = new ArrayList();
        bookAuthors.add(new BookAuthor(1, 1, 1));
        bookAuthors.add(new BookAuthor(2, 1, 2));
        bookAuthors.add(new BookAuthor(3, 1, 3));
        bookAuthors.add(new BookAuthor(4, 1, 9));
        return bookAuthors;
    }

    private List<BookCategory> findAllBookCategories() {
        List<BookCategory> bookCategoryies = new ArrayList();
        bookCategoryies.add(new BookCategory(1, 1, 1));
        bookCategoryies.add(new BookCategory(2, 1, 2));
        bookCategoryies.add(new BookCategory(3, 1, 3));
        bookCategoryies.add(new BookCategory(4, 1, 5));
        return bookCategoryies;
    }
}
