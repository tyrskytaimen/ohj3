import java.util.Comparator;

public class Attainment implements Comparable<Attainment> {
    public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment o1, Attainment o2) {
            int x = o1.courseCode.compareTo(o2.courseCode);
            if(x == 0) {
                x = o1.studentNumber.compareTo(o2.studentNumber);
            }
            return x;
        }
    };

    public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment o1, Attainment o2) {
            int x = o1.courseCode.compareTo(o2.courseCode);
            if(x == 0) {
                x = Integer.compare(o1.grade, o2.grade) * -1;
            }
            return x;
        }
    };

    private final String courseCode;
    private final String studentNumber;
    private final int grade;

    Attainment(String courseCode, String studentNumber, int grade) {
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d%n", courseCode, studentNumber, grade);
    }

    @Override
    public int compareTo(Attainment o) {
        int x = studentNumber.compareTo(o.studentNumber);
        if(x == 0) {
            x = courseCode.compareTo(o.courseCode);
        }
        return x;
    }
}
