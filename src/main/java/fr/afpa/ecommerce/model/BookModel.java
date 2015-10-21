package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Author;
import fr.afpa.ecommerce.bean.Book;
import fr.afpa.ecommerce.bean.BookAuthor;
import fr.afpa.ecommerce.bean.BookCategory;
import fr.afpa.ecommerce.jdbc.ConnectionFactory;
import fr.afpa.ecommerce.jdbc.ConnectionUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Riad YOUSFI
 */
public class BookModel implements Crud<Book> {

    @Override
    public Integer save(Book book) throws IOException, SQLException, ClassNotFoundException {
        String req = "INSERT INTO book (editor_id, language_id, tax_id, isbn, title, subtitle, summary, nb_page, release_date, edition, price) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        pstm.setInt(1, book.getEditorId());
        pstm.setInt(2, book.getLanguageId());
        pstm.setInt(3, book.getTaxId());
        pstm.setLong(4, book.getIsbn());
        pstm.setString(5, book.getTitle());
        pstm.setString(6, book.getSubtitle());
        pstm.setString(7, book.getSummary());
        pstm.setInt(8, book.getNbPage());
        pstm.setDate(9, new Date(book.getReleaseDate().getTime()));
        pstm.setInt(10, book.getEdition());
        pstm.setBigDecimal(11, book.getPrice());

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        Integer id = null;
        try (ResultSet rs = pstm.getGeneratedKeys()) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
            ConnectionUtil.close(rs);
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return id;
    }

    @Override
    public Book find(Integer id) throws IOException, SQLException, ClassNotFoundException {

        Book book = null;

        String req = "SELECT * FROM book WHERE deleted = false AND id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            book = new Book();
            book.setId(rs.getInt("id"));
            book.setEditorId(rs.getInt("editor_id"));
            book.setLanguageId(rs.getInt("language_id"));
            book.setTaxId(rs.getInt("tax_id"));
            book.setIsbn(rs.getLong("isbn"));
            book.setTitle(rs.getString("title"));
            book.setSubtitle(rs.getString("subtitle"));
            book.setSummary(rs.getString("summary"));
            book.setNbPage(rs.getInt("nb_page"));
            book.setReleaseDate(rs.getDate("release_date"));
            book.setEdition(rs.getInt("edition"));
            book.setPrice(rs.getBigDecimal("price"));
            book.setCreated(rs.getTimestamp("created"));
            book.setUpdated(rs.getTimestamp("updated"));
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return book;
    }

    @Override
    public List<Book> findAll() throws IOException, SQLException, ClassNotFoundException {

        List<Book> books = new ArrayList<>();

        String req = "SELECT e.name AS editor_name, l.name AS language_name, b.id, isbn, title, subtitle, release_date, b.created, b.updated "
                + "FROM book b "
                + "LEFT JOIN editor e ON (e.id = b.editor_id) "
                + "LEFT JOIN language l ON (l.id = b.language_id) "
                + "WHERE b.deleted = false";

        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.prepareStatement(req);
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setEditorName(rs.getString("editor_name"));
            book.setLanguageName(rs.getString("language_name"));
            book.setIsbn(rs.getLong("isbn"));
            book.setTitle(rs.getString("title"));
            book.setSubtitle(rs.getString("subtitle"));
            book.setReleaseDate(rs.getDate("release_date"));
            book.setCreated(rs.getTimestamp("created"));
            book.setUpdated(rs.getTimestamp("updated"));
            books.add(book);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return books;
    }

    public List<BookAuthor> findAuthors(Integer id) throws IOException, SQLException, ClassNotFoundException {

        List<BookAuthor> bookAuthors = new ArrayList();

        String req = "SELECT * FROM book_author WHERE book_id = ?";

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            BookAuthor bookAuthor = new BookAuthor();
            bookAuthor.setId(rs.getInt("id"));
            bookAuthor.setAuthorId(rs.getInt("author_id"));
            bookAuthor.setBookId(rs.getInt("book_id"));
            bookAuthors.add(bookAuthor);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return bookAuthors;
    }

    public List<BookCategory> findCategories(Integer id) throws IOException, SQLException, ClassNotFoundException {
        List<BookCategory> bookCategories = new ArrayList();

        String req = "SELECT * FROM book_category WHERE book_id = ?";

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setId(rs.getInt("id"));
            bookCategory.setCategoryId(rs.getInt("category_id"));
            bookCategory.setBookId(rs.getInt("book_id"));
            bookCategories.add(bookCategory);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return bookCategories;
    }

    @Override
    public void update(Book book) throws IOException, SQLException, ClassNotFoundException {

        String req = "UPDATE book SET editor_id = ?, language_id = ?, tax_id = ?, isbn = ?, title = ?, subtitle = ?, summary = ?, nb_page = ?, edition = ?, price = ? WHERE id = ?";

        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);

