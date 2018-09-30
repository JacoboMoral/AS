package dades.passarelesFila;

/* Imports de la classe */
import java.sql.*; 
import serveisComuns.baseDeDades.*;
import dades.excepcions.*;

/* Capa de Dades */
public class Exemplar {
	
	private String isbnLlibre;
	
	private String nomBiblioteca;
	
	private int quantitat;
	
	public Exemplar(String isbnLl, String nomB, int q) {
	    isbnLlibre = isbnLl;
	    nomBiblioteca = nomB;
	    quantitat = q;
	}
	
	public String obteISBN() {
		return isbnLlibre;
	}
	
	public String obteNomBiblioteca() {
		return nomBiblioteca;
	}
	
	public int obteQuants() {
		return quantitat;
	}
		
	public void posaQuants(int q) {
		quantitat = q;
	}
	
	public void insereix() throws CDExemplarJaExistentsBiblio, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();	    
			st.executeUpdate("insert into exemplar values('"+isbnLlibre+"','"+nomBiblioteca+"',"+quantitat+");");	    
			st.close();
		}
		catch (SQLException se) {
			if (se.getSQLState().equals("23505")) {
				/* Error de Primary Key */
				throw new CDExemplarJaExistentsBiblio();
			}
			else throw new BDException(11);
		}
	}
	
	public void modifica() throws BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();				
			st.executeUpdate("update exemplar set quants ="+quantitat+" where isbn ='"+isbnLlibre+"' and nomB='"+nomBiblioteca+"';");
			st.close();
		}
		catch (SQLException se) {
			throw new BDException(11);
		}
	}
}