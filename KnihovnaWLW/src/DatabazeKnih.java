import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class DatabazeKnih {
	public DatabazeKnih()
	{
		ulozeneRomany=new HashMap <>();
		ulozeneUcebnice=new HashMap <>();
	
		sc=new Scanner(System.in);
	}
	
	public void setRomany()
	{
		System.out.println("Zadejte nazev knihy, autora, rok vydani a zanr knihy(FANTASY, SCIFI, DETEKTIVKY, ROMANTIKA, HISTORIE)");
		String nazev=sc.nextLine();
		String autor=sc.nextLine();
		int rokvydani=Rozhrani.jenCisla(sc);
		sc.nextLine();
		boolean dostupnost = true;
		String input = sc.nextLine();
	    input = input.trim().toUpperCase();
        try {
        	Romany.Zanry zanr = Romany.Zanry.valueOf(input);
        	ulozeneRomany.put(nazev, new Romany  (nazev, autor, rokvydani, dostupnost, zanr));	
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Tento zanr neexistuje, zadejte existujici zanr: FANTASY, SCIFI, DETEKTIVKY, ROMANTIKA, HISTORIE.");
        	}
		
	}
	public void setUcebnice()
	{
		System.out.println("Zadejte nazev knihy, autora, rok vydani a vekovou kategorii knihy(PRVNI_STUPEN, DRUHY_STUPEN, STREDNI_SKOLA)");
		String nazev=sc.nextLine();
		String autor=sc.nextLine();
		int rokvydani=Rozhrani.jenCisla(sc);
		sc.nextLine();
		boolean dostupnost = true;
		String input = sc.nextLine();
	    input = input.trim().toUpperCase();
        try {
        	Ucebnice.VekovaKategorie vekovaKategorie = Ucebnice.VekovaKategorie.valueOf(input);
        	ulozeneUcebnice.put(nazev, new Ucebnice  (nazev, autor, rokvydani, dostupnost, vekovaKategorie));	
        } 
        catch (IllegalArgumentException e) {
            System.out.println("Tato kategorie neexistuje, zadejte existujici vekovou kategorii: PRVNI_STUPEN, DRUHY_STUPEN, STREDNI_SKOLA.");
        	}
		
	}
	public boolean setAutorR(String nazev, String autor)
	{
		if (ulozeneRomany.containsKey(nazev))
		{
		ulozeneRomany.get(nazev).setAutor(autor);
		return true;
		}
		System.out.println("Tento roman neexistuje");
	return false;
	}
	
	public boolean setAutorU(String nazev, String autor)
	{
		if (ulozeneUcebnice.containsKey(nazev))
		{
		ulozeneUcebnice.get(nazev).setAutor(autor);
		return true;
		}
		else
		{
		 System.out.println("Tato ucebnice neexistuje");}
		return false;
	}
	public boolean setRokR(String nazev, int rokvydani)
	{
		if (ulozeneRomany.containsKey(nazev))
		{
		ulozeneRomany.get(nazev).setRokVydani(rokvydani);
		return true;
		}
		System.out.println("Tento roman neexistuje");
	return false;
	}
	
	public boolean setRokU(String nazev, int rokvydani)
	{
		if (ulozeneUcebnice.containsKey(nazev))
		{
		ulozeneUcebnice.get(nazev).setRokVydani(rokvydani);
		return true;
		}
		else
		{
		 System.out.println("Tato ucebnice neexistuje");}
		return false;
	}
	public boolean setDostupnostR(String nazev, boolean dostupnost)
	{
		if (ulozeneRomany.containsKey(nazev))
		{ System.out.println("Pro zmenu stavu na DOSTUPNE stisknete 1, pro zmenu stavu na VYPUJCENE stisknete 2");
		int volba=Rozhrani.jenCisla(sc);
		if(volba==1) {
		ulozeneRomany.get(nazev).setDostupnost(dostupnost=true);
		return true;
		}
		else {
		ulozeneRomany.get(nazev).setDostupnost(dostupnost=false);
		}
		return true;
		}
		else
		{
		 System.out.println("Tento roman neexistuje");}
		return false;
	}
	public boolean setDostupnostU(String nazev, boolean dostupnost)
	{
		if (ulozeneUcebnice.containsKey(nazev))
		{ System.out.println("Pro zmenu stavu na DOSTUPNE stisknete 1, pro zmenu stavu na VYPUJCENE stisknete 2");
		int volba=Rozhrani.jenCisla(sc);
		if(volba==1) {
		ulozeneUcebnice.get(nazev).setDostupnost(dostupnost=true);
		return true;
		}
		else {
		ulozeneUcebnice.get(nazev).setDostupnost(dostupnost=false);
		}
		return true;
		}
		else
		{
		 System.out.println("Tato ucebnice neexistuje");}
		return false;
	}
	public boolean vymazR(String nazev) {
		if(ulozeneRomany.remove(nazev)!=null)
			return true;
		return false;
	}
	public boolean vymazU(String nazev) {
		if(ulozeneUcebnice.remove(nazev)!=null)
			return true;
		return false;
	}
	public void vypisKnihy() {
	    List<Knihy> knihyList = new ArrayList<>();
	    knihyList.addAll(ulozeneRomany.values());
	    knihyList.addAll(ulozeneUcebnice.values());
	    knihyList.sort(Comparator.comparing(Knihy::getNazev));
	    for (Knihy kniha : knihyList) {
	        System.out.println("Nazev: " + kniha.getNazev());
	        System.out.println("Autor: " + kniha.getAutor());
	        if (kniha instanceof Romany) {
	            Romany romany = (Romany) kniha;
	            System.out.println("Zanry: " + romany.getZanry());
	        } 
	        else if (kniha instanceof Ucebnice) {
	            Ucebnice ucebnice = (Ucebnice) kniha;
	            System.out.println("Vekova kategorie: " + ucebnice.getVekovaKategorie());
	        }
	        System.out.println("Rok vydani: " + kniha.getRokVydani());
	        System.out.println("Kniha je: " + (kniha.getDostupnost() ? "Dostupna" : "Vypujcena"));
	        System.out.println("------------------------");
	    }
	}
	public void najdiKnihu() {
	    List<Knihy> knihyList = new ArrayList<>();
	    knihyList.addAll(ulozeneRomany.values());
	    knihyList.addAll(ulozeneUcebnice.values());
	    System.out.println("Zadejte nazev hledane knihy:");
	    String nazev=sc.nextLine();
	    boolean existence = false;
	    for (Knihy kniha : knihyList) {
	        if (kniha.getNazev().equals(nazev)) {
	        	existence = true;
	        	System.out.println("Nazev: " + kniha.getNazev());
		        System.out.println("Autor: " + kniha.getAutor());
		        if (kniha instanceof Romany) {
		            Romany romany = (Romany) kniha;
		            System.out.println("Zanry: " + romany.getZanry());
		        } 
		        else if (kniha instanceof Ucebnice) {
		            Ucebnice ucebnice = (Ucebnice) kniha;
		            System.out.println("Vekova kategorie: " + ucebnice.getVekovaKategorie());
		        }
		        System.out.println("Rok vydani: " + kniha.getRokVydani());
		        System.out.println("Kniha je: " + (kniha.getDostupnost() ? "Dostupna" : "Vypujcena"));
		        System.out.println("------------------------");
		    }
	        break;
        }

	    if (!existence) {
        System.out.println("Kniha s nazvem " + nazev + " neexistuje.");
	    }
	}
	public void vypisKnihyAutora() {
	    List<Knihy> knihyList = new ArrayList<>();
	    knihyList.addAll(ulozeneRomany.values());
	    knihyList.addAll(ulozeneUcebnice.values());
	    System.out.println("Zadejte nazev autora:");
	    String autor=sc.nextLine();
	    boolean existence = false;
	    knihyList.sort(Comparator.comparing(Knihy::getRokVydani));
	    for (Knihy kniha : knihyList) {
	    	if (kniha.getAutor().equals(autor)) {
	    	existence = true;
	        System.out.println("Nazev: " + kniha.getNazev());
	        System.out.println("Autor: " + kniha.getAutor());
	        if (kniha instanceof Romany) {
	            Romany romany = (Romany) kniha;
	            System.out.println("Zanry: " + romany.getZanry());
	        } 
	        else if (kniha instanceof Ucebnice) {
	            Ucebnice ucebnice = (Ucebnice) kniha;
	            System.out.println("Vekova kategorie: " + ucebnice.getVekovaKategorie());
	        }
	        System.out.println("Rok vydani: " + kniha.getRokVydani());
	        System.out.println("Kniha je: " + (kniha.getDostupnost() ? "Dostupna" : "Vypujcena"));
	        System.out.println("------------------------");
	    }
        }

	    if (!existence) {
        System.out.println("Autor " + autor + " v databazi nenalezen.");
	    }

	 }
	public void vypisKnihyDleZanru() {
		    List<Romany> romanyList = new ArrayList<>();
		    romanyList.addAll(ulozeneRomany.values());
		    System.out.println("Zadejte nazev zanru(FANTASY, SCIFI, DETEKTIVKY, ROMANTIKA, HISTORIE):");
		    String zanr = sc.nextLine();
		    zanr = zanr.trim().toUpperCase();
		    boolean spravnyZanr = false;
		    for (Romany.Zanry validZanr : Romany.Zanry.values()) {
		        if (validZanr.name().equals(zanr)) {
		            spravnyZanr = true;
		            break;
		        }
		    }
		    if (!spravnyZanr) {
		        System.out.println("Tento zanr neexistuje, zadejte existujici zanr.");
		        return;
		    }
		    System.out.println("V danem zanru: " + zanr + " jsou tyto knihy: ");
		    
		    boolean existence = false;
		    for (Romany roman : romanyList) {
		        if (roman.getZanry().toString().equals(zanr)) {
		            existence = true;
		            System.out.println("Nazev: " + roman.getNazev());
		            System.out.println("Autor: " + roman.getAutor());
		            System.out.println("Rok vydani: " + roman.getRokVydani());
		            System.out.println("Kniha je: " + (roman.getDostupnost() ? "Dostupna" : "Vypujcena"));
		            System.out.println("------------------------");
		        }
		    }

		    if (!existence) {
		        System.out.println("V danem zanru nejsou zadane zadne knihy.");
		    }
		}
	public void vypisVypujceneKnihy(){
		 List<Knihy> knihyList = new ArrayList<>();
		    knihyList.addAll(ulozeneRomany.values());
		    knihyList.addAll(ulozeneUcebnice.values());
		    boolean vypujcene = false;
		    System.out.println("Seznam vypujcenych knih: "); 
		    for (Knihy kniha : knihyList) {
		    	if (kniha.getDostupnost()==false) {
		    		vypujcene=true;
		    		if (kniha instanceof Romany) {
			            System.out.println("Roman: " );
			        } 
			        else if (kniha instanceof Ucebnice) {
			            System.out.println("Ucebnice: ");
			        }
		    		System.out.println("Nazev: " + kniha.getNazev());
			        System.out.println("Autor: " + kniha.getAutor());
			        if (kniha instanceof Romany) {
			            Romany romany = (Romany) kniha;
			            System.out.println("Zanry: " + romany.getZanry());
			        } 
			        else if (kniha instanceof Ucebnice) {
			            Ucebnice ucebnice = (Ucebnice) kniha;
			            System.out.println("Vekova kategorie: " + ucebnice.getVekovaKategorie());
			        }
			        System.out.println("Rok vydani: " + kniha.getRokVydani());
			        System.out.println("Kniha je: " + (kniha.getDostupnost() ? "Dostupna" : "Vypujcena"));
			        System.out.println("------------------------");	
		    	}
		    	}
		    if (!vypujcene) {
		        System.out.println("Zadne knihy nejsou vypujcene.");
		    }
	}
	public void ulozRomanDoSouboru()
	{  
		System.out.println("Zadejte nazev knihy pro ulozeni do souboru:");
    	String nazev = sc.nextLine();
    	Romany roman = ulozeneRomany.get(nazev);
    	if(roman !=null) {
		try (FileWriter fw = new FileWriter(roman.getNazev()+".txt")){
			 fw.write("Nazev: " + roman.getNazev() + "\n"+"Autor: " + roman.getAutor() + "\n"+"Rok vydani: " + roman.getRokVydani()+"\n"+"Zanr: " + roman.getZanry()+"\n");
			 fw.write("Dostupnost: " + (roman.getDostupnost() ? "Dostupna" : "Vypujcena") + "\n");
			 fw.close();
			 System.out.println("Kniha byla ulozena do souboru.");
		}
	   catch (IOException e) {
			        System.out.println("Do souboru se nepodarilo zapsat knihu");
			    }
    	}
    	else { 
    		System.out.println("Kniha s nazvem " + nazev + " neexistuje.");
    	}
		}
	public void ulozUcebniciDoSouboru()
	{  
		System.out.println("Zadejte nazev knihy pro ulozeni do souboru:");
    	String nazev = sc.nextLine();
    	Ucebnice ucebnice = ulozeneUcebnice.get(nazev);
    	if(ucebnice !=null) {
		try (FileWriter fw = new FileWriter(ucebnice.getNazev()+".txt")){
			 fw.write("Nazev: " + ucebnice.getNazev() + "\n"+"Autor: " + ucebnice.getAutor() + "\n"+"Rok vydani: " + ucebnice.getRokVydani()+"\n"+"Vekova kategorie: " + ucebnice.getVekovaKategorie()+"\n");
			 fw.write("Dostupnost: " + (ucebnice.getDostupnost() ? "Dostupna" : "Vypujcena") + "\n");
			 fw.close();
			 System.out.println("Kniha byla ulozena do souboru.");
		}
	   catch (IOException e) {
			        System.out.println("Do souboru se nepodarilo zapsat knihu");
			    }
    	}
    	else { 
    		System.out.println("Kniha s nazvem " + nazev + " neexistuje.");
    	}
		}
	public void prectiRomanZeSouboru() { 
		System.out.println("Zadejte nazev souboru k nacteni ucebnice:");
		String soubor = sc.nextLine();
		try (BufferedReader br = new BufferedReader(new FileReader(soubor))) {
		        String line;
		        String nazev = null;
		        String autor = null;
		        int rokVydani = 0;
		        Romany.Zanry zanr = null;
		        boolean dostupnost = false;
	
		        while ((line = br.readLine()) != null) {
		            String[] parts = line.split(": ");
		            if (parts.length >= 2) {
		                switch (parts[0]) {
		                    case "Nazev":
		                        nazev = parts[1];
		                        break;
		                    case "Autor":
		                        autor = parts[1];
		                        break;
		                    case "Rok vydani":
		                        rokVydani = Integer.parseInt(parts[1]);
		                        break;
		                    case "Zanr":
		                        zanr = Romany.Zanry.valueOf(parts[1]);
		                        break;
		                    case "Dostupnost":
		                        if (parts[1].trim().equalsIgnoreCase("Dostupna")) {
		                            dostupnost = true;
		                        } else if (parts[1].trim().equalsIgnoreCase("Vypujcena")) {
		                            dostupnost = false;
		                        }
		                        break;
		            }
		        }
		        }
	
		        if (nazev != null && autor != null && rokVydani != 0 && zanr != null) {
		        	Romany roman = new Romany(nazev, autor, rokVydani, dostupnost, zanr);
		            ulozeneRomany.put(nazev, roman); 
		            System.out.println("Roman byl nactena ze souboru.");
		        } else {
		            System.out.println("Soubor neobsahuje vsechny potrebne informace pro vytvoreni romanu.");
		        }
	
		    } catch (FileNotFoundException e) {
		        System.out.println("Soubor " + soubor + " neexistuje.");
		    } catch (IOException e) {
		        System.out.println("Chyba pri cteni ze souboru " + soubor);
		    } catch (IllegalArgumentException e) {
		        System.out.println("Chyba pri nacitani zanru ze souboru.");
		    }
	}
	public void prectiUcebniciZeSouboru() {
		 System.out.println("Zadejte nazev souboru k nacteni ucebnice:");
		 String soubor = sc.nextLine();
		 try (BufferedReader br = new BufferedReader(new FileReader(soubor))) {
		        String line;
		        String nazev = null;
		        String autor = null;
		        int rokVydani = 0;
		        Ucebnice.VekovaKategorie vekovaKategorie = null;
		        boolean dostupnost = false;

		        while ((line = br.readLine()) != null) {
		            String[] parts = line.split(": ");
		            if (parts.length >= 2) {
		                switch (parts[0]) {
		                    case "Nazev":
		                        nazev = parts[1];
		                        break;
		                    case "Autor":
		                        autor = parts[1];
		                        break;
		                    case "Rok vydani":
		                        rokVydani = Integer.parseInt(parts[1]);
		                        break;
		                    case "Vekova kategorie":
		                        vekovaKategorie = Ucebnice.VekovaKategorie.valueOf(parts[1]);
		                        break;
		                    case "Dostupnost":
		                        if (parts[1].trim().equalsIgnoreCase("Dostupna")) {
		                            dostupnost = true;
		                        } else if (parts[1].trim().equalsIgnoreCase("Vypujcena")) {
		                            dostupnost = false;
		                        }
		                        break;
		            }
		        }
		        }

		        if (nazev != null && autor != null && rokVydani != 0 && vekovaKategorie != null) {
		        	Ucebnice ucebnice = new Ucebnice(nazev, autor, rokVydani, dostupnost, vekovaKategorie);
		            ulozeneUcebnice.put(nazev, ucebnice); 
		            System.out.println("Ucebnice byla nactena ze souboru.");
		        } else {
		            System.out.println("Soubor neobsahuje vsechny potrebne informace pro vytvoreni ucebnice.");
		        }

		    } catch (FileNotFoundException e) {
		        System.out.println("Soubor " + soubor + " neexistuje.");
		    } catch (IOException e) {
		        System.out.println("Chyba pri cteni ze souboru " + soubor);
		    } catch (IllegalArgumentException e) {
		        System.out.println("Chyba pri nacitani vekove kategorie ze souboru.");
		    }
	}
	
	public static Map<String, Romany> getUlozeneRomany() {
        return ulozeneRomany;
    }
	
	 public static Map<String, Ucebnice> getUlozeneUcebnice() {
	        return ulozeneUcebnice;
	    }
	
	private Scanner sc;
	private static HashMap<String, Romany> ulozeneRomany;
	private static HashMap<String, Ucebnice> ulozeneUcebnice;
}