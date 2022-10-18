//COMENTARIO

package juego.control;

//Paquetes a importar/

import juego.modelo.*;
import juego.util.Sentido;

/**
 * Arbitro que se encarga de seguir la logica en el juego con ciertas normas
 * 
 * @author Victor Gonzalez Del Campo
 
 * 
 * @author Alberto Lanchares Diez 
 * @version 1.0.2
 */

/**
 * Clase publica Arbitro
 * */
public class Arbitro {
	/**
	 * Tablero asigando al arbitro.
	 */
    private Tablero tablero;
    /**
	 * Turno asigando al arbitro.
	 */
    private Turno turno; 
    /**
	 * Torre asigando al arbitro.
	 */
    private Torre torre;
    /**
	 * Color asigando al arbitro.
	 */
    private Color color;
    /**
	 * Variable que almacena el número total de movimientos de la partida.
	 */
    private int num_jugada;
    
    /**
     * Constructor
     * 
     * @param tablero tablero sobre el que el arbitro trabajara
     * */
    public Arbitro(Tablero tablero) {
	this.tablero=tablero;
	num_jugada=0;
	}
    
    /**
     * Cambia el turno del jugador
     * */
    protected void cambiarTurno() {
    		if(turno==null) {
    			turno=Turno.NEGRO; 
    		}
    		else if(turno==Turno.BLANCO){
                    turno=Turno.NEGRO;
    		}
    		else{turno=Turno.BLANCO;}
    }
    /**
     * Coloca las torres en el tablero al empezar la partida
     * */
    public void colocarTorres() {
            for(int i=0;i<8;i++){
            	torre=new Torre(Turno.NEGRO,tablero.obtenerCelda(7, i).obtenerColor());
                tablero.colocar(torre, 7,i);
                torre=new Torre(Turno.BLANCO,tablero.obtenerCelda(0, i).obtenerColor());
                tablero.colocar(torre, 0,i);
            }
            turno=Turno.NEGRO;
    }
  
    /**
     * Inicializa el tablero pasando un array de las torres en nota algebraica.
	 * 
	 * @param torres array de las torres.
	 * @param coordenadas array de las coordenadas.
	 * @param ultimoColorTurnoNegro color del ultimo turno blanco.
	 * @param ultimoColorTurnoBlanco color del ultimo turno blanco.
	 * @param turnoActual turno del que tiene que mover ficha.
	 * 
	 * 
     * */
    public void colocarTorres(Torre[] torres, String[] coordenadas, Color ultimoColorTurnoNegro, Color ultimoColorTurnoBlanco, Turno turnoActual) {
    	String letras;
    	for(int i=0;i<coordenadas.length;i++) {
    		letras=""+coordenadas[2*i]+coordenadas[(2*i)-1];
    		tablero.colocar(torres[i], tablero.obtenerCeldaParaNotacionAlgebraica(letras));
    	}
    }
    
    
    
    /**
     * Retorna el turno del ganador de la partida, bien por alcanzar la fila
	 * de salida del jugador contrario, o bien por existir bloqueo mutuo.
	 * 
	 * @return el turno del ganador o null en caso de que no haya ganador
     * */
	public Turno consultarGanador() {
		if(estaAlcanzadaUltimaFilaPor(turno) || hayBloqueoMutuo()){
	                    return turno;
	                }
	                return null;
	}
	
	/**
	 * Comprueba si es legal realizar el movimiento con el turno actual
	 * 
	 * @param origen celda origen del movimiento
	 * @param destino celda destino del movimiento
	 * @return true si es legal, false si no lo es
	 * */
	public boolean esMovimientoLegalConTurnoActual(Celda origen, Celda destino) {
		if(!origen.estaVacia() && origen.obtenerTurnoDeTorre()==turno && tablero.estanVaciasCeldasEntre(origen, destino)
	  && (tablero.obtenerSentido(origen, destino)!=Sentido.HORIZONTAL_E && tablero.obtenerSentido(origen, destino)
	  !=Sentido.HORIZONTAL_O && tablero.obtenerSentido(origen, destino)!=null)
	  && (origen.obtenerColorDeTorre()==color || color==null) && destino.estaVacia()){
			return true;
        }
        return false;
	}
	
	/**
	 * Calcula si se ha alcanzo la ultima fila por alguno de los dos jugadores
	 * 
	 * @param turno tipo Turno que comprueba si ese turno ha alcanzado la ultima fila
	 * 
	 * @return true si el jugador ha alcanzado la ultima fila, false en caso contrario
	 * */
	public boolean estaAlcanzadaUltimaFilaPor(Turno turno) {
		if(turno != null){
	        if(turno==Turno.BLANCO){
	            for(int i=0;i<8;i++){
	                if(!tablero.obtenerCelda(7, i).estaVacia() && tablero.obtenerCelda(7, i).obtenerTurnoDeTorre()==Turno.BLANCO){
	                    return true;
	                }
	            }
	        }
	        else{
	            for(int i=0;i<8;i++){
	                if(!tablero.obtenerCelda(0, i).estaVacia() && tablero.obtenerCelda(0, i).obtenerTurnoDeTorre()==Turno.NEGRO){
	                    return true;
	                }
	            }
	        }
		}
        return false;
	}
	
