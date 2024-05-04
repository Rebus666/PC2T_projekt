

public class Knihy {
	private String nazev;
	private String autor;
	private int rokvydani;
	private boolean dostupnost;

	
	public Knihy(String nazev, String autor, int rokvydani, boolean dostupnost ) {
		this.nazev = nazev;
		this.autor = autor;
		this.rokvydani = rokvydani;
		this.dostupnost = dostupnost;
	}
	public String getNazev() {
		return nazev;
	}
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getRokVydani() {
		return rokvydani;
	}
	public void setRokVydani(int rokvydani) {
		this.rokvydani = rokvydani;
	}
	public boolean getDostupnost() {
		return dostupnost;
	}
	public void setDostupnost(boolean dostupnost) {
		this.dostupnost = dostupnost;
	}
}