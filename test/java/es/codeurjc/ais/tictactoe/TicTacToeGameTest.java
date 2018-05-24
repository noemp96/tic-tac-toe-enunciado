package es.codeurjc.ais.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

public class TicTacToeGameTest {
	
	//Crear el objeto TicTacToeGame
	TicTacToeGame t = new TicTacToeGame();		
	
	//Crear los dobles de los objetos Connection
	Connection c1 = mock(Connection.class);
	Connection c2 = mock(Connection.class);
	
	//Crear los dos jugadores (objetos Player)
	Player p1 = new Player(0, "X", "High Lady of the Night Court"); //int id, String label, String name
	Player p2 = new Player(1, "O", "The Queen of Terrasen");
	
	public void CrearTicTacToe() {
		
		//Añadir los dobles al objeto TicTacToeGame
		t.addConnection(c1);
		t.addConnection(c2);
		
		//Añadir los jugadores al objeto TicTacToeGame
		t.addPlayer(p1);
		t.addPlayer(p2);
		
		//Comprobar que la conexión 1 recibe el evento JOIN_GAME, con ambos jugadores 
		//Comprobar que la conexión 2 recibe el evento JOIN_GAME, con ambos jugadores
		verify(c1,Mockito.times(2)).sendEvent(eq(EventType.JOIN_GAME), eq(t.getPlayers()));
		verify(c2,Mockito.times(2)).sendEvent(eq(EventType.JOIN_GAME), eq(t.getPlayers()));
		
		//Comenzamos a rellenar el tablero
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		t.mark(0); //Coloca p1
		
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		t.mark(4); //Coloca p2
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		t.mark(1); //Coloca p1
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		t.mark(2); //Coloca p2
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		t.mark(5); //Coloca p1
			
	}
	
	@Test
	public void TicTacToeGameTestEmpate() {
		
		CrearTicTacToe();
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		assertFalse(t.checkTurn(0));
		assertTrue(t.checkTurn(1)); //turno de p2
		t.mark(7); //Coloca p2
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		assertTrue(t.checkTurn(0)); //turno de p1
		assertFalse(t.checkTurn(1));
		t.mark(6); //Coloca p1
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		assertFalse(t.checkTurn(0));
		assertTrue(t.checkTurn(1)); //turno de p2
		t.mark(3); //Coloca p2
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		assertTrue(t.checkTurn(0)); //turno de p1
		assertFalse(t.checkTurn(1));
		t.mark(8); //Coloca p1
		
		/*Partida terminada : caso de empate*/
		
		//Al final se comprueba que el juego acaba y que dependiendo de las casillas marcadas uno de los jugadores gana o hay empate.		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture()); 
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event = argument.getValue();
		WinnerValue winner = (WinnerValue) event;
		assertNull(winner);
		
	}
	
	@Test 
	public void TicTacToeGameTestGanar() {
		
		CrearTicTacToe();
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		assertFalse(t.checkTurn(0));
		assertTrue(t.checkTurn(1)); //turno de p2
		t.mark(7); //Coloca p2
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		assertTrue(t.checkTurn(0)); //turno de p1
		assertFalse(t.checkTurn(1));
		t.mark(6); //Coloca p1
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		assertFalse(t.checkTurn(0));
		assertTrue(t.checkTurn(1)); //turno de p2
		t.mark(8); //Coloca p2
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p1));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p1));
		reset(c1);
		reset(c2);
		assertTrue(t.checkTurn(0)); //turno de p1
		assertFalse(t.checkTurn(1));
		t.mark(3); //Coloca p1
		
		/*Partida terminada : caso en el que P1 gana*/
		
		//Al final se comprueba que el juego acaba y que dependiendo de las casillas marcadas uno de los jugadores gana o hay empate.		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture()); 
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event = argument.getValue();
		WinnerValue winner = (WinnerValue) event;
		assertEquals(winner.player.getId(), 0);
		int [] solucion = {0,3,6};
		for (int i: winner.pos) {
			assertTrue(comprobarGanador(solucion, i));
		}
		
	}
	
	@Test 
	public void TicTacToeGameTestPerder() {
		
		CrearTicTacToe();
		
		verify(c1).sendEvent(eq(EventType.SET_TURN), eq(p2));
		verify(c2).sendEvent(eq(EventType.SET_TURN), eq(p2));
		reset(c1);
		reset(c2);
		assertFalse(t.checkTurn(0));
		assertTrue(t.checkTurn(1)); //turno de p2
		t.mark(6); //Coloca p2
		
		/*Partida terminada : caso en el que P2 gana*/
		
		//Al final se comprueba que el juego acaba y que dependiendo de las casillas marcadas uno de los jugadores gana o hay empate.		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);		
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture()); 
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event = argument.getValue();
		WinnerValue winner = (WinnerValue) event;
		assertEquals(winner.player.getId(), 1);
		int [] solucion = {2,4,6};
		for (int i: winner.pos) {
			assertTrue(comprobarGanador(solucion, i));
		}
		
	}
	
	private boolean comprobarGanador (int [] solucion, int valor) {
		
		for(int i : solucion) {
			if(valor==i) {
				return true;
			}
		}
		return false;
	}
	


}
