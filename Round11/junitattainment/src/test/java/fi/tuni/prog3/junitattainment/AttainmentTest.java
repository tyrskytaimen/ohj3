package fi.tuni.prog3.junitattainment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttainmentTest {
    private final String courseCode = "AAA";
    private final String studentNumber = "000";
    private final int grade = 3;
    private final Attainment testAtm = new Attainment(courseCode,studentNumber,grade);

    @Test
    void testGet() {
        assertAll(() -> assertEquals(courseCode,testAtm.getCourseCode()),
                () -> assertEquals(studentNumber,testAtm.getStudentNumber()),
                () -> assertEquals(grade,testAtm.getGrade()));

    }

    @Test
    void testException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Attainment(null,null,6));
        assertEquals("Invalid course code, student number or grade!",exception.getMessage());
    }

    @Test
    void testToString() {
        final String expectedText = String.format("%s %s %d",courseCode, studentNumber, grade);
        assertEquals(expectedText, testAtm.toString());
    }

    @Test
    void testCompareTo() {
        Attainment atm2 = new Attainment("AAA","001",3);
        Attainment atm3 = new Attainment("BBB","000",3);
        assertAll(() -> assertEquals(-1,testAtm.compareTo(atm2)),
                () -> assertEquals(-1,testAtm.compareTo(atm3)));
    }
}