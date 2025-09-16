public class TestPolygon {
    public static void main(String[] args) {
        boolean error = false; // Flag to track if any test fails
        Polygon p1 = new Polygon();
        p1.addPoint(new Point());
        p1.addPoint(new Point(3, 0));
        p1.addPoint(new Point(3, 4));
        try {
            double peri = p1.perimeter();
            if (Math.abs(peri - 12.0) > 0.000001) { 
                System.err.println("FAIL Test #1: Expected perimeter 12.0, got " + peri);
                error = true;
            }
        } catch (Exception e) {
            System.err.println("FAIL Test #1: Exception from valid polygon: " + e.getMessage());
            error = true;
        }

        // Duplicate point should throw IllegalArgumentException
        Polygon p2 = new Polygon();
        p2.addPoint(new Point(0, 0));
        p2.addPoint(new Point(3, 0));
        try {
            p2.addPoint(new Point(0, 0)); 
            System.err.println("FAIL Test #2: Duplicate point should throw IllegalArgumentException");
            error = true;
        } catch (IllegalArgumentException e) {
            // This is what we expect! Test passes. Do nothing.
            System.out.println("PASS Test #2: Correctly caught " + e.getMessage());
        } catch (Exception e) {
            System.err.println("FAIL Test #2: Wrong exception thrown: " + e.toString());
            error = true;
        }

        // Perimeter of 2-sided polygon should throw RuntimeException
        Polygon p3 = new Polygon();
        p3.addPoint(new Point(0, 0));
        p3.addPoint(new Point(3, 0));
        try {
            p3.perimeter(); 
            System.err.println("FAIL Test #3: Perimeter on 2 sides should throw RuntimeException");
            error = true;
        } catch (RuntimeException e) {
            System.out.println("PASS Test #3: Correctly caught " + e.getMessage());
        } catch (Exception e) {
            System.err.println("FAIL Test #3: Wrong exception thrown: " + e.toString());
            error = true;
        }

        // Adding a 13th point should throw RuntimeException
        Polygon p4 = new Polygon();
        try {
            // Adding 12 points
            for (int i = 0; i < Polygon.MAX_SIDES; i++) {
                p4.addPoint(new Point(i, i));
            }
            p4.addPoint(new Point(13, 13)); 
            System.err.println("FAIL Test #4: Adding 13th point should throw RuntimeException");
            error = true;
        } catch (RuntimeException e) {
            System.out.println("PASS Test #4: Correctly caught " + e.getMessage());
        } catch (Exception e) {
            System.err.println("FAIL Test #4: Wrong exception thrown: " + e.toString());
            error = true;
        }

        // Final result 
        if (error) {
            System.err.println("FAIL: Some tests failed. Result code 1");
            System.exit(1);
        } else {
            System.out.println("All tests passed!");
        }
    }
}