package com.mycompany.gammaproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GUI  {

    private GammaProject gammaProject;

    public GUI(GammaProject gammaProject) {
        this.gammaProject = gammaProject;
        initialize();
    }
    private void initialize() {
        JFrame frame;
        JTextField textField;
        JLabel resultLabel;

        frame = new JFrame("Gamma Function Calculator");
        frame.setSize(GammaProject.FRAME_WIDTH, GammaProject.FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        String arial = "Arial";
        int fourteen = 14;

        JLabel label = new JLabel("Enter a number:");
        label.setBounds(GammaProject.LABEL_X, GammaProject.LABEL_Y, GammaProject.LABEL_WIDTH, GammaProject.LABEL_HEIGHT);
        label.setFont(new Font(arial, Font.PLAIN, fourteen));
        frame.add(label);

        textField = new JTextField();
        textField.setBounds(GammaProject.TEXT_FIELD_X, GammaProject.TEXT_FIELD_Y, GammaProject.TEXT_FIELD_WIDTH, GammaProject.TEXT_FIELD_HEIGHT);
        label.setLabelFor(textField);
        frame.add(textField);

        JButton computeButton = new JButton("Compute");
        computeButton.setBounds(GammaProject.BUTTON_X, GammaProject.BUTTON_Y, GammaProject.BUTTON_WIDTH, GammaProject.BUTTON_HEIGHT);
        int sixteen = 16;
        computeButton.setFont(new Font(arial, Font.BOLD, sixteen));
        computeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        computeButton.setMnemonic(KeyEvent.VK_C);
        computeButton.setToolTipText("Compute Gamma (Alt+C)");
        computeButton.setBackground(new Color(70, 130, 180)); // Steel Blue background
        computeButton.setForeground(Color.WHITE); // White text
        frame.add(computeButton);

        int hundredAndSixty = 160;
        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(GammaProject.BUTTON_X + hundredAndSixty, GammaProject.BUTTON_Y, GammaProject.BUTTON_WIDTH, GammaProject.BUTTON_HEIGHT);
        resetButton.setFont(new Font(arial, Font.PLAIN, sixteen));
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.setMnemonic(KeyEvent.VK_R);
        resetButton.setToolTipText("Reset the form (Alt+R)");
        resetButton.setBackground(new Color(220, 20, 60)); // Crimson background
        resetButton.setForeground(Color.WHITE); // White text
        frame.add(resetButton);

        resultLabel = new JLabel("Result:");
        resultLabel.setBounds(GammaProject.RESULT_LABEL_X, GammaProject.RESULT_LABEL_Y, GammaProject.RESULT_LABEL_WIDTH, GammaProject.RESULT_LABEL_HEIGHT);
        resultLabel.setFont(new Font(arial, Font.PLAIN, fourteen));
        frame.add(resultLabel);

        computeButton.addActionListener(e -> {
            String input = textField.getText();
            resultLabel.setText("Calculating...");
            try {
                double x = Double.parseDouble(input);
                if (x < 0) {
                    resultLabel.setText("Please enter a positive number.");
                    return;
                }
                double gammaValue = gammaProject.logGamma(x);
                resultLabel.setText("Gamma(" + x + ") = " + gammaProject.customExp(gammaValue));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        });

        resetButton.addActionListener(e -> {
            textField.setText("");
            resultLabel.setText("Result:");
        });

        frame.setVisible(true);
    }

}
