package Minesweeper;

import Minesweeper.Exception.MineSweeperException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int boardSize = 5;
        int numMines = 5;

        try{
            MineSweeper sweeper = new MineSweeper(boardSize, numMines);
            sweeper.printUserBoard();

            int row, col;

            while (sweeper.gameInProgress()) {
                Scanner input = new Scanner(System.in);
                System.out.println("Enter Row");
                row = input.nextInt();
                System.out.println("Enter Column");
                col = input.nextInt();

                try {
                    sweeper.pickCell(row, col);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Row Column [" + row + "][" + col + "] does not exist");
                }
                sweeper.printUserBoard();
            }

            if (sweeper.getHasHitBomb()) {
                System.out.println("You hit a mine!");
            } else if(sweeper.hasWon()){
                System.out.println("You won!");
            } else {
                System.out.println("The game ended unexpectedly");
            }
        } catch (MineSweeperException e){
            System.out.println(e.getMessage());
        }
    }
}
