import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int[][] tiles;
    private final int dimension;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException();

        dimension=tiles.length;

        this.tiles = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        String result = "" + dimension + "\n";
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result += " " + tiles[i][j];
            }
            result += "\n";
        }

        return result;
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    private int map2Dto1D(int i, int j) {
        return i * dimension + j;
    }

    private int[][] copy() {
        int[][] newTiles = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                newTiles[i][j] = tiles[i][j];
            }
        }
        return newTiles;
    }

    private int[][] swap(int row1, int col1, int row2, int col2) {
        int[][] result = copy();

        int temp = result[row1][col1];
        result[row1][col1] = result[row2][col2];
        result[row2][col2] = temp;

        return result;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (((map2Dto1D(i, j) + 1) % (dimension * dimension) != tiles[i][j]) && tiles[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        int rightRow, rightCol;
        int nowRow, nowCol;
        int dis;
        for (nowRow = 0; nowRow < dimension; nowRow++) {
            for (nowCol = 0; nowCol < dimension; nowCol++) {
                if (tiles[nowRow][nowCol] == 0) continue;
                rightRow = ((tiles[nowRow][nowCol] + dimension * dimension - 1) % (dimension * dimension)) / dimension;
                rightCol = ((tiles[nowRow][nowCol] + dimension * dimension - 1) % (dimension * dimension)) % dimension;
                dis = Math.abs(rightRow - nowRow) + Math.abs(rightCol - nowCol);
                count += dis;
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (!(y instanceof Board)) return false;
        Board that = (Board) y;
        return Arrays.deepEquals(this.tiles, that.tiles);
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();

        int row = 0, col = 0;
        //finding zero
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (tiles[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        //能向上移
        if (row > 0) {
            boards.add(new Board(swap(row, col, row - 1, col)));
        }
        //能向左移
        if (col > 0) {
            boards.add(new Board(swap(row, col, row, col - 1)));
        }
        //能向右移
        if (col < dimension - 1) {
            boards.add(new Board(swap(row, col, row, col + 1)));
        }
        //能向下移
        if (row < dimension - 1) {
            boards.add(new Board(swap(row, col, row + 1, col)));
        }

        return boards;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int row1=0,col1=0,row2=1,col2=1;

        if(tiles[row1][col1]==0){
            row1=1;
        }
        else if(tiles[row2][col2]==0){
            row2=0;
        }

        return new Board(swap(row1, col1, row2, col2));
    }

    // unit testing (not graded)
    public static void main(String[] args) {
//        Board b = new Board(new int[][]{{2, 15, 13, 3}, {4, 14, 1, 7}, {10, 9, 5, 11}, {8, 0, 12, 6}});
//        System.out.println(b);
//
//        System.out.println(b.twin());
//        System.out.println(b.manhattan());
//        System.out.println(b.isGoal());
    }

}