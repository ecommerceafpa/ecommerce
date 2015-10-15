package fr.afpa.ecommerce.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Crud<E> {

    public E save(E entity)throws IOException, SQLException, ClassNotFoundException;

    public E find(int id)throws IOException, SQLException, ClassNotFoundException;

    public List<E> findAll()throws IOException, SQLException, ClassNotFoundException;
    
    public boolean update(E entity)throws IOException, SQLException, ClassNotFoundException;
    
    public boolean delete(E entity)throws IOException, SQLException, ClassNotFoundException;

}
