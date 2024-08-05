/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.mycompany.gammaproject.GammaProject;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author yehia
 */

public class UnitTest {
    @Test
    public void testLogGamma() {
        double result = GammaProject.logGamma(5.0);
        double expected = 3.1780538303479458;  // Expected value for logGamma(5)
        assertEquals(expected, result, 1e-9);  // Use delta for floating-point comparison
    }

    @Test
    public void testCustomSqrt() {
        double result = GammaProject.customSqrt(25.0);
        double expected = 5.0;
        assertEquals(expected, result, 1e-9);  // Use delta for floating-point comparison
    }

    @Test
    public void testCustomPow() {
        double result = GammaProject.customPow(2.0, 3.0);
        double expected = 8.0;  // 2^3 = 8
        assertEquals(expected, result, 1e-9);  // Use delta for floating-point comparison
    }

    @Test
    public void testCustomExp() {
        double result = GammaProject.customExp(1.0);
        double expected = Math.exp(1.0);  // Expected value for e^1
        assertEquals(expected, result, 1e-9);  // Use delta for floating-point comparison
    }

    @Test
    public void testCustomSin() {
        double result = GammaProject.customSin(Math.PI / 2);
        double expected = 1.0;  // Expected value for sin(π/2)
        assertEquals(expected, result, 1e-9);  // Use delta for floating-point comparison
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCustomLogForNegativeInput() {
        GammaProject.customLog(-1.0);  // Should throw IllegalArgumentException
    }
//    @Test
//    public void testLogGammaWithSmallValue() {
//        double result = GammaProject.logGamma(0.1);
//        double expected = 2.252712651734205;  // Expected value for logGamma(0.1)
//        assertEquals(expected, result, 1e-9);
//    }

    @Test
    public void testLogGammaWithLargeValue() {
        double result = GammaProject.logGamma(100.0);
        double expected = 359.1342053695754;  // Expected value for logGamma(100.0)
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testLogGammaWithFraction() {
        double result = GammaProject.logGamma(2.5);
        double expected = 0.2846828704729192;  // Expected value for logGamma(2.5)
        assertEquals(expected, result, 1e-9);
    }
    @Test
    public void testCustomSqrtWithZero() {
        double result = GammaProject.customSqrt(0.0);
        double expected = 0.0;  // sqrt(0) should be 0
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomSqrtWithOne() {
        double result = GammaProject.customSqrt(1.0);
        double expected = 1.0;  // sqrt(1) should be 1
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomSqrtWithSmallValue() {
        double result = GammaProject.customSqrt(0.25);
        double expected = 0.5;  // sqrt(0.25) should be 0.5
        assertEquals(expected, result, 1e-9);
    }
    @Test
    public void testCustomPowWithZeroExponent() {
        double result = GammaProject.customPow(5.0, 0.0);
        double expected = 1.0;  // Any number to the power of 0 is 1
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomPowWithNegativeExponent() {
        double result = GammaProject.customPow(2.0, -2.0);
        double expected = 0.25;  // 2^(-2) = 1/(2^2) = 1/4 = 0.25
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomPowWithFractionalExponent() {
        double result = GammaProject.customPow(4.0, 0.5);
        double expected = 2.0;  // 4^0.5 = sqrt(4) = 2
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomPowWithNegativeBaseAndEvenExponent() {
        double result = GammaProject.customPow(-2.0, 2.0);
        double expected = 4.0;  // (-2)^2 = 4
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomPowWithNegativeBaseAndOddExponent() {
        double result = GammaProject.customPow(-2.0, 3.0);
        double expected = -8.0;  // (-2)^3 = -8
        assertEquals(expected, result, 1e-9);
    }
    @Test
    public void testCustomExpWithZero() {
        double result = GammaProject.customExp(0.0);
        double expected = 1.0;  // e^0 = 1
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomExpWithNegativeValue() {
        double result = GammaProject.customExp(-1.0);
        double expected = 1 / Math.exp(1.0);  // e^-1 = 1/e
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomExpWithLargeValue() {
        double result = GammaProject.customExp(10.0);
        double expected = Math.exp(10.0);  // e^10
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomExpWithFraction() {
        double result = GammaProject.customExp(0.5);
        double expected = Math.exp(0.5);  // e^0.5
        assertEquals(expected, result, 1e-9);
    }
    @Test
    public void testCustomSinWithZero() {
        double result = GammaProject.customSin(0.0);
        double expected = 0.0;  // sin(0) = 0
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomSinWithPi() {
        double result = GammaProject.customSin(Math.PI);
        double expected = 0.0;  // sin(π) = 0
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomSinWithPiOverTwo() {
        double result = GammaProject.customSin(Math.PI / 2);
        double expected = 1.0;  // sin(π/2) = 1
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomSinWithNegativeValue() {
        double result = GammaProject.customSin(-Math.PI / 2);
        double expected = -1.0;  // sin(-π/2) = -1
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void testCustomSinWithLargeValue() {
        double result = GammaProject.customSin(10 * Math.PI);
        double expected = 0.0;  // sin(10π) = 0
        assertEquals(expected, result, 1e-9);
    }
}
