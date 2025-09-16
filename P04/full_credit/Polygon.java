public class Polygon {
    public static final int MAX_SIDES = 12;
    private int numSides = 0;
    private Point[] points = new Point[MAX_SIDES];

    public void addPoint(Point point) {
        for (int i = 0; i < numSides; i++) {
            if (points[i].equals(point)) {
                throw new IllegalArgumentException("Duplicate point: " + point);
            }
        }
        if (numSides >= MAX_SIDES) {
            throw new RuntimeException("Polygon is full");
        }
        points[numSides] = point;
        numSides++;
    }

    public double perimeter() {
        if (numSides < 3) {
            throw new RuntimeException("Polygons require 3+ sides!");
        }
        double perimeter = 0.0;
        // Calculating distances between consecutive points
        for (int i = 0; i < numSides - 1; i++) {
            perimeter += lineLength(points[i], points[i + 1]);
        }
        // Adding the distance from the last point to the first point
        perimeter += lineLength(points[numSides - 1], points[0]);
        return perimeter;
    }

    private static double lineLength(Point p1, Point p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon[");
        for (int i = 0; i < numSides; i++) {
            sb.append(points[i]);
            if (i < numSides - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}