import java.util.Comparator;
import java.util.TreeMap;
import java.util.ArrayList;

public class StudentRegister {
    private TreeMap<String, ArrayList<Attainment>> studentsAtt = new TreeMap<>();
    private TreeMap<String, Course> courses = new TreeMap<>();
    private ArrayList<Student> students = new ArrayList<>();

    public StudentRegister() {}

    public ArrayList<Student> getStudents() {
        ArrayList<Student> list = students;
        list.sort((a,b) -> a.getName().compareToIgnoreCase(b.getName()));
        return list;
    }

    public ArrayList<Course> getCourses() {
        ArrayList<Course> list = new ArrayList<>();
        for(String code : courses.keySet()) {
            list.add(courses.get(code));
        }
        list.sort(Comparator.comparing(Course::getName));
        return list;
    }

    public void addStudent(Student student) {
        students.add(student);
        studentsAtt.put(student.getStudentNumber(), new ArrayList<>());
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public void addAttainment(Attainment att) {
        studentsAtt.get(att.getStudentNumber()).add(att);
    }

    public void printStudentAttainments(String studentNumber, String order) {
        boolean found = false;
        for(Student student : students) {
            if(student.getStudentNumber().equalsIgnoreCase(studentNumber)) {
                found = true;
                ArrayList<Attainment> attainments = studentsAtt.get(studentNumber);
                System.out.println(student.getName()+" ("+student.getStudentNumber()+"):");
                if("by name".equalsIgnoreCase(order)) {
                    attainments.sort((a,b) -> courses.get(a.getCourseCode()).getName().compareToIgnoreCase(courses.get(b.getCourseCode()).getName()));
                }
                if("by code".equalsIgnoreCase(order)) {
                    attainments.sort((a,b) -> a.getCourseCode().compareToIgnoreCase(b.getCourseCode()));
                }
                for(Attainment att : attainments) {
                    System.out.println("  "+att.getCourseCode()+" "+courses.get(att.getCourseCode()).getName()+": "
                            +att.getGrade());
                }
            }
        }
        if(!found) {
            System.out.println("Unknown student number: "+studentNumber);
        }
    }

    public void printStudentAttainments(String studentNumber) {
        boolean found = false;
        for(Student student : students) {
            if(student.getStudentNumber().equalsIgnoreCase(studentNumber)) {
                found = true;
                System.out.println(student.getName()+" ("+student.getStudentNumber()+"):");
                for(Attainment att : studentsAtt.get(studentNumber)) {
                    System.out.println("  "+att.getCourseCode()+" "+courses.get(att.getCourseCode()).getName()+": "
                    +att.getGrade());
                }
                break;
            }
        }
        if(!found) {
            System.out.println("Unknown student number: "+studentNumber);
        }
    }
}
