package game.ticTacToe;

import java.util.Scanner;

import game.Game;
import game.roles.Player;
import interfaces.IPlayer;

public class TicTacToePlayer extends Player implements IPlayer {

	public TicTacToePlayer(Game game) {
		super(game);
	}

	@Override
	public void move() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int row, col;
		do {
			System.out.print("Input row and col: ");
			row = sc.nextInt();
			col = sc.nextInt();
		} while (!this.isFieldEmpty(row, col));

		this.setMove(row, col);
		this.getGameBoard().setField(row, col, 'X');
		
	}

}
