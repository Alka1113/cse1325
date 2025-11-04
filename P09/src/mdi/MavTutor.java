package mdi;

import menu.Menu;
import menu.MenuItem;
import people.Student;
import people.Tutor;
import session.Course;
import session.Session;
import session.InvalidCourseException;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.IOException;

import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class MavTutor {
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Tutor> tutors = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();
    private List<?> view = courses;
    private Menu menu;

    
    private File file = null;
    public MavTutor() {
        initializeMenu();
    }
    
    private void initializeMenu() {
        // Create menu with pre and post objects
        String clearScreen = "\n".repeat(50);
        String title = "MavTutor Main Menu\n" + "=".repeat(18) + "\n";
        
        menu = new Menu(
            new Object[] {clearScreen, title},  // pre - clear screen and title
            new Object[] {this, "\nSelection? "}, // post - current view and prompt
            new MenuItem("Create Course", this::newCourse),
            new MenuItem("Create Student", this::newStudent),
            new MenuItem("Create Tutor", this::newTutor),
            new MenuItem("Create Session", this::newSession),
            new MenuItem("View Courses", () -> selectView(courses)),
            new MenuItem("View Students", () -> selectView(students)),
            new MenuItem("View Tutors", () -> selectView(tutors)),
            new MenuItem("View Sessions", () -> selectView(sessions)),
            new MenuItem("Quit", this::quit)
            new MenuItem("New", this::newz),
            new MenuItem("Save As", this::saveAs), 
            new MenuItem("Save", this::save),
            new MenuItem("Open", this::open)
        );
    }
    private void newz() {}
    private void saveAs() {} 
    private void save() {}
    private void open() {}

    public void newCourse() {
        try {
            String dept = Menu.getString("Enter department (3-4 letters): ");
            int number = Menu.getInt("Enter course number (1000-9999): ");
            
            Course course = new Course(dept, number);
            if (!courses.contains(course)) {
                courses.add(course);
                menu.result = new StringBuilder("Course created: " + course);
            } else {
                menu.result = new StringBuilder("Course already exists: " + course);
            }
        } catch (InvalidCourseException e) {
            menu.result = new StringBuilder("Error: " + e.getMessage());
        } catch (Exception e) {
            menu.result = new StringBuilder("Error creating course: " + e.getMessage());
        }
    }
    
    public void newStudent() {
        if (courses.isEmpty()) {
            menu.result = new StringBuilder("Error: No courses available. Create courses first.");
            return;
        }
        
        try {
            String name = Menu.getString("Enter student name: ");
            String email = Menu.getString("Enter student email: ");
            
            Student student = new Student(name, email);
            
            while (true) {
                Integer courseIndex = Menu.selectItemFromList("Select course for student (blank to finish): ", courses);
                if (courseIndex == null) break;
                Course course = courses.get(courseIndex);
                student.addCourse(course);
                menu.result = new StringBuilder("Added course: " + course);
            }
            
            students.add(student);
            menu.result = new StringBuilder("Student created: " + student);
        } catch (Exception e) {
            menu.result = new StringBuilder("Error creating student: " + e.getMessage());
        }
    }
    
    public void newTutor() {
        if (courses.isEmpty()) {
            menu.result = new StringBuilder("Error: No courses available. Create courses first.");
            return;
        }
        
        try {
            String name = Menu.getString("Enter tutor name: ");
            String email = Menu.getString("Enter tutor email: ");
            String bio = Menu.getString("Enter tutor bio: ");
            int ssn = Menu.getInt("Enter tutor SSN: ");
            
            Integer courseIndex = Menu.selectItemFromList("Select course for tutor: ", courses);
            if (courseIndex == null) {
                menu.result = new StringBuilder("Error: No course selected.");
                return;
            }
            Course course = courses.get(courseIndex);
            
            Tutor tutor = new Tutor(name, email, ssn, bio, course);
            tutors.add(tutor);
            menu.result = new StringBuilder("Tutor created: " + tutor);
        } catch (Exception e) {
            menu.result = new StringBuilder("Error creating tutor: " + e.getMessage());
        }
    }
    
    public void newSession() {
        if (courses.isEmpty() || tutors.isEmpty() || students.isEmpty()) {
            menu.result = new StringBuilder("Error: Need courses, tutors, and students to create session.");
            return;
        }
        
        try {
            Integer courseIndex = Menu.selectItemFromList("Select course for session: ", courses);
            if (courseIndex == null) {
                menu.result = new StringBuilder("Error: No course selected.");
                return;
            }
            Course course = courses.get(courseIndex);
            
            Integer tutorIndex = Menu.selectItemFromList("Select tutor for session: ", tutors);
            if (tutorIndex == null) {
                menu.result = new StringBuilder("Error: No tutor selected.");
                return;
            }
            Tutor tutor = tutors.get(tutorIndex);
            
            Session session = new Session(course, tutor);
            
            String date = Menu.getString("Enter date (YYYYMMDD): ");
            String startTime = Menu.getString("Enter start time (HH:MM): ");
            int duration = Menu.getInt("Enter duration in minutes: ");
            session.setSchedule(date, startTime, duration);
            
            while (true) {
                Integer studentIndex = Menu.selectItemFromList("Select student for session (blank to finish): ", students);
                if (studentIndex == null) break;
                Student student = students.get(studentIndex);
                session.addStudent(student);
                menu.result = new StringBuilder("Added student: " + student.getName());
            }
            
            sessions.add(session);
            menu.result = new StringBuilder("Session created: " + session);
        } catch (Exception e) {
            menu.result = new StringBuilder("Error creating session: " + e.getMessage());
        }
    }
    
    public void selectView(List<?> list) {
        this.view = list;
        menu.result = new StringBuilder("View updated");
    }
    
    public void quit() {
        menu.result = null; // Signals menu to exit
    }
    
    @Override
    public String toString() {
        if (view == courses) {
            return Menu.listToString("Courses", courses, '•');
        } else if (view == students) {
            return Menu.listToString("Students", students, '•');
        } else if (view == tutors) {
            return Menu.listToString("Tutors", tutors, '•');
        } else if (view == sessions) {
            return Menu.listToString("Sessions", sessions, '•');
        } else {
            return "No view selected";
        }
    }
    
    private static void showSplashScreen() {
        String splash = 
            "╔════════════════════════════════════════╗\n" +
            "║           WELCOME TO MAVTUTOR          ║\n" +
            "║                                        ║\n" +
            "║         ███╗   ███╗ █████╗ ██╗        ║\n" +
            "║         ████╗ ████║██╔══██╗██║        ║\n" +
            "║         ██╔████╔██║███████║██║        ║\n" +
            "║         ██║╚██╔╝██║██╔══██║██║        ║\n" +
            "║         ██║ ╚═╝ ██║██║  ██║███████╗   ║\n" +
            "║         ╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝   ║\n" +
            "║                                        ║\n" +
            "║      Tutoring Management System        ║\n" +
            "║                                        ║\n" +
            "╚════════════════════════════════════════╝\n" +
            "           Loading... Please wait";
        
        System.out.println(splash);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args) {
        boolean showSplash = true;
        for (String arg : args) {
            if ("-nosplash".equals(arg)) {
                showSplash = false;
                break;
            }
        }
        
        if (showSplash) {
            showSplashScreen();
        }
        
        MavTutor mavTutor = new MavTutor();
        mavTutor.menu.run();
    }
}