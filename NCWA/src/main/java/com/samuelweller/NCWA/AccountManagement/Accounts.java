package com.samuelweller.NCWA.AccountManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import com.samuelweller.NCWA.NCAPI.LoginFailures;

public class Accounts {

	public static List<Account> ACCOUNTS = new ArrayList<Account>();
	public static List<String> ADJECTIVES = new ArrayList<String>();
	public static List<String> ANIMALS = new ArrayList<String>();

	public static boolean addAccount(Account account) {
		Optional<Account> matches = getAccountsByUsername(account.getUsername());
		System.out.println(matches);
		System.out.println(ACCOUNTS);
		if (!matches.isPresent()) {
			ACCOUNTS.add(account);
			return true;
		}
		return false;
	}

	public static LoginFailures login(String username, String password) {
		Optional<Account> matches = getAccountsByUsername(username);
		if (matches.isPresent()) {
			if (matches.get().login(password)) {
				return LoginFailures.SUCCESS;
			} else {
				return LoginFailures.INCORRECT_PASSWORD;
			}
		} else {
			return LoginFailures.NO_USERNAME;
		}
	}

	public static Optional<Account> getAccountsByUsername(String username) {
		System.out.println("Looking for " + username + " in " + ACCOUNTS);
		return ACCOUNTS.stream().filter(account -> account.getUsername().equals(username)).findFirst();
	}

	public static String checkUsername(String username) {
		int i = 1;
		String newUsername = username + i;
		if (username == "") {
			return generateUsername();
		} else {
			if (ACCOUNTS.stream().filter(account -> account.getUsername().equals(username)).count() == 0) {
				return username;
			} else {
				while (getAccountsByUsername(newUsername).isPresent()) {
					i++;
					newUsername = username + i;
				}
				return newUsername;
			}
		}
	}

	public static void initGenerator() {
		// Init lists, scanners
		Scanner adjectivesReader = null;
		Scanner animalsReader = null;

		// Load files from memory
		try {
			adjectivesReader = new Scanner((new File(Accounts.class.getResource("english-adjectives.txt").toURI())));
			animalsReader = new Scanner(new File(Accounts.class.getResource("english-animals.txt").toURI()));
		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
			System.out.println("Could not find generate username files");
		}

		// Read files
		while (adjectivesReader.hasNextLine()) {
			ADJECTIVES.add(adjectivesReader.nextLine());
		}
		while (animalsReader.hasNextLine()) {
			ANIMALS.add(animalsReader.nextLine());
		}
	}

	public static String generateUsername() {
		
		//If it's the first call then need to init lists from memory
		if (ANIMALS.size() < 1) {
			initGenerator();
		}
		
		//Random generation
		Random rand = new Random();
		int adjRand = rand.nextInt(ADJECTIVES.size());
		int aniRand = rand.nextInt(ANIMALS.size());
		String adj = ADJECTIVES.get(adjRand);
		String ani = ANIMALS.get(aniRand);
		String adjCap = adj.substring(0,1).toUpperCase() + adj.substring(1);
		String aniCap = ani.substring(0,1).toUpperCase() + ani.substring(1);
		
		//Run generated username through checker to add numbers if necessary
		return checkUsername(adjCap + aniCap);
	}

}
