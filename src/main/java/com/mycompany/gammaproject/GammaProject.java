package com.mycompany.gammaproject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
/**
 *
 * @author yehia
 */
public class GammaProject {
    /**
     * Gamma Project - A simple GUI-based application to compute the Gamma function.
     * <p>
     * This class provides methods to compute the Gamma function using the Lanczos
     * approximation and also includes custom implementations for various
     * mathematical functions such as square root, power, sine, logarithm, and
     * exponential.
     *
     * @author yehia
     */
    // Constants for Swing components layout
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;
    private static final int LABEL_X = 50;
    private static final int LABEL_Y = 50;
    private static final int LABEL_WIDTH = 150;
    private static final int LABEL_HEIGHT = 25;
    private static final int TEXT_FIELD_X = 200;
    private static final int TEXT_FIELD_Y = 50;
    private static final int TEXT_FIELD_WIDTH = 150;
    private static final int TEXT_FIELD_HEIGHT = 25;
    private static final int BUTTON_X = 50;
    private static final int BUTTON_Y = 100;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 25;
    private static final int RESULT_LABEL_X = 50;
    private static final int RESULT_LABEL_Y = 150;
    private static final int RESULT_LABEL_WIDTH = 300;
    private static final int RESULT_LABEL_HEIGHT = 25;
        // Lanczos coefficients for the series summation to compute A(x)
        private static final double[] COEFFICIENTS = {
                676.5203681218851, -1259.1392167224028,
                771.32342877765313, -176.61502916214059,
                12.507343278686905, -0.13857109526572012,
                9.9843695780195716e-6, 1.5056327351493116e-7
        };

        private static final double LANCZOS_CONSTANT_G = 7.0;
        private static final double PI = Math.PI;
        private static final double E = Math.E;
        private static final double EPSILON = 1e-15;

        /**
         * Computes the natural logarithm of the Gamma function using the Lanczos approximation.
         *
         * @param x The input value for which to compute the Gamma function.
         * @return The natural logarithm of the Gamma function for the given input.
         */
        public static double logGamma(double x) {
            double limit = 0.5;
            if (x < limit) {
                // Use reflection formula for x < 0.5
                return Math.log(PI / (customSin(PI * x) * logGamma(1 - x)));
            }

            x -= 1;
            double A = 0.99999999999980993;
            double t = x + LANCZOS_CONSTANT_G + 0.5;

            // Perform the coefficient series summation
            for (int i = 0; i < COEFFICIENTS.length; i++) {
                A += COEFFICIENTS[i] / (x + i + 1);
            }

            // Lanczos approximation formula
            return Math.log(Math.sqrt(2 * PI) * A) + (x + 0.5) * Math.log(t) - t;
        }

        /**
         * Computes the square root of a number using the Newton-Raphson method.
         *
         * @param value The value to compute the square root of.
         * @return The square root of the given value.
         */
        private static double customSqrt(final double value) {
            if (value == 0){ return 0;}

            double x = value;
            while (true) {
                double y = (x + value / x) / 2;
                if (Math.abs(x - y) < EPSILON) {
                    return y;
                }
                x = y;
            }
        }

        /**
         * Computes the power of a number using exponentiation by squaring.
         *
         * @param base     The base number.
         * @param exponent The exponent to raise the base to.
         * @return The result of raising the base to the power of the exponent.
         */
        private static double customPow(final double base, final double exponent) {
            if (exponent == 0) {
                return 1;
            }
            if (exponent < 0) {
                return 1 / customPow(base, -exponent);
            }

            double result = 1;
            double currentBase = base;
            int intExponent = (int) exponent;

            while (intExponent > 0) {
                if ((intExponent & 1) == 1) result *= currentBase;
                currentBase *= currentBase;
                intExponent >>= 1;
            }

            double fractionExponent = exponent - intExponent;
            if (fractionExponent != 0) {
                result *= customExp(fractionExponent * Math.log(base));
            }

            return result;
        }

