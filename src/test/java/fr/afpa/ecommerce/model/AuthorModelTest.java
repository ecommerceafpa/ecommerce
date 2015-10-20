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

    @Ignore
    @Test
    public void test1Save() throws IOException, SQLException, ClassNotFoundException {

    }

    @Ignore
    @Test
    public void test2Find() throws IOException, SQLException, ClassNotFoundException {

        AuthorModel authorModel = new AuthorModel();
        Author author = authorModel.find(1);
        assertNotNull(author);
    }

    @Ignore
    @Test
    public void test3Update() throws IOException, SQLException, ClassNotFoundException {
        AuthorModel authorModel = new AuthorModel();
        Author author = new Author("Riad", "YOUSFI", "Mon portrait");
        author.setId(1);
        authorModel.update(author);
    }

    @Ignore
    @Test
    public void test4FindAll() throws IOException, SQLException, ClassNotFoundException {
        AuthorModel authorModel = new AuthorModel();
        List<Author> authors = authorModel.findAll();
        assertNotNull(authors);
    }

    @Ignore
    @Test
    public void test5Delete() throws IOException, SQLException, ClassNotFoundException {
        AuthorModel authorModel = new AuthorModel();
        authorModel.delete(1);
    }

}
