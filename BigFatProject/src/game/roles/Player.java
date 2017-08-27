package game.roles;

import game.Board;
import game.Game;
import interfaces.IVerify;

public class Player implements IVerify {

	private Game game;
	private int[] move;
	private boolean isFirst;
	private EnumResult result;	
	private int madeMoves;
	
	public int getMadeMoves() {
		return this.madeMoves;
	}

	public Player(Game game) {
		this.game = game;
		this.move = new int[2];
		this.isFirst = false;
		this.result = EnumResult.DRAW;
	}
	
	public void decideWhoIsFirst(Player player) {
		if (this != null && (int) (Math.random() * 2) == 0) {
			this.setIsFirst();
		} else if (player != null){
			player.setIsFirst();
		}
	}
	
	protected Game getGame() {
		return this.game;
	}
	
	public EnumResult getResult() {
		return this.result;
	}
	
	public void setWin() {
		this.result = EnumResult.WIN;
	}
	
	public void setLose() {
		this.result = EnumResult.LOSE;
	}

	public int getIndex(Player player) {
		return (this.equals(player)) ? 1 : -1;
	}

	public boolean isFirst() {
		return this.isFirst;
	}

	public void setIsFirst() {
		this.isFirst = true;
	}
	
	private int[] getMove() {
		if (this.move != null) {
			return this.move;
		} else {
			System.out.println("get move");
			return null;
		}
	}
	
	protected boolean setMove(int row, int col) {
		if (this.isFieldEmpty(row, col)) {
			this.madeMoves++;
			this.getMove()[0] = row;
			this.getMove()[1] = col;
			return true;
		} else {
			return false;
		}
	}
	
	protected int getRowMove() {
		return this.getMove()[0];
	}

	protected int getColMove() {
		return this.getMove()[1];
	}
	
	public Board getGameBoard() {
		return this.getGame().getBoard();
	}
	protected void setField(int row, int col, char ch) {
		this.getGameBoard().setField(row, col, ch);
	}
	
	protected void increaseField(int row, int col) {
		this.getGameBoard().increaseField(row, col);
	}

	protected int getRow() {
		return this.getGameBoard().getRow();
	}

	protected int getCol() {
		return this.getGameBoard().getCol();
	}

	protected boolean isField(int row, int col, char ch) {
		return this.getGameBoard().isField(row, col, ch);
	}

	protected boolean isFieldEmpty(int row, int col) {
		return this.isField(row, col, ' ');
	}

}
