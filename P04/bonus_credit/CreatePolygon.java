import java.util.Scanner;

public class CreatePolygon {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Polygon poly = new Polygon();

        System.out.println("Enter a polygon using x y points (Ctrl-d or Ctrl-z to finish):");

        try {
            while (in.hasNextDouble()) {
                double x = in.nextDouble();
                if (!in.hasNextDouble()) {
                    System.err.println("ERROR: Missing Y value for the last point.");
                    break;
                }
                double y = in.nextDouble();

                Point p = new Point(x, y);
                poly.addPoint(p);
                System.out.println("Added point " + p);
            }
            // Final result
            System.out.println(poly + " has perimeter " + poly.perimeter());

        } catch (Exception e) { // Exception catching
            System.out.println("INVALID: " + e);
        } finally {
            in.close();
        }
    }
}