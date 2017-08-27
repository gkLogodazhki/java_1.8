package game;

import interfaces.IPrintBoard;
import interfaces.IVerify;

public class Board implements IPrintBoard, IVerify {

	private char[][] charBoard;
	private String linesBetweenRows;

	public Board(){
		this.charBoard = new char[1][1]; 
	}
	
	public Board(int row, int col, char ch) {
		this.setBoard(row, col, ch);
	}

	public void setBoard(int row, int col, char ch) {
		if (row >= 2 && col >= 2) {
			this.charBoard = new char[row][col];
			this.linesBetweenRows = "";
			for (int i = 0; i < col; i++) {
				this.linesBetweenRows += "----";
			}
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					this.charBoard[i][j] = ch;
				}
			}
		} else {
			System.out.println("can't set a board");
		}
	}

	public void increaseField(int row, int col) {
		if (verify(this,row, col) && this.charBoard[row][col] >= '0' && this.charBoard[row][col] <= '8') {
			this.charBoard[row][col]++;
		}
	}
	
	private char[][] getBoard(){
			return this.charBoard;
	}

	public int getRow() {
		return this.getBoard().length;
	}

	public int getCol() {
		return this.getBoard()[0].length;
	}

	public boolean isField(int row, int col, char ch) {
		return (verify(this,row, col) && (this.charBoard[row][col] == ch)) ? true : false;
	}

	public char getField(int row, int col) {
		return (verify(this,row, col)) ? this.charBoard[row][col] : ' ';
	}

	public void setField(int row, int col, char ch) {
		if (verify(this,row, col)) {
			this.charBoard[row][col] = ch;
		}
	}

	@Override
	public void printBoard() {
		if (this.charBoard != null) {
			System.out.print("  ");
			for (int i = 0; i < this.getCol(); i++) {
				System.out.printf("%4d", i);
			}
			System.out.println("\n   -" + this.linesBetweenRows);
			for (int i = 0; i < this.getRow(); i++) {
				System.out.format("%2d |", i);
				for (int j = 0; j < this.getCol(); j++) {
					System.out.printf("%2s |", this.getField(i, j));
				}
				System.out.println("\n   -" + this.linesBetweenRows);
			}
		}
	}

}
