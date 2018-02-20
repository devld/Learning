import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private Node root;
    private int size;

    public KdTree() {
        // construct an empty set of points 
        size = 0;
        root = null;
    }

    public boolean isEmpty() {
        // is the set empty? 
        return size == 0;
    }

    public int size() {
        // number of points in the set 
        return size;
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (p == null) {
            throw new IllegalArgumentException();
        }
        size++;
        if (root == null) {
            root = new Node(p);
            root.rect = new RectHV(0, 0, 1, 1);
        } else {
            Node parent = null;
            Node current = root;
            int c = 0;
            boolean parentVerticalSplit = false;
            while (current != null) {
                parentVerticalSplit = !parentVerticalSplit;
                if (p.compareTo(current.p) == 0) {
                    size--;
                    return;
                }
                if (parentVerticalSplit) {
                    c = Point2D.X_ORDER.compare(p, current.p);
                } else {
                    c = Point2D.Y_ORDER.compare(p, current.p);
                }

                if (c < 0) {
                    parent = current;
                    current = current.lb;
                } else {
                    parent = current;
                    current = current.rt;
                }
            }
            if (parent != null) {
                Node node = new Node(p);
                if (c < 0) {
                    if (parentVerticalSplit) {
                        node.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(),
                                parent.rect.ymax());
                    } else {
                        node.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(),
                                parent.p.y());
                    }
                    parent.lb = node;
                } else {
                    if (parentVerticalSplit) {
                        node.rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(),
                                parent.rect.ymax());
                    } else {
                        node.rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(),
                                parent.rect.ymax());
                    }
                    parent.rt = node;
                }
            }
        }
    }

    public boolean contains(Point2D p) {
        // does the set contain point p? 
        if (p == null) {
            throw new IllegalArgumentException();
        }
        Node current = root;
        boolean vertical = true;
        int c;
        while (current != null) {
            if (p.compareTo(current.p) == 0) {
                return true;
            }
            if (vertical) {
                c = Point2D.X_ORDER.compare(p, current.p);
            } else {
                c = Point2D.Y_ORDER.compare(p, current.p);
            }
            vertical = !vertical;
            if (c < 0) {
                current = current.lb;
            } else {
                current = current.rt;
            }
        }
        return false;
    }

    public void draw() {
        // draw all points to standard draw 
        StdDraw.setPenRadius(0.001);
        drawLines(root, 0);
        StdDraw.setPenRadius(0.01);
        drawPoints(root);
    }

    private void drawPoints(Node node) {
        // StdDraw.text(node.p.x(), node.p.y(), node.p.toString());
        StdDraw.setPenColor(Color.BLACK);
        node.p.draw();
        if (node.lb != null) {
            drawPoints(node.lb);
        }
        if (node.rt != null) {
            drawPoints(node.rt);
        }
    }

    private void drawLines(Node node, int level) {
        if (level == 0) {
            StdDraw.setPenColor(Color.BLACK);
            node.rect.draw();
        }

        if (level % 2 == 0) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }

        if (node.lb != null) {
            drawLines(node.lb, level + 1);
        }
        if (node.rt != null) {
            drawLines(node.rt, level + 1);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle (or on the boundary) 
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        List<Point2D> point2ds = new ArrayList<>();
        if (!isEmpty()) {
            range(root, rect, point2ds);
        }
        return point2ds;
    }

    private void range(Node node, RectHV rect, List<Point2D> point2ds) {
        if (rect.contains(node.p)) {
            point2ds.add(node.p);
        }

        if (node.lb != null && node.lb.rect.intersects(rect)) {
            range(node.lb, rect, point2ds);
        }
        if (node.rt != null && node.rt.rect.intersects(rect)) {
            range(node.rt, rect, point2ds);
        }
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty 
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }
        return nearest(root, p, root.p, true);
    }

    private Point2D nearest(Node node, final Point2D p, Point2D best, boolean vertical) {
        if (node == null) {
            return best;
        }
        double min = best.distanceSquaredTo(p);
        if (node.p.distanceSquaredTo(p) < min) {
            best = node.p;
        }

        if (vertical) {
            if (node.p.x() <= p.x()) {
                // search right
                best = nearest(node.rt, p, best, !vertical);
                if (node.lb != null && node.lb.rect.distanceSquaredTo(p) < best.distanceSquaredTo(p)) {
                    // if the closest point in left with p is closer than the best, search left
                    best = nearest(node.lb, p, best, !vertical);
                }
            } else {
                best = nearest(node.lb, p, best, !vertical);
                if (node.rt != null && node.rt.rect.distanceSquaredTo(p) < best.distanceSquaredTo(p)) {
                    best = nearest(node.rt, p, best, !vertical);
                }
            }
        } else {
            if (node.p.y() <= p.y()) {
                best = nearest(node.rt, p, best, !vertical);
                if (node.lb != null && node.lb.rect.distanceSquaredTo(p) < best.distanceSquaredTo(p)) {
                    best = nearest(node.lb, p, best, !vertical);
                }
            } else {
                best = nearest(node.lb, p, best, !vertical);
                if (node.rt != null && node.rt.rect.distanceSquaredTo(p) < best.distanceSquaredTo(p)) {
                    best = nearest(node.rt, p, best, !vertical);
                }
            }
        }

        return best;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        genString(root, sb);
        return size() + sb.toString();
    }

    private void genString(Node node, StringBuilder sb) {
        if (node.lb != null) {
            genString(node.lb, sb);
        }
        sb.append("<" + node.p.toString() + ", " + node.rect.toString() + ">");
        sb.append(", ");
        if (node.rt != null) {
            genString(node.rt, sb);
        }
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional) 
        KdTree kdTree = new KdTree();

        In in = new In("dat/circle10.txt");

        List<Point2D> point2ds = new ArrayList<>();

        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdTree.insert(p);
            point2ds.add(p);
        }
        // RectHV rectHV = new RectHV(0.1, 0, 0.7, 0.7);
        // rectHV.draw();

        // for (Point2D p : kdTree.range(rectHV)) {
        //     StdOut.println(p);
        // }

        for (Point2D p : point2ds) {
            if (!kdTree.contains(p)) {
                throw new RuntimeException("error in contains(" + p + ")");
            }
        }

        StdDraw.setPenRadius(0.01);
        Point2D p = new Point2D(0.5, 0.7);
        StdDraw.setPenColor(Color.BLUE);
        p.draw();
        StdDraw.setPenColor(Color.ORANGE);
        kdTree.nearest(p).draw();

        // StdOut.println(kdTree.toString());
        StdDraw.enableDoubleBuffering();
        kdTree.draw();
        StdDraw.show();
    }

    private static class Node {

        private final Point2D p; // the point
        private RectHV rect; // the axis-aligned rectangle corresponding to this node
        private Node lb; // the left/bottom subtree
        private Node rt; // the right/top subtree

        public Node(Point2D p) {
            this.p = p;
        }
    }

}