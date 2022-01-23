package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import excepcions.LlistaBuida;
import tads.Activitat;
import tads.ContingutExcursio;
import tads.Excursio;
import tads.ExcursioContractada;
import tads.LlistaDinamica;
import tads.LlistaJUCollection;
import tads.TADLlistaGenerica;
import tads.TADMultillistaGenerica;
import tads.Turista;

/**
 * Classe principal (main) d'on cridem a la resta de mètodes
 * @author Marc Isidre Balada Fibla
 * @since 30/03/2018
 *
 */
public class Consultar {
	
	static int MAX=12407;
	//static int MAX_ACTIVITATS=15;
	static Scanner teclat=new Scanner(System.in);
	
	public static void main(String[] args) throws LlistaBuida {
		
		//declarem les variables on assignarem el contingut dels fitxers
		String[] linies1, linies2;
		TADLlistaGenerica<Excursio> llistaExc = null;
		TADLlistaGenerica<Activitat> llistaActs = null;
		TADLlistaGenerica<Turista> llistaTuristes = null;
		//declarem 2 variables per als noms dels fitxers
		String f1, f2;
		//declarem 2 booleans per al tractament de null pointer exceptions
		boolean nullpointer = false;
		//declarem una variable on assignar l'opcio escollida d'implementacio de llista
		int opcio;
		//declarem mes variables per als temps d'execucio
		long tempsIni, tempsFi;
		//declarem les multillistes
		TADMultillistaGenerica<Excursio,Activitat> multAuxCE = new TADMultillistaGenerica<Excursio,Activitat>();
		TADMultillistaGenerica<Excursio,Turista> multAuxEC = new TADMultillistaGenerica<Excursio,Turista>();
		ContingutExcursio multiCE = new ContingutExcursio(multAuxCE);
		ExcursioContractada multiEC = new ExcursioContractada(multAuxEC);
		
		//demanem a l'usuari els noms dels fitxers a llegir 
		System.out.println("Escrigui el nom del fitxer amb les dades d'activitats i excursions:\t");
		f1 = teclat.nextLine(); //els llegim
		linies1 = llegirLiniesFitxer(MAX,f1); //els assignem a les variables
		//for (int k=0; k<MAX; k++) System.out.println(linies1[k]); //Comprovacio
		
		//repetim per al segon fitxer
		System.out.println("\nEscrigui el nom del fitxer amb les dades de turistes i excursions:\t");
		f2 = teclat.nextLine();
		linies2 = llegirLiniesFitxer(MAX,f2);
		//for (int k=0; k<MAX; k++) System.out.println(linies2[k]); //Comprovacio
		
		//demanem a l'usuari quina implementacio volem usar de la llista
		System.out.println("\nQuina implementacio voldria fer servir?\n\t"
				+ "1. Llista amb punters ordenada\n\t2.Estructura Java.Util"
				+ ".Collection\n");
		//i assignem la llista generica d'acord amb la demanda
		do {
		opcio = Integer.parseInt(teclat.nextLine());
		if (opcio == 1) {
			System.out.println("opcio 1: LlistaDinamica\n");
			llistaExc = new LlistaDinamica<Excursio>();
			llistaActs = new LlistaDinamica<Activitat>();
			llistaTuristes = new LlistaDinamica<Turista>();
		}
		else if (opcio == 2) {
			
			System.out.println("opcio 2: Llista Java Util Collection\n");
			llistaExc = new LlistaJUCollection<Excursio>();
			llistaActs = new LlistaJUCollection<Activitat>();
			llistaTuristes = new LlistaJUCollection<Turista>();
		}
		else System.out.println("Opcio incorrecta! Esculli una opcio correcta:\n\t"
				+ "1. Llista amb punters ordenada\n\t2.Estructura Java.Util"
				+ ".Collection\n");
		}while((opcio != 1)&&(opcio != 2));
		
		//creem les multillistes i les assignem a les classes corresponents
		for (int i = 0; i < linies1.length; i++) tractarFitxer1(linies1[i], llistaExc, llistaActs, multiCE);			
		for (int i = 0; i < linies2.length; i++) multiEC = tractarFitxer2(linies2[i], llistaExc, llistaTuristes, multiCE, multiEC);		
		
		do {
			System.out.println("\nMenu Principal\n\n\tEscolleixi una opcio:\n"
				+ "1. Mostrar les activitats i preu total d'una excursio mitjançant el seu codi"
				+ "\n2. Mostrar les excursions que ofereix una activitat mitjançant el seu codi"
				+ "\n3. Mostrar les activitats (d'un mateix codi) que han realitzat menys del nombre de turistes especificat"
				+ "\n4. Mostrar els turistes d'una excursio i el nombre total que n'hi ha en aquesta"
				+ "\n5. Mostrar les excursions d'un turista mitjançant el seu identificador"
				+ "\n6. Mostrar l'import total de les excursions que ha realitzat un turista especificant el seu identificador"
				+ "\n7. Consultar el nombre total d'activitats, nombre total de turistes i Nnombre total d'excursions"
				+ "\n8. Sortir\n");
			opcio = Integer.parseInt(teclat.nextLine());
			
			String id;
			int num;
			
			switch(opcio) {
				
				case 1: 
					 System.out.println("Introdueixi el codi de l'excursio:\n");
					 id = teclat.nextLine();
					
					 tempsIni = System.nanoTime(); 
					 //System.out.println(multiCE);
					 Excursio exc = multiCE.BuscarExc(id);
					 LlistaDinamica<Activitat> act = multiCE.retornarActivitats(exc);
					 
					 Iterator<Activitat> it = act.iterator();
					 
					 System.out.println("L'excursio "+exc.getNomExcursio()+" te les seguents activitats:\n");
					 while ((it.hasNext())&&(!nullpointer)) {
						 try {
							 System.out.println(it.next());
						 }catch(NullPointerException e) {
							 nullpointer=true;
						 }
					 }
					 System.out.println("\nL'excursio "+exc.getNomExcursio()+" ha tingut un preu de "+exc.getPreu()+" euros");
					 
					 tempsFi = System.nanoTime();
					 tempsFi = tempsFi - tempsIni;
					 System.out.println("\nTemps d'execucio mitja (ns): "+tempsFi);
					 break;
				
				case 2:
					System.out.println("Introdueixi el codi de l'activitat:\n");
					id = teclat.nextLine();
					
					tempsIni = System.nanoTime(); 
					Excursio exc2 = new Excursio(null, null);
					Activitat act2 = multiCE.BuscarAct(id);
					LlistaDinamica<Excursio> llExc = multiCE.retornarExcursions(act2);
					Iterator<Excursio> itExc = llExc.iterator();
					
					while(itExc.hasNext()) {
						exc2 = itExc.next();
						System.out.println("El preu de l'excursio "+exc2.getNomExcursio()+" es de "+exc2.getPreu());
					}
					
					tempsFi = System.nanoTime();
					tempsFi = tempsFi - tempsIni;
					System.out.println("\nTemps d'execucio mitja (ns): "+tempsFi);
					break;
					
				case 3:
					//Sembla un error en l'enunciat, no podem retornar activitats diferents amb el mateix codi
					System.out.println("Introdueix el nombre de turistes que tindran com a molt les activitats que es mostraran ");
					num = Integer.parseInt(teclat.nextLine());
					
					tempsIni = System.nanoTime();
					Activitat act3 = new Activitat(null, null, 0);
					int sumaTur;
					Iterator<Activitat> itAct = llistaActs.iterator();
					while (itAct.hasNext()) {
						act3 = itAct.next();
						LlistaDinamica<Excursio> llistaExc3 = multiCE.retornarExcursions(act3);
						Iterator<Excursio> itExc3 = llistaExc3.iterator();
						sumaTur=0;
						while (itExc3.hasNext()) {
							Excursio exc3 = itExc3.next();
							sumaTur = sumaTur + exc3.getNumTur();
						}
						if (sumaTur < num) System.out.println("Activitat: "+act3.getNomActivitat()+" Num. Turistes: "+sumaTur);
					}

					tempsFi = System.nanoTime();
					tempsFi = tempsFi - tempsIni;
					System.out.println("\nTemps d'execucio mitja (ns): "+tempsFi);
					break;
					
				case 4:
					System.out.println("Introdueix el codi de l'excursio ");
					id = teclat.nextLine();
					
					tempsIni = System.nanoTime();
					int sumaTur4 = 0;
					Excursio exc4 = multiCE.BuscarExc(id);
					LlistaDinamica<Turista> llistaTur = multiEC.retornarTuristes(exc4);
					Iterator<Turista> itTur = llistaTur.iterator();
					
					while (itTur.hasNext()) {
						sumaTur4++;
						System.out.println(itTur.next());
					}
					System.out.println("\nEl num total de turistes es "+sumaTur4);
					
					tempsFi = System.nanoTime();
					tempsFi = tempsFi - tempsIni;
					System.out.println("\nTemps d'execucio mitja (ns): "+tempsFi);
					break;
					
				case 5:
					System.out.println("Introdueix l'id del turista ");
					int numID = Integer.parseInt(teclat.nextLine());
					
					tempsIni = System.nanoTime();
					Turista tur = multiEC.BuscarTur(numID);
					LlistaDinamica<Excursio> llistaExc5 = multiEC.retornarExcursions(tur);
					Iterator<Excursio> itExc5 = llistaExc5.iterator();
					while (itExc5.hasNext()) System.out.println(itExc5.next());
					
					tempsFi = System.nanoTime();
					tempsFi = tempsFi - tempsIni;
					System.out.println("\nTemps d'execucio mitja (ns): "+tempsFi);
					break;
				
				case 6:
					System.out.println("Introdueix l'id del turista ");
					num = Integer.parseInt(teclat.nextLine());
					
					tempsIni = System.nanoTime();
					double SumaPreu = 0;
					Activitat act6 = new Activitat(null, null, 0);
					Excursio exc6 = new Excursio(null, null);
					Turista tur6 = multiEC.BuscarTur(num);
					LlistaDinamica<Excursio> llistaTur6 = multiEC.retornarExcursions(tur6);
					Iterator<Excursio> itExc6 = llistaTur6.iterator();
					while(itExc6.hasNext()) {
						exc6 = itExc6.next();
						String codiExc = exc6.getCodiExcursio();
						exc6 = multiEC.BuscarExc(codiExc);
						LlistaDinamica<Activitat> actsAux = multiCE.retornarActivitats(exc6);
						Iterator<Activitat> itAct6 = actsAux.iterator();
						while(itAct6.hasNext()) {
							act6 = itAct6.next();
							SumaPreu = SumaPreu + act6.getPreuActivitat();
						}	
					}
					System.out.println("L'import total de les excursions realitzades pel turista "
					+tur6.getNomTurista()+" "+tur6.getCognomTurista()+" es de "+SumaPreu+" euros");
					
					tempsFi = System.nanoTime();
					tempsFi = tempsFi - tempsIni;
					System.out.println("\nTemps d'execucio mitja (ns): "+tempsFi);
					break;
					
				case 7:
					tempsIni = System.nanoTime();
					
					System.out.println("Num. activitats: "+llistaActs.numElem()
					+"\nNum. turistes: "+llistaTuristes.numElem()
					+"\nNum. excursions: "+llistaExc.numElem());
					
					tempsFi = System.nanoTime();
					tempsFi = tempsFi - tempsIni;
					System.out.println("\nTemps d'execucio mitja (ns): "+tempsFi);
					break;
				
				case 8: System.out.println("Ha escollit l'opcio 'Sortir'");
					break;
				
				default: System.out.println("Aquesta opcio no es troba al menu! Escolleixi alguna opcio del menu:\n");
					break;
			}
		}while(opcio != 8);
		
		teclat.close();
	} //FI DEL MAIN
	
