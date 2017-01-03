package Minesweeper;

import Minesweeper.Exception.MineSweeperException;

import java.util.Random;

class MineSweeper {

    /**
     * the length and width of the minesweeper board
     */
    private int boardSize;

    /**
     * the number of mines in the board
     */
    private int numMines;

    /**
     * the number of cells picked so far
     */
    private int cellsPicked;

    /**
     * whether or not we have hit a bomb yet
     */
    private boolean hasHitBomb;

    // row and col offsets to get all adjacent cells
    private static final int[][] adjacencyMappings = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
    };

    private Cell[][] board;

    /**
     * @param boardSize the length and width of the minesweeper board
     * @param numMines  the number of mines in the board
     * @throws MineSweeperException checks to ensure minesweeper game can function as expected
     */
    MineSweeper(int boardSize, int numMines) throws MineSweeperException {

        if (boardSize <= 0) {
            throw new MineSweeperException("Board size can must be a positive value");
        }

        if (numMines < 0) {
            throw new MineSweeperException("Number of mines can not be negative");
        }

        if (numMines > (boardSize * boardSize)) {
            throw new MineSweeperException("Can't have more mines than cells. Lower number of mines or increase board size");
        }

        this.boardSize = boardSize;
        this.numMines = numMines;
        this.cellsPicked = 0;
        this.hasHitBomb = false;

        //the board the user will see
        board = new Cell[this.boardSize][this.boardSize];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Cell();
            }
        }

        this.createMines();
    }

    /**
     * Reveals what is behind a cell
     *
     * @param row row index
     * @param col column index
     * @return new value of cell
     */
    boolean pickCell(int row, int col) {
        Cell cell = getCell(row, col);

        if (!cell.getHasBeenPicked()) {
            cell.setHasBeenPicked(true);
            cell.setValue(Integer.toString(numMinesAdjecent(row, col)));
            ++this.cellsPicked;
        }

        if (cell.getIsBomb()) {
            setHasHitBomb(cell.getIsBomb());
        }

        return cell.getIsBomb();
    }


    /**
     * Randomly assigns cells to be bombs
     */
    private void createMines() {

        Random rand = new Random();

        int minesCreated = 0;

        while (minesCreated < this.numMines) {
            Cell cell = this.getCell(
                    rand.nextInt(this.boardSize),
                    rand.nextInt(this.boardSize)
            );

            if (cell.getIsBomb()) {
                continue;
            }

            cell.setIsBomb(true);
            minesCreated++;
        }
    }

    /**
     * Returns a cell if it exists
     *
     * @param row row index
     * @param col column index
     * @return Minesweeper.Cell
     */
    private Cell getCell(int row, int col) {
        if (isValidIndex(row, col)) {
            return this.board[row][col];
        }

        throw new IndexOutOfBoundsException();
    }

    /**
     *
     * @param row row index
     * @param col column index
     * @return boolean whether or not the row col index exists in the current board
     */
    private boolean isValidIndex(int row, int col) {
        return (row >= 0 && row < this.board.length && col >= 0 && col < this.board[row].length);
    }

    /**
     * Calculates the number of mines adjacent the cell at the given indices
     * @param row
     * @param col
     * @return the number of mines adjacent the cell at the given indices
     */
    private int numMinesAdjecent(int row, int col) {
        int numMinesAdjecent = 0;

        int adjacentRow, adjecentCol;
        for (int[] adjecencyMap : adjacencyMappings) {
            adjacentRow = row + adjecencyMap[0];
            adjecentCol = col + adjecencyMap[1];

            try {
                Cell cell = getCell(adjacentRow, adjecentCol);
                if (cell.getIsBomb()) {
                    ++numMinesAdjecent;
                }
            } catch (IndexOutOfBoundsException e) {
                // some adjacent cells wont exist and will throw exceptions
            }
        }

        return numMinesAdjecent;
    }

    /**
     * Prints the board to stdout
     */
    void printUserBoard() {
        for (Cell[] row : this.board) {
            for (Cell cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    /**
     *
     * @return whether or not the game is in progress
     */
    boolean gameInProgress() {
        return !getHasHitBomb() && !hasWon();
    }

    /**
     *
     * @return whether or not the game has been won
     */
    boolean hasWon() {
        return ((boardSize * boardSize) - cellsPicked) == numMines;
    }

    /**
     *
     * @return have we ever hit a bomb in this game
     */
    boolean getHasHitBomb() {
        return this.hasHitBomb;
    }

    /**
     *
     * @param hasHitBomb boolean have we hit a bomb?
     */
    private void setHasHitBomb(boolean hasHitBomb) {
        this.hasHitBomb = hasHitBomb;
    }
}