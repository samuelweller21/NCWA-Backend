package com.samuelweller.NCWA.NCAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.samuelweller.NCWA.RestController.AI;
import com.samuelweller.NCWA.RestController.MoveRequest;

public class Game {
	
	private Board board;
	private long id;
	private boolean turn; //True = O's turn, False = X's turn
	private int winner = 0;
	private List<MoveRequest> moveHistory = new ArrayList<MoveRequest>();
	
	public int getWinner() {
		return winner;
	}

	public Game() {
		id = LiveGames.NCIdCounter;
		LiveGames.NCIdCounter++;
		turn = true;
		board = new Board();
	}
	
	public Game(Game game) {
		this.id = -1;
		turn = game.getTurn();
		board = new Board(game.getBoard());
	}
	
	public boolean move(MoveRequest move) {
		
		if (move.getPlayer() == 1 && !turn) {
			System.out.println("It's X's turn");
			return false;
		} else if (move.getPlayer() == 2 && turn) {
			System.out.println("It's O's turn");
			return false;
		} else {
			if (this.board.move(move.getX(), move.getY(), move.getPlayer())) {
				turn = !turn;
				this.moveHistory.add(move);
				this.winner = this.board.winner();
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean move(int x, int y, int player) {
		return this.move(new MoveRequest(x,y,player));
	}
	
	public boolean move(int x, int y) {
		return this.move(x, y, this.getTurn() ? 1 : 2);
	}

	public long getId() {
		return id;
	}

	public boolean getTurn() {
		return turn;
	}
	
	public Board getBoard() {
		return this.board;
	}
		
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("Game of Noughts and Crosses!");
		sb.append("\n");
		sb.append("Id: " + this.getId() + "\n");
		sb.append("Turn: " + this.getTurn() + "\n");
		sb.append("Board:\n");
		sb.append(this.getBoard().toString() + "\n");
		sb.append("}\n");
		return sb.toString();
	}
	
	public List<Pos> legalMoves() {
		
		List<Pos> legalMoves = new ArrayList<Pos>();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.getBoard().getBoard()[i][j] == 0) {
					legalMoves.add(new Pos(i,j));
				}
			}
		}
		
		if (legalMoves.size() == 0) {
			return null;
		} else {
			return legalMoves;
		}
		
	}
	
	public boolean randomComputerMove() {
		//Get legal moves
		List<Pos> legalMoves = this.legalMoves();
		if (legalMoves == null) {
			return false;
		}
		Random r = new Random();
		//Choose random move from legal moves
		Pos move = legalMoves.get(r.nextInt(legalMoves.size()));
		//Make chosen move
		this.move(move.getX(), move.getY(), this.turn ? Board.NOUGHT : Board.CROSSES);
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (this.id != other.getId())
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		System.out.println(game.toString());
	}
	
	public void print() {
		System.out.println(this.toString());
	}

	public boolean mediumComputerMove() {
		
		//Get medium move from AI
		Pos bestMove = AI.bestMove(this, 3);
		
		//Make chosen move
		this.move(bestMove.getX(), bestMove.getY());
		return true;
	}
	
	public boolean perfectComputerMove() {
		
		//Get perfect move from AI
		Pos bestMove = AI.bestMove(this, 100);
		
		//Make chosen move
		this.move(bestMove.getX(), bestMove.getY());
		return true;
	}
	
}
