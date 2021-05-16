package com.samuelweller.NCWA.NCAPI;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	public static void main(String[] args) {
		Board board = new Board();
		System.out.println(board.toString());
	}

	private int[][] board = new int[3][3];
	
	public int[][] getBoard() {
		return board;
	}
	
	public static int NOUGHT = 1;
	public static int CROSSES = 2;
	public static int EMPTY = 0;
	
	public Board() {
		
		//Fill board as empty
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = Board.EMPTY;
			}
		}
	}
	
	public Board(Board b) {
		board = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = b.getBoard()[i][j];
			}
		}
	}
	
	public static List<Integer> toArray(Board board) {
		List<Integer> arr = new ArrayList<Integer>();
		for (int  i = 0; i < 3; i++) {
			for (int  j = 0; j < 3; j++) {
				arr.add(board.getBoard()[j][i]);
			}
		}
		return arr;
	}
	
	public static List<String> toCharArray(Board board) {
		List<String> arr = new ArrayList<String>();
		for (int  i = 0; i < 3; i++) {
			for (int  j = 0; j < 3; j++) {
				arr.add(board.getChar(board.getBoard()[j][i]));
			}
		}
		return arr;
	}
	
	
	
	public String getChar(int square) {
		if (square == 0) {
			return " ";
		} else if (square == 1) {
			return "O";
		} else if (square == 2) {
			return "X";
		}
		System.out.println("Warning - Improper board entry - " + square);
		return "-1";
	}
	
	public boolean move(int x, int y, int player) {
		if (x > 2 || y > 2) {
			System.out.println("You must make a move on the board");
			return false;
		}
		if (x < 0 || y < 0) {
			System.out.println("You must make a move on the board");
			return false;
		}
		if (board[x][y] != 0) {
			System.out.println("Warning - " + getChar(player) + " tried to play at " + 
		x + " , " + y + " but there is already a " + getChar(board[x][y]) + " there");
			return false;
		} else {
			board[x][y] = player;
			return true;
		}
	}
	
	public int winner() {
		return Board.WINNER(this.board);
	}
	
	public static int WINNER(int[][] board) {
		
		//Iterate over both players
		
		for (int test = 1; test < 3; test++) {
			
			//Find out if this player won
			
			if (board[0][0] == test && board[1][0] == test && board[2][0] == test) {
				return test;
			} else if (board[0][1] == test && board[1][1] == test && board[2][1] == test) {
				return test;
			} else if (board[0][2] == test && board[1][2] == test && board[2][2] == test) {
				return test;
			} else if (board[0][0] == test && board[0][1] == test && board[0][2] == test) {
				return test;
			} else if (board[1][0] == test && board[1][1] == test && board[1][2] == test) {
				return test;
			} else if (board[2][0] == test && board[2][1] == test && board[2][2] == test) {
				return test;
			} else if (board[0][0] == test && board[1][1] == test && board[2][2] == test) {
				return test;
			} else if (board[0][2] == test && board[1][1] == test && board[2][0] == test) {
				return test;
			}
		}
		
		//Is board full -> Draw
		int counter = 0;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0) {
					counter++;
					break;
				}
			}
			if (counter != 0) {
				break;
			}
		}
		if (counter == 0) {
			return -1;
		}
		 
		//No winner
		return 0;
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("/-----------\\");
		sb.append("\n");
		sb.append("| " + getChar(board[0][0]) + " | " + getChar(board[1][0]) + " | " + getChar(board[2][0]) + " |");
		sb.append("\n");                                                                                  
		sb.append("| " + getChar(board[0][1]) + " | " + getChar(board[1][1]) + " | " + getChar(board[2][1]) + " |");
		sb.append("\n");                                                                                  
		sb.append("| " + getChar(board[0][2]) + " | " + getChar(board[1][2]) + " | " + getChar(board[2][2]) + " |");
		sb.append("\n");
		sb.append("\\-----------/");
		
		return sb.toString();
		
	}

}
