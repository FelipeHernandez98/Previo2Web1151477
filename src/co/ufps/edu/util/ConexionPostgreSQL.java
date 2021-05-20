package co.ufps.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionPostgreSQL {

	private Connection con = null;
	private static ConexionPostgreSQL db;
	private PreparedStatement preparedStatement;
	
	private static final String url= "jdbc:postgresql://queenie.db.elephantsql.com/";
	private static final String dbName = "mnjgxshj";
    private static final String driver = "org.postgresql.Driver";
    private static final String userName = "mnjgxshj";
    private static final String password = "Uzjqo00sxV0W9OzPEB1q3wpoVvGMbbUV";
    
    public ConexionPostgreSQL() {
		try {
			Class.forName(driver).newInstance();
			con = (Connection)DriverManager.getConnection(url+dbName,userName,password);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void cerrarConexion() {
    	try {
    		con.close();
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static ConexionPostgreSQL getConexion() {
    	if (db == null) {
    		db = new ConexionPostgreSQL();
    	}
    	
    	return db;
    }
    
    public ResultSet query() throws SQLException {
    	ResultSet res = preparedStatement.executeQuery();
    	return res;
    }
    
    public int execute() throws SQLException {
    	int result = preparedStatement.executeUpdate();
    	return result;
    }
    
    public Connection getCon() {
    	return this.con;
    }
    
    public PreparedStatement setPreparedStatement (String sql) throws SQLException {
    	//db = getConexion();
    	this.preparedStatement = con.prepareStatement(sql);
    	return this.preparedStatement;
    	
    }
}

