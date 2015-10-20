package fr.afpa.ecommerce.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Crud<E> {

    public Integer save(E entity) throws IOException, SQLException, ClassNotFoundException;

    public E find(Integer id) throws IOException, SQLException, ClassNotFoundException;

    public List<E> findAll() throws IOException, SQLException, ClassNotFoundException;

    public void update(E entity) throws IOException, SQLException, ClassNotFoundException;

    public void delete(Integer id) throws IOException, SQLException, ClassNotFoundException;

}
