package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Author;
import fr.afpa.ecommerce.jdbc.ConnectionFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorModelTest {

    @Before
    public void setUpDataBase() throws IOException, SQLException, ClassNotFoundException, DataSetException, DatabaseUnitException {
        IDatabaseConnection connection = new DatabaseConnection(ConnectionFactory.getConnection());
        IDataSet dataSet = new FlatXmlDataSet(AuthorModelTest.class.getResourceAsStream("/database/dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }

    @Test
    public void test1Save() {
        try {
            //Save
            AuthorModel authorModel = new AuthorModel();
            Author author = new Author("Yacine", "YOUSFI", "Mon portrait");
            author = authorModel.save(author);
            assertNotNull("Save : " + author.toString(), author);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test2Find() {

        try {
            //Find
            AuthorModel authorModel = new AuthorModel();
            Author author = authorModel.find(1);
            assertNotNull(author);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Test
    public void test3Update() {
        try {
            //Update
            AuthorModel authorModel = new AuthorModel();
            Author author = new Author("Riad", "YOUSFI", "Mon portrait");
            author.setId(1);
            boolean isUpdated = authorModel.update(author);
            assertTrue(isUpdated);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Test
    public void test4FindAll() {
        try {
            //Find All
            AuthorModel authorModel = new AuthorModel();
            List<Author> authors = authorModel.findAll();
            assertNotNull(authors);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test5Delete() {
        try {
            //Delete
            AuthorModel authorModel = new AuthorModel();
            Author author = new Author();
            author.setId(1);
            boolean isDeleted = authorModel.delete(author);
            assertTrue(isDeleted);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

}
