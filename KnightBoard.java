public class KnightBoard {
  private int[][] board;

  //throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows < 0 || startingCols < 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative");
    }
    board = new int[startingRows][startingCols];
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
    level = 1;
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
    board[startingRow][startingCol] = -1;
    return solveH(startingRow, startingCol);
  }

  // row to move to, col to move to, level
  private boolean solveH(int row, int col, int level) {

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
    board[startingRow][startingCol] = -1;
    return countH(startingRow,startingCol);
  }

  public int countH(int row, int col) {

  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(5,5);
    System.out.println(k.toString());
  //  System.out.println(k.solve(4,4));
  //  k.reset();
  //  System.out.println(k.toString());
    System.out.println(k.countSolutions(4,4));
  }


}
