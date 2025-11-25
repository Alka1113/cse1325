package generics;
import java.util.Random;

public class DemoTop3 {
    public static void main(String[] args) {
        Random random = new Random();
        
        System.out.println("For the following values:");
        Top3<Double> doubleTop3 = new Top3<>();
        
        for (int i = 0; i < 15; i++) {
            double value = random.nextDouble();
            System.out.printf("%.4f ", value);
            if ((i + 1) % 5 == 0) System.out.println();
            doubleTop3.add(value);
        }
        
        System.out.println("\nthe top 3 values are:");
        System.out.print(doubleTop3);
        
        Quadratic.x = 5.0 + random.nextDouble();
        System.out.println("\nFor x=" + String.format("%.4f", Quadratic.x) + " and the following equations:");
        
        Top3<Quadratic> quadTop3 = new Top3<>();
        
        for (int i = 0; i < 8; i++) {
            double a = random.nextInt(4) + 1;
            double b = random.nextInt(7) - 3;
            double c = random.nextInt(21) - 10;
            
            Quadratic quad = new Quadratic(a, b, c);
            System.out.println(quad);
            quadTop3.add(quad);
        }
        
        System.out.println("\nThe top 3 values are:");
        System.out.print(quadTop3);
    }
}
