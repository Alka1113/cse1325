package people;

import session.Course;
import java.io.PrintStream;
import java.util.Scanner;

public class Tutor extends Person {
    private int ssn;
    private Course course;
    private String bio;
    
    // name, email, ssn, bio, course
    public Tutor(String name, String email, int ssn, String bio, Course course) {
        super(name, email);
        // Validating SSN range
        if (ssn < 001_01_0001 || ssn > 999_99_9999) {
            throw new IllegalArgumentException("Invalid SSN: " + ssn);
        }
        this.ssn = ssn;
        this.course = course;
        this.bio = bio;
    }

    public Tutor(Scanner in) {
        super(in); 
        this.ssn = in.nextInt();
        in.nextLine(); 
        this.bio = in.nextLine();
        this.course = new Course(in);
    }
    
    public void save(PrintStream out) {
        super.save(out); 
        out.println(ssn);
        out.println(bio);
        course.save(out);
    }
    
    public int getSSN() {
        return ssn;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public String getBio() {
        return bio;
    }
}