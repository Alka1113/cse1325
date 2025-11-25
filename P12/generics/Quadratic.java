package generics;

public class Quadratic {
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
    public int compareTo(q Quadratic){
        
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(a + "x^2 " + b + "x " + c + " = " + f());
        return sb.toString();
    }
}