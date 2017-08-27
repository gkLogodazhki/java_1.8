package interfaces;

import game.Board;

public interface IVerify {

	default boolean verify(Board board, int row, int col) {
		try {
			return (row >= 0 && row < board.getRow() && col >= 0 && col < board.getCol()) ? true : false;
		} catch (NullPointerException npe) {
			npe.getMessage();
			return false;
		}
	}
	
}
