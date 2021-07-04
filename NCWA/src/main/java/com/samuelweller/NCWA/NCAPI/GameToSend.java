package com.samuelweller.NCWA.NCAPI;

import java.util.List;

public class GameToSend {

	private List<Integer> board;
	private boolean turn;
	private int winner;
	
	public List<Integer> getBoard() {
		return board;
	}

	public void setBoard(List<Integer> board) {
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
		System.out.println(this.board.toString());
	}

	@Override
	public String toString() {
		return "GameToSend [board=" + board + ", turn=" + turn + ", winner=" + winner + "]";
	}
	
}
