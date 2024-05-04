
public class Romany extends Knihy {
		public enum Zanry {FANTASY, SCIFI, DETEKTIVKY, ROMANTIKA, HISTORIE };
        private Zanry zanry;
		public Romany(String nazev, String autor, int rokvydani, boolean dostupnost, Zanry zanry) {
			super(nazev, autor, rokvydani, dostupnost);
			this.zanry=zanry;
		}
		
		public Zanry getZanry() {
			return zanry;
		}
		public void setZanry(Zanry zanry) {
	        this.zanry = zanry;
	    }
}
