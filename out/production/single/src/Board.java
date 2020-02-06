import java.util.Arrays;

public class Board {
    private int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        //fixme: should i make a copy?
    }

    // string representation of this board
    public String toString() {
        String result = "" + dimension() + "\n";
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                result += " " + tiles[i][j];
            }
            result += "\n";
        }

        return result;
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    private int map2Dto1D(int i, int j) {
        return i * dimension() + j;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
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
        int right_row, right_col;
        int now_row, now_col;
        int dis;
        for (now_row = 0; now_row < dimension(); now_row++) {
            for (now_col = 0; now_col < dimension(); now_col++) {
                if(tiles[now_row][now_col]==0) continue;
                right_row = ((tiles[now_row][now_col] +8) % 9) / dimension();
                right_col = ((tiles[now_row][now_col] +8) % 9) % dimension();
                dis = Math.abs(right_row - now_row) + Math.abs(right_col - now_col);
                count += dis;
//                System.out.println("" + now_row + " " + now_col + " " + right_row + " " + right_col+"  "+dis);
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming()==0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if(this==y) return true;
        if(!(y instanceof Board)) return false;
        Board that=(Board)y;
        return Arrays.deepEquals(this.tiles, that.tiles);
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board b = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        Board c = new Board(new int[][]{{8, 1, 3}, {4, 0, 2}, {7, 6, 5}});
        System.out.println(b);
        System.out.println(b.equals(c));
        System.out.println("the hamming is " + b.hamming());
        System.out.println("the manhattan is " + b.manhattan());

    }

}