        pstm.setInt(1, book.getEditorId());
        pstm.setInt(2, book.getLanguageId());
        pstm.setInt(3, book.getTaxId());
        pstm.setLong(4, book.getIsbn());
        pstm.setString(5, book.getTitle());
        pstm.setString(6, book.getSubtitle());
        pstm.setString(7, book.getSummary());
        pstm.setInt(8, book.getNbPage());
        pstm.setInt(9, book.getEdition());
        pstm.setBigDecimal(10, book.getPrice());
        pstm.setInt(11, book.getId());

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }

        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

    @Override
    public void delete(Integer id) throws IOException, SQLException, ClassNotFoundException {
        String req = "UPDATE book SET deleted = 1 WHERE id = ?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        if (pstm.executeUpdate() == 0) {
            throw new SQLException();
        }
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);
    }

    public void addCategories(Book book) throws IOException, SQLException, ClassNotFoundException {

        Connection cnt = ConnectionFactory.getConnection();

        String queryDelete = "DELETE FROM book_category WHERE book_id=?";
        PreparedStatement statement = cnt.prepareStatement(queryDelete);
        statement.setInt(1, book.getId());
        statement.executeUpdate();
        ConnectionUtil.close(statement);

        for (Integer categoryId : book.getCategoryIds()) {
            String req = "INSERT INTO book_category (book_id, category_id) VALUES (?, ?)";
            PreparedStatement pstm = cnt.prepareStatement(req);
            pstm.setInt(1, book.getId());
            pstm.setInt(2, categoryId);
            if (pstm.executeUpdate() == 0) {
                throw new SQLException();
            }
            ConnectionUtil.close(pstm);
        }

        ConnectionUtil.close(cnt);
    }

    public void addAuthors(Book book) throws IOException, SQLException, ClassNotFoundException {

        Connection cnt = ConnectionFactory.getConnection();

        String queryDelete = "DELETE FROM book_author WHERE book_id=?";
        PreparedStatement statement = cnt.prepareStatement(queryDelete);
        statement.setInt(1, book.getId());
        statement.executeUpdate();
        ConnectionUtil.close(statement);

        for (Integer authorId : book.getAuthorIds()) {
            String req = "INSERT INTO book_author (book_id, author_id) VALUES (?, ?)";
            PreparedStatement pstm = cnt.prepareStatement(req);
            pstm.setInt(1, book.getId());
            pstm.setInt(2, authorId);
            if (pstm.executeUpdate() == 0) {
                throw new SQLException();
            }
            ConnectionUtil.close(pstm);
        }

        ConnectionUtil.close(cnt);
    }

    public List<Book> findBookByEvent() throws SQLException, IOException, ClassNotFoundException {
        List<Book> books = new ArrayList<>();

        String req = "SELECT b.id AS book_id, evt.id AS event_id, b.title, b.subtitle, e.name AS editor, b.release_date "
                + "FROM book b "
                + "JOIN editor e on(b.editor_id = e.id) "
                + "JOIN language lang on(lang.id = b.language_id) "
                + "JOIN book_event be on(b.id = be.book_id) "
                + "JOIN event evt on(evt.id = be.event_id) "
                + "WHERE b.deleted = false AND current_date() >= evt.start_date AND current_date() <= evt.end_date";

        Connection cnt = ConnectionFactory.getConnection();
        Statement stm = cnt.createStatement();
        ResultSet rs = stm.executeQuery(req);

        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("book_id"));
            book.setTitle(rs.getString("title"));
            book.setSubtitle(rs.getString("subtitle"));
            book.setReleaseDate(rs.getDate("release_date"));
            book.setEditorName(rs.getString("editor"));
            book.setEventId(rs.getInt("event_id"));
            books.add(book);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(stm);
        ConnectionUtil.close(cnt);

        return books;
    }

    public Book detailBook(Integer id) throws SQLException, IOException, ClassNotFoundException {

        Book book = null;
        String req = "SELECT b.title, b.edition, b.subtitle, b.price*(1+t.value/100) AS price, e.name AS editor, b.release_date, t.value, b.summary, b.isbn, b.nb_page, lang.name AS language "
                    + "FROM book b "
                    + "JOIN editor e on(b.editor_id = e.id) "
                    + "JOIN language lang on(lang.id = b.language_id) "
                    + "JOIN tax t on(b.tax_id = t.id) "
                    + "where b.id=?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            book = new Book();
            
            book.setEditorName(rs.getString("editor"));
            book.setLanguageName(rs.getString("language"));           
            book.setIsbn(rs.getLong("isbn"));
            book.setTitle(rs.getString("title"));
            book.setSubtitle(rs.getString("subtitle"));
            book.setSummary(rs.getString("summary"));
            book.setNbPage(rs.getInt("nb_page"));
            book.setReleaseDate(rs.getDate("release_date"));
            book.setEdition(rs.getInt("edition"));
            book.setPrice(rs.getBigDecimal("price"));           
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return book;
    }

    public List<Author> findAuthorsByBook(Integer id) throws SQLException, IOException, ClassNotFoundException {
        List<Author>authors=new ArrayList();
        Author author = null;
        String req = "select a.id, a.firstname, a.lastname from author a join book_author ba on (a.id=ba.author_id) join book b on(b.id=ba.book_id) where b.id=?";
        Connection cnt = ConnectionFactory.getConnection();
        PreparedStatement pstm = cnt.prepareStatement(req);
        pstm.setInt(1, id);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            author = new Author(); 
            author.setId(rs.getInt("id"));
            author.setFirstName(rs.getString("firstname"));
            author.setLastName(rs.getString("lastname")); 
            authors.add(author);
        }

        ConnectionUtil.close(rs);
        ConnectionUtil.close(pstm);
        ConnectionUtil.close(cnt);

        return authors;
    }

}
