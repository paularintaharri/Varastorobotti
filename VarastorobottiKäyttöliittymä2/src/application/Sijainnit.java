package application;
/**
 * Luokka toteuttaa Sijainnnit_IF rajapinnan ja sen metodit. Luokan tarkoitus on hakea sijainnit ja viedä ne
 * käyttöliittymälle.
 * @author Paula ja Johanna
 * @version 1.0
 */
public class Sijainnit implements Sijainnit_IF {

	/**
	 * Alustaa käyttöliittymän rajapinnan.
	 */
	MainGUI_IF gui;
	/**
	 * Alustaa modelin rajapinnan.
	 */
	Model_IF model;


	/**
	 * Muuttuja x johon x-koordinaatti talletetaan.
	 */
	private double x;
	/**
	 * Muuttujay johon y-koordinaatti talletetaan.
	 */
	private double y;

	/**
	 * Nimi koordinaattipisteille.
	 */
	private String name;

	/**
	 * Konstruktori, jossa tuodaan käyttöliittymän rajapinta ja modelin rajapinta Sijainnit-luokan
	 * käyttöön.
	 * @param gui Yhdistää rajapinnan Sijainnit-luokkaan.
	 * @param model Yhdistää rajapinnan Sijainnit-luokkaan.
	 */
	public Sijainnit(MainGUI_IF gui, Model_IF model) {
		this.gui = gui;
		this.model = model;}

	public Sijainnit(){}

	/**
	 * Konstruktori saa koordinaattipisteiden nimen, ja x- ja y-koordinaatit.
	 * @param nimi Nimen alustus.
	 * @param x X-koordinaatin alustus.
	 * @param y Y-koordinaatin alustus.
	 */
	public Sijainnit(String nimi, double x, double y){
		this.name = nimi;
		this.x = x;
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see application.Sijainnit_IF#getX()
	 * Metodi hakee Model-luokasta koordinaattipisteen nimellä x-koordinaatin.
	 */
	public double getX() {
		x = model.sijainti1(name);
		return x;
	}

	/* (non-Javadoc)
	 * @see application.Sijainnit_IF#getY()
	 * Metodi hakee Model-luokasta koordinaattipisteen nimellä y-koordinaatin.
	 */
	public double getY() {
		y = model.sijainti2(name);
		return y;
	}


	/* (non-Javadoc)
	 * @see application.Sijainnit_IF#setName(java.lang.String)
	 * Metodi asettaa nimen koordinaattipisteille
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

}


