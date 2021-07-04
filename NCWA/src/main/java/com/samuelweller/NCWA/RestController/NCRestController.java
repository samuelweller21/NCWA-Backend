package com.samuelweller.NCWA.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.samuelweller.NCWA.AccountManagement.Account;
import com.samuelweller.NCWA.AccountManagement.Accounts;
import com.samuelweller.NCWA.AccountManagement.SignUpForm;
import com.samuelweller.NCWA.NCAPI.Game;
import com.samuelweller.NCWA.NCAPI.GameToSend;
import com.samuelweller.NCWA.NCAPI.LiveGames;

@RestController
@CrossOrigin(origins={"http://localhost:3000", "https://samuelweller.com"})
public class NCRestController {
	
	//Game search
	
	@GetMapping("/search/{gameId}")
	public int searchGame(@PathVariable long gameId) {
		//-1 = not found
		//0 = players
		//1 = computers
		Game game = LiveGames.getTwoPlayerGame(gameId);
		if (game == null) {
			game = LiveGames.getComputerGame(gameId);
			if (game == null) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}

	//Two player games
	
	@GetMapping("/tpgames/{gameId}")
	public GameToSend getTPGame(@PathVariable long gameId) {
		System.out.println("Processing a TP get request");
		Game game = LiveGames.getTwoPlayerGame(gameId);
		if (game == null) {
			return null;
		} else {
			return new GameToSend(game);
		}
	}
	
	@GetMapping("/tpgames/new")
	public long createNewTPGame() {
		System.out.print("Creating a new computer game ...");
		long temp =  LiveGames.createTwoPlayerGame();
		System.out.println(temp);
		return temp;
	}
	
	@PostMapping("/tpgames/{id}/move")
	public ResponseEntity<GameToSend> TPMove(@PathVariable long id, @RequestBody MoveRequest move) {
		System.out.println("Recieved a move request on game " + id + " with move " + move.toString());
		boolean isMove = LiveGames.moveTwoPlayer(id, move);
		if (isMove) {
			return new ResponseEntity<GameToSend>(new GameToSend(LiveGames.getTwoPlayerGame(id)), HttpStatus.OK);
		} else {
			return new ResponseEntity<GameToSend>(new GameToSend(LiveGames.getTwoPlayerGame(id)), HttpStatus.BAD_REQUEST);
		}
	}
	
	//Account Management
	
	@PostMapping("/createaccount")
	public ResponseEntity<String> createAccount(@RequestBody Account account) {
		System.out.println("Recieved a new account request with details: " + account.getUsername() + " - " + account.getPassword());
		if (Accounts.addAccount(account)) {
			return new ResponseEntity<>("Account Created", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Account was not created", HttpStatus.OK);
		}
	}
	
	@PostMapping("/suggestusername")
	public ResponseEntity<String> suggestUsername(@RequestBody SignUpForm form) {
		System.out.println("Recieved a new username request: " + form.getUsername());
		return new ResponseEntity<>(Accounts.checkUsername(form.getUsername()), HttpStatus.OK);
	}
	
	//Computer games
	
	@GetMapping("/cpgames/new")
	public long createNewCPGame() {
		System.out.print("Creating a new computer game ...");
		long temp =  LiveGames.createComputerGame();
		System.out.println(temp);
		return temp;
	}
	
	@GetMapping("/cpgames/new/x")
	public long createNewCPGameX() {
		System.out.print("Creating a new X computer game ...");
		long id =  LiveGames.createComputerGameX();
		System.out.println(id);
		return id;
	}
	
	@GetMapping("/cpgames/{gameId}")
	public GameToSend getCPGame(@PathVariable long gameId) {
		System.out.println("Processing a computer get request");
		Game game = LiveGames.getComputerGame(gameId);
		if (game == null) {
			return null;
		} else {
			return new GameToSend(game);
		}
	}
	
	@PostMapping("/cpgames/{id}/move/{difficulty}")
	public ResponseEntity<GameToSend> CPMove(@PathVariable long id, @RequestBody MoveRequest move, @PathVariable String difficulty) {
		if (difficulty.equals("Random")) {
			System.out.println("Recieved a random move request on game " + id + " with move " + move.toString());
			boolean isMove = LiveGames.moveComputerRandom(id, move);
			if (isMove) {
				return new ResponseEntity<GameToSend>(new GameToSend(LiveGames.getComputerGame(id)), HttpStatus.OK);
			} else {
				return new ResponseEntity<GameToSend>(new GameToSend(LiveGames.getComputerGame(id)), HttpStatus.BAD_REQUEST);
			}
		} else if (difficulty.equals("Medium")) {
			System.out.println("Recieved a medium move request on game " + id + " with move " + move.toString());
			boolean isMove = LiveGames.moveComputerMedium(id, move);
			if (isMove) {
				return new ResponseEntity<GameToSend>(new GameToSend(LiveGames.getComputerGame(id)), HttpStatus.OK);
			} else {
				return new ResponseEntity<GameToSend>(new GameToSend(LiveGames.getComputerGame(id)), HttpStatus.BAD_REQUEST);
			}
		} else {
			System.out.println("Recieved a perfect move request on game " + id + " with move " + move.toString());
			boolean isMove = LiveGames.moveComputerPerfect(id, move);
			if (isMove) {
				return new ResponseEntity<GameToSend>(new GameToSend(LiveGames.getComputerGame(id)), HttpStatus.OK);
			} else {
				return new ResponseEntity<GameToSend>(new GameToSend(LiveGames.getComputerGame(id)), HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	//Catch all ?
	
	@GetMapping("/")
	public void catchAll() {
		System.out.println("Missed a request");
	}
}
	
	
	
