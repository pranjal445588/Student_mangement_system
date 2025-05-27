package model;

public class Attendance {
    private int id;
    private String admissionNumber;
    private String month;
    private float attendancePercentage;

    public Attendance(int id, String admissionNumber, String month, float attendancePercentage) {
        this.id = id;
        this.admissionNumber = admissionNumber;
        this.month = month;
        this.attendancePercentage = attendancePercentage;
    }

    public Attendance(String admissionNumber, String month, float attendancePercentage) {
        this(-1, admissionNumber, month, attendancePercentage);
    }

    public int getId() { return id; }
    public String getAdmissionNumber() { return admissionNumber; }
    public String getMonth() { return month; }
    public float getAttendancePercentage() { return attendancePercentage; }
}