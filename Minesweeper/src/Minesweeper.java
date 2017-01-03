import java.util.Scanner;

public class Minesweeper {

	public static void main(String[] args) {
		
		int[][] valueBoard = new int[5][5];//the board with bombs and empty spaces
		String[][] userBoard = new String[5][5];//the board the user will see
		int bombs;
		boolean hitBomb = true;

	    for (int row = 0; row < userBoard.length; row++)
	    {
	        for (int column = 0; column < userBoard[row].length; column++)
	        {
	            userBoard[row][column] = "x";
	        }//end 2nd for loop
	    }//end 1st for loop 
		
		valueBoard = SweeperMethods.createMines();
		SweeperMethods.printUserBoard(userBoard);
		
		while (hitBomb) {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter Row");
			int row = input.nextInt();
			System.out.println("Enter Column");
			int column = input.nextInt();
			
			if (valueBoard[row][column] != 8) {
				bombs = SweeperMethods.checkMines(valueBoard, row, column);
				userBoard[row][column] = Integer.toString(bombs);
				SweeperMethods.printUserBoard(userBoard);
				bombs = 0;
			}
			else if (valueBoard[row][column] == 8) {
				System.out.println("You hit a mine!");
				userBoard[row][column] = "8";
				SweeperMethods.printUserBoard(userBoard);
				hitBomb = false;
				break;
			}
			
			
		}//end while loop

	}//end main

}//end class
