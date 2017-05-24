package application;

/**
 *
 * Rajaointa Modelille
 * @author Paula ja Johanna
 * @version 1.0
 *
 */
public interface Model_IF {

	/**
	 * Palauttaa Sijainti1 arvon eli x-koordinaatin
	 * @param nam laatikon nimi
	 * @return nam
	 */
	public abstract double sijainti1 (String nam);
	/**
	 * Palauttaa Sijainti2 arvon eli y-koordinaatin
	 * @param nam laatikon nimi
	 * @return nam
	 */
	public abstract double sijainti2 (String nam);

}
