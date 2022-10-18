/**
 * Cada una de las celdas pertenecientes al Tablero..
 * 
 * @author Alberto Lanchares Diez 
 * @author Victor Gonzalez Del Campo
 * @version 1.0.2
 * 
 */

/*Paquete en el que se encuentra*/
package juego.modelo;

/**
 * Clase publica Celda
 * */
public class Celda {
	
	/**
	 * Posicion de la fila desntro del tablero.
	 */
	private int fila;
	/**
	 * Posicion de la columna desntro del tablero.
	 */
	private int columna;
	/**
	 * Torre que hay asiganda en la celda, en caso de estar vacía la celda el valor
	 * de pieza será null.
	 */
	private Torre torre;
	/**
	 * Color que hay asiganda en la celda, en caso de estar vacía la celda el valor
	 * de torre será null.
	 */
	private Color color;
	/**
	 * Turno que hay asiganda a la torre de dicha celda, en caso de estar vacía la celda el valor
	 * de color será null.
	 */
	private Turno turno;
	
	/**
	 * Constructor.
	 * 
	 * @param fila    valor de la fila en la que se encuentra la celda
	 * @param columna valor de la columna en la que se encuentra la celda
	 * @param color color de esa celda
	 */
	
	public Celda (int fila, int columna, Color color) {
		this.fila=fila;
		this.columna=columna;
		this.color=color;
	}
	

	/**
	 * Desvincula la torre asiganda previamente a la Celda.
	 */
	
	public void eliminarTorre() {
		this.torre = null;
	}
	
	/**
	 * Vincula una torre a la Celda.
	 * 
	 * @param torre torre que se quiere asignar a la Celda
	 */
	
	public void establecerTorre(Torre torre) {
		this.torre=torre;
		
	}
	
	/**
	 * Comprueba si una Celda esta vacía o no.
	 * 
	 * @return true en caso de estar vacía y false y caso contrario
	 */
	
	public boolean estaVacia() {
		if(torre == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Devuelve el valor de la fila correspondiente a la Celda.
	 * 
	 * @return fila
	 */
	
	public int obtenerFila() {
		return fila;
	}
	
	/**
	 * Devuelve el valor de la columna correspondiente a la Celda.
	 * 
	 * @return columna
	 */
	
	public int obtenerColumna() {
		return columna;
	}
	
	/**
	 * Devuelve el valor de la color correspondiente a la Celda.
	 * 
	 * @return color
	 */
	
	public Color obtenerColor() {
		return color;
	}
	
	/**
	 * Devuelve la torre asiganda a la Celda.
	 * 
	 * @return torre
	 */
	
	public Torre obtenerTorre() {
		return torre;
	}
	

	/**
	 *  Devuelve el color de la torre asiganda a la Celda.
	 *  
	 * @return color de la torre o null en caso de que la Celda este vacía
	 */
	
	public Color obtenerColorDeTorre() {
		if(estaVacia() == true) {
			return null;
		}else {
			return this.torre.obtenerColor();
		}
	}
	

	/**
	 *  Obtienes el turno de esa torre, es decir, si es blanca o negra.
	 *  
	 * @return turno turno de esa torre o null en caso de que la Celda este vacía
	 */
	public Turno obtenerTurnoDeTorre() {
		if(estaVacia() == true) {
			return null;
		}else {
			return this.torre.obtenerTurno();
		}
	}
	
	/**
	 * Comprueba si una Celda tiene las mismas coordenadas que la Celda.
	 * 
	 * @param celda la celda que se quiere comprobar
	 * @return true si tienen las mismas coordenadas y false en caso contrario
	 */ 
	
	public boolean tieneCoordenadasIguales(Celda celda) {
		if(celda.obtenerFila() == this.fila && celda.obtenerColumna() == this.columna) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Devuelve una cadena de texto
	 * 
	 * @return True En caso de que la torre y el turno sea null o False caso de que la torre y el turno no sean null
	 * 
	 * */
	
	public String toString() {
		if(torre==null && turno==null) {
		return "["+fila+"]"+"["+columna+"]"+"Color: "+ color.toChar()+"Turno:-"+"Torre:-";
	}else{
		return "["+fila+"]"+"["+columna+"]"+"Color: "+ color.toChar() + "Turno: "+ torre.obtenerTurno().toChar() + "Torre: " + torre.obtenerColor().toChar(); 
		}
	}
}
