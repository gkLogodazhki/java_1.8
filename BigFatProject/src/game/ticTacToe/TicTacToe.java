package game.ticTacToe;

import game.Game;
import game.roles.EnumResult;
import interfaces.Playable;

public class TicTacToe extends Game implements Playable {

	private static final int SIZE = 3;
	private static final char PLAYER_SIGN = 'X';
	private static final char BOT_SIGN = 'O';
	public TicTacToe() {
		this.setBoard(SIZE, SIZE, ' ');
		this.setPlayers(new TicTacToePlayer(this), new TicTacToeBot(this, PLAYER_SIGN, BOT_SIGN));
	}

	@Override
	public void startGame() {
		System.out.printf("Input format: row[0,%d] col[0,%d]\nExample input: 0 %d\nBoard", SIZE-1,SIZE-1,SIZE-1);
		this.getFirstPlayer().decideWhoIsFirst(this.getSecondPlayer());
		this.playGame();
	}

	@Override
	public void playGame() {
		this.getBoard().printBoard();

		if (this.isOnTurn()) {
			System.out.println("Your move");
			((TicTacToePlayer) this.getFirstPlayer()).move();

		} else {
			System.out.println("Bot move");
			((TicTacToeBot) this.getSecondPlayer()).move();
		}
		if (!isGameOver()) {
			this.nextTurn();
			playGame();
		}
	}

	@Override
	public boolean isGameOver() {
		if ((this.getFirstPlayer().getResult().equals(EnumResult.DRAW))
				&& (this.getSecondPlayer().getResult().equals(EnumResult.DRAW)) && this.getMoves() < SIZE*SIZE) {
			return false;
		} else {
			this.getBoard().printBoard();
			if (this.getMoves() <= SIZE*SIZE) {
				if (this.getSecondPlayer().getResult().equals(EnumResult.WIN)) {
					this.getFirstPlayer().setLose();
				} else if (this.getSecondPlayer().getResult().equals(EnumResult.LOSE)) {
					this.getFirstPlayer().setWin();;
				}
			}
			System.out.println("You " + this.getFirstPlayer().getResult());
			return true;
		}
	}

}
