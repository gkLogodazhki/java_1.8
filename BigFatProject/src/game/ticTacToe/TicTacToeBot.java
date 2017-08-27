package game.ticTacToe;

import game.Game;
import game.roles.Bot;
import interfaces.IPlayer;

public class TicTacToeBot extends Bot implements IPlayer {

	private static final int SIZE = 3;
	private final char[] signs;
	private int[] sums;
	private int option;
	private final int[][] mapBoard;

	public TicTacToeBot(Game game, char opponentSign, char mySign) {
		super(game);
		this.signs = new char[2];
		this.signs[0] = opponentSign;
		this.signs[1] = mySign;
		this.mapBoard = new int[this.getRow()][this.getCol()];
	}

	private int getMapField(int row, int col) {
		if (this.getGameBoard().verify(this.getGameBoard(),row, col)) {
			return this.mapBoard[row][col];
		} else {
			System.out.println("can't get map board element");
			return 0;
		}
	}

	private void setMapField(int row, int col, int value) {
		if (this.getGameBoard().verify(this.getGameBoard(),row, col)) {
			this.mapBoard[row][col] = value;
		}
	}

	@Override
	public void move() {

		this.setOption();
		
		// 3 rows, 3 cols, main diagonal, second diagonal
		if (this.option == -1) {
			this.moveDone();
		} else if (this.option < SIZE) {
			for (int i = 0; i < SIZE; i++) {
				this.setMove(this.option, i);
			}
		} else if (this.option < 2*SIZE) {
			for (int i = 0; i < SIZE; i++) {
				this.setMove(i, this.option % SIZE);
			}
		} else if (this.option == 2*SIZE) {
			for (int i = 0; i < SIZE; i++) {
				this.setMove(i, i);
			}
		} else if (this.option == 2*SIZE + 1) {
			for (int i = 0; i < SIZE; i++) {
				this.setMove(i, SIZE - i - 1);
			}
		} else {
			this.setField(this.getRowMove(), this.getColMove(), signs[1]);
		}
		
	}

	private void setOption() {

		this.sums = new int[8];
		this.option = -1;

		this.setMapBoard();
		this.setSums();

		this.doIWon();
		this.doILost();
		this.doINeedToDefence();

	}

	private void setMapBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (!this.isFieldEmpty(i, j)) {
					this.setMapField(i, j, (this.isField(i, j, signs[0])) ? 1 : -1);
				}
			}
		}
	}

	private void setSums() {
		for (int i = 0; i < 3; i++) {
			sums[6] += this.getMapField(i, i);
			sums[7] += this.getMapField(i, 2 - i);
			for (int j = 0; j < 3; j++) {
				sums[i] += this.getMapField(i, j);
				sums[i + 3] += this.getMapField(j, i);
			}
		}
	}

	private boolean check (int temp) {
		if (this.option == -1) {
			for (int i = 0; i < sums.length; i++) {
				if (sums[i] == temp) {
					this.option = i;
					return true;
				}
			}
		}
		return false;
	}
	
	private void doILost() {
		if (check(SIZE)) {
			this.setLose();
			this.option = this.sums.length;
		}
	}

	private void doIWon() {
		if (check(-SIZE+1)) {
			this.setWin();
		}
	}

	private void doINeedToDefence() {
		check(SIZE-1);
	}

	private int corners() {
		return this.getMapField(0, 0) + this.getMapField(0, 2) + this.getMapField(2, 0)
				+ this.getMapField(2, 2);
	}

	private int sides() {
		return this.getMapField(0, 1) + this.getMapField(1, 0) + this.getMapField(1, 2)
				+ this.getMapField(2, 1);
	}

	private int mid() {
		return this.getMapField(1, 1);
	}

	private boolean moveDone() {
		switch (this.getGame().getMoves()) {
		case 2:
			return (this.corners() == 1) ? this.moveMid()
					: (this.sides() == 1) ? this.botFirstMoveDecision() : this.moveCorner();
		case 3:
			return (this.corners() != 0 && this.mid() != 0) ? this.moveJump()
					: (this.mid() == 0) ? this.moveMid() : this.moveCorner();
		case 4:
			return (this.corners() == 2) ? this.moveSide() : this.moveCorner();
		case 5:
			return (this.corners() != 0 && this.sides() == 0) ? this.moveSide() : this.moveCorner();
		default:
			return this.moveRandom();
		}
	}

	private boolean botFirstMoveDecision() {
		int temp = (int) (Math.random() * 2);
		if (super.isField(0, 1, this.signs[0])) {
			return (temp == 0) ? this.setMove(0, 0) : this.setMove(0, 2);
		} else if (super.isField(1, 0, this.signs[0])) {
			return (temp == 0) ? this.setMove(0, 0) : this.setMove(2, 0);
		} else if (super.isField(1, 2, this.signs[0])) {
			return (temp == 0) ? this.setMove(0, 2) : this.setMove(2, 2);
		} else {
			return (temp == 0) ? this.setMove(2, 0) : this.setMove(2, 2);
		}
	}

	private boolean moveRandom() {
		return setRandomMove();
	}

	private boolean moveMid() {
		return this.setMove(1, 1);
	}

	private boolean moveCorner() {
		super.setRandomMove();
		return ((this.getRowMove() % 2 == 0) && (this.getColMove() % 2 == 0)) ? true : this.moveCorner();
	}

	private boolean moveSide() {
		super.setRandomMove();
		return ((this.getRowMove() + this.getColMove()) % 2 == 1) ? true : this.moveSide();
	}

	private boolean moveJump() {
		return (!super.isFieldEmpty(0, 0)) ? this.setMove(2, 2)
				: (!super.isFieldEmpty(0, 2)) ? this.setMove(2, 0)
						: (!super.isFieldEmpty(2, 0)) ? this.setMove(0, 2) : this.setMove(0, 0);

	}

}
