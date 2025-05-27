package model;

import java.sql.Timestamp;

public class Notice {
    private int id;
    private String courseName;
    private String message;
    private Timestamp datePosted;

    public Notice(int id, String courseName, String message, Timestamp datePosted) {
        this.id = id;
        this.courseName = courseName;
        this.message = message;
        this.datePosted = datePosted;
    }

    public Notice(String courseName, String message) {
        this(-1, courseName, message, null);
    }

    public int getId() { return id; }
    public String getCourseName() { return courseName; }
    public String getMessage() { return message; }
    public Timestamp getDatePosted() { return datePosted; }
}