        /**
         * Computes the exponential of a value using a series expansion.
         *
         * @param value The value to compute the exponential of.
         * @return The exponential of the given value.
         */
        private static double customExp(final double value) {
            double result = 1.0;
            double term = 1.0;

            for (int n = 1; Math.abs(term) > EPSILON; n++) {
                term *= value / n;
                result += term;
            }

            return result;
        }

        /**
         * Computes the natural logarithm of a value using the Taylor series expansion.
         *
         * @param value The value to compute the natural logarithm of.
         * @return The natural logarithm of the given value.
         */
        private static double customLog(final double value) {
            if (value <= 0) {
                throw new IllegalArgumentException("Logarithm input must be positive");
            }

            double result = 0.0;
            double x = (value - 1) / (value + 1);
            double term = x;
            int n = 1;

            while (Math.abs(term) > EPSILON) {
                result += term / n;
                term *= x * x;
                n += 2;
            }

            return 2 * result;
        }

        /**
         * Computes the sine of a value using the Taylor series expansion.
         *
         * @param value The value to compute the sine of.
         * @return The sine of the given value.
         */
        private static double customSin(double value) {
            value = value % (2 * PI);
            double result = 0.0;
            double term = value;
            int n = 1;

            while (Math.abs(term) > EPSILON) {
                result += term;
                term *= -1 * value * value / ((2 * n) * (2 * n + 1));
                n++;
            }

            return result;
        }


    /**
     * Main method to initialize the GUI and handle user input for computing the Gamma function.
     *
     * @param args Command line arguments (not used).
     */
        public static void main(String[] args) {
            JFrame frame = new JFrame("Gamma Function Calculator");
            frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);

            JLabel label = new JLabel("Enter a number:");
            label.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            frame.add(label);

            JTextField textField = new JTextField();
            textField.setBounds(TEXT_FIELD_X, TEXT_FIELD_Y, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
            label.setLabelFor(textField);
            textField.getAccessibleContext().setAccessibleName("Number Input");
            textField.getAccessibleContext().setAccessibleDescription("Input field to enter a number for which the Gamma function will be calculated.");
            frame.add(textField);

            JButton computeButton = new JButton("Compute Gamma");
            computeButton.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            computeButton.setFont(new Font("Arial", Font.BOLD, 16));
            computeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            computeButton.setMnemonic(KeyEvent.VK_C);
            computeButton.setToolTipText("Compute Gamma (Alt+C)");
            frame.add(computeButton);

            JButton resetButton = new JButton("Reset");
            resetButton.setBounds(BUTTON_X + 160, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            resetButton.setFont(new Font("Arial", Font.PLAIN, 16));
            resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            resetButton.setMnemonic(KeyEvent.VK_R);
            resetButton.setToolTipText("Reset the form (Alt+R)");
            frame.add(resetButton);

            JLabel resultLabel = new JLabel("Result:");
            resultLabel.setBounds(RESULT_LABEL_X, RESULT_LABEL_Y, RESULT_LABEL_WIDTH, RESULT_LABEL_HEIGHT);
            resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            resultLabel.getAccessibleContext().setAccessibleName("Result Label");
            resultLabel.getAccessibleContext().setAccessibleDescription("Displays the result of the Gamma function computation.");
            frame.add(resultLabel);

            computeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = textField.getText();
                    resultLabel.setText("Calculating...");
                    try {
                        double x = Double.parseDouble(input);
                        if (x < 0) {
                            resultLabel.setText("Please enter a positive number.");
                            return;
                        }
                        double gammaValue = logGamma(x);
                        resultLabel.setText("Gamma(" + x + ") = " + customExp(gammaValue));
                    } catch (NumberFormatException ex) {
                        resultLabel.setText("Please enter a valid number.");
                    }
                }
            });

            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.setText("");
                    resultLabel.setText("Result:");
                }
            });

            frame.setVisible(true);
}}


