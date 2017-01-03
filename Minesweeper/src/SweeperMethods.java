import java.util.Random;
public class SweeperMethods extends Minesweeper {

	
	public static int[][] createMines() {
	
	int numMines = 5;//sets number of mines to be spawned on the board
	int[][] board = new int[5][5];//sets map size of X by Y dimensions
	
	Random rand = new Random();//Creates a random object which we will use to select random spot for mines
	
	
	for (int i = 0; i < numMines; i++) { //for loop to write the mines onto the board
		int columnMine = rand.nextInt(5);//randomly generates what column will have a mine
		int rowMine = rand.nextInt(5);//randomly generates what row will have a mine
		board[rowMine][columnMine] = 8;//sets the random spot in the array to be a bomb which is denotes at 8
		}//end for loop
	
		return board;
	}//end createBoard
	
	public static boolean validIndex(int [][]b, int x, int y) {
		    boolean result = false;

		    for (int i = 0; i < b.length; i++) {
		        for (int j = 0; j < b.length; j++) {
		        	if (x >= 0 && x < b.length && y >= 0 && y < b[x].length) {
		                result = true;
		            }
		        }
		    }
		return result;
		}
	
	public static int checkMines(int[][] b, int x, int y) {
		int num = 8;
		int mineAdj = 0;//how many mines are adjacent
		boolean topLeft = validIndex(b, (x-1), (y-1));
		boolean top  = validIndex(b, (x-1), (y));
		boolean topRight = validIndex(b, (x-1), (y+1));
		boolean bottom = validIndex(b, (x+1), (y));
		boolean bottomLeft = validIndex(b, (x+1), (y-1));
		boolean bottomRight = validIndex(b, (x+1), (y+1));
		boolean left = validIndex(b, (x), (y-1));
		boolean right = validIndex(b, (x), (y+1));
		
		while (num > 0)	{
			if (topLeft && b[x-1][y-1] == 8) {
				mineAdj++;
				topLeft = false;
			}
			else if (topRight && b[x-1][y+1] == 8 ) {
				mineAdj++;
				topRight = false;
			}
			else if (top && b[x-1][y] == 8) {
				mineAdj++;
				top = false;
			}
			else if (left && b[x][y-1] == 8) {
				mineAdj++;
				left = false;
			}
			else if (right && b[x][y+1] == 8) {
				mineAdj++;
				right = false;
			}
			else if (bottomLeft && b[x+1][y+1] == 8) {
				mineAdj++;
				bottomLeft = false;
			}
			else if (bottom && b[x+1][y] == 8) {
				mineAdj++;
				bottom = false;
			}
			else if (bottomRight && b[x+1][y+1] == 8) {
				mineAdj++;
				bottomRight = false;
			}
			num--;
		}//end while
		return mineAdj;
	}//end checkMines
	
	public static void printUserBoard(String[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++ ) {
				System.out.print(b[i][j] + " ");
			}//end 2nd for loop
			System.out.println();
		}//end for 1st loop
	}//end printBoard method
	
    public static boolean win(String b[][]) {
        int count=0;
        for(int row = 0 ; row < b.length; row++) {
            for(int column = 0 ; column < b[0].length; column++) {
                if(b[row][column].equals("x")) {
                    count++;
                }
            }
        }
        if(count == 6) {
            return true;
        }
        else {
            return false;     
        }
    }
}//end class
	


