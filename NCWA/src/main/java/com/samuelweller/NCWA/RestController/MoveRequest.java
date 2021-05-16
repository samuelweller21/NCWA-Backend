package com.samuelweller.NCWA.RestController;

public class MoveRequest {

	private int x,y,player;
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public MoveRequest() {
		
	}
	
	public MoveRequest(int x, int y, int player) {
		this.x = x;
		this.y = y;
		this.player = player;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + player;
		result = prime * result + x;
		result = prime * result + y;
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
		MoveRequest other = (MoveRequest) obj;
		if (player != other.player)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MoveRequest [x=" + x + ", y=" + y + ", player=" + player + "]";
	}
	
}
