package session;

import people.Tutor;
import people.Student;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Session {
    private Course course;
    private Tutor tutor;
    private List<Student> students;
    private DateRange schedule;

    public Session(Course course, Tutor tutor) {
        if (course == null || tutor == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.course = course;
        this.tutor = tutor;
        this.students = new ArrayList<>();
    }
    

    public Session(Scanner in) {
        this.course = new Course(in);
        this.schedule = new DateRange(in);
        this.tutor = new Tutor(in);
        this.students = new ArrayList<>();
        int size = in.nextInt();
        in.nextLine(); 
        for (int i = 0; i < size; i++) {
            students.add(new Student(in));
        }
    }
    
    public void save(PrintStream out) {
        course.save(out);
        schedule.save(out);
        tutor.save(out);
        out.println(students.size());
        for (Student student : students) {
            student.save(out);
        }
    }

    public void setSchedule(String date, String startTime, String endTime) {
        this.schedule = new DateRange(date, startTime, endTime);
    }

    public void setSchedule(String date, String startTime, int durationMinutes) {
        this.schedule = new DateRange(date, startTime, durationMinutes);
    }
    
    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Session on ");
        sb.append(course).append(" at ").append(schedule);
        sb.append("\n");
        sb.append("  Tutor: ").append(tutor);
        sb.append("\n");
        sb.append("Students: ");
        if (students.isEmpty()) {
            sb.append("No students enrolled");
        } else {
            for (int i = 0; i < students.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(students.get(i).getName());
            }
        }
        return sb.toString();
    }
    
    public Course getCourse() { return course; }
    public Tutor getTutor() { return tutor; }
    public List<Student> getStudents() { return students; }
    public DateRange getSchedule() { return schedule; }
}