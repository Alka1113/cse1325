package session;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Objects;

public class Course {
    private String dept;
    private int number;
    
    public Course(String dept, int number) {
        // Validation of department length
        if (dept.length() < 3 || dept.length() > 4) {
            throw new InvalidCourseException(dept);
        }
        // Validation of course number range
        if (number < 1000 || number > 9999) {
            throw new InvalidCourseException(dept, number);
        }
        this.dept = dept;
        this.number = number;
    }
    
    // Restore constructor
    public Course(Scanner in) {
        this.dept = in.nextLine();
        this.number = in.nextInt();
        in.nextLine(); 
    }
    
    // Save method
    public void save(PrintStream out) {
        out.println(dept);
        out.println(number);
    }
    
    @Override
    public String toString() {
        return dept + number;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return number == course.number && Objects.equals(dept, course.dept);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(dept, number);
    }
    
    // Getters for dept and number
    public String getDept() {
        return dept;
    }
    
    public int getNumber() {
        return number;
    }
}