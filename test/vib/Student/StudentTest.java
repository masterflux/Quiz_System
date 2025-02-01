package uk.ac.ncl.vib.test.vib.Student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.ncl.vib.Student.Student;
import uk.ac.ncl.vib.Student.StudentStatistics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: StudentTest contains all the testing scenarios for Student Class
 * @author Vibhav
 * @date 12/10/2024
 **/

class StudentTest {

    private final Calendar cal =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
    private uk.ac.ncl.vib.Student.Student Student;
    private Date birth;
    /**
     * @Description: Creating a setUp to preload some values
     **/
    @BeforeEach
    void setUp() throws ParseException {

        cal.set(1999,06, 04);
         birth = cal.getTime();

        // Initialize a student object
        Student = new Student("John", "Wick", birth);
    }
    /**
     * @Description: Checking the constructor for valid input data
     **/
    @Test
    void validInputConstructor() {

        assertEquals("John", Student.getFirstName(), "First name should be John");
        assertEquals("Wick", Student.getSurName(), "Last name should be Doe");
        assertEquals(birth, Student.getDateOfBirth(), "Date of birth should match");
    }
    /**
     * @Description: Providing invalid birth date to Constructor
     **/
    @Test
    void InvalidDateOfBirthConstructor() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Student("John", "Wick", sdf.parse("32-13-202320"));
        });
        assertEquals("Invalid value, DOB pattern does not match", exception.getMessage());
    }
    /**
     * @Description: test for getFirstName Method
     **/
    @Test
    void testGetFirstName() {

        assertEquals("John", Student.getFirstName(), "First name should be John");
    }

    /**
     * @Description: test for getSurName Method
     **/
    @Test
    void testGetSurName() {

        assertEquals("Wick", Student.getSurName(), "Last name should be Wick");
    }

    /**
     * @Description: test for getFullName Method
     **/
    @Test
    void testGetFullName() {

        assertEquals("John Wick", Student.getFullName(), "Full name should be 'John Wick'");
    }

    /**
     * @Description: test for getDateOfBirth Method
     **/
    @Test
    void testGetDateOfBirth() {

        assertEquals(birth, Student.getDateOfBirth(), "Date of birth should match");
    }

    /**
     * @Description: test for getStudentStatistics Method
     **/
    @Test
    void testGetStudentStatistics() {
        assertNull(Student.getStudentStatistics(), "Student statistics should be null initially");

        StudentStatistics stats = new StudentStatistics("John", "Wick", birth);
        Student.setStudentStatistics(stats);
        assertNotNull(Student.getStudentStatistics(), "Student statistics should not be null after setting it");
        assertEquals(stats, Student.getStudentStatistics(), "Student statistics should match the set value");
    }



}