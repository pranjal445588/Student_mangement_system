import dao.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentDashboard extends JFrame {
    public StudentDashboard(String username) {
        setTitle("Student Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Student student = StudentDao.get(username);
        if (student == null) {
            JOptionPane.showMessageDialog(this, "Student profile not found.");
            dispose();
            return;
        }

        JTabbedPane tabs = new JTabbedPane();

        // Profile
        JPanel profilePanel = new JPanel(new GridLayout(4,2));
        profilePanel.add(new JLabel("Admission Number:"));
        profilePanel.add(new JLabel(student.getAdmissionNumber()));
        profilePanel.add(new JLabel("Name:"));
        profilePanel.add(new JLabel(student.getName()));
        profilePanel.add(new JLabel("Phone:"));
        profilePanel.add(new JLabel(student.getPhoneNumber()));
        profilePanel.add(new JLabel("Course Name:"));
        profilePanel.add(new JLabel(student.getCourseName()));
        tabs.add("Profile", profilePanel);

        // Results
        JPanel resultsPanel = new JPanel(new BorderLayout());
        DefaultTableModel resultTableModel = new DefaultTableModel(new String[]{"Exam", "Subject", "Marks"}, 0);
        JTable resultTable = new JTable(resultTableModel);
        List<Result> results = ResultDao.getByStudent(student.getAdmissionNumber());
        for (Result r : results) {
            resultTableModel.addRow(new Object[]{r.getExamType(), r.getSubject(), r.getMarks()});
        }
        resultsPanel.add(new JScrollPane(resultTable), BorderLayout.CENTER);
        tabs.add("Results", resultsPanel);

        // Attendance
        JPanel attendancePanel = new JPanel(new BorderLayout());
        DefaultTableModel attendanceModel = new DefaultTableModel(new String[]{"Month", "Attendance %"}, 0);
        JTable attendanceTable = new JTable(attendanceModel);
        List<Attendance> attendances = AttendanceDao.getByStudent(student.getAdmissionNumber());
        for (Attendance a : attendances) {
            attendanceModel.addRow(new Object[]{a.getMonth(), a.getAttendancePercentage()});
        }
        attendancePanel.add(new JScrollPane(attendanceTable), BorderLayout.CENTER);
        tabs.add("Attendance", attendancePanel);

        // Fee
        JPanel feePanel = new JPanel(new GridLayout(2,2));
        Fee fee = FeeDao.getByStudent(student.getAdmissionNumber());
        feePanel.add(new JLabel("Amount Paid:"));
        feePanel.add(new JLabel(fee != null ? String.valueOf(fee.getAmountPaid()) : "-"));
        feePanel.add(new JLabel("Due:"));
        feePanel.add(new JLabel(fee != null ? String.valueOf(fee.getDue()) : "-"));
        tabs.add("Fees", feePanel);

        // Notices
        JPanel noticePanel = new JPanel(new BorderLayout());
        DefaultTableModel noticeModel = new DefaultTableModel(new String[]{"Message", "Posted"}, 0);
        JTable noticeTable = new JTable(noticeModel);
        List<Notice> notices = NoticeDao.getByCourse(student.getCourseName());
        for (Notice n : notices) {
            noticeModel.addRow(new Object[]{n.getMessage(), n.getDatePosted()});
        }
        noticePanel.add(new JScrollPane(noticeTable), BorderLayout.CENTER);
        tabs.add("Notices", noticePanel);

        add(tabs);

        // Logout button
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginUI().setVisible(true);
        });
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutBtn);
        add(logoutPanel, BorderLayout.NORTH);
    }
}