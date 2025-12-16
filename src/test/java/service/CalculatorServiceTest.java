package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.example.service.CalculatorService;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testAdd() {
        double a = 5.0;
        double b = 3.0;

        double result = calculatorService.add(a, b);

        assertEquals(8.0, result, 0.001);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 3, 8",
            "0, 0, 0",
            "-5, 3, -2",
            "2.5, 1.5, 4.0"
    })
    void testAddParameterized(double a, double b, double expected) {
        assertEquals(expected, calculatorService.add(a, b), 0.001);
    }

    @Test
    void testSubtract() {
        assertEquals(2.0, calculatorService.subtract(5.0, 3.0), 0.001);
    }

    @Test
    void testMultiply() {
        assertEquals(15.0, calculatorService.multiply(5.0, 3.0), 0.001);
    }

    @Test
    void testDivide() {
        assertEquals(2.5, calculatorService.divide(5.0, 2.0), 0.001);
    }

    @Test
    void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculatorService.divide(5.0, 0.0)
        );

        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    void testDivideNegativeNumbers() {
        assertEquals(-2.5, calculatorService.divide(-5.0, 2.0), 0.001);
    }

    @Test
    void testAddWithLargeNumbers() {
        assertEquals(1.5E8, calculatorService.add(1.0E8, 0.5E8), 0.1);
    }
}
