import java.util.Scanner;
public class Hello{
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter your name");
        String name=s.nextLine();
        System.out.println("Hello, "+name);
    }
}