package com.samuelweller.NCWA.NCAPI;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

	@Test
	@DisplayName("Game turn testing")
	void testGameTurn() {
		Game game = new Game();
		assertEquals(false, game.move(0, 0, 2));
		assertEquals(true, game.move(0, 0, 1));
		assertEquals(false, game.move(0, 1, 1));
		assertEquals(false, game.move(0, 0, 2));
		assertEquals(true, game.move(0, 1, 2));
	}
	
	@Test
	@DisplayName("Game AI testing")
	void testGameAI() {
		Game game = new Game();
		game.move(0, 0);
		game.move(1, 0);
		game.move(0, 1);
		game.move(1, 1);
		game.perfectComputerMove();
		assertEquals(Board.NOUGHT, game.getBoard().getBoard()[0][2]);
	}
	
	@Test
	@DisplayName("Game legal moves testing")
	void testGameMoves() {
		Game game = new Game();
		game.move(0,0);
		game.move(0,1);
		game.move(0,2);
		game.move(1,0);
		game.move(1,1);
		game.move(1,2);
		List<Pos> moves = new ArrayList<Pos>();
		moves.add(new Pos(2,0));
		moves.add(new Pos(2,1));
		moves.add(new Pos(2,2));
		assertEquals(moves,game.legalMoves());
	}
	
}
