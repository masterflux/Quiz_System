package uk.ac.ncl.vib.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @Description: Contains implementation of Student class
 * @author Vibhav
 * @date 07/10/2024
 */

public class Student {

    private final String firstName;
    private final String surName;
    private final Date dateOfBirth;
    private StudentStatistics studentStatistics;

    /**
     * @Description: Student constructor which perform validations
     * @param firstName - first name of the student
     * @param surName  - last name of the student
     * @param dateOfBirth  - Birth Date of the student
     * @throws  IllegalArgumentException - if the entered First name is empty
     * @throws  IllegalArgumentException - if the entered Surname is empty
     * @throws  IllegalArgumentException - if the entered date is empty or if it does not match the date pattern
     */

    public Student(String firstName, String surName, Date dateOfBirth) {

        if(firstName.isEmpty()){
            throw new IllegalArgumentException("Invalid value, First Name cannot be empty");
        }
        if(surName.isEmpty()){
            throw new IllegalArgumentException("Invalid value, Surname cannot be empty");
        }
        if(dateOfBirth == null){
            throw new IllegalArgumentException("Invalid value, Date Of Birth cannot be null");
        }
        String regex = "^([0-2][0-9]|3[0-1])-(1[0-2]|0[1-9])-(19|20)\\d{2}$";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String format = sdf.format(dateOfBirth);

        if(!format.matches(regex)){
            throw new IllegalArgumentException("Invalid value, DOB pattern does not match");
        }

        this.firstName = firstName;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * @Description: return the First Name of the Student
     * @return String
     **/
    public String getFirstName() {
        return firstName;
    }

    /**
     * @Description: return the Last Name of the Student
     * @return String
     **/
    public String getSurName() {
        return surName;
    }

    /**
     * @Description: return the Full Name of the Student
     * @return String
     **/
    public String getFullName() {
        return getFirstName() + " " + getSurName();
    }

    /**
     * @Description: returns Birth Date of the Student
     * @return String
     **/
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @Description: returns studentStatistics
     * @return StudentStatistics
     **/
    public StudentStatistics getStudentStatistics() {
        return studentStatistics;
    }
    /**
     * @Description: set the stats for the student
     **/
    public void setStudentStatistics(StudentStatistics studentStatistics) {
        this.studentStatistics = studentStatistics;
    }

    /**
     * @Description: Overriding equals
     * @param o
     * @return boolean
     **/
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if(!(o instanceof Student)){
            return false;
        }
        Student stu = (Student) o;

        return Objects.equals(firstName, stu.firstName)
                && Objects.equals(surName, stu.surName)
                && Objects.equals(dateOfBirth, stu.dateOfBirth);
    }

    /**
     * @Description: Overriding hashcode
     * @return int
     **/
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash
                +(firstName == null ? 0 : firstName.hashCode())
                +(surName == null ? 0 : surName.hashCode())
                +(dateOfBirth == null ? 0 : dateOfBirth.hashCode());
        return 31*hash;
    }


    /**
     * @Description: Overriding toString to make string readable
     * @return String
     **/
    @Override
    public String toString() {
        SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
        String bd = simple.format(getDateOfBirth());

        return "Name: "+ getFullName();
    }
}
