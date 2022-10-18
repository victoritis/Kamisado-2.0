
 /**
 * El tablero que conforma el lugar de juego.
 * @author Victor Gonzalez Del Campo
 * @author Alberto Lanchares Diez 
 * 
 * @version 
 */
/*Paquete en el que se encuetra*/
package juego.modelo;

//import juego.modelo.Color;
import static juego.modelo.Color.*;

//import juego.util.Sentido;
import juego.util.Sentido;

/**
 * Clase publica Tablero
 * */
public class Tablero {
	
	/**
	 * Array de todas las celdas del tablero.
	 */
	private static Celda[][] celdas;
	/**
	 * Numero de filas de un tablero de kamisado.
	 */
	private final int FILAS = 8;
	/**
	 * Numero de columnas de un tablero de kamisado.
	 * 
	 * 
	 */
	/*
	 * private Celda[][] celdas;
	 * 
	 * celdas = new Celda[5][5];
	 * 
	 * o
	 * 
	 * private Celda[][] celdas = {{}};
	 * 
	 * 
	 * 
	 */
	private final int COLUMNAS = 8;
	
	/**
	 *Array de colores que conforman el tablero 
	 */
	private static final Color[][] MATRIZ_COLORES = { {NARANJA, AZUL, PURPURA, ROSA, AMARILLO, ROJO, VERDE, MARRON },
			{ ROJO, NARANJA, ROSA, VERDE, AZUL, AMARILLO, MARRON, PURPURA },
			{ VERDE, ROSA, NARANJA, ROJO, PURPURA, MARRON, AMARILLO, AZUL },
			{ ROSA, PURPURA, AZUL, NARANJA, MARRON, VERDE, ROJO, AMARILLO },
			{ AMARILLO, ROJO, VERDE, MARRON, NARANJA, AZUL, PURPURA, ROSA },
			{ AZUL, AMARILLO, MARRON, PURPURA, ROJO, NARANJA, ROSA, VERDE },
			{ PURPURA, MARRON, AMARILLO, AZUL, VERDE, ROSA, NARANJA, ROJO },
			{ MARRON, VERDE, ROJO, AMARILLO, ROSA, PURPURA, AZUL, NARANJA } };
	
	/**
	 * Constructor de tablero.
	 */
	public Tablero() {
		
		
		celdas = new Celda[FILAS][COLUMNAS];
		
		for(int i=0;i<FILAS; i++) {
			for(int j=0;j<COLUMNAS;j++) {
				celdas[i][j] = new Celda(i,j,MATRIZ_COLORES [i][j]);
				
			}
		}		
		} 
	
	
	
	/**
	 * Busca la torre de un color y un turno determinado
	 * 
	 * @param turno turno de la torre a buscar
	 * @param color color de la torre a buscar
	 * @return celdas[][] devuelve la celda correspondiente a su color y turno
	 */
	public Celda buscarTorre(Turno turno, Color color) {
		
		for(int i=0;i<FILAS;i++) {
			for(int j=0;j<COLUMNAS;j++) {
				if(celdas[i][j].obtenerTurnoDeTorre()==turno && celdas[i][j].obtenerColorDeTorre()==color && !celdas[i][j].estaVacia()) {
					return celdas[i][j];
				}
				
			}
		}
		return null;
	}
	/**
	 * Coloca una torre en una determinada celda.
	 * 
	 * @param torre pieza que se desea colocar
	 * @param celda celda donde se desea colocar la torre
	 */
	public void colocar (Torre torre, Celda celda) {
		if(celda.obtenerFila()>=0 && celda.obtenerFila()<obtenerNumeroFilas() && celda.obtenerColumna()>=0 && celda.obtenerColumna()<obtenerNumeroColumnas()) {
			torre.establecerCelda(celda);
			celda.establecerTorre(torre);	
		
		}		
	}
	
	/**
	 * Coloca una pieza en una determinada celda.
	 * 
	 * @param torre pieza que se desea colocar
	 * @param notacionAlgebraica notacion algebraica
	 */
	public void colocar (Torre torre, String notacionAlgebraica) {
		Celda celda = obtenerCeldaParaNotacionAlgebraica (notacionAlgebraica);
		colocar (torre, celda);
		
	}
	
