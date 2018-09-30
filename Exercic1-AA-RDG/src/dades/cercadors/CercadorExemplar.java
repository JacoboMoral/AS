package dades.cercadors;

/* Imports de la classe */
import java.sql.*; 

import dades.passarelesFila.*;
import dades.excepcions.*;
import serveisComuns.baseDeDades.*;

/* Capa de Dades */
public class CercadorExemplar {
	
	public static Exemplar cerca(String isbnLl, String nomB) throws CDExemplarNoExistBiblio, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select quants from exemplar where isbn ='"+isbnLl+"' and nomB = '"+nomB+"';");
			if (rs.next()) {
				int q = rs.getInt("quants");
				Exemplar pe = new Exemplar(isbnLl,nomB,q);
				rs.close();
				st.close();
				return pe;
			}
			else {
				rs.close();
				st.close();
				throw new CDExemplarNoExistBiblio();
			}
		}
		catch (SQLException se) {
			throw new BDException(10);
		}
	}
}