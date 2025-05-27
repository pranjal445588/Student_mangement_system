import dao.*;
import model.*;

import javax.swing.*;

public class StudentForms {
    // Add/Edit Student
    public static void showStudentDialog(JFrame parent, Student student) {
        JTextField admissionField = new JTextField(student == null ? "" : student.getAdmissionNumber());
        JTextField nameField = new JTextField(student == null ? "" : student.getName());
        JTextField phoneField = new JTextField(student == null ? "" : student.getPhoneNumber());
        JTextField courseField = new JTextField(student == null ? "" : student.getCourseName());
        admissionField.setEditable(student == null);

        Object[] fields = {
            "Admission Number:", admissionField,
            "Name:", nameField,
            "Phone Number:", phoneField,
            "Course Name:", courseField
        };

        if (JOptionPane.showConfirmDialog(parent, fields, (student == null ? "Add" : "Edit") + " Student", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            Student s = new Student(admissionField.getText(), nameField.getText(), phoneField.getText(), courseField.getText());
            if (student == null) {
                StudentDao.add(s);
            } else {
                StudentDao.update(s);
            }
        }
    }

    // Add Result
    public static void showResultDialog(JFrame parent, Result result) {
        JTextField admissionField = new JTextField(result == null ? "" : result.getAdmissionNumber());
        JTextField examField = new JTextField(result == null ? "" : result.getExamType());
        JTextField subjectField = new JTextField(result == null ? "" : result.getSubject());
        JTextField marksField = new JTextField(result == null ? "" : String.valueOf(result.getMarks()));

        Object[] fields = {
            "Admission Number:", admissionField,
            "Exam Type:", examField,
            "Subject:", subjectField,
            "Marks:", marksField
        };

        if (JOptionPane.showConfirmDialog(parent, fields, (result == null ? "Add" : "Edit") + " Result", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            Result r = new Result(admissionField.getText(), examField.getText(), subjectField.getText(), Integer.parseInt(marksField.getText()));
            if (result == null) {
                ResultDao.add(r);
            } else {
                ResultDao.update(r);
            }
        }
    }

    // Update Attendance
    public static void showAttendanceDialog(JFrame parent) {
        JTextField admissionField = new JTextField();
        JTextField monthField = new JTextField();
        JTextField percentField = new JTextField();

        Object[] fields = {
            "Admission Number:", admissionField,
            "Month:", monthField,
            "Attendance %:", percentField
        };

        if (JOptionPane.showConfirmDialog(parent, fields, "Update Attendance", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            Attendance a = new Attendance(admissionField.getText(), monthField.getText(), Float.parseFloat(percentField.getText()));
            AttendanceDao.addOrUpdate(a);
        }
    }

    // Update Fee
    public static void showFeeDialog(JFrame parent) {
        JTextField admissionField = new JTextField();
        JTextField paidField = new JTextField();
        JTextField dueField = new JTextField();

        Object[] fields = {
            "Admission Number:", admissionField,
            "Amount Paid:", paidField,
            "Due Amount:", dueField
        };

        if (JOptionPane.showConfirmDialog(parent, fields, "Update Fee", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            Fee f = new Fee(admissionField.getText(), Double.parseDouble(paidField.getText()), Double.parseDouble(dueField.getText()));
            FeeDao.addOrUpdate(f);
        }
    }

    // Add Notice
    public static void showNoticeDialog(JFrame parent) {
        JTextField courseField = new JTextField();
        JTextArea messageArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        Object[] fields = {
            "Course Name:", courseField,
            "Message:", scrollPane
        };

        if (JOptionPane.showConfirmDialog(parent, fields, "Add Notice", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            Notice n = new Notice(courseField.getText(), messageArea.getText());
            NoticeDao.add(n);
        }
    }

    // Set/Reset Student Password
    public static void showSetPasswordDialog(JFrame parent) {
        JTextField admissionField = new JTextField();
        JTextField passwordField = new JTextField();

        Object[] fields = {
            "Admission Number:", admissionField,
            "New Password (leave blank to use phone number):", passwordField
        };

        if (JOptionPane.showConfirmDialog(parent, fields, "Set/Reset Student Password", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String admissionNumber = admissionField.getText().trim();
            String newPassword = passwordField.getText().trim();

            Student student = StudentDao.get(admissionNumber);
            if (student == null) {
                JOptionPane.showMessageDialog(parent, "Student not found!");
                return;
            }
            if (newPassword.isEmpty()) {
                newPassword = student.getPhoneNumber();
            }
            // Create or update user record
            boolean exists = UserDao.exists(admissionNumber);
            if (!exists) {
                UserDao.createStudentUser(admissionNumber, newPassword);
                JOptionPane.showMessageDialog(parent, "Student user created!");
            } else {
                UserDao.updatePassword(admissionNumber, newPassword);
                JOptionPane.showMessageDialog(parent, "Password updated!");
            }
        }
    }
}