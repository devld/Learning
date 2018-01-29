import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {

    // store max point in a line segment
    private final List<Point[]> found = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        checkAndSort(points);
        // create an auxiliary array
        Point[] aux = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length; i++) {
            // base point
            Point base = points[i];
            // sorting these points by base.slopeOrder()
            Arrays.sort(aux, 0, aux.length, base.slopeOrder());

            // collinear points list
            List<Point> collPoints = new ArrayList<>();
            double slope = Double.NEGATIVE_INFINITY;

            for (int j = 1; j < aux.length; j++) {
                if (collPoints.isEmpty()) {
                    slope = base.slopeTo(aux[j]);
                    collPoints.add(aux[j]);
                } else {
                    if (slope == base.slopeTo(aux[j])) {
                        collPoints.add(aux[j]);
                    } else {
                        if (collPoints.size() >= 3) {
                            collPoints.add(base);
                            addToLineSegments(collPoints);
                        }
                        collPoints.clear();
                        // add the current different point to the list
                        collPoints.add(aux[j]);
                        slope = base.slopeTo(aux[j]);
                    }
                }
            }
            if (collPoints.size() >= 3) {
                // handle these points when jump out
                collPoints.add(base);
                addToLineSegments(collPoints);
            }
        }
    }

    /**
     * handle collinear points list, add different start and end points to line segment
     * 
     * @param points collinear points list
     */

    private void addToLineSegments(List<Point> points) {
        // same end point after sorting
        Collections.sort(points);
        Point start = points.get(0);
        Point end = points.get(points.size() - 1);

        for (Point[] p : found) {
            if (p[0].compareTo(start) == 0 && p[1].compareTo(end) == 0) {
                return;
            }
        }

        found.add(new Point[] { start, end });

    }

    public int numberOfSegments() {
        // the number of line segments
        return found.size();
    }

    public LineSegment[] segments() {
        // the line segments
        LineSegment[] lineSegments = new LineSegment[numberOfSegments()];
        int i = 0;
        for (Point[] p : found) {
            lineSegments[i++] = new LineSegment(p[0], p[1]);
        }
        return lineSegments;
    }

    private void checkAndSort(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i - 1].compareTo(points[i]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

}