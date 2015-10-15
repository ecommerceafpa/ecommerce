package fr.afpa.ecommerce.model;

import fr.afpa.ecommerce.bean.Editor;
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
public class EditorModelTest {
    
   @Before
    public void setUpDataBase() throws IOException, SQLException, ClassNotFoundException, DataSetException, DatabaseUnitException {
        IDatabaseConnection connection = new DatabaseConnection(ConnectionFactory.getConnection());
        IDataSet dataSet = new FlatXmlDataSet(EditorModelTest.class.getResourceAsStream("/database/dataset.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        connection.close();
    }

    @Test
    public void test1Save() {
        try {
            //Save
            EditorModel editorModel = new EditorModel();
            Editor editor = new Editor("Yacine");
            editor = editorModel.save(editor);
            assertNotNull("Save : " + editor.toString(), editor);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test2Find() {

        try {
            //Find
            EditorModel editorModel = new EditorModel();
            Editor editor = editorModel.find(1);
            assertNotNull(editor);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Test
    public void test3Update() {
        try {
            //Update
            EditorModel editorModel = new EditorModel();
            Editor editor = new Editor("Riad");
            editor.setId(1);
            boolean isUpdated = editorModel.update(editor);
            assertTrue(isUpdated);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    @Test
    public void test4FindAll() {
        try {
            //Find All
            EditorModel editorModel = new EditorModel();
            List<Editor> editors = editorModel.findAll();
            assertNotNull(editors);
        } catch (ClassNotFoundException | IOException | SQLException e) {
        }
    }

    @Test
    public void test5Delete() {
        try {
            //Delete
            EditorModel editorModel = new EditorModel();
            Editor editor = new Editor();
            editor.setId(1);
            boolean isDeleted = editorModel.delete(editor);
            assertTrue(isDeleted);

        } catch (ClassNotFoundException | IOException | SQLException e) {
        }

    }

    
}
