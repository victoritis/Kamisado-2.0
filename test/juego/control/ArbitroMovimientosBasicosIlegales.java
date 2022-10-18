package juego.control;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.opentest4j.MultipleFailuresError;

import juego.modelo.Celda;
import juego.modelo.Color;
import juego.modelo.Tablero;
import juego.modelo.Turno;

/**
 * Pruebas de movimientos ilegales.
 * 
 * Clase abstracta con métodos comunes de utilidad 
 * que son heredados en descendientes concretos.
 * 
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena Sánchez</a>
 * @version 1.0 20210811
 */
public abstract class ArbitroMovimientosBasicosIlegales {
	
	/** Tablero. */
	protected Tablero tablero;
	
	/** Arbitro. */
	protected Arbitro arbitro;
			
	/**
	 * Inicialización del tablero antes de cada test.
	 */
	@BeforeEach
	void inicializar() {
		tablero = new Tablero();
		arbitro = new Arbitro(tablero);
		arbitro.colocarTorres(); // colocamos torres
	}
	
	/**
	 * Realiza jugadas legales.
	 * 
	 * @param jugadasLegales jugadas legales
	 * @param ultimosColores últimos colores en cada movimiento
	 * @throws MultipleFailuresError si hay algún error en los tests
	 */
	protected void realizarJugadasLegales(String[] jugadasLegales, Color[][] ultimosColores)
			throws MultipleFailuresError {
		int cont = 0;
		for (String jugada : jugadasLegales) {
			Celda origen = tablero.obtenerCeldaOrigenEnJugada(jugada);
			Celda destino = tablero.obtenerCeldaDestinoEnJugada(jugada);
			assertThat("La jugada " + jugada + " no es legal.",
					arbitro.esMovimientoLegalConTurnoActual(origen, destino), is(true));
			arbitro.moverConTurnoActual(origen, destino);
			final int auxCont = cont;
			assertAll("Últimos colores tras movimientos legales",
					() -> assertThat("Último color de turno negro incorrecto en movimiento " + (auxCont + 1) + ".",
							arbitro.obtenerUltimoMovimiento(Turno.NEGRO), is(ultimosColores[auxCont][0])),
					() -> assertThat("Último color de turno blanco incorrecto en movimiento " + (auxCont + 1) + ".",
							arbitro.obtenerUltimoMovimiento(Turno.BLANCO), is(ultimosColores[auxCont][1])));
			cont++;
		}
	}
	

	/**
	 * Intenta realizar las jugadas ilegales.
	 * 
	 * @param jugadasIlegales jugadas ilegales
	 */
	protected void intentarJugadasIlegales(String[] jugadasIlegales) {
		for (String jugada : jugadasIlegales) {
			Celda origen = tablero.obtenerCeldaOrigenEnJugada(jugada);
			Celda destino = tablero.obtenerCeldaDestinoEnJugada(jugada);
			assertThat("La jugada " + jugada + " es legal.",
					arbitro.esMovimientoLegalConTurnoActual(origen, destino), is(false));
		}
	}
}
