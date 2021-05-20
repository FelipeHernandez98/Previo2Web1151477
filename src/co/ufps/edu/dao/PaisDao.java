package co.ufps.edu.dao;

import java.sql.SQLException;
import java.util.List;

import co.ufps.edu.modelo.Pais;

public interface PaisDao {

	public void insert(Pais pais) throws SQLException;
	public Pais select(int id);
	public List < Pais > selectAll();
	public void delete(String id) throws SQLException;
	public void update(Pais pais) throws SQLException;
	
}
