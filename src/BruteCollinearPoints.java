import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points
    private Point[] points;
    private LineSegment[] segments;
    int numberOfSegments;
    public BruteCollinearPoints(Point[] points){
        //todo
        this.points=points;
        Arrays.sort(points);//ensure p<=q<=r<=s

        Point p=points[0];
        Point q=points[1];
        Point r=points[2];
        Point s=points[3];

        double slope1=p.slopeTo(q);
        double slope2=p.slopeTo(r);
        double slope3=p.slopeTo(s);




    }

    // the number of line segments
    public int numberOfSegments(){
        //todo
//        return 0;
        return segments.length;
    }


    // the line segments
    public LineSegment[] segments(){
        //todo
        return null;
    }
}