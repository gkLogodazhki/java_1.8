package game;

import java.util.Scanner;

import game.dotsAndBoxes.DotsAndBoxes;
import game.foxAndHounds.FoxAndHounds;
import game.minesweeper.Minesweeper;
import game.roles.Player;
import game.ticTacToe.TicTacToe;
import interfaces.IGame;
import interfaces.Playable;

public class Game implements IGame {

	private Board board;
	private Player[] players;
	private int moves;
	private int allMoves;

	public Game() {
		this.board = new Board();
		this.moves = 1;
		this.players = new Player[2];
	}

	public void newGame(Playable game) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		loop: while (true) {
			System.out.print(
					"\n\tMENU\n1) TicTacToe\n2) Minesweeper\n3) DotsAndBoxes\n4) Fox\n5) Exit\nChoose an option: ");
			switch (sc.nextInt()) {
			case 1:
				game = new TicTacToe();
				((TicTacToe) game).startGame();
				break;
			case 2:
				game = new Minesweeper();
				((Minesweeper) game).startGame();
				break;
			case 3:
				game = new DotsAndBoxes();
				((DotsAndBoxes) game).startGame();
				break;
			case 4:
				game = new FoxAndHounds();
				((FoxAndHounds) game).startGame();
				break;
			case 5:
				break loop;
			default:
				continue loop;
			}
		}
	}

	protected void setBoard(int row, int col, char ch) {
		this.board = new Board(row, col, ch);
	}

	protected void setPlayers(Player p1, Player p2) {
		this.players[0] = p1;
		this.players[1] = p2;
	}

	public boolean isOnTurn() {
		return (this.getFirstPlayer().isFirst()) ? ((this.getMoves() % 2 == 1) ? true : false)
				: ((this.getMoves() % 2 == 1) ? false : true);
	}

	public void nextTurn() {
		this.moves++;
	}

	public Board getBoard() {
		return this.board;
	}

	public Player getFirstPlayer() {
		return this.players[0];
	}

	public Player getSecondPlayer() {
		return this.players[1];
	}

	public int getMoves() {
		return this.moves;
	}

	public int getAllMoves() {
		return this.allMoves;
	}

	protected void setAllMoves(int allMoves) {
		if (allMoves >= 0 && allMoves <= this.getBoard().getRow() * this.getBoard().getCol()) {
			this.allMoves = allMoves;
		}
	}

}
