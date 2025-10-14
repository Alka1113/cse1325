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