	/**
	 * Coloca una pieza en una determinada celda.
	 * 
	 * @param torre pieza que se desea colocar
	 * @param fila fila de la celda
	 * @param columna columna de la celda
	 */
	public void colocar (Torre torre, int fila, int columna) {
		
		if(fila>=0 && fila<obtenerNumeroFilas() && columna>=0 && columna<obtenerNumeroColumnas()) {
			celdas[fila][columna].establecerTorre(torre);
			torre.establecerCelda(celdas[fila][columna]);	
		}
	}
	
	/**
	 * Devuelve true si las celdas entre el origen y destino no
	 *contienen torres, es decir están vacías, o false en caso contrario
	 *
	 * @param origen origen de la celda
	 * @param destino destino de la celda
	 * @return true or false
	 */
	public boolean estanVaciasCeldasEntre (Celda origen, Celda destino) {
		Sentido sentido = obtenerSentido(origen, destino);
        boolean vacias = false;
        
        if (sentido != null) {
        	vacias = true;
        	int fila = origen.obtenerFila() + sentido.obtenerDesplazamientoEnFilas();
        	int columna = origen.obtenerColumna() + sentido.obtenerDesplazamientoEnColumnas();
        	while (fila != destino.obtenerFila() || columna != destino.obtenerColumna()) {

        		if (!celdas[fila][columna].estaVacia()) {
        			vacias = false;
        		}
            fila += sentido.obtenerDesplazamientoEnFilas();
            columna += sentido.obtenerDesplazamientoEnColumnas();
        	}
        }
        return vacias;
	}
	/**
	 * Para mover una torre a una nueva celda
	 * 
	 * @param origen Celda donde estaba la torre
	 * @param destino celda a donde mover la torre
	 */
	public void moverTorre (Celda origen, Celda destino) {
		if( destino.estaVacia() && !origen.estaVacia() && estanVaciasCeldasEntre(origen,destino)){
            destino.establecerTorre(origen.obtenerTorre());
            if(!origen.estaVacia()){origen.eliminarTorre();}
            destino.obtenerTorre().establecerCelda(destino);
     }
		
		
	}
	
	/**
	 * Devuelve la celda en las coordenadas indicadas.
	 * 
	 * @param fila    donde se encuentra la celda
	 * @param columna dondese encuentra la entrega
	 * @return devuelve la celda en las coordenadas indicadas o null si las
	 *         coordenadas no pertenecen al tablero
	 */
	
	public Celda obtenerCelda (int fila, int columna) {
		if (fila < FILAS &&   fila >= 0 && columna >= 0 && columna < COLUMNAS) {
			return celdas[fila][columna];
		}else {
			return null;
		}
	}
	
	/**
	 * Devuelve la celda en las coordenadas indicadas.
	 * 
	 * @param fila    donde se encuentra la celda
	 * @param columna dondese encuentra la entrega
	 * @return devuelve la celda en las coordenadas indicadas o null si las
	 *         coordenadas no pertenecen al tablero
	 */
	
	public Celda obtenerCeldaDestinoEnJugada(String textoJugada) {
		if(textoJugada.length()==4) {
			char[] movimiento=textoJugada.toCharArray();
	        return obtenerCeldaParaNotacionAlgebraica(""+movimiento[2]+movimiento[3]);
		}else {
			return null;
		}
		
	}
	
	/**
	 * Obtiene la celda origen de la jugada por texto
	 * 
	 * @param textoJugada texto de la jugada
	 * 
	 * @return Celda celda en notacion algebraica
	 */
	public Celda obtenerCeldaOrigenEnJugada (String textoJugada) {
		if(textoJugada.length()==4) {
			char[] movimiento=textoJugada.toCharArray();
	        return obtenerCeldaParaNotacionAlgebraica(""+movimiento[0]+movimiento[1]);
		}else {
			return null;
		}
	}
	
	/**
	 * Devuelve una celda en notación algebraica.
	 *
	 * @param texto cadena de texto de la celda en notacion algebraica
	 * @return devuelve la celda correspondiente a las coordenadas algebricas y si
	 *         no existe devuelve null
	 */
	
