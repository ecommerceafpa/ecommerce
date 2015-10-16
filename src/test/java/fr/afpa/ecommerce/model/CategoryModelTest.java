package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Category;
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
public class CategoryModelTest {

    @Ignore
    @Before
    public void setUpDataBase() throws IOException, SQLException, ClassNotFoundException, DataSetException, DatabaseUnitException {
        IDatabaseConnection connection = new DatabaseConnection(ConnectionFactory.getConnection());
        IDataSet dataSet = new FlatXmlDataSet(CategoryModelTest.class.getResourceAsStream("/database/dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }

    //@Ignore
    @Test
    public void test1Save() {
        try {
            //Save
            CategoryModel categoryModel = new CategoryModel();
            Category category = new Category("photo");
            category = categoryModel.save(category);
            assertTrue("Save : " + category.toString(), category.getId() > 0);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Ignore
    @Test
    public void test2Find() {

        try {
            //Find
            CategoryModel categoryModel = new CategoryModel();
            Category category = categoryModel.find(1);
            assertNotNull(category);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Ignore
    @Test
    public void test3Update() {
        try {
            //Update
            CategoryModel categoryModel = new CategoryModel();
            Category category = new Category("php");
            category.setId(2);
            boolean isUpdated = categoryModel.update(category);
            assertTrue(isUpdated);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Ignore
    @Test
    public void test4FindAll() {
        try {
            //Find All
            CategoryModel categoryModel = new CategoryModel();
            List<Category> categories = categoryModel.findAll();
            assertNotNull(categories);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Ignore
    @Test
    public void test5Delete() {
        try {
            //Delete
            CategoryModel categoryModel = new CategoryModel();
            Category category = new Category();
            category.setId(1);
            boolean isDeleted = categoryModel.delete(category);
            assertTrue(isDeleted);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

}
