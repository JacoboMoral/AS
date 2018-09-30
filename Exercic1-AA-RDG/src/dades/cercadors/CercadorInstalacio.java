package dades.cercadors;

/* Imports de la classe */
import java.sql.*; 
import dades.passarelesFila.*;
import dades.excepcions.*;
import serveisComuns.baseDeDades.*;

/* Capa de Dades */
public class CercadorInstalacio {
	
	public static Instalacio cerca(String nomI) throws CDInstalacioNoExisteix, BDException {
		try {
			Connection c = BaseDeDades.obteConnexio();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select adreca from instalacio "+ 
											"where nomI ='"+nomI+"';");
			if (rs.next()) {
				String adrc = rs.getString("adreca");
				Instalacio pi = new Instalacio(nomI,adrc);
				rs.close();
				st.close();
				return pi;
			}
			else {
				rs.close();
				st.close();
				throw new CDInstalacioNoExisteix();
			}
		}
		catch (SQLException se) {
			throw new BDException(10);		
		}
	}

}