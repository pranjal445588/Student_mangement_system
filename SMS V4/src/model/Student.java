package model;

public class Student {
    private String admissionNumber;
    private String name;
    private String phoneNumber;
    private String courseName;

    public Student(String admissionNumber, String name, String phoneNumber, String courseName) {
        this.admissionNumber = admissionNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.courseName = courseName;
    }

    public String getAdmissionNumber() { return admissionNumber; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getCourseName() { return courseName; }

    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
}