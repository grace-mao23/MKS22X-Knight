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

  }

  //throws IllegalStateException when the board contains non-zero values.
  //throws IllegalArgumentException when either parameter is negative or out of bounds.
  public boolean solve(int startingRow, int startingCol) {

  }

  //throws IllegalStateException when the board contains non-zero values.
  //throws IllegalArgumentException when either parameter is negative or out of bounds.
  public int countSolutions(int startingRow, int startingCol) {

  }

  private boolean solveH(int row ,int col, int level) {

  }
  // level is the # of the knight
}
