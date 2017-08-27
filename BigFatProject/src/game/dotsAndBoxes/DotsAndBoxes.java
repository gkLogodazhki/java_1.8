package game.dotsAndBoxes;

import java.util.Scanner;

import game.Game;
import interfaces.IPrintBoard;
import interfaces.Playable;

public class DotsAndBoxes extends Game implements Playable, IPrintBoard {

	@Override
	public void startGame() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int row, col;
		do {
			System.out.print("Input row and col: ");
			row = sc.nextInt();
			col = sc.nextInt();
		} while (row < 2 || row > 8 || col < 2 || col > 8);

		this.setBoard(row * 2 - 1, col * 2 - 1, ' ');
		this.setAllMoves((row - 1) * col + row * (col - 1));

		this.setPlayers(new DotsAndBoxesPlayer(this), new DotsAndBoxesBot(this));

		this.fillBoard();
		this.getFirstPlayer().decideWhoIsFirst(this.getSecondPlayer());

		playGame();

	}

	@Override
	public void playGame() {

		if (this.isOnTurn()) {
			outer: while (true) {
				this.printBoard();
				System.out.println("Your move");
				((DotsAndBoxesPlayer) this.getFirstPlayer()).move();
				for (int i = 1; i < this.getBoard().getRow() - 1; i += 2) {
					for (int j = 1; j < this.getBoard().getCol() -1; j += 2) {
						if (this.getBoard().isField(i, j, '4')) {
							this.getBoard().setField(i, j, 'A');
							if (!isGameOver()) {
								continue outer;
							}
						}
					}
				}
				break;
			}
		} else {
			System.out.println("Bot move");
			this.printBoard();
			((DotsAndBoxesBot) this.getSecondPlayer()).move();
		}

		if (!isGameOver()) {
			this.nextTurn();
			playGame();
		}

	}

	@Override
	public boolean isGameOver() {
		if (this.getFirstPlayer().getMadeMoves() + this.getSecondPlayer().getMadeMoves() == this.getAllMoves()) {
			int playerPoints = 0;
			int botPoints = 0;
			for (int i = 1; i < this.getBoard().getRow(); i+=2) {
				for (int j = 1; j < this.getBoard().getCol(); j+=2) {
					if (this.getBoard().isField(i, j, 'A')) {
						playerPoints++;
					} else {
						botPoints++;
					}
				}
			}
			if (playerPoints > botPoints) {
				this.getFirstPlayer().setWin();
				this.getSecondPlayer().setLose();
			} else {
				this.getFirstPlayer().setLose();
				this.getSecondPlayer().setWin();
			}
			this.printBoard();
			System.out.println("You " + this.getFirstPlayer().getResult() );
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void printBoard() {
		System.out.print("\n  ");
		for (int i = 0; i < this.getBoard().getRow(); i++) {
			System.out.print(i + " ");
		}
		for (int i = 0; i < this.getBoard().getRow(); i++) {
			System.out.printf("\n%d ", i);
			for (int j = 0; j < this.getBoard().getCol(); j++) {
				if (j % 2 == 1) {
					if (this.getBoard().isField(i, j, '-')) {
						System.out.print("---");
					} else if (this.getBoard().isField(i, j, 'A') || this.getBoard().isField(i, j, 'B')) {
						System.out.print(" " + this.getBoard().getField(i, j) + " ");
					} else {
						System.out.print("   ");
					}
				} else {
					System.out.print(this.getBoard().getField(i, j));
				}
			}
		}
		System.out.println();
	}

	private void fillBoard() {
		for (int i = 0; i < this.getBoard().getRow(); i++) {
			for (int j = 0; j < this.getBoard().getCol(); j++) {
				if (i % 2 == 0 && j % 2 == 0) {
					this.getBoard().setField(i, j, '*');
				}
				if (i % 2 == 1 && j % 2 == 1) {
					this.getBoard().setField(i, j, '0');
				}
			}
		}
	}
}
