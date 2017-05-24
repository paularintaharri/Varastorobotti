package application;
/**
 * Rajapinnan tarkoitus on kertoa mit‰ metodeita Sijainnit-luokka k‰ytt‰‰.
 * @author Paula ja Johanna
 * @version 1.0
 *
 */
public interface Sijainnit_IF {

	/**
	 * Abstrakti metodi m‰‰rittelee nimin asetuksen.
	 * @param nam Nimi, joka asetetaan.
	 */
	public abstract void setName(String nam);

	/**
	 * Abstrakti metodi hakee x:n arvon.
	 * @return Palauttaa x-arvon.
	 */
	public abstract double getX();
	/**
	 * Abstrakti metodi hakee y:n arvon.
	 * @return Palauttaa y-arvon.
	 */
	public abstract double getY();
	}