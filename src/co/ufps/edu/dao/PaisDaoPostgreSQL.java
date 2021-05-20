package co.ufps.edu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ufps.edu.modelo.Pais;
import co.ufps.edu.util.ConexionMySQL;

public class PaisDaoPostgreSQL implements PaisDao {


	private static final String INSERT_PAIS_SQL = "INSERT INTO country (id, name) VALUES (?, ?);";
	private static final String DELETE_PAIS_SQL = "DELETE FROM country WHERE id = ?;";
	private static final String UPDATE_PAIS_SQL = "UPDATE country SET id = ?, namel = ?;";
	private static final String SELECT_PAIS_BY_ID = "SELECT * FROM country WHERE id = ?;";
	private static final String SELECT_ALL_PAISES = "SELECT * FROM cpuntry;";
	
	private ConexionMySQL conexion;

	public PaisDaoPostgreSQL() {
		
		this.conexion = ConexionMySQL.getConexion();
	}
	
	public void insert(Pais pais) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_PAIS_SQL);
			preparedStatement.setString(1, pais.getId());
			preparedStatement.setString(2, pais.getName());
			
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (String id)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_PAIS_SQL);
			preparedStatement.setString(1, id);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Pais pais)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_PAIS_SQL);
			preparedStatement.setString(1, pais.getId());
			preparedStatement.setString(2, pais.getName());
			
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Pais> selectAll() {
		List <Pais> paises = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_PAISES);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
		
				paises.add(new Pais(id, name));
			}
			
		} catch (SQLException e) {
			
		}
		
		return paises;
	}
	
	
	public Pais select(int id) {
		Pais paises = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_PAIS_BY_ID);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				
				String id = rs.getString("id");
				String name = rs.getString("name");
				
				paises = new Pais(id, name);
			}
			
		} catch (SQLException e) {
			
		}
		
		return paises;
		
	}

	
	

}