	/**
	 * Comprueba si el jugador puede o no mover en su turno actualmente
	 * 
	 * @return true si se encuentra bloqueado, en caso contrarrio false
	 * */
    public boolean estaBloqueadoTurnoActual(){
        if(turno==Turno.BLANCO){
            if(tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()+1, tablero.buscarTorre(turno, color).obtenerColumna()).estaVacia()){
                return false;
            }
            if(tablero.buscarTorre(turno, color).obtenerColumna()!=0 && tablero.buscarTorre(turno, color).obtenerColumna()!=7){
                if(tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()+1, tablero.buscarTorre(turno, color).obtenerColumna()+1).estaVacia()
                        || (tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()+1, tablero.buscarTorre(turno, color).obtenerColumna()-1).estaVacia())){
                    return false;
                }
            }
            else{
                if(tablero.buscarTorre(turno, color).obtenerColumna()==0){
                    if(tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()+1, tablero.buscarTorre(turno, color).obtenerColumna()+1).estaVacia()){
                        return false;
                    }
                }
                else{
                    if(tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()+1,tablero.buscarTorre(turno, color).obtenerColumna()-1).estaVacia()){
                        return false;
                    }
                }
            }
        }
        else{
            if(tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()-1, tablero.buscarTorre(turno, color).obtenerColumna()).estaVacia()){
                return false;
            }
            if(tablero.buscarTorre(turno, color).obtenerColumna()!=0 && tablero.buscarTorre(turno, color).obtenerColumna()!=7){
                if(tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()-1, tablero.buscarTorre(turno, color).obtenerColumna()-1).estaVacia()
                        || (tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()-1, tablero.buscarTorre(turno, color).obtenerColumna()+1).estaVacia())){
                    return false;
                }
            }
            else{
                if(tablero.buscarTorre(turno, color).obtenerColumna()==0){
                    if(tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()-1, tablero.buscarTorre(turno, color).obtenerColumna()+1).estaVacia()){
                        return false;
                    }
                }
                else{
                    if(tablero.obtenerCelda(tablero.buscarTorre(turno, color).obtenerFila()-1,tablero.buscarTorre(turno, color).obtenerColumna()-1).estaVacia()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
	 * Devuelve true si hay un bloqueo por ambos jugadores y no pueden mover sus fichas y
	 * false si si pueden mover.
	 *
	 *
	 * @return true or false
	 */
    
   
	public boolean hayBloqueoMutuo() {
		if(estaBloqueadoTurnoActual()){
		cambiarTurno();
	        if(estaBloqueadoTurnoActual()){
	        cambiarTurno();
	            return true;
	        }
	    }
	    return false;
	}
	/**
	 * Realiza el movimiento de la torre desde la celda origen a la celda destino.
	 * 
	 * @param origen elda donde se encuentra la torre que se desea mover.
	 * @param destino celda donde se desea colocar la torre.
	 */
	public void moverConTurnoActual(Celda origen, Celda destino) {
        tablero.moverTorre(origen, destino);
        cambiarTurno();
        color=destino.obtenerColor();
        num_jugada++;
	}
	/**
	 * 
	 * Realiza un movimiento sin mover la ficha ya que esta bloqueado.
	 */
	public void moverConTurnoActualBloqueado() {
		num_jugada++;
	    color=obtenerUltimoMovimiento(turno);
	    cambiarTurno();
	}
	/**
	 * Obtiene el numero de la jugada.
	 * 
	 * @return jugada El numero de jugada.
	 */
	public int obtenerNumeroJugada() {
	    return num_jugada;
	}
	/**
	 * Obtiene el turno
	 * 
	 * @return turno del jugador.
	 */
	public Turno obtenerTurno() {
		return turno;
	}
	/**
	 * Obtiene el ultimo movimiento
	 * 
	 * @param turno turno de la torre a buscar.
	 * @return color de la ultima torre movida
	 */
	public Color obtenerUltimoMovimiento(Turno turno) {
	    if(obtenerNumeroJugada()==1){
	        return null;
	    }else
	    if(obtenerTurno()==turno){
	        return tablero.buscarTorre(turno,color).obtenerColor();
	    }
	    return tablero.buscarTorre(turno, color).obtenerColor();
	}
	        
	}
