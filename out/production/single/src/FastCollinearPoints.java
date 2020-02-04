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
        Point[] tempPoints = new Point[size];
        for (int i = 0; i < size; i++) {
            tempPoints[i] = points[i];
        }
        Arrays.sort(tempPoints);
        for (int i = 1; i < size; i++) {
            if (tempPoints[i - 1].compareTo(tempPoints[i]) == 0) throw new IllegalArgumentException();
        }

        lineSegments = new ArrayList<>();

        //当前的参考点
        Point currentCheck;
        //对照点重新存储,不包括参考点,共N-1个
        Point[] otherPoints = new Point[size-1];
        //开始比较斜率,一个一个来
        for (int i=0;i<size;i++) {
            currentCheck = points[i];
            // copy points without Point currentCheck to otherPoints
            for(int j=0;j<size;j++) {
                if(j<i) otherPoints[j] = points[j];
                if(j>i) otherPoints[j-1] = points[j];
            }

            //根据斜率对点排序
            //用的是结合了归并和插入的tim排序,稳定排序
            Arrays.sort(otherPoints,currentCheck.slopeOrder());
            //遍历已经排序的otherPoints找线段
            //注意,归并和插入排序都是稳定的,所以tim排序是稳定的,这非常重要
            //配合Point的compareTo方法,可以直接过滤掉重复线段
            //一开始没太注意compareTo方法,后来发现这个方法能固定住点之间的相对位置,所以可以过滤重复线段
            //两点共线
            int count=2;
            for(int k=1;k<size-1;k++) {
                double k1 = otherPoints[k-1].slopeTo(currentCheck);
                double k2 = otherPoints[k].slopeTo(currentCheck);
                if(k1==k2) {
                    count++;
                    //当循环到最后一个点,同时这个点和前面的点共线
                    if(k==size-2) {
                        //如果4点及以上共线,并且otherPoints中与参考点共线且排在最左边的点比参考点大的话,注意此处是遍历到头,所以索引是k-count+2
                        if(count>=4 && currentCheck.compareTo(otherPoints[k-count+2])==-1) {
                            //线段起点
                            Point start = currentCheck;
                            //线段终点
                            Point end = otherPoints[k];
                            lineSegments.add(new LineSegment(start,end));
                        }
                    }
                }
                else{
                    //如果4点及以上共线,并且otherPoints中与参考点共线且排在最左边的点比参考点大的话,索引是k-count+1
                    if(count>=4 && currentCheck.compareTo(otherPoints[k-count+1])==-1) {
                        Point start = currentCheck;
                        Point end = otherPoints[k-1];
                        lineSegments.add(new LineSegment(start,end));
                    }
                    count=2;
                }
            }
        }



//        int numOfSameSlope=0;
//        Double tempSlope;
//        Double slope;
//        //fixme: to jump the same
//
//        for(Point thisPoint:points){
//            Arrays.sort(tempPoints,thisPoint.slopeOrder());
//
//
//            tempSlope=thisPoint.slopeTo(tempPoints[1]);
//            System.out.println("tempSlope: "+ tempSlope);
//            numOfSameSlope=1;
//
//            for (int i=2;i<size;i++){
//                if(thisPoint.compareTo(tempPoints[i])<0){
//                    slope=thisPoint.slopeTo(tempPoints[i]);
//                    if(tempSlope.equals(slope)){
//                        numOfSameSlope++;
//                        System.out.println("numOfSameSlope++, now is "+numOfSameSlope);
//                    }
//                    else {
//                        if(numOfSameSlope>=3){
//                            System.out.println("put in segments");
//                            lineSegments.add(new LineSegment(thisPoint,tempPoints[i-1]));
//                        }
//                        System.out.println("slope is not the same");
//                        tempSlope=slope;
//                        numOfSameSlope=1;
//                    }
//                }
//
//                if(numOfSameSlope>=3){
//                    System.out.println("put in segments");
//                    lineSegments.add(new LineSegment(thisPoint,tempPoints[i-1]));
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
//
//        Point a = new Point(1, 2);
//        Point b = new Point(2, 4);
//        Point c = new Point(4, 8);
//        Point d = new Point(8, 16);
//
//        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(new Point[]{p, q, r, s, a, b, c, d});
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(new Point[]{p, q, r, s});
        System.out.println(fastCollinearPoints.numberOfSegments());
        for (LineSegment lineSegment:fastCollinearPoints.lineSegments){
            System.out.println(lineSegment);
        }
    }
}