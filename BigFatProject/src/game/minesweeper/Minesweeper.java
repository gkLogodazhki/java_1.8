package game.minesweeper;

import java.util.Scanner;

import game.Game;
import game.roles.EnumResult;
import interfaces.Playable;

public class Minesweeper extends Game implements Playable {

	private MinesweeperBoard minesweeperBoard;
	private int level;

	public Minesweeper() {
		this.minesweeperBoard = new MinesweeperBoard();
	}

	@Override
	public void startGame() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("Input level of play\n1) Easy\n2) Normal\n3) Hard\n4) Custom\nYour choice: ");
			level = sc.nextInt();
		} while (level <= 0 || level >= 5);

		this.minesweeperBoard.setMinesweeperBoard(level);
		this.setBoard(this.minesweeperBoard.getRow(), this.minesweeperBoard.getCol(), ' ');
		this.setPlayers(new MinesweeperPlayer(this, this.minesweeperBoard), null);
		System.out.println("Input example: 2 4 y");
		this.playGame();

	}

	@Override
	public void playGame() {
		System.out.printf("\nNumber of left mines: %d\n", ((MinesweeperBoard) this.minesweeperBoard).getMinesLeft());
		this.getBoard().printBoard();

		((MinesweeperPlayer) this.getFirstPlayer()).move();
		if (!isGameOver()) {
			this.playGame();
		} else {
			this.minesweeperBoard.printBoard();
			System.out.println("You " + this.getFirstPlayer().getResult());
		}
	}

	@Override
	public boolean isGameOver() {
		if (((MinesweeperBoard) this.minesweeperBoard).getMinesLeft() == 0) {
			for (int i = 0; i < this.getBoard().getRow(); i++) {
				for (int j = 0; j < this.getBoard().getCol(); j++) {
					if (this.getBoard().isField(i, j, 'X') && (!this.minesweeperBoard.isField(i, j, '*'))) {
						this.getFirstPlayer().setLose();
						return true;
					}
				}
			}
			this.getFirstPlayer().setWin();
			return true;
		} else if (this.getFirstPlayer().getResult().equals(EnumResult.LOSE)) {
			return true;
		} else {
			return false;
		}
	}

}
