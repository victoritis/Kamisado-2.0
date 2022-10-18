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
 * Clase Publica Torre
 * */
public class Torre {
	/**
	 * Color de la torre.
	 */
	private Color color;
	/**
	 * Turno de la torre.
	 */
	private Turno turno;
	/**
	 * Celda de la torre.
	 */
	private Celda celda;
	
	/**
	 * Constructor de Torre.
	 * 
	 * @param turno  representa el turno de torre.
	 * @param color representa el color de la torre.
	 */
	public Torre (Turno turno, Color color) {
		this.turno = turno;
		this.color = color;
	}
	/**
	 * Establece el turno de la torre.
	 * 
	 * @param turno  El jugador que le toca.
	 */
	public void establecerTurno(Turno turno) {
		this.turno= turno;
	}
	/**
	 * Obtiene el turno de la torre.
	 * 
	 * @return turno de la torre.
	 */
	public Turno obtenerTurno() {
		return turno;
	}
	/**
	 * Establece el color de la torre.
	 * 
	 * @param color donde se encuentra la torre.
	 */
	public void establecerColor(Color color) {
		this.color=color;
	}
	/**
	 * Obtiene el color de la torre.
	 * 
	 * @return color de la torre.
	 */
	public Color obtenerColor() {
		return color;
	}
	/**
	 * Obtiene la celda de la torre.
	 * 
	 * @return celda de la torre.
	 */
	public Celda obtenerCelda() {
		return celda;
	}
	/**
	 * Establece la celda de la torre.
	 * 
	 * @param celda donde se encuentra la torre.
	 */
	public void establecerCelda (Celda celda) {
		this.celda=celda;
	}
	
	/**
	 * Devuelve en formato texto el turno y el color y si es primer movimiento de la
	 * forma: turno-color-primerMovimiento.
	 */
	public String toString() {
		return turno.toChar() + "" + color.toChar();
	}
}


