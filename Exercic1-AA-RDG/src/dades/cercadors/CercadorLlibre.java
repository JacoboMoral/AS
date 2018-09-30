package dades.cercadors;

/* Imports de la classe */
import java.sql.*; 

import dades.passarelesFila.*;
import dades.excepcions.*;
import serveisComuns.baseDeDades.*;

/* Capa de Dades */
public class CercadorLlibre {
	
	public static Llibre cerca(String isbnLl) throws CDLlibreNoExisteix, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select titol from llibre where isbn ='"+isbnLl+"';");
			if (rs.next()) {
				String titolLl = rs.getString("titol");
				Llibre pll = new Llibre(isbnLl,titolLl);
				rs.close();
				st.close();
				return pll;
	    		}
			else {
				rs.close();
				st.close();
				throw new CDLlibreNoExisteix();
				}
		}
		catch (SQLException se) {
			throw new BDException(10);
		}
	}
}