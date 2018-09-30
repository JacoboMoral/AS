package domini.controladors;

/* Imports de la classe */

import dades.cercadors.*;
import dades.passarelesFila.*;
import dades.excepcions.*;
import domini.excepcions.*;
import serveisComuns.baseDeDades.*;


/* Capa de Domini */
public class TrCompraLlibres extends Transaccio {
	
	private String isbnLlibre;
	
	private String nomBiblioteca;
	
	private int quantitat;
	
	public TrCompraLlibres(String isbnLl, String nomB, int q) {
	    isbnLlibre = isbnLl;
	    nomBiblioteca = nomB;
	    quantitat = q;
	}
	
	public void execute() throws DOLlibreNoExisteix, DOBibliotecaNoExisteix, DOCapExemplar, BDException {
		if (quantitat<1) throw new DOCapExemplar();
		
		try {
			/* Dins del mètode cerca saltarà l'excepció llibreNoExisteix */
			CercadorLlibre.cerca(isbnLlibre);
		}
		catch (CDLlibreNoExisteix lne) {
			/* Per evitar que la capa de presentacio hagi de veure la capa de dades */
			throw new DOLlibreNoExisteix();
		}		
		
		try {
			/* Dins del mètode cerca saltarà l'excepció bibliotecaNoExisteix */
			CercadorBiblioteca.cerca(nomBiblioteca);
		}
		catch (CDBibliotecaNoExisteix bne) {
			/* Per evitar que la capa de presentacio hagi de veure la capa de dades */
			throw new DOBibliotecaNoExisteix();
		}
				
		try {
			Exemplar pe = CercadorExemplar.cerca(isbnLlibre,nomBiblioteca);
			int quants = pe.obteQuants();
			pe.posaQuants(quants+quantitat);
			pe.modifica();
			}
		catch (CDExemplarNoExistBiblio exe) {
			Exemplar pen = new Exemplar(isbnLlibre,nomBiblioteca,quantitat);
			try {
				pen.insereix();
			}
			catch(CDExemplarJaExistentsBiblio eje) {
				/* Aquest error no hauria de passar mai si el cercador funciona correctament */
				/* i si les transaccions estan ben aillades */
				throw new BDException(11);
			}
			}
	}
}