package interfaces;

import game.Board;

public interface Increasable {

	default void increaseField(Board board,int row, int col) {
		if (row%2 == 0) {
			board.setField(row, col, '-');
			if (row == 0) {
				board.increaseField(row+1, col);
			} else if (row == board.getRow()-1) {
				board.increaseField(row-1, col);
			} else {
				board.increaseField(row+1, col);
				board.increaseField(row-1, col);
			}
		} else {
			board.setField(row, col, '|');
			if (col == 0) {
				board.increaseField(row, col+1);
			} else if (col == board.getCol()-1) {
				board.increaseField(row, col-1);
			} else {
				board.increaseField(row, col+1);
				board.increaseField(row, col-1);
			}
		}
	}
}
