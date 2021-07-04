package com.samuelweller.NCWA.AccountManagement;

public class Account {

	public String username, password;
	
	public String getUsername() {
		return username;
	}
	
	public boolean login(String password) {
		if (this.password == password) {
			return true;
		} else {
			return false;
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account() {
		
	}
	
}
