import java.util.Arrays;

public class KnightBoard {
  private int[][] board;
  private int[][] outgoing;
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
    outgoing = new int[startingRows][startingCols];
    initialize();
  }

  // initialize board with outgoing moves
  private void initialize() {
    for (int i = 0; i < outgoing.length; i++) {
      for (int x = 0; x < outgoing[i].length; x++) {
        int count = 0;
        for (int y = 0; y < 8; y++) {
          int newRow = i + rowMoves[y];
          int newCol = x + colMoves[y];
          if (newRow >= 0 && newCol >= 0 &&
              newRow < outgoing.length && newCol < outgoing[newRow].length) {
            count++;
          }
        }
        outgoing[i][x] = count;
      }
    }
  }

  public String toStringOut() {
    String result = "";
    for (int i = 0; i < outgoing.length; i++) {
      for (int x = 0; x < outgoing[i].length; x++) {
        if (outgoing[i][x] < 10) {
          result += " " + (outgoing[i][x]) + " ";
        } else {
          result += (outgoing[i][x]) + " ";
        }
      }
      result += "\n";
    }
    return result;
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
    return solveO(startingRow, startingCol, 1);
  }

  private boolean solveO(int row, int col, int level) {
    if (level == board.length * board[0].length) {
      board[row][col] = level;
      return true;
    }
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) {
      return false; // you've moved the knight out of bounds, backtrack
    }
    if (board[row][col] != 0) {
      return false;
      // your knight is either on another knight or at a place already gone to before
    }
    // instead of going through regular list of moves, reorder (sort) to optimize
    int[] moves = reorder(row, col);
    for (int i : moves) { // NEED TO GO ONLY THROUGH ALL THE VIABLE MOVES
      board[row][col] = level;
      update(row,col);
      if (solveO(row + rowMoves[i],col + colMoves[i],level+1)) {
        return true; // checking if a solution works for the next move
      }
      board[row][col] = 0;
    }
    return false;
  }

  private void update(int row, int col) {
    int rowChanged;
    int colChanged;
    for (int i = 0; i < 8; i++) {
      rowChanged = row + rowMoves[i];
      colChanged = col + colMoves[i];
      if (rowChanged >= 0 && colChanged >= 0 &&
          rowChanged < board.length && colChanged < board[rowChanged].length) {
        outgoing[rowChanged][colChanged] -= 1;
      }
    }
  }

  // compare each moves outgoing board value and sort
  private int[] reorder(int row, int col) { // should be private
    //  System.out.println(toStringOut());
    //System.out.println(toString());
    int[] result = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
    if (row+rowMoves[0] < 0 || col+colMoves[0] < 0 ||
        row+rowMoves[0] >= board.length || col+colMoves[0] >= board[row+rowMoves[0]].length ||
        board[row+rowMoves[0]][col+colMoves[0]] != 0) {
      result[0] = 100;
    }
    for (int i = 1; i < 8; i++) {
      int currentValue = 0;
      if (row+rowMoves[i] < 0 || col+colMoves[i] < 0 ||
          row+rowMoves[i] >= board.length || col+colMoves[i] >= board[row+rowMoves[i]].length ||
          board[row+rowMoves[i]][col+colMoves[i]] != 0) {
        // not a viable move
        currentValue = 100;
      } else {
        currentValue = outgoing[row+rowMoves[i]][col+colMoves[i]];
      }
      //System.out.println(i+": " + currentValue);
      int newPlace = i;
      for (int x = i - 1; x >= 0; x--) {
        int compare = 0;
        if (result[x] == 100 || currentValue == 100) {
          compare = 100;
        } else {
          compare = outgoing[row+rowMoves[result[x]]][col+colMoves[result[x]]];
        }
        if (currentValue < compare) {
          result[x+1] = result[x]; // shifting
          newPlace = x;
        }
        if (currentValue == 100) {
          result[newPlace] = currentValue;
        } else {
          result[newPlace] = i;
        }
      }
    }
//    System.out.println(toStringOut());
  //  System.out.println(toString());
    int[] goodMoves = new int[outgoing[row][col]];
    int index = 0;
    //System.out.println(Arrays.toString(result));
    for (int i = 0; i < result.length; i++) {
      if (result[i] != 100) {
      //  System.out.println("I: "+index+ ", " + goodMoves.length + ", i: " + i);
        goodMoves[index] = result[i];
        index++;
      }
    }
  //  System.out.println(toStringOut());
    return goodMoves;
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
    return countO(startingRow,startingCol, 1);
  }

  public int countH(int row, int col, int level) {
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) {
      return 0; // you've moved the knight out of bounds, backtrack
    }
    if (board[row][col] != 0) {
      return 0;
      // your knight is either on another knight or at a place already gone to before
    }
    if (level == board.length * board[0].length) {
      return 1;
    }
    int result = 0;
    for (int i = 0; i < 8; i++) { // going through all 8
      board[row][col] = level;
      result += countH(row + rowMoves[i],col + colMoves[i],level+1);
      board[row][col] = 0;
    }
    return result;
  }

  public int countO(int row, int col, int level) {

  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(10,10);
  //  System.out.println(k.toStringOut());
    //System.out.println(Arrays.toString(k.reorder(3,1)));
  //  System.out.println(k.toString());
    System.out.println(k.solve(0,0));
    System.out.println(k.toString());
  //  k.reset();
  //  System.out.println(k.toString());
  //  System.out.println(k.countSolutions(0,0));
  }


}
