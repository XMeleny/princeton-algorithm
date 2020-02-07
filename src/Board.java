import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private int[][] tiles;
    private int dimension;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException();

        //dimension
        dimension = tiles.length;
        //tiles
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
                if ((map2Dto1D(i, j) + 1) % 9 != tiles[i][j]) {
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
                rightRow = ((tiles[nowRow][nowCol] + 8) % 9) / dimension;
                rightCol = ((tiles[nowRow][nowCol] + 8) % 9) % dimension;
                dis = Math.abs(rightRow - nowRow) + Math.abs(rightCol - nowCol);
                count += dis;
//                System.out.println("" + now_row + " " + now_col + " " + right_row + " " + right_col+"  "+dis);
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
        int row= StdRandom.uniform(dimension);
        int col=StdRandom.uniform(dimension);
        return new Board(swap(0,0,row,col));
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board b = new Board(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}});
        System.out.println(b.isGoal());
////        Board c = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
//        System.out.println(b);
////        System.out.println(b.equals(c));
////        System.out.println("the hamming is " + b.hamming());
////        System.out.println("the manhattan is " + b.manhattan());
//
//        for (Board board : b.neighbors()) {
//            System.out.println(board);
//        }

    }

}