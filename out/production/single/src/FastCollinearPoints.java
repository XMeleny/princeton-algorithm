import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException();

        for (Point point : points) {
            if (point == null) throw new IllegalArgumentException();
        }

        //check repeated points
        //copy: the system will check if arg points are mutated.
        //sort: makes comparing and making segment easier
        int size = points.length;
        Point[] orderPoints = new Point[size];
        for (int i = 0; i < size; i++) {
            orderPoints[i] = points[i];
        }
        Arrays.sort(orderPoints);
        for (int i = 1; i < size; i++) {
            if (orderPoints[i - 1].compareTo(orderPoints[i]) == 0) throw new IllegalArgumentException();
        }

        lineSegments = new ArrayList<>();

        Point thisPoint;
        Point[] slopeOrderPoints = new Point[size - 1];//不包含thisPoint的点集，并且在点从小到大排序的基础上进行slope稳定排序
        for (int i = 0; i < size - 3; i++) {//后面剩余的3个点肯定不用检验了
            //init: thisPoint and slopeOrderPoints------------------------------------------------
            thisPoint = orderPoints[i];
            for (int j = 0; j < size; j++) {
                if (j < i) slopeOrderPoints[j] = orderPoints[j];
                if (j > i) slopeOrderPoints[j - 1] = orderPoints[j];
            }
            Arrays.sort(slopeOrderPoints, thisPoint.slopeOrder());
            //init done--------------------------------------------------------------------------------


            int collinearNum = 2;
            int start = 0;
            double currentSlope = thisPoint.slopeTo(slopeOrderPoints[start]);
            for (int k = 1; k < size - 1; k++) {
                double slope = thisPoint.slopeTo(slopeOrderPoints[k]);

                if (currentSlope == slope) {
                    collinearNum++;
                } else {
                    if (collinearNum >= 4) {
                        //假如thisPoint比起点小，证明thisPoint是线段的起点而不是在线段的中间，这样可以排除重复线段
                        if (thisPoint.compareTo(slopeOrderPoints[start]) < 0) {
                            lineSegments.add(new LineSegment(thisPoint, slopeOrderPoints[k - 1]));
//                            System.out.println("add a new line: "+thisPoint+" to "+slopeOrderPoints[k-1]);
                        }
                    }
                    start = k;
                    currentSlope = slope;
                    collinearNum=2;
//                    System.out.println("k slope change to :"+k+"  "+slope);
                }
            }
            if (collinearNum >= 4) {
                //假如该点比起点小，证明该点是线段的起点而不是在线段的中间，排除重复线段
                if (thisPoint.compareTo(slopeOrderPoints[start]) < 0) {
                    lineSegments.add(new LineSegment(thisPoint, slopeOrderPoints[size - 2]));
//                    System.out.println("add a new line: "+thisPoint+" to "+slopeOrderPoints[size-2]);
                }
            }

        }


//        //当前的参考点
//        Point currentCheck;
//        //对照点重新存储,不包括参考点,共N-1个
//        Point[] otherPoints = new Point[size-1];
//        //开始比较斜率,一个一个来
//        for (int i=0;i<size;i++) {
//            currentCheck = tempPoints[i];
//            // copy points without Point currentCheck to otherPoints
//            for(int j=0;j<size;j++) {
//                if(j<i) otherPoints[j] = tempPoints[j];
//                if(j>i) otherPoints[j-1] = tempPoints[j];
//            }
//
//            //根据斜率对点排序
//            //用的是结合了归并和插入的tim排序,稳定排序
//            Arrays.sort(otherPoints,currentCheck.slopeOrder());
//            //遍历已经排序的otherPoints找线段
//            //注意,归并和插入排序都是稳定的,所以tim排序是稳定的,这非常重要
//            //配合Point的compareTo方法,可以直接过滤掉重复线段
//            //一开始没太注意compareTo方法,后来发现这个方法能固定住点之间的相对位置,所以可以过滤重复线段
//            //两点共线
//            int count=2;
//            for(int k=1;k<size-1;k++) {
//                double k1 = otherPoints[k-1].slopeTo(currentCheck);
//                double k2 = otherPoints[k].slopeTo(currentCheck);
//                if(k1==k2) {
//                    count++;
//                    //当循环到最后一个点,同时这个点和前面的点共线
//                    if(k==size-2) {
//                        //如果4点及以上共线,并且otherPoints中与参考点共线且排在最左边的点比参考点大的话,
//                        //注意此处是遍历到头,所以索引是k-count+2
//                        if(count>=4 && currentCheck.compareTo(otherPoints[k-count+2])==-1) {
//                            //线段起点
//                            Point start = currentCheck;
//                            //线段终点
//                            Point end = otherPoints[k];
//                            lineSegments.add(new LineSegment(start,end));
//                        }
//                    }
//                }
//                else{
//                    //如果4点及以上共线,并且otherPoints中与参考点共线且排在最左边的点比参考点大的话,索引是k-count+1
//                    if(count>=4 && currentCheck.compareTo(otherPoints[k-count+1])==-1) {
//                        Point start = currentCheck;
//                        Point end = otherPoints[k-1];
//                        lineSegments.add(new LineSegment(start,end));
//                    }
//                    count=2;
//                }
//            }
//        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);

    }

    public static void main(String[] args) {
        Point p = new Point(1, 1);
        Point q = new Point(2, 2);
        Point r = new Point(3, 3);
        Point s = new Point(4, 4);
        Point t = new Point(5, 5);
        Point y = new Point(6, 6);

//        Point[] list = new Point[]{s, r, q, p,t};
//        for (Point point : list) System.out.println(point);
////        Arrays.sort(list);
////        for(Point point:list) System.out.println(point);
//
//        Arrays.sort(list, list[1].slopeOrder());
//        for (Point point : list) System.out.println(point);

//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(0.01);
//        for (Point point : list) {
//            point.draw();
//        }
//        StdDraw.show();


////
        Point a = new Point(1, 2);
        Point b = new Point(2, 4);
        Point c = new Point(4, 8);
        Point d = new Point(8, 16);

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(new Point[]{p, q, r, s, t, y,a,b,c,d});
//        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(new Point[]{p, q, r, s, t, y});

        System.out.println(fastCollinearPoints.numberOfSegments());
        for (LineSegment lineSegment : fastCollinearPoints.lineSegments) {
            System.out.println(lineSegment);
        }
    }
}