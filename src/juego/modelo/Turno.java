/**
 /**
 * Turnos de cada jugador.
 * @author Alberto Lanchares Diez 
 * @author Victor Gonzalez Del Campo 
 * @version 1.0.2
 */

/*Paquete en el que se encuentra*/
package juego.modelo;

/**
 * Enumeracion Turno
 * */
public enum Turno {
	/**
	 * Turno BLANCO.
	 */
	BLANCO ('B'),
	/**
	 * Turno NEGRO.
	 */
	NEGRO ('N');
	
	/**
	 * Letra del Turno, 'B' en caso de BLANCO, 'N' en caso de NEGRO. 
	 */
	private char letra;
	
	/**
	 * Constructor de turno.
	 * 
	 * @param c el caracter correspondiente al turno.
	 */
	
	private Turno (char c) {
		letra=c; 
	}
	/**
	 * Devuelve la letra del turno correspondiente.
	 * 
	 * @return letra correspondiente al turno.
	 */
	public char toChar() {
		return letra;
	}
	
}


