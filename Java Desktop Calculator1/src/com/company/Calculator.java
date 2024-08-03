package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator = "";
    private double num1, num2, result;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5)); // 5x4 grid with gaps between buttons

        String[] buttons = {
                "C", "CE", "sqrt", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", "MI" // Added empty string for correct GridLayout
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "C":
                display.setText("");
                operator = "";
                break;
            case "CE":
                String currentText = display.getText();
                if (currentText.length() > 0) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            case "=":
                if (!operator.isEmpty()) {
                    num2 = Double.parseDouble(display.getText());
                    switch (operator) {
                        case "+": result = num1 + num2; break;
                        case "-": result = num1 - num2; break;
                        case "*": result = num1 * num2; break;
                        case "/":
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                display.setText("Hata");
                                return;
                            }
                            break;
                    }
                    display.setText(String.valueOf(result));
                    operator = "";
                }
                break;
            case "sqrt":
                double value = Double.parseDouble(display.getText());
                if (value >= 0) {
                    display.setText(String.valueOf(Math.sqrt(value)));
                } else {
                    display.setText("Hata");
                }
                break;
            default:
                if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                    display.setText(display.getText() + command);
                } else {
                    if (operator.isEmpty()) {
                        num1 = Double.parseDouble(display.getText());
                        display.setText("");
                    }
                    operator = command;
                }
                break;
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }
}
