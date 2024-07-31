import java.awt.Point;

class LineSegment {
    Point start;
    Point end;

    public LineSegment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "LineSegment{" + "start=" + start + ", end=" + end + '}';
    }
}