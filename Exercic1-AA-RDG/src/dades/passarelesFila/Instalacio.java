
package dades.passarelesFila;

/* Imports de la classe */
import java.sql.*; 
import serveisComuns.baseDeDades.*;
import dades.excepcions.*;

/* Capa de Dades */
public class Instalacio {
	
	private String nomInst;
	
	private String adrecaInst;
	
	public Instalacio(String nomI, String ad) {
		nomInst = nomI;
		adrecaInst = ad;
	}
	
	public String obteNom() {
		return nomInst;
	}
	
	public String obteAdreca() {
		return adrecaInst;
	}
		
	public void posaAdreca(String adreca) {
		adrecaInst = adreca;
	}
	
	public void insereix() throws CDInstalacioJaExisteix, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();
			st.executeUpdate("insert into instalacio values('"+nomInst+"','"+adrecaInst+"');");	
			st.close();
		}
		catch (SQLException se) {
			if (se.getSQLState().equals("23505")) {
				/* Error de Primary Key */
				throw new CDInstalacioJaExisteix();
			}
			else throw new BDException(11);
		}
	}
	
	public void modifica() throws BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();
			st.executeUpdate("update instalacio set adreca ='"+adrecaInst+"' where nomI ='"+nomInst+"';");
			st.close();
		}
		catch (SQLException se) {
			throw new BDException(11);
		}
	}
}