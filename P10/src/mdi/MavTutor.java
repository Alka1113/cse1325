package mdi;

import menu.Menu;
import menu.MenuItem;
import people.Student;
import people.Tutor;
import rating.Rateable;
import session.Course;
import session.Session;
import session.InvalidCourseException;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MavTutor {
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Tutor> tutors = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();
    private List<?> view = courses;
    private Menu menu;
    private File file = null;
    private boolean dirty = false;
    
    public MavTutor() {
        initializeMenu();
    }
    
    private void initializeMenu() {
        String clearScreen = "\n".repeat(50);
        String title = "MavTutor Main Menu\n" + "=".repeat(18) + "\n";
        
        menu = new Menu(
          new Object[] {clearScreen, title},  
            new Object[] {this, "\nSelection? "}, 
            new MenuItem("Create Course", this::newCourse),
            new MenuItem("Create Student", this::newStudent),
            new MenuItem("Create Tutor", this::newTutor),
            new MenuItem("Create Session", this::newSession),
            new MenuItem("View Courses", () -> selectView(courses)),
            new MenuItem("View Students", () -> selectView(students)),
            new MenuItem("View Tutors", () -> selectView(tutors)),
            new MenuItem("View Sessions", () -> selectView(sessions)),
            new MenuItem("Review Students", () -> review(students)),
            new MenuItem("Review Tutors", () -> review(tutors)),
            new MenuItem("Review Sessions", () -> review(sessions)),
            new MenuItem("New", this::newz),
            new MenuItem("Save As", this::saveAs),
            new MenuItem("Save", this::save),
            new MenuItem("Open", this::open),
            new MenuItem("Quit", this::quit)
        );
    }
    
     // Generic review method for P10
    private void review(List<? extends Rateable> list) {
        if (list.isEmpty()) {
            System.out.println("No items available to review.");
            return;
        }

        // Select item from list
        Rateable selected = Menu.selectItemFromList("Select item to review:", list);
        if (selected == null) return;
        
    }

    private void newz() {
        if (!safeToDiscardData()) {
            menu.result = new StringBuilder("Operation cancelled.");
            return;
        }
        courses.clear();
        students.clear();
        tutors.clear();
        sessions.clear();
        file = null;
        dirty = false;
        menu.result = new StringBuilder("All data cleared.");
    }
    
    private void saveAs() {
        file = null;
        save();
    }
    
    private void save() {
        try {
            if (file == null) {
                String filename = Menu.getString("Enter filename to save: ");
                file = new File(filename);
            }
            
            if (file != null) {
                try (PrintStream out = new PrintStream(file)) {
                    out.println(courses.size());
                    for (Course course : courses) {
                        course.save(out);
                    }
                    
                    out.println(students.size());
                    for (Student student : students) {
                        student.save(out);
                    }
                    
                    out.println(tutors.size());
                    for (Tutor tutor : tutors) {
                        tutor.save(out);
                    }
                    
                    out.println(sessions.size());
                    for (Session session : sessions) {
                        session.save(out);
                    }
                }
                dirty = false;
                menu.result = new StringBuilder("Data saved to " + file.getName());
            }
        } catch (IOException e) {
            menu.result = new StringBuilder("Error saving file: " + e.getMessage());
        }
    }
    
    private void open() {
        if (!safeToDiscardData()) {
            menu.result = new StringBuilder("Operation cancelled.");
            return;
        }
        
        try {
            String filename = Menu.getString("Enter filename to open: ");
            File openFile = new File(filename);
            
            if (!openFile.exists()) {
                menu.result = new StringBuilder("File not found: " + filename);
                return;
            }
            
            try (Scanner in = new Scanner(openFile)) {
                courses.clear();
                students.clear();
                tutors.clear();
                sessions.clear();
                file = openFile;
                
                int size = in.nextInt();
                in.nextLine();
                for (int i = 0; i < size; i++) {
                    courses.add(new Course(in));
                }
                
                size = in.nextInt();
                in.nextLine();
                for (int i = 0; i < size; i++) {
                    students.add(new Student(in));
                }
                
                size = in.nextInt();
                in.nextLine();
                for (int i = 0; i < size; i++) {
                    tutors.add(new Tutor(in));
                }
                
                size = in.nextInt();
                in.nextLine();
                for (int i = 0; i < size; i++) {
                    sessions.add(new Session(in));
                }
            }
            dirty = false;
            menu.result = new StringBuilder("Data loaded from " + openFile.getName());
        } catch (IOException e) {
            menu.result = new StringBuilder("Error loading file: " + e.getMessage());
            newz();
        } catch (Exception e) {
            menu.result = new StringBuilder("Error parsing file: " + e.getMessage());
            newz();
        }
    }
    
    private boolean safeToDiscardData() {
        if (!dirty) return true;
        
        while (true) {
            String response = Menu.getString("Unsaved data exists. (S)ave, (D)iscard, or (A)bort? ");
            if (response.equalsIgnoreCase("S")) {
                save();
                return !dirty;
            } else if (response.equalsIgnoreCase("D")) {
                dirty = false;
                return true;
            } else if (response.equalsIgnoreCase("A")) {
                return false;
            } else {
                menu.result = new StringBuilder("Please enter S, D, or A");
            }
        }
    }
    
    public void newCourse() {
        try {
            String dept = Menu.getString("Enter department (3-4 letters): ");
            int number = Menu.getInt("Enter course number (1000-9999): ");
            
            Course course = new Course(dept, number);
            if (!courses.contains(course)) {
                courses.add(course);
                dirty = true;
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
            dirty = true;
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
            dirty = true;
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
            dirty = true;
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
        if (!safeToDiscardData()) {
            menu.result = new StringBuilder("Quit cancelled.");
            return;
        }
        menu.result = null;
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