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

    public void add(T x){ }
    
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