package dades.passarelesFila;

/* Imports de la classe */
import java.sql.*; 
import serveisComuns.baseDeDades.*;
import dades.excepcions.*;

/* Capa de Dades */
public class Llibre {
	
	private String isbnLlibre;
	
	private String titolLlibre;
	
	public Llibre(String isbnLl, String titol) {
	    isbnLlibre = isbnLl;
	    titolLlibre = titol;
	}
	
	public String obteISBN() {
		return isbnLlibre;
	}
	
	public String obteTitol() {
		return titolLlibre;
	}
		
	public void posaTitol(String nouT) {
		titolLlibre = nouT;
	}
	
	public void insereix() throws CDLlibreJaExisteix, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();	    
			st.executeUpdate("insert into llibre values('"+isbnLlibre+"','"+titolLlibre+"');");	
			st.close();
		}
		catch (SQLException se) {
			if (se.getSQLState().equals("23505")) {
				/* Error de Primary Key */
				throw new CDLlibreJaExisteix();
			}
			else throw new BDException(11);
		}
	}
	
	public void modifica() throws BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();	    
			st.executeUpdate("update llibre set titol ='"+titolLlibre+"' where isbn ='"+isbnLlibre+"';");
			st.close();	
		}
		catch (SQLException se) {
			throw new BDException(11);
		}
	}
}