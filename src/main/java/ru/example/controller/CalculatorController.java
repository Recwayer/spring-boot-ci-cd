package ru.example.controller;


import org.springframework.web.bind.annotation.*;
import ru.example.dto.CalculationRequest;
import ru.example.service.CalculatorService;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) {
        return calculatorService.add(a, b);
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam double a, @RequestParam double b) {
        return calculatorService.subtract(a, b);
    }

    @GetMapping("/multiply")
    public double multiply(@RequestParam double a, @RequestParam double b) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/divide")
    public double divide(@RequestParam double a, @RequestParam double b) {
        return calculatorService.divide(a, b);
    }

    @PostMapping("/calculate")
    public double calculate(@RequestBody CalculationRequest request) {
        return switch (request.getOperation()) {
            case "add" -> calculatorService.add(request.getA(), request.getB());
            case "subtract" -> calculatorService.subtract(request.getA(), request.getB());
            case "multiply" -> calculatorService.multiply(request.getA(), request.getB());
            case "divide" -> calculatorService.divide(request.getA(), request.getB());
            default -> throw new IllegalArgumentException("Invalid operation");
        };
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
