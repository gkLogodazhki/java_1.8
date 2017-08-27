package game.roles;

import java.util.Random;

import game.Game;

public class Bot extends Player {

	public Bot(Game game) {
		super(game);
	}

	protected boolean setRandomMove() {
		while (true) {
			if (this.setMove(new Random().nextInt(this.getRow()), new Random().nextInt(this.getCol()))) {
				return true;
			}
		}
	}

}
