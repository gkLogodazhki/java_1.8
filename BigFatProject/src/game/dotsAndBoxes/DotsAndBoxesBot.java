package game.dotsAndBoxes;

import game.Game;
import game.roles.Bot;
import interfaces.IPlayer;
import interfaces.Increasable;

public class DotsAndBoxesBot extends Bot implements IPlayer, Increasable {

	public DotsAndBoxesBot(Game game) {
		super(game);
	}

	public boolean check(char ch) {
		for (int i = 1; i < this.getRow() - 1; i += 2) {
			for (int j = 1; j < this.getCol() - 1; j += 2) {
				if (this.isField(i, j, ch)) {
					return (ch == '3') ? this.checkThreeMove(i, j) : this.randomMove(i, j);
				}
			}
		}
		return false;
	}

	private boolean checkThreeMove(int i, int j) {
		this.getGameBoard().setField(i, j, 'B');
		outer: for (int t = -1; t <= 1; t++) {
			for (int p = -1; p <= 1; p++) {
				if (Math.abs(t + p) == 1) {
					if (this.setMove(i + t, j + p)) {
						this.increaseField(this.getGameBoard(), this.getRowMove(), this.getColMove());
						break outer;
					}
				}
			}
		}
		return true;
	}

	private boolean randomMove(int i, int j) {
		loop: while (true) {
			int random = (int) (Math.random() * 4);
			switch (random) {
			case 0:
				if (this.setMove(i - 1, j)) {
					break loop;
				}
			case 1:
				if (this.setMove(i + 1, j)) {
					break loop;
				}
			case 2:
				if (this.setMove(i, j - 1)) {
					break loop;
				}
			case 3:
				if (this.setMove(i, j + 1)) {
					break loop;
				}
			}
		}
		return true;
	}

	@Override
	public void move() {
		if (!this.checkDone()) {
			if (check('3')) {
				move();
			} else if (check('0') || check('1') || check('2')) {
				this.increaseField(this.getGameBoard(), this.getRowMove(), this.getColMove());
			}
		}
	}

	private boolean checkDone() {
		for (int i = 1; i < this.getRow() - 1; i++) {
			for (int j = 1; j < this.getCol() - 1; j++) {
				if (!(this.isField(i, j, 'A') || this.isField(i, j, 'B'))) {
					if (this.isField(i, j, '4')) {
						this.getGameBoard().setField(i, j, 'B');
					}
					return false;
				}
			}
		}
		return true;
	}

}
