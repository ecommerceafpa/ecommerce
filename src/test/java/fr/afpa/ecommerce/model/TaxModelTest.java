package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Tax;
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
public class TaxModelTest {
    
    @Before
    public void setUpDataBase() throws IOException, SQLException, ClassNotFoundException, DataSetException, DatabaseUnitException {
        IDatabaseConnection connection = new DatabaseConnection(ConnectionFactory.getConnection());
        IDataSet dataSet = new FlatXmlDataSet(TaxModelTest.class.getResourceAsStream("/database/dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }

    @Test
    public void test1Save() {
        try {
            //Save
            TaxModel taxModel = new TaxModel();
            Tax tax = new Tax("jtetaxe", 19.6f);
            tax = taxModel.save(tax);
            assertNotNull("Save : " + tax.toString(), tax);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test2Find() {

        try {
            //Find
            TaxModel taxModel = new TaxModel();
            Tax tax = taxModel.find(1);
            assertNotNull(tax);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Test
    public void test3Update() {
        try {
            //Update
            TaxModel taxModel = new TaxModel();
            Tax tax = new Tax("encoreunetaxe", 38.7f);
            tax.setId(1);
            boolean isUpdated = taxModel.update(tax);
            assertTrue(isUpdated);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Test
    public void test4FindAll() {
        try {
            //Find All
            TaxModel taxModel = new TaxModel();
            List<Tax> taxs = taxModel.findAll();
            assertNotNull(taxs);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test5Delete() {
        try {
            //Delete
            TaxModel taxModel = new TaxModel();
            Tax tax = new Tax();
            tax.setId(1);
            boolean isDeleted = taxModel.delete(tax);
            assertTrue(isDeleted);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }
    
}
