
public class Ucebnice extends Knihy{
	public enum VekovaKategorie {PRVNI_STUPEN, DRUHY_STUPEN, STREDNI_SKOLA}; 
    private VekovaKategorie vekovaKategorie;
	public Ucebnice(String nazev, String autor, int rokvydani, boolean dostupnost, VekovaKategorie vekovaKategorie) {
		super(nazev, autor, rokvydani, dostupnost);
		this.vekovaKategorie=vekovaKategorie;
	}
	
	public VekovaKategorie getVekovaKategorie() {
		return vekovaKategorie;
	}
	public void setVekovaKategorie(VekovaKategorie vekovaKategorie) {
        this.vekovaKategorie = vekovaKategorie;
    }
	
}
