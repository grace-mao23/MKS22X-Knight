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
        if (board[i][x] < 10) {
          result += " " + board[i][x] + " ";
        } else {
          result += board[i][x] + " ";
        }
      }
      result += "\n";
    }
    return result;
  }

  //throws IllegalStateException when the board contains non-zero values.
  //throws IllegalArgumentException when either parameter is negative or out of bounds.
  public boolean solve(int startingRow, int startingCol) {
    return true;
  }

  // level is the number of the knight
  private boolean solveH(int row ,int col, int level) {
    return true;
  }

  //throws IllegalStateException when the board contains non-zero values.
  //throws IllegalArgumentException when either parameter is negative or out of bounds.
  public int countSolutions(int startingRow, int startingCol) {
    return -1;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(2,2);
    System.out.println()
  }


}
