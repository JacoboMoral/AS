package domini.controladors;

/* Imports de la classe */
import java.util.Collection;

import dades.cercadors.*;
import dades.passarelesFila.*;
import domini.excepcions.*;
import serveisComuns.baseDeDades.*;


/* Capa de Domini */
public class TrBibPublicaAmbMesSocis extends Transaccio {
	
	private String nomBlibliotecaAmbMesSocis;
	
	public TrBibPublicaAmbMesSocis() {
		nomBlibliotecaAmbMesSocis = null;
	}
	
	public void execute() throws DONoHiHaBiblioPubliques, BDException {
		
		int max = 0;
		
		String nomBMax = null;
		
		Collection<Biblioteca> sB = CercadorBiblioteca.cercaPubliques();
		/* Dins del mètode cerca saltarà l'excepció bibliotecaNoExisteix */
				
		if (sB.isEmpty()) throw new DONoHiHaBiblioPubliques();
		
		for (Biblioteca pb: sB) {
			String nomB = pb.obteNom();
			Collection<Soci> sS = CercadorSoci.cercaSocisBiblioteca(nomB);
			if (sS.size() > max) {
				max = sS.size();
				nomBMax = nomB;
			}
		}
		nomBlibliotecaAmbMesSocis = nomBMax;
	}
	
	public String obteBibliotecaMesSocis() {
		return nomBlibliotecaAmbMesSocis;
	}
}