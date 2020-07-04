
package it.enaip.stage.dao;

import java.util.Optional;
import java.sql.SQLException;
import java.util.List;


public interface Dao<T, ID> {

     Optional<T> find(ID id) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean save(T o) throws SQLException;

    boolean update(T o) throws SQLException;

    boolean delete(T o) throws SQLException;

}
