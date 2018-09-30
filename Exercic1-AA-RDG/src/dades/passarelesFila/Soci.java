package dades.passarelesFila;

/* Imports de la classe */
import java.sql.*; 
import serveisComuns.baseDeDades.*;
import dades.excepcions.*;

/* Capa de Dades */
public class Soci {
	
	private String nomSoci;
	
	private int edat;

	private String nomInstalacio;
		
	public Soci(String nomS, int ed, String nomInst) {
		nomSoci = nomS;
	    edat = ed;
	    nomInstalacio = nomInst;
	}
	
	public String obteNom() {
		return nomSoci;
	}

	public int obteEdat() {
		return edat;
	}
	
	public String obteNomInstalacio() {
		return nomInstalacio;
	}
		
	public void posaEdat(int ed) {
		edat = ed;
	}
	
	public void posaNomInstalacio(String nomInst) {
		nomInstalacio = nomInst;
	}
		
	public void insereix() throws CDSociJaExisteix, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();	    
			st.executeUpdate("insert into soci values('"+nomSoci+"',"+edat+",'"+nomInstalacio+"');");	    
			st.close();
		}
		catch (SQLException se) {
			if (se.getSQLState().equals("23505")) {
				/* Error de Primary Key */
				throw new CDSociJaExisteix();
			}
			else throw new BDException(11);
		}
	}
	
	public void modifica() throws BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();	
			st.executeUpdate("update soci set edat = "+edat+", nomInst = '"+nomInstalacio+"' where nomS = '"+nomSoci+"';");
			st.close();
		}
		catch (SQLException se) {
			throw new BDException(11);
		}
	}
}