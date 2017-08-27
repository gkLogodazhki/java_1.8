package game.foxAndHounds;

import java.util.Arrays;
import java.util.Random;

import javax.print.attribute.standard.Sides;

import game.Game;
import game.roles.Player;
import interfaces.IPlayer;

public class FoxAndHoundsBot extends Player implements IPlayer {

	public FoxAndHoundsBot(Game game) {
		super(game);
	}
	
	// STILL IN PROGRESS

	@Override
	public void move() {
		int[] foxPosition = findFox();
		if (this.getGame().getMoves() <= 8) {
			this.firstMove();
		} else if (this.getGame().getMoves() <= 10) {
			check(0,1);
		} else if (this.getGame().getMoves() <= 16) {
			this.secondMove(foxPosition);
		}
	}

	private int[] rows = { 0, 0, 0, 0 };
	private int[] cols = { 1, 3, 5, 7 };

	private void firstMove() {
		while (true) {
			int random = new Random().nextInt(4);
			if (rows[random] == 0) {
				check(random, -1);
				break;
			}
		}
	}

	private void secondMove(int[] foxPosition) {
		this.moveRight();
		
	}
	
	private void moveRight() {
		while (true) {
			int index = new Random().nextInt(4);
			if (rows[index] %2 == 1) {
				if (this.isField(rows[index]+1, cols[index]-1, '@') && this.isFieldEmpty(rows[index]+1, cols[index]+1)) {
					check(index,1);
					return;
				}
			} else if (this.findFox()[0] == 2) {
				if (this.isFieldEmpty(rows[index]+1, cols[index]-1) && this.isFieldEmpty(rows[index]+1, cols[index]+1)) {
					check(index,1);
					return;
				}
			} else if (rows[index]%2 == 0) {
				if (this.isFieldEmpty(rows[index]+1, cols[index]-1) && this.isFieldEmpty(rows[index]+1, cols[index]+1)) {
					check(index,1);
					return;
				}
			}
		}
	}

	private int[] findFox() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.isField(i, j, 'F')) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}

	private char check(int index, int stepCol) {
		this.setField(rows[index], cols[index], ' ');
		this.rows[index]++;
		this.cols[index] += stepCol;
		char ch = this.getGameBoard().getField(rows[index], cols[index]);
		this.setField(rows[index], cols[index], '@');
		return ch;
	}

	private boolean noPossibleMoves() {

		return false;
	}
}
