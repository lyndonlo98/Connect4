import java.util.Scanner;

public class Connect4Model {
	private int[][] board;
	private int w, h;

	public Connect4Model(int h, int w) {
		board = new int[w][h];
		this.w = w;
		this.h = h;
	}
	
	/**
	 * This method add a piece to a column
	 * @param
	 * @return void
	 */
	public void addPiece(int player, int column) {
		// Start from bottom, add a piece if its empty
		for(int i = w - 1; i >= 0; i--) {
			if(board[i][column] == 0) {
				board[i][column] = player;
				return;
			}						
		}
	}

	public int has4() {
		final int HEIGHT = board.length;
		final int WIDTH = board[0].length;
		final int EMPTY_SLOT = 0;

		for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
			for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
				int player = board[r][c];
				if (player == EMPTY_SLOT)
					continue; // don't check empty slots

				if (c + 3 < WIDTH &&
						player == board[r][c+1] && // look right
						player == board[r][c+2] &&
						player == board[r][c+3])
					return player;
				if (r + 3 < HEIGHT) {
					if (player == board[r+1][c] && // look up
							player == board[r+2][c] &&
							player == board[r+3][c])
						return player;
					if (c + 3 < WIDTH &&
							player == board[r+1][c+1] && // look up & right
							player == board[r+2][c+2] &&
							player == board[r+3][c+3])
						return player;
					if (c - 3 >= 0 &&
							player == board[r+1][c-1] && // look up & left
							player == board[r+2][c-2] &&
							player == board[r+3][c-3])
						return player;
				}
			}
		}
		return EMPTY_SLOT; // no winner found
	}

	public String toString() {
		String s = "";
		for(int i = 0; i < h; i++) {
			s += " " + i + "  ";
		}
		s += "\n";
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				if(board[i][j] == 0) {
					s += "[_]" + " ";
				}else {
					s += "[" + board[i][j] + "]" + " ";
				}
			}			
			s+="\n";
		}
		return s;
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		// we define some variables for our game like 
		// dimensions and nb max of moves
		int height = 6; int width = 7; int moves = height * width;

		// we create the ConnectFour instance
		Connect4Model board = new Connect4Model(width, height);

		// we explain users how to enter their choices
		System.out.println("Use 0-" + (width - 1) + " to choose a column");
		// we display initial board
		System.out.println(board);

		// we iterate until max nb moves be reached
		// simple trick to change player turn at each iteration
		
		/*
		 * 
		 * alternate between player 1 and 2
		 * 
		 */
		int player = 0;
		while( moves-- > 0) {
			// current player
			if(moves%2 == 0) {
				player = 2;
			}else {
				player = 1;
			}

			//player--;
			do {
				System.out.println("\nPlayer " + player+ " turn: ");
				int col = input.nextInt();

				// check if column is ok
				if (!(0 <= col && col < width)) {
					System.out.println("Column must be between 0 and " + (width - 1));
					continue;
				}
				else if(0 <= col && col < width) {
					board.addPiece(player, col);
					break;
				}
				System.out.println("Column " +col + " is full.");
			}while(true);
			
			// we display the board
			System.out.println(board);

			// we need to check if a player won. If not, 
			// we continue, otherwise, we display a message
			if (board.has4() != 0) {
				System.out.println("\nPlayer " + board.has4() + " wins!");
				return;
			}
		}
		System.out.println("Game over. No winner. Try again!");
	}
}


