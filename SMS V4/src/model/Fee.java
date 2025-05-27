package model;

public class Fee {
    private int id;
    private String admissionNumber;
    private double amountPaid;
    private double due;

    public Fee(int id, String admissionNumber, double amountPaid, double due) {
        this.id = id;
        this.admissionNumber = admissionNumber;
        this.amountPaid = amountPaid;
        this.due = due;
    }

    public Fee(String admissionNumber, double amountPaid, double due) {
        this(-1, admissionNumber, amountPaid, due);
    }

    public int getId() { return id; }
    public String getAdmissionNumber() { return admissionNumber; }
    public double getAmountPaid() { return amountPaid; }
    public double getDue() { return due; }
}