package dades.cercadors;

/* Imports de la classe */
import java.sql.*; 
import java.util.*;

import dades.passarelesFila.*;
import dades.excepcions.*;
import serveisComuns.baseDeDades.*;

/* Capa de Dades */
public class CercadorSoci {
	
	public static Soci cerca(String nomS) throws CDSociNoExisteix, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select edat, nomInst from soci where nomS ='"+nomS+"';");
			if (rs.next()) {
				int edat = rs.getInt("edat");
				String nomInst = rs.getString("nomInst");
				Soci ps = new Soci(nomS,edat,nomInst);
				rs.close();
				st.close();
				return ps;
	    		}
			else {
				rs.close();
				st.close();
				throw new CDSociNoExisteix();
				}
		}
		catch (SQLException se) {
			throw new BDException(10);
		}
	}
	
	public static Collection<Soci> cercaSocisBiblioteca(String nomB) throws BDException {
				
		try {
			Connection c = BaseDeDades.obteConnexio();
			List<Soci> sS = new ArrayList<>();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select nomS, edat from soci "+ 
											"where nomInst ='"+nomB+"';");
			while (rs.next()) {
				String nomS = rs.getString("nomS");
				int edat = rs.getInt("edat");
				Soci ps = new Soci(nomS,edat,nomB);
				sS.add(ps);
			}
			rs.close();
			st.close();
			return sS;
		}
		catch (SQLException se) {
			throw new BDException(10);
			
		}
	}

}