package presentacio;

/* Imports de la classe */
import java.io.*;

import serveisComuns.baseDeDades.*;
import serveisComuns.objectesAuxiliars.*;
import serveisComuns.entradaSortida.*;
import domini.controladors.*;
import domini.excepcions.*;

/* Capa de presentacio */
class Exercise1 {
	
	public static void main(String[] args) {
		try{
			
			/* Connectar-se a la base de dades */
			ControlES ces = new ControlES();
			Tuple dc = ces.obteDadesConnexio();
			BaseDeDades.connectar(dc);
			
			/* Entradad de dades a través del teclat */
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			/* Llegir quina transacció volen executar */
	    	System.out.println("1-TrCompraLlibres");
			System.out.println("2-TrBibliotecaPublicaAmbMesSocis");
			System.out.println("3-TrConsultaExemplars");
			System.out.println("4-TrNovaBiblioteca");
			System.out.println("5-Acabar:");
			int opcio = Integer.parseInt(br.readLine());
			
		    while (opcio!=5) {
		    	switch (opcio) {
		    		/* Transacció compraLlibres */
					case 1: 
		    			try {
		    				BaseDeDades.startReadWriteTr();
		    				Tuple de = ces.obteDadesEntrada("dadesEntradaCompraLlibres.txt");
		    				String isbnLl = de.consulta(1);
			    			String nomB = de.consulta(2);
			    			int q = Integer.parseInt(de.consulta(3));
			    			TrCompraLlibres trcll = new TrCompraLlibres(isbnLl,nomB,q);
			    			trcll.execute();
			    			System.out.println("Increment fet de "+q+" exemplars del llibre "+isbnLl+" a la bilbioteca '"+nomB+"'");
			    			BaseDeDades.commit();
			    		}
			    		catch (DOLlibreNoExisteix llne) {
			    			System.out.println("El llibre no existeix");	
			    			BaseDeDades.rollback();
			    		}	
			    		catch (DOBibliotecaNoExisteix bne) {
			    			System.out.println("La bilbioteca no existeix");
			    			BaseDeDades.rollback();
			    		}	
			    		catch (DOCapExemplar cee) {
			    			System.out.println("La quantitat no és correcta");
			    			BaseDeDades.rollback();
			    		}	
				   		break;
					/* Transacció biblioteca amb més socis - la transacció s'ha implementat amb passarela fila pur*/
			    	case 2: 
				   		try {
				   			BaseDeDades.startReadOnlyTr();
				   			long startTime = System.currentTimeMillis();
				   			TrBibPublicaAmbMesSocis trcbp = new TrBibPublicaAmbMesSocis();
				   			trcbp.execute();
				   			String nomB = trcbp.obteBibliotecaMesSocis();
				   			System.out.println("Biblioteca amb més socis: "+nomB);
				   			System.out.print("Durada transacció passarela fila pur ");
				   			long stopTime = System.currentTimeMillis();
				   	        long elapsedTime = stopTime - startTime;
				   	        System.out.println(elapsedTime);		/* Es mostra la durada de la transacció de la transacció */
				   			BaseDeDades.commit();
				   		}
				   		catch (DONoHiHaBiblioPubliques nbpe) {
			    			System.out.println("No existeix cap biblioteca pública");	
			    			BaseDeDades.rollback();
			    		}		    		
				   		break;	
				   	/* Transacció consulta exemplars */
				   	case 3:
				   		System.out.println("Implementar com a aprenentatge autònom");
				   		break;
				   		/* try {
		    				BaseDeDades.startReadOnlyTr();
		    				Tuple de = ces.obteDadesEntrada("dadesEntradaConsultaExemplars.txt");
		    				String isbnLl = de.consulta(1);
			    			String nomB = de.consulta(2);
				   			TrConsultaExemplars trce = new TrConsultaExemplars(isbnLl,nomB);
				   			trce.execute();
				   			int q = trce.obteQuantitatExemplars();
				   			System.out.println("La quantitat d'exemplars del llibre "+isbnLl+
				   						" que hi ha a la bilbioteca '"+nomB+"' és "+q);
				   			BaseDeDades.commit();
				   		}
		    			catch (DOLlibreNoExisteix llne) {
		    				System.out.println("El llibre no existeix");	
		    				BaseDeDades.rollback();
		    			}	
		    			catch (DOBibliotecaNoExisteix bne) {
		    				System.out.println("La bilbioteca no existeix");
		    				BaseDeDades.rollback();
		    			}	
		    			catch (DONoHiHaExemplars nhhe) {
			    			System.out.println("No hi ha exemplars del llibre a la biblioteca");	
			    			BaseDeDades.rollback();
			    		}		    		
				   		break;	*/
				   	 /* Transacció nova biblioteca */
					 case 4: 
				   		System.out.println("Implementar com a aprenentatge autònom");
				   		break;
				   		/* try {
		    				BaseDeDades.startReadWriteTr();
		    				Tuple de = ces.obteDadesEntrada("dadesEntradaNovaBiblioteca.txt");
		    				String nomB = de.consulta(1);
			    			String adrc = de.consulta(2);
			    			Boolean pub = Boolean.valueOf(de.consulta(3));
				   			TrNovaBiblioteca trce = new TrNovaBiblioteca(nomB,adrc,pub);
				   			trce.execute();
				   			System.out.println("Alta feta de la biblioteca '"+nomB+"'");
				   			BaseDeDades.commit();
				   		}
		    			catch (DOBibliotecaJaExisteix bje) {
		    				System.out.println("El biblioteca ja existeix");	
		    				BaseDeDades.rollback();
		    			}	
		    			catch (DOInstalacioJaExisteix ije) {
		    				System.out.println("La instalació ja existeix");
		    				BaseDeDades.rollback();
		    			}	
				   		break;	*/
				   	default: 
				   		System.out.println("Opció invalida");
			           	break; 
			    	}	
				System.out.println();	
		    	System.out.println("1-TrCompraLlibres");
				System.out.println("2-TrBibliotecaPublicaAmbMesSocis");
				System.out.println("3-TrConsultaExemplars");
				System.out.println("4-TrNovaBiblioteca");
				System.out.println("5-Acabar:");
				opcio = Integer.parseInt(br.readLine());
				}
		    
		    /* Desconnectar-se a la base de dades */
			BaseDeDades.desconnectar();
			
			}
			catch (IOException bde) {
				System.out.println("Error al llegir la opcio seleccionada");
    			}
			catch (NumberFormatException nfe) {
				System.out.println("Error al llegir la opcio");
				}
			catch (ErrorLecturaDadesConnexio bde) {
	    		System.out.println("Error al llegir del fitxer dadesconnexio");
	    		}
	    	catch (ErrorLecturaDadesEntrada ioe) {
	    		System.out.println("Error al llegir el fitxer de les dades entrada duna transacció");
	    		}	
	    	catch (BDException bde) {
	    		/* Errors quan es selecciona, insereix, modifica o esborra files */
	    		System.out.println(bde.getMissatge());
	    		}	
	}
}