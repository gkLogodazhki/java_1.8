package demo;

import game.Game;
import interfaces.IGame;
import interfaces.Playable;

public class Demo {

	public static void main(String[] args) {
		
		IGame game = new Game();
		
		Playable playable = null;
		
		game.newGame(playable);
		
	}
	
}
