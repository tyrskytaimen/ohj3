package fi.tuni.prog3.junitattainment;

public class Attainment implements Comparable<Attainment>{
    private final String courseCode;
    private final String studentNumber;
    private final int grade;

    Attainment(String courseCode, String studentNumber, int grade) throws IllegalArgumentException{
        if(courseCode == null || studentNumber == null || grade < 0 || grade > 5) {
            throw new IllegalArgumentException("Invalid course code, student number or grade!");
        }
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
        return String.format("%s %s %d",courseCode, studentNumber, grade);
    }

    @Override
    public int compareTo(Attainment o) {
        if(studentNumber.equals(o.studentNumber)) {
            return courseCode.compareTo(o.courseCode);
        }
        else {
            return studentNumber.compareTo(o.studentNumber);
        }
    }
}
