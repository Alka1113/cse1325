package generics;
public class Quadratic implements Comparable<Quadratic> {
    public static double x;
    private double a, b, c;
    
    public Quadratic(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public double f() {
        return a * x * x + b * x + c;
    }
    
    @Override
    public int compareTo(Quadratic q) { 
        double classValue = this.f();
        double outerValue = q.f();
        
        if (classValue > outerValue) {
            return 1;
        } else if (classValue < outerValue) {
            return -1;
        } else {return 0;}
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(a + "x^2 " + b + "x " + c + " = " + f());
        return sb.toString();
    }
}