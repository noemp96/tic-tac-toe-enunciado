package es.codeurjc.ais.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Cell;

public class TestBoard {

	public Board CrearBoard() {
		
		//Creamos el tablero
		Board board = new Board();
		
		//rellenamos las primeras casillas
		Cell c0 = board.getCell(0);
		Cell c1 = board.getCell(1);
		Cell c2 = board.getCell(2);
		Cell c4 = board.getCell(4);
		Cell c5 = board.getCell(5);
		c0.value = "X";
		c1.value = "X";
		c2.value = "O";
		c4.value = "O";
		c5.value = "X";
		/*
		  0|0|1
		  _ _ _
		   |1|0
		  _ _ _
		   | |
		*/
		
		//Devolvemos el tablero
		return board;
	}
	
	@Test
	public void BoardEmpate() {
		Board board = CrearBoard();
		
		//Comprobamos que el tablero no está lleno
		assertFalse(board.checkDraw()); 
		//Comprobamos que aún no hay ningún ganador
		assertNull(board.getCellsIfWinner("X"));
		assertNull(board.getCellsIfWinner("O"));
		
		//Terminamos de reyenar el tablero 
		Cell c3 = board.getCell(3);
		Cell c6 = board.getCell(6);
		Cell c7 = board.getCell(7);
		Cell c8 = board.getCell(8);
		c3.value = "O";
		c6.value = "X";
		c7.value = "O";
		c8.value = "X";
		
		//Comprobamos que el tablero está lleno
		assertTrue(board.checkDraw()); 
		//Comprobamos que no hay ningún ganador
		assertNull(board.getCellsIfWinner("X"));
		assertNull(board.getCellsIfWinner("O"));
		
	}
	
	@Test
	public void BoardPerder() {
		Board board = CrearBoard();
		
		//Comprobamos que el tablero no está lleno
		assertFalse(board.checkDraw()); 
		//Comprobamos que aún no hay ningún ganador
		assertNull(board.getCellsIfWinner("X"));
		assertNull(board.getCellsIfWinner("O"));
		
		//Terminamos de reyenar el tablero 
		Cell c6 = board.getCell(6);
		c6.value = "O";
		
		//Comprobamos que el tablero no está lleno
		assertFalse(board.checkDraw()); 
		//Comprobamos que no hay ningún ganador
		assertNull(board.getCellsIfWinner("X"));
		int [] solucion = {2,4,6};
		
		for (int i: board.getCellsIfWinner("O")) {
			assertTrue(comprobarGanador(solucion, i));
		}
		
	}
	
	@Test
	public void BoardGanar() {
		Board board = CrearBoard();
		
		//Comprobamos que el tablero no está lleno
		assertFalse(board.checkDraw()); 
		//Comprobamos que aún no hay ningún ganador
		assertNull(board.getCellsIfWinner("X"));
		assertNull(board.getCellsIfWinner("O"));
		
		//Terminamos de reyenar el tablero 
		Cell c3 = board.getCell(3);
		Cell c6 = board.getCell(6);
		Cell c7 = board.getCell(7);
		Cell c8 = board.getCell(8);
		c3.value = "X";
		c6.value = "X";
		c7.value = "O";
		c8.value = "O";
		 
		//Comprobamos que no hay un ganador
		int [] solucion = {0,3,6};
		for (int i: board.getCellsIfWinner("X")) {
			assertTrue(comprobarGanador(solucion, i));
		}
		assertNull(board.getCellsIfWinner("O"));
		
		//Comprobamos que el tablero está lleo y hay una línea igual
		assertTrue(board.checkDraw()); 
		/*esta solución nos demuestra que checkDraw no funciona como se describe en la práctica ya que 
		 checkDraw() devuelve true si el tablero está completo sin ninguna línea con fichas iguales y 
		 aqui tenemos una fila con fichas iguales*/
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
