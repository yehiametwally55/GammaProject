package com.mycompany.gammaproject;

/**
 * GammaProject - A simple GUI-based application to compute the Gamma function.
 * <p>
 * This class provides methods to compute the Gamma function using the Lanczos
 * approximation and also includes custom implementations for various
 * mathematical functions such as square root, power, sine, logarithm, and
 * exponential.
 * </p>
 *
 * @author yehia
 */
public class GammaProject {

    /**
     * Width of the application frame in pixels.
     */
    public static final int FRAME_WIDTH = 400;

    /**
     * Height of the application frame in pixels.
     */
    public static final int FRAME_HEIGHT = 300;

    /**
     * X-coordinate of the label in the application frame.
     */
    public static final int LABEL_X = 50;

    /**
     * Y-coordinate of the label in the application frame.
     */
    public static final int LABEL_Y = 50;

    /**
     * Width of the label in pixels.
     */
    public static final int LABEL_WIDTH = 150;

    /**
     * Height of the label in pixels.
     */
    public static final int LABEL_HEIGHT = 25;

    /**
     * X-coordinate of the text field in the application frame.
     */
    public static final int TEXT_FIELD_X = 200;

    /**
     * Y-coordinate of the text field in the application frame.
     */
    public static final int TEXT_FIELD_Y = 50;

    /**
     * Width of the text field in pixels.
     */
    public static final int TEXT_FIELD_WIDTH = 150;

    /**
     * Height of the text field in pixels.
     */
    public static final int TEXT_FIELD_HEIGHT = 25;

    /**
     * X-coordinate of the button in the application frame.
     */
    public static final int BUTTON_X = 50;

    /**
     * Y-coordinate of the button in the application frame.
     */
    public static final int BUTTON_Y = 100;

    /**
     * Width of the button in pixels.
     */
    public static final int BUTTON_WIDTH = 150;

    /**
     * Height of the button in pixels.
     */
    public static final int BUTTON_HEIGHT = 25;

    /**
     * X-coordinate of the result label in the application frame.
     */
    public static final int RESULT_LABEL_X = 50;

    /**
     * Y-coordinate of the result label in the application frame.
     */
    public static final int RESULT_LABEL_Y = 150;

    /**
     * Width of the result label in pixels.
     */
    public static final int RESULT_LABEL_WIDTH = 300;

    /**
     * Height of the result label in pixels.
     */
    public static final int RESULT_LABEL_HEIGHT = 25;

    /**
     * Lanczos coefficients for the series summation to compute A(x).
     */
    private static final double[] COEFFICIENTS = {
            676.5203681218851, -1259.1392167224028, 771.32342877765313,
            -176.61502916214059, 12.507343278686905, -0.13857109526572012,
            9.9843695780195716e-6, 1.5056327351493116e-7
    };

    /**
     * Lanczos approximation constant G.
     */
    private static final double LANCZOS_CONSTANT_G = 7.0;

    /**
     * Value of Pi (π).
     */
    private static final double PI = Math.PI;

    /**
     * Small value used as the tolerance for floating-point comparisons.
     */
    private static final double EPSILON = 1e-15;

    /**
     * Computes the natural logarithm of the Gamma function using the Lanczos
     * approximation.
     *
     * @param x The input value for which to compute the Gamma function.
     * @return The natural logarithm of the Gamma function for the given input.
     */
    public static double logGamma(double x) {
        final double limit = 0.5;
        if (x < limit) {
            // Use reflection formula for x < 0.5
            double sinValue = customSin(PI * x);
            double gammaValue = logGamma(1 - x);

            if (sinValue == 0 || gammaValue == 0) {
                throw new ArithmeticException("Denominator is zero, cannot compute the logarithm.");
            }
            return Math.log(PI / (sinValue * gammaValue));
        }

        x -= 1;
        double counter = 0.99999999999980993;
        double t = x + LANCZOS_CONSTANT_G + limit;

        // Perform the coefficient series summation
        for (int i = 0; i < COEFFICIENTS.length; i++) {
            counter += COEFFICIENTS[i] / (x + i + 1);
        }

        // Lanczos approximation formula
        return Math.log(Math.sqrt(2 * PI) * counter)
                +
                (x + limit) * Math.log(t) - t;
    }

    /**
     * Computes the square root of a number using the Newton-Raphson method.
     *
     * @param value The value to compute the square root of.
     * @return The square root of the given value.
     */
    public static double customSqrt(final double value) {
        if (value == 0) {
            return 0;
        }

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
    public static double customPow(final double base, final double exponent) {
        if (exponent == 0) {
            return 1.0;
        }
        if (exponent < 0) {
            return 1.0 / customPow(base, -exponent);
        }

        double result = 1.0;
        double currentBase = base;
        int intExponent = (int) exponent;  // Integer part of the exponent

        while (intExponent > 0) {
            if ((intExponent & 1) == 1) {
                result *= currentBase;
            }
            currentBase *= currentBase;
            intExponent >>= 1;
        }

        double fractionExponent = exponent - (int) exponent;
        if (fractionExponent != 0.0) {
            result *= Math.exp(fractionExponent * Math.log(base));
        }

        return result;
    }

    /**
     * Computes the exponential of a value using a series expansion.
     *
     * @param value The value to compute the exponential of.
     * @return The exponential of the given value.
     */
    public static double customExp(final double value) {
        double result = 1.0;
        double term = 1.0;

        for (int n = 1; Math.abs(term) > EPSILON; n++) {
            term *= value / n;
            result += term;
        }

        return result;
    }

    /**
     * Computes the natural logarithm of
     * a value using the Taylor series expansion.
     *
     * @param value The value to compute the natural logarithm of.
     * @return The natural logarithm of the given value.
     */
    public static double customLog(final double value) {
        if (value <= 0) {
            throw new
                    IllegalArgumentException("Logarithm input must be positive");
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
    public static double customSin(double value) {
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
     * Main method to initialize the GUI and handle user input for computing
     * the Gamma function.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        GammaProject gammaProject = new GammaProject();
        new GUI(gammaProject);
    }
    }
