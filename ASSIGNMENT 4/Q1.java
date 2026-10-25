import java.util.*;

class Course {
    String code;
    int capacity;
    HashSet<String> students;
    ArrayList<String> prerequisites;
    LinkedList<String> waitlist;

    Course(String code, int capacity, ArrayList<String> prerequisites) {
        this.code = code;
        this.capacity = capacity;
        this.students = new HashSet<>();
        this.prerequisites = new ArrayList<>(prerequisites);
        this.waitlist = new LinkedList<>();
    }

    void register(String studentId, HashMap<String, Course> courses,
                  HashSet<String> completedCourses) {
        for (String pre : prerequisites) {
            if (!completedCourses.contains(pre)) {
                System.out.println("Prerequisite not completed: " + pre);
                return;
            }
        }

        if (students.contains(studentId)) {
            System.out.println("Already registered");
            return;
        }

        if (waitlist.contains(studentId)) {
            System.out.println("Already on waitlist");
            return;
        }

        if (students.size() < capacity) {
            students.add(studentId);
            System.out.println(studentId + " registered in " + code);
        } else {
            waitlist.addLast(studentId);
            System.out.println(studentId + " added to waitlist");
        }
    }

    void drop(String studentId) {
        if (students.remove(studentId)) {
            System.out.println(studentId + " dropped " + code);

            if (!waitlist.isEmpty()) {
                String nextStudent = waitlist.removeFirst();
                students.add(nextStudent);
                System.out.println(nextStudent + " promoted from waitlist");
            }
        } else if (waitlist.remove(studentId)) {
            System.out.println(studentId + " removed from waitlist");
        } else {
            System.out.println("Student not found");
        }
    }

    void display() {
        System.out.println("Course: " + code);
        System.out.println("Enrolled students: " + students);
        System.out.println("Prerequisites: " + prerequisites);
        System.out.println("Waitlist: " + waitlist);
    }
}

public class Q1{
    public static void main(String[] args) {
        HashMap<String, Course> courses = new HashMap<>();

        courses.put("CS101",
            new Course("CS101", 2, new ArrayList<>()));

        courses.put("CS201",
            new Course("CS201", 2,
                new ArrayList<>(Arrays.asList("CS101"))));

        HashSet<String> completed = new HashSet<>();
        completed.add("CS101");

        Course c = courses.get("CS201");

        c.register("S1", courses, completed);
        c.register("S2", courses, completed);
        c.register("S3", courses, completed);

        c.display();

        c.drop("S1");
        c.display();
    }
}