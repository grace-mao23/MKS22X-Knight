public class KnightBoard {
  private int[][] board;
  private int[] rowMoves;
  private int[] colMoves;

  //throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows < 0 || startingCols < 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative");
    }
    board = new int[startingRows][startingCols];
    rowMoves = new int[] { -2, -2, -1, 1, 2, 2, -1, 1 };
    colMoves = new int[] { -1, 1, 2, 2, 1, -1, -2, -2 };
  }


  public String toString() {
    String result = "";
    for (int i = 0; i < board.length; i++) {
      for (int x = 0; x < board[i].length; x++) {
        if (board[i][x] == 0) {
            result += " " + board[i][x]; // should be " _" after testing
        } else if (board[i][x] < 10) {
          result += " " + (board[i][x]) + " ";
        } else {
          result += (board[i][x]) + " ";
        }
      }
      result += "\n";
    }
    return result;
  }

  private void reset() {
    for (int i = 0; i < board.length; i++) {
      for (int x = 0; x < board[i].length; x++) {
        board[i][x] = 0;
      }
    }
  }

  //throws IllegalStateException when the board contains non-zero values.
  //throws IllegalArgumentException when either parameter is negative or out of bounds.
  public boolean solve(int startingRow, int startingCol) {
    for (int i = 0; i < board.length; i++) {
      for (int x = 0; x < board[i].length; x++) {
        if (board[i][x] != 0) {
          throw new IllegalStateException("Board is not empty");
        }
      }
    }
    if (startingRow < 0 || startingCol < 0 ||
        startingRow >= board.length || startingCol >= board[startingRow].length) {
      throw new IllegalArgumentException("Parameters out of bounds");
    }
    return solveH(startingRow, startingCol, 1);
  }

  // row knight is at, col knight is at, level
  private boolean solveH(int row, int col, int level) {
    if (level > board.length * board[0].length) {
      return true;
    }
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) {
      return false; // you've moved the knight out of bounds, backtrack
    }
    if (board[row][col] != 0) {
      return false;
      // your knight is either on another knight or at a place already gone to before
    }
    for (int i = 0; i < 8; i++) { // going through all 8
      board[row][col] = level;
      if (solveH(row + rowMoves[i],col + colMoves[i],level+1)) {
        return true; // checking if a solution works for the next move
      }
      board[row][col] = 0;
    }
    return false;
  }

  //throws IllegalStateException when the board contains non-zero values.
  //throws IllegalArgumentException when either parameter is negative or out of bounds.
  public int countSolutions(int startingRow, int startingCol) {
    for (int i = 0; i < board.length; i++) {
      for (int x = 0; x < board[i].length; x++) {
        if (board[i][x] != 0) {
          throw new IllegalStateException("Board is not empty");
        }
      }
    }
    if (startingRow < 0 || startingCol < 0 ||
        startingRow >= board.length || startingCol >= board[startingRow].length) {
      throw new IllegalArgumentException("Parameters out of bounds");
    }
    return countH(startingRow,startingCol, 1);
  }

  public int countH(int row, int col, int level) {
    if (level > board.length * board[0].length) {
      return 1;
    }
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) {
      return 0; // you've moved the knight out of bounds, backtrack
    }
    if (board[row][col] != 0) {
      return 0;
      // your knight is either on another knight or at a place already gone to before
    }
    int result = 0;
    for (int i = 0; i < 8; i++) { // going through all 8
      board[row][col] = level;
      result += countH(row + rowMoves[i],col + colMoves[i],level+1);
      board[row][col] = 0;
    }
    return result;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(5,5);
  //  System.out.println(k.toString());
    //System.out.println(k.solve(4,4));
  //  System.out.println(k.toString());
  //  k.reset();
  //  System.out.println(k.toString());
    System.out.println(k.countSolutions(2,2));
  }


}
