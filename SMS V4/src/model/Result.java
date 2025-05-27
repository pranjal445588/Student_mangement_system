package model;

public class Result {
    private int id;
    private String admissionNumber;
    private String examType;
    private String subject;
    private int marks;

    public Result(int id, String admissionNumber, String examType, String subject, int marks) {
        this.id = id;
        this.admissionNumber = admissionNumber;
        this.examType = examType;
        this.subject = subject;
        this.marks = marks;
    }

    public Result(String admissionNumber, String examType, String subject, int marks) {
        this(-1, admissionNumber, examType, subject, marks);
    }

    public int getId() { return id; }
    public String getAdmissionNumber() { return admissionNumber; }
    public String getExamType() { return examType; }
    public String getSubject() { return subject; }
    public int getMarks() { return marks; }
}