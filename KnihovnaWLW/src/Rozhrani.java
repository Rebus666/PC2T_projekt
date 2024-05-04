import java.util.InputMismatchException;
import java.util.Scanner;

public class Rozhrani {
	private static String nazev;
	private static String autor;
	private static int rokvydani;
	private static boolean dostupnost=true;
	public static int jenCisla(Scanner sc) 
	{
		int volba = 0;
		try
		{
			volba = sc.nextInt();
		}
		catch(InputMismatchException e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("Zadejte prosim cislo! ");
			sc.nextLine();
			volba = jenCisla(sc);
		}
		return volba;
	}
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int volba;
		boolean run=true;
		DatabazeKnih ulozeneKnihy=new DatabazeKnih();
		sqlKodiky.createTableR();
		sqlKodiky.createTableU();
		sqlKodiky.nactiRomanyQuery(ulozeneKnihy);
		sqlKodiky.nactiUcebniceQuery(ulozeneKnihy);
		while(run)
		{
			System.out.println("Zvolte funkci ze seznamu:");
			System.out.println("1 .. vlozeni nove knihy");
			System.out.println("2 .. uprava existujici knihy");
			System.out.println("3 .. odstraneni existujici knihy");
			System.out.println("4 .. zmena stavu knihy");
			System.out.println("5 .. vypis vsech knih databaze v abecednim poradi");
			System.out.println("6 .. vyhledani knihy podle nazvu");
			System.out.println("7 .. vypis vsech knih podle autora");
			System.out.println("8 .. vypis vsech knih daneho zanru");
			System.out.println("9 .. vypis vsech vypujcenych knih");
			System.out.println("10 .. ulozeni informaci o knize do souboru");
			System.out.println("11 .. nacteni informaci o knize ze souboru");
			System.out.println("12 .. ukonceni aplikace");
			volba=jenCisla(sc);
			switch(volba)
			{
				
			case 1:{
				System.out.println("Pro zadani ROMANU stisknete 1, pro zadani UCEBNICE stisknete 2:");
				int typ = jenCisla(sc);
				sc.nextLine();
				if(typ==1)
		        		{
						ulozeneKnihy.setRomany();
		        		} 
				else if (typ == 2) { 
		        		 ulozeneKnihy.setUcebnice();
		        		} 
				else {
					System.out.println("Neplatna volba.");
		        	 }
		          break;
			}	
			case 2:{
			System.out.println("Pro upravu existujiciho ROMANU stisknete 1, pro upraveni UCEBNICE 2");
			int typ = jenCisla(sc);
	        if(typ==1)
	        {
	        	System.out.println("Pro upravu AUTORA stisknete 1, ROKU_VYDANI 2, DOSTUPNOSTI 3");
	        	int uprava = jenCisla(sc);
	        	sc.nextLine();
	                if(uprava==1) {
	                    	System.out.println("Zadejte jmeno upravovane knihy a nove jmeno autora");
	                    	nazev=sc.nextLine();
	                    	autor=sc.nextLine();
	                    	ulozeneKnihy.setAutorR(nazev, autor);
		                    break;
	                		}
	                else if(uprava==2) {
	                    	System.out.println("Zadejte jmeno upravovane knihy a novy rok vydani");
	                    	nazev=sc.nextLine();
	                    	rokvydani=jenCisla(sc);
	                    	ulozeneKnihy.setRokR(nazev, rokvydani);
	                    	break;
	                		}
	                else if(uprava==3) {
	                		System.out.println("Zadejte jmeno upravovane knihy");
	                		nazev=sc.nextLine();
	                		ulozeneKnihy.setDostupnostR(nazev, dostupnost);
	                		break;
	                		}
	                else {
						System.out.println("Neplatna volba.");
			        	 }
	        }
	        else if(typ==2) 
	        {
	        	System.out.println("Pro upravu AUTORA stisknete 1, ROKU_VYDANI 2, DOSTUPNOSTI 3");
	        	int uprava = jenCisla(sc);
	        	sc.nextLine();
	        	if(uprava==1) {
                	System.out.println("Zadejte jmeno upravovane knihy a nove jmeno autora");
                	nazev=sc.nextLine();
                	autor=sc.nextLine();
                	ulozeneKnihy.setAutorU(nazev, autor);
                    break;
            		}
	        	else if(uprava==2) {
                	System.out.println("Zadejte jmeno upravovane knihy a novy rok vydani");
                	nazev=sc.nextLine();
                	rokvydani=jenCisla(sc);
                	ulozeneKnihy.setRokU(nazev, rokvydani);
                	break;
            		}
	        	else if(uprava==3) {
            		System.out.println("Zadejte jmeno upravovane knihy");
            		nazev=sc.nextLine();
            		ulozeneKnihy.setDostupnostU(nazev, dostupnost);
            		break;
            		}
	        	else {
	        		System.out.println("Neplatna volba.");
	        		}
	        	}
	        else {
				System.out.println("Neplatna volba.");
	        	 }
			}
			case 3:{
				System.out.println("Pro odstraneni ROMANU stisknete 1, UCEBNICE stisknete 2:");
				int typ = jenCisla(sc);
				sc.nextLine();
				if(typ==1)
		        	{
					System.out.println("Zadejte jmeno knihy k odstraneni");
                	nazev=sc.nextLine();
                	if(ulozeneKnihy.vymazR(nazev))
                		System.out.println("Kniha " + nazev +" byla odstranena");
                	else
                		System.out.println("Kniha " + nazev +" neexistuje");
		        		}
				else if(typ == 2) {
					System.out.println("Zadejte jmeno knihy k odstraneni");
                	nazev=sc.nextLine();
                	if(ulozeneKnihy.vymazU(nazev))
                		System.out.println("Kniha " + nazev +" byla odstranena");
                	else
                		System.out.println("Kniha " + nazev +" neexistuje");
		        		}
				else {
					System.out.println("Neplatna volba.");
		        	 }
		        break;
				}
			case 4:{
				System.out.println("Pro zmenu dostupnosti ROMANU stisknete 1, UCEBNICE stisknete 2:");
				int typ = jenCisla(sc);
				sc.nextLine();
				if(typ==1)
		        	{
					System.out.println("Zadejte jmeno upravovane knihy");
                	nazev=sc.nextLine();
                	ulozeneKnihy.setDostupnostR(nazev, dostupnost);
		        
		        		}
		        
				else if (typ==2)
		        	{ 
		        	System.out.println("Zadejte jmeno upravovane knihy");
                    nazev=sc.nextLine();
                    ulozeneKnihy.setDostupnostU(nazev, dostupnost);
		        	 	}
				else {
					System.out.println("Neplatna volba.");
		        	 }
		        break;
				}	
			case 5:{
				ulozeneKnihy.vypisKnihy();
				break;
			}
			case 6:{
				ulozeneKnihy.najdiKnihu();
				break;
			}
			case 7:{
				ulozeneKnihy.vypisKnihyAutora();
				break;
			}
			case 8:{
				ulozeneKnihy.vypisKnihyDleZanru();
				break;
			}
			case 9:{
				ulozeneKnihy.vypisVypujceneKnihy();
				break;
			}
			case 10:{
				System.out.println("Pro ulozeni ROMANU stisknete 1, pro ulozeni UCEBNICE stisknete 2:");
				int typ = jenCisla(sc);
				sc.nextLine();
		        if(typ==1)
		        {
					ulozeneKnihy.ulozRomanDoSouboru();
			        break;
				}
		        else if(typ==2) {
		        	ulozeneKnihy.ulozUcebniciDoSouboru();
			        break;
		        }
		        else {
					System.out.println("Neplatna volba.");
		        	 }
		        break;
			}
			case 11:{
				System.out.println("Pro nacteni souboru ROMAN stisknete 1, pro ulozeni UCEBNICE stisknete 2:");
				int typ = jenCisla(sc);
				sc.nextLine();
		        if(typ==1)
		        {
					ulozeneKnihy.prectiRomanZeSouboru();
			        break;
				}
		        else if(typ==2) {
		        	ulozeneKnihy.prectiUcebniciZeSouboru();
			        break;
		        }
		        else {
					System.out.println("Neplatna volba.");
		        	 }
		        break;
			}
			case 12:{
				run=false;
				break;
			}
	        	
		}
	}
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				sqlKodiky.smazRomanyQuery();
				for (Romany roman : DatabazeKnih.getUlozeneRomany().values()) {
					sqlKodiky.ulozRomanyQuery(roman); 
					}
					
				sqlKodiky.smazUcebniceQuery();
				for (Ucebnice ucebnice : DatabazeKnih.getUlozeneUcebnice().values()) {
					sqlKodiky.ulozUcebniceQuery(ucebnice);
			}
				databazeSQL.closeConnection();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}));
}
}




	


