package co.ufps.edu.dao;

public class PaisDaoFactory {

		public static PaisDao getPaisDao(String type) {
			switch(type) {
			case "mysql":
				return new PaisDaoMySQL();
			case "postgresql":
				return new PaisDaoPostgreSQL();
			default:
				return new PaisDaoMySQL();
				
			}
		}

		
		
	}
