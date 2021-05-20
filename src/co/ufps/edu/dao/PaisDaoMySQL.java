package co.ufps.edu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ufps.edu.modelo.Pais;
import co.ufps.edu.util.ConexionMySQL;

public class PaisDaoMySQL implements PaisDao {
	

	private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario (nombre, email, pais) VALUES (?, ?, ?);";
	private static final String DELETE_USUARIO_SQL = "DELETE FROM usuario WHERE id = ?;";
	private static final String UPDATE_USUARIO_SQL = "UPDATE usuario SET nombre = ?, email = ?, pais = ? WHERE id = ?;";
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM usuario WHERE id = ?;";
	private static final String SELECT_ALL_USUARIOS = "SELECT * FROM usuario;";
	
	private ConexionMySQL conexion;

	public PaisDaoMySQL() {
		
		this.conexion = ConexionMySQL.getConexion();
	}
	
	public void insert(Pais pais) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedStatement.setString(1, pais.getId());
			preparedStatement.setString(2, pais.getName());
			
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void delete (String id)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_USUARIO_SQL);
			preparedStatement.setString(1, id);

			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public void update(Pais pais)  throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
			preparedStatement.setString(1, pais.getId());
			preparedStatement.setString(2, pais.getName());
			
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}
	
	public List<Pais> selectAll() {
		List <Pais> paises = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_USUARIOS);
			
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
	
	
	public Pais select(String id) {
		Pais paises = null;
		
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_USUARIO_BY_ID);
			preparedStatement.setString(1, id);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				
				
				String name = rs.getString("name");
				
				paises = new Pais(id, name);
			}
			
		} catch (SQLException e) {
			
		}
		
		return paises;
		
	}

	

}
