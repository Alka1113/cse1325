package people;

import session.Course;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Student extends Person {
    private int studentID;
    private static int nextStudentID = 0;
    private ArrayList<Course> courses;
    
    public Student(String name, String email) {
        super(name, email);
        this.studentID = nextStudentID++;
        this.courses = new ArrayList<>();
    }
    

    public Student(Scanner in) {
        super(in); 
        this.studentID = in.nextInt();
        in.nextLine(); 
        
        this.courses = new ArrayList<>();
        int size = in.nextInt();
        in.nextLine(); 
        for (int i = 0; i < size; i++) {
            courses.add(new Course(in));
        }
        
        if (studentID >= nextStudentID) {
            nextStudentID = studentID + 1;
        }
    }
    

    public void save(PrintStream out) {
        super.save(out); 
        out.println(studentID);
        out.println(courses.size());
        for (Course course : courses) {
            course.save(out);
        }
    }
    
    public void addCourse(Course course) {
        courses.add(course);
    }
    
    public Course[] getCourses() {
        return courses.toArray(new Course[0]);
    }
    
    public int getStudentID() {
        return studentID;
    }
    
    @Override
    public String toString() {
        String superString = super.toString();
        return superString.replace(")", ", #" + studentID + ")");
    }
}