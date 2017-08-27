package game.minesweeper;

import java.util.Scanner;

import game.Game;
import game.roles.Player;
import interfaces.IPlayer;
import interfaces.IVerify;

public class MinesweeperPlayer extends Player implements IPlayer, IVerify {
	
	private MinesweeperBoard minesweeperBoard;
	
	public MinesweeperPlayer(Game game,MinesweeperBoard board) {
		super(game);
		this.minesweeperBoard = board;
	}

	@Override
	public void move() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int row, col;
		char comand = ' ';
		do {
			System.out.printf("Input format: row[0,%d] col[0,%d] comand[y,n,?]\nInput: ", this.getCol(),
					this.getCol());
			row = sc.nextInt();
			col = sc.nextInt();
			comand = sc.next().charAt(0);
			if ((this.getGameBoard().getField(row, col) >= '1' && this.getGameBoard().getField(row, col) <= '8')
					|| this.isField(row, col, 'O')) {
				continue;
			} else if (comand == 'y' || comand == 'n' || comand == '?') {
				break;
			}
		} while (true);
		this.setMove(row, col);
		this.move(comand);
	}

	public void move(char comand) {
		
		final int row = this.getRowMove();
		final int col = this.getColMove();

		switch (comand) {
		case 'y':
			if (this.isFieldEmpty(row, col) || this.isField(row, col, '?')) {
				this.setField(row, col, 'X');
				((MinesweeperBoard) this.minesweeperBoard).increaseFoundMines();
			}
			break;
		case 'n':
			this.getGameBoard().setField(row, col, this.minesweeperBoard.getField(row, col));
			if (this.isField(row, col, '0')) {
				this.findAllZeroes(row, col);
			} else if (this.isField(row, col, '*')) {
				this.setLose();
			}
			break;
		case '?':
			if (this.isField(row, col, 'X')) {
				((MinesweeperBoard) this.minesweeperBoard).decreaseFoundMines();
			}
			this.setField(row, col, '?');
		}

	}
	
	private void findAllZeroes(int row, int col) {
		this.minesweeperBoard.setField(row, col, 'O');
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (row + i >= 0 && row + i < this.getRow() && col + j >= 0
						&& col + j < this.getCol()) {
					this.getGameBoard().setField(row + i, col + j,
							this.minesweeperBoard.getField(row + i, col + j));
					if (this.isField(row + i, col + j, '0')) {
						this.findAllZeroes(row + i, col + j);
					}
				}
			}
		}
	}

}
