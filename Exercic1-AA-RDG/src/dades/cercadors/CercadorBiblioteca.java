package dades.cercadors;

/* Imports de la classe */
import java.sql.*; 
import java.util.*;
import dades.passarelesFila.*;
import dades.excepcions.*;
import serveisComuns.baseDeDades.*;

/* Capa de Dades */
public class CercadorBiblioteca {
	
	public static Biblioteca cerca(String nomB) throws CDBibliotecaNoExisteix, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select nomB, publica from biblioteca "+ 
											"where nomB ='"+nomB+"';");
			if (rs.next()) {
				Boolean bibPub = rs.getBoolean("publica");
				Biblioteca pb = new Biblioteca(nomB,bibPub);
				rs.close();
				st.close();
				return pb;
			}
			else {
				rs.close();
				st.close();
				throw new CDBibliotecaNoExisteix();
			}
		}
		catch (SQLException se) {
			throw new BDException(10);		
		}
	}
	
	public static Collection<Biblioteca> cercaPubliques() throws BDException {
		
		
		try {
			List<Biblioteca> sB = new ArrayList<>();
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select nomB, publica from biblioteca "+ 
											"where publica is true;");
			while (rs.next()) {
				String nomB = rs.getString("nomB");
				Boolean bibPub = rs.getBoolean("publica");
				Biblioteca pb = new Biblioteca(nomB,bibPub);
				sB.add(pb);
			}
			rs.close();
			st.close();
			return sB;
		}
		catch (SQLException se) {
			throw new BDException(10);
			
		}
	}
}