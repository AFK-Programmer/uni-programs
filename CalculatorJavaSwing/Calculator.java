import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField textField;
    String operator = "";
    double num1 = 0, num2 = 0;

    public Calculator() {
        setTitle("Calculator");
        setSize(320, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Display
        textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.BOLD, 28));
        textField.setEditable(false);
        textField.setBackground(new Color(30, 30, 30));
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        // Button Panel
        JPanel panel = new JPanel(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(45, 45, 45));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttons = {
                "C", "", "", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", ""
        };

        for (String text : buttons) {
            if (text.equals("")) {
                panel.add(new JLabel());
                continue;
            }

            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Colors
            if (text.matches("[0-9.]")) {
                btn.setBackground(new Color(70, 70, 70));
                btn.setForeground(Color.WHITE);
            } else if (text.equals("=")) {
                btn.setBackground(new Color(0, 120, 215));
                btn.setForeground(Color.WHITE);
            } else if (text.equals("C")) {
                btn.setBackground(new Color(200, 80, 80));
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(new Color(100, 100, 100));
                btn.setForeground(Color.WHITE);
            }

            // Hover effect
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(btn.getBackground().brighter());
                }

                public void mouseExited(MouseEvent e) {
                    btn.setBackground(btn.getBackground().darker());
                }
            });

            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);

        // Bottom About Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(45, 45, 45));

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

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            textField.setText(textField.getText() + command);
        } else if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = 0;
            operator = "";
        } else if (command.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(textField.getText());
            operator = command;
            textField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case "+": textField.setText(String.valueOf(num1 + num2)); break;
                case "-": textField.setText(String.valueOf(num1 - num2)); break;
                case "*": textField.setText(String.valueOf(num1 * num2)); break;
                case "/": textField.setText(String.valueOf(num1 / num2)); break;
            }
        }
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
        new Calculator();
    }
}