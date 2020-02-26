import edu.princeton.cs.algs4.Picture;

import java.awt.Color;

public class SeamCarver {
    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();
        this.picture = new Picture(picture);
    }

    // current picture
    public Picture picture() {
        return picture;
    }

    // width of current picture
    public int width() {
        return picture.width();
    }

    // height of current picture
    public int height() {
        return picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (!(0 <= x && x <= width() - 1 && 0 <= y && y <= height() - 1)) throw new IllegalArgumentException();

        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1) return 1000;

        Color left = picture.get(x - 1, y);
        Color right = picture.get(x + 1, y);
        Color up = picture.get(x, y - 1);
        Color down = picture.get(x, y + 1);


        int rx = left.getRed() - right.getRed();
        int gx = left.getGreen() - right.getGreen();
        int bx = left.getBlue() - right.getBlue();

        int ry = up.getRed() - down.getRed();
        int gy = up.getGreen() - down.getGreen();
        int by = up.getBlue() - down.getBlue();

        return Math.sqrt(rx * rx + gx * gx + bx * bx + ry * ry + gy * gy + by * by);
    }

    private void relax(int x, int y, boolean isHor, double[][] distTo, int[][] edgeTo) {
        double energy = energy(x, y);
        if (isHor) {
            //处理水平seam

            //如果有左上元素
            if (y > 0) {
                if (distTo[x - 1][y - 1] + energy < distTo[x][y]) {
                    distTo[x][y] = distTo[x - 1][y - 1] + energy;
                    edgeTo[x][y] = y - 1;
                }
            }
            if (distTo[x - 1][y] + energy < distTo[x][y]) {
                distTo[x][y] = distTo[x - 1][y] + energy;
                edgeTo[x][y] = y;

            }
            //如果有左下元素
            if (y < height() - 1) {
                if (distTo[x - 1][y + 1] + energy < distTo[x][y]) {
                    distTo[x][y] = distTo[x - 1][y + 1] + energy;
                    edgeTo[x][y] = y + 1;
                }
            }
        } else {
            //如果有左上元素
            if (x > 0) {
                if (distTo[x - 1][y - 1] + energy < distTo[x][y]) {
                    distTo[x][y] = distTo[x - 1][y - 1] + energy;
                    edgeTo[x][y] = x - 1;
                }
            }
            if (distTo[x][y - 1] + energy < distTo[x][y]) {
                distTo[x][y] = distTo[x][y - 1] + energy;
                edgeTo[x][y] = x;
            }
            //如果有右上元素
            if (x < width() - 1) {
                if (distTo[x + 1][y - 1] + energy < distTo[x][y]) {
                    distTo[x][y] = distTo[x + 1][y - 1] + energy;
                }
            }
        }
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        int width = width();
        int height = height();

        int[] seam = new int[width];

        double[][] distTo = new double[width][height];
        int[][] edgeTo = new int[width][height];

        //init
        for (int col = 1; col < width; col++) {
            for (int row = 0; row < height; row++) {
                distTo[col][row] = Double.POSITIVE_INFINITY;
            }
        }
        for (int row = 0; row < height; row++) {
            distTo[0][row] = 1000;
        }

        //handle
        for (int col = 1; col < width; col++) {
            for (int row = 0; row < height; row++) {
                relax(row, col, true, distTo, edgeTo);
            }
        }

        double minDist = Double.POSITIVE_INFINITY;
        int last = 1;
        //get the smallest position
        for (int i = 0; i < height; i++) {
            if (distTo[width - 1][i] < minDist) {
                minDist = distTo[width - 1][i];
                last = i;
            }
        }

        seam[width - 1] = last;
        for (int i = width - 2; i >= 0; i--) {
            seam[i] = edgeTo[i + 1][seam[i + 1]];
        }

        return seam;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int[] seam = new int[height()];

        return seam;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (height() <= 1) throw new IllegalArgumentException();
        if (seam == null || seam.length != width()) throw new IllegalArgumentException();

        for (int i = 0; i < height(); i++) {

        }

        //copy and paste

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (width() <= 1) throw new IllegalArgumentException();
        if (seam == null || seam.length != height()) throw new IllegalArgumentException();

        //copy and paste

    }

    //  unit testing (optional)
    public static void main(String[] args) {

    }
}
