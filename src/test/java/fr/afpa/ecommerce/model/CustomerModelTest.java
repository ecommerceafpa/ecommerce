package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Customer;
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
public class CustomerModelTest {

    @Before
    public void setUpDataBase() throws IOException, SQLException, ClassNotFoundException, DataSetException, DatabaseUnitException {
        IDatabaseConnection connection = new DatabaseConnection(ConnectionFactory.getConnection());
        IDataSet dataSet = new FlatXmlDataSet(CustomerModelTest.class.getResourceAsStream("/database/dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }

    @Test
    public void test1Save() {
        try {
            //Save
            CustomerModel customerModel = new CustomerModel();
            Customer customer = new Customer("Yacine", "YOUSFI", "you", "det");
            customer = customerModel.save(customer);
            assertNotNull("Save : " + customer.toString(), customer);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test2Find() {

        try {
            //Find
            CustomerModel customerModel = new CustomerModel();
            Customer customer = customerModel.find(1);
            assertNotNull(customer);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Test
    public void test3Update() {
        try {
            //Update
            CustomerModel customerModel = new CustomerModel();
            Customer customer = new Customer("Riad", "YOUSFI", "ris", "jet");
            customer.setId(1);
            boolean isUpdated = customerModel.update(customer);
            assertTrue(isUpdated);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Test
    public void test4FindAll() {
        try {
            //Find All
            CustomerModel customerModel = new CustomerModel();
            List<Customer> customers = customerModel.findAll();
            assertNotNull(customers);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test5Delete() {
        try {
            //Delete
            CustomerModel customerModel = new CustomerModel();
            Customer customer = new Customer();
            customer.setId(1);
            boolean isDeleted = customerModel.delete(customer);
            assertTrue(isDeleted);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test6Disable() {
        try {
            //Disable
            CustomerModel customerModel = new CustomerModel();
            Customer customer = new Customer();
            customer.setId(1);
            customer.setDisabled(true);
            boolean isDisabled = customerModel.disable(customer.getId(), customer.isDisabled());
            assertTrue(isDisabled);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }
}
