 
/**
 * Colores del tablero.
 * @author Alberto Lanchares Diez 
 * @author Victor Gonzalez Del Campo 
 * @version 1.0.2
 */ 

/*Paquete en el que se encuentra*/
package juego.modelo;
/*Paquete a importar*/
import java.util.Random;

/**
 * Enumeracon Color
 * */
public enum Color{ 
	/** Color: MARRON. */
	MARRON ('M'),
	/** Color: VERDE. */
	VERDE ('V'),
	/** Color: ROJO. */
	ROJO('R'),
	/** Color: AMARILLO. */
	AMARILLO('A'),
	/** Color: ROSA. */
	ROSA('S'),
	/** Color: PURPURA. */
	PURPURA('P'),
	/** Color: AZUL. */
	AZUL('Z'),
	/** Color: NARANJA. */
	NARANJA('N');
	
	/**
	 * Letra del color, 'M' en caso de color MARRON, 'V' en caso de color VERDE,'R' en caso de color ROJO,
	 * 'A' en caso de color AMARILLO, 'S' en caso de color ROSA, 'P' en caso de color PURPURA, 
	 * 'Z' en caso de color AZUL y 'N' en caso de color NARANJA.
	 */
	private char letra;
	
	/**
	 * Constructor .
	 * 
	 * @param c letra correspondiente al color
	 */
	
	private Color(char c) {
		letra=c;
	}
	
	/**
	 * Convierte en una cadena de caracteres.
	 * 
	 * @return letra  letra correspondiente al color.
	 */
	public char toChar() {
		return letra;
	}
	
	/**
	 * Obtine un color aleatorio.
	 * 
	 * @return Color.values()[aleatorio]  Devuelve el color aleatorio.
	 */
	
	public static Color obtenerColorAleatorio() {
		int aleatorio = new Random().nextInt(Color.values().length);
		return Color.values()[aleatorio];
	}
}

