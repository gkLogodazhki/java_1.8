package game.dotsAndBoxes;

import java.util.Scanner;

import game.Game;
import game.roles.Player;
import interfaces.IPlayer;
import interfaces.Increasable;

public class DotsAndBoxesPlayer extends Player implements IPlayer, Increasable {

	public DotsAndBoxesPlayer(Game game) {
		super(game);
	}

	@Override
	public void move() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("!!!  row + col = odd number !!!");
		System.out.printf("Input format: row[0][%d] col[0][%d]\n", super.getRow()-1, super.getCol()-1);
		int row, col;
		do {
			System.out.println("Input row and col: ");
			row = sc.nextInt();
			col = sc.nextInt();
		} while (((row + col) % 2 == 0) || !super.isFieldEmpty(row, col));
		this.setMove(row, col);
		this.increaseField(this.getGameBoard(), row, col);

	}

}
