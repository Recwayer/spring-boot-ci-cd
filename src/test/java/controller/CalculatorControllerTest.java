package controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.controller.CalculatorController;
import ru.example.dto.CalculationRequest;
import ru.example.service.CalculatorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorControllerTest {

    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;


    @Test
    void testAddEndpoint() {
        when(calculatorService.add(5.0, 3.0)).thenReturn(8.0);

        double result = calculatorController.add(5.0, 3.0);

        assertEquals(8.0, result, 0.001);
        verify(calculatorService, times(1)).add(5.0, 3.0);
    }

    @Test
    void testSubtractEndpoint() {
        when(calculatorService.subtract(5.0, 3.0)).thenReturn(2.0);

        double result = calculatorController.subtract(5.0, 3.0);

        assertEquals(2.0, result, 0.001);
        verify(calculatorService, times(1)).subtract(5.0, 3.0);
    }

    @Test
    void testMultiplyEndpoint() {
        when(calculatorService.multiply(5.0, 3.0)).thenReturn(15.0);

        double result = calculatorController.multiply(5.0, 3.0);

        assertEquals(15.0, result, 0.001);
        verify(calculatorService, times(1)).multiply(5.0, 3.0);
    }

    @Test
    void testDivideEndpointSuccess() {
        when(calculatorService.divide(10.0, 2.0)).thenReturn(5.0);

        double result = calculatorController.divide(10.0, 2.0);

        assertEquals(5.0, result, 0.001);
        verify(calculatorService, times(1)).divide(10.0, 2.0);
    }

    @Test
    void testDivideEndpointByZero() {
        when(calculatorService.divide(10.0, 0.0))
                .thenThrow(new IllegalArgumentException("Cannot divide by zero"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculatorController.divide(10.0, 0.0)
        );

        assertEquals("Cannot divide by zero", exception.getMessage());
        verify(calculatorService, times(1)).divide(10.0, 0.0);
    }

    @Test
    void testCalculateEndpointAdd() {
        CalculationRequest request = new CalculationRequest();
        request.setA(5.0);
        request.setB(3.0);
        request.setOperation("add");

        when(calculatorService.add(5.0, 3.0)).thenReturn(8.0);

        double result = calculatorController.calculate(request);

        assertEquals(8.0, result, 0.001);
        verify(calculatorService, times(1)).add(5.0, 3.0);
    }

    @Test
    void testCalculateEndpointSubtract() {
        CalculationRequest request = new CalculationRequest();
        request.setA(10.0);
        request.setB(4.0);
        request.setOperation("subtract");

        when(calculatorService.subtract(10.0, 4.0)).thenReturn(6.0);

        double result = calculatorController.calculate(request);

        assertEquals(6.0, result, 0.001);
        verify(calculatorService, times(1)).subtract(10.0, 4.0);
    }

    @Test
    void testCalculateEndpointMultiply() {
        CalculationRequest request = new CalculationRequest();
        request.setA(7.0);
        request.setB(3.0);
        request.setOperation("multiply");

        when(calculatorService.multiply(7.0, 3.0)).thenReturn(21.0);

        double result = calculatorController.calculate(request);

        assertEquals(21.0, result, 0.001);
        verify(calculatorService, times(1)).multiply(7.0, 3.0);
    }

    @Test
    void testCalculateEndpointDivide() {
        CalculationRequest request = new CalculationRequest();
        request.setA(15.0);
        request.setB(3.0);
        request.setOperation("divide");

        when(calculatorService.divide(15.0, 3.0)).thenReturn(5.0);

        double result = calculatorController.calculate(request);

        assertEquals(5.0, result, 0.001);
        verify(calculatorService, times(1)).divide(15.0, 3.0);
    }

    @Test
    void testCalculateEndpointDivideByZero() {
        CalculationRequest request = new CalculationRequest();
        request.setA(5.0);
        request.setB(0.0);
        request.setOperation("divide");

        when(calculatorService.divide(5.0, 0.0))
                .thenThrow(new IllegalArgumentException("Cannot divide by zero"));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculatorController.calculate(request)
        );

        assertEquals("Cannot divide by zero", exception.getMessage());
        verify(calculatorService, times(1)).divide(5.0, 0.0);
    }

    @Test
    void testHealthEndpoint() {
        String result = calculatorController.health();

        assertEquals("OK", result);
        verifyNoInteractions(calculatorService);
    }

    @Test
    void testAddEndpointWithNegativeNumbers() {
        when(calculatorService.add(-5.0, 3.0)).thenReturn(-2.0);

        double result = calculatorController.add(-5.0, 3.0);

        assertEquals(-2.0, result, 0.001);
        verify(calculatorService, times(1)).add(-5.0, 3.0);
    }

    @Test
    void testAddEndpointWithDecimals() {
        when(calculatorService.add(2.5, 1.5)).thenReturn(4.0);

        double result = calculatorController.add(2.5, 1.5);

        assertEquals(4.0, result, 0.001);
        verify(calculatorService, times(1)).add(2.5, 1.5);
    }
}
