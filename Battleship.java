import java.util.Scanner;

public class Battleship {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		welcome();
		char[][] locationBoard1 = createBoard();
		char[][] locationBoard2 = createBoard();
		playerInput(input, locationBoard1, 1);
		// System.out.println();
		printNewlines();
		playerInput(input, locationBoard2, 2);
		System.out.println();
		// history board is used to track player's hit and missed shots ('X' for hit and
		// 'O' for miss)
		char[][] historyBoard1 = createBoard();
		char[][] historyBoard2 = createBoard();
		int currentTurn = 1;
		do {
			if (currentTurn == 1) {
				if (isValidFire(input, locationBoard2, historyBoard2, 1, 2)) {
					currentTurn = 2;
					if (hasWinner(locationBoard2, historyBoard2, 1)) {
						break;
					}
				}
				System.out.println();
			} else {
				if (isValidFire(input, locationBoard1, historyBoard1, 2, 1)) {
					currentTurn = 1;
					if (hasWinner(locationBoard1, historyBoard1, 2)) {
						break;
					}
				}
				System.out.println();
			}
		} while (true);
		printFinalBoard(historyBoard1, historyBoard2);
	}

	private static void welcome() {
		System.out.println("Welcome to Battleship!\n");
	}

	private static void playerInput(Scanner input, char[][] board, int player) {
		System.out.println("PLAYER " + String.valueOf(player) + ", ENTER YOUR SHIPS' COORDINATES.");
		int ship = 1;
		int row;
		int col;
		do {
			System.out.println("Enter ship " + String.valueOf(ship) + " location:");
			row = input.nextInt();
			col = input.nextInt();
			if (isValidInput(board, row, col)) {
				// System.out.println(String.valueOf(row) + " " + String.valueOf(col));
				ship++;
			}
		} while (ship <= 5);
		printBattleShip(board);
	}

	private static boolean isValidBound(int row, int col) {
		boolean flag = false;
		if (row >= 0 && row < 5 && col >= 0 && col < 5) {
			flag = true;
		}
		return flag;
	}

	private static boolean isValidInput(char[][] board, int row, int col) {
		boolean flag = false;
		if (isValidBound(row, col)) {
			if (board[row][col] == '@') {
				System.out.println("You already have a ship there. Choose different coordinates.");
			} else {
				// update ship on the board
				board[row][col] = '@';
				flag = true;
			}
		} else {
			System.out.println("Invalid coordinates. Choose different coordinates.");
		}
		return flag;
	}

	private static boolean isValidMove(char[][] opponentBoard, char[][] opponentHistoryBoard, int row, int col,
			int player, int opponent) {
		boolean flag = false;
		if (isValidBound(row, col)) {
			if (opponentHistoryBoard[row][col] != '-') {
				System.out.println("You already fired on this spot. Choose different coordinates.");
			} else {
				if (opponentBoard[row][col] != '@') {
					System.out.printf("PLAYER %s MISSED!\n", String.valueOf(player));
					opponentHistoryBoard[row][col] = 'O';
				} else {
					System.out.printf("PLAYER %s HIT PLAYER %s's SHIP!\n", String.valueOf(player),
							String.valueOf(opponent));
					opponentHistoryBoard[row][col] = 'X';
				}
				printBattleShip(opponentHistoryBoard);
				flag = true;
			}
		} else {
			System.out.println("Invalid coordinates. Choose different coordinates.");
		}
		return flag;
	}

	private static boolean isValidFire(Scanner input, char[][] opponentBoard, char[][] opponentHistoryBoard, int player,
			int opponent) {
		boolean flag = false;
		System.out.printf("Player %s, enter hit row/column:\n", String.valueOf(player));
		int row = input.nextInt();
		int col = input.nextInt();
		if (isValidMove(opponentBoard, opponentHistoryBoard, row, col, player, opponent)) {
			flag = true;
		}
		return flag;
	}

	private static boolean hasWinner(char[][] board, char[][] historyBoard, int player) {
		int score = 0;
		boolean flag = false;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (board[row][col] == '@' && historyBoard[row][col] == 'X') {
					score++;
				}
			}
		}
		if (score == 5) {
			System.out.println(
					"PLAYER " + String.valueOf(player).toUpperCase()
							+ " WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
			flag = true;
		}
		return flag;
	}

	private static void printFinalBoard(char[][] historyBoard1, char[][] historyBoard2) {
		System.out.println("Final boards:\n");
		printBattleShip(historyBoard1);
		System.out.println();
		printBattleShip(historyBoard2);
		return;
	}

	private static char[][] createBoard() {
		char[][] board = new char[5][5];
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				board[row][col] = '-';
			}
		}
		return board;
	}

	// Use this method to print game boards to the console.
	private static void printBattleShip(char[][] board) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(board[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}

	private static void printNewlines() {
		int MAX = 100;
		for (int i = 0; i < MAX; i++) {
			System.out.println();
		}
	}
}
