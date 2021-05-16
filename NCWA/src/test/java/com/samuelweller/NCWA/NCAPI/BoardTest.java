package com.samuelweller.NCWA.NCAPI;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
	
	@Test
	@DisplayName("Testing board winning logic")
	void testBoardWinning() {
		
		for (int player = 1; player < 3; player++) {
			
			Board board = new Board();
			board.move(0, 0, player);
			board.move(0, 1, player);
			board.move(0, 2, player);
			
			assertEquals(player, board.winner());
			
			board = new Board();
			board.move(0, 0, player);
			board.move(1, 0, player);
			board.move(2, 0, player);
			
			assertEquals(player, board.winner());
			
			board = new Board();
			board.move(0, 0, player);
			board.move(1, 1, player);
			board.move(2, 2, player);
			
			assertEquals(player, board.winner());
		}
	}

	@Test
	@DisplayName("Get Char Test")
	void testBoardChar() {
		Board board = new Board();
		
		assertEquals(" ", board.getChar(0));
		assertEquals("O", board.getChar(1));
		assertEquals("X", board.getChar(2));
		assertEquals("-1", board.getChar(3));
	}
	
}