	public Celda obtenerCeldaParaNotacionAlgebraica (String texto) {
		if(texto.length()==2 && texto.charAt(0) >='a'&& texto.charAt(0) <='h' && texto.charAt(1)>='1' && texto.charAt(1)<='8' ) {
		 char[] letranum = texto.toCharArray();
	        int fila;
	        int columna;
	       
	        switch(letranum[0]){
	            case 'a': columna=0;break; 
	            case 'b': columna=1;break; 
	            case 'c': columna=2;break; 
	            case 'd': columna=3;break; 
	            case 'e': columna=4;break; 
	            case 'f': columna=5;break; 
	            case 'g': columna=6;break; 
	            case 'h': columna=7;break; 
	            default: return null;
	        }
	        switch(letranum[1]){
	            case '1': fila=7;break;
	            case '2': fila=6;break;
	            case '3': fila=5;break;
	            case '4': fila=4;break;
	            case '5': fila=3;break;
	            case '6': fila=2;break;
	            case '7': fila=1;break;
	            case '8': fila=0;break;
	            default:return null;
	        }
	       return celdas[fila][columna];
		}
		return null;
	   	}
	
	/**
	 * Devuelve un array de una dimensión con todas las celdas del tablero.
	 * 
	 * @return todas corresponde a un array de todas las celdas del tablero
	 */
	public Celda [] obtenerCeldas(){
		Celda[] crearceldas = new Celda[obtenerNumeroFilas() * obtenerNumeroColumnas()];
		int contador = 0;
		for (int i = 0; i < obtenerNumeroFilas(); i++) {
			for (int j = 0; j < obtenerNumeroColumnas(); j++) {
				crearceldas[contador] = obtenerCelda(i, j);
				contador++;
			}

		}
		return crearceldas;
	}
	
	/**
	 * Devuelve las coordenadas en notación algebraica de una celda.
	 *
	 * @param celda a la que queremos obtener las coordenadas en notación algebraica
	 * @return devuelve las coordenadas en notación algebraica de la celda.
	 */
	
	public String obtenerCoordenadasEnNotacionAlgebraica(Celda celda) {
		int fila = celda.obtenerFila();
        int columna = celda.obtenerColumna();
        char numero='-';
        char letra='-';
        if(fila < FILAS && fila >= 0 && columna < COLUMNAS && columna >= 0 ) {
        switch(columna){
            case 0: letra='a'; break;
            case 1: letra='b'; break;
            case 2: letra='c'; break;
            case 3: letra='d'; break;
            case 4: letra='e'; break;
            case 5: letra='f'; break;
            case 6: letra='g'; break;
            case 7: letra='h'; break;
            default: letra='-';
        }
        switch(fila){
            case 0: numero='8';break;
            case 1: numero='7';break;
            case 2: numero='6';break;
            case 3: numero='5';break;
            case 4: numero='4';break;
            case 5: numero='3';break;
            case 6: numero='2';break;
            case 7: numero='1';break;
            default: numero='-';
        }
        }
        return ""+letra+numero;
	}
	/**
	 * Devuelve la jugada en notacion algebraica
	 * 
	 * @param origen Celda origen de la jugada
	 * @param destino Celda destino de la jugada
	 * 
	 * @return letras las letras
	 */
	public String obtenerJugadaEnNotacionAlgebraica(Celda origen, Celda destino) {
			String letras = "";
			letras += obtenerCoordenadasEnNotacionAlgebraica(origen);
			letras += obtenerCoordenadasEnNotacionAlgebraica(destino);
	        return letras;
	}
	
	/**
	 * Devuelve el numero de columnas totales del tablero.
	 * 
	 * @return columnas del tablero
	 */
	public int obtenerNumeroColumnas() {
		return COLUMNAS;
	}
	
	/**
	 * Obtiene el numero de filas totales del tablero
	 * 
	 * @return FILAS el valor de las filas del tablero
	 */
	
	public int obtenerNumeroFilas() {
		return FILAS;
	}
	
