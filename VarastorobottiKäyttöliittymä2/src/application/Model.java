package application;

/**
 * Palauttaa käyttöliittymälle valittua laatikkoa vastaavat x- ja y-koordinaatit
 * @author Paula ja Johanna
 * @version 1.0
 *
 */
public class Model implements Model_IF {

	/**
	 * Laatikon nimi
	 */
	String nam;
	/**
	 * x-koordinaatti
	 *
	 */
	double x;
	/**
	 * y-koordinaatti
	 */
	double y;



	/*
	 * Etsii laatikon nimeä vastaavan oikean x-koordinaatin arvon ja palauttaa sen käyttöliittymälle
	 */
	@Override
	public double sijainti1(String nam) {
		this.nam = nam;
		switch (nam){

		case "Laatikko2":
			x = 30.0;
			break;
		case "Laatikko3":
			x = 30.0;
			break;
		case "Laatikko4":
			x = 120.0;
			break;
		case "Laatikko5":
			x = 120.0;
			break;
		case "Laatikko6":
			x = 120.0;
			break;
		}
		return x;


	}

	/* Etsii laatikon nimeä vastaavan oikean y-koordinaatin arvon ja palauttaa sen käyttöliittymälle
	 */
	@Override
	public double sijainti2(String nam) {
		this.nam = nam;
		switch (nam){

		case "Laatikko2":
			y = 75.0;

			break;
		case "Laatikko3":
			y = 125.0;
			break;
		case "Laatikko4":
			y = 125.0;
			break;
		case "Laatikko5":
			y = 75.0;
			break;
		case "Laatikko6":
			y = 25.0;
			break;

		}
		return y;
	}





}
