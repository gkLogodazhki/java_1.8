package game.foxAndHounds;

import game.Game;
import game.roles.EnumResult;
import interfaces.Playable;

public class FoxAndHounds extends Game implements Playable {
	
	private static final int SIZE = 8;
	public FoxAndHounds() {
		this.setBoard(SIZE, SIZE, ' ');
		this.setPlayers(new FoxAndHoundsPlayer(this), new FoxAndHoundsBot(this));
	}

	@Override
	public void startGame() {
		for (int i = 1; i < SIZE; i+=2){
			this.getBoard().setField(0, i, '@');
		}
		this.getBoard().setField(SIZE-1, this.getFirstPlayer().getGameBoard().getCol(), 'F');
		this.getFirstPlayer().decideWhoIsFirst(this.getSecondPlayer());
		playGame();
	}

	@Override
	public void playGame() {
		this.getBoard().printBoard();
		if (this.isOnTurn()) {
			System.out.println("Player move");
			((FoxAndHoundsPlayer) this.getFirstPlayer()).move();
		} else {
			System.out.println("Bot move");
			((FoxAndHoundsBot) this.getSecondPlayer()).move();
		}
		
		if (!isGameOver()) {
			this.nextTurn();
			this.playGame();
		}
	}

	@Override
	public boolean isGameOver() {
		if (this.getFirstPlayer().getResult().equals(EnumResult.DRAW)) {
			return false;
		} else {
			return true;
		}
		
	}

}
