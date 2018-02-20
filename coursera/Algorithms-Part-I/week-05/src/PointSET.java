import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {

    private final SET<Point2D> pointsSet;

    public PointSET() {
        // construct an empty set of points 
        pointsSet = new SET<>();
    }

    public boolean isEmpty() {
        // is the set empty? 
        return pointsSet.isEmpty();
    }

    public int size() {
        // number of points in the set 
        return pointsSet.size();
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (p == null) {
            throw new IllegalArgumentException();
        }
        pointsSet.add(p);
    }

    public boolean contains(Point2D p) {
        // does the set contain point p? 
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return pointsSet.contains(p);
    }

    public void draw() {
        // draw all points to standard draw 
        for (Point2D point : pointsSet) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle (or on the boundary) 
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        List<Point2D> point2ds = new ArrayList<>();
        if (!isEmpty()) {
            for (Point2D point : pointsSet) {
                if (rect.contains(point)) {
                    point2ds.add(point);
                }
            }
        }
        return point2ds;
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }
        double distance = Double.POSITIVE_INFINITY;
        Point2D result = null;
        for (Point2D point : pointsSet) {
            if (p.distanceSquaredTo(point) < distance) {
                distance = p.distanceSquaredTo(point);
                result = point;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional) 
    }
}