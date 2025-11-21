import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SudokuGUI extends JFrame {
    private JTextField[][] cells;
    private SudokuSolver solver;
    private SudokuGenerator generator;

    public SudokuGUI() {
        cells = new JTextField[SudokuSolver.SIZE][SudokuSolver.SIZE];
        generator = new SudokuGenerator();

        setTitle("Sudoku Solver");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SudokuSolver.SIZE + 1, SudokuSolver.SIZE));

        for (int row = 0; row < SudokuSolver.SIZE; row++) {
            for (int col = 0; col < SudokuSolver.SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                add(cells[row][col]);
            }
        }

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] board = new int[SudokuSolver.SIZE][SudokuSolver.SIZE];
                for (int row = 0; row < SudokuSolver.SIZE; row++) {
                    for (int col = 0; col < SudokuSolver.SIZE; col++) {
                        String text = cells[row][col].getText();
                        if (!text.isEmpty()) {
                            board[row][col] = Integer.parseInt(text);
                        } else {
                            board[row][col] = 0;
                        }
                    }
                }

                solver = new SudokuSolver(board);
                if (solver.solve()) {
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(null, "No solution exists!");
                }
            }
        });

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] puzzle = generator.generatePuzzle(40); // generate a puzzle with 40 empty cells
                solver = new SudokuSolver(puzzle);
                updateBoard();
            }
        });

        add(generateButton);
        add(solveButton);
    }

    private void updateBoard() {
        int[][] solvedBoard = solver.getBoard();
        for (int row = 0; row < SudokuSolver.SIZE; row++) {
            for (int col = 0; col < SudokuSolver.SIZE; col++) {
                if (solvedBoard[row][col] == 0) {
                    cells[row][col].setText("");
                } else {
                    cells[row][col].setText(String.valueOf(solvedBoard[row][col]));
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuGUI().setVisible(true);
            }
        });
    }
}


class SudokuSolver {
    private int[][] board;
    public static final int SIZE = 9;

    public SudokuSolver(int[][] board) {
        this.board = board;
    }

    public boolean isSafe(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int sqrt = (int) Math.sqrt(SIZE);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isSafe(row, col, num)) {
                            board[row][col] = num;
                            if (solve()) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public void printBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int d = 0; d < SIZE; d++) {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int) Math.sqrt(SIZE) == 0) {
                System.out.print("");
            }
        }
    }
}


class SudokuGenerator {
    private static final int SIZE = 9;
    private static final int SUBGRIDSIZE = 3;
    private int[][] board;
    private Random random;

    public SudokuGenerator() {
        board = new int[SIZE][SIZE];
        random = new Random();
    }

    public int[][] generatePuzzle(int emptyCells) {
        fillBoard();
        removeCells(emptyCells);
        return board;
    }

    private void fillBoard() {
        fillDiagonalSubgrids();
        fillRemaining(0, SUBGRIDSIZE);
    }

    private void fillDiagonalSubgrids() {
        for (int i = 0; i < SIZE; i += SUBGRIDSIZE) {
            fillSubgrid(i, i);
        }
    }

    private void fillSubgrid(int row, int col) {
        boolean[] used = new boolean[SIZE + 1];
        for (int i = 0; i < SUBGRIDSIZE; i++) {
            for (int j = 0; j < SUBGRIDSIZE; j++) {
                int num;
                do {
                    num = random.nextInt(SIZE) + 1;
                } while (used[num]);
                used[num] = true;
                board[row + i][col + j] = num;
            }
        }
    }

    private boolean fillRemaining(int i, int j) {
        if (j >= SIZE && i < SIZE - 1) {
            i++;
            j = 0;
        }
        if (i >= SIZE && j >= SIZE)
            return true;

        if (i < SUBGRIDSIZE) {
            if (j < SUBGRIDSIZE)
                j = SUBGRIDSIZE;
        } else if (i < SIZE - SUBGRIDSIZE) {
            if (j == (i / SUBGRIDSIZE) * SUBGRIDSIZE)
                j += SUBGRIDSIZE;
        } else {
            if (j == SIZE - SUBGRIDSIZE) {
                i++;
                j = 0;
                if (i >= SIZE)
                    return true;
            }
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(i, j, num)) {
                board[i][j] = num;
                if (fillRemaining(i, j + 1))
                    return true;
                board[i][j] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num || board[x][col] == num)
                return false;
        }

        int startRow = row - row % SUBGRIDSIZE, startCol = col - col % SUBGRIDSIZE;
        for (int i = 0; i < SUBGRIDSIZE; i++)
            for (int j = 0; j < SUBGRIDSIZE; j++)
                if (board[i + startRow][j + startCol] == num)
                    return false;

        return true;
    }

    private void removeCells(int numCells) {
        int count = numCells;
        while (count != 0) {
            int cellId = random.nextInt(SIZE * SIZE);
            int i = (cellId / SIZE);
            int j = cellId % SIZE;
            if (j != 0)
                j--;

            if (board[i][j] != 0) {
                count--;
                board[i][j] = 0;
            }
        }
    }
}
