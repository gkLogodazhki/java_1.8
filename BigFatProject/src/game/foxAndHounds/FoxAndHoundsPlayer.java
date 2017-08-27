package game.foxAndHounds;

import java.util.Random;
import java.util.Scanner;

import game.Game;
import game.roles.EnumResult;
import game.roles.Player;
import interfaces.IPlayer;

public class FoxAndHoundsPlayer extends Player implements IPlayer {

	private static final int SIZE = 8;
	public FoxAndHoundsPlayer(Game game) {
		super(game);
		this.setMove(SIZE-1, new Random().nextInt(SIZE/2)*2);
	}

//	public void check() {
//		if (getRow() == 0) {
//			this.setWin();
//		} else if (getRow() == 7) {
//			if ((getCol() == 0 && this.isField(6, 1, 'H')) || (getCol() == 7 && this.isField(6, 6, 'H'))
//					|| (this.isField(6, getCol() - 1, 'H') && this.isField(6, getCol() + 1, 'H'))) {
//				this.setLose();
//			}
//		} else if (getCol() == 0) {
//			if (this.isField(getRow() - 1, 1, 'H') && this.isField(getRow() + 1, 1, 'H')) {
//				this.setLose();
//			}
//		} else if (getCol() == 7) {
//			if (this.isField(getRow() - 1, 6, 'H') && this.isField(getRow() + 1, 6, 'H')) {
//				this.setLose();
//			}
//		}
//	}

	@Override
	public void move() {
		if (!this.getResult().equals(EnumResult.DRAW)) {
			return;
		}
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Directions: Q, E, Z, C");
		
		final int row = this.getRowMove();
		final int col = this.getColMove();
		
		outer: while (true) {
			System.out.print("Input direction: ");
			switch (sc.next()) {
			case "Q":
				if (this.getColMove() > 0 && this.setMove(this.getRowMove() - 1, this.getColMove() - 1)) {
					break outer;
				}
				break;
			case "Z":
				if (this.getColMove() > 0 && this.getRowMove() < SIZE-1
						&& this.setMove(this.getRowMove() + 1, this.getColMove() - 1)) {
					break outer;
				}
				break;
			case "E":
				if (this.getColMove() < SIZE-1 && (this.setMove(this.getRowMove() - 1, this.getColMove() + 1))) {
					break outer;
				}
				break;
			case "C":
				if (this.getRowMove() < SIZE-1 && this.getColMove() < SIZE-1
						&& this.setMove(this.getRowMove() + 1, this.getColMove() + 1)) {
					break outer;
				}
				break;
			default:
				continue outer;
			}
		}
		
		this.setField(row, col, ' ');
		this.setField(this.getRowMove(), this.getColMove(), 'F');

	}

}
