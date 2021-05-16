package com.samuelweller.NCWA.NCAPI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.samuelweller.NCWA.RestController.MoveRequest;

@Service
public class LiveGames {

	public static long NCIdCounter = 10000001;
	public static List<Game> LIVE_TWO_PLAYER_GAMES = new ArrayList<Game>();
	public static List<Game> LIVE_COMPUTER_GAMES = new ArrayList<Game>();
	public static List<Game> LIVE_ONLINE_GAMES = new ArrayList<Game>();
	
	
	public LiveGames() {
		//Populate a few games for testing
		for (int i = 0; i < 100; i++) {
			LiveGames.LIVE_TWO_PLAYER_GAMES.add(new Game());
		}

	}
	
	//Two Player Games:
	
	public static void addTwoPlayerGame(Game game) {
		LiveGames.LIVE_TWO_PLAYER_GAMES.add(game);
	}
	
	public static boolean deleteTwoPlayerGame(Game game) {
		for (Game currentGame : LiveGames.LIVE_TWO_PLAYER_GAMES) {
			if (currentGame.getId() == game.getId()) {
				LiveGames.LIVE_TWO_PLAYER_GAMES.remove(currentGame);
				return true;
			}
		}
		return false;
	}
	
	public static boolean deleteTwoPlayerGame(long id) {
		for (Game currentGame : LiveGames.LIVE_TWO_PLAYER_GAMES) {
			if (currentGame.getId() == id) {
				LiveGames.LIVE_TWO_PLAYER_GAMES.remove(currentGame);
				return true;
			}
		}
		return false;
	}
	
	public static long createTwoPlayerGame() {
		Game game = new Game();
		LiveGames.LIVE_TWO_PLAYER_GAMES.add(game);
		return game.getId();
	}
	
	public static boolean moveTwoPlayer(long gameId, int x, int y, int player) {
		for (Game currentGame : LiveGames.LIVE_TWO_PLAYER_GAMES) {
			if (currentGame.getId() == gameId) {
				return currentGame.move(x, y, player);
			}
		}
		return false;
	}
	
	public static boolean moveTwoPlayer(long gameId, MoveRequest move) {
		for (Game currentGame : LiveGames.LIVE_TWO_PLAYER_GAMES) {
			if (currentGame.getId() == gameId) {
				return currentGame.move(move);
			}
		}
		return false;
	}
	
	public static Game getTwoPlayerGame(long id) {
		for (Game currentGame : LiveGames.LIVE_TWO_PLAYER_GAMES) {
			if (currentGame.getId() == id) {
				return currentGame;
			}
		}
		return null;
	}
	
	//Computer Games:
	
	public static long createComputerGame() {
		Game game = new Game();
		LiveGames.LIVE_COMPUTER_GAMES.add(game);
		System.out.println("Debug: " + game.getTurn());
		return game.getId();
	}
	
	public static Game getComputerGame(long id) {
		for (Game currentGame : LiveGames.LIVE_COMPUTER_GAMES) {
			if (currentGame.getId() == id) {
				return currentGame;
			}
		}
		return null;
	}

	public static boolean moveComputerRandom(long gameId, MoveRequest move) {
		for (Game currentGame : LiveGames.LIVE_COMPUTER_GAMES) {
			if (currentGame.getId() == gameId) {
				
				//Make player move - NB: we do not check whether the player
				//move wins or the computer move wins or there are two possible 
				//legal moves left to make
				if (currentGame.move(move)) {
					if (currentGame.getWinner() == 0) {
						if (currentGame.randomComputerMove()) {
							return true;
						} else {
							System.out.println("A problem making a computer move");
						}
					} else {
						return true;
					}
				} else {
					System.out.println("A problem making a player move");
				}
				return false;
			}
		}
		return false;
	}

	public static long createComputerGameX() {
		Game game = new Game();
		game.randomComputerMove();
		LiveGames.LIVE_COMPUTER_GAMES.add(game);
		System.out.println("Debug: " + game.getTurn());
		return game.getId();
	}

	public static boolean moveComputerMedium(long gameId, MoveRequest move) {
		for (Game currentGame : LiveGames.LIVE_COMPUTER_GAMES) {
			if (currentGame.getId() == gameId) {
				//Make player move - NB: we do not check whether the player
				//move wins or the computer move wins or there are two possible 
				//legal moves left to make
				if (currentGame.move(move)) {
					if (currentGame.getWinner() == 0) {
						if (currentGame.mediumComputerMove()) {
							return true;
						} else {
							System.out.println("A problem making a computer move");
						}
					} else {
						return true;
					}
				} else {
					System.out.println("A problem making a player move");
				}
				return false;
			}
		}
	return false;
	}
	
	public static boolean moveComputerPerfect(long gameId, MoveRequest move) {
		for (Game currentGame : LiveGames.LIVE_COMPUTER_GAMES) {
			if (currentGame.getId() == gameId) {
				//Make player move - NB: we do not check whether the player
				//move wins or the computer move wins or there are two possible 
				//legal moves left to make
				if (currentGame.move(move)) {
					if (currentGame.getWinner() == 0) {
						if (currentGame.perfectComputerMove()) {
							return true;
						} else {
							System.out.println("A problem making a computer move");
						}
					} else {
						return true;
					}
				} else {
					System.out.println("A problem making a player move");
				}
				return false;
			}
		}
	return false;
	}
		
}