	/**
	 * Métode per llegir i guardar el contingut del fitxer
	 * @param numLinies - linies que llegirem del fitxer
	 * @param f - nom del fitxer indicat per l'usuari
	 * @return Un String amb el contingut del fitxer
	 */
	private static String[] llegirLiniesFitxer(int numLinies, String f) {
		String[] resultat;
		if (numLinies>=1001970) numLinies=1001970;
		if (numLinies<0) numLinies=0;
		resultat=new String[numLinies];
		try {
			BufferedReader fin=new BufferedReader(new FileReader(f+".csv"));
			for (int i=0; i<numLinies; i++) {
				resultat[i]=fin.readLine();
				//System.out.println(resultat[i]);
			}
			fin.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("No s'ha trobat el fitxer amb les excursions i activitats");
		}
		catch (IOException e) {
			System.out.println("S'ha produit un error al llegir el fitxer amb les dades de les excursions i activitats");
		}
		return resultat;
	}
	
	/**
	 * Assigna cada línia llegida anteriorment a les seves respectives variables genèriques
	 * @param llista String amb tota la lectura del fitxer
	 * @param exc llista genèrica amb les excursions
	 * @param acts llista genèrica amb les activitats
	 * @throws LlistaBuida si no hi ha res per tractar a la llista
	 */
	public static void tractarFitxer1(String llista, TADLlistaGenerica<Excursio> exc, 
			TADLlistaGenerica<Activitat> acts, ContingutExcursio multce) throws NullPointerException, LlistaBuida {
		
		String nomExc, codiExc;
		String codiAct = new String();
		String nomAct = new String();
		double preuAct;
		Excursio auxExc;
		Activitat auxAct;
		StringTokenizer linTok;
		
		try {	
			linTok = new StringTokenizer(llista, ";");
						
			try {
				codiExc = linTok.nextToken(); 
				nomExc = linTok.nextToken();
				auxExc = new Excursio(codiExc, nomExc);	
				exc.inserir(auxExc);
				
				while (linTok.hasMoreTokens()) {
					codiAct = linTok.nextToken();
					nomAct = linTok.nextToken();
					preuAct = Double.parseDouble(linTok.nextToken());	
					auxAct = new Activitat(codiAct, nomAct, preuAct);
					acts.inserir(auxAct);
					multce.relacionarExcAct(auxExc,auxAct); 
				}
				
			}catch (NoSuchElementException e) {
				e.printStackTrace();
				
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metode per tractar el fitxer de turistes i excursions. Tambe tracta el cas de l'excursio repetida.
	 * @param llista
	 * @param exc
	 * @param tur
	 * @param ce
	 * @param multec
	 * @return
	 * @throws NullPointerException
	 * @throws LlistaBuida
	 */
	public static ExcursioContractada tractarFitxer2(String llista,TADLlistaGenerica<Excursio> exc,
			TADLlistaGenerica<Turista> tur, ContingutExcursio ce, ExcursioContractada multec) throws NullPointerException, LlistaBuida{
		
		String nomExc, codiExc;
		int codiTur;
		String nomTur = new String();
		String cognom;
		Excursio auxExc, excRepetida;
		Turista auxTur;
		StringTokenizer linTok;
		
		try {	
			linTok = new StringTokenizer(llista, ";");
						
			try {
				codiExc = linTok.nextToken(); 
				nomExc = linTok.nextToken();	
				auxExc = new Excursio(codiExc, nomExc);	
				excRepetida = ce.BuscarExc(codiExc);
				if(excRepetida != null) auxExc.setNumTur(excRepetida.getNumTur());
				exc.inserir(auxExc);
				
				codiTur = Integer.parseInt(linTok.nextToken());
				nomTur = linTok.nextToken();
				cognom = linTok.nextToken();				
				auxTur = new Turista(codiTur, nomTur, cognom);				
				tur.inserir(auxTur);
				multec.relacionarExcTur(auxExc, auxTur); 

			}catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		return multec;
	}
	
}
