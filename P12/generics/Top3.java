package generics;
public class Top3<T>{
    private T first;
    private T second;
    private T third;
    
    public Top3() {
        first = null;
        second = null;
        third = null;
    }

    public void add(T x){
        if (first == null) {
            first = x;
        } else if (isGreater(x, first)) {
            third = second;
            second = first;
            first = x;
        } else if (second == null || isGreater(x, second)) {
            third = second; 
            second = x; 
        } else if (third == null || isGreater(x, third)) {
            third = x;
        }
    }
    
    private boolean isGreater(T a, T b) {
        if (a instanceof Double && b instanceof Double) {
            return (Double)a > (Double)b;
        }
        if (a instanceof Integer && b instanceof Integer) {
            return (Integer)a > (Integer)b;
        }
        if (a instanceof Quadratic && b instanceof Quadratic) {
            return ((Quadratic)a).f() > ((Quadratic)b).f();
        }
        return false;
    }
    public T getFirst() { return first; }
    public T getSecond() { return second; }
    public T getThird() { return third; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(first).append("\n");
        sb.append(second).append("\n");
        sb.append(third).append("\n");
        return sb.toString();
    }
}