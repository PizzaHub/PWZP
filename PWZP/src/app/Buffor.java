package app;

/**
 * Klasa Buffor
 * Służy do przechowywania danych, zmiennych, z których korzystają inne klasy.
 *
 */
class Buffor {
	
	String[][] dane = {
			{"Margherita","9.50", "17.80", "19.90"},	
			{"Capriciosa","15.50", "22.80", "27.00"},
			{"Califfo","19.00", "27.90", "33.00"},
			{"Calzone","19.00", "28.30", "33.00"},	
			{"Decoro", "18.30", "26.70", "31.80"},
			{"Pepe Roso","15.40", "22.80", "26.80"},
			{"Napoletana","17.00", "24.60", "29.60"},	
			{"Piacere","17.60", "26.90", "30.60"},
			{"Roma", "16.90", "24.80", "29.30"},
		    {"Polska","18.10", "25.60", "31.50"},	
			{"Margherita","9.50", "17.80", "19.90"},
			{"Margherita","9.50", "17.80", "19.90"},
		    {"Margherita","9.50", "17.80", "19.90"},
			{"Margherita","9.50", "17.80", "19.90"},
			{"Margherita","9.50", "17.80", "19.90"},
	};
	
	String[] skladniki = {
			"ser, sos pomidorowy, oregano",	
			"ser, sos pomidorowy, szynka, pieczarki," +"\n"+ "oregano",
			"ser, sos pomidorowy, szynka, kabanosy," +"\n"+ "papryka konserwowa, oliwki zielone, oregano",
			"ser, sos pomidorowy, szynka, kabanosy," +"\n"+ "boczek wędzony, salami, oregano",	
			"ser, sospomidorowy, szynka, pieczarki," +"\n"+ "papryka konserwowa, czosnek, oregano",
			"ser, sos pomidorowy, salami, oregano" +"\n"+ "papryka konserwowa",
			"ser, sos pomidorowy, salami, oliwki zielone",	
			"ser, sos pomidorowy, salami, boczek wędzony," +"\n"+ "cebula biała, kukurydza, oregano",
			"ser, sos pomidorowy, salami, kabanosy," +"\n"+ "papryka konserwowa, oregano",
		    "ser, sos pomidorowy, szynka, kiełbasa," +"\n"+ "kabanosy, biała cebula, świeża papryka, oregano",	
			"ser, sos pomidorowy, oregano",
			"ser, sos pomidorowy, oregano",
		    "ser, sos pomidorowy, oregano",
			"ser, sos pomidorowy, oregano",
			"ser, sos pomidorowy, oregano",
	};
	
	private static int numerRzedu, liczbaPizz, dodaj, rozmiarZamowienia;
	private static float cena, kosztElementu, kosztLaczny, kosztLacznyBezDostawy;	
	private static float kosztWlasnej, kosztLacznyWlasnej;
	private static boolean dostawa=false;
	
//*************************************************************************************************************************************	
	
	
	static boolean isDostawa() {
		return dostawa;
	}

	static void setDostawa(boolean dostawa) {
		Buffor.dostawa = dostawa;
	}

	static int getRozmiarZamowienia() {
		return rozmiarZamowienia;
	}

	static void setRozmiarZamowienia(int rozmiarZamowienia) {
		Buffor.rozmiarZamowienia = rozmiarZamowienia;
	}
	
	static float getKosztLacznyBezDostawy() {
		return kosztLacznyBezDostawy;
	}

	static void setKosztLacznyBezDostawy(float kosztLacznyBezDostawy) {
		Buffor.kosztLacznyBezDostawy = kosztLacznyBezDostawy;
	}
	
	static int getDodaj() {
		return dodaj;
	}

	static void setDodaj(int dodaj) {
		Buffor.dodaj = dodaj;
	}

	private static String nazwaPizzy, rozmiarPizzy, sos, paragonBezLacznegoKosztu;
	static String getParagonBezLacznegoKosztu() {
		return paragonBezLacznegoKosztu;
	}

	static void setParagonBezLacznegoKosztu(String paragonBezLacznegoKosztu) {
		Buffor.paragonBezLacznegoKosztu = paragonBezLacznegoKosztu;
	}
	
	static float getCena() {
		return cena;
	}

	static void setCena(float cena) {
		Buffor.cena = cena;
	}

	static String getSos() {
		return sos;
	}

	static float getKosztElementu() {
		return kosztElementu;
	}

	static float getKosztWlasnej() {
		return kosztWlasnej;
	}
	
	static void setKosztElementu(float kosztElementu) {
		Buffor.kosztElementu = kosztElementu;
	}
	
	static void setKosztWlasnej(float kosztWlasnej) {
		Buffor.kosztWlasnej = kosztWlasnej;
	}
	
	static float getKosztLaczny() {
		return kosztLaczny;
	}

	static void setKosztLaczny(float kosztLaczny) {
		Buffor.kosztLaczny = kosztLaczny;
	}
	
	static float getKosztLacznyWlasnej() { 
		return kosztLacznyWlasnej;
	}

	static void setKosztLacznyWlasnej(float kosztLacznyWlasnej) {
		Buffor.kosztLacznyWlasnej = kosztLacznyWlasnej;
	}

	static void setSos(String sos) {
		Buffor.sos = sos;
	}

	static int getLiczbaPizz() {
		return liczbaPizz;
	}

	static void setLiczbaPizz(int liczbaPizz) {
		Buffor.liczbaPizz = liczbaPizz;
	}

	static String getRozmiarPizzy() {
		return rozmiarPizzy;
	}

	static void setRozmiarPizzy(String rozmiarPizzy) {
		Buffor.rozmiarPizzy = rozmiarPizzy;
	}

	static String getNazwaPizzy() {
		return nazwaPizzy;
	}

	static void setNazwaPizzy(String nazwaPizzy) {
		Buffor.nazwaPizzy = nazwaPizzy;
	}

	String[][] getDane() {
		return dane;
	}

	void setDane(String[][] dane) {
		this.dane = dane;
	}

	static int getNumerRzedu() {
		return numerRzedu;
	}

	static void setNumerRzedu(int numerRzedu) {
		Buffor.numerRzedu = numerRzedu;
	}
	
}
