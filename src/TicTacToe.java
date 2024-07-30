import java.util.Scanner;
import java.util.Random;
/**
 * A simple console-based Tic-Tac-Toe game where a player can play against the computer.
 */
public class TicTacToe {
	/**
	 * A simple console-based Tic-Tac-Toe game where a player can play against the computer.
	 */
	private static char[][] board = {
			{'-','-','-'},
			{'-','-','-'},
			{'-','-','-'},
			};
	
	/**
     * The main method that runs the Tic-Tac-Toe game.
     * It manages the game loop, determines the first player, and handles the game restart logic.
     * 
     * @param args Command line arguments (not used).
     */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to Tic-Tac-Toe");
		System.out.println("Board Map:");
		System.out.println("1 | 2 | 3\n4 | 5 | 6\n7 | 8 | 9\n");
		//Check if player wants to go first
		boolean first = playFirst(in);
		//Initiate game loop
		while(true) {
			while(true) {
			if (first == true) {
				System.out.println();
				System.out.println("Current game:");
				printBoard();
				System.out.println();
				if (gameIsOver()==true) {
					break;
				}
				playerTurn(in);
				if (gameIsOver()==true) {
					break;
				}
				computerTurn();
			}else {
				System.out.println();
				System.out.println("Current game:");
				if (gameIsOver()==true) {
					break;
				}
				computerTurn();
				printBoard();
				System.out.println();
				if (gameIsOver()==true) {
					break;
				}
				playerTurn(in);
			}
			}
			if (playAgain(in)==true) {
				reset();
			}else {
				System.out.println("Thank's for playing!!");
				break;
			}
			
		}
	}
	
	/**
     * Resets the game board to the initial empty state.
     */
	private static void reset() {
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
				
				}
			}
		}
		
	
	/**
     * Asks the player if they want to play another game.
     * 
     * @param in The Scanner object for user input.
     * @return true if the player wants to play again, false otherwise.
     */
	private static boolean playAgain(Scanner in) {
		boolean open = true;
		String answer;
		
		
		while(open) {
			System.out.println();
			System.out.println("Do you want to play again?");
			answer = in.nextLine();
		if (answer.equalsIgnoreCase("yes")||answer.equalsIgnoreCase("no")){
			open = false;
		}else {
			System.out.println("Please enter a valid input");
		}
		if (answer.equalsIgnoreCase("yes")) {
			return true;
		}
		}
		return false;
		
	}
	
	/**
     * Checks if the game is over by determining if there's a winner or the board is full.
     * 
     * @return true if the game is over, false otherwise.
     */
	private static boolean gameIsOver() {
		if(won('X')) {
			printBoard();
			System.out.println("You won!!");
			return true;
		}
		if (won('O')) {
			printBoard();
			System.out.println("You lost");
			return true;
		}
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == '-') {
					return false;
				}
			}
		}
		System.out.println("It's a Tie");
		printBoard();
		return true;
	}
	
	/**
     * Checks if the specified player has won the game.
     * 
     * @param player The character representing the player ('X' or 'O').
     * @return true if the player has won, false otherwise.
     */
	private static boolean won(char player) {
		if(board[0][0]==player && board[0][1]==player && board[0][2]== player ||
		   board[1][0]==player && board[1][1]==player && board[1][2]== player ||	
		   board[2][0]==player && board[2][1]==player && board[2][2]== player ||
		   board[0][0]==player && board[1][0]==player && board[2][0]== player ||
		   board[0][1]==player && board[1][1]==player && board[2][1]== player ||
		   board[0][2]==player && board[1][2]==player && board[2][2]== player ||
		   board[0][0]==player && board[1][2]==player && board[2][2]== player ||
		   board[0][2]==player && board[1][1]==player && board[2][0]== player){
			return true;
		}
		return false;
	}
	
	/**
     * Checks if the specified position on the board is valid and available.
     * 
     * @param position The position to check (1-9).
     * @return true if the position is valid and available, false otherwise.
     */
	private static boolean isValid(String position) {
		switch (position) {
		case "1": {
			return (board[0][0]=='-');
		}
		case "2": {
			return (board[0][1]=='-');
		}
		case "3": {
			return (board[0][2]=='-');
		}
		case "4": {
			return (board[1][0]=='-');
		}
		case "5": {
			return (board[1][1]=='-');
		}
		case "6": {
			return (board[1][2]=='-');
		}
		case "7": {
			return (board[2][0]=='-');
		}
		case "8": {
			return (board[2][1]=='-');
		}
		case "9": {
			return (board[2][2]=='-');
		}
		default:
			return false;
		}
	}
	
	/**
     * Handles the computer's turn by randomly selecting an available spot.
     */
	private static void computerTurn() {
		Random rand = new Random();
		int move;
		while(true) {
		move = rand.nextInt(9) + 1;
		if (isValid(Integer.toString(move))) {
			break;
		}
		}
		System.out.println("Computer move: " + move);
		placeMove(Integer.toString(move), 'O');
	}


	/**
     * Handles the player's turn by asking for input and placing their mark on the board.
     * 
     * @param in The Scanner object for user input.
     */
	private static void playerTurn(Scanner in) {
		while(true) {
		System.out.println("what position do you want to play?");
		String move = in.nextLine();
		if (isValid(move)) {
			placeMove(move, 'X');
			break;
		}else {
			System.out.println("Please enter a valid move");
		}
		}
	}

	/**
     * Places the player's or computer's mark on the board at the specified position.
     * 
     * @param position The position to place the mark (1-9).
     * @param mark The mark to place ('X' or 'O').
     */
	private static void placeMove(String position, char mark) {
		switch (position) {
		case "1": {
			board[0][0]= mark;
			break;
		}
		case "2": {
			board[0][1]= mark;
			break;
		}
		case "3": {
			board[0][2]= mark;
			break;
		}
		case "4": {
			board[1][0]= mark;
			break;
		}
		case "5": {
			board[1][1]= mark;
			break;
		}
		case "6": {
			board[1][2]= mark;
			break;
		}
		case "7": {
			board[2][0]= mark;
			break;
		}
		case "8": {
			board[2][1]= mark;
			break;
		}
		case "9": {
			board[2][2]= mark;
			break;
		}
		default:
			return;
		}
	}


	/**
     * Prints the current state of the game board.
     */
	private static void printBoard() {
		for (int i = 0; i < board.length; i++) {
	        System.out.printf("%c | %c | %c%n", board[i][0], board[i][1], board[i][2]);
	        
		}
	}
	
	/**
     * Asks the player if they want to play first.
     * 
     * @param in The Scanner object for user input.
     * @return true if the player wants to play first, false otherwise.
     */
	private static boolean playFirst(Scanner in) {
		boolean open = true;
		String answer;
		
		//Check input
		while(open) {
			System.out.println("Do you want to play first?");
			answer = in.nextLine();
		if (answer.equalsIgnoreCase("yes")||answer.equalsIgnoreCase("no")){
			open = false;
		}else {
			System.out.println("Please enter a valid input");
		}
		if (answer.equalsIgnoreCase("yes")) {
			return true;
		}
		}
		return false;
		
	}
	
	
	
	
}

