import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final List<LineSegment> lineSegments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        checkAndSort(points);

        for (int a = 0; a < points.length; a++) {
            for (int b = a + 1; b < points.length; b++) {
                for (int c = b + 1; c < points.length; c++) {
                    for (int d = c + 1; d < points.length; d++) {
                        double slope = points[a].slopeTo(points[b]);
                        if (slope == points[b].slopeTo(points[c]) && slope == points[c].slopeTo(points[d])) {
                            lineSegments.add(new LineSegment(points[a], points[d]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        // the line segments
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
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