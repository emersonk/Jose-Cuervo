package AI;

import ygraphs.ai.smart_fox.games.GameModel;

/**
 *
 *
 * @author macke Class containing board as string[][] supports new locations,
 *         and generating new boards from actions
 *
 */

public class State extends GameModel {
	// constants for board marking
	public static final String POS_MARKED_BLACK = "black";
	public static final String POS_MARKED_WHITE = "white";
	public static final String POS_MARKED_ARROW = "arrow";
	public static final String POS_AVAILABLE = "available";
	private String[][] board;
	int rows, columns;

	// constructor for new state with rows and columns of the game board
	public State(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		board = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.board[i][j] = "available";
			}
		}
	}

	public State(String[][] b) {
		this.board = b;
	}

	// marks a new move on the current instance of state.board
	/**
	 * Marks a move on the board associated with this state (changes this board)
	 * 
	 * @param move
	 * @return marks move and returns true is move is on the board
	 */
	public boolean positionMarked(GameMove move) {
		boolean valid = true;
		if ((((move.newRow >= board.length ? 1 : 0) | (move.newCol >= board.length ? 1 : 0)) != 0)
				|| (move.newRow <= -1) || (move.newCol <= -1)) {
			valid = false;
		} else if (!board[move.newRow][move.newCol].equalsIgnoreCase("available")) {
			valid = false;
		}
		if (move.newRow != -1 && move.newCol != -1)
			board[move.newRow][move.newCol] = board[move.row][move.col];
		if (move.row != -1 && move.row != -1)
			board[move.row][move.col] = "available";
		if (move.arrowRow != -1 && move.arrowCol != -1)
			board[move.arrowRow][move.arrowCol] = "arrow";

		return valid;
	}

	/**
	 * 
	 * @param state
	 *            state being moved on
	 * @param move
	 *            move being performed
	 * @return receives a move and a state, then executes the move on the state
	 *         and returns a new board
	 */
	public State result(State state, GameMove move) {
		State newState = new State(state.rows, state.columns);
		for (int i = 0; i < state.board.length; i++) {
			for (int j = 0; j < state.board[0].length; j++) {
				newState.board[i][j] = state.board[i][j];
			}
		}
		newState.positionMarked(move);
		return newState;

	}

	/**
	 * 
	 * @param i
	 *            row coordinate
	 * @param j
	 *            column coordinate
	 * @param text
	 *            set a particular board location,
	 */
	public void setBoardLocation(int i, int j, String text) {
		this.board[i][j] = text;
	}

	/**
	 * 
	 * @return returns the board associated with this state
	 */
	public String[][] getBoard() {
		return this.board;
	}

	/**
	 * returns a String representation of the board state associated with the
	 * current node
	 */
	public String toString() {
		String b = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				switch (this.board[i][j]) {
				case "white":
					b += "|W";
					break;
				case "black":
					b += "|B";
					break;
				case "arrow":
					b += "|+";
					break;
				case "available":
					b += "|_";
					break;
				}
			}
			b += "|\n";
		}
		return b;
	}

	public String toString(int row, int col) {
		String b = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (i == row && j == col) {
					b += "|x";
				} else
					switch (this.board[i][j]) {
					case "white":
						b += "|W";
						break;
					case "black":
						b += "|B";
						break;
					case "arrow":
						b += "|+";
						break;
					case "available":
						b += "| ";
						break;
					}
			}
			b += "|\n";
		}
		return b;
	}
}
