import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class CalendarSwing extends JFrame {

    public CalendarSwing(int year) {
        setTitle("Calendar - " + year);
        setSize(1000, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top label
        JLabel titleLabel = new JLabel("Calendar - " + year, JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        add(titleLabel, BorderLayout.NORTH);

        // Main panel for months
        JPanel monthsPanel = new JPanel(new GridLayout(3, 4, 10, 10));

        String[] monthNames = {
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"
        };

        for (int month = 0; month < 12; month++) {
            JPanel monthPanel = createMonthPanel(month, year, monthNames[month]);
            monthsPanel.add(monthPanel);
        }

        add(new JScrollPane(monthsPanel), BorderLayout.CENTER);

        // Bottom About Button
        JPanel bottomPanel = new JPanel();
        JButton aboutBtn = new JButton("About");
        aboutBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        aboutBtn.setBackground(new Color(0, 150, 136));
        aboutBtn.setForeground(Color.WHITE);
        aboutBtn.setFocusPainted(false);
        aboutBtn.addActionListener(e -> showAbout());
        bottomPanel.add(aboutBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createMonthPanel(int month, int year, String monthName) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel label = new JLabel(monthName, JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);

        JPanel daysPanel = new JPanel(new GridLayout(0, 7));
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        for (String d : days) {
            JLabel dayLabel = new JLabel(d, JLabel.CENTER);
            dayLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
            daysPanel.add(dayLabel);
        }

        // Fill the days
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int firstDay = cal.get(Calendar.DAY_OF_WEEK); // 1 = Sunday
        int maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Empty cells for first week
        for (int i = 1; i < firstDay; i++) {
            daysPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= maxDays; day++) {
            JLabel dayLabel = new JLabel(String.valueOf(day), JLabel.CENTER);
            dayLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            daysPanel.add(dayLabel);
        }

        panel.add(daysPanel, BorderLayout.CENTER);
        return panel;
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this,
                "Name: Joy Biswas\n" +
                "ID: 20242116010\n" +
                "Course Code: CSE-2200\n" +
                "This program is developed with JAVA.",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter Year:");
        try {
            int year = Integer.parseInt(input);
            new CalendarSwing(year);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Year!");
        }
    }
}