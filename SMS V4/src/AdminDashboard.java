import dao.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminDashboard extends JFrame {
    private DefaultTableModel studentTableModel, resultTableModel, attendanceTableModel, feeTableModel, noticeTableModel;
    private Timer autoRefreshTimer;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // Student Tab
        JPanel studentPanel = new JPanel(new BorderLayout());
        studentTableModel = new DefaultTableModel(new String[]{"Admission No", "Name", "Phone", "Course"}, 0);
        JTable studentTable = new JTable(studentTableModel);
        refreshStudentTable();
        studentPanel.add(new JScrollPane(studentTable), BorderLayout.CENTER);

        JPanel studentBtnPanel = new JPanel();
        JButton addStudentBtn = new JButton("Add Student");
        addStudentBtn.addActionListener(e -> StudentForms.showStudentDialog(this, null));
        JButton setPwdBtn = new JButton("Set/Reset Student Password");
        setPwdBtn.addActionListener(e -> StudentForms.showSetPasswordDialog(this));
        JButton refreshStudentBtn = new JButton("Refresh Now");
        refreshStudentBtn.addActionListener(e -> refreshStudentTable());
        studentBtnPanel.add(addStudentBtn);
        studentBtnPanel.add(setPwdBtn);
        studentBtnPanel.add(refreshStudentBtn);
        studentPanel.add(studentBtnPanel, BorderLayout.SOUTH);

        tabs.add("Students", studentPanel);

        // Result Tab
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultTableModel = new DefaultTableModel(new String[]{"ID", "Admission No", "Exam", "Subject", "Marks"}, 0);
        JTable resultTable = new JTable(resultTableModel);
        refreshResultTable();
        resultPanel.add(new JScrollPane(resultTable), BorderLayout.CENTER);
        JButton addResultBtn = new JButton("Add Result");
        addResultBtn.addActionListener(e -> StudentForms.showResultDialog(this, null));
        JButton refreshResultBtn = new JButton("Refresh Now");
        refreshResultBtn.addActionListener(e -> refreshResultTable());
        JPanel resultBtnPanel = new JPanel();
        resultBtnPanel.add(addResultBtn);
        resultBtnPanel.add(refreshResultBtn);
        resultPanel.add(resultBtnPanel, BorderLayout.SOUTH);
        tabs.add("Results", resultPanel);

        // Attendance Tab
        JPanel attendancePanel = new JPanel(new BorderLayout());
        attendanceTableModel = new DefaultTableModel(new String[]{"ID", "Admission No", "Month", "Attendance %"}, 0);
        JTable attendanceTable = new JTable(attendanceTableModel);
        refreshAttendanceTable();
        attendancePanel.add(new JScrollPane(attendanceTable), BorderLayout.CENTER);
        JButton updateAttendanceBtn = new JButton("Update Attendance");
        updateAttendanceBtn.addActionListener(e -> StudentForms.showAttendanceDialog(this));
        JButton refreshAttendanceBtn = new JButton("Refresh Now");
        refreshAttendanceBtn.addActionListener(e -> refreshAttendanceTable());
        JPanel attBtnPanel = new JPanel();
        attBtnPanel.add(updateAttendanceBtn);
        attBtnPanel.add(refreshAttendanceBtn);
        attendancePanel.add(attBtnPanel, BorderLayout.SOUTH);
        tabs.add("Attendance", attendancePanel);

        // Fee Tab
        JPanel feePanel = new JPanel(new BorderLayout());
        feeTableModel = new DefaultTableModel(new String[]{"ID", "Admission No", "Paid", "Due"}, 0);
        JTable feeTable = new JTable(feeTableModel);
        refreshFeeTable();
        feePanel.add(new JScrollPane(feeTable), BorderLayout.CENTER);
        JButton updateFeeBtn = new JButton("Update Fee");
        updateFeeBtn.addActionListener(e -> StudentForms.showFeeDialog(this));
        JButton refreshFeeBtn = new JButton("Refresh Now");
        refreshFeeBtn.addActionListener(e -> refreshFeeTable());
        JPanel feeBtnPanel = new JPanel();
        feeBtnPanel.add(updateFeeBtn);
        feeBtnPanel.add(refreshFeeBtn);
        feePanel.add(feeBtnPanel, BorderLayout.SOUTH);
        tabs.add("Fees", feePanel);

        // Notice Tab
        JPanel noticePanel = new JPanel(new BorderLayout());
        noticeTableModel = new DefaultTableModel(new String[]{"ID", "Course", "Message", "Posted"}, 0);
        JTable noticeTable = new JTable(noticeTableModel);
        refreshNoticeTable();
        noticePanel.add(new JScrollPane(noticeTable), BorderLayout.CENTER);
        JButton addNoticeBtn = new JButton("Add Notice");
        addNoticeBtn.addActionListener(e -> StudentForms.showNoticeDialog(this));
        JButton refreshNoticeBtn = new JButton("Refresh Now");
        refreshNoticeBtn.addActionListener(e -> refreshNoticeTable());
        JPanel noticeBtnPanel = new JPanel();
        noticeBtnPanel.add(addNoticeBtn);
        noticeBtnPanel.add(refreshNoticeBtn);
        noticePanel.add(noticeBtnPanel, BorderLayout.SOUTH);
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

        // Real-time auto-refresh every 10 seconds (10000 ms)
        autoRefreshTimer = new Timer(10000, (ActionEvent e) -> refreshAllTables());
        autoRefreshTimer.start();
    }

    private void refreshAllTables() {
        refreshStudentTable();
        refreshResultTable();
        refreshAttendanceTable();
        refreshFeeTable();
        refreshNoticeTable();
    }

    private void refreshStudentTable() {
        studentTableModel.setRowCount(0);
        for (Student s : StudentDao.getAll()) {
            studentTableModel.addRow(new Object[]{
                    s.getAdmissionNumber(), s.getName(), s.getPhoneNumber(), s.getCourseName()
            });
        }
    }

    private void refreshResultTable() {
        resultTableModel.setRowCount(0);
        for (Student s : StudentDao.getAll()) {
            for (model.Result r : ResultDao.getByStudent(s.getAdmissionNumber())) {
                resultTableModel.addRow(new Object[]{
                        r.getId(), r.getAdmissionNumber(), r.getExamType(), r.getSubject(), r.getMarks()
                });
            }
        }
    }

    private void refreshAttendanceTable() {
        attendanceTableModel.setRowCount(0);
        for (Student s : StudentDao.getAll()) {
            for (model.Attendance a : AttendanceDao.getByStudent(s.getAdmissionNumber())) {
                attendanceTableModel.addRow(new Object[]{
                        a.getId(), a.getAdmissionNumber(), a.getMonth(), a.getAttendancePercentage()
                });
            }
        }
    }

    private void refreshFeeTable() {
        feeTableModel.setRowCount(0);
        for (Student s : StudentDao.getAll()) {
            model.Fee fee = FeeDao.getByStudent(s.getAdmissionNumber());
            if (fee != null) {
                feeTableModel.addRow(new Object[]{
                        fee.getId(), fee.getAdmissionNumber(), fee.getAmountPaid(), fee.getDue()
                });
            }
        }
    }

    private void refreshNoticeTable() {
        noticeTableModel.setRowCount(0);
        // Aggregate all notices for all courses without duplicates
        java.util.Set<Integer> seen = new java.util.HashSet<>();
        for (Student s : StudentDao.getAll()) {
            for (model.Notice n : NoticeDao.getByCourse(s.getCourseName())) {
                if (!seen.contains(n.getId())) {
                    noticeTableModel.addRow(new Object[]{
                            n.getId(), n.getCourseName(), n.getMessage(), n.getDatePosted()
                    });
                    seen.add(n.getId());
                }
            }
        }
    }
}