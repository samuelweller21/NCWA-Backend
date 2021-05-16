package com.samuelweller.NCWA.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.samuelweller.NCWA.NCAPI.Board;
import com.samuelweller.NCWA.NCAPI.Game;
import com.samuelweller.NCWA.NCAPI.Pos;

public class AI {
	
	public static Pos bestMove(Game game, int depth) {
		
		Map moveEvals = new HashMap<Pos, Integer>();
		List<Pos> legalMoves = game.legalMoves();
		for (int i = 0; i < legalMoves.size(); i++) {
			Game copy = new Game(game);
			moveEvals.put(legalMoves.get(i), 
					AI.eval(copy, 
							new MoveRequest(legalMoves.get(i).getX(), 
									legalMoves.get(i).getY(), 
									game.getTurn() ? 1 : 2), 
							depth-1));
		}
		
		//For debug print moves and their evals
		Iterator iterator = moveEvals.keySet().iterator();
		while (iterator.hasNext()) {
			Pos n = (Pos) iterator.next();
			System.out.println("Move " + n.toString() + " has eval " + moveEvals.get(n));
		}
		
		
		//Depending on whos turn it is pick the min or max from this list
		//NB We may want to list all possible moves of the same value
		//and then randomly pick from them to keep it interesting
		
		//First try and get eval equal to your number
		
		Iterator<Pos> itr = moveEvals.keySet().iterator();
		while (itr.hasNext()) {
			Pos n = itr.next();
			if (((Integer) moveEvals.get(n)) == (game.getTurn() ? 1 : 2)) {
				return n;
			}
		}
		
		//Then look for draws
		
		Iterator<Pos> itr2 = moveEvals.keySet().iterator();
		while (itr2.hasNext()) {
			Pos n = itr2.next();
			if (((Integer) moveEvals.get(n)) == -1) {
				return n;
			}
		}
		
		//Then look for unknown (only possible when there's a depth issue)
		
		Iterator<Pos> itr3 = moveEvals.keySet().iterator();
		while (itr3.hasNext()) {
			Pos n = itr3.next();
			if (((Integer) moveEvals.get(n)) == 0) {
				return n;
			}
		}
		
		//If nothing else then just return a losing move
		
		Iterator<Pos> itr4 = moveEvals.keySet().iterator();
		while (itr4.hasNext()) {
			return itr4.next();
		}
		
		return null;
	}
	
	public static int eval(Game game, MoveRequest move, int depth) {
		if (depth != 0) {
			if (game.getWinner() != 0) {
				return game.getWinner();
			} else {
				Game newGame = new Game(game);
				newGame.move(move);
				if (newGame.getWinner() != 0) {
					return newGame.getWinner();
				} else {
					Map moveEvals = new HashMap<Pos, Integer>();
					List<Pos> legalMoves = newGame.legalMoves();
					for (int i = 0; i < legalMoves.size(); i++) {
						moveEvals.put(legalMoves.get(i), 
								AI.eval(newGame, 
										new MoveRequest(legalMoves.get(i).getX(), 
												legalMoves.get(i).getY(), 
												move.getPlayer() == 1 ? 2 : 1), 
										depth-1));
					}
					//Depending on whos turn it is pick the min or max from this list
					//NB We may want to list all possible moves of the same value
					//and then randomly pick from them to keep it interesting
					
					//First try and get eval equal to your number
					
					Iterator<Pos> itr = moveEvals.keySet().iterator();
					while (itr.hasNext()) {
						Pos n = itr.next();
						if (((Integer) moveEvals.get(n)) == (newGame.getTurn() ? 1 : 2)) {
							return ((Integer) moveEvals.get(n));
						}
					}
					
					//Then look for draws
					
					Iterator<Pos> itr2 = moveEvals.keySet().iterator();
					while (itr2.hasNext()) {
						if (((Integer) moveEvals.get(itr2.next())) == -1) {
							return -1;
						}
					}
					
					//Then look for unknown (only possible when there's a depth issue)
					
					Iterator<Pos> itr3 = moveEvals.keySet().iterator();
					while (itr3.hasNext()) {
						if (((Integer) moveEvals.get(itr3.next())) == 0) {
							return 0;
						}
					}
					
					//If nothing else then just return a losing move
					
					Iterator<Pos> itr4 = moveEvals.keySet().iterator();
					while (itr4.hasNext()) {
						return ((Integer) moveEvals.get(itr4.next()));
					}
					
				}
			}
		} else {
			return 0;
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.print();
		System.out.println(AI.bestMove(game, 100));
		//System.out.println(AI.eval(game, new MoveRequest(0,2,Board.CROSSES), 100));
	}

}
