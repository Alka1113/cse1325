package people;
import session.Course;
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
    
    public void addCourse(Course course) {
        courses.add(course);
    }
    
    public Course[] getCourses() {
        return courses.toArray(new Course[0]);
    }
    
    @Override
    public String toString() {
        // Replacing ")" from superclass with ", #<studentID>)"
        String superString = super.toString();
        return superString.replace(")", ", #" + studentID + ")");
    }
}