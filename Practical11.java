import java.util.Random;
import java.util.Scanner;

public class Practical11{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int[][] grid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (random.nextInt(4) == 0) {
                    grid[i][j] = random.nextInt(9) + 1;
                } else {
                    grid[i][j] = 0;
                }
            }
        }

        displayGrid(grid);

        boolean gameFinished = false;
        while (!gameFinished) {
            System.out.print("Enter row (1-" + rows + "): ");
            int row = scanner.nextInt() - 1;

            System.out.print("Enter column (1-" + cols + "): ");
            int col = scanner.nextInt() - 1;

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                System.out.println("Invalid row or column. Please try again.");
                continue;
            }

            if (grid[row][col] != 0) {
                System.out.println("This cell is already filled. Choose an empty cell.");
                displayGrid(grid);
                continue;
            }

            System.out.print("Enter a number (1-9): ");
            int num = scanner.nextInt();

            if (num < 1 || num > 9) {
                System.out.println("Invalid number. Please enter a number between 1 and 9.");
                continue;
            }

            if (isNumberInRow(grid, row, num) || isNumberInColumn(grid, col, num)) {
                System.out.println("Invalid move. Number already exists in this row or column.");
                displayGrid(grid);
                continue;
            }

            grid[row][col] = num;

            displayGrid(grid);

            gameFinished = isGameFinished(grid);
            if (gameFinished) {
                System.out.println("Congratulations! You solved the Sudoku.");
            }
        }

        scanner.close();
    }

    public static void displayGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        System.out.println("\nSudoku Grid:");

        for (int i = 0; i < cols; i++) {
            System.out.print(" ---");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + grid[i][j] + " ");
                }
                System.out.print("|");
            }
            System.out.println();

            for (int i2 = 0; i2 < cols; i2++) {
                System.out.print(" ---");
            }
            System.out.println();
        }
    }

    public static boolean isGameFinished(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNumberInRow(int[][] grid, int row, int num) {
        for (int col = 0; col < grid[row].length; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberInColumn(int[][] grid, int col, int num) {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }
}

