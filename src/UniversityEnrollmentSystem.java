import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UniversityEnrollmentSystem {

    private static final Map<String, Course> courses = new HashMap<>();
    private static final Map<String, Boolean> studentCourses = new HashMap<>();

    public static void main(String[] args) {
        courses.put("Advanced Java", new Course("Advanced Java", 2, "Core Java"));
        courses.put("Core Java", new Course("Core Java", 3, null));

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the course you want to enroll in: ");
        String courseName = scanner.nextLine();

        System.out.print("Enter the courses you've completed (comma separated): ");
        String completedCoursesInput = scanner.nextLine();
        for (String completedCourse : completedCoursesInput.split(",")) {
            studentCourses.put(completedCourse.trim(), true);
        }

        try {
            enrollInCourse(courseName);
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static void enrollInCourse(String courseName) throws CourseFullException, PrerequisiteNotMetException {
        Course course = courses.get(courseName);

        if (course == null) {
            System.out.println("Error: Course does not exist.");
            return;
        }

        if (course.getPrerequisite() != null && !studentCourses.getOrDefault(course.getPrerequisite(), false)) {
            throw new PrerequisiteNotMetException("Complete " + course.getPrerequisite() + " before enrolling in " + courseName + ".");
        }

        if (course.getCapacity() <= 0) {
            throw new CourseFullException("The course " + courseName + " is full.");
        }

        course.decreaseCapacity();
        System.out.println("Successfully enrolled in " + courseName + ". Remaining slots: " + course.getCapacity());
    }

    static class Course {
        private String name;
        private int capacity;
        private String prerequisite;

        public Course(String name, int capacity, String prerequisite) {
            this.name = name;
            this.capacity = capacity;
            this.prerequisite = prerequisite;
        }

        public String getPrerequisite() {
            return prerequisite;
        }

        public int getCapacity() {
            return capacity;
        }

        public void decreaseCapacity() {
            if (capacity > 0) {
                capacity--;
            }
        }
    }

    static class CourseFullException extends Exception {
        public CourseFullException(String message) {
            super(message);
        }
    }
    static class PrerequisiteNotMetException extends Exception {
        public PrerequisiteNotMetException(String message) {
            super(message);
        }
    }
}
