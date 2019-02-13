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

  // 0 - up left, 1 - up right, 2 - right up, 3 - right down, 4 - down right
  // 5 - down left, 6 - right down, 7 - right up
  private boolean moveKnight(int choice, int level, int row, int col) {
    if (board[row][col] != -1) return false;
    int[] rowChange = new int[] {-2, -2, -1, 1, 2, 2, 1, -1};
    int[] colChange = new int[] {-1, 1, 2, 2, 1, -1, -2, -2};
    int changedRow = row + rowChange[choice];
    int changedCol = col + colChange[choice];
    if (changedRow < 0 || changedCol < 0 ||
        changedRow > board.length || changedCol > board[changedRow].length) {
      return false;
    }
    board[row][col] = level;
    board[changedRow][changedCol] = -1;
    return true;
  }

  //throws IllegalStateException when the board contains non-zero values.
  //throws IllegalArgumentException when either parameter is negative or out of bounds.
  public boolean solve(int startingRow, int startingCol) {
    return true;
  }

  // level is the number of the knight
  private boolean solveH(int row, int col, int level) {
    return true;
  }

  //throws IllegalStateException when the board contains non-zero values.
  //throws IllegalArgumentException when either parameter is negative or out of bounds.
  public int countSolutions(int startingRow, int startingCol) {
    return -1;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(2,2);
    System.out.println(k.toString());
  }


}