	/**
	 * Cuenta el número de torres de un determinado color que hay sobre el tablero
	 * actualmente.
	 * 
	 * @param color color de la torre
	 * @return totales numero de torres que quedan en el tablero de un color
	 */
	public int obtenerNumeroTorres(Color color) {
		int contador=0;
		for(int i=0;i<FILAS; i++) {
			for(int j=0;j<COLUMNAS;j++) {
				if(!celdas[i][j].estaVacia() && celdas[i][j].obtenerColorDeTorre() == color) {
					contador++;
				}
			}
		}
		return contador;
	}
	
	/**
	 * Cuenta el número de torres de un determinado turno que hay sobre el tablero
	 * actualmente.
	 * 
	 * @param turno turno de la torre
	 * @return totales numero de torres que quedan en el tablero de un turno
	 */
	public int obtenerNumeroTorres(Turno turno) {
		int contador=0;
		for(int i=0;i<FILAS; i++) {
			for(int j=0;j<COLUMNAS;j++) {
				if(!celdas[i][j].estaVacia() && celdas[i][j].obtenerTurnoDeTorre() == turno) {
					contador++;
				}
			}
		}
		return contador;
	}
	
	/**
	 * Obtiene el sentido de la jugada sabiendo la celda origen y destino
	 * 
	 * @param origen Celda origen de la jugada
	 * @param destino Celda destino de la jugada
	 * 
	 * @return Sentido el sentido
	 */
	public Sentido obtenerSentido(Celda origen, Celda destino) {
		int fil_origen = origen.obtenerFila();
        int col_origen = origen.obtenerColumna();

        int fil_destino = destino.obtenerFila();
        int col_destino = destino.obtenerColumna();
        
        if (col_origen == col_destino) {
        	if (fil_destino < fil_origen) {
                return Sentido.VERTICAL_N;
            } else {
                return Sentido.VERTICAL_S;
            }
        }else if(Math.abs(destino.obtenerFila() - origen.obtenerFila()) == Math.abs(destino.obtenerColumna() - origen.obtenerColumna())) {
        	if (fil_destino < fil_origen && col_destino > col_origen) {
        		return Sentido.DIAGONAL_NE;
        	}
        	if (fil_destino < fil_origen && col_destino < col_origen) {
        		return Sentido.DIAGONAL_NO;
        	}
        	if (fil_destino > fil_origen && col_destino > col_origen) {
                return Sentido.DIAGONAL_SE;
            }
            if (fil_destino > fil_origen && col_destino < col_origen) {
                return Sentido.DIAGONAL_SO;
            }
        }
        if (fil_origen == fil_destino) {
            if (col_destino > col_origen) {
                return Sentido.HORIZONTAL_E;
            } else {
                return Sentido.HORIZONTAL_O;
            }
        }
        
        return null;
	}
	
	
	/**
	 * Imprime por pantalla el estado actual del tablero, con sus torres
	 * 
	 * @return Imprimir cadena de texto que contiene la informacion del tablero
	 */
	public String toString() {
		String letras="    a        b        c        d        e        f        g        h\n";
		Celda celdaletra;
		Color colorletra;
		for (int i=0; i<obtenerNumeroFilas(); i++) {
			for (int k=0; k<3; k++) {			
				for (int j=0; j<obtenerNumeroColumnas(); j++) {
					//obtenemos la celda y su color para mostrarla
					celdaletra= obtenerCelda(i,j);	
					colorletra = celdaletra.obtenerColor();
					if (k!=1) { // mostramos los colores de la celda si no es la del medio.
						letras = letras.concat("  "+colorletra.toChar()+".."+colorletra.toChar());
						letras = letras.concat("   ");					
					} else { // mostramos el color del turno del jugador y el color de la torre si es la del medio.
						if (j==0) {
							letras = letras.concat(8-i+" ");
						}
						letras = letras.concat("-");
						if (celdaletra.estaVacia()==true) {
							letras = letras.concat("--");
						} else {
							letras = letras.concat(celdaletra.obtenerTorre().obtenerTurno().toChar()+""+celdaletra.obtenerTorre().obtenerColor().toChar());
						}
						letras = letras.concat("-     ");
					}
					if (j==obtenerNumeroColumnas()-1) {
						letras = letras.concat("\n");// Salto de linea.
					}
				}
				if (k==2) {
					letras = letras.concat("\n");//Salto de linea
				}
			}
		}
		return letras;
	}
}
