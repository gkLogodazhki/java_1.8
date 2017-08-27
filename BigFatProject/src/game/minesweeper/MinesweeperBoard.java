package game.minesweeper;

import java.util.Scanner;

import game.Board;

public class MinesweeperBoard extends Board {

	private int numberOfMines;
	private int numberOfFoundMines;

	public void setMinesweeperBoard(int level) {
		switch (level) {
		case 1:
			super.setBoard(9,9, '0');
			this.numberOfMines = 10;
			break;
		case 2:
			super.setBoard(16,16, '0');
			this.numberOfMines = 49;
			break;
		case 3:
			super.setBoard(16, 30, '0');
			this.numberOfMines = 100;
			break;
		case 4:
			Scanner sc = new Scanner(System.in);
			int row, col, numberOfMines;
			do {
				System.out.println("Input number of rows");
				row = sc.nextInt();
				System.out.println("Input number of cols");
				col = sc.nextInt();
				System.out.println("Input number of mines");
				numberOfMines = sc.nextInt();
			} while (row < 0 || col < 0 || numberOfMines <= 0);
			this.numberOfMines = numberOfMines; 
			this.setBoard(row, col, '0');
			break;
		}

		fillMines();

	}

	private void fillMines() {
		for (int p = 0; p < this.numberOfMines; p++) {
			int row = (byte) (Math.random() * this.getRow());
			int col = (byte) (Math.random() * this.getCol());
			if (!super.isField(row, col, '*')) {
				super.setField(row, col, '*');
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (row + i >= 0 && row + i < this.getRow() && col + j >= 0 && col + j < this.getCol()) {
							if (!super.isField(row + i, col + j, '*')) {
								super.setField(row + i, col + j,
										(char) (super.getField(row + i, col + j) + '1' - '0'));
							}
						}
					}
				}
			}
		}
	}

	public int getNumberOfMines() {
		return this.numberOfMines;
	}
	
	public void increaseFoundMines() {
		this.numberOfFoundMines++;
	}

	public void decreaseFoundMines() {
		this.numberOfFoundMines--;
	}

	public int getMinesLeft() {
		return this.numberOfMines - this.numberOfFoundMines;
	}

}
