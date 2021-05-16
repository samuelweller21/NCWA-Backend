package com.samuelweller.NCWA.NCAPI;

import java.util.List;

public class GameToSend {

	private List<String> board;
	private boolean turn;
	private int winner;
	
	public List<String> getBoard() {
		return board;
	}

	public void setBoard(List<String> board) {
		this.board = board;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	public GameToSend(Game game) {
		this.board = Board.toCharArray(game.getBoard());
		this.turn = game.getTurn();
		this.winner = game.getWinner();
	}

	@Override
	public String toString() {
		return "GameToSend [board=" + board + ", turn=" + turn + ", winner=" + winner + "]";
	}
	
}
