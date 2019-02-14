public class KnightBoard {
  private int[][] board;
  private int level = 1;

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

  private int[] move(int i) {
    int[] rowChange = new int[] {-2, -2, -1, 1, 2, 2, 1, -1};
    int[] colChange = new int[] {-1, 1, 2, 2, 1, -1, -2, -2};
    int[] result = new int[] { rowChange[i], colChange[i] };
    return result;
  }

  // 0 - up left, 1 - up right, 2 - right up, 3 - right down, 4 - down right
  // 5 - down left, 6 - right down, 7 - right up
  private boolean moveKnight(int choice, int row, int col) {
    if (board[row][col] != -1) return false;
    int changedRow = row + move(choice)[0];
    int changedCol = col + move(choice)[1];
  //  System.out.println("Row: " + changedRow + ", Col: " + changedCol);
    if (changedRow < 0 || changedCol < 0 ||
        changedRow >= board.length || changedCol >= board[0].length ||
        board[changedRow][changedCol] != 0) {
      return false;
    }
    board[row][col] = level;
    board[changedRow][changedCol] = -1;
    level++;
    return true;
  }

  private boolean backKnight(int choice, int row, int col) {
    if (board[row][col] != -1) return false;
    int backRow = row - move(choice)[0];
    int backCol = col - move(choice)[1];
    if (backRow < 0 || backCol < 0 ||
        backRow >= board.length || backCol >= board[0].length ||
        board[backRow][backCol] == 0) {
      return false;
    }
    board[row][col] = 0;
    board[backRow][backCol] = -1;
    level--;
    return true;
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

  // level is the number of the knight
  private boolean solveH(int row, int col) {
    if (level == board.length * board[0].length) {
      board[row][col] = level;
      return true; // board solved
    }
    for (int i = 0; i < 8; i++) {
  //    System.out.println("AChoice: "+i+" Round: "+round+"\n"+toString());
      boolean moved = moveKnight(i,row,col);
      if (moved && solveH(row+move(i)[0],col+move(i)[1])) {
//        System.out.println("Choice: "+i+"Round: "+round+"\n"+toString());
        return true;
      }
      if (moved) {
  //      System.out.println("False activated Row: " + row + " Col: "+ col);
        backKnight(i,row + move(i)[0],col + move(i)[1]);
      }
    }
//    System.out.println("False");
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
    board[startingRow][startingCol] = -1;
    return countH(startingRow,startingCol);
  }

  public int countH(int row, int col) {
    System.out.println(toString());
    if (level == board.length * board[0].length) {
      System.out.println("Row: " + row + ", Col: " + col);
      board[row][col] = level;
      return 1; // board solved
    }
    int result = 0;
    for (int i = 0; i < 8; i++) {
      System.out.println("Row: " + row + ", Col: " + col);
      boolean moved = moveKnight(i,row,col);
      if (moved) {
        System.out.println("Moved: \n" + toString());
        result += countH(row+move(i)[0],col+move(i)[1]);
      //  System.out.println("ARow: "+move(i)[0]+ ", Col: " + move(i)[1]);
        backKnight(i,row-move(i)[0],col-move(i)[1]);
      } else if (i == 7) {
        backKnight(i,row,col);
      }
      System.out.println("Test:\n"+toString());
    }
    return 0;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(5,5);
    System.out.println(k.toString());
  //  System.out.println(k.solve(4,4));
    k.reset();
  //  System.out.println(k.toString());
    System.out.println(k.countSolutions(4,4));
  }


}
