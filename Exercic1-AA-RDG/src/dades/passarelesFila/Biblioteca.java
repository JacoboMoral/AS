package dades.passarelesFila;

/* Imports de la classe */
import java.sql.*; 
import serveisComuns.baseDeDades.*;
import dades.excepcions.*;

/* Capa de Dades */
public class Biblioteca {
	
	private String nomBiblioteca;
	
	private Boolean publica;
	
	/* falta afegir l'atribut FK cap a instalacio */
	
	public Biblioteca(String nomB, Boolean publ) {
		nomBiblioteca = nomB;
		publica = publ;
	}
	
	public String obteNom() {
		return nomBiblioteca;
	}
	
	public void posaPublica(Boolean p) {
		publica = p;
	}
	
	public Boolean obtePublica() {
		return publica;
	}
	
	public void insereix() throws CDBibliotecaJaExisteix, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();	    
			st.executeUpdate("insert into biblioteca values('"+nomBiblioteca+"',"+publica+");");	
			st.close();
		}
		catch (SQLException se) {
			if (se.getSQLState().equals("23505")) {
				/* Error de Primary Key */
				throw new CDBibliotecaJaExisteix();
			}
			else throw new BDException(11);
		}
	}
	
	public void modifica() throws BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();	    
			st.executeUpdate("update biblioteca set publica ='"+publica+"' where nomB ='"+nomBiblioteca+"';");
			st.close();
		}
		catch (SQLException se) {
			throw new BDException(11);
		}
	}
}
